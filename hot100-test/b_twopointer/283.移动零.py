#
# @lc app=leetcode.cn id=283 lang=python3
#
# [283] 移动零
#

# @lc code=start
from typing import List
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        zeroCount=0
        for i in range(0,len(nums)):
            if nums[i]==0:
                zeroCount+=1
            else:
                nums[i-zeroCount]=nums[i]
        for i in range(len(nums)-zeroCount,len(nums)):
            nums[i]=0
# @lc code=end

