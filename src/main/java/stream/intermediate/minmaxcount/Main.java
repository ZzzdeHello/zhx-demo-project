package stream.intermediate.minmaxcount;

import com.model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @Author zzzde
 * @Date 2023/9/22
 */
public class Main {

    public static void main(String[] args) {
        List<User> UserList = new ArrayList<>();
        UserList.add(new User("张三", 1000, 20, "男", "北京"));
        UserList.add(new User("李四", 2000, 21, "男", "南京"));
        UserList.add(new User("王五", 3000, 20, "女", "合肥"));
        UserList.add(new User("赵六", 4000, 22, "男", "四川"));
        UserList.add(new User("孙七", 5000, 25, "女", "上海"));

        // 根据条件价格比较，获取最大值
        Optional<User> max = UserList.stream().max(Comparator.comparingInt(User::getSalary));
        System.out.println("员工工资最大值：" + max.get().getSalary());

        // 过滤出部分数据，之后再计算满足条件的数量
        long count = UserList.stream().filter(p -> p.getSalary() > 2000).count();
        System.out.println("工资大于2000元的人数：" + count);

        // 计算所有员工工资总和 mapToInt mapToLong mapToDouble
        int sum = UserList.stream().mapToInt(User::getSalary).sum();
        System.out.println("所有员工工资总和：" + sum);
    }
}
