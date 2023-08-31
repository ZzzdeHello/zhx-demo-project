package json;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test1 {
//    public static void main(String[] args) {
//        Map<String,Object> params = new HashMap();
//        params.put("orderId", "80000016221001948");
//        params.put("orderType", "");
//        params.put("resultRemark", "");
//        params.put("appointTime", "");
//        params.put("contactDate", "2021-07-27 14:38:35");
//        params.put("orderNum", "");
//        params.put("resultNbr", "6000");
//        params.put("mktMsg", "无人接听");
//        params.put("mktResult", "4000");
//        params.put("salerNo", "6000");
//        params.put("channelName", "外呼系统");
//        params.put("channelCode", "QD00010");
//        Map a = new HashMap();
//        a.put("answerTitle", "否");
//        a.put("questTitle", "您好，我是杭州电信10000号客户代表，请问您是XXX先生/小姐吗？");
//        Map b = new HashMap();
//        b.put("answerTitle", "否");
//        b.put("questTitle", "您好，我是杭州电信10000号客户代表，请问您是XXX先生/小姐吗？");
//        List<Map> list = new ArrayList();
//        list.add(a);
//        list.add(b);
//        Map c = new HashMap();
//        c.put("wenjuncont", JSON.toJSONString(list));
//        params.put("paramJsonStr", JSON.toJSONString(c));
//        List<Map<String, Object>> orderList = new ArrayList<>();
//        orderList.add(params);
//        System.out.println(JSON.toJSON(orderList));
//    }

//    public static void main(String[] args) {
//        String jsonStr = "" +
//                "{\"salerNo\":\"990002\",\"appointTime\":\"\",\"channelName\":\"外呼系统\",\"channelCode\":\"QD00010\",\"resultRemark\":\"\",\"mktResult\":\"4000\",\"mktMsg\":\"无人接听\",\"orderType\":\"\",\"paramJsonStr\":{\"salerPhone\":\"13306717268\",\"salerOrgName\":\"西湖分局\",\"wenjuncont\":[{\"questTitle\":\"您好，我是杭州电信10000号客户代表，请问您是XXX先生/小姐吗？\",\"answerTitle\":\"是\"},{\"questTitle\":\"XX先生/小姐，很高兴联系到您，想请问一下您是否知道我们公司给您提供服务的渠道经理是谁吗？ttttt\",\"answerTitle\":\"不知道\"},{\"questTitle\":\"渠道经理叫什么名字？\",\"answerTitle\":\"蒋敏飞\"},{\"questTitle\":\"我们渠道经理给您提供的支撑服务是否到位？\",\"answerTitle\":\"是\"}],\"salerName\":\"990002\"},\"contactDate\":\"2021-07-27 17:21:21\",\"orderNum\":\"\",\"resultNbr\":\"6000\",\"orderId\":\"80000016221002428\"}";
//
//        Map<String,Object> parse = (Map<String,Object>)JSON.parse(jsonStr);
//        String salerNo = parse.get("salerNo").toString();
//        System.out.println(salerNo);
//
//        String paramJsonStr = parse.get("paramJsonStr").toString();
//        System.out.println(paramJsonStr);
//        Map<String,Object> parse1 = (Map<String,Object>)JSON.parse(paramJsonStr);
//        System.out.println(parse1.get("salerPhone"));
//    }

    /*
    测试结果
    ali 的fastJSon 可直接将 对象转化为string字符串输出
     */
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("r",10);
        User user = new User(10,"user");
        String s1 = JSON.toJSONString(user);
        System.out.println(s1);
        map.put("user", user);
        System.out.println(JSON.toJSONString(map));
    }

    static class User {
        public User(int i, String name) {
            this.i = i;
            this.name = name;
        }

        int i ;
        String name;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
