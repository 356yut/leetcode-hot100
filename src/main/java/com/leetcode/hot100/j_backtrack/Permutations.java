package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.List;

class Permutations {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            visited[i] = false;
        }
        backtrack(nums,visited);
        return res;
    }

    private void backtrack(int[] nums, boolean[] visited) {
        if(path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            path.add(nums[i]);
            visited[i] = true;
            backtrack(nums,visited);
            path.remove(path.size()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();
        System.out.println(p.permute(new int[]{1, 2, 3}));
    }
}
