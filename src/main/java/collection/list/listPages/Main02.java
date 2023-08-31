package collection.list.listPages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main02 {

    public static void main(String[] args) {
        boolean deptCheck = false;
        Map param = new HashMap();
        param.put("dept","121");
        String deptCode = "djak,121" ;
        //String[] depts = deptCode.split(",");
        boolean dept = deptCode.contains(String.valueOf(param.get("dept")));
        System.out.println(dept);
        if(!dept){
            System.out.println("cddddddd");
        }
    }

    private static final int PAGE_SIZE = 4;

    /**
     * 手动分页
     */
    private static List getTempPageList(List list,int page) {
        int listSize = list.size();
        int fromIndex;
        int toIndex;
        fromIndex = (page-1) * PAGE_SIZE;
        toIndex = (page) * PAGE_SIZE;
        if (toIndex > listSize) {
                toIndex = listSize;
        }
        //截取一段集合数据
        List tempPageList = list.subList(fromIndex, toIndex);
        return tempPageList;
    }

    /**   * 获取分页
     * * @param listSize 总list大小
     * */
    private static Integer getPage(int listSize) {
        int page = listSize / PAGE_SIZE;
        int surplus = listSize % PAGE_SIZE;
        if (0 != surplus) {
            page++;
        }
        return page;
    }
}
