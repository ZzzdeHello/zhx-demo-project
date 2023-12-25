package zzzde.demo.main;

import com.alibaba.fastjson.JSON;
import zzzde.code.technic.lambda.mylambda.People;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author zzzde
 * @Date 2023/11/14
 */
public class MainWork {

    private static final Integer MARK_FLAG_TRUE = 1 ;

    private static final Integer MARK_FLAG_FALSE = 0 ;

    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        People p1 = People.builder().sex(2).age(21).name("珠珠").score(100D).build();
        People p2 = People.builder().sex(1).age(21).name("小王").score(78D).build();
        People p3 = People.builder().sex(2).age(21).name("李普").score(79D).build();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        List<People> cyclic = cyclic(list);
        System.out.println(JSON.toJSONString(cyclic));

        List<People> people = cyclic.subList(3,4);
        String name = people.stream().max(Comparator.comparing(People::getScore)).get().getName();
        System.out.println(JSON.toJSONString(people));
        System.out.println(name);
    }

    public static List<People> cyclic(List<People> candidateCollect){
        Integer maxAge = candidateCollect.stream().max(Comparator.comparing(People::getAge)).get().getAge();
        // 电量最高电池
        People maxScorePeople = candidateCollect.stream().max(Comparator.comparing(People::getScore)).get();
        // 可用容量非最高，且 电池容量小于18AH，则标记为
        if (maxScorePeople.getAge() < maxAge) {
            maxScorePeople.setFlag(MARK_FLAG_TRUE);
        }else{
            maxScorePeople.setFlag(MARK_FLAG_FALSE);
        }
        return candidateCollect;
    }
}
