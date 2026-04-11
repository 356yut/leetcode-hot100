package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class LetterCombinationsOfAPhoneNumber {
    String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> result = new ArrayList<>();
    StringBuilder cur= new StringBuilder();
    public List<String> letterCombinations(String digits) {
        backtrack(0,digits);
        return result;
    }

    private void backtrack(int i, String digits) {
        if (i == digits.length()) {
            result.add(cur.toString());
            return;
        }
        String s = letterMap[digits.charAt(i)-'0'];
        for (int j = 0; j < s.length(); j++) {
            cur.append(s.charAt(j));
            backtrack(i+1, digits);
            cur.deleteCharAt(cur.length()-1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber sol = new LetterCombinationsOfAPhoneNumber();
        System.out.println(sol.letterCombinations("23"));
    }
}
