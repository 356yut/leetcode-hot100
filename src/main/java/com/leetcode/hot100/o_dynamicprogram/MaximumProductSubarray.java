package com.leetcode.hot100.o_dynamicprogram;

class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = curMax;
            curMax = Math.max(Math.max(curMax * nums[i], nums[i]), curMin * nums[i]);
            curMin = Math.min(Math.min(temp * nums[i], nums[i]), curMin * nums[i]);
            res = Math.max(curMax, res);
        }
        return res;
    }
}
