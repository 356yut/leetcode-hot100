#
# @lc app=leetcode.cn id=104 lang=python3
#
# [104] 二叉树的最大深度
# 取左右较大深度+1
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
from typing import Optional
# @lc code=start

class Solution:
    def maxDepth(self, root: Optional[TreeNode]) -> int:
        if root==None:
            return 0
        leftDepth=self.maxDepth(root.left)
        rightDepth=self.maxDepth(root.right)
        return max(leftDepth,rightDepth)+1
# @lc code=end

