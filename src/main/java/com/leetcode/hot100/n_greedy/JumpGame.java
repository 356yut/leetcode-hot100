package com.leetcode.hot100.n_greedy;

class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReach=nums[0];
        int i=0;
        while(i<=maxReach && i<nums.length) {
            maxReach=Math.max(maxReach,i+nums[i]);
            i++;
        }
        return i>nums.length-1;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        System.out.println(jumpGame.canJump(new int[]{3,2,1,0,4}));
    }
}
