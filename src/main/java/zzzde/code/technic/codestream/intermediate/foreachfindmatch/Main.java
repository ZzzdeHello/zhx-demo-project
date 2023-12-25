package zzzde.code.technic.codestream.intermediate.foreachfindmatch;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 遍历与匹配
 *
 * @Author zzzde
 * @Date 2023/9/22
 */
public class Main {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        List<Integer> collect = list.stream().filter(x -> x > 6).collect(Collectors.toList());
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        findFirst.ifPresent(integer -> System.out.println("匹配第一个值：" + integer));
        System.out.println("大于6的值：" + collect);
        findAny.ifPresent(i -> System.out.println("匹配任意一个值：" + + findAny.get()));
        System.out.println("是否存在大于6的值：" + anyMatch);
    }
}
