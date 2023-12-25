package zzzde.code.technic.collection.map.filter;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {
        Map result = new HashMap();
        System.out.println(result.get("isBatch"));
        if( "1".equals(result.get("isBatch"))&& "0".equals(result.get("isSend")) ){
            System.out.println(result.get("isBatch"));
            return;
        }
    }
}
