package zzzde.code.technic.lambda;

import java.util.ArrayList;
import java.util.List;

//需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
public class Test {
    public static void main(String[] args) {

//        //构造方法引用
//        Supplier<Item> itemSupplier = Item::new;
//        Item item = itemSupplier.get();
//        item.setId(11);
//        item.setS("fafafdfsafasf");
//        item.setV(21.213);
//        System.out.println(item);

        //@FunctionalInterface 函数式接口的注解
        //List 的 foreach 是 一个函数式接口 、sort 接受的参数Comparator是一个函数式接口 使用
        List<Item> list = new ArrayList<>();
        // 篇幅有限，只添加4个
        list.add(new Item(11, "1111111111", 100.0));
        list.add(new Item(16, "1111111111", 100.0));
        list.add(new Item(13, "1111111111", 100.0));
        list.add(new Item(14, "1111111111", 100.0));

        //list.sort((s1,s2) -> s1.getId().compareTo(s2.getId()));
        //list.sort((s1,s2) -> Item.compareById(s1,s2));//类的静态方法的引用
        list.sort(Item::compareById);//静态方法引用
        list.forEach(System.out::println);

    }
//    static class StudentAgeComparator implements Comparator<Item> {
//        public int compare(Item s1, Item s2) {
//           return s1.getId().compareTo(s2.getId());
//        }
//    }
}
