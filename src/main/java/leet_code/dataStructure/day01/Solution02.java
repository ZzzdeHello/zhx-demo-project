package leet_code.dataStructure.day01;

// 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//示例：
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解
public class Solution02 {

    public int[]  method( int[] nums) {
       return null;
    }
}
class SolutionGF {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}

/*
        假设 nums 数组的长度是 n，下标从 0 到 n−1。
        max{f(i) }, i 从 0-n
        如何求得 f(i).
        写出这样的动态规划转移方程：
 */


//
//        因此我们只需要求出每个位置的 f(i)，然后返回 fff 数组中的最大值即可。那么我们如何求 f(i)f(i)f(i) 呢？我们可以考虑 nums[i]\textit{nums}[i]nums[i] 单独成为一段还是加入 f(i−1)f(i-1)f(i−1) 对应的那一段，这取决于 nums[i]\textit{nums}[i]nums[i] 和 f(i−1)+nums[i]f(i-1) + \textit{nums}[i]f(i−1)+nums[i] 的大小，我们希望获得一个比较大的，于是可以
//
//        f(i)=max⁡{f(i−1)+nums[i],nums[i]}f(i) = \max \{ f(i-1) + \textit{nums}[i], \textit{nums}[i] \} f(i)=max{f(i−1)+nums[i],nums[i]}
//
//        不难给出一个时间复杂度 O(n)O(n)O(n)、空间复杂度 O(n)O(n)O(n) 的实现，即用一个 fff 数组来保存 f(i)f(i)f(i) 的值，用一个循环求出所有 f(i)f(i)f(i)。考虑到 f(i)f(i)f(i) 只和 f(i−1)f(i-1)f(i−1) 相关，于是我们可以只用一个变量 pre\textit{pre}pre 来维护对于当前 f(i)f(i)f(i) 的 f(i−1)f(i-1)f(i−1) 的值是多少，从而让空间复杂度降低到 O(1)O(1)O(1)，这有点类似「滚动数组」的思想
