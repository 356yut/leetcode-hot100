#
# @lc app=leetcode.cn id=141 lang=python3
#
# [141] 环形链表
#
# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
# @lc code=start
from typing import Optional
class Solution:
    def hasCycle(self, head: Optional[ListNode]) -> bool:
        if head==None or head.next==None:
            return False
        slow,fast=head,head
        slow=slow.next
        fast=fast.next.next
        while(slow!=fast):
            if fast==None or fast.next==None:
                return False
            slow=slow.next
            fast=fast.next.next
        return True
# @lc code=end
s=Solution()
a = ListNode(1)
a.next = ListNode(2)
print(s.hasCycle(a))  # False


