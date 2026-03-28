package com.leetcode.hot100.g_linkedlist;

/*给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
示例 1：输入：head = [1,2,3,4,5], n = 2 输出：[1,2,3,5]
示例 2：输入：head = [1], n = 1 输出：[]
示例 3：输入：head = [1,2], n = 1 输出：[1]
提示：链表中结点的数目为 sz，1 <= sz <= 30，0 <= Node.val <= 100，1 <= n <= sz
进阶：你能尝试使用一趟扫描实现吗？*/
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        System.out.println("===== 测试用例1：常规链表 1->2->3->4->5，删除倒数第2个节点 =====");
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.print("原链表：");
        printList(head1);
        ListNode newHead1 = removeNthFromEnd(head1, 2);
        System.out.print("删除后：");
        printList(newHead1);
        System.out.println();

        System.out.println("===== 测试用例2：单节点链表 [1]，删除倒数第1个节点 =====");
        ListNode head2 = new ListNode(1);
        System.out.print("原链表：");
        printList(head2);
        ListNode newHead2 = removeNthFromEnd(head2, 1);
        System.out.print("删除后：");
        printList(newHead2);
        System.out.println();

        System.out.println("===== 测试用例3：双节点链表 1->2，删除倒数第1个节点 =====");
        ListNode head3 = new ListNode(1, new ListNode(2));
        System.out.print("原链表：");
        printList(head3);
        ListNode newHead3 = removeNthFromEnd(head3, 1);
        System.out.print("删除后：");
        printList(newHead3);
        System.out.println();

        System.out.println("===== 测试用例4：双节点链表 1->2，删除倒数第2个节点 =====");
        ListNode head4 = new ListNode(1, new ListNode(2));
        System.out.print("原链表：");
        printList(head4);
        ListNode newHead4 = removeNthFromEnd(head4, 2);
        System.out.print("删除后：");
        printList(newHead4);
        System.out.println();
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prev = new ListNode(0);
        prev.next = head;
        ListNode curr = head;
        for (int i = 1; i < n; i++) {
            curr = curr.next;
        }
        while (curr.next != null) {
            prev = prev.next;
            curr = curr.next;
        }
        curr = prev.next;
        prev.next = curr.next;
        curr.next = null;
        if(curr == head) {return prev.next;}
        return head;
    }
}
