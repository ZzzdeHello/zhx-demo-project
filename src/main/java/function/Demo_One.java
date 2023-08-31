package function;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * function接口自带的 apply和andThen 示例
 */
public class Demo_One {

    public static void main(String[] args) {
        Function<String, String> function1 = string -> {
            System.out.println("function1输出:" + string);
            return string;

        };

        Function<String, Object> function2 = string -> {
            System.out.println("function2输出:" + string);
            return string;
        };
        function1.apply("1 : Hello World! ");
        function2.apply("2 : Hello World! ");
        System.out.println("------------");
        Function<String, Object> OneThenTwo = function1.andThen(function2);
        OneThenTwo.apply("3 : Hello World! ");
    }


}
