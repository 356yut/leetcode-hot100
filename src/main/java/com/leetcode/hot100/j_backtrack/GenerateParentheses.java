package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.List;

class GenerateParentheses {
    List<String> result = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    public List<String> generateParenthesis(int n) {
        backtrack(n,0,0,0);
        return result;
    }

    private void backtrack(int n, int left, int right,int index) {
        if (index == n*2) {
            result.add(new String(cur));
            return;
        }
        if (left<n) {
            cur.append("(");
            backtrack(n,left+1,right,index+1);
            cur.deleteCharAt(cur.length()-1);
        }
        if (right<left) {
            cur.append(")");
            backtrack(n,left,right+1,index+1);
            cur.deleteCharAt(cur.length()-1);
        }
    }
    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();
        System.out.println(g.generateParenthesis(3));
    }
}
