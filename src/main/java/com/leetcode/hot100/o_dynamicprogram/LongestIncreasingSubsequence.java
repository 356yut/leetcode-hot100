package com.leetcode.hot100.o_dynamicprogram;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp =new int[n];
        int len=0;
        for(int num:nums){
            int l=0,r=len;
            while(l<r){
                int mid=(l+r)/2;
                if(dp[mid]<num){
                    l=mid+1;
                }else {
                    r=mid;
                }
            }
            dp[l]=num;
            if(l==len){
                len++;
            }
        }
        return len;
    }
}
