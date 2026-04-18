package com.leetcode.hot100.k_binarysearch;

class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int n1 = nums1.length, n2 = nums2.length;
        int left = 0, right = n1;
        // i1和i2分别是两个数组的分割点
        int i1,i2;
        // median1是前一部分的最大值，median2是后一部分的最小值
        int median1 = 0, median2 = 0;
        while (left <= right) {
            i1 = left + (right - left) / 2;
            i2 = (n1+n2+1)/2-i1;
            // nums1左边最大
            int nums_in1 = (i1==0?Integer.MIN_VALUE:nums1[i1-1]);
            // num1右边最小
            int nums_i1 = (i1==n1?Integer.MAX_VALUE:nums1[i1]);
            // nums2左边最大
            int nums_in2 = (i2==0?Integer.MIN_VALUE:nums2[i2-1]);
            // nums2右边最小
            int nums_i2 = (i2==n2?Integer.MAX_VALUE:nums2[i2]);

            if(nums_in1<=nums_i2){
                median1=Math.max(nums_in1,nums_in2);
                median2=Math.min(nums_i1,nums_i2);
                left = i1 + 1;
            }else {
                right = i1 - 1;
            }
        }
        return (n1+n2)%2==0?(median1+median2)/2.0:median1;
    }
}
