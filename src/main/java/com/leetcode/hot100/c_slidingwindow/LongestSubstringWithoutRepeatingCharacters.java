package com.leetcode.hot100.c_slidingwindow;

import java.util.HashSet;
import java.util.Set;

/*给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。示例 1: 输入: s = "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。示例 2: 输入: s = "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。示例 3: 输入: s = "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是子串的长度，"pwke" 是一个子序列，不是子串。提示：0 <= s.length <= 5 * 104，s 由英文字母、数字、符号和空格组成。*/
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        int res=0;
        int len=s.length();
        int left=0;
        Set<Character> set=new HashSet<>();
        for (int right=0;right<len;right++) {
            char c=s.charAt(right);
            while(set.contains(c)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            res=Math.max(res,right-left+1);
        }
        return res;
    }
}
