package leet_code.dataStructure.day01;

import java.util.HashSet;

// 判断数组中是否存在重复元素 [1,2,3,1] true,否者返回false。
public class Solution01 {

    public boolean  method( int[] nums) {
        Boolean b = false ;
        HashSet<Integer> set =  new HashSet();
        for(int i : nums){
            set.add(i);
        }
        return set.size() < nums.length;
    }

    // 官方：排序在比较相邻位置是否一致。
}
