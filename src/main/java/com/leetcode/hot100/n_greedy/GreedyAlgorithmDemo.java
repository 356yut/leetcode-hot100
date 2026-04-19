package com.leetcode.hot100.n_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GreedyAlgorithmDemo {
    public static void main(String[] args) {
        // 1. 演示活动选择问题（贪心经典场景）
        System.out.println("===== 活动选择问题 =====");
        // 定义活动：数组格式[开始时间, 结束时间]
        int[][] activities = {{1, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 7}};
        List<int[]> selectActivities = activitySelect(activities);
        System.out.println("选中的不重叠活动（开始-结束）：");
        for (int[] act : selectActivities) {
            System.out.print(act[0] + "-" + act[1] + " ");
        }

        // 2. 演示零钱兑换问题（贪心常用场景）
        System.out.println("\n===== 零钱兑换问题 =====");
        // 硬币面额（降序排列，适配贪心策略）
        int[] coins = {5, 2, 1};
        // 目标金额
        int target = 11;
        int minCoinNum = coinChange(coins, target);
        System.out.println("凑成" + target + "元最少需要硬币数量：" + minCoinNum);
    }

    /**
     * 活动选择问题 - 贪心实现
     * 贪心策略：每次选择结束时间最早的活动，预留更多时间给后续活动
     */
    public static List<int[]> activitySelect(int[][] activities) {
        List<int[]> result = new ArrayList<>();
        if (activities == null || activities.length == 0) {
            return result;
        }

        // 步骤1：制定贪心策略 -> 按活动结束时间升序排序
        Arrays.sort(activities, Comparator.comparingInt(a -> a[1]));

        // 步骤2：迭代选择第一个活动（结束最早）
        result.add(activities[0]);
        int lastEndTime = activities[0][1];

        // 步骤3：遍历剩余活动，选择不重叠的局部最优解
        for (int i = 1; i < activities.length; i++) {
            int currentStart = activities[i][0];
            // 校验：当前活动开始时间 >= 上一个选中活动的结束时间
            if (currentStart >= lastEndTime) {
                result.add(activities[i]);
                lastEndTime = activities[i][1];
            }
        }

        // 步骤4：返回最终解
        return result;
    }

    /**
     * 零钱兑换问题 - 贪心实现
     * 贪心策略：每次选择当前最大面额硬币，减少总硬币数
     * 适用场景：面额满足倍数关系（如人民币）
     */
    public static int coinChange(int[] coins, int target) {
        if (coins == null || coins.length == 0 || target < 0) {
            return -1;
        }

        int count = 0;
        int remaining = target;

        // 步骤1：制定贪心策略 -> 按面额降序遍历
        for (int coin : coins) {
            // 步骤2：迭代选择当前最大面额硬币（局部最优）
            while (remaining >= coin) {
                remaining -= coin;
                count++;
            }
            // 步骤3：终止条件：金额凑完则退出
            if (remaining == 0) {
                break;
            }
        }

        // 无法凑出金额返回-1
        return remaining == 0 ? count : -1;
    }
}
