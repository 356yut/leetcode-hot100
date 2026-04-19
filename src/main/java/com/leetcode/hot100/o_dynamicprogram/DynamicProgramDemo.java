package com.leetcode.hot100.o_dynamicprogram;

public class DynamicProgramDemo {
    public static void main(String[] args) {
        // 测试爬楼梯问题（动态规划核心案例）
        int stairNum = 10;
        System.out.println("爬" + stairNum + "阶楼梯的方法数（DP解法）：" + climbStairs(stairNum));
        System.out.println("爬" + stairNum + "阶楼梯的方法数（递归暴力解法）：" + climbStairsRecursion(stairNum));

        // 测试斐波那契数列（动态规划基础案例）
        int fibNum = 10;
        System.out.println("斐波那契数列第" + fibNum + "项（DP解法）：" + fibonacci(fibNum));
    }

    /**
     * 爬楼梯问题 - 动态规划标准实现
     * 问题：n阶楼梯，每次只能爬1阶或2阶，求总方法数
     * 严格对应动态规划5个常用操作
     */
    public static int climbStairs(int n) {
        // 边界特殊值处理
        if (n <= 1) return 1;
        // 操作1：定义状态 dp[i] = 爬i阶楼梯的总方法数
        int[] dp = new int[n + 1];
        // 操作3：初始化边界值（最小子问题的解）
        dp[1] = 1;
        dp[2] = 2;
        // 操作4：确定遍历顺序（正向遍历，依赖前序状态）
        for (int i = 3; i <= n; i++) {
            // 操作2：状态转移方程 dp[i] = dp[i-1] + dp[i-2]
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // 操作5：获取最终结果
        return dp[n];
    }

    /**
     * 爬楼梯 - 递归暴力解法（用于对比，存在大量重复计算）
     */
    public static int climbStairsRecursion(int n) {
        if (n <= 1) return 1;
        return climbStairsRecursion(n - 1) + climbStairsRecursion(n - 2);
    }

    /**
     * 斐波那契数列 - 动态规划基础实现
     * 定义：F(0)=0，F(1)=1，F(n)=F(n-1)+F(n-2)
     */
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        // 定义状态
        int[] dp = new int[n + 1];
        // 初始化边界
        dp[0] = 0;
        dp[1] = 1;
        // 遍历计算
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
