package com.leetcode.hot100.h_binarynode;

class KthSmallestElementInABst {
    int res=0;
    int count=0;
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root,k);
        return res;
    }
    public void inorderTraversal(TreeNode root,int k) {
        if(root==null) return;
        inorderTraversal(root.left,k);
        count++;
        if(count==k){
            res=root.val;
            return;
        }
        inorderTraversal(root.right,k);
    }
}


