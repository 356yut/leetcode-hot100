package com.leetcode.hot100.p_multi_dynamicprogram;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n=s.length();
        if(n<2){
            return s;
        }
        int maxLen=1;
        int begin=0;
        boolean[][] dp=new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i]=true;
        }
        char[] chars=s.toCharArray();
        for (int len = 2; len <= n; len++) {
            for (int left = 0; left <= n-len; left++) {
                int right=left+len-1;
                if(chars[right]!=chars[left]){
                    dp[left][right]=false;
                }else {
                    if(right-left<3){
                        dp[left][right]=true;
                    }else {
                        dp[left][right]=dp[left+1][right-1];
                    }
                }
                if(dp[left][right]&&right-left+1>maxLen){
                    maxLen=right-left+1;
                    begin=left;
                }
            }
        }

        return s.substring(begin,begin+maxLen);
    }
}
