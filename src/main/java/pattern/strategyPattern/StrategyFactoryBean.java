package pattern.strategyPattern;

import pattern.strategyPattern.someWay.BatchSendForAreaThread;
import pattern.strategyPattern.someWay.BatchSendForFileThread;
import pattern.strategyPattern.someWay.BatchSendForPeopleThread;
import pattern.strategyPattern.someWay.BatchSendThread;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//  简单工厂模式又称为静态工厂方法模型：根据用户的选择条件来实例化相关的类 。
//  实例化对象的时候不再使用 new Object()形式，可以根据用户的选择条件来实例化相关的类。对于客户端来说，去除了具体的类的依赖。只需要给出具体实例的描述给工厂，工厂就会自动返回具体的实例对象
//  懒汉式单例模式，线程不安全。使用单例模式来管理这个bean 保证只有一个实例，（避免频繁创建类，回收类）
public class StrategyFactoryBean {
    // 工厂内策略的集合
    private static Map<String, OrderSendThreadWay> STRATEGY_MAP = new HashMap<>();
    static{
        STRATEGY_MAP.put(SendWayKeys.PEOPLE, new BatchSendForPeopleThread());
        STRATEGY_MAP.put(SendWayKeys.FILE, new BatchSendForFileThread());
        STRATEGY_MAP.put(SendWayKeys.AREA, new BatchSendForAreaThread());
    }
    private static final OrderSendThreadWay EMPTY = new BatchSendThread();

    private StrategyFactoryBean(){} // 私有化 策略工厂的构造方法
    // 简单工厂类的核心方法：无需提供子类类名，只需要提供字符串即可确认使用那种
    public static OrderSendThreadWay getPromotionStrategy(String sendWayKey){
        OrderSendThreadWay strategy = STRATEGY_MAP.get(sendWayKey);
        return null == strategy ? EMPTY : strategy;
    }
    // 固定值
    private interface SendWayKeys{
        String PEOPLE = "PEOPLE";
        String FILE = "FILE";
        String AREA = "AREA";
    }

    public static Set<String> getKeysSet(){
        return STRATEGY_MAP.keySet();
    }
}
