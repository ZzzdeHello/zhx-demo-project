package leet_code.arithmetic;

import java.util.PriorityQueue;

/**
 * 堆（优先队列）
 * <p>
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为x 和y，且x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果x == y，那么两块石头都会被完全粉碎；
 * 如果x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 */
public class Question1046 {

    public int getLastStone(int[] stones) {
        /*
         * 特点
         * 优先队列，按照给定的排序方式自己排序队列
         * 优先级队列不允许空元素;不允许插入不可比较的对象(这样做可能会导致ClassCastException)。
         * 队列的头是相对于指定顺序的最小元素
         * 优先队列是无界的，右内部控制容量的元素，容量会自动增长。
         */
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            // 先后去除两个最大的元素
            int a = pq.poll();
            int b = pq.poll();
            // 比较
            if (a > b) {
                // 如果两个元素不同，则减去值，然后重新放入队列
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
