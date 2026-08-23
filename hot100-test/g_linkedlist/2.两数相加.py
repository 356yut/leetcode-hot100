#
# @lc app=leetcode.cn id=2 lang=python3
#
# [2] 两数相加
# 设置一个进位和一个本位
# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
# @lc code=start
from typing import Optional
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        pre=ListNode()
        l3=pre
        a=0
        b=0
        while(l1!=None or l2!=None or b!=0):
            a=b
            if l1:
                a+=l1.val
                l1=l1.next
            if l2:
                a+=l2.val
                l2=l2.next
            b=a//10
            pre.next=ListNode(a%10,None)
            pre=pre.next
        return l3.next

# @lc code=end

s=Solution()
a=[2,4,9]
b=[5,6,4,9]

def build_list(values):
    dummy=ListNode()
    cur=dummy
    for value in values:
        cur.next=ListNode(value)
        cur=cur.next
    return dummy.next

def to_list(l):
    values=[]
    while l:
        values.append(l.val)
        l=l.next
    return values

l1=build_list(a)
l2=build_list(b)
result=s.addTwoNumbers(l1,l2)
print(to_list(result))
