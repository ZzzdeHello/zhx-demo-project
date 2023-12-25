package collection.ListOrder;

import java.util.*;

public class TEST {
//    public static void main(String[] args) {
//        String json = "[{\"dispatchTime\":\"2020-10-06 15:17:47\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33028202096\",\"orderId\":\"80000473758021134\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"},{\"dispatchTime\":\"2020-10-06 15:18:37\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33028202096\",\"orderId\":\"80000473758021118\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"},{\"dispatchTime\":\"2020-10-06 15:19:24\",\"dispatchType\":\"1\",\"areaId\":\"7113\",\"dispatchSslesCode\":\"Y33020110488\",\"orderId\":\"80000448820048807\",\"contactTel\":\"18106659592\",\"salesstaffCode\":\"Y33021193533\"},{\"dispatchTime\":\"2020-10-06 15:19:24\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33020110488\",\"orderId\":\"80000448820048807\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"},{\"dispatchTime\":\"2020-10-06 15:19:24\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33028202096\",\"orderId\":\"80000473758067150\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"},{\"dispatchTime\":\"2020-10-06 15:20:10\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33028202096\",\"orderId\":\"80000473758021134\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"},{\"dispatchTime\":\"2020-10-06 15:21:01\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33028202096\",\"orderId\":\"80000473758020558\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"},{\"dispatchTime\":\"2020-10-06 15:21:54\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33028202096\",\"orderId\":\"80000473758019038\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"},{\"dispatchTime\":\"2020-10-06 15:22:21\",\"dispatchType\":\"1\",\"areaId\":\"9115\",\"dispatchSslesCode\":\"Y33028202096\",\"orderId\":\"80000473758014366\",\"contactTel\":\"18069056778\",\"salesstaffCode\":\"Y33028288065\"}]" ;
//                Map map = new HashMap();
//        map.put("paramList", JSON.parse(json));
//        List<Map<String, Object>> paramList =  (List<Map<String,Object>>)map.get("paramList");
//        paramList.stream().forEach(map1->System.out.println(JSON.toJSONString(map1)));
//    }
    public static void main(String[] args) {
        List limitList = new ArrayList<String>(3);
        limitList.add("S1");limitList.add("S2");
        limitList.add("S3");
        limitList.add("S4");
        limitList.stream().forEach(System.out::println);
    }
}
