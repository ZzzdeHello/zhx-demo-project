package collection.stream.filter;

import com.alibaba.fastjson.JSON;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class StreamFilterMain {
    public static void main(String[] args) {
        Map data = new HashMap();
        List<Map<String,Object>> orgList = new ArrayList<>();
        Map map1 = new HashMap(); map1.put("privFlag",true); map1.put("name","1");
        Map map2 = new HashMap(); map2.put("privFlag",true); map2.put("name","2");
        Map map3 = new HashMap(); map3.put("privFlag",true); map3.put("name","3");
        Map map4 = new HashMap(); map4.put("privFlag",false); map4.put("name","4");
        orgList.add(map1);
        orgList.add(map2);
        orgList.add(map3);
        orgList.add(map4);
        data.put("orgList",orgList);
        List<Map<String,Object>> orgListResult = (List<Map<String,Object>>)data.get("orgList");
        List<Map<String, Object>> neworgListResult = orgListResult.stream().filter(map -> (boolean) map.get("privFlag")).collect(toList());
        System.out.println(JSON.toJSONString(neworgListResult));
    }

    private static void streamFilter() {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四光");
        list.add("吴克x");
        list.add("吴x");
        list.add("吴xx");

        list.stream()
                .filter(name -> name.startsWith("吴"))
                .filter(name-> name.length()==3)
                .forEach(name->System.out.println(name));
        System.out.println(JSON.toJSON(list));
    }





    /**
     *流的方法 总诉
     *分割合并成一个字符串list
     * @return
     */
    private static String streamAll(){
        List<User> uList = new ArrayList<>();
        User u1 = new User();
        u1.setName("a1;a2;a3;a4;a5");

        User u2 = new User();
        u2.setName("b1;b2;b3;b4;b5");

        uList.add(u1);
        uList.add(u2);

        List<String>   addrList = uList.stream().map(User::getName).flatMap(x-> Arrays.stream(x.split(";"))).collect(toList());
        //或者
        List<String> ridStrList = uList.stream().map(User::getName).map(x -> x.split(";")).flatMap(Arrays::stream).collect(toList());
        System.out.println(addrList);
        System.out.println(ridStrList);
        return "addrList" + addrList.toString() + "ridStrList" + ridStrList.toString();
    }

    /**
     *"Hello","World" 返回 ["H","e","l","o","W","r","d"]
     */
    public static void streamError(){
        String[] words = new String[]{"Hello","World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        a.forEach(System.out::print); // 这样的打印结果为 String[] 的 list 集合 不符合条件
        // 需要 List<String>

        //代码运行过程 Stream<String> -> Stream<String[]> map操作时候：两个单词各自生成 一个String[]
    }

    /**
     * 对上述进行扁平化处理
     */
    public static void streamErrorFlat(){
        String[] words = new String[]{"Hello","World"};
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        //代码运行过程 Stream<String> -> Stream<String[]> map操作时候：两个单词各自生成 一个String[]
        // 加入扁平化处理之后，所有stream流合并成一个流处理
        //打印
        a.forEach(System.out::print);
    }

}
