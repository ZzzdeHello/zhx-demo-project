package pattern.strategyPattern;

import java.util.Map;

public class WayContext {
    private OrderSendThreadWay strategy;
    private Map param;
    public WayContext(OrderSendThreadWay strategy, Map param){
        this.param = param;
        this.strategy = strategy;
    }
    /**
     * 策略方法
     */
    public void contextInterface(){
        strategy.setParam(param);
        Thread thread = new Thread(strategy);
        thread.start();
    }
}
