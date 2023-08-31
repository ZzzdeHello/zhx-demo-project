package lambda.mylambda;

/**
 * Context 是一个环境类。 使用了某种策略的类 --> 构造方法中属性有一个接口
 * 新建Context类时候，使用 实现了策略接口的某类。
 * 　Context上下文角色，也叫Context封装角色，起承上启下的作用，屏蔽高层模块对策略、算法的直接访问，封装可能存在的变化。
 */
public class ContentCenter {
    private TravelWayInterface travelWayInterface;
    public ContentCenter (TravelWayInterface travelWayInterface){
        this.travelWayInterface = travelWayInterface;
    }
    public String executeTravalWay(int M){
        return this.travelWayInterface.goOutMethod(M);
    }

}
