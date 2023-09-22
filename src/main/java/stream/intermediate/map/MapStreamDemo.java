package stream.intermediate.map;

import com.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * map映射:map操作能够将流中的每一个元素映射为另外的元素
 * <R> Stream<R> map(Function<? super T, ? extends R> mapper);
 *
 * @Author zzzde
 * @Date 2023/9/21
 */
public class MapStreamDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setName("小朱").setAge(20).setDescription("一个帅比");
        User user1 = new User();
        user1.setName("小明").setAge(21).setDescription("一个大聪明");
        User user2 = new User();
        user2.setName("小强").setAge(19).setDescription("一个小晴明");
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        // 提取User对象的Name，放入List集合中
        List<String> collect = list.stream().map(User::getName).collect(Collectors.toList());
        collect.forEach(System.out::println);

        List<Integer> collect2 = list.stream().map(u -> u.getAge() + u.getAge()).collect(Collectors.toList());
        collect2.forEach(System.out::println);
        // 如果map的参数e就是返回值，所以可以用peek函数。peek函数是一种特殊的map函数，当函数没有返回值或者参数就是返回值的时候可以使用peek函数
        List<User> collect3 = list.stream().peek(u -> u.setAge(u.getAge() + 1)).collect(Collectors.toList());
        collect3.forEach(System.out::println);
    }
}
