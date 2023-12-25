package zzzde.code.technic.collection.map.filter.groupBy;

import com.alibaba.fastjson.JSONObject;
import zzzde.code.technic.codejson.AttrValueVo;

import java.util.*;
import java.util.stream.Collectors;

import static zzzde.code.technic.collection.sort.TestDateSort.sortList;

//利用对象属性值的 第一个字母来分组
public class Test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add( new AttrValueVo("1da","1111"));
        list.add( new AttrValueVo("1de","1211"));
        list.add( new AttrValueVo("O2O","2111"));
        list.add( new AttrValueVo("O2O","2211"));
        list.add( new AttrValueVo("3","1311"));
        list.add( new AttrValueVo("4","1311"));
        List<AttrValueVo> map = sortList(list);
        System.out.println(JSONObject.toJSON(map));
    }

    public static Map<String,List<AttrValueVo>> transfer(List<AttrValueVo> list){
        Map<String, List<AttrValueVo>>  map = list.stream().collect(Collectors.groupingBy(attrValueVo -> {
            return attrValueVo.getAttrValueName().substring(0, 1);
        }));
        return map;
    }


}
