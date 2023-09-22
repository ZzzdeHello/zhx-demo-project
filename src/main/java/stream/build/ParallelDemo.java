package stream.build;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author zzzde
 * @Date 2023/9/22
 */
public class ParallelDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c");
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        // 创建一个并行流 并行流内部以多线程并行执行的方式对流处理，
        // 并行流对顺序要求无要求才行
        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(System.out::println);
    }
}
