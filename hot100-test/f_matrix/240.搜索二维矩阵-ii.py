#
# @lc app=leetcode.cn id=240 lang=python3
#
# [240] 搜索二维矩阵 II
#

# @lc code=start
from typing import List
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        m=len(matrix)
        n=len(matrix[0])
        i=0
        j=n-1
        while(True):
            cur=matrix[i][j]
            if cur==target:
                return True
            elif cur>target:
                j-=1
                if j<0:
                    return False
            else:
                i+=1
                if i>m-1:
                    return False
                
# @lc code=end

