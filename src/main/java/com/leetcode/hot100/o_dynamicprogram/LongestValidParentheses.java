package com.leetcode.hot100.o_dynamicprogram;

import java.util.Deque;
import java.util.LinkedList;

class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxLen=0;
        Deque<Integer> stack=new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='('){
                stack.push(i);
            }else {
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else {
                    maxLen=Math.max(maxLen,i-stack.peek());
                }
            }
        }
        return maxLen;
    }
}
