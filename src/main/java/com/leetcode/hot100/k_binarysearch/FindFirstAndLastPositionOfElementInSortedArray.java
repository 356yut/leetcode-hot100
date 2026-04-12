package com.leetcode.hot100.k_binarysearch;

class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int left = findLeft(nums, 0, nums.length - 1, target);
        int right = findRight(nums, 0, nums.length - 1, target);
        if (left > nums.length - 1 || nums[left] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{left, right};
    }

    private int findRight(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private int findLeft(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElementInSortedArray f = new FindFirstAndLastPositionOfElementInSortedArray();
        System.out.println(f.searchRange(new int[]{1, 2, 3, 4, 4, 4, 7, 8, 9}, 4));
    }
}
