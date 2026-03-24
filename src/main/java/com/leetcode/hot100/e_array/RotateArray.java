package com.leetcode.hot100.e_array;


import java.util.Arrays;

/*给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
示例 1: 输入: nums = [1,2,3,4,5,6,7], k = 3 输出: [5,6,7,1,2,3,4]
示例 2: 输入：nums = [-1,-100,3,99], k = 2 输出：[3,99,-1,-100]
提示：1 <= nums.length <= 10^5，-2^31 <= nums[i] <= 2^31 - 1，0 <= k <= 10^5*/
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);

    }
    public static void reverse(int[] nums,int start,int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
