#
# @lc app=leetcode.cn id=41 lang=python3
#
# [41] 缺失的第一个正数
# 根据抽屉原理，最小的一定小于等于n+1
# 所以小于等于0和大于n的都直接设置为0，然后其余的num[i]放在对应大小的位置
# 注意是数字相同不转换，不是位置

# @lc code=start
from typing import List
class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        n=len(nums)
        for i in range(n):
            while(nums[i]>=1 and nums[i]<=n):
                to=nums[i]-1
                if nums[to]==nums[i]:break
                temp=nums[to]
                nums[to]=nums[i]
                nums[i]=temp
                
        for i in range(n):
            if nums[i]!=i+1:
                return i+1
        return n+1
                    
                
# @lc code=end

