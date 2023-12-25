package zzzde.n.knowledge.pattern.strategyPattern;

public class Main {
    //普通的单纯只有策略模式时候的 客户端调用方法
//    public static void main(String[] args) {
//        System.out.println("------------start------------");
//        Map param = new HashMap();
//        //客户类通过环境类获取具体策略
//        Context c = new Context();
//        OrderSendThreadWay strategyA = new BatchSendThread();
//        c.setOrderSendThreadWay(strategyA);
//        c.setParam(param);
//        c.contextInterface();
//        System.out.println("------------------------");
//        OrderSendThreadWay strategyB = new BatchSendForPeopleThread();
//        c.setOrderSendThreadWay(strategyB);
//        c.setParam(param);
//        c.contextInterface();
//        System.out.println("------------over------------");
//    }

    // 单例模式 导致新建的对象同一个。该种方式无法在线程实例中带入参数。不能使用
//    public static void main(String[] args) {
//        System.out.println("------------start------------");
//        Map param = new HashMap();
//        OrderSendThreadWay people = StrategyFactoryBean.getPromotionStrategy("PEOPLE");
//        OrderSendThreadWay people2 = StrategyFactoryBean.getPromotionStrategy("PEOPLE");
//        System.out.println(people.hashCode());
//        System.out.println(people2.hashCode());
//        people.setParam(param);
//        Thread zzzde.project.technic.thread = new Thread(people);
//        zzzde.project.technic.thread.start();
//        System.out.println("------------end------------");
//    }

}
