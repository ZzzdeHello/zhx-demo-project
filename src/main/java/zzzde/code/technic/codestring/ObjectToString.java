package zzzde.code.technic.codestring;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Object to String 的三种方法
 *  1、Object + "" 为空的话返回的是 null 字符串
 *  2、String强转 有些类型没法强转会报错
 *  3、String.valueOf() 为空话返回的是 null 字符串：return (obj == null) ? "null" : obj.toString();
 *  4、Object.toString()  必须保证有值 ，否者会报空指针
 *
 *  方式 1 效率最低
 *  方式 2、3 效率最高
 *  toString还行
 */
public class ObjectToString {
    public static void main(String[] args) {
        Map  sup = new HashMap();
        sup.put("CASE_FILE_ID","100");

        // toString 方法
        long l = new Date().getTime();
        for(int i = 0; i<10000; i++){
            String caseFileId=sup.get("CASE_FILE_ID").toString();
        }
        long l2 = new Date().getTime();
        System.out.println(l2-l); //5 毫秒

        // toString 方法
        long ll1 = new Date().getTime();
        for(int i = 0; i<10000; i++){
            String caseFileId=sup.get("CASE_FILE_ID")+"";
        }
        long ll2 =new Date().getTime();
        System.out.println(ll2-ll1); // 14毫秒

        // 3
        long l3 = new Date().getTime();
        for(int i = 0; i<10000; i++){
            String caseFileId2=(String) sup.get("CASE_FILE_ID");
        }
        long l4 = new Date().getTime();
        System.out.println(l4-l3); // 2 毫秒

        long l5 = new Date().getTime();
        for(int i = 0; i<10000; i++){
            String case3 = String.valueOf(sup.get("CASE_FILE_ID"));
        }
        long l6 =  new Date().getTime();
        System.out.println(l6-l5); //1毫秒

    }
}
