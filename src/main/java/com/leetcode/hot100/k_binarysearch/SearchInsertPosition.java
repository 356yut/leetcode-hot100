package com.leetcode.hot100.k_binarysearch;

class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] < target) {
                i = mid + 1;
            }else {
                j = mid - 1;
            }
        }
        return i;
    }
    public static void main(String[] args) {
        SearchInsertPosition si = new SearchInsertPosition();
        System.out.println(si.searchInsert(new int[]{1,3,5,6}, 5));
    }
}
