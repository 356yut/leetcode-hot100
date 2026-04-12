package com.leetcode.hot100.k_binarysearch;

public class BinarySearchDemo {
    // 迭代实现二分查找（最常用，推荐使用）
    public static int binarySearchByIteration(int[] arr, int target) {
        // 初始化左右边界
        int left = 0;
        int right = arr.length - 1;
        // 循环查找，直到左边界超过右边界
        while (left <= right) {
            // 计算中间索引，规避整数溢出
            int mid = left + (right - left) / 2;
            // 找到目标值，直接返回索引
            if (arr[mid] == target) {
                return mid;
            }
            // 目标值更小，收缩右边界，查找左半区间
            else if (target < arr[mid]) {
                right = mid - 1;
            }
            // 目标值更大，收缩左边界，查找右半区间
            else {
                left = mid + 1;
            }
        }
        // 循环结束未找到，返回-1
        return -1;
    }

    // 递归实现二分查找
    public static int binarySearchByRecursion(int[] arr, int target, int left, int right) {
        // 递归终止条件：边界交叉，未找到元素
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (target < arr[mid]) {
            // 递归查找左半区间
            return binarySearchByRecursion(arr, target, left, mid - 1);
        } else {
            // 递归查找右半区间
            return binarySearchByRecursion(arr, target, mid + 1, right);
        }
    }

    public static void main(String[] args) {
        // 二分查找必须使用【有序数组】
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int targetExist = 7;   // 存在的目标值
        int targetNotExist = 10; // 不存在的目标值

        // 测试迭代版二分查找
        System.out.println("===== 迭代版二分查找测试 =====");
        int index1 = binarySearchByIteration(sortedArray, targetExist);
        System.out.println("目标值 " + targetExist + " 的索引：" + index1);
        int index2 = binarySearchByIteration(sortedArray, targetNotExist);
        System.out.println("目标值 " + targetNotExist + " 的索引：" + index2);

        // 测试递归版二分查找
        System.out.println("\n===== 递归版二分查找测试 =====");
        int index3 = binarySearchByRecursion(sortedArray, targetExist, 0, sortedArray.length - 1);
        System.out.println("目标值 " + targetExist + " 的索引：" + index3);
        int index4 = binarySearchByRecursion(sortedArray, targetNotExist, 0, sortedArray.length - 1);
        System.out.println("目标值 " + targetNotExist + " 的索引：" + index4);
    }
}
