package com.leetcode.hot100.l_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackDemo {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        // 取栈顶
        System.out.println(stack.peekFirst());
        // 取栈底
        System.out.println(stack.peekLast());

        // 栈大小
        System.out.println(stack.size());
        // 是否为空
        System.out.println(stack.isEmpty());
    }
}
