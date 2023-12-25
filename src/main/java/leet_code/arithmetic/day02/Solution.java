package leet_code.arithmetic.day02;

/**
 *  默认该数组 升序 有序 并且不重复
 *  给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(zzzde.project.technic.log n) 的算法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例： [0,3,4,6,9] 输入 2
 * 输出： 1 （ 插入之后 target 所在的索引的值。）
 */
public class Solution {

    /**
     *
     * @param nums  升序 有序 并且不重复 的数组
     * @param target 目标数字
     * @return
     */
    public int insertSearch_My(int[] nums , int target){
        int left = 0 ;
        int right = nums.length ;
        while (left < right ){
            int mid  =  (right - left ) / 2  + left ;
            if( nums[mid] == target ){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1 ;
            }else if (nums[mid] > target){
                right = mid ;
            }
        }
        // 此时可以确认一个 int[mid]  此时的 left = right
        // 然后比较  int[mid] 与 target的关系 确认是要 +1 还是-1.
        if(nums[left] < target ) return left +1 ;
        if(nums[left] > target ) return left -1 ;
        return 0;
    }
    /*
        zzzde.project.technic.log(n)的事件复杂度 直接明示了 使用二分法的算法解题目。
     */

    //-----------------------------
    public int insertSearch_Answer( int[] nums , int target ){
        int n = nums.length ;
        int left= 0 ;
        int right = n - 1 ;
        while (left <= right) {
            int mid = ( right -left ) / 2 + left ;
            if( nums[mid]<target )
                left =mid+1;
            else right =mid - 1;
        }
        return left;
    }
    /*
        解题思路：
           规律为：
           nums[pos-1] < target <=  nums[pos] ；pos为nums中需要找到的位置 position
     */
}
