package mytest;

import mytest.Conf;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Conf.class)
public class EsRefreshTest {

    private static final  String FILE_KEY_WORK = "_esRefresh"; // 文件名称关键字，包含时才处理

    @Resource
    EsServiceUtil esServiceUtil;
    //1、CSV文件流读取 一行为一条数据，逗号隔开为一个字段。orderId（custOrderNbr）；contact_org;staffCode
    //2、 pst 测试单条数据 是否能更新成功。
    @Test
    public void testCustomerAccessTrackQuery() throws Exception {
//        String startTimeStr = "2021-07-06";
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse = simpleDateFormat.parse(startTimeStr);
        System.out.println("start...");
        Map<String,Object> param = new HashMap();
        param.put("sysSource","营服协同中心");
        param.put("custOrderNbr","80000016221002893");
        SearchHits searchHits = esServiceUtil.queryHits(param, "cust_access_trace_2021", "cust_access_trace",10);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("accessBusiType","服务外呼");
        dataMap.put("busiTypeLevelFirst","杭州-星级-积分翻倍兑");
        esServiceUtil.update(searchHits.getAt(0),dataMap);
        System.out.println("end...");
    }

    @Test
    public void testEsRefresh(){
        try{
            String localDir = System.getProperty("user.dir") + "/file/es/";
            File localFile = new File(localDir);
            File[] listFile = localFile.listFiles();
            if(listFile == null){
                System.out.println("文件目录为空");
                return;
            }
            if( listFile.length > 0 ) {
                for (File file : listFile) {
                    System.out.println(file.getName() + "file.start...");
                    parseFile(file);
                    System.out.println(file.getName() + "file.end...");
                }
            }
//            if( listFile.length > 0 ){
//                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);//按文件开始解析文件池
//                for( File file : listFile ){
//                    fixedThreadPool.execute(()->{
//                        //在本地服务器 按批次单线程解析文件
//                        System.out.println(file.getName() +"file.start...");
//                        parseFile(file);
//                        System.out.println(file.getName() +"file.end...");
//                    });
//                }
//                fixedThreadPool.shutdown();
//            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * 文件解析并刷新
     * @param
     */
    private void parseFile(File parsingFile){
        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bfReader = null;
        try{
            String localErrorDir =  System.getProperty("user.dir") + "/file/es/error/";
            List<String> errorList = new ArrayList<>();
            if( parsingFile == null ){
                System.out.println("File为null直接返回");
                return;
            }
            if(parsingFile.getName().endsWith(".csv") && parsingFile.getName().contains(FILE_KEY_WORK)){
                    System.out.println("开始读取文件:"+parsingFile.getName());
                    // 解析文件
                    inputStream = new FileInputStream(parsingFile);
                    inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    bfReader = new BufferedReader(inputStreamReader,5*1024*1024);// 分5MB 缓存文件读取
                    //解析数据
                    String lineData = "";
                    int line = 0 ;
                    while ( (lineData = bfReader.readLine()) !=null ){
                        System.out.println(lineData);
                        line++;
                        String[] fields = lineData.split(",",-1);
                        String custOrderNbr = fields[0];
                        String busiTypeLevelFirst = fields[1];
                        String accessBusiType = fields[2];
                        Map<String,Object> upDateMap = new HashMap<>();
                        try {
                            if(StringUtils.isEmpty(custOrderNbr)){
                                continue;
                            }
                            upDateMap.put("accessBusiType",accessBusiType);
                            upDateMap.put("busiTypeLevelFirst",busiTypeLevelFirst);
                            boolean isUpdate = esUpdateMethod(custOrderNbr, "营服协同中心", upDateMap);
                            if(!isUpdate){
                                //写入本地文件,记录出异常数据 ：lineData
                                errorList.add(lineData);
                            }
                        }catch (Exception e){
                            //写入本地文件,记录出异常数据 ：lineData
                            System.out.println("文件的第"+ line +"行的字段有问题出现异常记录error文件");
                            errorList.add(lineData);
                        }
                        //10000条异常是新写一个文件
                        if(errorList.size()>10000){
                            dealErrorMsg(localErrorDir,errorList);
                            errorList.clear();
                        }
                    }
                    dealErrorMsg(localErrorDir,errorList);
                    System.out.println("一共执行的行数为:"+line);
                }

        }catch (Exception  e) {
            System.out.println("文件解析失败");
            e.printStackTrace();
        }finally {
            try {
                if(bfReader != null){
                    bfReader.close();
                }
                if(inputStreamReader != null){
                    inputStreamReader.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            }catch (Exception e){
                System.out.println("流关闭异常");
                e.printStackTrace();
            }

        }

    }

    private boolean esUpdateMethod(String custOrderNbr , String sysSource ,Map<String,Object>  updateMap ){
        boolean b = true;
        try{
            Map<String,Object> param = new HashMap();
            param.put("custOrderNbr",custOrderNbr);
            param.put("sysSource",sysSource);
            SearchHits searchHits = esServiceUtil.queryHits(param, "cust_access_trace_2021", "cust_access_trace",10);
            if(searchHits.totalHits > 0){
                for( SearchHit searchHit : searchHits ){
                    esServiceUtil.update(searchHit,updateMap);
                }
            }else {
                System.out.println("条件: " + custOrderNbr + "&"+sysSource +" ,没有查询到数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    //当出错量大于10000条数据时，记录进本地文件
    public void dealErrorMsg(String localDir, List<String> data) throws Exception {
        File fileCsv = null;

        File localFileDir = new File(localDir);
        if (!localFileDir.exists()) {
            localFileDir.mkdirs();
        }
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateStr = sdf.format(now);

        String errorFile = "error_" + dateStr + "_esRefresh" + ".csv";
        //csv文件
        fileCsv = new File(localDir + errorFile);
        FileOutputStream output = new FileOutputStream(fileCsv, true);
        OutputStreamWriter outputStream = new OutputStreamWriter(output, "UTF-8");// 文件写入流
        BufferedWriter writer = new BufferedWriter(outputStream, 5 * 1024 * 1024);
        try {
            if (data.size() > 0) {
                try {
                    //写文件
                    for (int i = 0; i < data.size(); i++) {
                        String lineStr = data.get(i);
                        writer.write(lineStr);
                        writer.newLine();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
            outputStream.close();
            output.close();
        }

    }

}
