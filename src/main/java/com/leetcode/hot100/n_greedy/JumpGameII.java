package com.leetcode.hot100.n_greedy;

class JumpGameII {
    public int jump(int[] nums) {
        int end=0,maxReach=0;
        int count=0;
        for(int i=0;i<nums.length-1;i++){
            maxReach=Math.max(maxReach,i+nums[i]);
            if(i==end){
                end=maxReach;
                count++;
            }
        }
        return count;
    }
}
