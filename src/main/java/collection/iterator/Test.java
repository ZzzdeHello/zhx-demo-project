package collection.iterator;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list  = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals(2)) iterator.remove();
            System.out.println(JSONObject.toJSON(list));
        }
    }
}
