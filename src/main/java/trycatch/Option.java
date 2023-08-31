package trycatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Option {
    public static void main(String[] args) {
        Map param = new HashMap();
        String eventCode = Optional.ofNullable(param.get("eventCode")).orElse("").toString();
        System.out.println(eventCode);
    }
}
