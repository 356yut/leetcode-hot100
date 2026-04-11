package com.leetcode.hot100.i_graph;

class ImplementTriePrefixTree {
    class Trie {
        class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isEnd;
        }

        TrieNode root = new TrieNode();

        public Trie() {
        }

        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new TrieNode();
                }
                current = current.children[c - 'a'];
            }
            current.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    return false;
                }
                current = current.children[c - 'a'];
            }
            return current.isEnd;
        }

        public boolean startsWith(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (current.children[c - 'a'] == null) {
                    return false;
                }
                current = current.children[c - 'a'];
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Trie trie = new ImplementTriePrefixTree().new Trie();
        trie.insert("apple");

        System.out.println(trie.search("apple"));// 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }
}
