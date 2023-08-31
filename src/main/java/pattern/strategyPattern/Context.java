package pattern.strategyPattern;

import java.util.Map;

/**
 * 环境 决定使用何种策略 (具有设置和选择做菜策略的方法)
 * 持有一个策略类的引用，最终给客户端调用
  */

//进阶 环境类可以和工厂类结合 策略工厂类：管理大量策略类
public class Context {

    private OrderSendThreadWay orderSendThreadWay;
    private Map param ;

    public OrderSendThreadWay getOrderSendThreadWay() {
        return orderSendThreadWay;
    }

    public void setOrderSendThreadWay(OrderSendThreadWay orderSendThreadWay) {
        this.orderSendThreadWay = orderSendThreadWay;
    }

    public Map getParam() {
        return param;
    }

    public void setParam(Map param) {
        this.param = param;
    }

    /**
     * 策略方法
     */
    public void contextInterface(){
        orderSendThreadWay.setParam(param);
        Thread thread = new Thread(orderSendThreadWay);
        thread.start();
    }
}
