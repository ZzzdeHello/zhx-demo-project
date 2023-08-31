package leet_code.arithmetic.day03;

import java.util.Arrays;

/**
 *  给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 *  [-12,0,2,3,11] 平方之后排序 新数组 [0,4,9,121,144]
 */
public class Solution {

    public int[]  my(int[] nums ){
        // 求绝对值，与0 比较距离 排序。
        return null;
    }
    // 直接排序
    public int[] answer_1 (int[] nums ){
        //方法一: 直接求平方 排序。
        int[] ans  = new int[nums.length];
        for(int i = 0 ; i< nums.length ; i++){
            ans[i] = nums[i] * nums[i] ;
        }
        Arrays.sort(ans);
        return ans;
    }

    // 方法二 ：双指针
    public int[] answer_2 (int[] nums){
        //确认 正数与负数的分界索引的位置
        int length = nums.length;
        int flgIndex = -1 ;
        for(int i = 0 ; i < length ; i++){
            if (nums[i] < 0)  flgIndex = i;
            else break;
        }
        int[] ans = new int[length]; // 新建答案数组
        int newAnsIndex = 0 ;
        int left = flgIndex ; //分界线左侧指针
        int right = flgIndex + 1 ; //分界线右侧指针
        while (left > 0 || right < length ){ // 在nums 数组范围内时才循环
            if(left < 0 ){//全大于零  (全小于零)
                ans[newAnsIndex] = nums[right]*nums[right]; //左指针已出局，直接算右指针即可 ，即只剩下右侧 单调递增
                right++;
            }else if ( right == length ){
                ans[newAnsIndex] = nums[left]*nums[left]; //右指针已出局，直接算左指针即可 ，即只剩下左侧，为单调递减
                left--;
            } else if ( nums[left]* nums[left] <= nums[right] * nums[right] ){
                ans[newAnsIndex] = nums[left]*nums[left];
                left--;
            }else {
                // if (nums[left]* nums[left] > nums[right] * nums[right] ) 可写可不写
                ans[newAnsIndex] = nums[right]*nums[right];
                right++;
            }
            newAnsIndex++;
        }

        return ans ;
    }



}
//同样地，我们可以使用两个指针分别指向位置 0 和 n−1，每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
// 这种方法无需处理某一指针移动至边界的情况，读者可以仔细思考其精髓所在。
//

class Solution_02 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n]; //新数组
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }
    // pos 从 n-1 逆序开始放入值。
}