package zzzde.code.technic.function;

import java.util.function.Consumer;

/**
 * @author zzzde
 * @version 1.0
 * @date 2023/4/1 15:17
 */
public class FlowManager2<T>{

    public void startFlow(T hello_world, Consumer<T> o, Consumer<T> o1) {
        o.accept(hello_world);
        o1.accept(hello_world);
    }
}



