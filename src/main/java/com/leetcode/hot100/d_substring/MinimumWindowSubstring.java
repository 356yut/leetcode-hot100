package com.leetcode.hot100.d_substring;
/*给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的最短窗口子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。测试用例保证答案唯一。
示例 1：输入：s = "ADOBECODEBANC", t = "ABC"，输出："BANC"
示例 2：输入：s = "a", t = "a"，输出："a"
示例 3: 输入: s = "a", t = "aa"，输出: ""
提示：m == s.length，n == t.length，1 <= m, n <= 10^5，s 和 t 由英文字母组成
进阶：设计一个在 O(m + n) 时间内解决此问题的算法*/
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "abcabcbb";
        String t = "bbbbb";
        System.out.println(minWindow(s, t));
    }
    public static String minWindow(String s, String t) {
        int[] need=new int[128];
        int[] window=new int[128];
        int count =0;
        for(char c : t.toCharArray()){
            if(need[c]==0){
                count++;
            }
            need[c]++;
        }
        int left=0,right=0;
        int valid=0;
        int start=0,minLen=Integer.MAX_VALUE;
        while(right<s.length()){
            char c = s.charAt(right);
            right++;
            if(need[c]!=0){
                window[c]++;
                if(window[c]==need[c]){
                    valid++;
                }
            }
            // 收缩左边
            while (valid==count){
                if(right-left<minLen){
                    minLen=right-left;
                    start=left;
                }
                char b = s.charAt(left);
                left++;
                if(need[b]!=0){
                    if(window[b]==need[b]){
                        valid--;
                    }
                    window[b]--;
                }
            }
        }





        return minLen==Integer.MAX_VALUE?"": s.substring(start,start+minLen);
    }
}
