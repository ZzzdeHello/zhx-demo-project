package leet_code.arithmetic.day01;

/**
 * 二分法
 * 题：给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 假设有 [-1,0,3,9,10,19]
 * 目标为 9 则输出 3 数组下标的值。
 */
public class Solution {

    int[] is = new int[]{-1,0,3,9,10,19};

    int target = 9 ;

    public int search(int[] ints , int target ){
        int first = 0 ;
        int end = ints.length -1 ;
        while (first <= end ){
            int mid = ( end - first ) / 2 + first ;
            int number = ints[mid];
            if(number == target ){
               return number;
            }else if( number > target){
                end = mid - 1 ;
            }else {
                first = mid + 1 ;
            }
        }
        return  -1;
    }
/*
    根据 数组下标分析 在 x轴上 [0,length] 上 ，由于数组是有序的 所以比较 target 数字大小和mid值即可。
    [0,mid] 或者是 [mid,length]
    关键在于如何变化的确认二分之后的 mid值。
    所以关键点在于： (end - first ) /2 + first 这一步，再结合循环之后的mid-1 或者mid+1 就可以保证循环之后取到新的mid值。
    即 ：end/2 + first/2 = （end+first）/2
 */

}
