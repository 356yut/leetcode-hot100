package com.leetcode.hot100.e_array;

import java.util.Arrays;

public class ArrayDemo {
    public static void main(String[] args) {
        // ==================== 1. 数组初始化 ====================
        // 静态初始化：直接指定元素值
        int[] staticArray = {10, 20, 30, 40, 50};
        // 动态初始化：指定数组长度，元素默认赋值（int类型默认0）
        int[] dynamicArray = new int[5];
        System.out.println("===== 数组初始化 =====");
        System.out.print("静态初始化数组：");
        printArray(staticArray);
        System.out.print("动态初始化数组（默认值）：");
        printArray(dynamicArray);

        // ==================== 2. 访问与修改元素 ====================
        System.out.println("\n===== 访问与修改元素 =====");
        // 访问索引为2的元素
        System.out.println("访问索引2的元素：" + staticArray[2]);
        // 修改索引为2的元素
        staticArray[2] = 33;
        System.out.print("修改后数组：");
        printArray(staticArray);

        // ==================== 3. 数组遍历 ====================
        System.out.println("\n===== 数组遍历 =====");
        // 普通for循环遍历
        System.out.print("普通for循环遍历：");
        for (int i = 0; i < staticArray.length; i++) {
            System.out.print(staticArray[i] + " ");
        }
        // 增强for循环遍历
        System.out.print("\n增强for循环遍历：");
        for(int num:staticArray) System.out.print(num+" ");
        // ==================== 4. 线性查找元素 ====================
        System.out.println("\n\n===== 线性查找元素 =====");
        int target = 40;
        int index = linearSearch(staticArray, target);
        if (index != -1) {
            System.out.println("元素" + target + "的索引：" + index);
        } else {
            System.out.println("未找到元素" + target);
        }

        // ==================== 5. 插入元素 ====================
        System.out.println("\n===== 插入元素 =====");
        int insertIndex = 2;
        int insertValue = 25;
        int[] insertArray = insertElement(staticArray, insertIndex, insertValue);
        System.out.print("插入元素后数组：");
        printArray(insertArray);

        // ==================== 6. 删除元素 ====================
        System.out.println("\n===== 删除元素 =====");
        int deleteIndex = 3;
        int[] deleteArray = deleteElement(insertArray, deleteIndex);
        System.out.print("删除索引" + deleteIndex + "后数组：");
        printArray(deleteArray);

        // ==================== 7. 数组排序（冒泡排序） ====================
        System.out.println("\n===== 冒泡排序 =====");
        int[] unSortArray = {5, 2, 9, 1, 5, 6};
        System.out.print("排序前数组：");
        printArray(unSortArray);
        bubbleSort(unSortArray);
        System.out.print("排序后数组：");
        printArray(unSortArray);

        // ==================== 8. 查找最大/最小值 ====================
        System.out.println("\n===== 查找最大/最小值 =====");
        int[] numArray = {12, 35, 99, 7, 54};
        System.out.println("数组最大值：" + getMax(numArray));
        System.out.println("数组最小值：" + getMin(numArray));

        // ==================== 9. 数组反转 ====================
        System.out.println("\n===== 数组反转 =====");
        int[] reverseArray = {1, 2, 3, 4, 5};
        System.out.print("反转前数组：");
        printArray(reverseArray);
        reverseArray(reverseArray);
        System.out.print("反转后数组：");
        printArray(reverseArray);
        System.out.println(Arrays.stream(staticArray).sum());
        System.out.println(Arrays.stream(staticArray).max().getAsInt());
    }

    // 工具方法：打印数组
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 线性查找：返回元素索引，未找到返回-1
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 插入元素：新建数组实现插入
    public static int[] insertElement(int[] arr, int index, int value) {
        // 校验索引合法性
        if (index < 0 || index > arr.length) {
            throw new RuntimeException("索引不合法");
        }
        int[] newArr = new int[arr.length + 1];
        for (int i = 0; i < newArr.length; i++) {
            if (i < index) {
                newArr[i] = arr[i];
            } else if (i == index) {
                newArr[i] = value;
            } else {
                newArr[i] = arr[i - 1];
            }
        }
        return newArr;
    }

    // 删除元素：新建数组实现删除
    public static int[] deleteElement(int[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            throw new RuntimeException("索引不合法");
        }
        int[] newArr = new int[arr.length - 1];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[count++] = arr[i];
            }
        }
        return newArr;
    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 获取最大值
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // 获取最小值
    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    // 数组反转
    public static void reverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
