package com.leetcode.hot100.m_heap;

class MyHeapDemo {
    private int[] heap;
    private int size;

    public MyHeapDemo(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        heap = new int[capacity];
        size = 0;
    }

    // ========== 核心操作 ==========
    // 1. 向上堆化，插入元素用
    private void siftUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap[parent] < heap[index]) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    // 2. 向下堆化（删除堆顶/建堆使用）：父节点比子节点小，向下交换调整
    private void siftDown(int index) {
        int larger = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < size && heap[left] > heap[larger]) {
            larger = left;
        }
        if (right < size && heap[right] > heap[larger]) {
            larger = right;
        }
        if (larger != index) {
            swap(index, larger);
            siftDown(larger);
        }
    }

    // ========== 常用操作 ==========
    // 1. 建堆：将无序数组转为大顶堆
    public void buildHeap(int[] arr) {
        if (arr == null || arr.length == 0) return;
        if (arr.length > heap.length) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(arr, 0, heap, 0, arr.length);
        size = arr.length;
        // 从最后一个非叶子节点开始，一个个向下堆化
        int lastNonLeaf = (size - 2) / 2;
        for (int i = lastNonLeaf; i >= 0; i--) {
            siftDown(i);
        }
    }

    // 2. 插入元素
    public void insert(int value) {
        if (size >= heap.length) {
            throw new IllegalArgumentException();
        }
        heap[size] = value;
        siftUp(size++);
    }

    // 3. 取堆顶
    public Integer getTop() {
        if (size == 0) return null;
        return heap[0];
    }

    // 4. 移除堆顶
    public Integer removeTop() {
        if (size == 0) return null;
        int top = heap[0];
        heap[0] = heap[--size];
        siftDown(0);
        return top;
    }

    // 5. 堆排序
    public void heapSort() {
        int tempSize = size;
        while (size > 0) {
            int top = removeTop();
            heap[size] = top;
        }
        size = tempSize;
        printHeap();
    }

    // ========== 工具方法 ==========
    private void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 测试主方法
    public static void main(String[] args) {
        // 初始化堆容量为10
        MyHeapDemo maxHeap = new MyHeapDemo(10);
        int[] arr = {3, 1, 5, 2, 4};

        // 1. 建堆
        maxHeap.buildHeap(arr);
        System.out.print("建堆后大顶堆：");
        maxHeap.printHeap();

        // 2. 获取堆顶
        System.out.println("堆顶元素：" + maxHeap.getTop());

        // 3. 插入元素
        maxHeap.insert(6);
        System.out.print("插入元素6后：");
        maxHeap.printHeap();

        // 4. 删除堆顶
        Integer removed = maxHeap.removeTop();
        System.out.println("删除的堆顶元素：" + removed);
        System.out.print("删除堆顶后堆：");
        maxHeap.printHeap();

        // 5. 堆排序
        maxHeap.heapSort();
    }

//    建堆后大顶堆：5 4 3 2 1
//    堆顶元素：5
//    插入元素6后：6 4 5 2 1 3
//    删除的堆顶元素：6
//    删除堆顶后堆：5 4 3 2 1
//    堆排序结果：1 2 3 4 5
}
