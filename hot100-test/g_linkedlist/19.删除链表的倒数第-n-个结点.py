#
# @lc app=leetcode.cn id=19 lang=python3
#
# [19] 删除链表的倒数第 N 个结点
# 注意重新设置newHead，因为head可能会被删除
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
# @lc code=start
from typing import Optional
class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        pre=ListNode()
        pre.next=head
        tail=head
        newHead=pre
        i=0
        while(i<n-1):
            tail=tail.next
            i+=1
        while(tail.next!=None):
            pre=pre.next
            tail=tail.next
        cur=pre.next
        pre.next=cur.next
        cur.next=None
        return newHead.next
        
# @lc code=end
s=Solution()
print(s.removeNthFromEnd(ListNode(1,None),1))
