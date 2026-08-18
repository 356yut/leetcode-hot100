#
# @lc app=leetcode.cn id=128 lang=python3
#
# [128] 最长连续序列
# 把输入转换为set，然后依次进行以下操作：
# 如果curNum-1不在dp中，说明是起始，开始操作，确认下一个在不在，在就curLen+1


# @lc code=start
from typing import List
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        dp=set(nums)
        maxLength=0;
        for num in dp:
            if not (num-1) in dp:
                curNum=num
                curLen=1
                while((curNum+1) in dp):
                    curNum+=1
                    curLen+=1
                maxLength=max(maxLength,curLen)
        return maxLength
# @lc code=end

