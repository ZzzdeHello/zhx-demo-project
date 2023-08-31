import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Work {

//    public static void main(String[] args) {
//        int count=22; // 所有任务量
//        int persons = 4 ; // 总人数
//        for(int personNum= 0; personNum < persons; personNum++)
//        {
//            int min = count * personNum / persons;
//            int max = count * (personNum + 1) / persons;
//            for (int i = min ; i < max; i++)
//            {
//                System.out.println( "人员编号：" +personNum +"，获取" + i );
//            }
//        }
//    }




    public static void main(String[] args) {
        test001();
    }

    public static void  test (){
        List<Map> list = new ArrayList<>();
        Map<String,Object> map = new HashMap();
        map.put("a",1);
        list.add(map);
        list = changeMap(list);
        System.out.println(list.toString());
    }

    public static List<Map> changeMap(List<Map> list){
        for (Map m: list){
            m.put("b",1);
        }
        return list;

    }

    public static void test001(){
        int hash = 2; // 0010
        int n = 10 ; //  1010
        int i = (n ) & hash ; // 与运算 只有两个值都为 1 是 才为 1  故 0010 i= 2
        int j = (n ) | hash ; // 或运算 只有两个值都为 1 是 才为 1  故 1010 j= 10
        System.out.println(i);
        System.out.println(j);
    }
}
