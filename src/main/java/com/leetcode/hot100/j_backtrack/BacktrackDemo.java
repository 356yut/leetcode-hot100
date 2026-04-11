package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法通用Demo
 * 包含：全排列、子集、组合 三大经典回溯场景
 */
public class BacktrackDemo {
    // 全局存储最终结果
    private static final List<List<Integer>> result = new ArrayList<>();
    // 全局存储当前递归的搜索路径
    private static final List<Integer> path = new ArrayList<>();

    // ==================== 1. 全排列（无重复元素）====================
    public static void permute(int[] nums) {
        // 标记元素是否被选中，用于剪枝
        boolean[] used = new boolean[nums.length];
        backtrackPermute(nums, used);
    }

    // 全排列回溯核心
    private static void backtrackPermute(int[] nums, boolean[] used) {
        // 终止条件：路径长度=数组长度，生成有效排列
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 遍历所有可选元素
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // 剪枝：跳过已选元素
            used[i] = true;       // 做出选择
            path.add(nums[i]);    // 记录路径
            backtrackPermute(nums, used); // 递归深入
            path.remove(path.size() - 1); // 撤销选择（核心回溯操作）
            used[i] = false;      // 恢复状态
        }
    }

    // ==================== 2. 子集 ====================
    public static void subsets(int[] nums) {
        backtrackSubset(nums, 0);
    }

    // 子集回溯核心
    private static void backtrackSubset(int[] nums, int start) {
        // 终止条件：无显式终止，所有路径都是有效子集
        result.add(new ArrayList<>(path));
        // 从start遍历，避免重复子集
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrackSubset(nums, i + 1);
            path.remove(path.size() - 1); // 回溯
        }
    }

    // ==================== 3. 组合（n个数选k个）====================
    public static void combine(int n, int k) {
        backtrackCombine(n, k, 1);
    }

    // 组合回溯核心（带剪枝优化）
    private static void backtrackCombine(int n, int k, int start) {
        // 终止条件：路径长度=k，生成有效组合
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 剪枝：提前结束循环，减少无效搜索
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtrackCombine(n, k, i + 1);
            path.remove(path.size() - 1); // 回溯
        }
    }

    // 重置数据（多次调用时清空）
    private static void reset() {
        result.clear();
        path.clear();
    }

    // 测试主方法
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        System.out.println("=== 数组 [1,2,3] 的全排列 ===");
        permute(nums);
        System.out.println(result);
        reset();

        System.out.println("\n=== 数组 [1,2,3] 的所有子集 ===");
        subsets(nums);
        System.out.println(result);
        reset();

        System.out.println("\n=== 从数字1-3中选2个的组合 ===");
        combine(3, 2);
        System.out.println(result);
    }
}