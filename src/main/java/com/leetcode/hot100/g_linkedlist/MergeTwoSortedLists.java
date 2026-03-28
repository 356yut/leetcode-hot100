package com.leetcode.hot100.g_linkedlist;
/*将两个升序链表合并为一个新的升序链表并返回，新链表通过拼接给定两个链表的所有节点组成
示例1：输入l1 = [1,2,4]，l2 = [1,3,4]，输出[1,1,2,3,4,4]
示例2：输入l1 = []，l2 = []，输出[]
示例3：输入l1 = []，l2 = [0]，输出[0]
提示：两个链表的节点数目范围是 [0, 50]，-100 <= Node.val <= 100，l1和l2均按非递减顺序排列*/
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode result = mergeTwoLists(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode tail=new ListNode(0);
        ListNode newHead=tail;
        while (true) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
                tail = tail.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
                tail = tail.next;
            }
            if(list1==null){
                tail.next=list2;
                break;
            }
            if(list2==null){
                tail.next=list1;
                break;
            }
        }
        return newHead.next;
    }
}
