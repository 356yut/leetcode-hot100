package com.leetcode.hot100.n_greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        int[] lastOccurrence = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            lastOccurrence[s.charAt(i) - 'a']=i;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);
            if (i == end) {
                res.add(end - start+1);
                start = i+1;
            }
        }
        return res;
    }
}
