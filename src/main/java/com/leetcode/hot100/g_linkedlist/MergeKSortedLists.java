package com.leetcode.hot100.g_linkedlist;

public class MergeKSortedLists {
    public static void main(String[] args) {
        // 2. 创建测试用的【有序链表】（3个测试链表）
        // 链表1：1 -> 4 -> 5
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);

        // 链表2：1 -> 3 -> 4
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        // 链表3：2 -> 6
        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);

        // 3. 将所有链表放入数组，作为方法入参
        ListNode[] lists = {list1, list2, list3};

        // 4. 调用合并算法，得到最终合并后的链表
        ListNode result = mergeKLists(lists);

        // 5. 打印结果（辅助方法）
        System.out.print("合并后的有序链表：");
        printLinkedList(result);
    }

    // 辅助方法：打印链表所有节点值（方便查看结果）
    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        // 遍历链表，直到节点为null
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        // 边界条件处理
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return MergeTwoSortedLists(l1,l2);
    }
    private static ListNode MergeTwoSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
