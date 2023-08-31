package date;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * 证明 System.currentTimeMillis()方法获得的毫秒数 和Date.getTime()一样单位
 */
public class systemGetTimeMillis {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(l-3600000);
        Date now = new Date(l);
        long time = now.getTime();
        System.out.println(time);
        System.out.println(now);
        System.out.println(JSONObject.toJSONString(now));
    }
}
