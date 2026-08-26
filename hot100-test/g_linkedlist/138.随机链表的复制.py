#
# @lc app=leetcode.cn id=138 lang=python3
#
# [138] 随机链表的复制
# 注意使用字典，然后用get获取元素
# Definition for a Node.
class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random
from typing import Optional
# @lc code=start


class Solution:
    def copyRandomList(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if not head: return None
        mapping={}
        cur=head
        while(cur):
            mapping[cur]=Node(cur.val)
            cur=cur.next
        cur=head
        while(cur):
            mapping[cur].next=mapping.get(cur.next)
            mapping[cur].random=mapping.get(cur.random)
            cur=cur.next
        return mapping[head]
# @lc code=end

