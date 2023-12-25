package zzzde.n.knowledge.algorithm.WRoundRobin;

public class RoundRobin {
    /**
     * @Date: 2019/01/16
     * @describe: 简单的轮训算法...
     * 				简介: 1. 将目标放置一容器内.
     * 					2. 定义一标识, 记录上次访问的该目标对象(标识应该是索引等,需要有规律性)
     * 					3. (该标识 + 1) 取模, 然后获取到该目标对象. 同时更新该目标标识
     * @return : null
     * @throws:
     */
    public static void main(String[] args) {
        int[] arr = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        int index = 4; // 索引:指定起始位置
        for (int i = 0; i < 50; i++) {
            int nextIndex = (index + 1) % arr.length;
            index = nextIndex;
            System.out.println("arr[index]:"+arr[index] + " ,index=" + index);
        }
    }
}
