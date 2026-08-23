#
# @lc app=leetcode.cn id=206 lang=python3
#
# [206] 反转链表
# 记得pre开始是None
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
# @lc code=start
# Definition for singly-linked list.
from typing import Optional
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head==None or head.next==None:
            return head
        pre=None
        cur=head
        while(cur!=None):
            next=cur.next
            cur.next=pre
            pre=cur
            cur=next
        return pre
        
# @lc code=end
s=Solution()
a=ListNode(1,ListNode(2,ListNode(3,ListNode(4,ListNode(5,None)))))
cur=s.reverseList(a)
while(cur):
    print(cur.val)
    cur=cur.next

