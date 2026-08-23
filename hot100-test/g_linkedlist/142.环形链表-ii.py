#
# @lc app=leetcode.cn id=142 lang=python3
#
# [142] 环形链表 II
# fast先两步走再一步走
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
# @lc code=start
from typing import Optional
class Solution:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head==None or head.next==None:
            return None
        slow=head.next
        fast=head.next.next
        while(fast!=slow):
            if fast==None or fast.next==None:
                return None
            slow=slow.next
            fast=fast.next.next
        slow=head
        while(fast!=slow):
            slow=slow.next
            fast=fast.next
        return fast
# @lc code=end

