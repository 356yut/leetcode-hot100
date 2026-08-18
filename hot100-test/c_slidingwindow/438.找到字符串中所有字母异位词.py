#
# @lc app=leetcode.cn id=438 lang=python3
#
# [438] 找到字符串中所有字母异位词
#

# @lc code=start
from typing import List
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        l1=len(s)
        l2=len(p)
        if(l1<l2): return list()
        res=[]
        pCount=[0]*26
        sCount=[0]*26
        for ch in p:
            pCount[ord(ch)-97]+=1
        for ch in s[:l2]:
            sCount[ord(ch)-97]+=1
        if pCount==sCount:
            res.append(0)
        for i in range(l2,l1):
            sCount[ord(s[i])-97]+=1
            sCount[ord(s[i-l2])-97]-=1
            if pCount==sCount:
                res.append(i-l2+1)
        return res
            
# @lc code=end
s=Solution()
print(s.findAnagrams("cbaebabacd","abc"))

