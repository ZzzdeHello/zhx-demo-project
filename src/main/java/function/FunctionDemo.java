package function;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        // Function<T,R> T 入参；R 为返回参数
        Function<String,Long> function = a -> a.length() + 1L; // T 输入值。 R 返回方 Function<T,R> --中的方法--> R apply(T t)
        System.out.println(function.apply("输入"));
        Long longNum = function.apply("测试长度");
        System.out.println(longNum);
    }

}
