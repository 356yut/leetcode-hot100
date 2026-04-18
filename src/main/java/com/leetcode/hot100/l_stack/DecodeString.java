package com.leetcode.hot100.l_stack;

import java.util.ArrayDeque;
import java.util.Deque;

class DecodeString {
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<String> preStrStack = new ArrayDeque<>();
        StringBuilder resStr = new StringBuilder();
        int k = 0;
        for(char c : s.toCharArray()) {
            if(Character.isDigit(c)) {
                k=k*10+c-'0';
            } else if (c == '[') {
                countStack.push(k);
                preStrStack.push(resStr.toString());
                resStr = new StringBuilder();
                k = 0;
            }else if(c == ']') {
                int count = countStack.pop();
                StringBuilder prevStr = new StringBuilder().append(preStrStack.pop());
                for (int i = 0; i < count; i++) {
                    prevStr.append(resStr);
                }
                resStr = prevStr;
            }else{
                resStr.append(c);
            }
            System.out.println(resStr);
            System.out.println(preStrStack.peek());
            System.out.println("------");
        }
        return resStr.toString();
    }
    public static void main(String[] args) {
        DecodeString decodeString = new DecodeString();
        System.out.println(decodeString.decodeString("2[3[a]2[bc]]"));
    }
}
