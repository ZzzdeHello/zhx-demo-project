package collection.ListOrder;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class ListToStr {
    public static void main(String[] args) {
        List colNameList=  new ArrayList<>();
        colNameList.add("ss");
        colNameList.add("s1s");
        colNameList.add("s2s");
        String[] colName = new String[colNameList.size()];
        colNameList.toArray(colName);
        System.out.println(JSON.toJSONString(colName));
    }
}
