#
# @lc app=leetcode.cn id=3 lang=python3
#
# [3] 无重复字符的最长子串
# 设置字典就行

# @lc code=start
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        res=0;
        left=0;
        curStr={}
        for i,char in enumerate(s):
            if char in curStr and curStr[char]>=left:
                left=curStr[char]+1
            curStr[char]=i
            res=max(res,i-left+1)
        return res
# @lc code=end
