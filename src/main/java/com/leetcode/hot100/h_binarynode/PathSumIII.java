package com.leetcode.hot100.h_binarynode;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        // 哈希表：key=前缀和，value=该前缀和出现的次数
        Map<Long, Integer> prefixMap = new HashMap<>();
        // 初始化前缀和0出现1次，处理从根节点开始的路径
        prefixMap.put(0L, 1);
        return dfs(root, 0, targetSum, prefixMap);
    }

    private int dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixMap) {
        // 递归终止条件：节点为空
        if (node == null) {
            return 0;
        }
        // 更新当前节点的前缀和
        currentSum += node.val;
        // 获取以当前节点为结尾的合法路径数量
        int res = prefixMap.getOrDefault(currentSum - targetSum, 0);
        // 将当前前缀和存入哈希表，计数+1
        prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);
        // 递归遍历左子树并累加路径数
        res += dfs(node.left, currentSum, targetSum, prefixMap);
        // 递归遍历右子树并累加路径数
        res += dfs(node.right, currentSum, targetSum, prefixMap);
        // 回溯操作：移除当前前缀和，不影响其他分支计算
        prefixMap.put(currentSum, prefixMap.get(currentSum) - 1);
        return res;
    }
}
