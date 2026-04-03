package com.leetcode.hot100.h_binarynode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树操作实现类
 * 包含二叉树所有常用操作的具体实现
 */
class BinaryTree {
    // 根节点
    TreeNode root;

    // 构造方法：初始化空二叉树
    public BinaryTree() {
        this.root = null;
    }

    // ==================== 1. 手动构建二叉树 ====================
    public void createTree() {
        // 手动构建示例二叉树
        // 结构：
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
    }

    // ==================== 2. 深度优先遍历（递归实现） ====================
    // 前序遍历：根节点 → 左子树 → 右子树
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历：左子树 → 根节点 → 右子树
    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    // 后序遍历：左子树 → 右子树 → 根节点
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    // ==================== 3. 广度优先遍历（层序遍历） ====================
    // 按层次从上到下、从左到右遍历节点，使用队列实现
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            // 左子节点入队
            if (current.left != null) {
                queue.offer(current.left);
            }
            // 右子节点入队
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    // ==================== 4. 计算二叉树最大深度 ====================
    public int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左、右子树深度，取最大值+1（当前节点）
        int leftDepth = getMaxDepth(node.left);
        int rightDepth = getMaxDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // ==================== 5. 统计总节点数 ====================
    public int getNodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左子树节点数 + 右子树节点数 + 1（当前节点）
        return getNodeCount(node.left) + getNodeCount(node.right) + 1;
    }

    // ==================== 6. 统计叶子节点数 ====================
    public int getLeafCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 叶子节点：无左右子节点
        if (node.left == null && node.right == null) {
            return 1;
        }
        return getLeafCount(node.left) + getLeafCount(node.right);
    }

    // ==================== 7. 查找指定值的节点 ====================
    public TreeNode searchNode(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        // 找到目标节点，直接返回
        if (node.val == target) {
            return node;
        }
        // 递归查找左子树
        TreeNode leftResult = searchNode(node.left, target);
        if (leftResult != null) {
            return leftResult;
        }
        // 递归查找右子树
        return searchNode(node.right, target);
    }

    // ==================== 8. 清空二叉树 ====================
    public void clearTree(TreeNode node) {
        if (node == null) {
            return;
        }
        // 递归清空左、右子树
        clearTree(node.left);
        clearTree(node.right);
        // 释放当前节点
        node.left = null;
        node.right = null;
        node = null;
    }
}

/**
 * 测试主类
 * 调用所有二叉树操作，验证功能
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树对象
        BinaryTree tree = new BinaryTree();
        // 手动构建二叉树
        tree.createTree();

        System.out.println("========== 二叉树常用操作测试结果 ==========");
        System.out.print("1. 前序遍历结果：");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.print("2. 中序遍历结果：");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.print("3. 后序遍历结果：");
        tree.postOrder(tree.root);
        System.out.println();

        System.out.print("4. 层序遍历结果：");
        tree.levelOrder(tree.root);
        System.out.println();

        System.out.println("5. 二叉树最大深度：" + tree.getMaxDepth(tree.root));
        System.out.println("6. 二叉树总节点数：" + tree.getNodeCount(tree.root));
        System.out.println("7. 二叉树叶子节点数：" + tree.getLeafCount(tree.root));

        // 查找值为4的节点
        TreeNode target = tree.searchNode(tree.root, 4);
        System.out.println("8. 查找节点（值=4）：" + (target != null ? "找到节点" : "未找到节点"));

        // 清空二叉树
        tree.clearTree(tree.root);
        System.out.println("9. 清空二叉树后，总节点数：" + tree.getNodeCount(tree.root));
    }
}