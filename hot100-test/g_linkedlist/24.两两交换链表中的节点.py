#
# @lc app=leetcode.cn id=24 lang=python3
#
# [24] 两两交换链表中的节点
#
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
# @lc code=start
from typing import Optional
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head==None or head.next==None:
            return head
        pre=ListNode(0,head)
        newHead=pre
        l1=head
        while(l1!=None and l1.next!=None):
            l2=l1.next
            next=l2.next
            pre.next=l2
            l2.next=l1
            l1.next=next
            pre=l1
            l1=next
        return newHead.next
# @lc code=end

