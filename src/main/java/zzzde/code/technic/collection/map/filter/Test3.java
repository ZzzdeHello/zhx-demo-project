package zzzde.code.technic.collection.map.filter;

//利用流 排序

import zzzde.code.technic.lambda.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test3 {
    public static void main(String[] args) {
        List<Item> list = new ArrayList<>();
        Item item1 = new Item(1);list.add(item1);
        Item item2 = new Item(2);list.add(item2);
        Item item3 = new Item(100);list.add(item3);
        Item item4 = new Item(4);list.add(item4);
        Item item5 = new Item(5);list.add(item5);
        Item item6 = new Item(6);list.add(item6);
        Item item7 = new Item(7);list.add(item7);
        Item item8 = new Item(8);list.add(item8);
        Item item9 = new Item(9);list.add(item9);
        Item item10 = new Item(10);list.add(item10);
        List<Item> sortedEmp = list.stream().sorted(Comparator.comparing(Item::getL).reversed()).collect(Collectors.toList());
        sortedEmp.stream().forEach(System.out::println);
    }

}
