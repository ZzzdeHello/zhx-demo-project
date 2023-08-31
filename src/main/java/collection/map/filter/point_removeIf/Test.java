package collection.map.filter.point_removeIf;

import collection.map.filter.CollectionUtil;
import collection.map.filter.TwBizOrderStatusCdEnum;
import collection.map.filter.TwBizOrderTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        Map stringObjectMap = new HashMap();

        List<Map<String, Object>> unDealAndUnSenList = new ArrayList();
        Map map = new HashMap();map.put("statusCd","1000");
        unDealAndUnSenList.add(map);
        Map map1 = new HashMap();map1.put("statusCd","10000");
        unDealAndUnSenList.add(map1);
        Map map3 = new HashMap();map3.put("statusCd","1000");
        unDealAndUnSenList.add(map3);
        Map map4 = new HashMap();map4.put("statusCd","10000");
        unDealAndUnSenList.add(map4);
        Map map5 = new HashMap();map5.put("statusCd","30000");
        unDealAndUnSenList.add(map5);
        Map map6 = new HashMap();map6.put("statusCd","1000");
        unDealAndUnSenList.add(map6);


        stringObjectMap.put("orderList",unDealAndUnSenList);
        List<Map<String, Object>> tempunDealAndUnSenList2 = (List<Map<String, Object>>)stringObjectMap.get("orderList");
        List<Map<String, Object>> tempunDealAndUnSenList3 = (List<Map<String, Object>>)stringObjectMap.get("orderList");
        System.out.println(tempunDealAndUnSenList2.equals(tempunDealAndUnSenList3));
       // int size = tempunDealAndUnSenList2.size();
        List<Map<String, Object>> unSendList = new ArrayList<>();
        unSendList = getUnDealList( tempunDealAndUnSenList2);
        List<Map<String, Object>> unDealList = new ArrayList<>();
        unDealList = getUnSendList( tempunDealAndUnSenList3);

        System.out.println(unSendList.size());
        System.out.println(unDealList.size());
//        int i = 2; int j = 1;
//        System.out.println("111");
//        if (i!=0)flag:{
//            System.out.println("----before flag-----");
//            if (j>0 ) break flag;
//            System.out.println("----after flag-----");
//        }
//        System.out.println("======");
    }
    //得到待接单
    private static List<Map<String, Object>> getUnSendListForCount(List<Map<String, Object>> unDealAndUnSenList){
        List<Map<String, Object>> statusCd = unDealAndUnSenList.stream().filter(map -> TwBizOrderStatusCdEnum.INIT.getStatusCd().equals(map.get("statusCd").toString())).collect(Collectors.toList());
        CollectionUtil.removeIf(unDealAndUnSenList, map1 -> TwBizOrderTypeEnum.BSS_ORDER.getValueName().equals(map1.get("orderType")));
        return statusCd;
    }
    //得到待处理
    private static List<Map<String, Object>> getUnDealListForCount(List<Map<String, Object>> unDealAndUnSenList){
        List<Map<String, Object>> statusCd = unDealAndUnSenList.stream().filter(map -> TwBizOrderStatusCdEnum.DEAL.getStatusCd().equals(map.get("statusCd").toString())).collect(Collectors.toList());
        CollectionUtil.removeIf(unDealAndUnSenList, map1 -> TwBizOrderTypeEnum.BSS_ORDER.getValueName().equals(map1.get("orderType")));
        return statusCd;
    }

    //过滤掉待接单 预受理订单-->得到待处理;
    private static List<Map<String, Object>> getUnDealList(List<Map<String, Object>> unDealAndUnSenList){
        CollectionUtil.removeIf(unDealAndUnSenList , map -> TwBizOrderStatusCdEnum.INIT.getStatusCd().equals(map.get("statusCd").toString()));
        CollectionUtil.removeIf(unDealAndUnSenList, map1 -> TwBizOrderTypeEnum.BSS_ORDER.getValueName().equals(map1.get("orderType")));
        return unDealAndUnSenList;
    }
    //过滤掉待处理-->得到待接单
    private static List<Map<String, Object>> getUnSendList(List<Map<String, Object>> unDealAndUnSenList){
        CollectionUtil.removeIf(unDealAndUnSenList , map -> TwBizOrderStatusCdEnum.DEAL.getStatusCd().equals(map.get("statusCd").toString()));
        CollectionUtil.removeIf(unDealAndUnSenList, map1 -> TwBizOrderTypeEnum.BSS_ORDER.getValueName().equals(map1.get("orderType")));
        return unDealAndUnSenList;
    }
}
