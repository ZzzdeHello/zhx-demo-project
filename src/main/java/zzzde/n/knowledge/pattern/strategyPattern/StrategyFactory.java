package zzzde.n.knowledge.pattern.strategyPattern;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// 利用工厂模式管理 大量具体策略类
public class StrategyFactory {
    // 工厂内策略的集合
    private static Map<String, OrderSendThreadWay> STRATEGY_MAP = new HashMap<>();
    // todo 根据key 返回一个新实例，并将param 参数带入进去
    private StrategyFactory(){} // 私有化 策略工厂的构造方法
    // 简单工厂类的核心方法：无需提供子类类名，只需要提供字符串即可确认使用那种
    public static OrderSendThreadWay getPromotionStrategy(String sendWayKey,Map param){
        OrderSendThreadWay strategy = STRATEGY_MAP.get(sendWayKey);
        strategy.setParam(param);
        return strategy;
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
