package mytest;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;
import zzzde.project.technic.trycatch.AssertUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class EsServiceUtil {

    @Resource
    private Client client;

    private static final  String FILE_KEY_WORD = "_esRefresh"; // 文件名称关键字，包含时才处理
    private static final String KEYWORD = ".keyword";
    private static final String indexName = "cust_access_trace";
    private static final String typeName = "cust_access_trace";
    private static final String ACCESS_TIME_DATE ="accessTimeDate";

    public SearchHits queryHits(Map<String,Object> paramMap, String index,String typeName, Integer pageSize){
        if (pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch()
                .setIndices(index)
                .setTypes(typeName);
        QueryBuilder queryBuilder = buildQueryBuilder(paramMap,"");
        searchRequestBuilder.setQuery(queryBuilder);
        searchRequestBuilder.setSize(pageSize);

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
        RestStatus status = searchResponse.status();
        if (status.getStatus()!= 200) {
            System.out.println("es Qeury zzzde.project.technic.trycatch.exception");
            return null;
        }
        return searchResponse.getHits();

    }

    public void update(SearchHit searchHit, Map<String, Object> dataMap) {
        UpdateRequestBuilder updateRequestBuilder = client.prepareUpdate(searchHit.getIndex(), searchHit.getType(),searchHit.getId());
        updateRequestBuilder.setDoc(dataMap);
        UpdateResponse updateResponse = updateRequestBuilder.execute().actionGet();
        RestStatus restStatus = updateResponse.status();
        AssertUtil.isTrue(RestStatus.OK.equals(restStatus),"更新es失败");
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
