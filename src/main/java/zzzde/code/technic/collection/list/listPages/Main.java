package zzzde.code.technic.collection.list.listPages;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    private static final int PAGE_SIZE = 5;

    /**
     * 手动分页
     */
    private static void splitList(List list) {
        int listSize = list.size();
        int page = getPage(listSize);
        int fromIndex;
        int toIndex;
        List pageList = new ArrayList<>();
        for (int i = 0; i < page; i++) {
            fromIndex = i * PAGE_SIZE;
            toIndex = (i + 1) * PAGE_SIZE;
            if (toIndex > listSize) {
                toIndex = listSize;
            }
            //截取一段集合数据
            List subList = list.subList(fromIndex, toIndex);
            pageList.add(subList);
        }
        pageList.forEach(System.out::println);
    }



    /**   * 获取分页   * @param listSize 总list大小   */
    private static Integer getPage(int listSize) {
        int page = listSize / PAGE_SIZE;
        int surplus = listSize % PAGE_SIZE;
        if (0 != surplus) {
            page++;
        }
        return page;
    }
}
