#
# @lc app=leetcode.cn id=101 lang=python3
# 左右子树对称
# [101] 对称二叉树
#
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
# @lc code=start
from typing import Optional
class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        if root.left==None and root.right==None:
            return True
        return self.isSame(root.left,root.right)

    def isSame(self,left: Optional[TreeNode],right: Optional[TreeNode])-> bool:
        if left==None and right==None:
            return True
        if left and right and left.val==right.val:
            return self.isSame(left.left,right.right) and self.isSame(left.right,right.left)
        return False
# @lc code=end

