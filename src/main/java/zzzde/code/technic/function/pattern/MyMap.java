package zzzde.code.technic.function.pattern;

import java.util.function.BiFunction;

public class MyMap<K,R>  {

    public R compute(K key,
                     BiFunction<? super K, ? super R, ? extends R> remappingFunction) {
        // V v = remappingFunction.apply(key, v);
        // return  v ;
        return  null ;
    }

}
