package com.leetcode.hot100.o_dynamicprogram;

import java.util.Arrays;

class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        for (int coin : coins) {
            if(coin>amount) continue;
            dp[coin]=1;
            for (int i = coin+1; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(new int[]{1, 2, 5}, 8));
    }
}
