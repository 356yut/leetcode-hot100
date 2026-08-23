#
# @lc app=leetcode.cn id=48 lang=python3
#
# [48] 旋转图像
# 先转置，再把每一行reverse

# @lc code=start
from typing import List
class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        m=len(matrix)
        n=len(matrix[0])
        ## 转置数组
        for i in range(m):
            for j in range(i+1,n):
                matrix[i][j],matrix[j][i]=matrix[j][i],matrix[i][j]
        
        ## 翻转每行
        for i in range(m):
            matrix[i].reverse()
            
# @lc code=end

