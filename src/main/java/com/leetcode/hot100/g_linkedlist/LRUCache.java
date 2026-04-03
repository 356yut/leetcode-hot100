package com.leetcode.hot100.g_linkedlist;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;

    public DLinkedNode() {
    }

    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache1 {
    HashMap<Integer, DLinkedNode> cache;
    int capacity, size;
    DLinkedNode head, tail;


    public LRUCache1(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<Integer, DLinkedNode>();
        head=new DLinkedNode();
        tail=new DLinkedNode();
        head.next=tail;
        tail.prev=head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node==null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }


    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node!=null) {
            node.value = value;
            moveToHead(node);
        }else {
            node = new DLinkedNode(key, value);
            cache.put(key, node);
            addToHead(node);
            if(size==capacity) {
                DLinkedNode tailNode=removeTail();
                cache.remove(tailNode.key);
            }else {
                size++;
            }
        }
    }

    private DLinkedNode removeTail() {
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
}
class LRUCache {
    Map<Integer, Integer> cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer,Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };

    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}


