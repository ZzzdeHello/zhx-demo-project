package zzzde.code.technic.collection.sort;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CoolectionSortTest02 {
    public static void main(String[] args) {
        //模拟数据
        List<Map<String,Object>> contactTaskExtDfnList = new ArrayList();
        Map map01 = new HashMap(); map01.put("COL_IDX",1);
        Map map03 = new HashMap(); map03.put("COL_IDX",3);
        Map map02 = new HashMap(); map02.put("COL_IDX",2);
        contactTaskExtDfnList.add(map02);
        contactTaskExtDfnList.add(map03);
        contactTaskExtDfnList.add(map01);
        System.out.println(JSONUtils.toJSONString(contactTaskExtDfnList));
        //排序
        List<Map<String, Object>> collect = contactTaskExtDfnList.stream().sorted((map1, map2) ->
                Integer.compare(
                        Integer.valueOf(Optional.ofNullable(map1.get("COL_IDX")).orElse("0").toString()),
                        Integer.valueOf(Optional.ofNullable(map2.get("COL_IDX")).orElse("0").toString())
                )
        ).collect(Collectors.toList());
        System.out.println(JSONUtils.toJSONString(collect));
    }
}
