package com.leetcode.hot100.p_multi_dynamicprogram;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if(m==1||n==1){
            return 1;
        }
        int k=Math.min(m-1,n-1);
        int total=m+n-2;
        long res=1;
        for (int i = 1; i <= k; i++) {
            res=res*(total-k+i)/i;
        }
        return (int)res;
    }
}
