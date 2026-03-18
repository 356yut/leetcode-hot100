package com.leetcode.hot100.d_substring;
/*
给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的子数组的个数。子数组是数组中元素的连续非空序列。
示例 1：输入：nums = [1,1,1], k = 2，输出：2；
示例 2：输入：nums = [1,2,3], k = 3，输出：2。
提示：1 <= nums.length <= 2 * 10^4，-1000 <= nums[i] <= 1000，-10^7 <= k <= 10^7。
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums={1,1,1};
        int k=2;
        System.out.println(subarraySum(nums,k));
    }
    public static int subarraySum(int[] nums, int k) {
        int result = 0;
        Map<Integer,Integer> prefixMap = new HashMap<>();
        prefixMap.put(0,1);
        int prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            // prefixSum[j]-prefixSum[i]=k
            // prefixSum[i]=prefixSum[j]-k
            if (prefixMap.containsKey(prefixSum - k)) {
                result += prefixMap.get(prefixSum - k);
            }
            prefixMap.put(prefixSum, prefixMap.getOrDefault(prefixSum, 0) + 1);
        }
        return result;
    }
}
