#
# @lc app=leetcode.cn id=189 lang=python3
#
# [189] 轮转数组
#

# @lc code=start
class Solution:
    def rotate(self, nums: list[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        # reverse(nums,0,len(nums))
        # reverse(nums,0,k)
        # reverse(nums,k,len(nums))
        while k>=len(nums):
            k=k-len(nums)
        if k==0:
            return
        nums.reverse()
        nums[:k]=reversed(nums[:k])
        nums[k:]=reversed(nums[k:])
        
        
# @lc code=end

