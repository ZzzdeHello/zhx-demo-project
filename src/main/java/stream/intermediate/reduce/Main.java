package stream.intermediate.reduce;

import com.model.User;

import java.util.ArrayList;
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

        // 求所有员工的工资之和、最高工资
        // 求工资之和方法1：
        Optional<Integer> sumSalary = UserList.stream().map(User::getSalary).reduce(Integer::sum);
        // 求工资之和方法2：
        Integer sumSalary2 = UserList.stream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);

        // 求最高工资方法1：
        Integer maxSalary = UserList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), Integer::max);
        // 求最高工资方法2：
        Integer maxSalary2 = UserList.stream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), (max1, max2) -> max1 > max2 ? max1 : max2);
        // 求最高工资方法3：
        Integer maxSalary3 = UserList.stream().map(User::getSalary).reduce(Integer::max).get();
        System.out.println("工资之和，方法1：" + sumSalary);
        System.out.println("工资之和，方法2：" + sumSalary2);
        System.out.println("最高工资，方法1：" + maxSalary);
        System.out.println("最高工资，方法2：" + maxSalary2);
        System.out.println("最高工资，方法3：" + maxSalary3);
    }
}
