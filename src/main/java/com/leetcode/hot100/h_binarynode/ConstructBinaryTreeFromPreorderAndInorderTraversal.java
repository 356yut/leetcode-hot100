package com.leetcode.hot100.h_binarynode;

import java.util.Arrays;
import java.util.HashMap;

class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    HashMap<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight) {
            return null;
        }
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int inRoot = indexMap.get(rootVal);
        int leftSize = inRoot - inLeft;
        root.left = build(preorder, inorder, preLeft + 1, preLeft + leftSize, inLeft, inRoot - 1);
        root.right = build(preorder, inorder, preLeft + leftSize + 1, preRight, inRoot + 1, inRight);
        return root;
    }
}
