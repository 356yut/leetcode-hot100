#
# @lc app=leetcode.cn id=53 lang=python3
#
# [53] 最大子数组和
# preSum是截止当前的前缀和，小于0舍弃，大于0累加

# @lc code=start
from typing import List
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        preSum=nums[0]
        res=nums[0]
        for i in range(1,len(nums)):
            if preSum<0:
                preSum=0
            preSum+=nums[i]
            res=max(res,preSum)
        return res
            
# @lc code=end

