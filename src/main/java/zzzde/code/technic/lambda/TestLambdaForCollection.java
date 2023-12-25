package zzzde.code.technic.lambda;

import java.util.ArrayList;

public class TestLambdaForCollection {
    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>();
//
//        Collections.addAll(list, 1,2,3,4,5);
//
//        //lambda表达式 方法引用
//        list.forEach(System.out::println);
//
//        list.forEach(element -> {
//            if (element % 2 == 0) {
//                System.out.println(element);
//            }
//        });

        ArrayList<Item> items = new ArrayList<>();
        Item item = new Item(11,"小亚索",56);
        System.out.println(item);
        items.add(item);
        items.add(new Item(5, "日本马桶盖", 999.05 ));
        items.add(new Item(7, "格力空调", 888.88 ));
        items.add(new Item(17, "肥皂", 2.00 ));
        items.add(new Item(9, "冰箱", 4200.00 ));
        items.sort((o1, o2) -> o1.getId() - o2.getId());
        System.out.println(items);
        items.removeIf(ele -> ele.getId() == 7);
        //通过 foreach 遍历，查看是否已经删除
        items.forEach(System.out::println);
    }


}


