package com.leetcode.hot100.g_linkedlist;

import java.util.List;

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
//示例 1：输入：head = [1,2,3,4,5]，输出：[5,4,3,2,1]
//示例 2：输入：head = [1,2]，输出：[2,1]
//示例 3：输入：head = []，输出：[]
//提示：链表中节点的数目范围是 [0, 5000]，-5000 <= Node.val <= 5000
//进阶：链表可以选用迭代或递归方式完成反转，需要用两种方法解决该题
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode headnew=reverseList1(head);
        while(headnew!=null){
            System.out.println(headnew.val);
            headnew=headnew.next;
        }

    }
    // 迭代
    public static ListNode reverseList0(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }

    // 递归
    public static ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        return reverseList2(head, prev);

    }
    public static ListNode reverseList2(ListNode curr,ListNode prev) {
        if (curr == null) return prev;
        ListNode next = curr.next;
        curr.next = prev;
        return reverseList2(next,curr);
    }
}
