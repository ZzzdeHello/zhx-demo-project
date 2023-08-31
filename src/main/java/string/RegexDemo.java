package string;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/14 15:20
 */
public class RegexDemo {

    /**
     * 利用好其预编译功能，可以有效加快正则匹配速度
     */
    public static Pattern dynamic = Pattern.compile(".*\\$\\{([a-z]+)\\}.*");
    public static Pattern dynamicLimitCount = Pattern.compile("\\$\\{([a-z]+)\\}");

    /**
     * 判断内容中是否包含动态参数(${key}形式的)
     *
     * @param content 要判断的内容
     * @return
     */
    public static boolean isContainsDynamicParameter(String content) {
        return dynamic.matcher(content).matches();
    }

    /**
     * 按照动态内容的参数出现顺序,将参数放到List中
     *
     * @param content
     * @return
     */
    public static String getFullStrByContent(String content, String variables) {
        String[] strings = variables.split(",");
        int i = 0 ;
        Matcher m = dynamicLimitCount.matcher(content);
        while (m.find()) {
            //要替换的子串
            String replace = m.group(0);
            content = content.replace(replace,strings[i]);
        }
        return content;
    }

    public static void main(String[] args) {
        //测试代码
        String content = "尊敬的${name}客户您好，请于${time}前到达";
        String fullStrByContent = getFullStrByContent(content, "zzz,2022-11-1");
        System.out.println(fullStrByContent);
    }
}
