package zzzde.code.technic.lambda.lambdaUseScene;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        ArrayList appleList = new ArrayList<Apple>();
        appleList.add( new Apple("red",100));
        appleList.add( new Apple("green",200));
        appleList.add( new Apple("red",200));
        appleList.add( new Apple("green",100));

        //使用匿名类
        List<Apple> redApples = filterApples(appleList,(Apple a) -> "red".equals(a.getColor()));
        System.out.println(redApples.toString());
    }
    public static List<Apple> filterApples(List<Apple> apples, TPredicate<Apple> p) {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

}
