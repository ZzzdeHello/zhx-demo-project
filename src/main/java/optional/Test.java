package optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Map param = new HashMap();
        String commLv3Id = Optional.ofNullable(param.get("COMM_LVL3_ID")).orElse("").toString();
        String commLv4Id = Optional.ofNullable(param.get("COMM_LVL4_ID")).orElse("").toString();
        if(commLv4Id.equals("")&&commLv4Id.equals("")){
            System.out.println("11111111111");
        }
        System.out.println(commLv4Id);
    }
}
