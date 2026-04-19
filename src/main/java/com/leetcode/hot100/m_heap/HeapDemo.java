package com.leetcode.hot100.m_heap;

class HeapDemo {
    private int[] heap; // 存储堆的数组
    private int size;   // 堆当前元素个数

    // 构造方法：初始化堆容量
    public HeapDemo(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // ==================== 核心：堆化操作 ====================
    // 向上堆化（插入元素使用）：子节点比父节点大，向上交换调整
    private void siftUp(int index) {
        // 父节点索引
        int parentIndex = (index - 1) / 2;
        // 子节点大于父节点，交换并继续向上调整
        while (index > 0 && heap[index] > heap[parentIndex]) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // 向下堆化（删除堆顶/建堆使用）：父节点比子节点小，向下交换调整
    private void siftDown(int index) {
        // 最大元素索引，初始为当前父节点
        int largest = index;
        // 左、右子节点索引
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        // 找父节点、左子、右子中的最大值
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // 最大值不是父节点，交换后继续向下调整
        if (largest != index) {
            swap(index, largest);
            siftDown(largest);
        }
    }

    // ==================== 常用操作 ====================
    // 1. 建堆：将无序数组转为大顶堆
    public void buildHeap(int[] arr) {
        if (arr == null || arr.length == 0) return;
        // 复制数组到堆
        System.arraycopy(arr, 0, heap, 0, arr.length);
        size = arr.length;
        // 从最后一个非叶子节点开始，向下堆化
        int lastNonLeaf = (size - 2) / 2;
        for (int i = lastNonLeaf; i >= 0; i--) {
            siftDown(i);
        }
    }

    // 2. 插入元素
    public void insert(int val) {
        if (size >= heap.length) {
            System.out.println("堆已满，无法插入");
            return;
        }
        // 元素添加到堆尾
        heap[size] = val;
        // 向上堆化调整
        siftUp(size);
        size++;
    }

    // 3. 删除堆顶元素
    public Integer removeTop() {
        if (size == 0) {
            System.out.println("堆为空，无法删除");
            return null;
        }
        // 堆顶元素
        int top = heap[0];
        // 堆尾元素移到堆顶
        heap[0] = heap[size - 1];
        size--;
        // 向下堆化调整
        siftDown(0);
        return top;
    }

    // 4. 获取堆顶元素
    public Integer getTop() {
        if (size == 0) return null;
        return heap[0];
    }

    // 5. 堆排序
    public void heapSort() {
        int tempSize = size;
        // 循环删除堆顶，放到数组末尾，完成排序
        while (size > 0) {
            int top = removeTop();
            heap[size] = top;
        }
        // 恢复堆大小，打印排序结果
        size = tempSize;
        System.out.print("堆排序结果：");
        printHeap();
    }

    // ==================== 工具方法 ====================
    // 交换数组两个元素
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 打印堆
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // 测试主方法
    public static void main(String[] args) {
        // 初始化堆容量为10
        HeapDemo maxHeap = new HeapDemo(10);
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
}
