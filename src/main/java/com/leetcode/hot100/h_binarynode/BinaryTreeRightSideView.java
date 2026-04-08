package com.leetcode.hot100.h_binarynode;

import java.util.*;

class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        //新建队列
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int count=1;
        while(count>0) {
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                if (i==count-1) res.add(node.val);
            }
            count=queue.size();
        }
        return res;
    }
    public static void main(String[] args) {
        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();

        // 测试用例1：空树
        TreeNode root1 = null;
        System.out.println("测试用例1（空树）：" + solution.rightSideView(root1)); // 预期：[]

        // 测试用例2：单节点树 [1]
        TreeNode root2 = new TreeNode(1);
        System.out.println("测试用例2（单节点）：" + solution.rightSideView(root2)); // 预期：[1]

        // 测试用例3：标准二叉树 [1,2,3,null,5,null,4]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.right = new TreeNode(5);
        root3.right.right = new TreeNode(4);
        System.out.println("测试用例3（标准二叉树）：" + solution.rightSideView(root3)); // 预期：[1,3,4]

        // 测试用例4：左斜树 [1,2,null,3,null]
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        System.out.println("测试用例4（左斜树）：" + solution.rightSideView(root4)); // 预期：[1,2,3]
    }
}
