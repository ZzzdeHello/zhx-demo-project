package collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListDemo {

    public static void main(String[] args) {
        method001();
        // method002();
        //  method003();
    }

    public static void method001 () {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (2 == next) {
                list.remove(next);
            }
        }
        System.out.println(list);
    }

    //这里直接调用集合的remove方法，那么会导致modCount，然而Iterator对象里的expectedModCount属性没有更新，
    //所以在使用Iterator对象获取元素时的前置检测不通过，抛出异常：ConcurrentModificationException
    public static void method002 () {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (2 == next) {
                list.remove(next);
            }
        }
        System.out.println(list);
    }

    // foreach 循环类似遍历器循环，都有快速失败机制。
    public static void method003 () {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        for(int i : list){
            if (i == 2) list.remove(i-1);
        }
        System.out.println(list);
    }
}
