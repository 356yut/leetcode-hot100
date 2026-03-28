package com.leetcode.hot100.g_linkedlist;
/*题目描述：给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字0之外，这两个数都不会以0开头。输入示例：l1 = [2,4,3]，l2 = [5,6,4]，输出[7,0,8]，对应计算342 + 465 = 807；l1 = [0]，l2 = [0]，输出[0]；l1 = [9,9,9,9,9,9,9]，l2 = [9,9,9,9]，输出[8,9,9,9,0,0,0,1]。提示：每个链表中的节点数在范围[1, 100]内，0 <= Node.val <= 9，题目数据保证列表表示的数字不含前导零。*/
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);
        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0;
        int sum;
        while (l1 != null || l2 != null|| carry != 0) {
            sum = carry;
            sum+= l1 == null ? 0 : l1.val;
            sum+= l2 == null ? 0 : l2.val;
            ListNode newNode= new ListNode(sum%10);
            carry = sum/10;
            prev.next = newNode;
            prev = newNode;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return head.next;
    }
}
