package function;

import lombok.Data;

import java.util.function.Consumer;

/**
 * Consumer 函数方法
 *  accept 方法（抽象方法必须实现）；andThen自带方法，在接收accept方法之后，执行 addThen 方法。
 *  类似消息队列消费的感觉，先接收参数，之后再执行方法。
 *  这样我们可以将赋值动作和计算动作分开来执行
 * @author zzzde
 * @version 1.0
 * @date 2023/4/1 15:14
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        new FlowManager2<String>().startFlow("Hello World", (String message) -> System.out.println("Phase 1 : " + message), (String message) -> System.out.println("Phase 2 : " + message));
        // 函数式写法
        Consumer<String> phase1 = (String message) -> System.out.println("Phase 1 : " + message);
        Consumer<String> phase2 = (String message) -> System.out.println("Phase 2 : " + message);
        new FlowManager2<String>().startFlow("Hello World", phase1, phase2);
        System.out.println();

        // 函数式编程定义：接收到message时的参数。
        Consumer<String> demo1 = (String message) -> System.out.println("value:" + message) ;
        System.out.println("执行一堆操作");
        // 每接收一次对象都会执行之前定义好的函数方法
        demo1.accept("20230401");
        demo1.accept("222");

    }

}


