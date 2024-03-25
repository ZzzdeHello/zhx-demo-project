package zzzde.demo.main;

import cn.hutool.core.math.MathUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main02 {

    public static void main(String[] args) {

        long orderUsageDuration = (61001l) / 1000 / 60;
        System.out.println(orderUsageDuration);
        double ceil = Math.ceil(orderUsageDuration);
        System.out.println(ceil);

        long s = (980) / 1000 / 60;
        int userDuration = Double.valueOf(Math.ceil(s)).intValue();
        System.out.println(userDuration);
    }

    public static String sign(Map<String, String> param, String salt) {

        List<String> keys = new ArrayList<>(param.keySet());
        Collections.sort(keys);

        StringBuilder toSign = new StringBuilder();
        for (String key : keys) {
            String value = param.get(key);
            if (null != value && !"".equals(value) && !"sign".equals(key)
                    && !"key".equals(key)) {
                toSign.append(key).append("=").append(value).append("&");
            }
        }

        if (StringUtils.isNotEmpty(salt)) {
            toSign.append("key=").append(salt);
        } else {
            toSign.setLength(toSign.length() - 1);
        }
        return DigestUtils.md5Hex(toSign.toString().getBytes(StandardCharsets.UTF_8));
    }
}
