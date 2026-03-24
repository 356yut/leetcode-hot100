package com.leetcode.hot100.g_linkedlist;

public class SingleLinkedListDemo {
    // 链表的头节点（不存储具体数据，仅作为链表入口）
    private Node head;

    // 1. 定义链表节点类（内部静态类）
    private static class Node {
        // 数据域：存储数据
        int data;
        // 指针域：指向下一个节点
        Node next;

        // 节点构造方法
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // 构造方法：初始化空链表
    public SingleLinkedListDemo() {
        this.head = new Node(0);
    }

    // 2. 头插法：在链表头部插入节点
    public void addFirst(int data) {
        Node newNode = new Node(data);
        // 新节点指向原头节点的下一个节点
        newNode.next = head.next;
        // 头节点指向新节点
        head.next = newNode;
    }

    // 3. 尾插法：在链表尾部追加节点
    public void addLast(int data) {
        Node newNode = new Node(data);
        // 临时节点，用于遍历到链表尾部
        Node temp = head;
        // 遍历到最后一个节点
        while (temp.next != null) {
            temp = temp.next;
        }
        // 最后一个节点指向新节点
        temp.next = newNode;
    }

    // 4. 遍历链表：打印所有节点数据
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        Node temp = head.next;
        System.out.print("链表元素：");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // 5. 获取链表有效长度
    public int getLength() {
        int length = 0;
        Node temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    // 6. 按值查找节点：判断数据是否存在
    public boolean exists(int data) {
        Node temp = head.next;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // 7. 按索引修改节点数据（索引从0开始）
    public void update(int index, int newData) {
        if (index < 0 || index >= getLength()) {
            System.out.println("索引越界，修改失败！");
            return;
        }
        Node temp = head.next;
        // 遍历到目标索引节点
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = newData;
        System.out.println("修改成功！");
    }

    // 8. 按值删除节点（删除第一个匹配的节点）
    public void delete(int data) {
        Node temp = head;
        // 找到待删除节点的前一个节点
        while (temp.next != null && temp.next.data != data) {
            temp = temp.next;
        }
        // 未找到节点
        if (temp.next == null) {
            System.out.println("未找到该数据，删除失败！");
            return;
        }
        // 跳过待删除节点，完成删除
        temp.next = temp.next.next;
        System.out.println("删除成功！");
    }

    // 9. 清空链表
    public void clear() {
        head.next = null;
        System.out.println("链表已清空！");
    }

    // 主方法：测试所有链表操作
    public static void main(String[] args) {
        // 初始化空链表
        SingleLinkedListDemo list = new SingleLinkedListDemo();

        // 测试尾插法
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.list(); // 输出：链表元素：10 20 30

        // 测试头插法
        list.addFirst(5);
        list.list(); // 输出：链表元素：5 10 20 30

        // 测试获取长度
        System.out.println("链表长度：" + list.getLength()); // 输出：4

        // 测试查找节点
        System.out.println("是否存在20：" + list.exists(20)); // 输出：true
        System.out.println("是否存在40：" + list.exists(40)); // 输出：false

        // 测试修改节点
        list.update(1, 15);
        list.list(); // 输出：链表元素：5 15 20 30

        // 测试删除节点
        list.delete(20);
        list.list(); // 输出：链表元素：5 15 30

        // 测试清空链表
        list.clear();
        list.list(); // 输出：链表为空！
    }
}
