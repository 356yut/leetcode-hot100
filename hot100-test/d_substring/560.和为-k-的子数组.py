#
# @lc app=leetcode.cn id=560 lang=python3
#
# [560] 和为 K 的子数组
# 使用前缀+hash列表
# preSum[left]=nums[0]+nums[1]+...+nums[left]
# nums[left+1]+...+nums[right]=k
# k=preSum[right]-preSum[left]
# preSum[right]-k=preSum[left]
# 注意先查再更新

# @lc code=start
from typing import List
import collections
class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        res=0
        preSums={0:1}
        curSum=0
        for num in nums:
            curSum+=num
            res+=preSums.get(curSum-k,0)
            preSums[curSum]=preSums.get(curSum,0)+1
        print(preSums.items())
        return res
        
        
# @lc code=end

s=Solution()
print(s.subarraySum([1],0))


