package collection.map.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cglib.core.Local;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test8 {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        Calendar instance = Calendar.getInstance();
        for (int i = 0; i < 10; i++) {
            instance.set(Calendar.HOUR,-i);
            Date instanceTimeTemp = instance.getTime();
            long time = instanceTimeTemp.getTime();
            Map<String, Object> map = new HashMap<>();
            map.put("orderCreateDate",time);
            list.add(map);
            System.out.println(map.get("orderCreateDate"));
            String timestamp =simpleDateFormat.format(new Date(time));
            System.out.println(timestamp);
        }

        List<Map<String, Object>> list1 = convertCreateDate(list);
        System.out.println(list1);

    }
//    private List<Map<String, Object>> convertCreateDate(List<Map<String, Object>> orderList){
//        for(Map<String, Object> map : orderList){
//            String timestamp =simpleDateFormat.format(new Date((long)map.get("orderCreateDate")));
//            map.put("orderCreateDate",timestamp);
//        }
//        return orderList;
//    }
    private static List<Map<String, Object>> convertCreateDate(List<Map<String, Object>> orderList){
        Collections.sort(orderList, (o1, o2) -> {
            String name1=String.valueOf(o1.get("orderCreateDate"));
            String name2=String.valueOf(o2.get("orderCreateDate"));
            return name1.compareTo(name2);
        });
        for(Map<String, Object> map : orderList){
            String timestamp =simpleDateFormat.format(new Date((long)map.get("orderCreateDate")));
            map.put("orderCreateDate",timestamp);
        }
        return orderList;
    }
}
