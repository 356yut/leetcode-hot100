#
# @lc app=leetcode.cn id=543 lang=python3
# 左右取高度，相加更新最大值
# [543] 二叉树的直径
from typing import Optional
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
# @lc code=start

class Solution:
    res=0
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        if root==None:
            return 0
        self.getMaxDepth(root)
        return self.res
    def getMaxDepth(self,root: Optional[TreeNode])-> int:
        if root==None:
            return 0
        leftDepth=self.getMaxDepth(root.left)
        rightDepth=self.getMaxDepth(root.right)
        self.res=max(self.res,leftDepth+rightDepth)
        return max(leftDepth,rightDepth)+1
# @lc code=end

