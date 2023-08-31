package mytest;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ES scroll方式 ，执行深度分页处理大数据量批量数据
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Conf.class)
public class EsScrollGetTest {
    @Resource
    private Client client;
    //---------------------常量-----------------
    private static final  String FILE_KEY_WORD = "_esRefresh"; // 文件名称关键字，包含时才处理
    private static final String KEYWORD = ".keyword";
    private static final String indexName = "cust_access_trace";
    private static final String typeName = "cust_access_trace";
    private static final String ACCESS_TIME_DATE ="accessTimeDate";

    @Test
    public void test1() throws Exception{
        String localDir = System.getProperty("user.dir") + "/file/esFresh/";
        List <String> data = new ArrayList<>();
        Map<String,Object> param = new HashMap();
        param.put("sysSource","营服协同中心");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2021-07-06");
        param.put("startTime",parse);
        String notExistFieldName = "accessBusiType";

        Map scrollWithId = setScroll(param,notExistFieldName, 2021);
        List<String> custOrderNbrs = (List<String>) scrollWithId.get("custOrderNbrs");
        if(custOrderNbrs.size() >0 ){
            data.addAll(custOrderNbrs);
        }
        int i = 1 ;
        do{
            Map<String, Object> scrollMap = queryByScroll(scrollWithId.get("scrollId").toString());
            if ( scrollMap ==null  || scrollMap.isEmpty() || i >= 33 ){
                break;
            }
            List<String> temp = (List<String>)scrollMap.get("custOrderNbrs");
            data.addAll(temp);
            i++;
        }while(true);
        System.out.println("总执行次数"+ i);
        writeFile(localDir,data);
    }
    //设置游标值;并返回docId;scrollId
    private Map<String,Object> setScroll( Map<String, Object> conditionParamMap,String notExistFieldName,Integer indexYear ){
        List<String> data = new ArrayList<>();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch()
                .setIndices(indexName+"_"+indexYear)
                .setTypes(typeName);
        //1、创建查询构建器
        QueryBuilder queryBuilder = buildQueryBuilder(conditionParamMap,notExistFieldName);
        //2、查询条件置入请求
        searchRequestBuilder.setQuery(queryBuilder);
        //3、设置游标有效期，每个卷尺的大小
        searchRequestBuilder.setScroll(TimeValue.timeValueMinutes(3));
        searchRequestBuilder.setSize(10000);//每页大小。每次query都会查询出size大小的数据，最大为10000
        //执行查询
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        String scrollId = searchResponse.getScrollId();//获取返回的游标值。
        Map<String,Object> m = new HashMap<String,Object>();
        SearchHit[] hits = searchResponse.getHits().getHits();
        for(SearchHit hit :hits ){
            data.add(hit.getSourceAsMap().get("custOrderNbr").toString());
        }
        m.put("scrollId",scrollId);//获取返回的 游标值
        m.put("custOrderNbrs", data );// 需要保存的值
        System.out.println(JSON.toJSONString(m));
        return m;
    }
    private Map<String,Object> queryByScroll(String scrollId){
        List<String> data = new ArrayList<>();
        SearchResponse rep1 = client.prepareSearchScroll(scrollId)  //设置游标
                .setScroll(TimeValue.timeValueMinutes(3))  //设置游标有效期
                .execute()
                .actionGet();
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("scrollId",rep1.getScrollId());
        SearchHit[] hits = rep1.getHits().getHits();
        if(hits == null || hits.length == 0){
            return null;
        }else {
            for(SearchHit hit : hits ){
                data.add(hit.getSourceAsMap().get("custOrderNbr").toString());
            }
        }
        m.put("custOrderNbrs", data);
        return m;
    }

    public void writeFile(String localDir, List<String> data) throws Exception {
        File fileCsv = null;

        File localFileDir = new File(localDir);
        if (!localFileDir.exists()) {
            localFileDir.mkdirs();
        }
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateStr = sdf.format(now);

        String fileName = "custNbrs_" + dateStr + FILE_KEY_WORD + ".csv";
        //csv文件
        fileCsv = new File(localDir + fileName);
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

    /**
     * 构建查询builder
     *
     * @param queryMap 查询条件
     * @param notExistFieldName 不存在的字段
     * @return queryBuilder
     */
    private QueryBuilder buildQueryBuilder(Map<String, Object> queryMap,String notExistFieldName) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //字段条件放入 Set 获取 key\value
        Set<Map.Entry<String, Object>> entries = queryMap.entrySet();
        //时间参数以外的查询字段统一构建
        for (Map.Entry<String, Object> entry : entries) {
            if(!StringUtils.equals(entry.getKey(),"startTime") && !StringUtils.equals(entry.getKey(),"endTime")) {
                buildQueryBuilder(boolQueryBuilder, entry);
            }
        }
        //时间参数查询字段构建
        Date startTime = (Date)queryMap.get("startTime");
        Date endTime = (Date)queryMap.get("endTime");
        buildRangeQueryBuilder(boolQueryBuilder,startTime,endTime);

        // 字段不存在条件构建
        if(StringUtils.isNotEmpty(notExistFieldName)) {
            buildQueryBuilderWithNotFiled(boolQueryBuilder, notExistFieldName );
        }

        return boolQueryBuilder;
    }

    /**
     * 特殊方法：字段不存在方法构建
     * 构建查询builder
     *
     * @param boolQueryBuilder 目标builder
     * @param notExistFieldName  存在的字段
     */
    private void buildQueryBuilderWithNotFiled(BoolQueryBuilder boolQueryBuilder, String notExistFieldName) {
        ExistsQueryBuilder existsQueryBuilder = QueryBuilders.existsQuery(notExistFieldName);
        boolQueryBuilder.mustNot(existsQueryBuilder);

    }

    /**
     *  时间范围查询构建方法
     * @param boolQueryBuilder 构建对象
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    private void buildRangeQueryBuilder(BoolQueryBuilder boolQueryBuilder,Date startTime, Date endTime){
        if(startTime != null || endTime != null) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(ACCESS_TIME_DATE);
            if (startTime != null){
                rangeQueryBuilder.gt(startTime.getTime());
            }
            if (endTime != null){
                rangeQueryBuilder.lt(endTime.getTime());
            }
            boolQueryBuilder.must(rangeQueryBuilder);
        }
    }

    private void buildQueryBuilder(BoolQueryBuilder boolQueryBuilder, Map.Entry<String, Object> entry) {
        String key = entry.getKey();
        Object value = entry.getValue();
        if (StringUtils.isBlank(key) || Objects.isNull(value)) {
            return;
        }

        if (value instanceof String) {
            key = key + KEYWORD;
        }
        boolQueryBuilder.must(QueryBuilders.termQuery(key, value));
    }
}
