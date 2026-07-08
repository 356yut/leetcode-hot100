package com.leetcode.hot100.q_skill;

public class SortColors {
    public void sortColors(int[] nums) {
        int left=0;
        int right= nums.length-1;
        int cur=0;
        while (cur<=right){
            if(nums[cur]==0){
                swap(nums,left,cur);
                left++;
                cur++;
            } else if (nums[cur]==2) {
                swap(nums,cur,right);
                right--;
            }else {
                cur++;
            }
        }
    }

    private void swap(int[] nums, int n1, int n2) {
        int temp=nums[n1];
        nums[n1]=nums[n2];
        nums[n2]=temp;
    }
}
