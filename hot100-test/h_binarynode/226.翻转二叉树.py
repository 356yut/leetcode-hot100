#
# @lc app=leetcode.cn id=226 lang=python3
#
# [226] 翻转二叉树
# 把左右调换，递归
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
from typing import Optional
# @lc code=start

class Solution:
    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if root==None:
            return
        root.right,root.left=root.left,root.right
        self.invertTree(root.right)
        self.invertTree(root.left)
        return root
# @lc code=end

