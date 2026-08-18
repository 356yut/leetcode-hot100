#
# @lc app=leetcode.cn id=11 lang=python3
#
# [11] 盛最多水的容器
#

# @lc code=start
from typing import List
class Solution:
    def maxArea(self, height: List[int]) -> int:
        left=0
        right=len(height)-1
        res=0
        while(left<right):
            if(height[left]>height[right]):
                res=max(res,(right-left)*height[right])
                right-=1
            else:
                res=max(res,(right-left)*height[left])
                left+=1
        return res
# @lc code=end

