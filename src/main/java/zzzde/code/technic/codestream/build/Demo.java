package zzzde.code.technic.codestream.build;

import cn.hutool.core.util.NumberUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

/**
 * @Author zzzde
 * @Date 2023/9/21
 */
public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        List<String> collect = list.stream().filter(s -> s.equals("1")).collect(Collectors.toList());
        System.out.println(collect);
        Stream<String> stream = list.stream();
        Stream<String> stringStream = list.parallelStream();
        System.out.println("-------------");
        // 三种基础提供了基本数据类型的流
        IntStream.of(1,2,3,4).forEach(System.out::println);
        int[] ar = {12,3,41,15};
        IntStream intStream = Arrays.stream(ar);

        LongStream.of(1L,2L,3L,4L).forEach(System.out::println);
        DoubleStream.of(1.1,2.1,3.1,4.1).forEach(System.out::println);
        System.out.println("----------------------------------------");
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println);

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
        System.out.println("----------");
        double div = NumberUtil.div(205 - 200, 200, 5);
        System.out.println(div);
        System.out.println("---1-------");
        double v=0;
        try {
            v = Double.parseDouble("string");
        }catch (NumberFormatException numberFormatException){
            System.out.println(numberFormatException.getMessage());
        }
        System.out.println("v = " +v);

    }
}
