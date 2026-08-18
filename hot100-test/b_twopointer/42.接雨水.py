#
# @lc app=leetcode.cn id=42 lang=python3
#
# [42] 接雨水
# 左右各求当前最大，然后得可存的水，左右两边结果取最小值

# @lc code=start
from typing import List
class Solution:
    def trap(self, height: List[int]) -> int:
        curMax=0
        res=0
        left=[0 for i in range(len(height))]
        right=[0 for i in range(len(height))]
        for i in range(0,len(height)):
            if curMax>height[i]:
                left[i]=curMax-height[i]
            curMax=max(curMax,height[i])
        curMax=0
        for i in range(len(height)-1,-1,-1):
            if curMax>height[i]:
                right[i]=curMax-height[i]
            curMax=max(curMax,height[i])
        for i in range(0,len(height)):
            res+=min(left[i],right[i])
        return res
# @lc code=end

