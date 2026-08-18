#
# @lc app=leetcode.cn id=15 lang=python3
#
# [15] 三数之和
# 第一个数为基准，其他的两个选择控制大小，注意移动左右指针

# @lc code=start
class Solution:
    def threeSum(self, nums: list[int]) -> list[list[int]]:
        length=len(nums)
        if length<3:
            return list()
        nums=sorted(nums)
        res=[]
        for i in range(0,length-2):
            if i > 0 and nums[i] == nums[i - 1]:
                continue
            left=i+1
            right=length-1
            while(left<right):
                curSum=nums[i]+nums[left]+nums[right]
                if curSum==0:
                    res.append([nums[i],nums[left],nums[right]])
                    left+=1
                    right-=1
                    while left < right and nums[left] == nums[left - 1]:
                        left += 1
                    while left < right and nums[right] == nums[right + 1]:
                        right -= 1
                elif curSum<0:
                    left+=1
                else:
                    right-=1
        return res
                    
# @lc code=end

