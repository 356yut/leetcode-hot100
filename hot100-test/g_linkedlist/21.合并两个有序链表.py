#
# @lc app=leetcode.cn id=21 lang=python3
#
# [21] 合并两个有序链表
#
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# @lc code=start
from typing import Optional
class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        if list1==None:
            return list2
        elif list2==None:
            return list1
        cur=ListNode()
        head=cur
        while(list1 and list2):
            while(list1 and list2 and list1.val<=list2.val):
                cur.next=list1
                cur=cur.next
                list1=list1.next
            while(list1 and list2 and list2.val<=list1.val):
                cur.next=list2
                cur=cur.next
                list2=list2.next
        if list1!=None:
            cur.next=list1
        if list2!=None:
            cur.next=list2
        return head.next
            
# @lc code=end

