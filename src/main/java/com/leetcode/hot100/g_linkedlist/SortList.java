package com.leetcode.hot100.g_linkedlist;

public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(0);
        head.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next = new ListNode(8);
        ListNode result = sortList(head);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }

    }
    public static ListNode sortList1(ListNode head) {

        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current=current.next;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev;
        ListNode head1,head2,next;
        for (int i = 1; i < length; i<<=1) {
//            ReverseNodesInKGroup.printList(head);
            prev=dummy;
            current = prev.next;
            while (current != null) {
                head1 = current;
                for(int j=1;j<i&&current.next!=null;j++) {
                    current=current.next;
                }
                head2 = current.next;
                current.next=null;
                current=head2;
                for(int j=1;j<i&&current!=null&&current.next!=null;j++) {
                    current=current.next;
                }
                next=null;
                if(current!=null) {
                    next=current.next;
                    current.next=null;
                }
                prev.next=mergeSortList(head1,head2);
                while (prev.next!=null) {
                    prev=prev.next;
                }
                current=next;
            }

        }
        return dummy.next;

    }
    public static ListNode sortList(ListNode head){
        if(head==null||head.next==null) {
            return head;
        }
        ListNode slow=head,fast=head;
        while(fast.next!=null&&fast.next.next!=null) {
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode slowNext=slow.next;
        slow.next=null;
        ListNode left=sortList(head);
        ListNode right=sortList(slowNext);
        return mergeSortList(left,right);
    }
    public static ListNode mergeSortList(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                curr.next = head1;
                head1 = head1.next;
            }else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        curr.next = head1 != null ? head1 : head2;
        return dummy.next;
    }
}
