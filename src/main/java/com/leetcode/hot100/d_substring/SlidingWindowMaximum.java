package com.leetcode.hot100.d_substring;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*题目描述：给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位，返回滑动窗口中的最大值。
示例 1：输入：nums = [1,3,-1,-3,5,3,6,7], k = 3，输出：[3,3,5,5,6,7]
示例 2：输入：nums = [1], k = 1，输出：[1]
提示：1 <= nums.length <= 10^5，-10^4 <= nums[i] <= 10^4，1 <= k <= nums.length*/
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) return new int[0];
        int[] res = new int[len - k + 1];
        // 存储元素，按i排序的（可能有的不在里面）
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        for (int i = k; i < len; i++) {
            // 移除左边元素
            if (deque.peekFirst() == nums[i - k]) deque.removeFirst();
            // 移除比右边小的元素，然后移入右边元素
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();

            System.out.println(deque);
        }
        return res;
    }
}
