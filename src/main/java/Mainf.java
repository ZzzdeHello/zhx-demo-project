import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速构建ArrayList
 *
 * @Author zzzde
 * @Date 2023/9/21
 */
public class Mainf {
    public static void main(String[] args) {
        ArrayList<String> c = Lists.newArrayList("c测试");
        c.forEach(System.out::println);
    }
}
