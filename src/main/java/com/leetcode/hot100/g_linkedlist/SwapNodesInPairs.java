package com.leetcode.hot100.g_linkedlist;
/*
1. 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
示例 1：输入：head = [1,2,3,4]，输出：[2,1,4,3]
示例 2：输入：head = []，输出：[]
示例 3：输入：head = [1]，输出：[1]
提示：链表中节点的数目在范围 [0, 100] 内，0 <= Node.val <= 100
*/

public class SwapNodesInPairs {
    // 打印链表的工具方法（用于输出结果）
    public static void printList(ListNode head) {
        ListNode cur = head;
        System.out.print("[");
        while (cur != null) {
            System.out.print(cur.val);
            if (cur.next != null) {
                System.out.print(",");
            }
            cur = cur.next;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        // 测试用例1：空链表
        System.out.println("测试用例1：空链表");
        ListNode head1 = null;
        System.out.print("原链表：");
        printList(head1);
        ListNode res1 = swapPairs(head1);
        System.out.print("交换后：");
        printList(res1);
        System.out.println("------------------------");

        // 测试用例2：单个节点
        System.out.println("测试用例2：单个节点 [1]");
        ListNode head2 = new ListNode(1);
        System.out.print("原链表：");
        printList(head2);
        ListNode res2 = swapPairs(head2);
        System.out.print("交换后：");
        printList(res2);
        System.out.println("------------------------");

        // 测试用例3：四个节点 [1,2,3,4]
        System.out.println("测试用例3：四个节点 [1,2,3,4]");
        ListNode head3 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.print("原链表：");
        printList(head3);
        ListNode res3 = swapPairs(head3);
        System.out.print("交换后：");
        printList(res3);
        System.out.println("------------------------");

        // 测试用例4：三个节点 [1,2,3]
        System.out.println("测试用例4：三个节点 [1,2,3]");
        ListNode head4 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.print("原链表：");
        printList(head4);
        ListNode res4 = swapPairs(head4);
        System.out.print("交换后：");
        printList(res4);
    }
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = new ListNode(0);
        ListNode node1, node2;
        prev.next = head;
        ListNode newHead = head.next;
        while (prev.next != null && prev.next.next != null) {
            node1 = prev.next;
            node2 = prev.next.next;
            prev.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            prev=node2.next;

        }
        return newHead;
    }
}
