package com.leetcode.hot100.o_dynamicprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> pre=new ArrayList<>();
        List<Integer> cur=new ArrayList<>();
        pre.add(1);
        res.add(pre);
        if (numRows == 1) {
            return res;
        }
        pre=new ArrayList<>();
        pre.add(1);
        pre.add(1);
        res.add(pre);
        for (int i = 2; i < numRows; i++) {
            cur.add(1);
            for (int j = 0; j < pre.size()-1; j++) {
                cur.add(pre.get(j) + pre.get(j+1));
            }
            cur.add(1);
            res.add(cur);
            pre=cur;
            cur=new ArrayList<>();
        }
        return res;
    }

    public static void main(String[] args) {
        PascalsTriangle p = new PascalsTriangle();
        List<List<Integer>> res = p.generate(5);
        for (List<Integer> row : res) {
            System.out.println(Arrays.toString(row.toArray()));
        }
    }
}
