package algorithm.slidingwindow;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *      每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢
 *      到达n台阶时，只有两种情况可以到达，上一步为跳了一阶和上一步跳了两阶 。总的情况为两者的和
 *      状态函数，f(x)=f(x−1)+f(x−2)
 *      利用滑动窗口来进行和的动态规划，r中记录当前n阶的可能出现的情况数量， p 和 q 记录 n-1的 和 n-2的情况
 *
 * 【斐波那契数列】
 * 【动态规划与滑动窗口法】
 * 滑动，区间范围是移动的，按照一定方法移动
 * 窗口，窗口大小可变化，缩小
 *
 * @Author zzzde
 * @Date 2023/10/12
 */
public class Main {
    public static void main(String[] args) {
        // n 为输入值 ,n 的 N的取值范围为 （0-N）的整数，
        int n = 5;
        int p = 1 , q = 1;
        int r = 0 ;
        for (int i = 0; i < n -1 ; i++) {
            r = p + q ;
            p = q ;
            q = r ;
        }
        System.out.println(r);
    }
}
