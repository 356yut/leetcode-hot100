package com.leetcode.hot100.g_linkedlist;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        // 构建测试链表 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 测试示例1：k=2
        System.out.print("原链表：");
        printList(head);
        int k1 = 2;
        ListNode result1 = reverseKGroup(head, k1);
        System.out.print("k=2翻转后链表：");
        printList(result1);

        // 重新构建链表，测试示例2：k=3
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        int k2 = 3;
        ListNode result2 = reverseKGroup(head2, k2);
        System.out.print("k=3翻转后链表：");
        printList(result2);
    }

    // 打印链表的辅助方法（用于测试输出）
    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) return head;
            tail = tail.next;
        }
        ListNode newHead = reverse(head, k);
        head.next = reverseKGroup(tail, k);

        return newHead;

    }

    // 翻转前k个节点
    private static ListNode reverse(ListNode head, int k) {
        ListNode prev = null;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;

    }
}
