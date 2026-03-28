package com.leetcode.hot100.g_linkedlist;

public class LinkedListCycleII {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode head1 = new ListNode(2);
        ListNode head2 = new ListNode(0);
        ListNode head3 = new ListNode(-4);
        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head2;
        ListNode res = detectCycle(head);
        if (res != null) {
            System.out.println(res.val);
        }else {
            System.out.println("null");
        }
    }
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
