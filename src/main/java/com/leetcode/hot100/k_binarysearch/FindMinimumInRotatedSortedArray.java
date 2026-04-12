package com.leetcode.hot100.k_binarysearch;

class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        if(nums[0]<=nums[right]) {
            return nums[0];
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid>0&&nums[mid-1]>nums[mid]) {
                return nums[mid];
            }
            if (nums[mid]<nums[right]) {
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray obj = new FindMinimumInRotatedSortedArray();
        System.out.println(obj.findMin(new int[]{2,1}));
    }
}
