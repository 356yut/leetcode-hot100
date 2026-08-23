#
# @lc app=leetcode.cn id=54 lang=python3
#
# [54] 螺旋矩阵
# 设置top bottom right left 每次修改记得判断有没有left>right bottom>top
# @lc code=start
from typing import List
class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        m=len(matrix)
        n=len(matrix[0])
        left=0
        right=n-1
        bottom=0
        top=m-1
        res=[]
        while(True):
            for j in range(left,right+1):
                res.append(matrix[bottom][j])
            bottom+=1
            if bottom>top: break
            
            for i in range(bottom,top+1):
                print(i)
                res.append(matrix[i][right])
            right-=1
            if left>right:break
            
            for j in range(right,left-1,-1):
                res.append(matrix[top][j])
            top-=1
            if bottom>top: break
            
            for i in range(top,bottom-1,-1):
                res.append(matrix[i][left])
            left+=1
            if left>right:break
        return res
# @lc code=end

s=Solution()
print(s.spiralOrder([[1,2,3,4],[5,6,7,8],[9,10,11,12]]))