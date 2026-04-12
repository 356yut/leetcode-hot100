package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.List;

class PalindromePartitioning {
    List<List<String>> result = new ArrayList<List<String>>();
    List<String> path = new ArrayList<>();
    boolean[][] dp;
    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];
        for (int i=n-1; i>=0; i--) {
            for (int j=i; j<n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if(i>=j-1) dp[i][j] = true;
                    else if(dp[i+1][j-1]) dp[i][j] = true;
                }
            }
        }
        bactrack(s,0);
        return result;
    }

    private void bactrack(String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if(dp[start][i]){
                path.add(s.substring(start, i+1));
                bactrack(s, i+1);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitioning p = new PalindromePartitioning();
        System.out.println(p.partition("aab"));
    }
}
