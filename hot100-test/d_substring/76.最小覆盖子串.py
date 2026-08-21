#
# @lc app=leetcode.cn id=76 lang=python3
#
# [76] 最小覆盖子串
# 左右指针移动，右边先覆盖，左边再收缩

# @lc code=start
class Solution:
    def minWindow(self, s: str, t: str) -> str:
        m=len(s)
        n=len(t)
        if m<n: return ""
        
        need=[0]*128
        window=[0]*128
        required=0
        formed=0
        left=0
        start=0
        length=float("inf")
        for char in t:
            idx=ord(char)
            if need[idx]==0:
                required+=1
            need[idx]+=1
        for right in range(m):
            idx=ord(s[right])
            window[idx]+=1
            if window[idx]==need[idx]:
                formed+=1
            if formed==required:
                while(True):
                    idx=ord(s[left])
                    if window[idx]==need[idx]:
                        curLength=right-left+1
                        if curLength<length:
                            start=left
                            length=curLength
                        window[idx]-=1
                        formed-=1
                        left+=1
                        break
                    window[idx]-=1
                    left+=1
        return "" if length==float("inf") else s[start:start+length]
                    
                
# @lc code=end


s ="ADOBECODEBANC"
t ="ABC"
so=Solution()
print(so.minWindow(s,t))

