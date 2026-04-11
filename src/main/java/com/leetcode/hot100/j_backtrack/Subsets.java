package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.List;

class Subsets {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> cur = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        bactrack(0,nums);
        return res;
    }

    private void bactrack(int i, int[] nums) {
        if (i == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        cur.add(nums[i]);
        bactrack(i+1, nums);
        cur.remove(cur.size()-1);
        bactrack(i+1, nums);
    }

    public static void main(String[] args) {
        Subsets s = new Subsets();
        System.out.println(s.subsets(new int[]{1, 2, 3}));
    }
}
