package zzzde.code.technic.utils;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreconditionsUtils {
    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        String str= "11111";
        String[] array = str.split("\7",-1);
        if(array[2].indexOf("{")>0&&array[2].indexOf("}")>0) {
            String celveName = array[2].substring(array[2].indexOf("{") + 1, array[2].indexOf("}"));
            String huodongName = array[2].substring(0, array[2].indexOf("{"));
            map.put("policyName", celveName);
            map.put("huodongName", huodongName);
            map.put("priority", array[3]);
            System.out.println(JSONUtils.toJSONString(map));
        }

    }
}
