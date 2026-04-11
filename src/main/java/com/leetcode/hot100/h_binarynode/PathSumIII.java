package com.leetcode.hot100.h_binarynode;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long,Integer> prefixMap = new HashMap<>();
        prefixMap.put(0L,1);
        return dfs(root,0L,targetSum,prefixMap);
    }

    private int dfs(TreeNode root,Long curSum, int targetSum, Map<Long, Integer> prefixMap) {
        if (root == null) return 0;
        curSum += root.val;
        int res=prefixMap.getOrDefault(curSum-targetSum,0);
        prefixMap.put(curSum,prefixMap.getOrDefault(curSum,0) + 1);
        res+=dfs(root.left,curSum,targetSum,prefixMap);
        res+=dfs(root.right,curSum,targetSum,prefixMap);
        prefixMap.put(curSum,prefixMap.get(curSum)-1);
        return res;
    }


}
