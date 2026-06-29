package com.leetcode.hot100.o_dynamicprogram;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet=new HashSet<>(wordDict);
        int strLength=s.length();
        boolean[] dp=new boolean[strLength+1];
        dp[0]=true;
        for (int i = 0; i <= strLength; i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j]&& wordSet.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[strLength];
    }
}
