#
# @lc app=leetcode.cn id=160 lang=python3
#
# [160] 相交链表
# x+z+y=y+z+x
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
# @lc code=start
# Definition for singly-linked list.

class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> Optional[ListNode]:
        curA=headA
        curB=headB
        flag=0
        while(curA!=curB):
            if curA.next:
                curA=curA.next
            else:
                if flag:return None
                curA=headB
                flag=1
            if curB.next:
                curB=curB.next
            else:
                curB=headA
        return curA
# @lc code=end

