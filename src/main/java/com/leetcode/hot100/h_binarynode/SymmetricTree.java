package com.leetcode.hot100.h_binarynode;

class SymmetricTree {
    public static void main(String[] args) {

    }
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left,root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        int leftVal = left==null?101:left.val;
        int rightVal = right==null?101:right.val;
        if (leftVal != rightVal) return false;
        return isMirror(left.right,right.left) && isMirror(left.left,right.right);
    }
}
