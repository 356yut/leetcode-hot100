package com.leetcode.hot100;

import java.util.Scanner;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.HeadlessException;

/**
 * 连字符字符串转大驼峰 + 剪贴板复制工具
 * 示例输入：intersection-of-two-linked-lists
 * 示例输出：IntersectionOfTwoLinkedLists
 */
public class LinkStringToCamel {
    public static void main(String[] args) {
        // 1. 获取用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入连字符格式字符串：");
        String input = scanner.nextLine().trim();
        scanner.close();

        // 2. 转换为大驼峰格式
        String result = convertToPascalCase(input);

        // 3. 控制台输出结果
        System.out.println("转换完成：" + result);

        // 4. 复制到系统剪贴板
        copyToClipboard(result);
    }

    /**
     * 核心转换方法：连字符分隔字符串 → 大驼峰命名法
     * @param input 输入字符串(如a-b-c)
     * @return 大驼峰字符串(如ABC)
     */
    private static String convertToPascalCase(String input) {
        // 空值校验
        if (input == null || input.isEmpty()) {
            return "";
        }
        // 按连字符分割字符串
        String[] wordArray = input.split("-");
        StringBuilder builder = new StringBuilder();

        for (String word : wordArray) {
            if (word.isEmpty()) continue;
            // 首字母大写 + 剩余字符原样拼接
            builder.append(Character.toUpperCase(word.charAt(0)));
            if (word.length() > 1) {
                builder.append(word.substring(1));
            }
        }
        return builder.toString();
    }

    /**
     * 剪贴板复制方法
     * @param text 要复制的文本
     */
    private static void copyToClipboard(String text) {
        try {
            StringSelection selection = new StringSelection(text);
            // 获取系统剪贴板
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            // 设置剪贴板内容
            clipboard.setContents(selection, null);
            System.out.println("✅ 已成功复制到剪贴板！");
        } catch (HeadlessException e) {
            // 无图形界面环境（如服务器）报错处理
            System.err.println("❌ 复制失败：当前环境不支持剪贴板功能");
        }
    }
}