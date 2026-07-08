package com.leetcode.hot100.q_skill;



public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n=nums.length;
        int i,j;
        for (i = n-1; i >0 ; i--) {
            if(nums[i-1]<nums[i]){
                break;
            }
        }
        if(i>=1) {
            for (j = n - 1; j >= 0; j--) {
                if (nums[j] > nums[i-1]) {
                    break;
                }
            }
            swap(nums, i-1, j);
        }
        reverse(nums,i,n-1);
    }

    private void reverse(int[] nums, int i, int i1) {
        while (i<i1){
            swap(nums, i,i1);
            i++;
            i1--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }


}
