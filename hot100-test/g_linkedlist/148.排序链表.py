#
# @lc app=leetcode.cn id=148 lang=python3
#
# [148] 排序链表
# 递归使用排序
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
from typing import Optional
# @lc code=start
class Solution:
    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        # 归并排序
        if head==None or head.next==None:
            return head
        slow=head
        fast=head
        while(fast.next and fast.next.next):
            slow=slow.next
            fast=fast.next.next
        slowNext=slow.next
        slow.next=None
        
        left=self.sortList(head)
        right=self.sortList(slowNext)
        
        return self.merge(left,right)
    
    def merge(self,left,right):
        dummy=ListNode()
        cur=dummy
        while(left and right):
            while(left and right and left.val<=right.val):
                cur.next=left
                cur=cur.next
                left=left.next
            while(left and right and left.val>=right.val):
                cur.next=right
                cur=cur.next
                right=right.next
        if left:
            cur.next=left
        else:
            cur.next=right
        return dummy.next
        
                    
        
        
# @lc code=end

