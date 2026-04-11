package com.leetcode.hot100.h_binarynode;

class BinaryTreeMaximumPathSum {
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));

        maxSum = Math.max(maxSum,left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum obj = new BinaryTreeMaximumPathSum();
        obj.maxSum = Integer.MIN_VALUE;
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(obj.maxPathSum(root));
    }
}
