package com.leetcode.hot100.g_linkedlist;
/*给你两个单链表的头节点 headA 和 headB，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null。题目数据保证整个链式结构中不存在环，函数返回结果后，链表必须保持其原始结构。
自定义评测：评测系统的输入为intersectVal（相交的起始节点的值，不存在相交节点则为0）、listA（第一个链表）、listB（第二个链表）、skipA（在listA中从头节点开始跳到交叉节点的节点数）、skipB（在listB中从头节点开始跳到交叉节点的节点数），评测系统将根据这些输入创建链式数据结构，并将两个头节点传递给程序，程序能正确返回相交节点则为正确答案。
示例1：输入intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3，输出Intersected at '8'
示例2：输入intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1，输出Intersected at '2'
示例3：输入intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2，输出No intersection
提示：listA中节点数目为m，listB中节点数目为n，1 <= m, n <= 3 * 104，1 <= Node.val <= 105，0 <= skipA <= m，0 <= skipB <= n；如果两个链表没有交点，intersectVal为0；如果有交点，intersectVal == listA[skipA] == listB[skipB]
进阶：设计时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案*/

public class IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        ListNode node1= new ListNode(4);
        ListNode node2= new ListNode(1);
        ListNode node3= new ListNode(8);
        ListNode node4= new ListNode(4);
        ListNode node5= new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node6= new ListNode(5);
        ListNode node7= new ListNode(6);
        ListNode node8= new ListNode(1);
        node6.next = node7;
        node7.next = node8;

        ListNode node9= getIntersectionNode(node1, node6);
        if(node9==null) System.out.println(0);
        else System.out.println(node9.val);

    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA= nodeA==null?headB:nodeA.next;
            nodeB= nodeB==null?headA:nodeB.next;

        }
        return nodeA;
    }

}
