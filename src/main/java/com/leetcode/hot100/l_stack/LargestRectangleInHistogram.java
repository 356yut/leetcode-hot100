package com.leetcode.hot100.l_stack;

import java.util.ArrayDeque;
import java.util.Deque;

class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int[] newHeights = new int[heights.length+1];
        System.arraycopy(heights, 0, newHeights, 0, heights.length);
        newHeights[heights.length] = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int max = newHeights[0];
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                int topIndex = stack.pop();
                int topHeight = newHeights[topIndex];
                int width = stack.isEmpty()?i:i - stack.peek() - 1;
                max = Math.max(max, topHeight * width);
            }
            stack.push(i);
        }
        return max;
    }
}
