package com.leetcode.hot100.k_binarysearch;

class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if(nums[mid]>=nums[left]){
                if(target>=nums[left]&&target<nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else{
                if(target>nums[mid]&&target<=nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray sol = new SearchInRotatedSortedArray();
        System.out.println(sol.search(new int[]{4,5,6,0,1,2,3}, 0));
    }
}
