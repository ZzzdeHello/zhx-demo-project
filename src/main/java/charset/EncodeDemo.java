package charset;

import java.io.UnsupportedEncodingException;

/**
 * UTF-8 编码解码 示例
 */
public class EncodeDemo {
    public static void main(String[] args) {
        String name = "测试";
        m(name);
    }

    public static void m(String name) {
        try {
            name = java.net.URLEncoder.encode(name, "UTF-8"); // 编码一次
            System.out.println(name);
            name = java.net.URLEncoder.encode(name, "UTF-8");// 编码两次次
            System.out.println(name);
            name = java.net.URLDecoder.decode(name, "UTF-8"); // 解码一次
            System.out.println(name);
            System.out.println(java.net.URLDecoder.decode(name, "UTF-8"));// 解码两次

            System.out.println("----------");
            String string = "%25E6%25B5%258B%25E8%25AF%2595";
            System.out.println(java.net.URLDecoder.decode(string, "ISO-8859-1"));
            String stirng1 = java.net.URLDecoder.decode(java.net.URLDecoder.decode(string, "ISO-8859-1"), "UTF-8");// 编码两次次
            System.out.println(stirng1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
