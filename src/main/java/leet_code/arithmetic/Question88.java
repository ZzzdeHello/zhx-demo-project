package leet_code.arithmetic;

/**
 * 【合并-排序】
 * 给你两个按 【非递减顺序】 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * <p>
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 */
public class Question88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // num1 【1 ，2 ，3 ，4】 p1指针
        // num2 【4 ，5 ，6 ，7，9】 p2指针
        int p1 = 0;
        int p2 = 0;
        int cur = 0;
        int[] tempArray = new int[m + n];
        // 双指针控制
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                // 当P1指针指向第一队列的最后，说明P1最后指向的值，比p2当前值还要大。
                cur = nums2[p2];
                p2++;
                // 可简写成 cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1];
                p1++;
                // 可简写成 cur = nums2[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                // 将较小值给cur
                cur = nums1[p1++];
            } else {
                // 将较小值给cur
                cur = nums2[p2++];
            }
            tempArray[p1 + p2 - 1] = cur;
        }
        // copy数组
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = tempArray[i];
        }
    }
}
