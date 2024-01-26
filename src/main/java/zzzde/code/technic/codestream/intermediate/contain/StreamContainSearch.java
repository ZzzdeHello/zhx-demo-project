package zzzde.code.technic.codestream.intermediate.contain;

import cn.hutool.core.convert.Convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 集合内通过流判断 ，判断某个key的value是否包含某种值
public class StreamContainSearch {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map map = new HashMap();map.put("custName","1");map.put("custName2","2");
        Map map2 = new HashMap();map2.put("custName","203");
        Map map3 = new HashMap();map3.put("custName","123");
        list.add(map);
        list.add(map2);
        list.add(map3);

        String containKey = "1";
        System.out.println(map.get("custName").toString().contains(containKey));
        List<Map> n = list.stream().filter(map1 -> map1.get("custName").toString().contains(containKey)).collect(Collectors.toList());
        List<Map<String,Object>> list2 = new ArrayList();
        System.out.println(n);
        List<Map<String,Object>> n2 = list2.stream().filter(x -> x.get("custName").toString().contains(containKey)).collect(Collectors.toList());
        System.out.println(n2);

//        Double similarityScore = Double.parseDouble("") / 100d;
//        System.out.println(similarityScore);


         if (0  == Convert.toInt("1",0)){
             System.out.println("--------------------进入");
         }
    }
}
