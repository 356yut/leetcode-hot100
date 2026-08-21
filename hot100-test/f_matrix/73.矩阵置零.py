#
# @lc app=leetcode.cn id=73 lang=python3
#
# [73] 矩阵置零
# m+n的数组记录行列为0
# 进阶版使用第1行和第1列记录（注意遍历的时候跳过第一行和第一列）

# @lc code=start
from typing import List


class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        if not matrix or not matrix[0]:
            return

        m = len(matrix)       # 行数
        n = len(matrix[0])    # 列数

        first_row_zero = any(matrix[0][j] == 0 for j in range(n))
        first_col_zero = any(matrix[i][0] == 0 for i in range(m))

        # 使用第一行和第一列记录需要置零的行、列
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[i][0] = 0
                    matrix[0][j] = 0

        # 根据标记置零
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][0] == 0 or matrix[0][j] == 0:
                    matrix[i][j] = 0

        # 处理第一行
        if first_row_zero:
            for j in range(n):
                matrix[0][j] = 0

        # 处理第一列
        if first_col_zero:
            for i in range(m):
                matrix[i][0] = 0
        
        
# @lc code=end

class Solution:
    def setZeroes2(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        m=len(matrix)
        n=len(matrix[0])
        zero_idx=[0]*(m+n)
        for i in range(m):
            for j in range(n):
                if not matrix[i][j]:
                    zero_idx[i]=1
                    zero_idx[m+j]=1
        for i in range(m):
            for j in range(n):
                if zero_idx[i] or zero_idx[m+j]:
                    matrix[i][j]=0

