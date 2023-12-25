package zzzde.code.technic.collection.list.sublist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * subList 获取到的为 List 的子类。 是一个内部视图。对子类的所有操作都会反馈到父类上。
 * 故在assmberEroor 方法上新增 map,会导致父集合list新增一个map。
 * 可以利用subList调整 父集合的非结构性改动
 *
 * 故分页处理的时候，尽量不要去改动 ArrayList 里面的结构，只当成一个可读的来处理。
 */

public class SubListTest {
    @Test
    public void test01(){
        Map map = new HashMap();map.put("1","map1");
        Map map1 = new HashMap();map1.put("1","map2");
        Map map2 = new HashMap();map2.put("1","map3");
        Map map3 = new HashMap();map3.put("1","map4");
        Map map4 = new HashMap();map4.put("1","map5");
        List<Map> orderList = new ArrayList<>();
        orderList.add(map);
        orderList.add(map1);
        orderList.add(map2);
        orderList.add(map3);
        orderList.add(map4);
        int listSize = orderList.size();
        Integer pages = getPage(listSize);// 获取总页数
        for ( int page = 1 ; page <= pages ; page ++ ) {
            System.out.println("page" + page);
            List<Map> tempPageList = getTempPageList(orderList, page);
            System.out.println(tempPageList.toString());
            List<Map> tempList = assmberEroor(tempPageList);
            System.out.println(tempList);
            System.out.println("-------");
        }
    }

    // list新增 map的时候 会新增在旧 list 上。因为还是返回了原来的 list 地址。
    private List<Map> assmberEroor(List<Map> list){
        Map map = new HashMap();
        map.put("meth01","1111");
        list.add(map);
        return list;
    }

    private List<Map> assmber(List<Map> list){
        for(Map map :list){
            map.put("2","assmber++");
        }
        return list;
    }

    /**
     * 手动分页
     */
    private List<Map> getTempPageList (List<Map> list, int page){
        int listSize = list.size();
        int fromIndex;
        int toIndex;
        fromIndex = (page - 1) * 2;
        toIndex = (page) * 2;
        if (toIndex > listSize) {
            toIndex = listSize;
        }
        //截取一段集合数据
        System.out.println("getTempPageList的" + fromIndex + "|"+toIndex);
        return list.subList(fromIndex, toIndex);
    }

    /**
     *  获取分页
     *  @param listSize 总list大小
     */
    private  Integer getPage ( int listSize){
        int page = listSize / 2;
        int surplus = listSize % 2;
        if (0 != surplus) {
            page++;
        }
        return page;
    }
}
