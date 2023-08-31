package collection.sort;

import com.alibaba.fastjson.JSONObject;
import json.AttrValueVo;
import collection.map.filter.TwBizOrderTypeEnum;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TestDateSort2 {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        List<Map<String,Object>> orderList = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i <7; i++) {
            instance.set(Calendar.HOUR,-i);
            Date instanceTimeTemp = instance.getTime();
            long time = instanceTimeTemp.getTime();
            Map<String, Object> map = new HashMap<>();
            map.put("orderCreateDate",time);
            if(i ==1){
                map.put("orderType","O2O");
            }else if (i==2 || i ==3 || i ==4){
                map.put("orderType",3);
            }else {
                map.put("orderType","4");
            }
            orderList.add(map);
            System.out.println(map.get("orderCreateDate"));
            String timestamp =simpleDateFormat.format(new Date(time));
            System.out.println(timestamp);
        }

        System.out.println(JSONObject.toJSONString(orderList));

        String sortordForDate = "desc";
        String sortordForType = "All";
        List<Map<String, Object>> order = sortFilterList(sortordForDate, sortordForType, orderList);
        System.out.println(JSONObject.toJSONString(order));


    }

    //利用对象属性、lambda表达式 排序
    public static List<AttrValueVo> sortList (List<AttrValueVo> list){
        List<AttrValueVo> names = list.stream().sorted(Comparator.comparing(AttrValueVo::getAttrValueName).reversed()).collect(Collectors.toList());
        names.stream().forEach(System.out::println);
        return names;
    }

    public static List<Map<String,Object>>  sortFilterList(String  sortordForDate,String sortordForType , List<Map<String,Object>> orderList ){


        //判断顺序/倒叙
        switch (sortordForDate) {
            case "desc" : {// 降序
                Collections.sort(orderList, (o1, o2) -> {
                    String name1=String.valueOf(o1.get("orderCreateDate"));
                    String name2=String.valueOf(o2.get("orderCreateDate"));
                    return name2.compareTo(name1);
                });
                break;
            }
            case "asc" :{//升序
                Collections.sort(orderList, (o1, o2) -> {
                    String name1=String.valueOf(o1.get("orderCreateDate"));
                    String name2=String.valueOf(o2.get("orderCreateDate"));
                    return name1.compareTo(name2);
                });
                break;
            }
        }

        List<Map<String, Object>> orderType =  orderList ;
        // 根据类型过滤
        switch (sortordForType) {
            case "O2O" : {
                orderType = orderList.stream().filter(map ->
                        String.valueOf(map.get("orderType")).equals(TwBizOrderTypeEnum.TW_ORDER_UNDERLINE.getValue())).collect(Collectors.toList());
                break;
            }
            case "3" : {
                 orderType = orderList.stream().filter(map ->
                        String.valueOf(map.get("orderType")).equals(TwBizOrderTypeEnum.TW_ORDER_ONLINE.getValue())).collect(Collectors.toList());
                break;
            }
            case "4" : {
                 orderType =orderList.stream().filter( map ->
                        String.valueOf(map.get("orderType")).equals(TwBizOrderTypeEnum.TW_ORDER_REMOVE
                                .getValue())).collect(Collectors.toList());
                break;
            }
            case "5" : {
                orderType =orderList.stream().filter( map ->
                        String.valueOf(map.get("orderType")).equals(TwBizOrderTypeEnum.TW_ORDER_FACE2FACE.getValue())).collect(Collectors.toList());
                break;
            }
            default:
        }

        for(Map<String, Object> map : orderType){
            String timestamp =simpleDateFormat.format(new Date((long)map.get("orderCreateDate")));
            map.put("orderCreateDate",timestamp);
        }
        return orderType;
    }
}
