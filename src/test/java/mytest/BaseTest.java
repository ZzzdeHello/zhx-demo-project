package mytest;

//import excelPackage.readExcel.DemoDataListener;
import org.junit.Test;
import org.junit.runner.RunWith;
        import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zzzde.project.technic.projmybatis.serevice.TestPstEvn;

import javax.annotation.Resource;
        import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContextConfiguration(locations = {"classpath*:application-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

//    @Resource
//    ProductDao productDao;
//    @zzzde.demo.main.Test
//    public void test(){
//        List<Map> maps = productDao.selectProduct();
//        System.out.println(maps.toString());
//    }

//    @zzzde.demo.main.Test
//    public void test2(){
//        List<Map> maps = productDao.selectProductOrShopProduct("1");
//        System.out.println(maps.toString());
//    }
    @Resource
    TestPstEvn testPstEvn;

    @Test
    public void test1(){
        String s = testPstEvn.testPstEvn();
        System.out.println(s);
    }


    /**
     * 最简单的写
     * <p>1. 创建excel对应的实体对象 参照{@link}
     * <p>2. 直接写即可
     */
//    @zzzde.demo.main.Test
//    public void simpleWrite() throws Exception {
//        // 写法1
//        String fileNamePath = "E:\\WorkProject\\DemoDATEBASE1\\src\\main\\java\\excelPackage\\writeExcel\\writeExcel";
//        String fileName = fileNamePath + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, ProductData.class).sheet("模板1").doWrite(data());
//
//         // 写法2
//        fileName = fileNamePath + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = null;
//        try {
//            excelWriter = EasyExcel.write(fileName, ProductData.class).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(data(), writeSheet);
//        } finally {
//            // 千万别忘记finish 会帮忙关闭流
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }
//    }
//`product_id` char(4) NOT NULL,
//  `product_name` varchar(100) NOT NULL,
//  `product_type` varchar(32) NOT NULL,
//  `sale_price` int(11) DEFAULT NULL,
//  `purchase_price` int(11) DEFAULT NULL,
//  `regist_date` zzzde.code.technic.date DEFAULT NULL,
//  `da` bigint(16) DEFAULT NULL,
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    private List<ProductData> data() throws ParseException {
//        List<ProductData> list = new ArrayList<ProductData>();
//        List<Map> maps = productDao.selectProduct();
//        for (Map map :maps) {
//            ProductData productData = new ProductData();
//            productData.setProductId(String.valueOf(map.get("product_id")));
//            productData.setProductName(String.valueOf(map.get("product_name")));
//            productData.setSalePrice(Integer.valueOf(String.valueOf(map.get("sale_price"))));
//            productData.setPurchasePrice(Integer.valueOf(String.valueOf(map.get("purchase_price"))));
//            productData.setRegistDate(simpleDateFormat.parse(String.valueOf(map.get("regist_date"))));
//            productData.setDa(String.valueOf(map.get("da")));
//            list.add(productData);
//        }
//        return list;


        @Test
    public void testEsRefresh(){
        System.out.println("start...");
        // parseFile();
        System.out.println("end...");
    }
    /**
     * 文件解析并刷新
     * @param
     */
//    private void parseFile(){
//        FileInputStream inputStream = null;
//        InputStreamReader inputStreamReader = null;
//        BufferedReader bfReader = null;
//        try{
//            String localDir = System.getProperty("user.dir") + "/zzzde.project.technic.file/es/";
//            String localErrorDir =  System.getProperty("user.dir") + "/zzzde.project.technic.file/es/error/";
//            List<String> errorList = new ArrayList<>();
//            File localFile = new File(localDir);
//            File[] listFile = localFile.listFiles();
//            if(listFile == null ){
//                System.out.println("listFile为null直接返回");
//                return;
//            }
//            for(File parsingFile : listFile ){
//                if(parsingFile.getName().endsWith(".csv") && parsingFile.getName().contains(fileKeyWord)){
//                    System.out.println("开始读取文件:"+parsingFile.getName());
//                    // 解析文件
//                    inputStream = new FileInputStream(parsingFile);
//                    inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//                    bfReader = new BufferedReader(inputStreamReader,5*1024*1024);// 分5MB 缓存文件读取
//                    //解析数据
//                    String lineData = "";
//                    int line = 0 ;
//                    while ( (lineData = bfReader.readLine()) !=null ){
//                        line++;
//                        String[] fields = lineData.split(",",-1);
//                        String custNbr = fields[0];
//                        String contactOrg = fields[1];
//                        String staffCode = fields[2];
//                        Map<String,Object> upDateMap = new HashMap<>();
//                        try {
//                            if(StringUtils.isEmpty(custNbr)){
//                                continue;
//                            }
//                            System.out.println("工单为"+custNbr);
//                            if(StringUtils.isEmpty(contactOrg)){
//                                upDateMap.put("companyInfoFirst","");
//                                upDateMap.put("companyInfoSecond","");
//                                upDateMap.put("companyInfoThird","");
//                            }
//                            if(StringUtils.isEmpty(staffCode)){
//                                upDateMap.put("accessStaffCode","");
//                                upDateMap.put("accessStaffName","");
//                                upDateMap.put("accessStaffPhone","");
//                            }
//                            upDateMap.put("isActive","被动");
//                            // boolean isUpdate = esUpdateMethod(custNbr, "营服协同中心", upDateMap);
//                            //if(!isUpdate){
//                                //写入本地文件,记录出异常数据 ：lineData
//                                //errorList.add(lineData);
//                           // }
//                        }catch (Exception e){
//                            //写入本地文件,记录出异常数据 ：lineData
//                            System.out.println("文件的第"+ line +"行的字段有问题出现异常记录error文件");
//                            errorList.add(lineData);
//                        }
//                        //10000条异常是新写一个文件
//                        if(errorList.size()>10000){
//                            // dealErrorMsg(localErrorDir,errorList);
//                            errorList.clear();
//                        }
//                    }
//
//                }
//
//            }
//        }catch (Exception  e) {
//            System.out.println("文件解析失败");
//            e.printStackTrace();
//        }finally {
//            try {
//                if(bfReader != null){
//                    bfReader.close();
//                }
//                if(inputStreamReader != null){
//                    inputStreamReader.close();
//                }
//                if(inputStream != null){
//                    inputStream.close();
//                }
//            }catch (Exception e){
//                System.out.println("流关闭异常");
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    @Test
    public void test01(){
        Map map = new HashMap();map.put("1","map1");
        Map map1 = new HashMap();map1.put("1","map2");
        Map map2 = new HashMap();map2.put("1","map3");
        Map map3 = new HashMap();map3.put("1","map4");
        Map map4 = new HashMap();map4.put("1","map5");
        List<Map> orderList = new ArrayList<>();
        orderList.add(map);
        orderList.add(map1);
        orderList.add(map2);
        orderList.add(map3);
        orderList.add(map4);
        int listSize = orderList.size();
        Integer pages = getPage(listSize);// 获取总页数
        for ( int page = 1 ; page <= pages ; page ++ ) {
            System.out.println("page" + page);
            List<Map> tempPageList = getTempPageList(orderList, page);
            System.out.println(tempPageList.toString());
            List<Map> tempList = assmberEroor(tempPageList);
            System.out.println(tempList);
            System.out.println("-------");
        }
    }

    // list新增 map的时候 会新增在旧 list 上。因为还是返回了原来的 list 地址。
    private List<Map> assmberEroor(List<Map> list){
        Map map = new HashMap();
        map.put("meth01","1111");
        list.add(map);
        return list;
    }

    private List<Map> assmber(List<Map> list){
        for(Map map :list){
            map.put("2","assmber++");
        }
        return list;
    }

    /**
     * 手动分页
     */
    private List<Map> getTempPageList (List<Map> list, int page){
        int listSize = list.size();
        int fromIndex;
        int toIndex;
        fromIndex = (page - 1) * 2;
        toIndex = (page) * 2;
        if (toIndex > listSize) {
            toIndex = listSize;
        }
        //截取一段集合数据
        System.out.println("getTempPageList的" + fromIndex + "|"+toIndex);
        return list.subList(fromIndex, toIndex);
    }

    /**
     *  获取分页
     *  @param listSize 总list大小
     */
    private  Integer getPage ( int listSize){
        int page = listSize / 2;
        int surplus = listSize % 2;
        if (0 != surplus) {
            page++;
        }
        return page;
    }
}

