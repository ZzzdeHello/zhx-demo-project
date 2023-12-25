package zzzde.code.technic.collection.sort;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class CollectionSortTest {
    public static void main(String[] args) {
        List<Map<String,Object>> orderList = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i <5; i++) {
            instance.set(Calendar.HOUR,+i);
            Date instanceTimeTemp = instance.getTime();
            long time = instanceTimeTemp.getTime();
            Map<String, Object> map = new HashMap<>();
            map.put("orderCreateDate",time);
            if(i ==1){
                map.put("orderType","O2O");
            }else if (i==2){
                map.put("orderType","2");
            }else {
                map.put("orderType","4");
            }
            orderList.add(map);
            System.out.println(map.get("orderCreateDate"));
            String timestamp =simpleDateFormat.format(new Date(time));
            System.out.println(timestamp);
        }
        System.out.println(JSONObject.toJSONString(orderList));
        List list = sortGF(orderList);
        System.out.println(JSONObject.toJSONString(list));
    }
    public static List sortGF(List list){
        Collections.sort(list, new PriceComparator());
        return list;
    }

    static class PriceComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Map map1 = (Map)o1;
            Map map2  =(Map)o2;
            String name1 = String.valueOf(map1.get("orderCreateDate"));
            String name2 = String.valueOf(map2.get("orderCreateDate"));
            System.out.println( name1.compareTo(name2));
            return name2.compareTo(name1);
        }
    }

}
