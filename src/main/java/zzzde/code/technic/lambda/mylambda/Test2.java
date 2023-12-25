package zzzde.code.technic.lambda.mylambda;

import zzzde.code.technic.lambda.Item;

import java.util.function.BiPredicate;
import java.util.function.Function;


public class Test2 {

        public static void main(String[] args) {
            BiPredicate<String, String> bp1 = (x, y) -> x.equals(y);
            boolean test1 = bp1.test("Mr.nobody", "Mr.anybody");
            System.out.println(test1);

            BiPredicate<String, String> bp2 = String::equals;
            boolean test2 = bp2.test("Mr.nobody", "Mr.nobody");
            System.out.println(test2);
        }
        public String LambdaMethodArray(){
            Function<Integer, Item[]> arrayItem = Item[]::new ;
            return "";
        }


}
