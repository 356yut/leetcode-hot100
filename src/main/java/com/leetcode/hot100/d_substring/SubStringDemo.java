package com.leetcode.hot100.d_substring;

import java.util.Arrays;

public class SubStringDemo {
    public static void main(String[] args) {
        String originalStr = "Hello World,I Love World";
        System.out.println(originalStr.length());
        System.out.println(originalStr.substring(0, originalStr.length() - 1));
        System.out.println(originalStr.substring(5));

        System.out.println(originalStr.contains("Hello"));
        System.out.println(originalStr.contains("World"));

        System.out.println(originalStr.toLowerCase());
        System.out.println(originalStr.toUpperCase());

        System.out.println(originalStr.indexOf("Hello"));
        System.out.println(originalStr.indexOf("w"));
        System.out.println(originalStr.lastIndexOf("h"));

        System.out.println(originalStr.replace("Hello", "World"));
        System.out.println(originalStr.replaceFirst("World", "H"));
        System.out.println(originalStr.replaceAll("World", ""));

        System.out.println(Arrays.toString(originalStr.split(",")));
        String subStr = originalStr.substring(1, originalStr.length() - 1);
        System.out.println(subStr);

        // 输出a的ascii码
        System.out.println((int)"a".charAt(0));
        // 输出97的字母
        System.out.println((char)97);

    }
}
