#
# @lc app=leetcode.cn id=94 lang=python3
#
# [94] 二叉树的中序遍历
# 设置一个全局变量
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

from typing import Optional,List
# @lc code=start

class Solution:
    res=[]
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        self.res=[]
        self.inorder(root)
        return self.res
    def inorder(self,root: Optional[TreeNode]) -> None:
        if root==None:
            return
        if root.left:
            self.inorder(root.left)
        self.res.append(root.val)
        if root.right:
            self.inorder(root.right)
        
# @lc code=end

