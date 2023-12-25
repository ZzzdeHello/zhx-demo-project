package zzzde.n.knowledge.algorithm.WRoundRobin;

//权重轮询策略 测试类。
public class WeightFactorProduceStrategyTest {
    public static void main(String[] args) {
        IProduceStrategy weightFcProStrategy = new WeightFactorProduceStrategy("0:100,1:15,2:20");

        for (int i = 0; i < 100; i++) {
//            weightFcProStrategy.getPartitionIdForTopic();
            System.out.println(weightFcProStrategy.getPartitionIdForTopic());
        }
    }
}
