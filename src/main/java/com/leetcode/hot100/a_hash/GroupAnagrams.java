package com.leetcode.hot100.a_hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
示例 1:输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]，输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:输入: strs = [""]，输出: [[""]]
示例 3:输入: strs = ["a"]，输出: [["a"]]
提示：1 <= strs.length <= 104，0 <= strs[i].length <= 100，strs[i] 仅包含小写字母*/
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat","eea","eae"};
        List<List<String>> res = groupAnagrams(strs);

        System.out.println(res);
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!map.containsKey(key)) {
                map.put(key,new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
