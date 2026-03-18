package com.leetcode.hot100.c_slidingwindow;

import java.util.ArrayList;
import java.util.List;

/*给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
示例 1:
输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
示例 2:
输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
提示:
        1 <= s.length, p.length <= 3 * 104
s 和 p 仅包含小写字母*/

public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s = "bpaa", p = "aa";
        System.out.println(findAnagrams(s, p));


    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        int sLen = s.length();
        int pLen = p.length();
        if (sLen == 0 || pLen == 0 || sLen < pLen) return result;
        int[] count = new int[26];
        for (char c : p.toCharArray()) {
            count[c - 'a']++;
        }
        int matchCount = 0;
        for (int i = 0; i < pLen; i++) {
            count[s.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) matchCount++;
        }
        if (matchCount == 26) {
            result.add(0);
        }
        for (int i = 0; i < sLen-pLen; i++) {
            int leftCharIdx = s.charAt(i+pLen) - 'a';
            count[leftCharIdx]--;
            if (count[leftCharIdx] == 0) {
                matchCount++;
            }else if(count[leftCharIdx]==-1){
                matchCount--;
            }

            int rightCharIdx = s.charAt(i) - 'a';
            count[rightCharIdx]++;
            if (count[rightCharIdx] == 0) {
                matchCount++;
            }else if(count[rightCharIdx]==1){
                matchCount--;
            }

            if (matchCount == 26) {
                result.add(i+1);
            }
        }
        return result;
    }
}
