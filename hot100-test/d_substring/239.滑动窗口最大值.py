#
# @lc app=leetcode.cn id=239 lang=python3
#
# [239] 滑动窗口最大值
# 注意python中的队列是deque

# @lc code=start
from typing import List
import collections
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        n=len(nums)
        q=collections.deque()
        for i in range(k):
            while q and nums[q[-1]]<=nums[i]:
                q.pop()
            q.append(i)
            
        res=[nums[q[0]]]
        for i in range(k,n):
            while q and q[0]<=i-k:
                q.popleft()
            while q and nums[q[-1]]<=nums[i]:
                q.pop()
            q.append(i)
            res.append(nums[q[0]])
        return res
# @lc code=end

