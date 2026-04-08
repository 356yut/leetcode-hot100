package com.leetcode.hot100.h_binarynode;

import java.util.ArrayList;
import java.util.List;

class FlattenBinaryTreeToLinkedList {
    List<TreeNode> list = new ArrayList<>();
    public void flatten2(TreeNode root) {
        inorderTraversal(root);
        list.add(null);
        for (int i = 0; i < list.size()-1; i++) {
            TreeNode node = list.get(i);
            node.left = null;
            node.right = list.get(i + 1);
        }
    }
    public void inorderTraversal(TreeNode root) {
        if (root == null) return;
        list.add(root);
        inorderTraversal(root.left);
        inorderTraversal(root.right);
    }
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList solution = new FlattenBinaryTreeToLinkedList();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        solution.flatten(root);
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
}
