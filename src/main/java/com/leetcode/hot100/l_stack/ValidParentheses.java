package com.leetcode.hot100.l_stack;

import java.util.ArrayDeque;
import java.util.Deque;

class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            char top = stack.pop();
            if (c == ')' && top != '(') return false;
            if (c == '}' && top != '{') return false;
            if (c == ']' && top != '[') return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses v = new ValidParentheses();
        System.out.println(v.isValid("()[{}]"));
    }
}
