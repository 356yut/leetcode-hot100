package com.leetcode.hot100.m_heap;

class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums,0,n-1,n-k);
    }
    int quickSelect(int[] nums, int l, int r, int k) {
        if(l>=r) return nums[l];
        int x = nums[l],i=l-1,j=r+1;
        while(i<j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if(i<j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if(k<=j){
            return quickSelect(nums,l,j,k);
        }
        return quickSelect(nums,j+1,r,k);
    }
}
