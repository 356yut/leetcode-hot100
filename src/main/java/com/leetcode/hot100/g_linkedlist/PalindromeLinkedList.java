package com.leetcode.hot100.g_linkedlist;
/*给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
示例 1：输入：head = [1,2,2,1]，输出：true
示例 2：输入：head = [1,2]，输出：false
提示：链表中节点数目在范围[1, 10^5] 内，0 <= Node.val <= 9
进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？*/

public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(head));

    }
    public static boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightPre=null;

        ListNode rightCurr=slow.next;
        slow.next=null;

        while(rightCurr!=null){
            ListNode rightNext=rightCurr.next;
            rightCurr.next=rightPre;
            rightPre=rightCurr;
            rightCurr=rightNext;
        }
        while(rightPre!=null){
            System.out.print(head.val + " ");
            System.out.println(rightPre.val);
            if(head.val != rightPre.val){
                return false;
            }
            head=head.next;
            rightPre=rightPre.next;
        }
        return true;
    }
    public static boolean isPalindrome2(ListNode head) {
        // 1. 空链表 / 单节点 直接返回 true
        if (head == null || head.next == null) {
            return true;
        }

        // 2. 快慢指针找中点（slow 最终指向链表前半段最后一个节点）
        ListNode slow = head;
        ListNode fast = head;  // ✅ 修复：fast 必须从 head 开始
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 3. 拿到后半段起点
        ListNode rightHead = slow.next;

        // 4. 反转后半段链表 ✅ 核心修复
        ListNode pre = null;
        ListNode curr = rightHead;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        ListNode reversedRight = pre;  // 反转后的后半段头节点

        // 5. 前半段 和 反转后的后半段 逐一对比
        ListNode left = head;
        while (reversedRight != null) {  // ✅ 修复：用后半段长度做循环条件
            if (left.val != reversedRight.val) {
                return false;
            }
            left = left.next;
            reversedRight = reversedRight.next;
        }

        return true;
    }
}
