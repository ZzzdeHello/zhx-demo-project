package zzzde.n.knowledge.pattern.strategyMe;

// 自定义选择的线程方式
// 抽象的策略方式
public interface SendThreadWay extends Runnable {
    // 设置策略参数
    void setParam (SendQO sendQO) ;
}
