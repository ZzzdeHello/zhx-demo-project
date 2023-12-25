package zzzde.code.technic.collection.sort;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import zzzde.code.technic.codejson.AttrValueVo;
import zzzde.code.technic.collection.map.filter.TwBizOrderTypeEnum;

import java.text.SimpleDateFormat;

import java.util.*;
import java.util.stream.Collectors;

public class TestDateSort {
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

        String sortordForDate = "asc";
        String sortordForType = "3";
        List<Map<String, Object>> order = sortFilterList(sortordForDate, sortordForType, orderList);
        System.out.println(JSONObject.toJSONString(order));


    }

    //利用集合内某元素来排序 重写 Comparator 比较器
    public static List<Map<String,Object>>  order(String sortordForType ,String sortordForDate, List<Map<String,Object>> orderList ){

        //判断顺序/倒叙
        switch (sortordForDate) {
                case "desc" : {// 降序
                    orderList.sort((o1, o2) -> {
                        String name1 = String.valueOf(o1.get("orderCreateDate"));
                        String name2 = String.valueOf(o2.get("orderCreateDate"));
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

        switch (sortordForType) {
            case "Y" : {// 根据类型排序
                Collections.sort(orderList, (o1, o2) -> {
                    String name1=String.valueOf(o1.get("orderType"));
                    String name2=String.valueOf(o2.get("orderType"));
                    return name1.compareTo(name2);
                });
                break;
            }
            default:
        }
        return orderList;
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
        return orderType;
    }


    //批次排序测试  （ 1-2 为升序 加一个 ‘-’ ）为降序   2-1 为降序
    public String  test1(){
        List<Integer> pushOrderList = new ArrayList<>();
        pushOrderList.add(1);pushOrderList.add(3);
        pushOrderList.add(10);pushOrderList.add(9);
        Collections.sort(pushOrderList, (o1, o2) -> {
            return -Integer.compare(Integer.valueOf(o1), Integer.valueOf(o2));
        });
        System.out.println(JSONUtils.toJSONString(pushOrderList));
        return JSONUtils.toJSONString(pushOrderList).toString();
    }
}
