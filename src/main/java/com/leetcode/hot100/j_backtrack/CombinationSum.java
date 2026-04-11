package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CombinationSum {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates,target,0);
        return res;
    }

    private void backtrack(int[] candidates, int remain,int start) {
        if(remain == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = start; i < candidates.length; i++) {
            if(candidates[i] > remain) {
                continue;
            }
            cur.add(candidates[i]);
            backtrack(candidates,remain - candidates[i],i);
            cur.remove(cur.size()-1);
        }
    }
    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(new int[]{2,3,6,7}, 7));
    }
}
