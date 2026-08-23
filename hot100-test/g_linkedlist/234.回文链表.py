#
# @lc app=leetcode.cn id=234 lang=python3
#
# [234] 回文链表
#
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
# @lc code=start
from typing import Optional
class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        if head==None or head.next==None:
            return True
        slow=head
        fast=head
        flag=0
        while(True):
            if fast==None:
                fast=head
                break
            elif fast.next==None:
                fast=head
                slow=slow.next
                flag=1
                break
            slow=slow.next
            fast=fast.next.next
        pre=None
        cur=fast
        while(cur!=slow):
            next=cur.next
            cur.next=pre
            pre=cur
            cur=next
        if flag:
            pre=pre.next
        while(pre):
            if pre.val!=slow.val:
                return False
            pre=pre.next
            slow=slow.next
        return True
# @lc code=end
s=Solution()
a=ListNode(1,ListNode(1))
print(s.isPalindrome(a))
