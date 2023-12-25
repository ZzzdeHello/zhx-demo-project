package zzzde.code.technic.codejson;

import com.alibaba.fastjson.JSON;

import java.util.Map;

/**
 * 反序列
 *
 */
public class test3 {
    public static void main(String[] args) {
        String msgbody = "{\"resultCode\": \"0\",  \"resultMsg\": \"\",  \"resultObject\": \"[\\\"{\\\\\\\"statusDate\\\\\\\":\\\\\\\"2020-10-13 20:14:30\\\\\\\",\\\\\\\"orderInfo\\\\\\\":[{\\\\\\\"受理工号\\\\\\\":\\\\\\\"17765472624\\\\\\\"}],\\\\\\\"status\\\\\\\":\\\\\\\"受理中\\\\\\\"}\\\",\\\"{\\\\\\\"statusDate\\\\\\\":\\\\\\\"2020-10-13 20:15:30\\\\\\\",\\\\\\\"orderInfo\\\\\\\":{\\\\\\\"质检工号\\\\\\\":\\\\\\\"17765472624\\\\\\\",\\\\\\\"质检时间\\\\\\\":\\\\\\\"2020-10-13 20:15:30\\\\\\\"},\\\\\\\"status\\\\\\\":\\\\\\\"质检完成\\\\\\\"}\\\",\\\"{\\\\\\\"statusDate\\\\\\\":\\\\\\\"2020-10-13 20:15:30\\\\\\\",\\\\\\\"orderInfo\\\\\\\":{\\\\\\\"费用\\\\\\\":\\\\\\\"100.00\\\\\\\"},\\\\\\\"status\\\\\\\":\\\\\\\"收费完成\\\\\\\"}\\\"]\",  \"success\": true}";
        Map msgbodyMap = JSON.parseObject(msgbody,Map.class);
        System.out.println(msgbodyMap.get("resultCode"));
        System.out.println(msgbodyMap.get("resultMsg"));
        System.out.println(msgbodyMap.get("success"));System.out.println(msgbodyMap.get("resultObject"));
    }
}
