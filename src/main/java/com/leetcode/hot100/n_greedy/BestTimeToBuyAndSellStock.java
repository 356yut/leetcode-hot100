package com.leetcode.hot100.n_greedy;

class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int profit = Integer.MIN_VALUE;
        int curMin = prices[0];
        for (int price : prices) {
            curMin = Math.min(curMin, price);
            profit = Math.max(profit, price - curMin);
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {7,6,5,4,1};
        BestTimeToBuyAndSellStock stock = new BestTimeToBuyAndSellStock();
        System.out.println(stock.maxProfit(prices));
    }
}
