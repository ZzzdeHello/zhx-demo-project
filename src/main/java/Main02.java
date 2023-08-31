import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.fastjson.JSON;
import collection.map.filter.CollectionUtil;

import java.time.LocalDateTime;
import java.util.*;

public class Main02 {
    public static void main(String[] args) {
        Long l = 1682242147 * 1000L;
        LocalDateTime of = LocalDateTimeUtil.of(l);
        System.out.println(of.toString());
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        LocalDateTime of2 = LocalDateTimeUtil.of(new Date());
        System.out.println(of2.toString());
    }
}
