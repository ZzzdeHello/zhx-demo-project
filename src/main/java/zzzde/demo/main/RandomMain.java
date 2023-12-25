package zzzde.demo.main;

import com.alibaba.fastjson.JSON;
import zzzde.code.technic.lambda.mylambda.People;

import java.util.*;

/**
 * @Author zzzde
 * @Date 2023/11/23
 */
public class RandomMain {

    public static void main(String[] args) {
        List<People> list= new ArrayList<>();
        People p1 = People.builder().age(10).score(0.121).name("小明").build();
        People p2 = People.builder().age(4).score(0.32).name("小2").build();
        People p3 = People.builder().age(5).score(0.41).name("小3").build();
        // People p3 = People.builder().score(0.4).name("小谢").build();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        // 生成一个随机数
        Random random = new Random();
        Double userSelect = random.nextDouble();
        System.out.println(userSelect);
        for (People people : list) {
            // 随机数小于中奖几率，则中奖
            if (userSelect < people.getScore()) {
                // 最大中奖数（0：代表不限制次数）
                int maxNum = people.getAge();
                // 判断游戏奖品当前中奖数及最大中奖数
                if (maxNum != 0 && maxNum <= people.getAge()) {
                    // 超过最大中奖数则不中
                    break;
                } else {
                    System.out.println(JSON.toJSONString(people));
                }
            }
        }
    }
}
