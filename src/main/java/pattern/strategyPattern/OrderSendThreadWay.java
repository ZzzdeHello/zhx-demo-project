package pattern.strategyPattern;

import java.util.Map;

// 自定义选择的线程方式
// 抽象的策略方式
public interface OrderSendThreadWay extends Runnable {
    // 设置策略参数
    public Map<String,Object> setParam (Map<String,Object> param) ;
}
