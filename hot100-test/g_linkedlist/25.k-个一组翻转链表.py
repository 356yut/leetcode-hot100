#
# @lc app=leetcode.cn id=25 lang=python3
#
# [25] K 个一组翻转链表
# 每k个翻转一下
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
# @lc code=start
from typing import Optional
class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        pre=ListNode(0,head)
        first=head
        last=head
        flag=0
        newHead=first
        while(last!=None):
            i=0
            while(last!=None and i<k-1):
                last=last.next
                i+=1
            if(i==k-1 and last!=None):
                first,last=self.reverse(pre,first,last)
                print(first.val,last.val)
            else:
                break
            if flag==0:
                newHead=first
                flag=1
            pre=last
            first=last.next
            last=last.next
        return newHead
            
    def reverse(self,pre,first,last):
        next=last.next
        pp=None
        cur=first
        while(cur!=next):
            nn=cur.next
            cur.next=pp
            pp=cur
            cur=nn
        pre.next=last
        first.next=next
        return last,first
            
            
# @lc code=end

