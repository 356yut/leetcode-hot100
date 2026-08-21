#
# @lc app=leetcode.cn id=56 lang=python3
#
# [56] 合并区间
# 注意数组排序

# @lc code=start
from typing import List
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        res=[]
        new_arr=sorted(intervals,key=lambda x:x[0])
        i=0
        while i<len(new_arr):
            start=new_arr[i][0]
            end=new_arr[i][1]
            while i+1<len(new_arr) and new_arr[i+1][0]<=end:
                i+=1
                end=max(new_arr[i][1],end)
            res.append([start,end])
            i+=1
        return res
# @lc code=end

