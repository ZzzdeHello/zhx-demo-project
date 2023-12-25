package zzzde.project.technic.trycatch;

import java.util.*;

public class TestAss {
    public static void main(String[] args) {
        List<Map<String, Object>> dealList = new ArrayList<>();
        List<Map<String, Object>> unDealList = new ArrayList<>();
        List<Map<String, Object>> unSendList = new ArrayList<>();
        try{
            dealList = new ArrayList<>();
        AssertUtil.isTrue(dealList.size()>0, "查询无结果");
            System.out.println("0--------");
        }catch (IllegalArgumentException i){
            i.printStackTrace();
        }
        System.out.println(dealList.size());
    }
}
