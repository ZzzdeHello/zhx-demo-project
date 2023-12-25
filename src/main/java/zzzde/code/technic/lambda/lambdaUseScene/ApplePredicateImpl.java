package zzzde.code.technic.lambda.lambdaUseScene;

import java.util.ArrayList;
import java.util.List;

public class ApplePredicateImpl implements TPredicate<Apple> {
    public boolean test(Apple apple) {
        return apple.getColor().equals("red");
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

