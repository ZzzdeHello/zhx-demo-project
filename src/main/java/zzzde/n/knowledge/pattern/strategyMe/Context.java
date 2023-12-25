package zzzde.n.knowledge.pattern.strategyMe;


/**
 * 环境 决定使用何种策略 (具有设置和选择做菜策略的方法)
 * 持有一个策略类的引用，最终给客户端调用
 */

//进阶 环境类可以和工厂类结合 策略工厂类：管理大量策略类
public class Context {

    private SendThreadWay orderSendThreadWay;
    private SendQO param;

    public SendThreadWay getOrderSendThreadWay() {
        return orderSendThreadWay;
    }

    public void setOrderSendThreadWay(SendThreadWay orderSendThreadWay) {
        this.orderSendThreadWay = orderSendThreadWay;
    }

    public SendQO getParam() {
        return param;
    }

    public void setParam(SendQO param) {
        this.param = param;
    }

    /**
     * 策略方法
     */
    public void contextInterface() {
        orderSendThreadWay.setParam(param);
        Thread thread = new Thread(orderSendThreadWay);
        thread.start();
    }
}
