package zzzde.code.technic.codestream.intermediate.contain;

import cn.hutool.core.convert.Convert;
import zzzde.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 集合内通过流判断 ，判断某个key的value是否包含某种值
public class StreamContainSearch02 {

    public static void main(String[] args) {
       List<Student> list = new ArrayList<>();
        List<String> collect = list.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(collect);
        collect.add("s");
        System.out.println(collect);

    }

}
