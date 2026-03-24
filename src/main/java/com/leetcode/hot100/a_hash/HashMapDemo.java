package com.leetcode.hot100.a_hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * HashMap 常用操作完整演示
 * 包含：增删改查、遍历、判断、排序（key/value）、扩容相关常用方法
 */
public class HashMapDemo {
    public static void main(String[] args) {
        // ============== 1. 创建HashMap对象 ==============
        HashMap<String, Integer> map = new HashMap<>();

        // ============== 2. 添加元素 ==============
        map.put("张三", 18);
        map.put("李四", 19);
        map.put("王五", 20);
        System.out.println("===== 初始添加元素后 =====");
        System.out.println(map);

        // ============== 3. 获取元素（基础+防空指针） ==============
        // 普通获取
        System.out.println("获取张三的年龄：" + map.get("张三"));
        // 推荐：getOrDefault 键不存在时返回默认值，避免空指针
        System.out.println("获取赵六的年龄（默认值）：" + map.getOrDefault("赵六", 0));

        // ============== 4. 修改元素 ==============
        // 方式1：put覆盖（原有key存在则修改）
        map.put("张三", 28);
        // 方式2：replace 语义化修改（推荐）
        map.replace("李四", 29);
        System.out.println("===== 修改元素后 =====");
        System.out.println(map);

        // ============== 5. 删除元素 ==============
        map.remove("张三"); // 根据key删除
        System.out.println("===== 删除张三后 =====");
        System.out.println(map);

        // ============== 6. 遍历元素（3种常用方式） ==============
        System.out.println("===== 遍历方式1：keySet遍历（原代码） =====");
        for (String key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }

        System.out.println("===== 遍历方式2：entrySet遍历（推荐，效率最高） =====");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("===== 遍历方式3：仅遍历所有value（会保留重复的value） =====");
        for (Integer value : map.values()) {
            System.out.println("年龄：" + value);
        }

        // 恢复数据，用于后续排序演示
        map.put("张三", 18);

        // ============== 7. 按Key排序（核心完善） ==============
        // HashMap无序，TreeMap天然支持Key升序排序
        System.out.println("===== 按Key升序排序 =====");
        TreeMap<String, Integer> sortedByKeyMap = new TreeMap<>(map);
        System.out.println(sortedByKeyMap);

        // ============== 8. 按Value排序（核心完善，升序+降序） ==============
        System.out.println("===== 按Value升序排序 =====");
        // 1. 转成List<Entry> 2. 排序 3. 放入LinkedHashMap保持顺序
        LinkedHashMap<String, Integer> sortedByValueAsc = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
        System.out.println(sortedByValueAsc);

        System.out.println("===== 按Value降序排序 =====");
        LinkedHashMap<String, Integer> sortedByValueDesc = map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
        System.out.println(sortedByValueDesc);

        // ============== 9. 判断元素 ==============
        System.out.println("===== 判断操作 =====");
        System.out.println("是否包含key：张三 → " + map.containsKey("张三"));
        System.out.println("是否包含value：18 → " + map.containsValue(18));

        // ============== 10. 集合信息 ==============
        System.out.println("HashMap大小：" + map.size());
        System.out.println("是否为空：" + map.isEmpty());

        // ============== 11. 清空HashMap ==============
        map.clear();
        System.out.println("===== 清空后 =====");
        System.out.println("是否为空：" + map.isEmpty());
        System.out.println("HashMap大小：" + map.size());
    }
}