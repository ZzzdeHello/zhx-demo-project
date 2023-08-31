package collection.map.filter.point_removeIf;

import java.util.function.Predicate;

public class PredicateNegate {

    public static void main(String[] args) {

        PredicateNegate predicateNegate = new PredicateNegate();

        System.out.println(predicateNegate.testNageteMethod("1",stringOne -> stringOne.equals("zhangsan")));


    }


    public boolean testNageteMethod(String stringValue, Predicate<String> predicate) {
        return predicate.negate().test(stringValue);
    }
}