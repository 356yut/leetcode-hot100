#
# @lc app=leetcode.cn id=49 lang=python3
#
# [49] 字母异位词分组
# 创建一个key不存在时为空列表的字典
# 每个key是str排序后的list，value是相同key的str，最后输出mp的values()

# @lc code=start
import collections
from typing import List
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        mp=collections.defaultdict(list)  # key 不存在时自动创建空列表，方便按排序后的字符串分组
        for str in strs:
            key="".join(sorted(str))
            mp[key].append(str)
        return list(mp.values())    
        
# @lc code=end

