#
# @lc app=leetcode.cn id=23 lang=python3
#
# [23] 合并 K 个升序链表
# 每两个合并一次
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
from typing import List,Optional
# @lc code=start

class Solution:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        if not lists:return None
        n=len(lists)
        if n==1: return lists[0]
        mid=n//2
        left=self.mergeKLists(lists[:mid])
        right=self.mergeKLists(lists[mid:])
        
        dummy=ListNode()
        cur=dummy
        while(left and right):
            if left.val<=right.val:
                cur.next=left
                left=left.next
            else:
                cur.next=right
                right=right.next
            cur=cur.next
        if left:
            cur.next=left
        else: cur.next=right
        return dummy.next
# @lc code=end

class Solution2:
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        n=len(lists)
        if n==0: return
        cur=None
        for i in range(n):
            if not lists[i]:
                continue
            cur=self.merge(cur,lists[i])
        return cur
    def merge(self,left,right):
        dummy=ListNode()
        cur=dummy
        while(left and right):
            while(left and right and left.val<=right.val):
                cur.next=left
                cur=cur.next
                left=left.next
            while(left and right and right.val<=left.val):
                cur.next=right
                cur=cur.next
                right=right.next
        if left:
            cur.next=left
        else:
            cur.next=right
        return dummy.next