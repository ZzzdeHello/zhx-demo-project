package zzzde.code.technic.collection.map.filter.extendPackage;

import zzzde.code.technic.lambda.Item;
import zzzde.code.technic.lambda.ItemSon;

import java.util.ArrayList;
import java.util.List;

public class extentsTTest {
    public static void main(String[] args) {
//        List<? extends Item> list ;
//        list = new ArrayList<Item>();
//        List<? extends Item> list2 ;
//        list2 = new ArrayList<ItemSon>();
        // list2.add(new ItemSon());
        List < ? super Item> list ;
        list = new ArrayList<Object>();
        list.add(new Item());
        list.add(new ItemSon());

        List < ? super ItemSon> list2 ;
        list2 = new ArrayList<Item>();
        list2.add(new ItemSon());
        //list2.add(new Item());

    }

}
