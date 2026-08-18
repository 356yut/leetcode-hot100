#
# @lc app=leetcode.cn id=1 lang=python
#
# [1] 两数之和
#

# @lc code=start
class Solution(object):
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        dp=dict()
        for i in range(0,len(nums)):
            if(nums[i] in dp):
                return [dp[nums[i]],i]
            dp[target-nums[i]]=i
            
        
# @lc code=end

