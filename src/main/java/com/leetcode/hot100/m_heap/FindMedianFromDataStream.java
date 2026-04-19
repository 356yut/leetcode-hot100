package com.leetcode.hot100.m_heap;

import java.util.PriorityQueue;

class FindMedianFromDataStream {

    class MedianFinder {
        private PriorityQueue<Integer> minHeap;
        private PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>((v1, v2) -> v2 - v1);
        }

        public void addNum(int num) {
            if(maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            }else {
                minHeap.offer(num);
            }
            if(minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            if(maxHeap.size() > minHeap.size()+1) {
                minHeap.offer(maxHeap.poll());
            }
        }

        public double findMedian() {
            if(maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {

    }
}
