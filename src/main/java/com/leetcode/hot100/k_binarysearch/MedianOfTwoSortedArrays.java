package com.leetcode.hot100.k_binarysearch;

class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int m = (n1 + n2) / 2+1;
        int l1=0,l2=0;
        if((n1+n2)%2==1){
            while(m>0&&l1<n1&&l2<n2){
                if(nums1[l1]<=nums2[l2]){
                    l1++;
                    m--;
                }else{
                    l2++;
                    m--;
                }
            }
            if(m>0){
                if(l1<n1) return nums1[l1+m];
                else return nums2[l2+m];
            }
            return nums1[l1];
        }
    }
}
