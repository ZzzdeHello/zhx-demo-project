package hutool;

import com.alibaba.fastjson.JSON;
import zzzde.code.technic.lambda.mylambda.People;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zzzde
 * @Date 2023/11/27
 */
public class Main {

    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        People p1 = People.builder().sex(2).age(21).name("珠珠").score(100D).build();
        People p2 = People.builder().sex(1).age(21).name("小王").score(78D).build();
        People p3 = People.builder().sex(2).age(21).name("李普").score(79D).build();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        list = list.stream().peek(x -> x.setMoney(11)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list));

    }
}
