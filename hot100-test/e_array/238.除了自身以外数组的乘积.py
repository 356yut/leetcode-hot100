#
# @lc app=leetcode.cn id=238 lang=python3
#
# [238] 除了自身以外数组的乘积
# 可以把ans本身当成pre，然后tmp当后面计算的

# @lc code=start
from typing import List
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        tmp=1
        n=len(nums)
        ans=[1]*n
        for i in range(1,n):
            ans[i]=ans[i-1]*nums[i-1]
        for i in range(n-2,-1,-1):
            tmp*=nums[i+1]
            ans[i]*=tmp
        return ans
            
# @lc code=end

