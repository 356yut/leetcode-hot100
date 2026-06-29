```
src/
└── leetcode/
    └── hot100/
        ├── 01array        ✅ 第1个显示
        ├── 02string       ✅ 第2个显示
        ├── 03hashmap      ✅ 第3个显示
        ├── 04twopointer   ✅ 第4个显示
        ├── 05slidingwindow
        ├── 06stack
        ├── 07linkedlist
        ├── 08binarytree
        ├── 09backtrack
        ├── 10dp
        └── 11greedy
```

哈希：整理算法内容，使用java编写一个demo演示常用操作，需要包含以下内容，注意不使用分级标题（可以使用有序列表和无序列表）

1.定义

2.常见操作



整理算法内容，使用java，需要包含以下内容，注意不使用分级标题（可以使用有序列表和无序列表），不要写总结

1.题目描述

2.算法思想+代码

（如果有多种解法分开写）



# 模板

## 哈希

**定义** 

哈希（散列）是一种通过哈希函数将任意长度的输入数据，转换为固定长度的整数输出（哈希值/散列值）的算法，核心作用是建立键（Key）与值（Value） 的快速映射关系；基于哈希算法实现的数据结构称为哈希表，Java中的HashMap、HashSet、HashTable等集合类底层均基于哈希表实现，不同输入数据通过哈希函数得到相同哈希值的现象称为哈希冲突，是哈希算法需要处理的核心问题。

**常见操作**

```java
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
```

### [1. 两数之和](https://leetcode.cn/problems/two-sum/)

1. 题目描述
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。你可以按任意顺序返回答案。
示例 1：输入：nums = [2,7,11,15], target = 9，输出：[0,1]，解释：因为 nums[0] + nums[1] == 9，返回 [0, 1]
示例 2：输入：nums = [3,2,4], target = 6，输出：[1,2]
示例 3：输入：nums = [3,3], target = 6，输出：[0,1]
提示：2 <= nums.length <= 104，-109 <= nums[i] <= 109，-109 <= target <= 109，只会存在一个有效答案
进阶：你可以想出一个时间复杂度小于 O(n²) 的算法吗？

2. 解法一：暴力枚举法
- 算法思想：通过双重for循环遍历数组中的所有元素对，依次计算两个元素的和，判断是否等于目标值target。题目保证仅有一个有效答案，找到符合条件的下标后直接返回即可。该方法实现简单直观，时间复杂度为O(n²)，空间复杂度为O(1)。
- Java代码：
```java
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 双重循环遍历所有元素对
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // 判断两数之和是否等于目标值
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // 题目保证有解，此处仅为语法完整性
        return new int[0];
    }
}
```

3. 解法二：哈希表法（最优解）
- 算法思想：利用HashMap存储数组元素的值（key）和对应的下标（value），仅需一次遍历数组。遍历当前元素时，计算target与当前元素的差值，若差值已存在于HashMap中，说明找到符合条件的两个数，直接返回差值对应的下标和当前下标；若不存在，则将当前元素和下标存入HashMap。该方法时间复杂度为O(n)，空间复杂度为O(n)，满足进阶要求。
- Java代码：
```java
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 初始化哈希表，key：数组元素值，value：元素下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 计算需要寻找的差值
            int diff = target - nums[i];
            // 判断差值是否存在于哈希表中
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            // 不存在则将当前元素和下标存入哈希表
            map.put(nums[i], i);
        }
        // 题目保证有解，此处仅为语法完整性
        return new int[0];
    }
}
```

### [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/)

1. 题目描述
给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
示例 1:输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]，输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:输入: strs = [""]，输出: [[""]]
示例 3:输入: strs = ["a"]，输出: [["a"]]
提示：1 <= strs.length <= 104，0 <= strs[i].length <= 100，strs[i] 仅包含小写字母

2. 算法思想+代码
解法一：排序哈希表法
- 算法思想：字母异位词的字符种类和数量完全一致，对字符串的字符排序后，所有字母异位词的排序结果完全相同。以排序后的字符串为哈希表的键，原字符串为值存入对应列表，遍历完成后哈希表的值集合即为分组结果。
- 代码：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 定义哈希表，key为排序后的字符串，value为字母异位词列表
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // 将字符串转为字符数组并排序
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            // 键不存在则创建新列表，存在则直接添加元素
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        // 返回哈希表所有值的集合
        return new ArrayList<>(map.values());
    }
}
```

解法二：计数哈希表法
- 算法思想：字符串仅包含小写字母，统计每个字符串26个字母的出现次数，将计数结果拼接为唯一字符串作为哈希表键，字母异位词的计数键完全相同，以此完成分组，该方法无需排序，效率更高。
- 代码：
```java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // 数组统计26个小写字母的出现次数
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            // 拼接计数结果为唯一键，用#分隔避免数字拼接歧义
            StringBuilder sb = new StringBuilder();
            for (int num : count) {
                sb.append(num).append('#');
            }
            String key = sb.toString();
            // 简化写法：键不存在则创建列表，并存入当前字符串
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
```

### [128. 最长连续序列](https://leetcode.cn/problems/longest-consecutive-sequence/)

1. 题目描述
给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
示例 1：输入：nums = [100,4,200,1,3,2]，输出：4，解释：最长数字连续序列是 [1, 2, 3, 4]，它的长度为 4。
示例 2：输入：nums = [0,3,7,2,5,8,4,6,0,1]，输出：9。
示例 3：输入：nums = [1,0,1,2]，输出：3。
提示：0 <= nums.length <= 105，-109 <= nums[i] <= 109

2. 算法思想+代码
- 解法一：哈希集合解法（满足O(n)时间复杂度要求）
  算法思想：
  1. 首先将数组中的所有元素存入HashSet，完成去重操作，同时利用HashSet实现O(1)时间复杂度的元素查找。
  2. 遍历HashSet中的每一个元素，仅当元素num的前一个数字num-1不存在于集合中时，判定num为连续序列的起始数字。
  3. 从起始数字开始，依次向后查找num+1、num+2等连续数字是否存在于集合中，统计当前连续序列的长度。
  4. 遍历过程中持续更新最长连续序列的长度，最终返回结果。
  5. 每个元素只会被访问一次，整体时间复杂度为O(n)，空间复杂度为O(n)。
  Java代码：
  ```java
  import java.util.HashSet;
  import java.util.Set;
  
  public class Solution {
      public int longestConsecutive(int[] nums) {
          // 定义哈希集合存储数组元素，实现去重和快速查找
          Set<Integer> numSet = new HashSet<>();
          for (int num : nums) {
              numSet.add(num);
          }
          // 记录最长连续序列的长度
          int maxLength = 0;
          // 遍历集合中的每个元素
          for (int num : numSet) {
              // 判断当前数字是否为连续序列的起点（前一个数字不存在）
              if (!numSet.contains(num - 1)) {
                  int currentNum = num;
                  int currentLength = 1;
                  // 向后查找连续的数字
                  while (numSet.contains(currentNum + 1)) {
                      currentNum++;
                      currentLength++;
                  }
                  // 更新最长序列长度
                  maxLength = Math.max(maxLength, currentLength);
              }
          }
          return maxLength;
      }
  }
  ```

## 双指针

1. 双指针是一种应用在数组、链表、字符串等线性数据结构上的算法思想，通过定义**两个独立的指针**，分别控制不同的遍历位置、移动速度或移动方向，协同完成数据遍历、查找、修改、筛选等操作；该算法无需额外开辟辅助数据结构，时间复杂度通常为O(n)，空间复杂度为O(1)，核心优势是替代暴力枚举法，大幅减少无效遍历次数，是优化线性结构操作的核心方法。
2. 双指针的常见操作包含四大核心类型，附Java实现代码与适用场景，- 同向快慢指针：两个指针从线性结构的同一侧出发，快指针负责遍历所有元素，慢指针负责记录有效元素的位置，适用于数组去重、链表判环、删除指定元素等场景；Java代码示例（数组去重）：
```java
public class DoublePointer {
    // 有序数组去重，返回去重后数组长度
    public static int removeDuplicates(int[] nums) {
        // 边界值判断
        if (nums == null || nums.length == 0) return 0;
        // 慢指针：指向当前有效元素的末尾
        int slow = 0;
        // 快指针：遍历整个数组
        for (int fast = 1; fast < nums.length; fast++) {
            // 遇到不重复元素，慢指针前移并赋值
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
```
- 相向双指针：两个指针分别从线性结构的头部、尾部同时出发，向中间方向移动，适用于有序数组两数之和、数组反转、回文字符串判断等场景；Java代码示例（有序数组两数之和）：
```java
// 有序数组中查找和为target的两个元素下标
public static int[] twoSum(int[] nums, int target) {
    int left = 0; // 左指针：从头部开始
    int right = nums.length - 1; // 右指针：从尾部开始
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) {
            // 找到目标，返回下标
            return new int[]{left, right};
        } else if (sum < target) {
            // 和过小，左指针右移增大数值
            left++;
        } else {
            // 和过大，右指针左移减小数值
            right--;
        }
    }
    // 未找到返回默认值
    return new int[]{-1, -1};
}
```
- 滑动窗口双指针：属于同向双指针的延伸，左右指针动态组成一个滑动窗口，通过移动指针调整窗口的大小与位置，适用于查找最长子串、最短子数组、连续子数组求和等连续区间问题；Java代码示例（长度最小的子数组）：
```java
// 寻找和≥target的最短连续子数组长度
public static int minSubArrayLen(int target, int[] nums) {
    int left = 0; // 窗口左边界
    int sum = 0; // 窗口内元素和
    int minLen = Integer.MAX_VALUE; // 记录最小长度
    // 右指针：扩展窗口右边界
    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];
        // 满足条件时，收缩左指针缩小窗口
        while (sum >= target) {
            minLen = Math.min(minLen, right - left + 1);
            sum -= nums[left];
            left++;
        }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```
- 多指针（双指针扩展）：基于双指针思想扩展为三个及以上指针，适用于三数之和、合并两个有序数组、排序链表合并等多元素协同处理场景；Java代码示例（合并两个有序数组）：
```java
// 合并nums2到nums1，最终nums1为有序数组
public static void merge(int[] nums1, int m, int[] nums2, int n) {
    // p1：nums1有效元素尾部指针，p2：nums2尾部指针，p：合并后数组尾部指针
    int p1 = m - 1, p2 = n - 1, p = m + n - 1;
    // 从后往前遍历，避免覆盖元素
    while (p1 >= 0 && p2 >= 0) {
        if (nums1[p1] > nums2[p2]) {
            nums1[p--] = nums1[p1--];
        } else {
            nums1[p--] = nums2[p2--];
        }
    }
    // 处理nums2剩余元素
    while (p2 >= 0) {
        nums1[p--] = nums2[p2--];
    }
}
```

### [283. 移动零](https://leetcode.cn/problems/move-zeroes/)

1. 题目描述
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。请注意 ，必须在不复制数组的情况下原地对数组进行操作。
示例 1: 输入: nums = [0,1,0,3,12] 输出: [1,3,12,0,0]
示例 2: 输入: nums = [0] 输出: [0]
提示: 1 <= nums.length <= 10^4，-2^31 <= nums[i] <= 2^31 - 1
进阶：你能尽量减少完成的操作次数吗

2. 算法思想+代码
- 解法一：双指针法（最优解，满足进阶要求）
  算法思想：定义慢指针记录非零元素的存放位置，快指针遍历数组寻找非零元素；快指针找到非零元素后，将其赋值给慢指针指向的位置，慢指针右移；遍历结束后，将慢指针之后的所有位置赋值为0，实现原地移动零，时间复杂度O(n)，空间复杂度O(1)
  Java代码：
  
  ```java
  public class Solution {
      public void moveZeroes(int[] nums) {
          // 慢指针：标记下一个非零元素要放置的索引位置
          int left = 0;
          // 快指针：遍历整个数组，查找非零元素
          for (int right = 0; right < nums.length; right++) {
              // 快指针找到非零元素
              if (nums[right] != 0) {
                  // 将非零元素赋值给慢指针位置
                  nums[left] = nums[right];
                  // 慢指针后移，准备存储下一个非零元素
                  left++;
              }
          }
          // 将慢指针之后的所有位置赋值为0
          for (int i = left; i < nums.length; i++) {
              nums[i] = 0;
          }
      }
  
      // 测试示例
      public static void main(String[] args) {
          Solution solution = new Solution();
          int[] nums1 = {0,1,0,3,12};
          solution.moveZeroes(nums1);
          for (int num : nums1) {
              System.out.print(num + " ");
          }
          System.out.println();
          int[] nums2 = {0};
          solution.moveZeroes(nums2);
          for (int num : nums2) {
              System.out.print(num + " ");
          }
      }
  }
  ```
- 解法二：暴力遍历法
  算法思想：遍历数组，遇到0元素时，将该元素后方的所有元素依次向前移动一位，最后将数组末尾赋值为0；该方法需要多次遍历数组，操作次数较多，时间复杂度O(n²)，空间复杂度O(1)
  Java代码：
  ```java
  public class Solution {
      public void moveZeroes(int[] nums) {
          // 数组长度，动态缩减避免重复处理末尾的0
          int len = nums.length;
          for (int i = 0; i < len; i++) {
              // 找到数组中的0元素
              if (nums[i] == 0) {
                  // 将0后方的所有元素向前移动一位
                  for (int j = i + 1; j < len; j++) {
                      nums[j - 1] = nums[j];
                  }
                  // 数组最后一位赋值为0
                  nums[len - 1] = 0;
                  // 元素前移后，当前索引仍为新的0，索引回退
                  i--;
                  // 有效长度减一，不再处理已移动到末尾的0
                  len--;
              }
          }
      }
  
      // 测试示例
      public static void main(String[] args) {
          Solution solution = new Solution();
          int[] nums1 = {0,1,0,3,12};
          solution.moveZeroes(nums1);
          for (int num : nums1) {
              System.out.print(num + " ");
          }
      }
  }
  ```

### [11. 盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/)

1. 题目描述
给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。返回容器可以储存的最大水量。说明：你不能倾斜容器。
示例 1：输入：[1,8,6,2,5,4,8,3,7]，输出：49 ，解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]，在此情况下，容器能够容纳水的最大值为 49。
示例 2：输入：height = [1,1]，输出：1。
提示：n == height.length，2 <= n <= 105，0 <= height[i] <= 104。

2. 解法一：暴力枚举法
- 算法思想：通过双重for循环遍历数组中所有两两组合的垂线，对每一组垂线计算其能容纳的水量，水量由两垂线的水平距离（宽度）和两垂线中较短的高度决定，计算公式为 min(height[i], height[j]) * (j - i)，遍历过程中记录最大的水量值。该方法思路简单但时间复杂度较高，无法通过大数据量的测试用例。
- 代码：
```java
public class Solution {
    public int maxArea(int[] height) {
        // 记录最大水量
        int max = 0;
        int n = height.length;
        // 遍历所有左边界垂线
        for (int i = 0; i < n; i++) {
            // 遍历所有右边界垂线
            for (int j = i + 1; j < n; j++) {
                // 计算当前组合的盛水量
                int area = Math.min(height[i], height[j]) * (j - i);
                // 更新最大水量
                max = Math.max(max, area);
            }
        }
        return max;
    }
}
```

3. 解法二：双指针法（最优解法）
- 算法思想：初始化左指针指向数组起始位置，右指针指向数组末尾位置，循环计算当前指针所指垂线的盛水量并更新最大值。由于水量由较短的垂线高度决定，因此每次将高度较小的指针向中间移动，尝试寻找更高的垂线以增大盛水量，直到左右指针相遇时结束循环。该方法仅需一次遍历，时间效率最优。
- 代码：
```java
public class Solution {
    public int maxArea(int[] height) {
        // 左指针初始指向数组头部
        int left = 0;
        // 右指针初始指向数组尾部
        int right = height.length - 1;
        // 存储最终的最大水量
        int maxArea = 0;
        // 左右指针未相遇时持续遍历
        while (left < right) {
            // 计算两指针间的宽度
            int width = right - left;
            // 计算当前指针组合的盛水量
            int currentArea = Math.min(height[left], height[right]) * width;
            // 更新最大水量
            maxArea = Math.max(maxArea, currentArea);
            // 移动高度较小的指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
```

### [15. 三数之和](https://leetcode.cn/problems/3sum/)

1. 题目描述
给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。
示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
提示：
3 <= nums.length <= 3000
-10^5 <= nums[i] <= 10^5

2. 算法思想+代码
- 解法一：排序+双指针法（最优解法，时间复杂度O(n²)，空间复杂度O(logn)）
  算法思想：
  1. 先对数组进行排序，排序后便于跳过重复元素，同时利用有序数组的特性使用双指针优化查找效率
  2. 遍历数组，固定第一个元素nums[i]，若当前元素与前一个元素重复，直接跳过，避免生成重复三元组
  3. 定义左指针left = i + 1，右指针right = nums.length - 1，在左右指针不重合的前提下循环
  4. 计算三个数的和sum = nums[i] + nums[left] + nums[right]
  5. 若sum == 0，将三元组加入结果集，然后分别移动左右指针并跳过重复元素，防止结果重复
  6. 若sum < 0，说明需要增大数值，将左指针右移
  7. 若sum > 0，说明需要减小数值，将右指针左移
  Java代码：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // 数组为空或长度小于3，直接返回空集合
        if (nums == null || nums.length < 3) {
            return res;
        }
        // 对数组执行排序
        Arrays.sort(nums);
        int len = nums.length;
        // 遍历固定第一个数
        for (int i = 0; i < len; i++) {
            // 排序后第一个数大于0，后续数字均更大，无满足条件的三元组
            if (nums[i] > 0) {
                break;
            }
            // 跳过重复的第一个元素，避免重复结果
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 定义左右指针
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    // 将符合条件的三元组加入结果集
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 跳过左指针重复元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过右指针重复元素
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 移动指针继续查找
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 三数之和偏小，左指针右移
                    left++;
                } else {
                    // 三数之和偏大，右指针左移
                    right--;
                }
            }
        }
        return res;
    }

    // 测试方法
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums1));
        int[] nums2 = {0,1,1};
        System.out.println(solution.threeSum(nums2));
        int[] nums3 = {0,0,0};
        System.out.println(solution.threeSum(nums3));
    }
}
```
- 解法二：暴力枚举法（基础解法，时间复杂度O(n³)，大数据量下会超时，仅用于原理理解）
  算法思想：
  1. 通过三层for循环遍历数组中所有不重复的三元组组合
  2. 判断三个数字的和是否等于0，满足条件则将三元组存入集合实现自动去重
  3. 对存入集合的三元组提前排序，保证相同组合的三元组格式一致，最终将集合转为列表返回
  Java代码：
```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSumBruteForce {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        int length = nums.length;
        // 三层循环遍历所有三元组组合
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    // 判断三数之和是否为0
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        // 排序后存入集合，避免重复三元组
                        list.sort(Integer::compareTo);
                        resultSet.add(list);
                    }
                }
            }
        }
        // 将去重后的集合转换为列表
        return new ArrayList<>(resultSet);
    }

    // 测试方法
    public static void main(String[] args) {
        ThreeSumBruteForce solution = new ThreeSumBruteForce();
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums1));
        int[] nums2 = {0,1,1};
        System.out.println(solution.threeSum(nums2));
        int[] nums3 = {0,0,0};
        System.out.println(solution.threeSum(nums3));
    }
}
```

### [42. 接雨水](https://leetcode.cn/problems/trapping-rain-water/)

1. 题目描述
给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
示例 1：输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]，输出：6，解释：数组对应的高度图可以接 6 个单位的雨水
示例 2：输入：height = [4,2,0,3,2,5]，输出：9
提示：n == height.length，1 <= n <= 2 * 104，0 <= height[i] <= 105

2. 算法思想+代码
- 解法一：暴力法
  算法思想：遍历数组中的每一个位置，分别查找该位置左侧最高的柱子高度和右侧最高的柱子高度，取两个高度的较小值减去当前柱子高度，得到当前位置能接的雨水量，累加所有位置的有效雨水量即为总雨水量，若计算结果为负则该位置无法接雨水
  Java代码：
```java
public class Solution {
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        // 首尾柱子无法接雨水，跳过
        for (int i = 1; i < n - 1; i++) {
            int leftMax = 0, rightMax = 0;
            // 找左侧最大高度
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 找右侧最大高度
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            // 计算当前位置雨水量
            int min = Math.min(leftMax, rightMax);
            res += min > height[i] ? min - height[i] : 0;
        }
        return res;
    }
}
```

- 解法二：动态规划法
  算法思想：暴力法会重复计算每个位置的左右最大高度，动态规划通过预处理优化，创建leftMax数组存储每个位置左侧的最大高度，rightMax数组存储每个位置右侧的最大高度，仅需三次遍历即可完成计算，时间复杂度优化为O(n)，空间复杂度为O(n)
  Java代码：
```java
public class Solution {
    public int trap(int[] height) {
        int res = 0;
        int n = height.length;
        if (n == 0) return 0;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        // 初始化左侧最大高度数组
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // 初始化右侧最大高度数组
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // 计算总雨水量
        for (int i = 1; i < n - 1; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
}
```

- 解法三：双指针法
  算法思想：在动态规划的基础上进一步优化空间复杂度，使用左右两个指针从数组两端向中间遍历，同时维护左右两侧的最大高度；当左侧最大高度小于右侧最大高度时，当前左指针位置的雨水量由左侧最大高度决定，反之由右侧最大高度决定，逐位计算并累加雨水量，空间复杂度降至O(1)
  Java代码：
```java
public class Solution {
    public int trap(int[] height) {
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                // 处理左指针
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += leftMax - height[left];
                }
                left++;
            } else {
                // 处理右指针
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += rightMax - height[right];
                }
                right--;
            }
        }
        return res;
    }
}
```

- 解法四：单调栈法
  算法思想：采用单调递减栈存储柱子的索引，遍历数组时，若当前柱子高度大于栈顶柱子高度，说明形成了可接雨水的凹槽；弹出栈顶作为凹槽底部，新栈顶为左侧边界，当前柱子为右侧边界，计算凹槽的宽度和有效高度，累加对应雨水量，重复操作直到栈恢复递减状态，再将当前索引入栈
  Java代码：
```java
import java.util.Stack;
public class Solution {
    public int trap(int[] height) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int n = height.length;
        for (int i = 0; i < n; i++) {
            // 栈不为空且当前高度大于栈顶高度，形成凹槽
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                // 计算凹槽宽度
                int width = i - stack.peek() - 1;
                // 计算有效高度
                int h = Math.min(height[i], height[stack.peek()]) - height[top];
                res += width * h;
            }
            stack.push(i);
        }
        return res;
    }
}
```

## 滑动窗口

滑动窗口算法是处理数组、字符串连续子区间问题的高效线性算法，基于双指针思想实现，核心内容整理如下：
1. 定义：滑动窗口是通过维护**左指针left**和**右指针right**形成动态连续子区间（窗口）的算法，窗口会随着指针移动实现滑动效果；该算法仅需一次遍历数组/字符串即可完成计算，时间复杂度为O(n)，能替代暴力枚举的O(n²)低效解法；窗口具备连续、边界动态调整、元素不重复遍历的核心特征，专门用于解决连续子数组/子串的最值、长度、求和、字符统计等问题，是Java中处理连续区间类题目的首选算法之一。
2. 常见操作：滑动窗口的所有操作围绕窗口初始化、扩展、收缩、结果记录展开，Java实现有标准化执行流程，具体操作如下
   - 初始化参数：定义左指针left初始值为0，声明右指针right用于遍历集合，初始化窗口状态变量（如求和用sum、字符统计用HashMap、结果存储用ans等）
   - 扩展右窗口边界：通过for/while循环移动右指针right，遍历所有元素，将当前元素加入窗口，同步更新窗口状态变量
   - 收缩左窗口边界：根据题目条件判断窗口合法性，不满足条件时循环移动左指针left，将当前元素移出窗口并更新状态，直到窗口恢复合法
   - 记录窗口结果：在窗口合法的前提下，更新目标结果（如最长子串长度、最小子数组和、窗口最大值等）
   - 遍历终止：右指针完成整个数组/字符串遍历后，算法执行结束，返回最终结果
   滑动窗口分为固定窗口和动态窗口两类，操作细节存在差异：
   - 固定长度滑动窗口操作：窗口大小固定不变，右指针移动至指定长度后，同步移动左指针保持窗口尺寸，每滑动一次计算一次窗口状态
   - 动态长度滑动窗口操作：窗口无固定大小，根据题目约束条件（如和超限、字符重复等）动态收缩左指针，窗口大小实时变化
   - Java固定窗口示例（求长度为k的连续子数组最大和）：
```java
public class SlidingWindow {
    public static int fixedWindowMaxSum(int[] nums, int k) {
        int left = 0;
        int currentSum = 0;
        int maxSum = 0;
        // 右指针遍历，扩展窗口
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            // 窗口达到固定长度，开始滑动并收缩左边界
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);
                currentSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }
}
```
   - Java动态窗口示例（求无重复字符的最长子串长度）：
```java
import java.util.HashSet;
public class SlidingWindow {
    public static int dynamicWindowMaxLength(String s) {
        int left = 0;
        int maxLen = 0;
        HashSet<Character> window = new HashSet<>();
        // 右指针遍历，扩展窗口
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            // 字符重复，动态收缩左边界
            while (window.contains(ch)) {
                window.remove(s.charAt(left));
                left++;
            }
            window.add(ch);
            // 记录合法窗口的最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

1. 题目描述：给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。示例 1: 输入: s = "abcabcbb" 输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。示例 2: 输入: s = "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。示例 3: 输入: s = "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是子串的长度，"pwke" 是一个子序列，不是子串。提示：0 <= s.length <= 5 * 104，s 由英文字母、数字、符号和空格组成。
2. 算法思想+代码
- 解法一：暴力枚举法
  算法思想：遍历字符串中所有可能的起始和结束位置，生成所有子串，逐一检查子串是否存在重复字符，记录无重复子串的最大长度。该方法实现简单，但时间复杂度为O(n²)，在字符串长度较大时效率极低。
  Java代码：
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        // 遍历所有起始位置
        for (int i = 0; i < n; i++) {
            // 标记当前子串的字符是否重复
            boolean[] visited = new boolean[128];
            // 遍历所有结束位置
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                // 字符重复，跳出当前循环
                if (visited[c]) {
                    break;
                }
                visited[c] = true;
                // 更新最大长度
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        return maxLen;
    }
}
```
- 解法二：滑动窗口（哈希集合基础版）
  算法思想：采用滑动窗口模型，用左、右指针界定当前无重复字符的子串窗口，右指针不断向右扩展窗口；当遇到重复字符时，移动左指针缩小窗口，同时用哈希集合存储窗口内的字符，实时更新无重复子串的最大长度。时间复杂度为O(n)，每个字符仅被访问两次。
  Java代码：
```java
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合存储当前窗口的字符
        Set<Character> set = new HashSet<>();
        int n = s.length();
        // 左指针
        int left = 0;
        int maxLen = 0;
        // 右指针遍历字符串
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            // 字符重复，移动左指针并移除字符
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```
- 解法三：滑动窗口（哈希数组优化版）
  算法思想：在滑动窗口基础上优化，用长度为128的数组存储字符的最新索引（覆盖所有ASCII字符），遇到重复字符时，左指针直接跳转到重复字符的下一个位置，无需逐步移动，效率更高。时间复杂度O(n)，空间复杂度O(1)。
  Java代码：
```java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 存储字符的最新索引，初始值为-1
        int[] index = new int[128];
        for (int i = 0; i < 128; i++) {
            index[i] = -1;
        }
        int n = s.length();
        int maxLen = 0;
        int left = 0;
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            // 字符重复，更新左指针
            if (index[c] >= left) {
                left = index[c] + 1;
            }
            // 更新当前字符的索引
            index[c] = right;
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
```

### [438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/)

1. 题目描述
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
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
s 和 p 仅包含小写字母

2. 算法思想+代码
- 算法思想：解法一为固定大小滑动窗口，窗口长度与字符串p的长度一致。由于字符串仅包含小写字母，使用两个长度为26的整型数组分别统计p的字符频次、s中当前滑动窗口的字符频次。遍历字符串s时，不断更新窗口内的字符频次，每次窗口滑动后对比两个频次数组，若完全相等则当前窗口的起始索引为目标答案。该方法时间复杂度O(n)，n为s的长度，空间复杂度O(1)（使用固定长度的数组，属于常数空间）。
- Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        // 边界条件：s长度小于p，直接返回空集合
        if (sLen < pLen) {
            return res;
        }

        // 统计p的字符频次和s窗口的字符频次
        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // 初始化第一个窗口
        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // 对比初始窗口
        if (isEqual(pCount, sCount)) {
            res.add(0);
        }

        // 滑动窗口遍历剩余位置
        for (int i = pLen; i < sLen; i++) {
            // 移除窗口左侧字符
            sCount[s.charAt(i - pLen) - 'a']--;
            // 添加窗口右侧新字符
            sCount[s.charAt(i) - 'a']++;
            // 对比频次数组
            if (isEqual(pCount, sCount)) {
                res.add(i - pLen + 1);
            }
        }
        return res;
    }

    // 辅助方法：判断两个频次数组是否相等
    private boolean isEqual(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
```

2. 算法思想+代码
- 算法思想：解法二为优化版滑动窗口，通过双指针动态维护窗口，引入匹配数变量记录窗口中与p字符频次匹配的字符种类数。仅统计p中存在的字符，当匹配数等于p的有效字符种类数，且窗口长度等于p的长度时，记录窗口起始索引。该方法省略了频次数组的全量对比操作，执行效率更高，时间复杂度O(n)，空间复杂度O(1)。
- Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        if (sLen < pLen) {
            return res;
        }

        int[] count = new int[26];
        // 统计p中所有字符的频次
        for (char ch : p.toCharArray()) {
            count[ch - 'a']++;
        }

        // 双指针定义窗口左右边界，match记录匹配的字符种类数
        int left = 0, match = 0;
        for (int right = 0; right < sLen; right++) {
            // 处理右侧字符
            int cur = s.charAt(right) - 'a';
            count[cur]--;
            // 该字符频次匹配，匹配数+1
            if (count[cur] == 0) {
                match++;
            }

            // 当窗口长度超过p的长度，收缩左边界
            while (right - left + 1 > pLen) {
                int leftCur = s.charAt(left) - 'a';
                // 该字符原本匹配，移除后匹配数-1
                if (count[leftCur] == 0) {
                    match--;
                }
                count[leftCur]++;
                left++;
            }

            // 所有字符种类匹配，且窗口长度符合要求，记录索引
            if (match == 26) {
                res.add(left);
            }
        }
        return res;
    }
}
```

## 子串
1. 子串定义：字符串的子串是原字符串中连续的一段字符序列，属于字符串的局部连续片段；Java中String类为不可变字符序列，所有子串相关操作都会生成新的String对象，不会修改原始字符串；子串要求字符连续，与不要求连续的子序列是不同概念，子串是特殊的子序列。
2. 子串常见操作Java实现：编写统一的操作类，将所有子串操作封装在main方法中，包含截取子串、判断包含、查找索引、替换、分割、统计次数、截取首尾子串等全部操作，代码可直接编译运行，完整代码如下
```java
public class SubstringOperation {
    // 自定义方法：统计子串在主串中出现的次数
    public static int countSubStr(String mainStr, String subStr) {
        // 边界值校验
        if (mainStr == null || subStr == null || subStr.isEmpty() || mainStr.length() < subStr.length()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        // 循环查找子串并计数
        while ((index = mainStr.indexOf(subStr, index)) != -1) {
            count++;
            // 跳过已匹配的子串，避免重复统计
            index += subStr.length();
        }
        return count;
    }

    public static void main(String[] args) {
        // 定义测试用原始字符串
        String originalStr = "HelloJavaJavaWorld";
        System.out.println("===== 子串常见操作测试 =====");
        System.out.println("原始字符串：" + originalStr);
        System.out.println("------------------------");

        // 操作1：获取指定索引范围的子串
        String sub1 = originalStr.substring(5);
        String sub2 = originalStr.substring(0, 5);
        System.out.println("1. 截取指定范围子串");
        System.out.println("从索引5截取到末尾：" + sub1);
        System.out.println("截取索引[0,5)子串：" + sub2);
        System.out.println("------------------------");

        // 操作2：判断是否包含指定子串
        boolean contains = originalStr.contains("Java");
        System.out.println("2. 判断包含子串");
        System.out.println("是否包含Java：" + contains);
        System.out.println("------------------------");

        // 操作3：查找子串首次/末次出现的索引
        int firstIndex = originalStr.indexOf("Java");
        int lastIndex = originalStr.lastIndexOf("Java");
        System.out.println("3. 查找子串索引");
        System.out.println("Java首次索引：" + firstIndex);
        System.out.println("Java末次索引：" + lastIndex);
        System.out.println("------------------------");

        // 操作4：替换指定子串
        String replaceAll = originalStr.replace("Java", "Python");
        String replaceFirst = originalStr.replaceFirst("Java", "Python");
        System.out.println("4. 替换子串");
        System.out.println("替换所有Java：" + replaceAll);
        System.out.println("替换第一个Java：" + replaceFirst);
        System.out.println("------------------------");

        // 操作5：按子串分割字符串
        String splitStr = "123,456,789,Java";
        String[] strArray = splitStr.split(",");
        System.out.println("5. 按子串分割字符串");
        System.out.print("分割结果：");
        for (String s : strArray) {
            System.out.print(s + " ");
        }
        System.out.println("\n------------------------");

        // 操作6：统计子串出现次数
        int count = countSubStr(originalStr, "Java");
        System.out.println("6. 统计子串次数");
        System.out.println("Java出现次数：" + count);
        System.out.println("------------------------");

        // 操作7：截取首尾指定长度子串
        String headSub = originalStr.substring(0, 3);
        String tailSub = originalStr.substring(originalStr.length() - 3);
        System.out.println("7. 截取首尾子串");
        System.out.println("截取前3位：" + headSub);
        System.out.println("截取后3位：" + tailSub);
    }
}
```
3. 代码运行说明：直接运行main方法即可执行所有子串操作，控制台会按顺序打印每一步操作的结果；自定义的countSubStr方法用于统计子串次数，补充了Java没有原生统计方法的场景；所有操作都遵循String不可变特性，不会修改原始字符串，仅生成新的字符串对象；代码添加了边界校验，避免空指针等异常。

### [560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/)

1. 题目描述：给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的子数组的个数。子数组是数组中元素的连续非空序列。示例 1：输入：nums = [1,1,1], k = 2，输出：2；示例 2：输入：nums = [1,2,3], k = 3，输出：2。提示：1 <= nums.length <= 2 * 10^4，-1000 <= nums[i] <= 1000，-10^7 <= k <= 10^7。
2. 解法一：暴力枚举法，算法思想：通过双层循环遍历数组中所有连续子数组，外层循环指定子数组的起始索引，内层循环从起始索引开始依次累加元素，每累加一次判断当前累加和是否等于k，若相等则将结果计数加1。该方法逻辑直观但时间复杂度为O(n²)，在数据量较大时会出现超时情况。Java代码：
```java
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        // 遍历所有子数组起始位置
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            // 遍历起始位置后的所有元素，累加计算子数组和
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                // 和等于k则计数加1
                if (currentSum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // 测试主方法
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1,1,1};
        int k1 = 2;
        System.out.println(solution.subarraySum(nums1, k1));
        int[] nums2 = {1,2,3};
        int k2 = 3;
        System.out.println(solution.subarraySum(nums2, k2));
    }
}
```
3. 解法二：前缀和+哈希表优化法，算法思想：利用前缀和原理，定义preSum为数组前i个元素的累加和，子数组[j,i-1]的和=preSum[i]-preSum[j]，令其等于k可得preSum[j]=preSum[i]-k。使用哈希表存储前缀和出现的次数，初始时前缀和0出现1次，遍历数组时计算当前前缀和，查询哈希表中preSum[i]-k的出现次数并累加到结果，最后将当前前缀和存入哈希表。该方法时间复杂度O(n)，空间复杂度O(n)，为最优解法。Java代码：
```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        // 哈希表：key=前缀和，value=该前缀和出现的次数
        Map<Integer, Integer> prefixMap = new HashMap<>();
        // 初始状态：前缀和为0，出现1次
        prefixMap.put(0, 1);
        int count = 0;
        int preSum = 0;
        for (int num : nums) {
            // 计算当前前缀和
            preSum += num;
            // 查找符合条件的前缀和数量并累加
            if (prefixMap.containsKey(preSum - k)) {
                count += prefixMap.get(preSum - k);
            }
            // 更新当前前缀和的出现次数
            prefixMap.put(preSum, prefixMap.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    // 测试主方法
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1,1,1};
        int k1 = 2;
        System.out.println("测试用例1结果：" + solution.subarraySum(nums1, k1));
        int[] nums2 = {1,2,3};
        int k2 = 3;
        System.out.println("测试用例2结果：" + solution.subarraySum(nums2, k2));
    }
}
```
### [239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/)

1. 题目描述：给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位，返回滑动窗口中的最大值。
示例 1：输入：nums = [1,3,-1,-3,5,3,6,7], k = 3，输出：[3,3,5,5,6,7]
示例 2：输入：nums = [1], k = 1，输出：[1]
提示：1 <= nums.length <= 10^5，-10^4 <= nums[i] <= 10^4，1 <= k <= nums.length

2. 算法思想+代码
解法一：暴力枚举法
- 算法思想：遍历数组中所有滑动窗口的起始位置，对每个窗口内的元素逐一遍历查找最大值并记录。该方法逻辑简单易实现，时间复杂度为O(n*k)，在数据量达到题目上限时会超时，仅适用于小规模数据场景。
- 代码：
```java
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 处理空数组特殊情况
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        // 结果数组的长度为 n - k + 1
        int[] result = new int[n - k + 1];
        // 遍历每个滑动窗口的起始索引
        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            // 遍历窗口内所有元素找最大值
            for (int j = i + 1; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }
            }
            result[i] = max;
        }
        return result;
    }
}
```

解法二：单调双端队列法（最优解法）
- 算法思想：使用双端队列维护滑动窗口内元素的索引，保证队列中索引对应的元素严格递减。遍历数组时，先移除队列中超出当前窗口左边界的元素索引；再移除队列尾部所有小于当前元素的索引，维持队列的递减性；将当前元素索引加入队列尾部；当索引大于等于k-1时，窗口正式形成，队列头部索引对应的元素就是当前窗口的最大值，将其存入结果数组。该方法每个元素仅入队和出队一次，时间复杂度为O(n)，空间复杂度为O(k)，可高效处理大规模数据。
- 代码：
```java
import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        // 双端队列存储元素的索引，保证对应元素递减
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            // 移除超出滑动窗口左边界的元素
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // 移除队列尾部小于当前元素的索引，维持递减规则
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            // 将当前元素索引加入队列尾部
            deque.offerLast(i);
            // 窗口形成后，记录队列头部的最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
```

### [76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring/)

1.题目描述
给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的最短窗口子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。测试用例保证答案唯一。
示例 1：输入：s = "ADOBECODEBANC", t = "ABC"，输出："BANC"
示例 2：输入：s = "a", t = "a"，输出："a"
示例 3: 输入: s = "a", t = "aa"，输出: ""
提示：m == s.length，n == t.length，1 <= m, n <= 10^5，s 和 t 由英文字母组成
进阶：设计一个在 O(m + n) 时间内解决此问题的算法

2.算法思想+代码
解法：滑动窗口（双指针）算法

- 算法思想：使用左右两个指针维护滑动窗口，右指针不断向右扩展窗口，直到窗口包含 t 中所有字符；之后尝试左移左指针缩小窗口，在保证窗口仍包含 t 所有字符的前提下，记录最小窗口的起始位置和长度；通过哈希表/数组统计字符出现次数，判断窗口是否满足条件，整体时间复杂度 O(m + n)，符合进阶要求。

```java
class Solution {
    public String minWindow(String s, String t) {
        // 统计字符串t和滑动窗口中的字符数量
        int[] need = new int[128];
        int[] window = new int[128];
        // 统计t中不同字符的个数
        int count = 0;
        for (char c : t.toCharArray()) {
            if (need[c] == 0) {
                count++;
            }
            need[c]++;
        }

        // 滑动窗口左右指针
        int left = 0, right = 0;
        // 记录窗口中满足t字符数量要求的字符个数
        int valid = 0;
        // 记录最小覆盖子串的起始索引和长度
        int start = 0, minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            // 移入窗口的字符
            char c = s.charAt(right);
            right++;
            // 更新窗口内数据
            if (need[c] != 0) {
                window[c]++;
                // 该字符数量达到t中的要求
                if (window[c] == need[c]) {
                    valid++;
                }
            }

            // 当窗口满足条件时，尝试收缩左边界
            while (valid == count) {
                // 更新最小覆盖子串
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                // 移出窗口的字符
                char d = s.charAt(left);
                left++;
                // 更新窗口内数据
                if (need[d] != 0) {
                    if (window[d] == need[d]) {
                        valid--;
                    }
                    window[d]--;
                }
            }
        }
        // 返回结果
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
```

## 普通数组

1. 数组定义：Java中的普通数组是存储相同数据类型元素的有序集合，属于引用数据类型，数组创建后长度固定不可修改，元素通过整数索引访问，索引起始值为0，最后一个元素的索引为数组长度减1
2. 数组常见操作
- 数组初始化（静态初始化、动态初始化）
- 数组元素遍历（普通for循环、增强for循环）
- 访问与修改指定索引的元素
- 线性查找指定目标元素
- 插入元素（数组长度固定，通过新建数组实现）
- 删除元素（通过新建数组实现）
- 数组基础排序（冒泡排序）
- 查找数组最大值、最小值
- 数组元素反转

```java
public class ArrayDemo {
    public static void main(String[] args) {
        // ==================== 1. 数组初始化 ====================
        // 静态初始化：直接指定元素值
        int[] staticArray = {10, 20, 30, 40, 50};
        // 动态初始化：指定数组长度，元素默认赋值（int类型默认0）
        int[] dynamicArray = new int[5];
        System.out.println("===== 数组初始化 =====");
        System.out.print("静态初始化数组：");
        printArray(staticArray);
        System.out.print("动态初始化数组（默认值）：");
        printArray(dynamicArray);

        // ==================== 2. 访问与修改元素 ====================
        System.out.println("\n===== 访问与修改元素 =====");
        // 访问索引为2的元素
        System.out.println("访问索引2的元素：" + staticArray[2]);
        // 修改索引为2的元素
        staticArray[2] = 33;
        System.out.print("修改后数组：");
        printArray(staticArray);

        // ==================== 3. 数组遍历 ====================
        System.out.println("\n===== 数组遍历 =====");
        // 普通for循环遍历
        System.out.print("普通for循环遍历：");
        for (int i = 0; i < staticArray.length; i++) {
            System.out.print(staticArray[i] + " ");
        }
        // 增强for循环遍历
        System.out.print("\n增强for循环遍历：");
        for (int num : staticArray) {
            System.out.print(num + " ");
        }

        // ==================== 4. 线性查找元素 ====================
        System.out.println("\n\n===== 线性查找元素 =====");
        int target = 40;
        int index = linearSearch(staticArray, target);
        if (index != -1) {
            System.out.println("元素" + target + "的索引：" + index);
        } else {
            System.out.println("未找到元素" + target);
        }

        // ==================== 5. 插入元素 ====================
        System.out.println("\n===== 插入元素 =====");
        int insertIndex = 2;
        int insertValue = 25;
        int[] insertArray = insertElement(staticArray, insertIndex, insertValue);
        System.out.print("插入元素后数组：");
        printArray(insertArray);

        // ==================== 6. 删除元素 ====================
        System.out.println("\n===== 删除元素 =====");
        int deleteIndex = 3;
        int[] deleteArray = deleteElement(insertArray, deleteIndex);
        System.out.print("删除索引" + deleteIndex + "后数组：");
        printArray(deleteArray);

        // ==================== 7. 数组排序（冒泡排序） ====================
        System.out.println("\n===== 冒泡排序 =====");
        int[] unSortArray = {5, 2, 9, 1, 5, 6};
        System.out.print("排序前数组：");
        printArray(unSortArray);
        bubbleSort(unSortArray);
        System.out.print("排序后数组：");
        printArray(unSortArray);

        // ==================== 8. 查找最大/最小值 ====================
        System.out.println("\n===== 查找最大/最小值 =====");
        int[] numArray = {12, 35, 99, 7, 54};
        System.out.println("数组最大值：" + getMax(numArray));
        System.out.println("数组最小值：" + getMin(numArray));

        // ==================== 9. 数组反转 ====================
        System.out.println("\n===== 数组反转 =====");
        int[] reverseArray = {1, 2, 3, 4, 5};
        System.out.print("反转前数组：");
        printArray(reverseArray);
        reverseArray(reverseArray);
        System.out.print("反转后数组：");
        printArray(reverseArray);
    }

    // 工具方法：打印数组
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 线性查找：返回元素索引，未找到返回-1
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 插入元素：新建数组实现插入
    public static int[] insertElement(int[] arr, int index, int value) {
        // 校验索引合法性
        if (index < 0 || index > arr.length) {
            throw new RuntimeException("索引不合法");
        }
        int[] newArr = new int[arr.length + 1];
        for (int i = 0; i < newArr.length; i++) {
            if (i < index) {
                newArr[i] = arr[i];
            } else if (i == index) {
                newArr[i] = value;
            } else {
                newArr[i] = arr[i - 1];
            }
        }
        return newArr;
    }

    // 删除元素：新建数组实现删除
    public static int[] deleteElement(int[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            throw new RuntimeException("索引不合法");
        }
        int[] newArr = new int[arr.length - 1];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[count++] = arr[i];
            }
        }
        return newArr;
    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 获取最大值
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // 获取最小值
    public static int getMin(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    // 数组反转
    public static void reverseArray(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
```

1. 数组核心特性：同数据类型、长度固定、索引从0开始，修改/插入/删除需通过新建数组实现
2. 代码覆盖数组全量常用操作，工具方法封装可直接复用，运行后可直观看到每一步操作的执行结果

### [53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/)

1. 题目描述
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。子数组是数组中的一个连续部分。
示例 1：输入：nums = [-2,1,-3,4,-1,2,1,-5,4]，输出：6，解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
示例 2：输入：nums = [1]，输出：1
示例 3：输入：nums = [5,4,-1,7,8]，输出：23
提示：1 <= nums.length <= 10^5，-10^4 <= nums[i] <= 10^4
进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

2. 解法一：Kadane算法（动态规划）
- 算法思想：该算法是求解最大子数组和的最优解法，时间复杂度为O(n)，空间复杂度为O(1)。核心逻辑是遍历数组时，维护两个关键变量，currentMax表示以当前元素结尾的连续子数组的最大和，maxSum表示全局的最大子数组和。对于数组中的每个元素，判断将其加入前一个子数组，还是以该元素作为新子数组的起点，取两者的较大值更新currentMax；同时用currentMax更新全局最大值maxSum，遍历完成后maxSum即为结果。
- Java代码：
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // 初始化当前最大和与全局最大和为数组第一个元素
        int currentMax = nums[0];
        int maxSum = nums[0];
        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 更新当前最大和：要么重新开始，要么延续之前子数组
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            // 更新全局最大和
            maxSum = Math.max(maxSum, currentMax);
        }
        return maxSum;
    }
}
```

3. 解法二：分治法
- 算法思想：采用分治的思想将问题拆解，时间复杂度为O(nlogn)，空间复杂度为O(logn)。将数组从中间位置拆分为左、右两个子数组，最大子数组和存在三种可能：完全位于左子数组、完全位于右子数组、跨越中间节点。递归求解左、右子数组的最大和，再单独计算跨越中间节点的最大子数组和，最终取三种情况的最大值作为结果。计算跨中间的和时，分别从中间向左、向右遍历累加，找到两侧的最大和后相加。
- Java代码：
```java
class Solution {
    public int maxSubArray(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    // 分治递归函数：计算nums[left...right]的最大子数组和
    private int divideAndConquer(int[] nums, int left, int right) {
        // 递归终止条件：子数组只有一个元素
        if (left == right) {
            return nums[left];
        }
        // 计算中间位置
        int mid = left + (right - left) / 2;
        // 递归求左半部分最大和
        int leftMax = divideAndConquer(nums, left, mid);
        // 递归求右半部分最大和
        int rightMax = divideAndConquer(nums, mid + 1, right);
        // 计算跨越中间的最大和
        int crossMax = crossSum(nums, left, mid, right);
        // 返回三种情况的最大值
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    // 计算跨越中间节点的最大子数组和
    private int crossSum(int[] nums, int left, int mid, int right) {
        // 计算左半部分（从mid向左）的最大和
        int leftSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = mid; i >= left; i--) {
            currentSum += nums[i];
            leftSum = Math.max(leftSum, currentSum);
        }
        // 计算右半部分（从mid+1向右）的最大和
        int rightSum = Integer.MIN_VALUE;
        currentSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            currentSum += nums[i];
            rightSum = Math.max(rightSum, currentSum);
        }
        // 跨越和为左右之和
        return leftSum + rightSum;
    }
}
```

### [56. 合并区间](https://leetcode.cn/problems/merge-intervals/)

1. 题目描述
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
示例 1：输入：intervals = [[1,3],[2,6],[8,10],[15,18]]，输出：[[1,6],[8,10],[15,18]]，解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]
示例 2：输入：intervals = [[1,4],[4,5]]，输出：[[1,5]]，解释：区间 [1,4] 和 [4,5] 可被视为重叠区间
示例 3：输入：intervals = [[4,7],[1,4]]，输出：[[1,7]]，解释：区间 [1,4] 和 [4,7] 可被视为重叠区间
提示：1 <= intervals.length <= 104，intervals[i].length == 2，0 <= starti <= endi <= 104

2. 算法思想+代码
- 算法思想：
  1. 对区间数组按照区间的起始元素进行升序排序，保证相邻区间按起始点有序排列，能够快速判断区间是否重叠
  2. 创建列表用于存储合并后的最终区间，遍历排序后的每一个区间
  3. 若结果列表为空，直接将当前区间加入列表；若列表不为空，取出列表中最后一个已合并的区间
  4. 判断当前区间与最后一个合并区间是否重叠：当前区间的起始值 ≤ 最后一个区间的结束值则判定为重叠，更新最后一个区间的结束值为两个区间结束值的最大值；若不重叠则直接将当前区间加入结果列表
  5. 遍历完成后，将存储合并区间的列表转换为二维数组返回
- Java代码：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // 处理空数组边界情况
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }
        // 按照区间的起始值升序排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 定义列表存储合并后的区间
        List<int[]> result = new ArrayList<>();
        // 遍历排序后的所有区间
        for (int[] interval : intervals) {
            int currentStart = interval[0];
            int currentEnd = interval[1];
            // 结果列表为空，直接添加第一个区间
            if (result.isEmpty()) {
                result.add(new int[]{currentStart, currentEnd});
            } else {
                // 获取结果列表中最后一个已合并的区间
                int[] lastInterval = result.get(result.size() - 1);
                int lastStart = lastInterval[0];
                int lastEnd = lastInterval[1];
                // 判断两个区间是否重叠，重叠则合并
                if (currentStart <= lastEnd) {
                    // 更新最后一个区间的结束值为最大值
                    lastInterval[1] = Math.max(lastEnd, currentEnd);
                } else {
                    // 不重叠，直接添加当前区间
                    result.add(new int[]{currentStart, currentEnd});
                }
            }
        }
        // 将列表转换为二维数组返回
        return result.toArray(new int[result.size()][]);
    }
}
```

### [189. 轮转数组](https://leetcode.cn/problems/rotate-array/)

1. 题目描述
给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
示例 1: 输入: nums = [1,2,3,4,5,6,7], k = 3 输出: [5,6,7,1,2,3,4]
示例 2: 输入：nums = [-1,-100,3,99], k = 2 输出：[3,99,-1,-100]
提示：1 <= nums.length <= 10^5，-2^31 <= nums[i] <= 2^31 - 1，0 <= k <= 10^5

2. 解法一：额外数组法
- 算法思想：创建一个与原数组长度相同的新数组，先对k取模数组长度（避免k大于数组长度导致无效轮转），遍历原数组，将原数组索引i的元素放置到新数组的(i + k) % 数组长度位置，最后将新数组的元素复制到原数组中完成轮转，空间复杂度为O(n)，时间复杂度为O(n)
- 代码：
```java
public class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        k = k % n;
        for (int i = 0; i < n; i++) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }
}
```

3. 解法二：环状替换法
- 算法思想：采用原地替换方式，数组元素的轮转形成多个环状链路，从起始索引开始，将当前元素移动到目标位置，再将目标位置的原元素作为下一个要移动的元素，循环执行直到回到起始索引；统计已替换的元素个数，个数等于数组长度时结束，空间复杂度为O(1)，时间复杂度为O(n)
- 代码：
```java
public class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = 0;
        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
}
```

4. 解法三：三次反转法
- 算法思想：通过三次原地反转实现最优原地轮转，步骤为反转整个数组、反转前k个元素、反转从k到末尾的元素，先对k取模数组长度，空间复杂度为O(1)，时间复杂度为O(n)
- 代码：
```java
public class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```

### [238. 除了自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self/)

1. 题目描述
给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
示例 1:输入: nums = [1,2,3,4] 输出: [24,12,8,6]
示例 2:输入: nums = [-1,1,0,-3,3] 输出: [0,0,9,0,0]
提示：2 <= nums.length <= 105，-30 <= nums[i] <= 30，输入 保证 数组 answer[i] 在 32 位 整数范围内
进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）

2. 算法思想+代码
- 解法一：左右乘积数组法（空间复杂度O(n)）
  算法思想：分别构建左乘积数组和右乘积数组，左乘积数组left[i]存储nums数组中索引i左侧所有元素的乘积，右乘积数组right[i]存储nums数组中索引i右侧所有元素的乘积；最终结果数组answer[i]等于left[i]与right[i]的乘积。该方法时间复杂度为O(n)，需要遍历数组三次，空间复杂度为O(n)，需要额外开辟两个数组存储左右乘积。
  Java代码：
  
  ```java
  class Solution {
      public int[] productExceptSelf(int[] nums) {
          int n = nums.length;
          // 定义左乘积数组、右乘积数组、结果数组
          int[] left = new int[n];
          int[] right = new int[n];
          int[] answer = new int[n];
          
          // 初始化左数组第一个元素为1（左侧无元素）
          left[0] = 1;
          // 正向遍历填充左乘积数组
          for (int i = 1; i < n; i++) {
              left[i] = left[i-1] * nums[i-1];
          }
          
          // 初始化右数组最后一个元素为1（右侧无元素）
          right[n-1] = 1;
          // 反向遍历填充右乘积数组
          for (int i = n-2; i >= 0; i--) {
              right[i] = right[i+1] * nums[i+1];
          }
          
          // 计算最终结果
          for (int i = 0; i < n; i++) {
              answer[i] = left[i] * right[i];
          }
          return answer;
      }
  }
  ```
  
- 解法二：原地修改法（进阶，O(1)额外空间）
  算法思想：利用结果数组作为左乘积数组，先正向遍历存储每个位置的左侧乘积；再定义一个变量存储右侧实时乘积，反向遍历数组，将变量与结果数组当前值相乘，更新为最终结果，同时更新变量为当前元素与变量的乘积。输出数组不视为额外空间，因此额外空间复杂度为O(1)，时间复杂度为O(n)，仅需遍历数组两次。
  Java代码：
  ```java
  class Solution {
      public int[] productExceptSelf(int[] nums) {
          int n = nums.length;
          // 结果数组，用于存储左乘积，最终存储答案
          int[] answer = new int[n];
          
          // 正向遍历，填充左乘积
          answer[0] = 1;
          for (int i = 1; i < n; i++) {
              answer[i] = answer[i-1] * nums[i-1];
          }
          
          // 反向遍历，用变量存储右乘积，更新结果
          int right = 1;
          for (int i = n-1; i >= 0; i--) {
              answer[i] = answer[i] * right;
              // 更新右乘积，包含当前nums元素，供左侧元素使用
              right = right * nums[i];
          }
          
          return answer;
      }
  }
  ```

### [41. 缺失的第一个正数](https://leetcode.cn/problems/first-missing-positive/)

1. 题目描述
给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
示例 1：输入：nums = [1,2,0]，输出：3，解释：范围 [1,2] 中的数字都在数组中。
示例 2：输入：nums = [3,4,-1,1]，输出：2，解释：1 在数组中，但 2 没有。
示例 3：输入：nums = [7,8,9,11,12]，输出：1，解释：最小的正数 1 没有出现。
提示：1 <= nums.length <= 105，-2^31 <= nums[i] <= 2^31 - 1

2. 算法思想+代码
- 解法一：哈希表法（时间复杂度O(n)，空间复杂度O(n)）
  算法思想：缺失的最小正整数一定在区间 [1, 数组长度n+1] 内，首先遍历数组将所有正整数存入哈希集合，再从1开始依次校验正整数是否存在于集合中，第一个不存在的正整数即为最终答案。
  Java代码：
```java
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        // 存储数组中的所有正整数
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        // 从1开始查找缺失的最小正整数
        for (int i = 1; i <= n + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}
```

- 解法二：原地哈希法（时间复杂度O(n)，空间复杂度O(1)，满足题目最优要求）
  算法思想：直接将原数组作为哈希表，规定数值为i的正整数应存放在下标i-1的位置。遍历数组，把1~n范围内的正整数交换到对应下标位置；完成交换后再次遍历数组，第一个下标i对应元素不等于i+1的，i+1就是答案；若所有元素都匹配，答案为数组长度+1。该方法无需额外辅助空间，满足常数空间要求。
  Java代码：
```java
public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 将符合条件的正整数交换到对应下标位置
        for (int i = 0; i < n; i++) {
            // 仅处理1~n的数，且目标位置元素不重复时交换
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // 查找第一个缺失的正整数
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 数组包含1~n所有数时，返回n+1
        return n + 1;
    }
}
```
##  矩阵

1. 矩阵定义：矩阵是由m×n个数值按照行和列排列组成的矩形数表，称为m行n列矩阵（m×n矩阵），矩阵中的每个数值称为元素，Java中使用二维数组存储矩阵元素，行下标和列下标从0开始计数；当m=n时，该矩阵为n阶方阵，是常用的特殊矩阵形式。
2. 矩阵常见操作
- 矩阵的创建与初始化
- 矩阵格式化打印输出
- 同型矩阵加法运算
- 同型矩阵减法运算
- 矩阵乘法运算（前行数与后列数匹配）
- 矩阵转置运算
- 二阶方阵行列式计算
- 二阶方阵逆矩阵求解
- 矩阵元素的修改、遍历与查找

以下代码完整实现上述所有矩阵操作，包含异常校验和测试用例，可直接运行：
```java
public class MatrixOperationDemo {

    // 1. 打印矩阵（格式化输出）
    public static void printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("矩阵为空");
            return;
        }
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    // 2. 矩阵加法（仅同型矩阵可运算）
    public static int[][] addMatrix(int[][] a, int[][] b) {
        // 校验矩阵维度
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("加法要求两个矩阵为同型矩阵");
        }
        int rows = a.length;
        int cols = a[0].length;
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = a[i][j] + b[i][j];
            }
        }
        return res;
    }

    // 3. 矩阵减法（仅同型矩阵可运算）
    public static int[][] subMatrix(int[][] a, int[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("减法要求两个矩阵为同型矩阵");
        }
        int rows = a.length;
        int cols = a[0].length;
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = a[i][j] - b[i][j];
            }
        }
        return res;
    }

    // 4. 矩阵乘法（第一个矩阵列数 = 第二个矩阵行数）
    public static int[][] mulMatrix(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("乘法要求第一个矩阵列数等于第二个矩阵行数");
        }
        int rows = a.length;
        int cols = b[0].length;
        int mid = b.length;
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < mid; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    // 5. 矩阵转置（行变列、列变行）
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }

    // 6. 二阶方阵行列式计算
    public static int calcDeterminant2Order(int[][] matrix) {
        if (matrix.length != 2 || matrix[0].length != 2) {
            throw new IllegalArgumentException("仅支持二阶方阵行列式计算");
        }
        // 二阶行列式公式：ad - bc
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    // 7. 二阶方阵求逆（行列式≠0时可逆）
    public static double[][] inverse2Order(int[][] matrix) {
        int det = calcDeterminant2Order(matrix);
        if (det == 0) {
            throw new IllegalArgumentException("行列式为0，矩阵不可逆");
        }
        double[][] res = new double[2][2];
        // 二阶逆矩阵公式：1/|A| * [[d, -b], [-c, a]]
        res[0][0] = (double) matrix[1][1] / det;
        res[0][1] = (double) -matrix[0][1] / det;
        res[1][0] = (double) -matrix[1][0] / det;
        res[1][1] = (double) matrix[0][0] / det;
        return res;
    }

    // 8. 修改矩阵指定位置元素
    public static void updateElement(int[][] matrix, int row, int col, int value) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            throw new IllegalArgumentException("下标越界");
        }
        matrix[row][col] = value;
    }

    // 打印浮点型矩阵（用于逆矩阵输出）
    public static void printDoubleMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double num : row) {
                System.out.printf("%.2f\t", num);
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    // 主方法：测试所有矩阵操作
    public static void main(String[] args) {
        // 初始化测试矩阵
        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{5, 6}, {7, 8}};
        int[][] matrixC = {{1, 2, 3}, {4, 5, 6}};

        System.out.println("矩阵A：");
        printMatrix(matrixA);

        System.out.println("矩阵B：");
        printMatrix(matrixB);

        // 加法运算
        System.out.println("A + B：");
        printMatrix(addMatrix(matrixA, matrixB));

        // 减法运算
        System.out.println("A - B：");
        printMatrix(subMatrix(matrixA, matrixB));

        // 乘法运算
        System.out.println("A * B：");
        printMatrix(mulMatrix(matrixA, matrixB));

        // 矩阵转置
        System.out.println("矩阵C转置：");
        printMatrix(transposeMatrix(matrixC));

        // 二阶行列式
        System.out.println("矩阵A的行列式值：" + calcDeterminant2Order(matrixA));
        System.out.println("------------------------");

        // 二阶逆矩阵
        System.out.println("矩阵A的逆矩阵：");
        printDoubleMatrix(inverse2Order(matrixA));

        // 修改元素
        updateElement(matrixA, 0, 0, 10);
        System.out.println("修改A[0][0]为10后的矩阵A：");
        printMatrix(matrixA);
    }
}
```

### [73. 矩阵置零](https://leetcode.cn/problems/set-matrix-zeroes/)

1. 题目描述
给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用原地算法。
示例 1：
输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
输出：[[1,0,1],[0,0,0],[1,0,1]]
示例 2：
输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
提示：m == matrix.length，n == matrix[0].length，1 <= m, n <= 200，-2³¹ <= matrix[i][j] <= 2³¹ - 1
进阶：一个直观的解决方案是使用 O(mn) 的额外空间，一个简单的改进方案是使用 O(m + n) 的额外空间，需实现仅使用常量空间的解决方案

2. 解法一：O(m + n) 额外空间解法
- 算法思想：创建两个布尔类型的标记数组，分别记录需要置零的行和列。第一次遍历矩阵，将存在0元素的行和列在标记数组中标记为true；第二次遍历矩阵，根据标记数组将对应行和列的所有元素置为0。该方法时间复杂度为O(mn)，空间复杂度为O(m+n)，逻辑简单易理解。
- Java代码：
```java
public class Solution {
    public void setZeroes(int[][] matrix) {
        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;
        // 标记需要置零的行
        boolean[] row = new boolean[m];
        // 标记需要置零的列
        boolean[] col = new boolean[n];

        // 第一次遍历：标记所有含0的行和列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        // 第二次遍历：根据标记置零
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当前行或列被标记，则置为0
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

3. 解法二：O(1) 常量空间原地解法
- 算法思想：利用矩阵自身的第一行和第一列作为标记空间，替代额外的数组，实现常量级空间复杂度。首先单独标记第一行和第一列是否存在0；遍历矩阵除第一行第一列外的所有元素，若元素为0，将对应第一行的列位置、第一列的行位置标记为0；根据第一行和第一列的标记，将对应行和列置零；最后根据初始标记，决定是否将第一行和第一列整体置零。该方法时间复杂度为O(mn)，空间复杂度为O(1)，满足进阶的原地最优要求。
- Java代码：
```java
public class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 标记第一行是否有0
        boolean row0 = false;
        // 标记第一列是否有0
        boolean col0 = false;

        // 检查第一行是否存在0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                row0 = true;
                break;
            }
        }

        // 检查第一列是否存在0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
                break;
            }
        }

        // 遍历除第一行第一列外的元素，标记对应行和列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 根据第一列的标记，置零对应行
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 根据第一行的标记，置零对应列
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 若第一行有0，置零第一行
        if (row0) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // 若第一列有0，置零第一列
        if (col0) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
```

### [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

1. 题目描述
给你一个 m 行 n 列的矩阵 matrix，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
提示：
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100

2. 解法一：边界模拟法
- 算法思想：通过定义矩阵的上、下、左、右四个边界，按照顺时针方向依次遍历矩阵的四条边，每遍历完一条边就收缩对应的边界，当边界发生交叉时停止遍历。具体遍历顺序：从左到右遍历上边界，上边界向下收缩；从上到下遍历右边界，右边界向左收缩；若上下边界未交叉，从右到左遍历下边界，下边界向上收缩；若左右边界未交叉，从下到上遍历左边界，左边界向右收缩，循环执行该过程直至所有元素被遍历。
- 代码
```java
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // 矩阵为空的边界处理
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        // 初始化上下左右四个边界
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (true) {
            // 从左到右遍历上边界
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            // 边界交叉则退出循环
            if (top > bottom) break;

            // 从上到下遍历右边界
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (left > right) break;

            // 从右到左遍历下边界
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            bottom--;
            if (top > bottom) break;

            // 从下到上遍历左边界
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;
        }
        return res;
    }

    // 测试方法
    public static void main(String[] args) {
        SpiralMatrix solution = new SpiralMatrix();
        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(solution.spiralOrder(matrix1));
        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(solution.spiralOrder(matrix2));
    }
}
```

3. 解法二：方向数组法
- 算法思想：定义顺时针的四个移动方向（右、下、左、上），用二维数组存储行和列的偏移量；初始化当前遍历的行、列索引和方向索引，创建标记数组记录元素是否被访问过；循环遍历矩阵元素，将当前元素加入结果集并标记为已访问，判断下一个位置是否越界或已访问，若是则切换方向，否则移动到下一个位置，直至遍历完所有元素。
- 代码
```java
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix2 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // 矩阵为空的边界处理
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 标记数组：记录元素是否被访问过
        boolean[][] visited = new boolean[m][n];
        // 定义四个方向：右、下、左、上，存储行和列的偏移量
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int total = m * n;
        // 当前遍历的行、列索引
        int row = 0, col = 0;
        // 当前方向索引
        int dirIndex = 0;

        for (int i = 0; i < total; i++) {
            // 将当前元素加入结果集
            res.add(matrix[row][col]);
            visited[row][col] = true;
            // 计算下一个要遍历的位置
            int nextRow = row + dirs[dirIndex][0];
            int nextCol = col + dirs[dirIndex][1];
            // 判断下一个位置是否越界或已访问，若是则切换方向
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]) {
                dirIndex = (dirIndex + 1) % 4;
            }
            // 移动到下一个合法位置
            row += dirs[dirIndex][0];
            col += dirs[dirIndex][1];
        }
        return res;
    }

    // 测试方法
    public static void main(String[] args) {
        SpiralMatrix2 solution = new SpiralMatrix2();
        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(solution.spiralOrder(matrix1));
        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(solution.spiralOrder(matrix2));
    }
}
```

### [48. 旋转图像](https://leetcode.cn/problems/rotate-image/)

1. 题目描述
    给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
    输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
    输出：[[7,4,1],[8,5,2],[9,6,3]]
    输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
    输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
    提示：n == matrix.length == matrix[i].length，1 <= n <= 20，-1000 <= matrix[i][j] <= 1000

1. 算法思想+代码

- 解法一：转置矩阵后反转每行
  算法思想：顺时针旋转90度可通过两步原地操作实现，第一步对矩阵进行转置（将matrix[i][j]与matrix[j][i]交换），第二步将转置后的矩阵每一行元素反转，两步完成后即为旋转结果，时间复杂度O(n²)，空间复杂度O(1)
  代码
  
  ```java
  public class RotateImage {
      public void rotate(int[][] matrix) {
          int n = matrix.length;
          // 矩阵转置：行和列互换
          for (int i = 0; i < n; i++) {
              for (int j = i; j < n; j++) {
                  int temp = matrix[i][j];
                  matrix[i][j] = matrix[j][i];
                  matrix[j][i] = temp;
              }
          }
          // 反转每一行元素
          for (int i = 0; i < n; i++) {
              for (int j = 0; j < n / 2; j++) {
                  int temp = matrix[i][j];
                  matrix[i][j] = matrix[i][n - 1 - j];
                  matrix[i][n - 1 - j] = temp;
              }
          }
      }
  }
  ```
  测试用例1：matrix = [[1,2,3],[4,5,6],[7,8,9]]
  测试用例2：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
  测试结果1：[[7,4,1],[8,5,2],[9,6,3]]
  测试结果2：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
  
- 解法二：分层原地交换元素
  算法思想：将矩阵按层划分，从最外层到最内层依次处理，每层中按顺时针方向交换四个对应位置的元素，直接原地修改矩阵，无需额外空间，时间复杂度O(n²)，空间复杂度O(1)
  代码
  ```java
  public class RotateImage {
      public void rotate(int[][] matrix) {
          int n = matrix.length;
          // 遍历矩阵的每一层，从外层到内层
          for (int i = 0; i < n / 2; i++) {
              int start = i;
              int end = n - 1 - i;
              // 遍历当前层的元素，执行四个位置的交换
              for (int j = start; j < end; j++) {
                  // 保存顶部元素
                  int temp = matrix[start][j];
                  // 左侧元素赋值到顶部
                  matrix[start][j] = matrix[end - j + start][start];
                  // 底部元素赋值到左侧
                  matrix[end - j + start][start] = matrix[end][end - j + start];
                  // 右侧元素赋值到底部
                  matrix[end][end - j + start] = matrix[j][end];
                  // 顶部元素赋值到右侧
                  matrix[j][end] = temp;
              }
          }
      }
  }
  ```
  测试用例1：matrix = [[1,2,3],[4,5,6],[7,8,9]]
  测试用例2：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
  测试结果1：[[7,4,1],[8,5,2],[9,6,3]]
  测试结果2：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

### [240. 搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii/)

1. 题目描述
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：每行的元素从左到右升序排列，每列的元素从上到下升序排列。
示例 1：输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5 输出：true
示例 2：输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20 输出：false
提示：m == matrix.length，n == matrix[i].length，1 <= n, m <= 300，-10^9 <= matrix[i][j] <= 10^9 ，每行的所有元素从左到右升序排列，每列的所有元素从上到下升序排列，-10^9 <= target <= 10^9
2. 算法思想+代码
- 解法一：暴力遍历法
  算法思想：通过双重for循环遍历矩阵中的每一个元素，将每个元素与目标值target进行比对，若找到相等元素则返回true，遍历完成后未找到则返回false，该方法实现简单但时间复杂度较高。
  Java代码：
  
  ```java
  class Solution {
      public boolean searchMatrix(int[][] matrix, int target) {
          // 获取矩阵的行数
          int m = matrix.length;
          // 矩阵为空直接返回false
          if (m == 0) return false;
          // 获取矩阵的列数
          int n = matrix[0].length;
          // 双重循环遍历所有元素
          for (int i = 0; i < m; i++) {
              for (int j = 0; j < n; j++) {
                  // 找到目标值返回true
                  if (matrix[i][j] == target) {
                      return true;
                  }
              }
          }
          // 遍历结束未找到返回false
          return false;
      }
  }
  ```
- 解法二：每行二分查找法
  算法思想：利用矩阵每行元素从左到右升序的特性，对矩阵的每一行单独执行二分查找算法，逐行判断目标值是否存在，相比暴力法减少了查找次数，时间复杂度为O(m log n)。
  Java代码：
  ```java
  class Solution {
      public boolean searchMatrix(int[][] matrix, int target) {
          int m = matrix.length;
          if (m == 0) return false;
          int n = matrix[0].length;
          // 遍历每一行
          for (int[] row : matrix) {
              // 对当前行执行二分查找
              int left = 0, right = n - 1;
              while (left <= right) {
                  int mid = left + (right - left) / 2;
                  if (row[mid] == target) {
                      return true;
                  } else if (row[mid] < target) {
                      // 目标值更大，查找右半部分
                      left = mid + 1;
                  } else {
                      // 目标值更小，查找左半部分
                      right = mid - 1;
                  }
              }
          }
          return false;
      }
  }
  ```
- 解法三：线性查找法（最优解）
  算法思想：利用矩阵行升序、列升序的双重有序特性，选择矩阵右上角元素作为查找起点，若当前元素大于target，说明目标值在当前元素左侧，列索引减一；若当前元素小于target，说明目标值在当前元素下方，行索引加一；若相等则找到目标值，该方法时间复杂度为O(m+n)，空间复杂度为O(1)。
  Java代码：
  ```java
  class Solution {
      public boolean searchMatrix(int[][] matrix, int target) {
          int m = matrix.length;
          if (m == 0) return false;
          int n = matrix[0].length;
          // 初始化起点为右上角：行索引0，列索引n-1
          int row = 0, col = n - 1;
          // 边界条件：行不超过总行数，列不小于0
          while (row < m && col >= 0) {
              if (matrix[row][col] == target) {
                  // 找到目标值
                  return true;
              } else if (matrix[row][col] > target) {
                  // 当前值过大，向左移动
                  col--;
              } else {
                  // 当前值过小，向下移动
                  row++;
              }
          }
          // 遍历结束未找到
          return false;
      }
  }
  ```

## 链表

1. 定义：单链表是线性表的链式存储实现形式，由多个独立的节点构成，每个节点包含两个核心部分，分别是存储业务数据的数据域、指向下一个节点的引用指针域，节点在内存中无需连续存储，通过指针域串联成完整链表，与数组相比，链表的节点插入、删除操作更高效，但不支持随机访问节点。
2. 常见操作
- 定义链表节点结构，封装数据和后继节点引用
- 初始化空的单链表
- 头插法：在链表头部新增节点
- 尾插法：在链表尾部追加节点
- 遍历链表：依次输出所有节点的数据
- 计算链表的有效长度
- 按数据值查找节点是否存在
- 按节点位置修改数据值
- 按数据值删除指定节点
- 清空整个链表

```java
/**
 * 单链表Demo：实现链表的所有常用基础操作
 */
public class SingleLinkedListDemo {
    // 链表的头节点（不存储具体数据，仅作为链表入口）
    private Node head;

    // 1. 定义链表节点类（内部静态类）
    private static class Node {
        // 数据域：存储数据
        int data;
        // 指针域：指向下一个节点
        Node next;

        // 节点构造方法
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // 构造方法：初始化空链表
    public SingleLinkedListDemo() {
        this.head = new Node(0);
    }

    // 2. 头插法：在链表头部插入节点
    public void addFirst(int data) {
        Node newNode = new Node(data);
        // 新节点指向原头节点的下一个节点
        newNode.next = head.next;
        // 头节点指向新节点
        head.next = newNode;
    }

    // 3. 尾插法：在链表尾部追加节点
    public void addLast(int data) {
        Node newNode = new Node(data);
        // 临时节点，用于遍历到链表尾部
        Node temp = head;
        // 遍历到最后一个节点
        while (temp.next != null) {
            temp = temp.next;
        }
        // 最后一个节点指向新节点
        temp.next = newNode;
    }

    // 4. 遍历链表：打印所有节点数据
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        Node temp = head.next;
        System.out.print("链表元素：");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // 5. 获取链表有效长度
    public int getLength() {
        int length = 0;
        Node temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    // 6. 按值查找节点：判断数据是否存在
    public boolean exists(int data) {
        Node temp = head.next;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // 7. 按索引修改节点数据（索引从0开始）
    public void update(int index, int newData) {
        if (index < 0 || index >= getLength()) {
            System.out.println("索引越界，修改失败！");
            return;
        }
        Node temp = head.next;
        // 遍历到目标索引节点
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = newData;
        System.out.println("修改成功！");
    }

    // 8. 按值删除节点（删除第一个匹配的节点）
    public void delete(int data) {
        Node temp = head;
        // 找到待删除节点的前一个节点
        while (temp.next != null && temp.next.data != data) {
            temp = temp.next;
        }
        // 未找到节点
        if (temp.next == null) {
            System.out.println("未找到该数据，删除失败！");
            return;
        }
        // 跳过待删除节点，完成删除
        temp.next = temp.next.next;
        System.out.println("删除成功！");
    }

    // 9. 清空链表
    public void clear() {
        head.next = null;
        System.out.println("链表已清空！");
    }

    // 主方法：测试所有链表操作
    public static void main(String[] args) {
        // 初始化空链表
        SingleLinkedListDemo list = new SingleLinkedListDemo();

        // 测试尾插法
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.list(); // 输出：链表元素：10 20 30

        // 测试头插法
        list.addFirst(5);
        list.list(); // 输出：链表元素：5 10 20 30

        // 测试获取长度
        System.out.println("链表长度：" + list.getLength()); // 输出：4

        // 测试查找节点
        System.out.println("是否存在20：" + list.exists(20)); // 输出：true
        System.out.println("是否存在40：" + list.exists(40)); // 输出：false

        // 测试修改节点
        list.update(1, 15);
        list.list(); // 输出：链表元素：5 15 20 30

        // 测试删除节点
        list.delete(20);
        list.list(); // 输出：链表元素：5 15 30

        // 测试清空链表
        list.clear();
        list.list(); // 输出：链表为空！
    }
}
```

### [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

1. 题目描述
给你两个单链表的头节点 headA 和 headB，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null。题目数据保证整个链式结构中不存在环，函数返回结果后，链表必须保持其原始结构。
自定义评测：评测系统的输入为intersectVal（相交的起始节点的值，不存在相交节点则为0）、listA（第一个链表）、listB（第二个链表）、skipA（在listA中从头节点开始跳到交叉节点的节点数）、skipB（在listB中从头节点开始跳到交叉节点的节点数），评测系统将根据这些输入创建链式数据结构，并将两个头节点传递给程序，程序能正确返回相交节点则为正确答案。
示例1：输入intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3，输出Intersected at '8'
示例2：输入intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1，输出Intersected at '2'
示例3：输入intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2，输出No intersection
提示：listA中节点数目为m，listB中节点数目为n，1 <= m, n <= 3 * 104，1 <= Node.val <= 105，0 <= skipA <= m，0 <= skipB <= n；如果两个链表没有交点，intersectVal为0；如果有交点，intersectVal == listA[skipA] == listB[skipB]
进阶：设计时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案
2. 算法思想+代码
- 解法一：哈希集合解法
  算法思想：首先遍历第一个链表A，将所有节点存入哈希集合中，利用哈希集合快速判断元素存在性的特性；接着遍历第二个链表B，逐个检查当前节点是否存在于哈希集合中，第一个匹配的节点就是相交起始节点；若遍历完成未找到则返回null。该方法时间复杂度O(m + n)，空间复杂度O(m)。
  Java代码
  ```java
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) {
   *         val = x;
   *         next = null;
   *     }
   * }
   */
  import java.util.HashSet;
  import java.util.Set;
  public class Solution {
      public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
          Set<ListNode> nodeSet = new HashSet<>();
          // 遍历链表A，将所有节点加入集合
          ListNode curA = headA;
          while (curA != null) {
              nodeSet.add(curA);
              curA = curA.next;
          }
          // 遍历链表B，查找相交节点
          ListNode curB = headB;
          while (curB != null) {
              if (nodeSet.contains(curB)) {
                  return curB;
              }
              curB = curB.next;
          }
          // 无相交节点返回null
          return null;
      }
  }
  ```
- 解法二：双指针解法（进阶最优解）
  算法思想：定义两个指针分别指向链表A、B的头节点，同步向后遍历；指针遍历到当前链表末尾时，切换到另一个链表的头节点继续遍历。两个指针走过的总长度均为m+n，会在相交节点相遇（有相交），或同时指向null（无相交）。该方法时间复杂度O(m + n)，空间复杂度O(1)，满足进阶要求。
  Java代码
  ```java
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) {
   *         val = x;
   *         next = null;
   *     }
   * }
   */
  public class Solution {
      public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
          // 边界判断：任一链表为空，直接无相交
          if (headA == null || headB == null) {
              return null;
          }
          ListNode curA = headA;
          ListNode curB = headB;
          // 指针不相遇则持续遍历
          while (curA != curB) {
              // 指针A遍历完A链表，指向B链表头节点
              curA = curA == null ? headB : curA.next;
              // 指针B遍历完B链表，指向A链表头节点
              curB = curB == null ? headA : curB.next;
          }
          // 返回相遇节点（相交节点或null）
          return curA;
      }
  }
  ```

**注意：**假设链表 A 长度 = 3，链表 B 长度 = 2，**无相交**：

- curA 路径：A1 → A2 → A3 → **null** → B1 → B2 → **null**
- curB 路径：B1 → B2 → **null** → A1 → A2 → A3 → **null**

### [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)

1. 题目描述
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
示例 1：输入：head = [1,2,3,4,5]，输出：[5,4,3,2,1]
示例 2：输入：head = [1,2]，输出：[2,1]
示例 3：输入：head = []，输出：[]
提示：链表中节点的数目范围是 [0, 5000]，-5000 <= Node.val <= 5000
进阶：链表可以选用迭代或递归方式完成反转，需要用两种方法解决该题

2. 算法思想+代码
- 解法一：迭代法
  - 算法思想：迭代法通过双指针遍历链表，定义前驱节点prev初始为null，当前节点curr初始为head；遍历过程中，先保存curr的下一个节点next，再将curr的next指向prev，然后将prev和curr依次后移，直到curr为null，此时prev即为反转后的链表头节点。
  - 代码：
```java
public ListNode reverseList(ListNode head) {
    // 前驱节点，初始为null
    ListNode prev = null;
    // 当前遍历节点
    ListNode curr = head;
    while (curr != null) {
        // 保存当前节点的下一个节点
        ListNode next = curr.next;
        // 反转当前节点的指向
        curr.next = prev;
        // 前驱节点后移
        prev = curr;
        // 当前节点后移
        curr = next;
    }
    // 最终prev为反转后的头节点
    return prev;
}
```
- 解法二：递归法
  - 算法思想：递归法利用递归回溯特性，递归终止条件为head为null或head.next为null（到达链表最后一个节点）；递归调用reverseList(head.next)获取反转后的新头节点，回溯时将head.next的next指向head，再将head.next置空断开原指向，避免形成环，最终返回新头节点。
  - 代码：
```java
public ListNode reverseList(ListNode head) {
    // 递归终止条件：节点为空或只有一个节点
    if (head == null || head.next == null) {
        return head;
    }
    // 递归反转后续链表，获取新头节点
    ListNode newHead = reverseList(head.next);
    // 反转当前节点与下一个节点的指向
    head.next.next = head;
    // 断开原指向，防止环
    head.next = null;
    // 返回新头节点
    return newHead;
}
```

3. 完整可运行代码
```java
// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseLinkedList {
    // 解法一：迭代法
    public ListNode reverseListByIteration(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // 解法二：递归法
    public ListNode reverseListByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 打印链表（辅助测试）
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // 主函数测试
    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        // 构建测试用例链表
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head2 = new ListNode(1, new ListNode(2));
        ListNode head3 = null;

        // 测试迭代法
        System.out.println("===== 迭代法测试 =====");
        ListNode res1 = solution.reverseListByIteration(head1);
        ListNode res2 = solution.reverseListByIteration(head2);
        ListNode res3 = solution.reverseListByIteration(head3);
        printList(res1);
        printList(res2);
        printList(res3);

        // 重新构建链表（因为迭代后原链表已改变）
        head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head2 = new ListNode(1, new ListNode(2));
        // 测试递归法
        System.out.println("===== 递归法测试 =====");
        res1 = solution.reverseListByRecursion(head1);
        res2 = solution.reverseListByRecursion(head2);
        res3 = solution.reverseListByRecursion(head3);
        printList(res1);
        printList(res2);
        printList(res3);
    }
}
```

4. 测试用例
- 测试用例1：head = [1,2,3,4,5]
- 测试用例2：head = [1,2]
- 测试用例3：head = []

5. 测试结果
===== 迭代法测试 =====
5 4 3 2 1 
2 1 

### [234. 回文链表](https://leetcode.cn/problems/palindrome-linked-list/)

1. 题目描述
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
示例 1：输入：head = [1,2,2,1]，输出：true
示例 2：输入：head = [1,2]，输出：false
提示：链表中节点数目在范围[1, 10^5] 内，0 <= Node.val <= 9
进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

2. 解法一：数组转换+双指针法
- 算法思想：将单链表的所有节点值依次存入数组，借助数组可随机访问的特性，用左指针指向数组头部、右指针指向数组尾部，双指针向中间移动并逐一比较元素值，全部相等则为回文链表。时间复杂度O(n)，空间复杂度O(n)。
```java
import java.util.ArrayList;
import java.util.List;

// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        // 存储链表节点值
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        // 遍历链表，将节点值加入集合
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        // 双指针比较
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

3. 解法二：快慢指针+反转后半链表（进阶最优解）
- 算法思想：通过快慢指针定位链表中间节点（快指针走两步、慢指针走一步），反转链表后半部分，再同时遍历前半部分和反转后的后半部分，逐一比较节点值，全部相等则为回文链表。时间复杂度O(n)，空间复杂度O(1)，满足进阶要求。
```java
import java.util.ArrayList;
import java.util.List;

// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 快慢指针找中间节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转后半部分链表
        ListNode secondHalf = reverseList(slow.next);
        ListNode firstHalf = head;
        // 比较前后两部分
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    // 反转链表辅助方法
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
```

### [141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)

1. 题目描述
给你一个链表的头节点 head ，判断链表中是否有环。如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 ，仅仅是为了标识链表的实际情况。如果链表中存在环 ，则返回 true ，否则返回 false 。
示例 1：输入：head = [3,2,0,-4], pos = 1，输出：true，解释：链表中有一个环，其尾部连接到第二个节点。
示例 2：输入：head = [1,2], pos = 0，输出：true，解释：链表中有一个环，其尾部连接到第一个节点。
示例 3：输入：head = [1], pos = -1，输出：false，解释：链表中没有环。
提示：链表中节点的数目范围是 [0, 104]，-105 <= Node.val <= 105，pos 为 -1 或者链表中的一个有效索引。
进阶：你能用 O(1)（即常量）内存解决此问题吗？

2. 解法一：哈希表法
- 算法思想：遍历链表的所有节点，使用哈希集合存储已经访问过的节点，每遍历一个节点就判断该节点是否存在于集合中。若存在，说明链表有环；若遍历到节点为null，说明链表无环。该方法时间复杂度为O(n)，空间复杂度为O(n)。
- Java代码：
```java
import java.util.HashSet;
import java.util.Set;

// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        // 存储已访问的节点
        Set<ListNode> visited = new HashSet<>();
        while (head != null) {
            // 当前节点已访问过，存在环
            if (visited.contains(head)) {
                return true;
            }
            // 将当前节点加入集合
            visited.add(head);
            // 移动到下一个节点
            head = head.next;
        }
        // 遍历到链表尾部，无环
        return false;
    }
}
```

3. 解法二：快慢指针法（双指针法）
- 算法思想：定义两个指针，慢指针每次移动1步，快指针每次移动2步，同时从链表头节点开始遍历。如果链表存在环，快指针最终会追上慢指针并相遇；如果快指针或快指针的下一个节点为null，说明链表到达尾部，无环。该方法时间复杂度为O(n)，空间复杂度为O(1)，满足进阶的常量内存要求。
- Java代码：
```java
// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        // 链表为空或只有一个节点，无环
        if (head == null || head.next == null) {
            return false;
        }
        // 慢指针
        ListNode slow = head;
        // 快指针
        ListNode fast = head.next;
        while (slow != fast) {
            // 快指针到达链表尾部，无环
            if (fast == null || fast.next == null) {
                return false;
            }
            // 慢指针走1步
            slow = slow.next;
            // 快指针走2步
            fast = fast.next.next;
        }
        // 指针相遇，存在环
        return true;
    }
}
```

### [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)

1. 题目描述
给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回 null。如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。不允许修改链表。
示例 1：输入：head = [3,2,0,-4], pos = 1 输出：返回索引为 1 的链表节点 解释：链表中有一个环，其尾部连接到第二个节点。
示例 2：输入：head = [1,2], pos = 0 输出：返回索引为 0 的链表节点 解释：链表中有一个环，其尾部连接到第一个节点。
示例 3：输入：head = [1], pos = -1 输出：返回 null 解释：链表中没有环。
提示：链表中节点的数目范围在范围 [0, 104] 内 -105 <= Node.val <= 105 pos 的值为 -1 或者链表中的一个有效索引
进阶：你是否可以使用 O(1) 空间解决此题？

2. 解法一：哈希表法
- 算法思想：遍历链表的所有节点，使用哈希集合记录已经访问过的节点。每遍历一个节点，先判断该节点是否存在于哈希集合中，若存在则为环的入口节点，直接返回该节点；若遍历至节点为null，说明链表无环，返回null。该方法时间复杂度为O(n)，空间复杂度为O(n)。
- Java代码
```java
import java.util.HashSet;
import java.util.Set;

// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public ListNode detectCycle(ListNode head) {
        // 存储已经访问过的节点
        Set<ListNode> visited = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            // 节点已存在，为环的入口节点
            if (visited.contains(current)) {
                return current;
            }
            visited.add(current);
            current = current.next;
        }
        // 遍历到链表末尾，无环
        return null;
    }
}
```

3. 解法二：快慢指针法（双指针法，O(1)空间）
- 算法思想：1. 初始化快指针和慢指针均指向链表头节点，慢指针每次移动1步，快指针每次移动2步；2. 若快慢指针相遇，证明链表存在环，若快指针指向null则链表无环；3. 指针相遇后将慢指针移回头节点，快慢指针均改为每次移动1步，两指针再次相遇的节点即为环的入口节点。该方法基于数学公式推导，时间复杂度O(n)，空间复杂度O(1)。
- Java代码
```java
// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class Solution {
    public ListNode detectCycle(ListNode head) {
        // 初始化快慢指针
        ListNode slow = head;
        ListNode fast = head;
        // 判断链表是否存在环
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇，证明有环
            if (slow == fast) {
                // 慢指针移回链表头节点
                slow = head;
                // 双指针同速移动，再次相遇即为环入口
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        // 无环返回null
        return null;
    }
}
```

### [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/)

1. 题目描述
将两个升序链表合并为一个新的升序链表并返回，新链表通过拼接给定两个链表的所有节点组成
示例1：输入l1 = [1,2,4]，l2 = [1,3,4]，输出[1,1,2,3,4,4]
示例2：输入l1 = []，l2 = []，输出[]
示例3：输入l1 = []，l2 = [0]，输出[0]
提示：两个链表的节点数目范围是 [0, 50]，-100 <= Node.val <= 100，l1和l2均按非递减顺序排列

2. 算法思想+代码
- 通用链表节点定义
```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```
- 解法一：迭代法
  算法思想：创建哑节点作为新链表的起始节点，使用指针遍历两个有序链表，每次比较两个链表当前节点的值，将值较小的节点拼接到新链表后，同时移动对应链表的指针；当其中一个链表遍历完毕后，直接将另一个链表的剩余节点拼接至新链表末尾，最终返回哑节点的下一个节点即为合并后的链表
  代码实现：
```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建哑节点，方便操作新链表
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 遍历两个链表，直到其中一个为空
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 拼接剩余未遍历完的链表节点
        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
```
- 解法二：递归法
  算法思想：设定递归终止条件，当其中一个链表为空时，直接返回另一个链表；每次递归比较两个链表头节点的值，将值较小的节点作为当前合并节点，递归处理该节点的下一个节点与另一个链表，通过回溯实现节点拼接，最终得到合并后的链表
  代码实现：
```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 递归终止条件：一个链表为空，返回另一个链表
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 比较节点值，递归拼接
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
```

### [2. 两数相加](https://leetcode.cn/problems/add-two-numbers/)

1. 题目描述：给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字0之外，这两个数都不会以0开头。输入示例：l1 = [2,4,3]，l2 = [5,6,4]，输出[7,0,8]，对应计算342 + 465 = 807；l1 = [0]，l2 = [0]，输出[0]；l1 = [9,9,9,9,9,9,9]，l2 = [9,9,9,9]，输出[8,9,9,9,0,0,0,1]。提示：每个链表中的节点数在范围[1, 100]内，0 <= Node.val <= 9，题目数据保证列表表示的数字不含前导零。
2. 算法思想+代码
- 核心思路为模拟数学中的竖式加法运算，从两个链表的头部（对应数字的个位）开始逐位相加
- 定义进位变量carry，初始值为0，用于存储每一位相加后产生的进位
- 创建哑节点（哨兵节点）dummy，用于简化结果链表的构建操作，避免处理头节点的特殊情况
- 循环遍历两个链表，当任意一个链表未遍历完成，或进位值不为0时，持续执行相加逻辑
- 每次循环中，获取当前两个链表节点的数值（节点为空则取0），计算当前位的总和：当前值1 + 当前值2 + 进位carry
- 计算当前位的结果值（总和对10取余），并更新进位值（总和除以10取整）
- 创建新的链表节点存储当前位结果，将其连接到结果链表的末尾
- 依次移动两个链表的指针，直到循环结束，最终返回哑节点的下一个节点作为结果链表的头节点
- 时间复杂度：O(max(n, m))，n和m分别为两个链表的长度，只需遍历最长链表一次
- 空间复杂度：O(max(n, m))，结果链表的长度最多为最长链表长度加1
```java
/**
 * 链表节点定义
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 创建哑节点，作为结果链表的前驱节点
        ListNode dummy = new ListNode(0);
        // 定义指针，用于构建结果链表
        ListNode current = dummy;
        // 进位变量，初始为0
        int carry = 0;

        // 循环条件：两个链表未遍历完 或 存在进位
        while (l1 != null || l2 != null || carry != 0) {
            // 获取当前节点的值，节点为空则取0
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            // 计算当前位的总和
            int sum = val1 + val2 + carry;
            // 更新进位
            carry = sum / 10;
            // 当前位的结果
            int resVal = sum % 10;

            // 创建新节点，拼接至结果链表
            current.next = new ListNode(resVal);
            // 移动结果链表指针
            current = current.next;

            // 移动两个输入链表的指针
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 返回结果链表的头节点
        return dummy.next;
    }
}
```

### [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)

1. 题目描述
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
示例 1：输入：head = [1,2,3,4,5], n = 2 输出：[1,2,3,5]
示例 2：输入：head = [1], n = 1 输出：[]
示例 3：输入：head = [1,2], n = 1 输出：[1]
提示：链表中结点的数目为 sz，1 <= sz <= 30，0 <= Node.val <= 100，1 <= n <= sz
进阶：你能尝试使用一趟扫描实现吗？

2. 解法一：两次遍历法
- 算法思想：首先遍历整个链表获取总长度sz，计算出待删除节点为正数第sz-n个节点；再次遍历链表，找到待删除节点的前一个节点并修改指针完成删除；引入哑节点指向头节点，解决删除头节点的边界问题。
- 代码：
```java
// 定义链表节点类
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 哑节点，避免头节点删除的空指针问题
        ListNode dummy = new ListNode(0, head);
        int length = 0;
        ListNode cur = head;
        // 第一次遍历：计算链表总长度
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        // 找到待删除节点的前一个节点
        cur = dummy;
        for (int i = 1; i <= length - n; i++) {
            cur = cur.next;
        }
        // 执行删除操作
        cur.next = cur.next.next;
        return dummy.next;
    }
}
```

3. 解法二：快慢指针法（一趟扫描）
- 算法思想：定义快慢指针初始均指向哑节点，快指针先向前移动n步，随后快慢指针同步移动，直至快指针到达链表末尾；此时慢指针的下一个节点即为待删除节点，修改指针完成删除，仅需一趟扫描链表。
- 代码：
```java
// 链表节点类与上文一致
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        // 初始化快慢指针
        ListNode fast = dummy;
        ListNode slow = dummy;
        // 快指针先移动n步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 快慢指针同步移动，直到快指针指向最后一个节点
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 删除目标节点
        slow.next = slow.next.next;
        return dummy.next;
    }
}
```

### [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)

1. 题目描述
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
示例 1：输入：head = [1,2,3,4]，输出：[2,1,4,3]
示例 2：输入：head = []，输出：[]
示例 3：输入：head = [1]，输出：[1]
提示：链表中节点的数目在范围 [0, 100] 内，0 <= Node.val <= 100

2. 算法思想+代码
- 链表节点定义（公共基础代码）
```java
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
```
- 解法一：递归法
  算法思想：递归通过分解子问题实现交换，终止条件为链表为空或仅有一个节点。每次递归处理当前两个相邻节点，将第一个节点的后继指向后续递归处理的结果，再将第二个节点指向第一个节点，完成交换后返回第二个节点作为当前子链表的头节点。
  代码：
```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 递归终止条件：无节点或只有一个节点，无需交换
        if (head == null || head.next == null) {
            return head;
        }
        // 定义待交换的两个节点
        ListNode first = head;
        ListNode second = head.next;
        // 第一个节点指向后续链表交换后的头节点
        first.next = swapPairs(second.next);
        // 第二个节点指向第一个节点，完成交换
        second.next = first;
        // 返回交换后的新头节点
        return second;
    }
}
```
- 解法二：迭代法
  算法思想：借助虚拟头节点规避头节点交换的边界问题，通过前驱指针遍历链表，循环交换相邻两个节点。每次定位待交换的两个节点，调整指针指向完成交换后，移动前驱指针继续处理后续节点，最终返回虚拟头节点的后继节点作为结果。
  代码：
```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        // 创建虚拟头节点，指向原链表头
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 前驱指针，用于绑定交换后的节点
        ListNode prev = dummy;
        // 存在两个可交换的节点时循环
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            // 节点交换操作
            first.next = second.next;
            second.next = first;
            prev.next = second;
            // 前驱指针后移，准备下一轮交换
            prev = first;
        }
        // 返回交换后的链表头节点
        return dummy.next;
    }
}
```

### [25. K 个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/)

1. 题目描述
给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
示例 1：输入：head = [1,2,3,4,5], k = 2 输出：[2,1,4,3,5]
示例 2：输入：head = [1,2,3,4,5], k = 3 输出：[3,2,1,4,5]
提示：链表中的节点数目为 n，1 <= k <= n <= 5000，0 <= Node.val <= 1000
进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？

2. 算法思想+代码
- 解法一：迭代法（O(1)额外空间，满足进阶要求）
  算法思想：1. 定义虚拟头节点，统一处理链表头节点翻转后的指向问题；2. 维护前驱节点、当前组起始节点、当前组结束节点、后继节点四个关键节点；3. 遍历链表，每次找到k个节点的组，若剩余节点不足k个则终止循环；4. 翻转当前组内的k个节点，重新建立前驱节点、翻转后组、后继节点的连接关系；5. 更新前驱节点为当前组的原起始节点，继续处理下一组节点。
  Java代码：
```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 虚拟头节点，简化边界处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 前驱节点：记录当前组的前一个节点
        ListNode pre = dummy;
        // 结束节点：记录当前组的最后一个节点
        ListNode end = dummy;

        while (end.next != null) {
            // 找到当前组的结束节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 剩余节点不足k个，直接退出
            if (end == null) {
                break;
            }
            // 记录当前组的起始节点
            ListNode start = pre.next;
            // 记录下一组的起始节点
            ListNode next = end.next;
            // 断开当前组与后续节点的连接
            end.next = null;
            // 翻转当前组
            pre.next = reverse(start);
            // 连接翻转后的组与后续节点
            start.next = next;
            // 更新前驱节点和结束节点
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    // 翻转单组链表
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
```

- 解法二：递归法
  算法思想：1. 递归的终止条件：找到的节点不足k个，直接返回当前头节点；2. 先遍历找到当前组的k个节点，确定翻转范围；3. 翻转当前k个节点，得到新的组头节点；4. 递归处理剩余未翻转的链表；5. 将当前组翻转后的尾节点指向递归返回的后续链表头节点，最终返回当前组的新头节点。
  Java代码：
```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找到当前组的尾节点
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            // 剩余节点不足k个，直接返回原头节点
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        // 翻转当前k个节点
        ListNode newHead = reverse(head, k);
        // 原头节点变为尾节点，指向后续递归翻转的结果
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    // 翻转前k个节点
    private ListNode reverse(ListNode head, int k) {
        ListNode pre = null;
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
```

### [138. 随机链表的复制](https://leetcode.cn/problems/copy-list-with-random-pointer/)

1. 题目描述
给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点。例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。返回复制链表的头节点。用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：val：一个表示 Node.val 的整数。random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。你的代码 只 接受原链表的头节点 head 作为传入参数。
示例 1：输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]] 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
示例 2：输入：head = [[1,1],[2,1]] 输出：[[1,1],[2,1]]
示例 3：输入：head = [[3,null],[3,0],[3,null]] 输出：[[3,null],[3,0],[3,null]]
提示：0 <= n <= 1000，-104 <= Node.val <= 104，Node.random 为 null 或指向链表中的节点。

2. 算法思想+代码
- 解法一：哈希表法
  - 算法思想：通过哈希表建立原链表节点与新复制节点的一一映射关系，第一次遍历原链表创建所有新节点并存储映射关系；第二次遍历原链表，通过哈希表的映射为新节点赋值next和random指针，最终得到深拷贝链表。
  - 代码：
```java
// 随机链表节点定义
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        // 哈希表存储原节点与新节点的映射
        HashMap<Node, Node> nodeMap = new HashMap<>();
        Node current = head;
        // 第一次遍历：创建所有新节点并存入哈希表
        while (current != null) {
            nodeMap.put(current, new Node(current.val));
            current = current.next;
        }
        current = head;
        // 第二次遍历：设置新节点的next和random指针
        while (current != null) {
            nodeMap.get(current).next = nodeMap.get(current.next);
            nodeMap.get(current).random = nodeMap.get(current.random);
            current = current.next;
        }
        // 返回复制链表的头节点
        return nodeMap.get(head);
    }
}
```
- 解法二：原地复制法
  - 算法思想：分三步实现深拷贝，第一步在原链表每个节点后方插入对应复制节点，形成原节点与复制节点交替的链表；第二步遍历链表，为复制节点设置random指针；第三步拆分原链表和复制链表，分离出独立的深拷贝新链表，无需额外哈希表存储空间。
  - 代码：
```java
// 随机链表节点定义
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        // 处理空链表边界情况
        if (head == null) {
            return null;
        }
        Node current = head;
        // 第一步：在原节点后插入复制节点
        while (current != null) {
            Node copyNode = new Node(current.val);
            copyNode.next = current.next;
            current.next = copyNode;
            current = copyNode.next;
        }
        current = head;
        // 第二步：为复制节点设置random指针
        while (current != null) {
            Node copyNode = current.next;
            copyNode.random = (current.random != null) ? current.random.next : null;
            current = copyNode.next;
        }
        current = head;
        Node newHead = head.next;
        // 第三步：拆分原链表和复制链表
        while (current != null) {
            Node copyNode = current.next;
            current.next = copyNode.next;
            copyNode.next = (copyNode.next != null) ? copyNode.next.next : null;
            current = current.next;
        }
        return newHead;
    }
}
```

### [148. 排序链表](https://leetcode.cn/problems/sort-list/)

1. 题目描述
给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
示例 1：输入：head = [4,2,1,3]，输出：[1,2,3,4]
示例 2：输入：head = [-1,5,3,4,0]，输出：[-1,0,3,4,5]
示例 3：输入：head = []，输出：[]
提示：链表中节点的数目在范围 [0, 5 * 104] 内，-105 <= Node.val <= 105
进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

2. 算法思想+代码
- 算法思想：采用自底向上的归并排序，满足O(n log n)时间复杂度和常数级空间复杂度的进阶要求。链表的结构特性适配归并排序，无需数组的随机访问操作；自底向上通过迭代实现，避免了递归调用栈的空间开销，实现常数空间。核心步骤：1. 遍历计算链表的总长度；2. 从子链表长度为1开始，两两合并有序子链表；3. 每次将子链表长度翻倍，重复合并操作，直至合并为完整的有序链表；4. 实现链表切分和两个有序链表合并的辅助逻辑。
- Java代码实现
```java
// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode sortList(ListNode head) {
        // 处理空链表或单节点链表
        if (head == null || head.next == null) {
            return head;
        }
        // 计算链表总长度
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        // 哑节点，简化链表头节点的处理
        ListNode dummy = new ListNode(0, head);
        // 自底向上归并，子链表长度从1开始，每次翻倍
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummy;
            ListNode curr = dummy.next;
            // 遍历合并所有当前长度的子链表
            while (curr != null) {
                // 截取第一个长度为subLength的子链表
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                // 截取第二个长度为subLength的子链表
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                // 记录剩余未处理的节点
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                // 合并两个有序子链表
                prev.next = merge(head1, head2);
                // 移动指针到已排序链表的尾部
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummy.next;
    }

    // 合并两个有序链表
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 双指针合并
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        // 拼接剩余节点
        cur.next = head1 != null ? head1 : head2;
        return dummy.next;
    }
}
```

```java
class Solution {
    // 归并排序在链表上的实现
    public ListNode sortList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }

        // 1. 找中点
        ListNode slow = head, fast = head;
        while (fast.next!= null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断开链表
        ListNode slownext=slow.next;
        slow.next = null;
        

        // 2. 递归排序左右两部分
        ListNode left = sortList(head);
        ListNode right = sortList(slownext);

        // 3. 合并两个有序链表
        return merge(left, right);
    }

    // 合并两个有序链表
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
```

### [23. 合并 K 个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/)

1.题目描述
给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
示例 1：
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。1->1->2->3->4->4->5->6
示例 2：
输入：lists = []
输出：[]
示例 3：
输入：lists = [[]]
输出：[]
提示：
k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] 按升序排列
lists[i].length 的总和不超过 10^4

2.算法思想+代码
- 解法一：顺序合并
  算法思想：基于合并两个升序链表的基础方法，依次将链表数组中的每一个链表与当前已合并完成的链表进行合并，重复执行k-1次合并操作，最终得到所有链表合并后的升序链表。该方法实现简单，适合理解基础合并逻辑，但时间复杂度较高。
  代码：
```java
// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    // 合并K个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        // 遍历所有链表，依次合并
        for (ListNode list : lists) {
            ans = mergeTwoLists(ans, list);
        }
        return ans;
    }

    // 合并两个升序链表的辅助方法
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 双指针遍历两个链表，拼接较小节点
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 拼接剩余未遍历完的节点
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```

- 解法二：分治合并
  算法思想：采用分治策略，将k个链表两两分组，递归地合并每组内的两个链表，不断缩小合并的规模，直到所有链表合并为一个链表。该方法通过分治降低了合并的次数，时间复杂度优于顺序合并，是高效的合并方式。
  代码：
```java
// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // 边界条件处理
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    // 分治递归合并指定区间的链表
    private ListNode merge(ListNode[] lists, int left, int right) {
        // 区间内只有一个链表，直接返回
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        // 递归合并左半区和右半区
        ListNode leftList = merge(lists, left, mid);
        ListNode rightList = merge(lists, mid + 1, right);
        // 合并两个子链表
        return mergeTwoLists(leftList, rightList);
    }

    // 合并两个升序链表的辅助方法
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
```

- 解法三：优先队列（小顶堆）
  算法思想：利用小顶堆的特性，将所有链表的头节点加入优先队列中，每次取出堆顶的最小节点作为结果链表的节点，随后将该最小节点的下一个节点加入堆中，循环执行操作直到堆为空。该方法时间复杂度最优，代码实现简洁高效。
  代码：
```java
// 链表节点定义
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // 边界条件处理
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 定义小顶堆，按节点值升序排列
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        // 将所有非空链表的头节点入堆
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 循环取出堆顶最小节点构建结果链表
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            cur.next = minNode;
            cur = cur.next;
            // 将当前最小节点的下一个节点入堆
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }
        return dummy.next;
    }
}
```

### [146. LRU 缓存](https://leetcode.cn/problems/lru-cache/)

1. 题目描述
请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。实现 LRUCache 类：LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存；int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1；void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value；如果不存在，则向缓存中插入该组 key-value。如果插入操作导致关键字数量超过 capacity，则应该逐出最久未使用的关键字。函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
示例：输入["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]，[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]；输出[null, null, null, 1, null, -1, null, -1, 3, 4]
提示：1 <= capacity <= 3000，0 <= key <= 10000，0 <= value <= 105，最多调用 2 * 105 次 get 和 put

2. 解法一：哈希表+双向链表（手动实现）
- 算法思想：核心采用哈希表+双向链表组合实现，哈希表保证O(1)时间复杂度查找键对应的节点；双向链表用于维护节点的使用顺序，链表头部为最近使用的节点，尾部为最久未使用的节点。get操作：若key存在，将对应节点移动到链表头部标记为最近使用，返回节点值；若不存在返回-1。put操作：若key存在，更新节点值并移动到头部；若key不存在，新建节点加入哈希表和链表头部，若缓存容量超限，删除链表尾部的最久未使用节点，并同步移除哈希表中的对应键。
- Java代码
```java
import java.util.HashMap;
import java.util.Map;

// 双向链表节点类
class DLinkedNode {
    int key;
    int value;
    DLinkedNode prev;
    DLinkedNode next;
    public DLinkedNode() {}
    public DLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    // 哈希表：存储key和对应节点，实现O(1)查找
    private Map<Integer, DLinkedNode> cache;
    // 双向链表哨兵头、尾节点，简化边界操作
    private DLinkedNode head, tail;
    // 缓存最大容量
    private int capacity;
    // 当前缓存元素数量
    private int size;

    // 构造方法初始化缓存
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        cache = new HashMap<>();
        // 初始化哨兵节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    // 获取key对应的值
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        // 键不存在返回-1
        if (node == null) {
            return -1;
        }
        // 将节点移到头部，标记为最近使用
        moveToHead(node);
        return node.value;
    }

    // 插入或更新键值对
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 新建节点并加入缓存
            DLinkedNode newNode = new DLinkedNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            size++;
            // 超出容量，删除最久未使用的尾节点
            if (size > capacity) {
                DLinkedNode tailNode = removeTail();
                cache.remove(tailNode.key);
                size--;
            }
        } else {
            // 更新节点值并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    // 将节点移动到链表头部
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    // 删除指定节点
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 将节点添加到链表头部
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // 删除链表尾节点并返回
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
```

3. 解法二：基于Java内置LinkedHashMap实现
- 算法思想：LinkedHashMap是HashMap的子类，内部维护双向链表保证元素顺序；设置构造参数accessOrder为true时，节点会按访问顺序调整位置（访问/更新后移至链表尾部）；重写removeEldestEntry方法，当缓存大小超过容量时，自动删除最久未使用的头部节点。复用LinkedHashMap原生API即可实现LRU缓存，所有操作平均时间复杂度为O(1)。
- Java代码
```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Integer> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // 初始化LinkedHashMap，accessOrder=true开启访问顺序排序
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            // 重写方法，超出容量时删除最老节点
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        // 键不存在返回-1，存在则自动调整节点顺序
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        // 自动处理插入、更新和超限删除逻辑
        map.put(key, value);
    }
}
```

## 二叉树

二叉树定义

二叉树是一种非线性的树形数据结构，核心特征为**每个节点最多拥有两个子节点**，两个子节点严格区分左子节点和右子节点（顺序不可互换），是数据结构中最基础、应用最广泛的树形结构。

二叉树常见操作

1. 二叉树节点的定义与初始化
2. 手动构建二叉树结构
3. 深度优先遍历（前序遍历、中序遍历、后序遍历）
4. 广度优先遍历（层序遍历）
5. 计算二叉树的最大深度
6. 统计二叉树的总节点数量
7. 统计二叉树的叶子节点数量
8. 查找指定值的树节点
9. 清空/销毁二叉树

Java实现二叉树常用操作Demo

以下代码完整实现上述所有操作，包含节点定义、二叉树操作类、测试主方法，可直接运行：
```java
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树节点类
 * 定义二叉树的基础节点结构：值、左子节点、右子节点
 */
class TreeNode {
    // 节点存储的值
    int val;
    // 左子节点
    TreeNode left;
    // 右子节点
    TreeNode right;

    // 构造方法
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

/**
 * 二叉树操作实现类
 * 包含二叉树所有常用操作的具体实现
 */
class BinaryTree {
    // 根节点
    TreeNode root;

    // 构造方法：初始化空二叉树
    public BinaryTree() {
        this.root = null;
    }

    // ==================== 1. 手动构建二叉树 ====================
    public void createTree() {
        // 手动构建示例二叉树
        // 结构：
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
    }

    // ==================== 2. 深度优先遍历（递归实现） ====================
    // 前序遍历：根节点 → 左子树 → 右子树
    public void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历：左子树 → 根节点 → 右子树
    public void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    // 后序遍历：左子树 → 右子树 → 根节点
    public void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    // ==================== 3. 广度优先遍历（层序遍历） ====================
    // 按层次从上到下、从左到右遍历节点，使用队列实现
    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");
            // 左子节点入队
            if (current.left != null) {
                queue.offer(current.left);
            }
            // 右子节点入队
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
    }

    // ==================== 4. 计算二叉树最大深度 ====================
    public int getMaxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左、右子树深度，取最大值+1（当前节点）
        int leftDepth = getMaxDepth(node.left);
        int rightDepth = getMaxDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // ==================== 5. 统计总节点数 ====================
    public int getNodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 左子树节点数 + 右子树节点数 + 1（当前节点）
        return getNodeCount(node.left) + getNodeCount(node.right) + 1;
    }

    // ==================== 6. 统计叶子节点数 ====================
    public int getLeafCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 叶子节点：无左右子节点
        if (node.left == null && node.right == null) {
            return 1;
        }
        return getLeafCount(node.left) + getLeafCount(node.right);
    }

    // ==================== 7. 查找指定值的节点 ====================
    public TreeNode searchNode(TreeNode node, int target) {
        if (node == null) {
            return null;
        }
        // 找到目标节点，直接返回
        if (node.val == target) {
            return node;
        }
        // 递归查找左子树
        TreeNode leftResult = searchNode(node.left, target);
        if (leftResult != null) {
            return leftResult;
        }
        // 递归查找右子树
        return searchNode(node.right, target);
    }

    // ==================== 8. 清空二叉树 ====================
    public void clearTree(TreeNode node) {
        if (node == null) {
            return;
        }
        // 递归清空左、右子树
        clearTree(node.left);
        clearTree(node.right);
        // 释放当前节点
        node.left = null;
        node.right = null;
        node = null;
    }
}

/**
 * 测试主类
 * 调用所有二叉树操作，验证功能
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树对象
        BinaryTree tree = new BinaryTree();
        // 手动构建二叉树
        tree.createTree();

        System.out.println("========== 二叉树常用操作测试结果 ==========");
        System.out.print("1. 前序遍历结果：");
        tree.preOrder(tree.root);
        System.out.println();

        System.out.print("2. 中序遍历结果：");
        tree.inOrder(tree.root);
        System.out.println();

        System.out.print("3. 后序遍历结果：");
        tree.postOrder(tree.root);
        System.out.println();

        System.out.print("4. 层序遍历结果：");
        tree.levelOrder(tree.root);
        System.out.println();

        System.out.println("5. 二叉树最大深度：" + tree.getMaxDepth(tree.root));
        System.out.println("6. 二叉树总节点数：" + tree.getNodeCount(tree.root));
        System.out.println("7. 二叉树叶子节点数：" + tree.getLeafCount(tree.root));

        // 查找值为4的节点
        TreeNode target = tree.searchNode(tree.root, 4);
        System.out.println("8. 查找节点（值=4）：" + (target != null ? "找到节点" : "未找到节点"));

        // 清空二叉树
        tree.clearTree(tree.root);
        System.out.println("9. 清空二叉树后，总节点数：" + tree.getNodeCount(tree.root));
    }
}
```

```
========== 二叉树常用操作测试结果 ==========
1. 前序遍历结果：1 2 4 5 3 
2. 中序遍历结果：4 2 5 1 3 
3. 后序遍历结果：4 5 2 3 1 
4. 层序遍历结果：1 2 3 4 5 
5. 二叉树最大深度：3
6. 二叉树总节点数：5
7. 二叉树叶子节点数：3
8. 查找节点（值=4）：找到节点
9. 清空二叉树后，总节点数：0
```

1. 二叉树的遍历是核心操作，深度优先遍历基于**递归**实现，层序遍历基于**队列**实现
2. 深度、节点数、叶子节点数的统计均采用递归思想，利用二叉树的子结构特性简化计算
3. 手动构建二叉树是基础，实际开发中可根据需求改为递归/数组自动构建
4. 清空二叉树通过递归释放所有节点，避免内存泄漏

### [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/)

1. 题目描述
给定一个二叉树的根节点 root ，返回它的中序遍历。
示例 1：输入：root = [1,null,2,3]，输出：[1,3,2]
示例 2：输入：root = []，输出：[]
示例 3：输入：root = [1]，输出：[1]
提示：树中节点数目在范围 [0, 100] 内，-100 <= Node.val <= 100
进阶：递归算法很简单，你可以通过迭代算法完成吗？

2. 算法思想+代码
- 解法一：递归法
  算法思想：中序遍历遵循左子树→根节点→右子树的访问顺序，通过递归实现遍历逻辑，递归终止条件为当前节点为null；递归时先处理左子树，再将当前节点的值加入结果集合，最后处理右子树。
  代码：
  ```java
  import java.util.ArrayList;
  import java.util.List;
  
  // 二叉树节点定义
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
  
  class Solution {
      public List<Integer> inorderTraversal(TreeNode root) {
          List<Integer> res = new ArrayList<>();
          inorder(root, res);
          return res;
      }
  
      // 递归遍历核心方法
      private void inorder(TreeNode node, List<Integer> res) {
          // 递归终止：节点为空
          if (node == null) {
              return;
          }
          // 第一步：递归遍历左子树
          inorder(node.left, res);
          // 第二步：访问当前根节点
          res.add(node.val);
          // 第三步：递归遍历右子树
          inorder(node.right, res);
      }
  }
  ```
- 解法二：迭代法（栈实现）
  算法思想：使用栈模拟递归的调用过程，首先循环遍历当前节点的左子节点并依次入栈，直到左子节点为null；然后弹出栈顶节点（根节点），将其值加入结果集合，再将当前节点指向该节点的右子节点，重复上述操作，直到栈为空且当前节点为null，完成遍历。
  代码：
  ```java
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Stack;
  
  // 二叉树节点定义（与递归法复用）
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
  
  class Solution {
      public List<Integer> inorderTraversal(TreeNode root) {
          List<Integer> res = new ArrayList<>();
          // 用栈模拟递归调用
          Stack<TreeNode> stack = new Stack<>();
          TreeNode cur = root;
          // 循环条件：当前节点不为空 或 栈中还有未处理的节点
          while (cur != null || !stack.isEmpty()) {
              // 遍历至最左节点，所有左节点依次入栈
              while (cur != null) {
                  stack.push(cur);
                  cur = cur.left;
              }
              // 弹出栈顶节点（根节点）
              cur = stack.pop();
              // 访问当前节点
              res.add(cur.val);
              // 处理当前节点的右子树
              cur = cur.right;
          }
          return res;
      }
  }
  ```
  
  ### [104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/)
  
  104. 二叉树的最大深度
1. 题目描述
   给定一个二叉树 root ，返回其最大深度。二叉树的最大深度是指从根节点到最远叶子节点的最长路径上的节点数。
   示例 1：输入：root = [3,9,20,null,null,15,7]，输出：3
   示例 2：输入：root = [1,null,2]，输出：2
   提示：树中节点的数量在 [0, 104] 区间内，-100 <= Node.val <= 100
2. 解法一：深度优先搜索（递归）
   - 算法思想：采用递归的方式遍历二叉树，递归终止条件为当前节点为null，此时深度为0；对于非空节点，其最大深度为左子树最大深度与右子树最大深度的最大值加1（当前节点占一个深度）。
   - 代码
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int maxDepth(TreeNode root) {
        // 递归终止条件：节点为空，深度为0
        if (root == null) {
            return 0;
        }
        // 递归计算左子树深度
        int leftDepth = maxDepth(root.left);
        // 递归计算右子树深度
        int rightDepth = maxDepth(root.right);
        // 当前节点深度 = 左右子树最大深度 + 1
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```
3. 解法二：广度优先搜索（迭代，层序遍历）
   - 算法思想：借助队列实现二叉树的层序遍历，每遍历完整的一层，深度值加1；遍历完所有层级后，最终的深度值即为二叉树的最大深度。
   - 代码
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int maxDepth(TreeNode root) {
        // 根节点为空，深度为0
        if (root == null) {
            return 0;
        }
        // 初始化队列，存储每一层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        // 层序遍历
        while (!queue.isEmpty()) {
            // 获取当前层的节点数量
            int size = queue.size();
            // 遍历当前层所有节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 左子节点入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                // 右子节点入队
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 每遍历完一层，深度加1
            depth++;
        }
        return depth;
    }
}
```

###   [226. 翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/)

1. 题目描述
给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
示例 1：输入：root = [4,2,7,1,3,6,9]，输出：[4,7,2,9,6,3,1]
示例 2：输入：root = [2,1,3]，输出：[2,3,1]
示例 3：输入：root = []，输出：[]
提示：树中节点数目范围在 [0, 100] 内，-100 <= Node.val <= 100

2. 算法思想+代码
- 解法一：递归法
  算法思想：递归的核心逻辑为交换每个节点的左右子节点，递归终止条件是当前节点为null（空节点无需翻转）。先递归翻转当前节点的左子树，再递归翻转当前节点的右子树，最后交换左右子节点，自底向上完成整棵二叉树的翻转。
  Java代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode invertTree(TreeNode root) {
        // 递归终止条件：空节点直接返回
        if (root == null) {
            return null;
        }
        // 递归翻转左子树
        TreeNode left = invertTree(root.left);
        // 递归翻转右子树
        TreeNode right = invertTree(root.right);
        // 交换当前节点的左右子节点
        root.left = right;
        root.right = left;
        return root;
    }
}
```

- 解法二：迭代法（层序遍历/BFS）
  算法思想：借助队列实现二叉树的层序遍历，遍历过程中逐个交换每个节点的左右子节点。首先将根节点加入队列，循环取出队首节点，交换其左右孩子，若孩子节点不为空则依次入队，直到队列为空，完成整棵树的翻转。
  Java代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public TreeNode invertTree(TreeNode root) {
        // 空树直接返回
        if (root == null) {
            return null;
        }
        // 初始化队列，用于层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出当前遍历的节点
            TreeNode node = queue.poll();
            // 交换当前节点的左右子节点
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            // 左子节点非空，加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 右子节点非空，加入队列
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }
}
```

###   [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/)

  1. 题目描述
给你一个二叉树的根节点 root ，检查它是否轴对称。
示例 1：输入：root = [1,2,2,3,4,4,3]，输出：true
示例 2：输入：root = [1,2,2,null,3,null,3]，输出：false
提示：树中节点数目在范围 [1, 1000] 内，-100 <= Node.val <= 100
进阶：你可以运用递归和迭代两种方法解决这个问题吗

2. 算法思想+代码
- 递归解法
  算法思想：判断二叉树对称的核心是验证根节点的左子树和右子树是否呈镜像对称。镜像对称满足三个条件：两个对比节点的数值相等；左节点的左子树与右节点的右子树对称；左节点的右子树与右节点的左子树对称。递归终止条件：两个节点均为空则对称；仅一个节点为空则不对称。
  代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        // 根节点为空则对称，否则判断左右子树
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    // 递归判断两个节点是否镜像对称
    private boolean isMirror(TreeNode left, TreeNode right) {
        // 两个节点都为空，对称
        if (left == null && right == null) {
            return true;
        }
        // 一个为空一个不为空，不对称
        if (left == null || right == null) {
            return false;
        }
        // 数值相等，且左左和右右对称、左右和右左对称
        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
}
```

- 迭代解法
  算法思想：借助队列模拟递归过程，将需要对比的节点成对入队。每次从队列中取出两个节点，先判断是否为空、数值是否相等；若相等则将左节点的左子节点与右节点的右子节点、左节点的右子节点与右节点的左子节点成对入队，继续后续判断；若不满足条件则直接返回false。队列为空时所有节点验证完毕，返回true。
  代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 使用队列存储待对比的节点对
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            // 取出一对对比节点
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // 两个节点均为空，继续验证下一对
            if (left == null && right == null) {
                continue;
            }
            // 单个节点为空，不对称
            if (left == null || right == null) {
                return false;
            }
            // 节点值不相等，不对称
            if (left.val != right.val) {
                return false;
            }

            // 将下一组待对比节点成对入队
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }
}
```

### [543. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/)

1. 题目描述
给你一棵二叉树的根节点，返回该树的 直径 。二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。两节点之间路径的 长度 由它们之间边数表示。
示例 1：输入：root = [1,2,3,4,5]，输出：3，解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
示例 2：输入：root = [1,2]，输出：1
提示：树中节点数目在范围 [1, 104] 内，-100 <= Node.val <= 100

2. 算法思想+代码
- 算法思想：采用深度优先搜索（DFS）递归遍历二叉树的所有节点，二叉树的直径为任意节点的左子树高度与右子树高度之和的最大值；定义全局变量存储最大直径，递归函数计算当前节点的子树高度，返回当前节点的最大子树高度（左、右子树高度的较大值加1），遍历过程中不断更新最大直径。
- Java代码
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // 全局变量记录遍历过程中的最大直径
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    // 递归计算节点的深度，同时更新最大直径
    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 计算左子树深度
        int left = depth(node.left);
        // 计算右子树深度
        int right = depth(node.right);
        // 更新最大直径：左深度+右深度
        max = Math.max(max, left + right);
        // 返回当前节点的最大子树深度
        return Math.max(left, right) + 1;
    }
}
```

### [102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)

1. 题目描述
给你二叉树的根节点 root ，返回其节点值的层序遍历。（即逐层地，从左到右访问所有节点）
示例 1：输入：root = [3,9,20,null,null,15,7]，输出：[[3],[9,20],[15,7]]
示例 2：输入：root = [1]，输出：[[1]]
示例 3：输入：root = []，输出：[]
提示：树中节点数目在范围 [0, 2000] 内，-1000 <= Node.val <= 1000
2. 解法一：BFS（队列实现）
- 算法思想：借助队列先进先出的特性实现逐层遍历，初始将根节点入队，循环处理队列时，先获取当前队列的长度（即当前层的节点数），依次取出当前层所有节点，记录节点值后将其左右非空子节点入队，直至队列为空，最终得到各层节点值集合
- 代码：
```java
import java.util.*;
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 空树直接返回空集合
        if (root == null) {
            return res;
        }
        // 初始化队列存储节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 获取当前层的节点数量
            int levelSize = queue.size();
            List<Integer> levelNodes = new ArrayList<>();
            // 遍历当前层所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = queue.poll();
                levelNodes.add(currNode.val);
                // 左子节点非空则入队
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                // 右子节点非空则入队
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
            // 将当前层节点值加入结果集
            res.add(levelNodes);
        }
        return res;
    }
}
```
3. 解法二：DFS（递归实现）
- 算法思想：通过递归深度遍历二叉树，传递当前节点的层数参数，将节点值添加到结果集合中对应层数的列表里，递归优先访问左子节点再访问右子节点，保证层序从左到右的顺序，最终完成层序遍历
- 代码：
```java
import java.util.*;
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 调用递归方法，初始层数为0
        dfs(root, 0, res);
        return res;
    }
    // 递归遍历：node当前节点，level当前层数，res结果集
    private void dfs(TreeNode node, int level, List<List<Integer>> res) {
        // 递归终止条件：节点为空
        if (node == null) {
            return;
        }
        // 若当前层数无对应列表，创建新列表
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        // 将节点值加入对应层的列表
        res.get(level).add(node.val);
        // 递归遍历左子树，层数+1
        dfs(node.left, level + 1, res);
        // 递归遍历右子树，层数+1
        dfs(node.right, level + 1, res);
    }
}
```

### [108. 将有序数组转换为二叉搜索树](https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/)

1. 题目描述
给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
示例 1：
输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]
解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案
示例 2：
输入：nums = [1,3]
输出：[3,1]
解释：[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树
提示：
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums 按 严格递增 顺序排列
2. 算法思想+代码
- 解法一：递归法
  算法思想：有序数组是二叉搜索树的中序遍历结果，平衡二叉搜索树要求每个节点的左右子树高度差不超过1。每次选取数组区间的中间元素作为当前根节点，左半区间递归构建左子树，右半区间递归构建右子树，递归终止条件为左边界大于右边界。
  Java代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // 调用递归函数，初始区间为数组首尾
        return dfs(nums, 0, nums.length - 1);
    }

    // 递归构建平衡二叉搜索树
    private TreeNode dfs(int[] nums, int left, int right) {
        // 递归终止：区间无效，返回空节点
        if (left > right) {
            return null;
        }
        // 计算中间索引，避免整数溢出
        int mid = left + (right - left) / 2;
        // 以中间元素创建根节点
        TreeNode root = new TreeNode(nums[mid]);
        // 递归构建左子树
        root.left = dfs(nums, left, mid - 1);
        // 递归构建右子树
        root.right = dfs(nums, mid + 1, right);
        return root;
    }
}
```
- 解法二：迭代法
  算法思想：模拟递归的分治逻辑，借助栈存储待处理的数组区间、父节点和子节点类型，通过迭代遍历区间，选取中间元素创建节点并关联父节点，利用栈先进后出的特性依次处理左右子区间，最终构建平衡二叉搜索树。
  Java代码：
```java
import java.util.Deque;
import java.util.LinkedList;

// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // 边界判断
        if (nums == null || nums.length == 0) {
            return null;
        }
        // 栈存储：[左边界, 右边界, 父节点, 是否为左子节点]
        Deque<Object[]> stack = new LinkedList<>();
        stack.push(new Object[]{0, nums.length - 1, null, false});
        TreeNode root = null;

        while (!stack.isEmpty()) {
            Object[] cur = stack.pop();
            int left = (int) cur[0];
            int right = (int) cur[1];
            TreeNode parent = (TreeNode) cur[2];
            boolean isLeft = (boolean) cur[3];

            // 计算中间索引
            int mid = left + (right - left) / 2;
            TreeNode curNode = new TreeNode(nums[mid]);

            // 关联当前节点与父节点
            if (parent == null) {
                root = curNode;
            } else {
                if (isLeft) {
                    parent.left = curNode;
                } else {
                    parent.right = curNode;
                }
            }

            // 先压右区间，再压左区间（栈先进后出）
            if (mid + 1 <= right) {
                stack.push(new Object[]{mid + 1, right, curNode, false});
            }
            if (left <= mid - 1) {
                stack.push(new Object[]{left, mid - 1, curNode, true});
            }
        }
        return root;
    }
}
```

### [98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/)

1. 题目描述
给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。有效二叉搜索树定义如下：节点的左子树只包含严格小于当前节点的数。节点的右子树只包含严格大于当前节点的数。所有左子树和右子树自身必须也是二叉搜索树。
示例 1：输入：root = [2,1,3] 输出：true
示例 2：输入：root = [5,1,4,null,null,3,6] 输出：false 解释：根节点的值是 5 ，但是右子节点的值是 4 。
提示：树中节点数目范围在[1, 104]内，-2^31 <= Node.val <= 2^31 - 1
2. 解法一：递归上下界校验法
- 算法思想：基于二叉搜索树的数值区间规则，为每个节点设定上下边界，根节点的上下界为负无穷和正无穷；当前节点的左子节点取值范围为（父节点下界，父节点值），右子节点取值范围为（父节点值，父节点上界）；递归校验所有节点是否在对应区间内，因节点值包含int类型极值，使用long类型避免数值溢出，空节点默认为有效节点。
- Java代码
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isValidBST(TreeNode root) {
        // 初始调用，下界为Long最小值，上界为Long最大值
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // 递归校验函数
    private boolean helper(TreeNode node, long lower, long upper) {
        // 空节点符合条件
        if (node == null) {
            return true;
        }
        // 当前节点值超出区间，直接返回false
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // 递归校验左子树（上界更新为当前节点值）和右子树（下界更新为当前节点值）
        return helper(node.left, lower, node.val) && helper(node.right, node.val, upper);
    }
}
```
3. 解法二：中序遍历迭代法
- 算法思想：二叉搜索树的中序遍历结果为严格递增序列，利用该特性校验有效性；使用栈实现迭代式中序遍历，遍历过程中记录上一个访问节点的数值，若当前节点数值小于等于上一个节点数值，则判定为无效二叉搜索树，该方式可避免递归栈溢出。
- Java代码
```java
import java.util.Deque;
import java.util.LinkedList;

// 二叉树节点定义（同解法一，可复用）
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        // 记录上一个节点的值，初始化为Long最小值
        long prev = Long.MIN_VALUE;

        while (root != null || !stack.isEmpty()) {
            // 遍历到最左子节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 出栈访问节点
            root = stack.pop();
            // 不满足严格递增，返回false
            if (root.val <= prev) {
                return false;
            }
            // 更新上一个节点值
            prev = root.val;
            // 遍历右子树
            root = root.right;
        }
        return true;
    }
}
```
4. 解法三：中序遍历递归法
- 算法思想：核心逻辑与中序遍历迭代法一致，通过递归实现二叉树中序遍历，定义全局变量记录上一个节点的数值，递归过程中实时校验序列的严格递增性。
- Java代码
```java
// 二叉树节点定义（同解法一，可复用）
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // 记录上一个节点的值
    private long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 递归遍历左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 校验当前节点是否大于上一个节点
        if (root.val <= prev) {
            return false;
        }
        // 更新上一个节点值
        prev = root.val;
        // 递归遍历右子树
        return isValidBST(root.right);
    }
}
```

### [230. 二叉搜索树中第 K 小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-bst/)

1. 题目描述
给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 小的元素（k 从 1 开始计数）。
示例 1：输入：root = [3,1,4,null,2], k = 1，输出：1
示例 2：输入：root = [5,3,6,2,4,null,null,1], k = 3，输出：3
提示：树中的节点数为 n ，1 <= k <= n <= 10^4，0 <= Node.val <= 10^4
进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？

2. 解法一：递归中序遍历
- 算法思想：二叉搜索树的中序遍历序列为升序序列，通过递归完成中序遍历，遍历过程中记录遍历次数，当次数等于k时，当前节点值即为所求结果。
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // 存储最终结果
    int res = 0;
    // 遍历计数
    int count = 0;
    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root, k);
        return res;
    }
    // 递归实现中序遍历
    private void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        // 遍历左子树
        inorderTraversal(node.left, k);
        // 访问当前节点，计数累加
        count++;
        // 计数匹配k，记录结果
        if (count == k) {
            res = node.val;
            return;
        }
        // 遍历右子树
        inorderTraversal(node.right, k);
    }
}
```

3. 解法二：迭代中序遍历
- 算法思想：使用栈模拟递归的中序遍历流程，将左子树节点依次入栈，弹出节点时进行计数，计数达到k时直接返回当前节点值，无需遍历完整树，空间效率更优。
```java
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;
        // 遍历计数
        int count = 0;
        while (current != null || !stack.isEmpty()) {
            // 左子树节点全部入栈
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // 弹出栈顶节点
            current = stack.pop();
            count++;
            // 找到第k小元素，直接返回
            if (count == k) {
                return current.val;
            }
            // 遍历右子树
            current = current.right;
        }
        // 题目保证k合法，无需处理异常情况
        return -1;
    }
}
```

4. 进阶解法：带子树节点统计的二叉搜索树
- 算法思想：自定义二叉树节点，为每个节点维护左子树的节点总数，查询时通过左子树节点数快速定位目标节点；查询、插入、删除操作的时间复杂度均为O(logn)，适合频繁修改和查询的场景。
```java
// 自定义带左子树节点统计的二叉树节点
class TreeNodeWithCount {
    int val;
    // 左子树的节点总数量
    int leftNodeCount;
    TreeNodeWithCount left;
    TreeNodeWithCount right;
    TreeNodeWithCount(int val) {
        this.val = val;
        this.leftNodeCount = 0;
    }
}

class Solution {
    // 插入节点并维护左子树节点数
    private TreeNodeWithCount insertNode(TreeNodeWithCount root, int val) {
        if (root == null) {
            return new TreeNodeWithCount(val);
        }
        if (val < root.val) {
            root.left = insertNode(root.left, val);
            root.leftNodeCount++;
        } else {
            root.right = insertNode(root.right, val);
        }
        return root;
    }

    // 查询第k小的元素
    private int findKthSmallest(TreeNodeWithCount root, int k) {
        if (root.leftNodeCount + 1 == k) {
            return root.val;
        } else if (root.leftNodeCount + 1 < k) {
            // 去右子树查找对应位次元素
            return findKthSmallest(root.right, k - root.leftNodeCount - 1);
        } else {
            // 去左子树查找
            return findKthSmallest(root.left, k);
        }
    }

    // 适配题目输入的封装方法
    public int kthSmallest(TreeNode root, int k) {
        TreeNodeWithCount newRoot = null;
        buildTree(root, newRoot);
        return findKthSmallest(newRoot, k);
    }

    // 将普通二叉树转为带统计的二叉树
    private void buildTree(TreeNode oldNode, TreeNodeWithCount newNode) {
        if (oldNode == null) return;
        newNode = insertNode(newNode, oldNode.val);
        buildTree(oldNode.left, newNode);
        buildTree(oldNode.right, newNode);
    }
}
```

### [199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)

1. 题目描述
给定一个二叉树的根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
示例 1：输入：root = [1,2,3,null,5,null,4]，输出：[1,3,4]
示例 2：输入：root = [1,2,3,4,null,null,null,5]，输出：[1,3,4,5]
示例 3：输入：root = [1,null,3]，输出：[1,3]
示例 4：输入：root = []，输出：[]
提示：二叉树的节点个数的范围是 [0,100]，-100 <= Node.val <= 100
2. 解法一：广度优先搜索（BFS）
- 算法思想：通过层序遍历二叉树，遍历每一层的所有节点，每一层仅保留最后一个节点的值（即最右侧节点），最终收集所有层的最右侧节点值即为结果。使用队列存储每一层的节点，依次处理每一层节点并记录最后一个节点值。
- 代码
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 队列辅助实现层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 获取当前层的节点总数
            int levelSize = queue.size();
            // 遍历当前层所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                // 每一层最后一个节点，加入结果列表
                if (i == levelSize - 1) {
                    res.add(node.val);
                }
                // 依次将左、右子节点入队
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return res;
    }
}
```
3. 解法二：深度优先搜索（DFS）
- 算法思想：采用根节点→右子树→左子树的遍历顺序，优先访问右子树节点，保证每一层第一个被访问的节点是该层最右侧节点。通过记录当前遍历深度，当深度与结果列表长度相等时，将当前节点值加入结果列表。
- 代码
```java
import java.util.ArrayList;
import java.util.List;

// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    // 深度优先遍历，depth为当前节点的深度
    private void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        // 当前深度未记录节点时，将当前节点加入结果
        if (depth == res.size()) {
            res.add(node.val);
        }
        // 优先遍历右子树，再遍历左子树
        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
    }
}
```

### [114. 二叉树展开为链表](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/)

1. 题目描述
给你二叉树的根结点 root ，请你将它展开为一个单链表：展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。展开后的单链表应该与二叉树 先序遍历 顺序相同。
示例 1：输入：root = [1,2,5,3,4,null,6]，输出：[1,null,2,null,3,null,4,null,5,null,6]
示例 2：输入：root = []，输出：[]
示例 3：输入：root = [0]，输出：[0]
提示：树中结点数在范围 [0, 2000] 内，-100 <= Node.val <= 100
进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？

2. 算法思想+代码
- 解法一：递归收集节点法
  算法思想：通过二叉树先序遍历的递归方式，将所有节点按顺序收集到列表中；遍历列表，依次将每个节点的左指针置为null，右指针指向列表中的下一个节点，完成链表转换；时间复杂度O(n)，空间复杂度O(n)
  代码：
```java
import java.util.ArrayList;
import java.util.List;

// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        // 先序遍历收集节点
        preOrder(root, list);
        // 拼接链表
        for (int i = 1; i < list.size(); i++) {
            TreeNode pre = list.get(i - 1);
            TreeNode cur = list.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }

    // 先序遍历递归
    private void preOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }
}
```

- 解法二：迭代先序遍历法
  算法思想：使用栈模拟先序遍历的迭代过程，记录上一个遍历的节点；遍历过程中直接修改节点指针，将当前节点作为前驱节点的右孩子，同时置空前驱节点的左指针；时间复杂度O(n)，空间复杂度O(n)
  代码：
```java
import java.util.Stack;

// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        // 迭代先序遍历
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            // 调整指针
            if (pre != null) {
                pre.left = null;
                pre.right = cur;
            }
            // 先压右孩子，再压左孩子
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            pre = cur;
        }
    }
}
```

- 解法三：原地前驱节点法（进阶O(1)空间）
  算法思想：遍历每个节点，若节点存在左子树，找到左子树的最右节点（先序遍历中左子树的最后一个节点）；将该前驱节点的右指针指向当前节点的右子树，再将当前节点的左子树移到右侧，置空左指针；循环处理所有节点，无需额外空间；时间复杂度O(n)，空间复杂度O(1)
  代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            // 左子树不为空，寻找前驱节点
            if (cur.left != null) {
                TreeNode predecessor = cur.left;
                // 找到左子树的最右节点
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                // 前驱节点指向当前节点的右子树
                predecessor.right = cur.right;
                // 左子树移到右侧，置空左指针
                cur.right = cur.left;
                cur.left = null;
            }
            // 处理下一个节点
            cur = cur.right;
        }
    }
}
```

### [105. 从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

1. 题目描述
给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
示例 1:
输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
输出: [3,9,20,null,null,15,7]
示例 2:
输入: preorder = [-1], inorder = [-1]
输出: [-1]
提示:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder 和 inorder 均 无重复 元素
inorder 均出现在 preorder
preorder 保证 为二叉树的前序遍历序列
inorder 保证 为二叉树的中序遍历序列

2. 解法一：递归法（哈希表优化）
- 算法思想：前序遍历的首个元素是二叉树的根节点，在中序遍历中定位根节点后，根节点左侧为左子树的中序遍历序列，右侧为右子树的中序遍历序列；根据左子树的节点数量，可划分出前序遍历中左子树和右子树的区间；递归执行根节点、左子树、右子树的构建逻辑；使用哈希表存储中序遍历元素与索引的映射，将根节点定位的时间复杂度优化至O(1)，整体时间复杂度为O(n)，空间复杂度为O(n)。
- Java代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

import java.util.HashMap;
import java.util.Map;

class Solution {
    // 哈希表存储中序遍历元素和索引的映射
    private Map<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        inorderMap = new HashMap<>();
        // 初始化哈希表
        for (int i = 0; i < n; i++) {
            inorderMap.put(inorder[i], i);
        }
        // 递归构建二叉树
        return build(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    // 递归函数：preLeft前序左边界，preRight前序右边界，inLeft中序左边界，inRight中序右边界
    private TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        // 递归终止条件：区间为空
        if (preLeft > preRight) {
            return null;
        }
        // 前序遍历第一个节点为根节点
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        // 找到根节点在中序遍历中的索引
        int inRoot = inorderMap.get(rootVal);
        // 左子树的节点个数
        int leftSize = inRoot - inLeft;
        // 构建左子树
        root.left = build(preorder, inorder, preLeft + 1, preLeft + leftSize, inLeft, inRoot - 1);
        // 构建右子树
        root.right = build(preorder, inorder, preLeft + leftSize + 1, preRight, inRoot + 1, inRight);
        return root;
    }
}
```

3. 解法二：迭代法
- 算法思想：通过栈模拟递归的构建过程，使用两个指针分别遍历前序和中序数组；首先将前序遍历的首个元素作为根节点并入栈，依次遍历前序数组，将元素作为栈顶节点的左子节点并入栈；当栈顶元素与中序指针指向的元素相等时，说明左子树构建完成，开始回溯弹出栈顶元素并移动中序指针，直至不满足条件后，将当前前序元素作为最后弹出节点的右子节点；重复该过程直至遍历完成，最终构建出完整二叉树，时间复杂度O(n)，空间复杂度O(n)。
- Java代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        // 中序遍历指针
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preVal = preorder[i];
            TreeNode node = stack.peek();
            // 栈顶节点值与中序当前值不等，说明是左子节点
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preVal);
                stack.push(node.left);
            } else {
                // 回溯构建右子节点
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
```

### [437. 路径总和 III](https://leetcode.cn/problems/path-sum-iii/)

1. 题目描述
给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
示例 1：输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8 输出：3 解释：和等于 8 的路径有 3 条
示例 2：输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22 输出：3
提示：二叉树的节点个数的范围是 [0,1000]，-10^9 <= Node.val <= 10^9，-1000 <= targetSum <= 1000

2. 解法一：暴力深度优先搜索
- 算法思想：采用双重递归实现，第一层递归遍历二叉树的所有节点，将每个节点作为路径的起点；第二层递归从当前起点节点出发，向下递归遍历左、右子节点，累加路径和，每遍历一个节点就判断当前路径和是否等于目标和，相等则将结果计数加一，最终统计所有符合条件的路径数量。
- 代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    // 统计符合条件的路径总数
    private int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 以当前节点为起点计算路径数
        dfs(root, targetSum);
        // 递归遍历左子树所有节点作为起点
        pathSum(root.left, targetSum);
        // 递归遍历右子树所有节点作为起点
        pathSum(root.right, targetSum);
        return count;
    }

    // 从当前节点出发，向下遍历计算路径和
    private void dfs(TreeNode node, long sum) {
        if (node == null) {
            return;
        }
        // 当前节点值匹配剩余和，路径数+1
        if (node.val == sum) {
            count++;
        }
        // 递归左子树，更新剩余目标和
        dfs(node.left, sum - node.val);
        // 递归右子树，更新剩余目标和
        dfs(node.right, sum - node.val);
    }
}
```

3. 解法二：前缀和+哈希表优化
- 算法思想：利用前缀和原理，前缀和指从根节点到当前节点的路径节点值总和。若根到节点A的前缀和为pre1，根到节点B的前缀和为pre2，且pre2 - pre1 = targetSum，则A到B的路径和为目标和。使用哈希表存储前缀和出现的次数，遍历二叉树时，计算当前前缀和与目标和的差值，差值对应的哈希表值即为当前节点结尾的合法路径数；遍历完子树后回溯哈希表，保证分支计算互不干扰。该方法时间复杂度为O(n)，空间复杂度为O(n)。
- 代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        // 哈希表：key=前缀和，value=该前缀和出现的次数
        Map<Long, Integer> prefixMap = new HashMap<>();
        // 初始化前缀和0出现1次，处理从根节点开始的路径
        prefixMap.put(0L, 1);
        return dfs(root, 0, targetSum, prefixMap);
    }

    private int dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixMap) {
        // 递归终止条件：节点为空
        if (node == null) {
            return 0;
        }
        // 更新当前节点的前缀和
        currentSum += node.val;
        // 获取以当前节点为结尾的合法路径数量
        int res = prefixMap.getOrDefault(currentSum - targetSum, 0);
        // 将当前前缀和存入哈希表，计数+1
        prefixMap.put(currentSum, prefixMap.getOrDefault(currentSum, 0) + 1);
        // 递归遍历左子树并累加路径数
        res += dfs(node.left, currentSum, targetSum, prefixMap);
        // 递归遍历右子树并累加路径数
        res += dfs(node.right, currentSum, targetSum, prefixMap);
        // 回溯操作：移除当前前缀和，不影响其他分支计算
        prefixMap.put(currentSum, prefixMap.get(currentSum) - 1);
        return res;
    }
}
```

### [236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/)

1. 题目描述：给定一个二叉树，找到该树中两个指定节点的最近公共祖先。最近公共祖先的定义为：对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
示例 1：输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1 输出：3 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
示例 2：输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4 输出：5 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
示例 3：输入：root = [1,2], p = 1, q = 2 输出：1
提示：树中节点数目在范围 [2, 105] 内。-109 <= Node.val <= 109 所有 Node.val 互不相同 。p != q p 和 q 均存在于给定的二叉树中。
2. 算法思想+代码
- 解法一：递归法
  算法思想：采用后序递归遍历二叉树，设置递归终止条件：当当前节点为null，或当前节点等于p，或当前节点等于q时，直接返回当前节点；递归遍历左子树得到左结果，递归遍历右子树得到右结果；若左结果和右结果都不为空，说明当前节点是p和q的最近公共祖先，返回当前节点；若仅左结果不为空，返回左结果；若仅右结果不为空，返回右结果。
  Java代码：
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件：节点为空 或 找到目标节点p/q
        if (root == null || root == p || root == q) {
            return root;
        }
        // 递归遍历左子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 递归遍历右子树
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        // 左右子树均找到节点，当前节点为最近公共祖先
        if (left != null && right != null) {
            return root;
        }
        // 仅左子树找到，返回左子树结果；仅右子树找到，返回右子树结果
        return left != null ? left : right;
    }
}
```
- 解法二：迭代法（父指针哈希表）
  算法思想：借助哈希表存储每个节点对应的父节点，通过广度优先遍历（BFS）遍历二叉树完成父节点的记录；再通过集合存储节点p的所有祖先节点（包含自身），接着遍历节点q的所有祖先节点，第一个存在于集合中的节点即为最近公共祖先。
  Java代码：
```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 存储节点与父节点的映射关系
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        // 存储p的所有祖先节点
        Set<TreeNode> ancestorSet = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        parentMap.put(root, null);
        queue.offer(root);
        
        // BFS遍历，记录所有节点的父节点
        while (!queue.isEmpty() && !(parentMap.containsKey(p) && parentMap.containsKey(q))) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }
        
        // 收集p的全部祖先节点
        while (p != null) {
            ancestorSet.add(p);
            p = parentMap.get(p);
        }
        
        // 查找q的祖先中第一个在集合中的节点
        while (!ancestorSet.contains(q)) {
            q = parentMap.get(q);
        }
        return q;
    }
}
```

### [124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/)

1. 题目描述
二叉树中的路径被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中至多出现一次。该路径至少包含一个节点，且不一定经过根节点。路径和是路径中各节点值的总和。给你一个二叉树的根节点 root，返回其最大路径和。
示例 1：输入：root = [1,2,3]，输出：6，解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
示例 2：输入：root = [-10,9,20,null,null,15,7]，输出：42，解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
提示：树中节点数目范围是 [1, 3 * 104]，-1000 <= Node.val <= 1000

2. 算法思想+代码
- 算法思想：采用深度优先搜索（DFS）递归遍历二叉树，核心是计算每个节点作为路径最高点的最大路径和，维护全局最大值作为最终结果。递归函数返回当前节点能向上传递的最大贡献值（仅取左/右子树的单边最大路径和，负贡献值取0）；当前节点的完整路径和为自身值+左子树贡献+右子树贡献，用该值更新全局最大路径和。
- Java代码
```java
// 二叉树节点定义
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    // 全局变量存储最大路径和
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateMaxGain(root);
        return maxSum;
    }

    // 递归计算节点的最大贡献值（向上传递的单边路径和）
    private int calculateMaxGain(TreeNode node) {
        // 空节点贡献值为0
        if (node == null) {
            return 0;
        }
        // 左子树最大贡献值，负数则取0（不选择该子树）
        int leftGain = Math.max(calculateMaxGain(node.left), 0);
        // 右子树最大贡献值，负数则取0
        int rightGain = Math.max(calculateMaxGain(node.right), 0);

        // 计算当前节点作为路径顶点的总路径和，更新全局最大值
        int currentPathSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, currentPathSum);

        // 返回当前节点向上传递的最大贡献值（只能选左或右单边）
        return node.val + Math.max(leftGain, rightGain);
    }
}
```

## 图论

1. 图论定义：图是描述事物及其关联关系的离散数学结构，由顶点集合V（代表具体事物）和边集合E（代表事物间的关联关系）构成，标准表示为G=(V,E)。细分类型包括无向图（边无方向，顶点双向连通）、有向图（边有固定指向）、无权图（边仅表示连通状态）、带权图（边附带权重值，可表示距离、成本等）；实际开发中主流存储方式为邻接矩阵（适合稠密图）和邻接表（适合稀疏图，Java语言首选）。
2. 图的常用操作，包含基础功能与经典算法实现
   - 基础构建操作：添加顶点、添加无向边/有向边/带权边
   - 遍历操作：深度优先搜索(DFS)、广度优先搜索(BFS)
   - 路径算法：单源最短路径（Dijkstra算法，适配非负权图）
   - 生成树算法：最小生成树（Prim算法，适配无向带权图）
   - 辅助操作：校验顶点合法性、打印图结构

采用**邻接表**实现（最常用的图存储方式），整合无向图、带权图、遍历、最短路径、最小生成树全功能，代码可直接运行：
```java
import java.util.*;

// 带权边的实体类（用于带权图、最短路径、最小生成树）
class Edge implements Comparable<Edge> {
    int to;     // 目标顶点
    int weight; // 边的权重

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    // 用于优先队列排序
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

// 图的核心实现类（邻接表）
class Graph {
    private final int vertexCount; // 顶点总数
    private final List<List<Edge>> adj; // 邻接表

    // 构造方法：初始化图
    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        adj = new ArrayList<>(vertexCount);
        // 为每个顶点初始化邻接链表
        for (int i = 0; i < vertexCount; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // ------------------- 1. 基础构建操作 -------------------
    /**
     * 添加无向带权边
     */
    public void addUndirectedEdge(int from, int to, int weight) {
        validateVertex(from);
        validateVertex(to);
        adj.get(from).add(new Edge(to, weight));
        adj.get(to).add(new Edge(from, weight));
    }

    /**
     * 添加有向带权边
     */
    public void addDirectedEdge(int from, int to, int weight) {
        validateVertex(from);
        validateVertex(to);
        adj.get(from).add(new Edge(to, weight));
    }

    // 校验顶点合法性
    private void validateVertex(int v) {
        if (v < 0 || v >= vertexCount) {
            throw new IllegalArgumentException("顶点编号非法！");
        }
    }

    // ------------------- 2. 图的遍历操作 -------------------
    /**
     * 深度优先搜索(DFS)：递归实现
     */
    public void dfs(int start) {
        validateVertex(start);
        boolean[] visited = new boolean[vertexCount];
        System.out.print("DFS遍历结果：");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        // 遍历所有邻接顶点
        for (Edge edge : adj.get(v)) {
            if (!visited[edge.to]) {
                dfsRecursive(edge.to, visited);
            }
        }
    }

    /**
     * 广度优先搜索(BFS)：队列实现
     */
    public void bfs(int start) {
        validateVertex(start);
        boolean[] visited = new boolean[vertexCount];
        Queue<Integer> queue = new LinkedList<>();
        System.out.print("BFS遍历结果：");
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");
            for (Edge edge : adj.get(v)) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    queue.offer(edge.to);
                }
            }
        }
        System.out.println();
    }

    // ------------------- 3. 单源最短路径：Dijkstra算法 -------------------
    public void dijkstra(int start) {
        validateVertex(start);
        int[] dist = new int[vertexCount]; // 存储起点到各顶点的最短距离
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 优先队列：按权重升序排列
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int v = curr.to;
            if (visited[v]) continue;
            visited[v] = true;

            // 松弛操作
            for (Edge edge : adj.get(v)) {
                int w = edge.to;
                if (!visited[w] && dist[v] != Integer.MAX_VALUE && dist[v] + edge.weight < dist[w]) {
                    dist[w] = dist[v] + edge.weight;
                    pq.offer(new Edge(w, dist[w]));
                }
            }
        }

        // 打印结果
        System.out.println("Dijkstra算法-起点" + start + "到各顶点最短距离：");
        for (int i = 0; i < vertexCount; i++) {
            System.out.println(start + " -> " + i + "：" + (dist[i] == Integer.MAX_VALUE ? "不可达" : dist[i]));
        }
    }

    // ------------------- 4. 最小生成树：Prim算法 -------------------
    public void prim(int start) {
        validateVertex(start);
        boolean[] visited = new boolean[vertexCount];
        int totalWeight = 0; // 最小生成树总权重
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        List<String> mstEdges = new ArrayList<>(); // 存储生成树的边

        while (!pq.isEmpty() && mstEdges.size() < vertexCount - 1) {
            Edge curr = pq.poll();
            int v = curr.to;
            if (visited[v]) continue;
            visited[v] = true;
            totalWeight += curr.weight;

            // 记录边（排除起点的初始边）
            if (curr.weight != 0) {
                mstEdges.add(curr.to + " <-> " + v + " (权重:" + curr.weight + ")");
            }

            for (Edge edge : adj.get(v)) {
                if (!visited[edge.to]) {
                    pq.offer(edge);
                }
            }
        }

        System.out.println("Prim算法最小生成树：");
        mstEdges.forEach(System.out::println);
        System.out.println("最小生成树总权重：" + totalWeight);
    }

    // ------------------- 辅助操作：打印图结构 -------------------
    public void printGraph() {
        System.out.println("图的邻接表结构：");
        for (int i = 0; i < vertexCount; i++) {
            System.out.print("顶点 " + i + "：");
            for (Edge edge : adj.get(i)) {
                System.out.print(edge.to + "(权重:" + edge.weight + ") ");
            }
            System.out.println();
        }
    }
}

// 测试主类
public class GraphDemo {
    public static void main(String[] args) {
        // 初始化一个5个顶点的无向带权图（顶点编号：0,1,2,3,4）
        Graph graph = new Graph(5);

        // 添加无向边
        graph.addUndirectedEdge(0, 1, 2);
        graph.addUndirectedEdge(0, 3, 6);
        graph.addUndirectedEdge(1, 2, 3);
        graph.addUndirectedEdge(1, 4, 4);
        graph.addUndirectedEdge(2, 4, 1);
        graph.addUndirectedEdge(3, 4, 5);

        // 执行所有操作
        graph.printGraph();
        System.out.println("------------------------");
        graph.dfs(0);
        graph.bfs(0);
        System.out.println("------------------------");
        graph.dijkstra(0);
        System.out.println("------------------------");
        graph.prim(0);
    }
}
```

1. 核心结构：使用`List<List<Edge>>`实现邻接表，`Edge`类封装边的目标顶点和权重；
2. 功能覆盖：无向图构建、DFS/BFS遍历、Dijkstra最短路径、Prim最小生成树；
3. 运行结果：控制台会依次打印图结构、遍历结果、最短路径、最小生成树，直观展示所有图论常用操作。
1. 图的核心是**顶点+边**，邻接表是Java实现图的最优选择；
2. 常用操作分为**构建、遍历、路径、生成树**四大类，覆盖图论基础应用场景；
3. 代码适配无向带权图，可快速修改为有向图（仅需替换边添加方法）。

### [200. 岛屿数量](https://leetcode.cn/problems/number-of-islands/)

1. 题目描述
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。此外，你可以假设该网格的四条边均被水包围。
示例 1：
输入：grid = [
    ['1','1','1','1','0'],
    ['1','1','0','1','0'],
    ['1','1','0','0','0'],
    ['0','0','0','0','0']
]
输出：1
示例 2：
输入：grid = [
    ['1','1','0','0','0'],
    ['1','1','0','0','0'],
    ['0','0','1','0','0'],
    ['0','0','0','1','1']
]
输出：3
提示：
m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] 的值为 '0' 或 '1'

2. 算法思想+代码
- 解法一：深度优先搜索（DFS）
算法思想：遍历二维网格中的每一个单元格，当遍历到值为'1'的陆地时，说明发现一座新岛屿，岛屿数量加1；通过深度优先搜索递归遍历该陆地上下左右四个方向的相邻单元格，将所有相连的陆地标记为'0'（水），避免重复统计同一座岛屿；递归终止条件为单元格超出网格边界或值为'0'。
Java代码：
```java
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        // 边界判断
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        // 遍历整个网格
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    // 深度优先搜索标记相连陆地
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    // 深度优先搜索方法
    private void dfs(char[][] grid, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;
        // 越界或当前为水，直接返回
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0') {
            return;
        }
        // 将当前陆地标记为水
        grid[i][j] = '0';
        // 上下左右四个方向递归
        dfs(grid, i - 1, j); // 上
        dfs(grid, i + 1, j); // 下
        dfs(grid, i, j - 1); // 左
        dfs(grid, i, j + 1); // 右
    }

    // 测试示例
    public static void main(String[] args) {
        NumberOfIslands solution = new NumberOfIslands();
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println(solution.numIslands(grid1));

        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println(solution.numIslands(grid2));
    }
}
```

- 解法二：广度优先搜索（BFS）
算法思想：遍历二维网格中的每一个单元格，当遍历到值为'1'的陆地时，岛屿数量加1；使用队列存储当前陆地的坐标，将当前陆地标记为'0'；循环取出队列中的坐标，遍历其上下左右四个方向的相邻单元格，若为陆地则加入队列并标记为'0'，直到队列为空，完成当前岛屿的遍历。
Java代码：
```java
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslandsBFS {
    public int numIslands(char[][] grid) {
        // 边界判断
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        // 遍历整个网格
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    // 广度优先搜索标记相连陆地
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    // 广度优先搜索方法
    private void bfs(char[][] grid, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        // 标记当前陆地为水
        grid[i][j] = '0';
        // 上下左右四个方向
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            // 遍历四个方向
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                // 未越界且为陆地
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == '1') {
                    queue.offer(new int[]{newX, newY});
                    grid[newX][newY] = '0';
                }
            }
        }
    }

    // 测试示例
    public static void main(String[] args) {
        NumberOfIslandsBFS solution = new NumberOfIslandsBFS();
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println(solution.numIslands(grid1));

        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println(solution.numIslands(grid2));
    }
}
```

### [994. 腐烂的橘子](https://leetcode.cn/problems/rotting-oranges/)

1. 题目描述
在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：值 0 代表空单元格；值 1 代表新鲜橘子；值 2 代表腐烂的橘子。每分钟，腐烂的橘子周围 4 个方向上相邻的新鲜橘子都会腐烂。返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
示例 1：输入：grid = [[2,1,1],[1,1,0],[0,1,1]]，输出：4
示例 2：输入：grid = [[2,1,1],[0,1,1],[1,0,1]]，输出：-1，解释：左下角的橘子永远不会腐烂，因为腐烂只会发生在 4 个方向上。
示例 3：输入：grid = [[0,2]]，输出：0，解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0。
提示：m == grid.length，n == grid[i].length，1 <= m, n <= 10，grid[i][j] 仅为 0、1 或 2

2. 算法思想+代码
- 解法一：广度优先搜索（BFS）
  算法思想：本题属于多源广度优先搜索场景，初始状态下所有腐烂的橘子均为搜索起点。首先遍历整个网格，完成两个操作：统计新鲜橘子的总数量，将所有腐烂橘子的行列坐标存入队列。随后以层序的方式遍历队列，每一层遍历对应一分钟的时间流逝；遍历当前层所有腐烂橘子时，向上下左右四个方向检查相邻单元格，若存在新鲜橘子则将其腐烂，新鲜橘子数量减一，并将该橘子坐标加入队列作为下一层的搜索节点。当队列遍历完成后，若新鲜橘子数量为0，返回层序遍历的总层数（即分钟数）；若仍有新鲜橘子剩余，返回-1。特殊情况：初始无新鲜橘子时，直接返回0。
  Java代码：
```java
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        // 获取网格的行数和列数
        int m = grid.length;
        int n = grid[0].length;
        // 队列存储腐烂橘子的坐标，数组元素0为行坐标，1为列坐标
        Queue<int[]> queue = new LinkedList<>();
        // 统计新鲜橘子的数量
        int freshCount = 0;

        // 第一次遍历网格：初始化队列，统计新鲜橘子数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    // 将初始腐烂橘子加入队列
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    // 新鲜橘子计数
                    freshCount++;
                }
            }
        }

        // 定义上下左右四个移动方向
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 记录橘子完全腐烂的分钟数
        int minute = 0;

        // BFS循环：有新鲜橘子且队列不为空时执行
        while (freshCount > 0 && !queue.isEmpty()) {
            // 当前层的腐烂橘子数量，控制层序遍历
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];
                // 遍历四个方向
                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    // 判断坐标越界，且当前位置是新鲜橘子
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1) {
                        // 新鲜橘子变为腐烂
                        grid[newX][newY] = 2;
                        // 新鲜橘子数量减1
                        freshCount--;
                        // 新腐烂的橘子加入队列
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            // 每完成一层遍历，时间加1分钟
            minute++;
        }

        // 无剩余新鲜橘子返回分钟数，否则返回-1
        return freshCount == 0 ? minute : -1;
    }
}
```

### [207. 课程表](https://leetcode.cn/problems/course-schedule/)

1. 题目描述
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则必须先学习课程 bi 。例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
示例 1：输入：numCourses = 2, prerequisites = [[1,0]] 输出：true 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
示例 2：输入：numCourses = 2, prerequisites = [[1,0],[0,1]] 输出：false 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
提示：1 <= numCourses <= 2000，0 <= prerequisites.length <= 5000，prerequisites[i].length == 2，0 <= ai, bi < numCourses，prerequisites[i] 中的所有课程对互不相同

2. 算法思想+代码
- 解法一：广度优先搜索（BFS）拓扑排序
  算法思想：问题等价于判断有向图中是否存在环，无环则可完成所有课程。基于拓扑排序的BFS实现，步骤为构建有向图邻接表并统计每个节点的入度；将入度为0的节点加入队列；依次取出队列节点，遍历其邻接节点并将邻接节点入度减1，入度为0则入队；统计遍历的节点总数，若等于总课程数则无环返回true，否则有环返回false。
  Java代码：
  ```java
  import java.util.*;
  class Solution {
      public boolean canFinish(int numCourses, int[][] prerequisites) {
          // 构建图的邻接表
          List<List<Integer>> adjacency = new ArrayList<>();
          // 入度数组，记录每个课程的先修课程数量
          int[] inDegree = new int[numCourses];
          // 初始化邻接表
          for (int i = 0; i < numCourses; i++) {
              adjacency.add(new ArrayList<>());
          }
          // 填充邻接表和入度数组
          for (int[] req : prerequisites) {
              int cur = req[0];
              int pre = req[1];
              adjacency.get(pre).add(cur);
              inDegree[cur]++;
          }
          // 队列存储入度为0的节点（无先修课程的课程）
          Queue<Integer> queue = new LinkedList<>();
          for (int i = 0; i < numCourses; i++) {
              if (inDegree[i] == 0) {
                  queue.offer(i);
              }
          }
          // 统计已学习的课程数量
          int count = 0;
          while (!queue.isEmpty()) {
              int course = queue.poll();
              count++;
              // 遍历当前课程的后续课程，入度减1
              for (int next : adjacency.get(course)) {
                  inDegree[next]--;
                  if (inDegree[next] == 0) {
                      queue.offer(next);
                  }
              }
          }
          // 已学习课程数等于总课程数，说明无环
          return count == numCourses;
      }
  }
  ```
- 解法二：深度优先搜索（DFS）判断有向环
  算法思想：通过DFS遍历有向图，标记节点三种状态：0表示未访问，1表示正在访问（处于当前递归栈中），2表示已访问完成。遍历每个未访问节点，递归访问其邻接节点；若遇到状态为1的节点，说明存在环；若邻接节点全部访问完毕，将当前节点标记为2。全程无环则返回true，存在环则返回false。
  Java代码：
  ```java
  import java.util.*;
  class Solution {
      public boolean canFinish(int numCourses, int[][] prerequisites) {
          // 构建图的邻接表
          List<List<Integer>> adjacency = new ArrayList<>();
          for (int i = 0; i < numCourses; i++) {
              adjacency.add(new ArrayList<>());
          }
          for (int[] req : prerequisites) {
              adjacency.get(req[1]).add(req[0]);
          }
          // 标记节点状态：0=未访问，1=访问中，2=已访问
          int[] visited = new int[numCourses];
          // 遍历所有课程节点
          for (int i = 0; i < numCourses; i++) {
              // 检测到环直接返回false
              if (!dfs(adjacency, visited, i)) {
                  return false;
              }
          }
          return true;
      }
  
      // DFS递归：判断以node为起点的子图是否无环
      private boolean dfs(List<List<Integer>> adjacency, int[] visited, int node) {
          // 节点在当前递归链中，形成环
          if (visited[node] == 1) {
              return false;
          }
          // 节点已遍历完成，无环
          if (visited[node] == 2) {
              return true;
          }
          // 标记为正在访问
          visited[node] = 1;
          // 递归遍历所有邻接节点
          for (int next : adjacency.get(node)) {
              if (!dfs(adjacency, visited, next)) {
                  return false;
              }
          }
          // 标记为已访问完成
          visited[node] = 2;
          return true;
      }
  }
  ```

### [208. 实现 Trie (前缀树)](https://leetcode.cn/problems/implement-trie-prefix-tree/)

1. 题目描述
Trie（发音类似 "try"）或者说前缀树是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。请你实现 Trie 类：
Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
示例：
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]
解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True
提示：
1 <= word.length, prefix.length <= 2000
word 和 prefix 仅由小写英文字母组成
insert、search 和 startsWith 调用次数总计不超过 3 * 10^4 次

2. 算法思想+代码
算法思想：该实现为前缀树的最优高效版本，采用极简节点结构，每个节点仅包含对应26个小写英文字母的子节点数组和字符串结束标记，根节点直接初始化无冗余操作。插入字符串时，从根节点开始逐字符遍历，不存在对应子节点则创建，遍历完成后标记结尾节点；查找完整字符串时，逐字符匹配，中途无对应节点直接返回false，遍历完成后校验结尾标记；前缀匹配仅需逐字符完成遍历，无需校验结尾标记，全程无多余逻辑和内存开销，执行速度极快。
```java
class Trie {

    class Node {
        Node[] child = new Node[26];
        boolean isEnd;
    }

    Node root = new Node();

    public Trie() {

    }

    public void insert(String word) {
        Node cur = root;
        for (char w : word.toCharArray()) {
            if (cur.child[w - 'a'] == null) {
                cur.child[w - 'a'] = new Node();
            }
            cur = cur.child[w - 'a'];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        Node cur = root;
        for (char w : word.toCharArray()) {
            if (cur.child[w - 'a'] == null) {
                return false;
            }
            cur = cur.child[w - 'a'];
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node cur = root;
        for (char w : prefix.toCharArray()) {
            if (cur.child[w - 'a'] == null) {
                return false;
            }
            cur = cur.child[w - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
```

## 回溯

**组合可重复**：for 从`i`开始，能反复选自己

**组合不重复**：for 从`i+1`开始，只往后选不回头

**排列要有序**：for 从`0`开始，从头遍历全元素



1. 回溯算法定义：回溯是一种基于深度优先搜索的暴力枚举算法思想，核心逻辑是沿着一个方向不断尝试选择元素，当发现当前路径无法生成有效解时，立即撤销上一步的选择（回溯），回到上一状态尝试其他可选分支，最终遍历所有可能的解空间，找到符合要求的全部解或最优解。该算法无需提前预知所有路径，通过递归实现状态的递进与回退，是解决组合、排列、子集、分割类问题的核心方法。
2. 回溯算法的常见操作
   - 路径记录：维护当前递归过程中已选择的元素集合，代表当前的搜索路径
   - 选择列表：确定当前步骤中可以选择的所有候选元素，是分支遍历的依据
   - 终止条件：定义递归的边界，当满足条件时将当前路径加入结果集
   - 回溯回退：递归深入后，撤销最后一步的选择，恢复路径和选择列表的状态
   - 剪枝优化：提前过滤不符合条件的分支，避免无效的递归搜索，提升效率

以下代码实现了回溯最常用的三个场景：全排列、子集、组合，完整覆盖上述所有常见操作，代码可直接运行：
```java
import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法通用Demo
 * 包含：全排列、子集、组合 三大经典回溯场景
 */
public class BacktrackDemo {
    // 全局存储最终结果
    private static final List<List<Integer>> result = new ArrayList<>();
    // 全局存储当前递归的搜索路径
    private static final List<Integer> path = new ArrayList<>();

    // ==================== 1. 全排列（无重复元素）====================
    public static void permute(int[] nums) {
        // 标记元素是否被选中，用于剪枝
        boolean[] used = new boolean[nums.length];
        backtrackPermute(nums, used);
    }

    // 全排列回溯核心
    private static void backtrackPermute(int[] nums, boolean[] used) {
        // 终止条件：路径长度=数组长度，生成有效排列
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 遍历所有可选元素
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // 剪枝：跳过已选元素
            used[i] = true;       // 做出选择
            path.add(nums[i]);    // 记录路径
            backtrackPermute(nums, used); // 递归深入
            path.remove(path.size() - 1); // 撤销选择（核心回溯操作）
            used[i] = false;      // 恢复状态
        }
    }

    // ==================== 2. 子集 ====================
    public static void subsets(int[] nums) {
        backtrackSubset(nums, 0);
    }

    // 子集回溯核心
    private static void backtrackSubset(int[] nums, int start) {
        // 终止条件：无显式终止，所有路径都是有效子集
        result.add(new ArrayList<>(path));
        // 从start遍历，避免重复子集
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrackSubset(nums, i + 1);
            path.remove(path.size() - 1); // 回溯
        }
    }

    // ==================== 3. 组合（n个数选k个）====================
    public static void combine(int n, int k) {
        backtrackCombine(n, k, 1);
    }

    // 组合回溯核心（带剪枝优化）
    private static void backtrackCombine(int n, int k, int start) {
        // 终止条件：路径长度=k，生成有效组合
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 剪枝：提前结束循环，减少无效搜索
        for (int i = start; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backtrackCombine(n, k, i + 1);
            path.remove(path.size() - 1); // 回溯
        }
    }

    // 重置数据（多次调用时清空）
    private static void reset() {
        result.clear();
        path.clear();
    }

    // 测试主方法
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        System.out.println("=== 数组 [1,2,3] 的全排列 ===");
        permute(nums);
        System.out.println(result);
        reset();

        System.out.println("\n=== 数组 [1,2,3] 的所有子集 ===");
        subsets(nums);
        System.out.println(result);
        reset();

        System.out.println("\n=== 从数字1-3中选2个的组合 ===");
        combine(3, 2);
        System.out.println(result);
    }
}
```

1. 全局变量`path`负责**记录当前路径**，`result`负责存储所有有效解
2. 每个回溯方法都遵循固定模板：`做出选择 → 递归 → 撤销选择（回溯）`
3. 包含**终止条件**判断、**剪枝优化**等核心操作
4. 重置方法`reset()`用于多次调用回溯场景时清空数据
5. 主方法直接测试三个最常用的回溯案例，运行后可直观看到结果
1. 回溯的核心是**递归+回退**，本质是暴力枚举所有可能解
2. 固定模板：选择→递归→撤销选择，是编写回溯代码的通用思路
3. 全排列、子集、组合是回溯算法的基础应用，掌握后可快速解决分割、棋盘等复杂回溯问题

### [46. 全排列](https://leetcode.cn/problems/permutations/)

1. 题目描述
给定一个不含重复数字的数组 nums ，返回其所有可能的全排列 。你可以按任意顺序返回答案。
示例 1：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：
输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：
输入：nums = [1]
输出：[[1]]
提示：
1 <= nums.length <= 6
-10 <= nums[i] <= 10
nums 中的所有整数 互不相同

2. 解法一：回溯法
- 算法思想：基于深度优先搜索的回溯思想，使用标记数组记录数组元素是否被使用，依次选取未使用的数字加入当前排列组合，当当前排列长度与原数组长度一致时，将其加入结果集；之后进行回溯操作，撤销上一步的选择，继续遍历剩余未使用的数字，最终枚举所有合法的全排列。
- Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class Permutations {
    // 存储最终的全排列结果
    List<List<Integer>> res = new ArrayList<>();
    // 存储当前遍历的排列
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 标记数组：记录对应位置的数字是否已被使用
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used);
        return res;
    }

    // 回溯核心方法
    private void backtrack(int[] nums, boolean[] used) {
        // 递归终止条件：当前排列长度等于数组长度，说明找到一个全排列
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历数组中的每个数字
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字已被使用，跳过
            if (used[i]) {
                continue;
            }
            // 选择当前数字
            used[i] = true;
            path.add(nums[i]);
            // 递归深入
            backtrack(nums, used);
            // 回溯：撤销选择
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    // 测试
    public static void main(String[] args) {
        Permutations solution = new Permutations();
        int[] nums = {1,2,3};
        System.out.println(solution.permute(nums));
    }
}
```

3. 解法二：递归交换法
- 算法思想：通过递归交换数组元素的位置实现全排列，固定数组的第k位元素，递归处理k+1到末尾的元素；当递归到数组末尾时，记录当前数组的排列；回溯时将元素交换回原位置，遍历所有元素交换组合，无需额外使用标记数组，空间效率更高。
- Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class PermutationsSwap {
    // 存储最终结果
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 从第0位开始递归交换
        backtrack(nums, 0);
        return res;
    }

    // 递归交换核心方法
    private void backtrack(int[] nums, int start) {
        // 递归终止条件：起始索引到达数组末尾，记录当前排列
        if (start == nums.length) {
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            res.add(path);
            return;
        }
        // 遍历从start开始的所有元素，与start位置交换
        for (int i = start; i < nums.length; i++) {
            // 交换元素
            swap(nums, start, i);
            // 递归处理下一个位置
            backtrack(nums, start + 1);
            // 回溯：交换回原位置
            swap(nums, start, i);
        }
    }

    // 交换数组中两个位置的元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 测试
    public static void main(String[] args) {
        PermutationsSwap solution = new PermutationsSwap();
        int[] nums = {1,2,3};
        System.out.println(solution.permute(nums));
    }
}
```

### [78. 子集](https://leetcode.cn/problems/subsets/)

1. 题目描述
给你一个整数数组 nums ，数组中的元素互不相同。返回该数组所有可能的子集（幂集）。解集不能包含重复的子集，你可以按任意顺序返回解集。
示例 1：输入：nums = [1,2,3]，输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：输入：nums = [0]，输出：[[],[0]]
提示：1 <= nums.length <= 10，-10 <= nums[i] <= 10，nums 中的所有元素互不相同
2. 解法一：回溯法
- 算法思想：子集问题是经典的回溯算法应用场景，核心逻辑为对数组中的每个元素做两种选择（选取该元素、不选取该元素），通过递归遍历数组所有元素，当遍历到数组末尾时，将当前构建的子集加入结果集；数组元素无重复，因此无需额外去重操作，直接回溯即可。
- Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class Subsets {
    // 存储最终结果
    List<List<Integer>> res = new ArrayList<>();
    // 存储当前子集
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    // 回溯函数，start表示当前遍历的起始索引
    private void backtrack(int[] nums, int start) {
        // 将当前子集加入结果集（每一步的路径都是一个有效子集）
        res.add(new ArrayList<>(path));
        // 遍历数组，从start开始避免重复子集
        for (int i = start; i < nums.length; i++) {
            // 选择当前元素
            path.add(nums[i]);
            // 递归遍历下一个元素
            backtrack(nums, i + 1);
            // 回溯，撤销选择
            path.remove(path.size() - 1);
        }
    }
}
```
3. 解法二：迭代法
- 算法思想：从空集开始初始化结果集，依次遍历数组中的每个元素，将当前元素添加到已有的所有子集中，生成新的子集并合并到结果集中，逐步扩充得到所有子集。
- Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        // 初始化结果集，默认包含空集
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        // 遍历数组中的每个元素
        for (int num : nums) {
            // 获取当前结果集的大小
            int size = res.size();
            // 遍历已有子集，添加当前元素生成新子集
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(res.get(i));
                newSubset.add(num);
                res.add(newSubset);
            }
        }
        return res;
    }
}
```
4. 解法三：位运算
- 算法思想：数组长度为n时，子集总数量为2ⁿ，每个子集可以对应一个n位的二进制数，二进制位为1表示选取对应位置的元素，为0表示不选取；遍历0到2ⁿ-1的所有数字，根据二进制位的状态构建对应子集。
- Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        // 总子集数：2^n
        int total = 1 << n;

        // 遍历所有二进制数
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            // 遍历每一位，判断是否选取元素
            for (int i = 0; i < n; i++) {
                // 判断第i位是否为1
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            res.add(subset);
        }
        return res;
    }
}
```

### [17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)

1. 题目描述
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合，答案可以按任意顺序返回。数字到字母的映射与电话按键相同，数字1不对应任何字母。
示例 1：输入：digits = "23"，输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：输入：digits = "2"，输出：["a","b","c"]
提示：1 <= digits.length <= 4，digits[i] 是范围 ['2', '9'] 的一个数字。

2. 解法一：回溯法
- 算法思想：基于回溯的深度优先搜索思路，先构建数字与字母的映射关系，通过递归遍历输入数字的每一位，将当前数字对应的每一个字母拼接到临时字符串中；当临时字符串的长度与输入数字的长度相等时，说明完成了一个有效组合，将其加入结果集；随后回溯，移除最后拼接的字母，继续尝试当前数字的其他字母，直至遍历完所有可能的组合。
- 代码：
```java
import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {
    // 数字到字母的映射，数组索引对应电话数字，0和1无对应字母
    private final String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    // 存储最终的字母组合结果
    private List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        // 边界处理：输入为空直接返回空集合
        if (digits == null || digits.length() == 0) {
            return result;
        }
        // 启动回溯递归，初始索引为0，初始拼接字符串为空
        backtrack(digits, 0, new StringBuilder());
        return result;
    }

    /**
     * 回溯核心方法
     * @param digits 输入的数字字符串
     * @param index 当前遍历到的数字索引
     * @param sb 用于拼接字母的可变字符串
     */
    private void backtrack(String digits, int index, StringBuilder sb) {
        // 递归终止条件：拼接完成，加入结果集
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        // 获取当前数字对应的所有字母
        char currentNum = digits.charAt(index);
        String letters = letterMap[currentNum - '0'];
        // 遍历当前数字的每一个字母，进行拼接与回溯
        for (int i = 0; i < letters.length(); i++) {
            // 拼接当前字母
            sb.append(letters.charAt(i));
            // 递归处理下一个数字
            backtrack(digits, index + 1, sb);
            // 回溯：撤销最后一个拼接的字母
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 测试方法
    public static void main(String[] args) {
        LetterCombinations solution = new LetterCombinations();
        System.out.println(solution.letterCombinations("23"));
    }
}
```

3. 解法二：广度优先搜索（BFS）
- 算法思想：借助队列实现广度优先遍历，初始队列为空字符串；依次遍历输入的每一个数字，取出队列中当前所有的字符串，将每个字符串与当前数字对应的所有字母拼接，生成新字符串并入队；遍历完所有数字后，队列中的所有元素即为最终的字母组合。
- 代码：
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.utilQueue;

public class LetterCombinationsBFS {
    // 数字到字母的映射关系
    private final String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        // 边界处理
        if (digits == null || digits.length() == 0) {
            return result;
        }
        // 初始化队列，存入空字符串作为起始
        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        // 遍历每一个输入的数字
        for (int i = 0; i < digits.length(); i++) {
            String letters = letterMap[digits.charAt(i) - '0'];
            // 获取当前队列的长度，固定遍历次数
            int queueSize = queue.size();
            // 遍历当前队列的所有元素
            for (int j = 0; j < queueSize; j++) {
                String tempStr = queue.poll();
                // 拼接当前数字的所有字母，并入队
                for (char c : letters.toCharArray()) {
                    queue.offer(tempStr + c);
                }
            }
        }
        // 将队列中的结果转为集合返回
        result.addAll(queue);
        return result;
    }

    // 测试方法
    public static void main(String[] args) {
        LetterCombinationsBFS solution = new LetterCombinationsBFS();
        System.out.println(solution.letterCombinations("23"));
    }
}
```

### [39. 组合总和](https://leetcode.cn/problems/combination-sum/)

1. 题目描述
给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
对于给定的输入，保证和为 target 的不同组合数少于 150 个。

示例 1：
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。7 也是一个候选， 7 = 7 。仅有这两种组合。

示例 2：
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]

示例 3：
输入：candidates = [2], target = 1
输出：[]

提示：
1 <= candidates.length <= 30
2 <= candidates[i] <= 40
candidates 的所有元素 互不相同
1 <= target <= 40

2. 算法思想+代码
- 解法一：回溯法（深度优先搜索）
  算法思想：本题是可重复选取元素的组合枚举问题，采用回溯算法（深度优先搜索）求解。核心逻辑为递归遍历候选数组，将元素加入当前组合并扣减剩余目标值；当剩余值为0时，记录当前有效组合；若当前元素大于剩余值则剪枝，终止当前分支递归。通过固定递归的起始索引，不回头遍历之前的元素，避免生成重复组合，同时允许当前元素重复选取。
  Java代码：
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 存储最终的所有组合结果
    private List<List<Integer>> result = new ArrayList<>();
    // 存储当前递归路径中的组合元素
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 处理空数组边界情况
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        // 启动回溯递归，起始索引为0
        backTracking(candidates, target, 0);
        return result;
    }

    /**
     * 回溯递归方法
     * @param candidates 候选数组
     * @param remain 剩余需要凑齐的数值
     * @param start 遍历起始索引（去重核心）
     */
    private void backTracking(int[] candidates, int remain, int start) {
        // 递归终止条件：剩余值为0，找到有效组合
        if (remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 从start索引开始遍历，避免重复组合
        for (int i = start; i < candidates.length; i++) {
            // 剪枝操作：当前元素大于剩余值，无需继续递归
            if (candidates[i] > remain) {
                continue;
            }
            // 选择当前元素，加入路径
            path.add(candidates[i]);
            // 递归：可重复选取，因此起始索引仍为i
            backTracking(candidates, remain - candidates[i], i);
            // 回溯：撤销最后一次选择，恢复路径
            path.remove(path.size() - 1);
        }
    }

    // 测试示例
    public static void main(String[] args) {
        Solution solution = new Solution();
        // 测试示例1
        int[] arr1 = {2,3,6,7};
        System.out.println(solution.combinationSum(arr1, 7));
        // 测试示例2
        int[] arr2 = {2,3,5};
        System.out.println(solution.combinationSum(arr2, 8));
        // 测试示例3
        int[] arr3 = {2};
        System.out.println(solution.combinationSum(arr3, 1));
    }
}
```

### [22. 括号生成](https://leetcode.cn/problems/generate-parentheses/)

1. 题目描述：数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。示例 1：输入：n = 3，输出：["((()))","(()())","(())()","()(())","()()()"]；示例 2：输入：n = 1，输出：["()"]。提示：1 <= n <= 8。
2. 解法一：回溯法（深度优先搜索）
- 算法思想：通过递归回溯的方式逐位构建括号字符串，为保证括号有效性，遵循两个核心规则：左括号的使用数量小于n时，可添加左括号；右括号的使用数量小于左括号数量时，可添加右括号。当构建的字符串长度等于2n时，即为有效括号组合，将其加入结果集合。
- Java代码
```java
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    // 存储最终的有效括号组合
    private List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        // 开启回溯，初始左括号数、右括号数均为0，拼接字符串为空
        backtrack(n, 0, 0, "");
        return result;
    }

    /**
     * 回溯核心方法
     * @param n 目标括号对数
     * @param leftCount 已使用的左括号数量
     * @param rightCount 已使用的右括号数量
     * @param path 当前拼接的括号字符串
     */
    private void backtrack(int n, int leftCount, int rightCount, String path) {
        // 终止条件：字符串长度达到2n，说明生成有效组合
        if (path.length() == 2 * n) {
            result.add(path);
            return;
        }
        // 规则1：左括号未用完，可添加左括号
        if (leftCount < n) {
            backtrack(n, leftCount + 1, rightCount, path + "(");
        }
        // 规则2：右括号数量小于左括号，可添加右括号
        if (rightCount < leftCount) {
            backtrack(n, leftCount, rightCount + 1, path + ")");
        }
    }

    // 测试方法
    public static void main(String[] args) {
        GenerateParentheses solution = new GenerateParentheses();
        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(1));
    }
}
```
3. 解法二：动态规划法
- 算法思想：定义dp[i]为i对有效括号的所有组合，初始状态dp[0]为空列表。状态转移逻辑：对于i对括号，可拆分为「( + dp[j]的组合 + ) + dp[i-j-1]的组合」，其中j的取值范围为0到i-1，遍历所有拆分方式，将拼接后的字符串加入dp[i]，最终dp[n]即为n对括号的所有有效组合。
- Java代码
```java
import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesesDP {
    public List<String> generateParenthesis(int n) {
        // 动态规划数组，dp.get(i)存储i对括号的有效组合
        List<List<String>> dp = new ArrayList<>();
        // 初始化：0对括号的组合为空字符串
        dp.add(new ArrayList<>());
        dp.get(0).add("");

        // 依次计算1~n对括号的有效组合
        for (int i = 1; i <= n; i++) {
            dp.add(new ArrayList<>());
            // 遍历所有拆分方式 j ∈ [0, i-1]
            for (int j = 0; j < i; j++) {
                // 遍历dp[j]的所有组合
                for (String left : dp.get(j)) {
                    // 遍历dp[i-j-1]的所有组合
                    for (String right : dp.get(i - j - 1)) {
                        // 拼接生成新的有效组合
                        dp.get(i).add("(" + left + ")" + right);
                    }
                }
            }
        }
        // 返回n对括号的结果集
        return dp.get(n);
    }

    // 测试方法
    public static void main(String[] args) {
        GenerateParenthesesDP solution = new GenerateParenthesesDP();
        System.out.println(solution.generateParenthesis(3));
        System.out.println(solution.generateParenthesis(1));
    }
}
```

### [79. 单词搜索](https://leetcode.cn/problems/word-search/)

1. 题目描述
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
示例 1：输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCCED" 输出：true
示例 2：输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "SEE" 输出：true
示例 3：输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = "ABCB" 输出：false
提示：m == board.length，n = board[i].length，1 <= m, n <= 6，1 <= word.length <= 15，board 和 word 仅由大小写英文字母组成
进阶：可以使用搜索剪枝的技术优化解决方案，在网格更大时更快解决问题

2. 解法一：深度优先搜索（DFS）+ 回溯算法
- 算法思想：该问题属于二维网格的路径查找问题，核心采用深度优先搜索遍历所有可能的路径，结合回溯法处理单元格的重复使用问题。遍历网格中的每一个单元格作为起始点，若当前单元格字符与单词首字符匹配，则向上下左右四个相邻方向递归搜索；递归过程中标记当前单元格为已访问，避免重复使用；当递归匹配到单词的最后一个字符时，说明找到有效路径，返回true；若某一方向搜索失败，则回溯恢复单元格的原始状态，继续探索其他方向；若所有起始点和路径都遍历完毕仍未匹配成功，返回false。
- Java代码
```java
public class Solution {
    // 定义上下左右四个方向的偏移量
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, cols;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        // 遍历每一个单元格作为起始搜索点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * DFS递归搜索
     * @param board 字符网格
     * @param word 目标单词
     * @param i 当前行坐标
     * @param j 当前列坐标
     * @param index 当前匹配到单词的第几个字符
     * @return 是否匹配成功
     */
    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        // 递归终止条件：匹配完所有字符
        if (index == word.length()) {
            return true;
        }
        // 越界判断 或 当前字符不匹配 直接返回false
        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != word.charAt(index)) {
            return false;
        }
        // 临时保存当前字符，标记为已访问（用特殊字符替代）
        char temp = board[i][j];
        board[i][j] = '#';
        // 向四个方向递归搜索
        for (int[] dir : dirs) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];
            if (dfs(board, word, newRow, newCol, index + 1)) {
                return true;
            }
        }
        // 回溯：恢复当前单元格的原始字符
        board[i][j] = temp;
        return false;
    }
}
```

3. 解法二：深度优先搜索（DFS）+ 回溯 + 剪枝优化
- 算法思想：在基础DFS+回溯的基础上增加剪枝策略，提前终止无效的搜索流程，优化执行效率。核心剪枝点：1. 若单词长度大于网格总字符数，直接返回false，不可能匹配；2. 递归过程中字符不匹配、坐标越界时立即返回，不进行后续操作；3. 找到有效路径后直接逐层返回true，终止所有剩余搜索。其余逻辑与基础解法一致，通过标记已访问单元格避免重复使用，递归探索四个方向，搜索失败后回溯恢复状态。
- Java代码
```java
public class Solution {
    // 四个搜索方向
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, cols;
    private char[] words;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        words = word.toCharArray();
        // 剪枝1：单词长度超过网格总字符数，直接返回false
        if (words.length > rows * cols) {
            return false;
        }
        // 遍历所有起始点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 深度优先搜索+回溯
    private boolean dfs(char[][] board, int i, int j, int index) {
        // 匹配完成
        if (index == words.length) {
            return true;
        }
        // 剪枝2：越界或字符不匹配，直接终止搜索
        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != words[index]) {
            return false;
        }
        // 标记已访问
        char temp = board[i][j];
        board[i][j] = '#';
        // 遍历四个方向
        for (int[] dir : dirs) {
            if (dfs(board, i + dir[0], j + dir[1], index + 1)) {
                return true;
            }
        }
        // 回溯恢复
        board[i][j] = temp;
        return false;
    }
}
```

### [131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/)

1. 题目描述
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。返回 s 所有可能的分割方案。
示例 1：输入：s = "aab" 输出：[["a","a","b"],["aa","b"]]
示例 2：输入：s = "a" 输出：[["a"]]
提示：1 <= s.length <= 16，s 仅由小写英文字母组成

- 解法一：回溯法（基础版）
  - 算法思想：该问题属于组合分割类问题，采用回溯（深度优先搜索）解决。从字符串的起始索引开始，遍历所有可能的结束索引，截取子串并通过双指针法判断是否为回文；若为回文，将其加入当前分割路径，递归处理剩余子串；当索引到达字符串末尾时，将路径加入结果集；递归回溯时移除最后加入的子串，继续探索其他分割可能。
  - 代码
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    // 存储最终所有分割方案
    List<List<String>> res = new ArrayList<>();
    // 存储当前分割的临时路径
    List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0);
        return res;
    }

    // 回溯核心方法，start为当前分割的起始索引
    private void backtrack(String s, int start) {
        // 终止条件：起始索引等于字符串长度，完成一次有效分割
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历所有可能的结束位置
        for (int i = start; i < s.length(); i++) {
            // 判断子串是否为回文
            if (isPalindrome(s, start, i)) {
                path.add(s.substring(start, i + 1));
                // 递归处理下一段子串
                backtrack(s, i + 1);
                // 回溯，撤销当前选择
                path.remove(path.size() - 1);
            }
        }
    }

    // 双指针法判断子串s[left, right]是否为回文
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

- 解法二：回溯+动态规划预处理（优化版）
  - 算法思想：为避免重复判断回文子串，先通过动态规划预处理字符串，生成二维布尔数组dp，dp[i][j]表示字符串索引i到j的子串是否为回文。预处理规则：单个字符一定是回文；两个相邻字符相等则为回文；长度大于2的子串，首尾字符相等且中间子串为回文，则当前子串为回文。预处理完成后，通过回溯法分割，直接查询dp数组判断回文，提升执行效率。
  - 代码
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    boolean[][] dp; // 动态规划数组，标记回文子串

    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];
        // 动态规划预处理所有子串的回文状态
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1]);
            }
        }
        backtrack(s, 0);
        return res;
    }

    // 回溯分割方法
    private void backtrack(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            // 直接查询预处理结果，无需重复判断
            if (dp[start][i]) {
                path.add(s.substring(start, i + 1));
                backtrack(s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
```

### [51. N 皇后](https://leetcode.cn/problems/n-queens/)

1. 题目描述
按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。给你一个整数 n ，返回所有不同的 n 皇后问题的解决方案。每一种解法包含一个不同的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
示例 1：输入：n = 4，输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]，解释：4 皇后问题存在两个不同的解法。
示例 2：输入：n = 1，输出：[["Q"]]
提示：1 <= n <= 9

2. 算法思想+代码
- 解法一：基础回溯法（逐行放置+暴力校验）
  算法思想：由于每行只能放置一个皇后，因此采用按行递归的回溯策略。从第一行开始，依次尝试在每一列放置皇后，放置前校验当前位置是否与已放置的皇后冲突（不在同一列、同一主对角线、同一副对角线）。若合法则标记皇后，递归处理下一行；当递归到最后一行时，说明找到一组有效解，将其存入结果集。递归返回后回溯，撤销当前皇后的放置，继续尝试下一列。
  Java代码：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // 存储所有合法的解法
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 初始化n*n的棋盘，默认用.填充
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        // 从第0行开始回溯
        backtrack(board, 0);
        return result;
    }

    // 回溯核心方法：row为当前要放置皇后的行
    private void backtrack(char[][] board, int row) {
        // 递归终止条件：所有行都放置完成，记录解法
        if (row == board.length) {
            result.add(convertBoardToList(board));
            return;
        }
        int n = board[row].length;
        // 遍历当前行的每一列
        for (int col = 0; col < n; col++) {
            // 判断当前位置是否可以放置皇后
            if (isValid(board, row, col)) {
                // 放置皇后
                board[row][col] = 'Q';
                // 递归处理下一行
                backtrack(board, row + 1);
                // 回溯：撤销当前位置的皇后
                board[row][col] = '.';
            }
        }
    }

    // 校验位置(row, col)是否合法
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 检查同一列是否已有皇后
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        // 检查左上对角线是否已有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查右上对角线是否已有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    // 将棋盘字符数组转换为字符串列表
    private List<String> convertBoardToList(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
}
```
- 解法二：优化回溯法（布尔数组标记冲突+回溯）
  算法思想：优化基础回溯的冲突校验逻辑，使用三个布尔数组分别标记已占用的列、主对角线（行-列固定，偏移n避免负数）、副对角线（行+列固定）。放置皇后时直接通过数组判断冲突，无需遍历校验，提升执行效率。整体回溯逻辑不变，按行递归放置、回溯记录有效解。
  Java代码：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    List<List<String>> result = new ArrayList<>();
    // 标记列是否被皇后占用
    boolean[] usedCol;
    // 标记主对角线（左上→右下）是否被占用
    boolean[] usedMainDiag;
    // 标记副对角线（右上→左下）是否被占用
    boolean[] usedSubDiag;
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        usedCol = new boolean[n];
        // 对角线最大长度为2n
        usedMainDiag = new boolean[2 * n];
        usedSubDiag = new boolean[2 * n];
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(board, 0);
        return result;
    }

    private void backtrack(char[][] board, int row) {
        // 所有行放置完毕，保存解法
        if (row == n) {
            result.add(convertBoardToList(board));
            return;
        }
        // 遍历当前行所有列
        for (int col = 0; col < n; col++) {
            // 主对角线索引：row-col + n 避免负数
            int mainIdx = row - col + n;
            // 副对角线索引：row+col
            int subIdx = row + col;
            // 无冲突则放置皇后
            if (!usedCol[col] && !usedMainDiag[mainIdx] && !usedSubDiag[subIdx]) {
                // 标记占用
                usedCol[col] = true;
                usedMainDiag[mainIdx] = true;
                usedSubDiag[subIdx] = true;
                board[row][col] = 'Q';
                // 递归下一行
                backtrack(board, row + 1);
                // 回溯撤销标记
                board[row][col] = '.';
                usedSubDiag[subIdx] = false;
                usedMainDiag[mainIdx] = false;
                usedCol[col] = false;
            }
        }
    }

    private List<String> convertBoardToList(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
}
```

## 二分查找

1. 定义
二分查找（Binary Search）又称折半查找，是专门针对**有序数据集**的高效查找算法，核心逻辑是每次将查找区间折半，通过对比中间元素与目标值的大小，快速缩小查找范围，最终定位目标元素或确认元素不存在；该算法有严格限制，仅适用于有序且支持随机访问的数据集（如有序数组），无序数据集无法使用。

2. 常见操作
- 前置要求：待查找数组必须为有序序列（默认升序），且支持通过索引直接访问元素
- 边界初始化：定义左边界left=0（数组起始索引），右边界right=数组长度-1（数组末尾索引）
- 中间值计算：计算当前区间的中间索引mid，推荐写法 `mid = left + (right - left) / 2`，避免`(left + right)`整数溢出问题
- 区间收缩：对比数组[mid]与目标值target，相等则返回mid；target < 数组[mid]则收缩右边界`right=mid-1`；target > 数组[mid]则收缩左边界`left=mid+1`
- 终止条件：left > right 时，说明目标元素不存在，返回-1
- 实现方式：分为迭代实现（常用，无栈溢出风险）和递归实现（代码简洁，深度过大可能栈溢出）
- 效率指标：时间复杂度最坏为O(log n)，空间复杂度迭代版O(1)、递归版O(log n)
- 适用场景：有序静态数据集的高频查找，不适合频繁增删的动态数据集

```java
public class BinarySearchDemo {
    // 迭代实现二分查找（最常用，推荐使用）
    public static int binarySearchByIteration(int[] arr, int target) {
        // 初始化左右边界
        int left = 0;
        int right = arr.length - 1;
        // 循环查找，直到左边界超过右边界
        while (left <= right) {
            // 计算中间索引，规避整数溢出
            int mid = left + (right - left) / 2;
            // 找到目标值，直接返回索引
            if (arr[mid] == target) {
                return mid;
            }
            // 目标值更小，收缩右边界，查找左半区间
            else if (target < arr[mid]) {
                right = mid - 1;
            }
            // 目标值更大，收缩左边界，查找右半区间
            else {
                left = mid + 1;
            }
        }
        // 循环结束未找到，返回-1
        return -1;
    }

    // 递归实现二分查找
    public static int binarySearchByRecursion(int[] arr, int target, int left, int right) {
        // 递归终止条件：边界交叉，未找到元素
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (target < arr[mid]) {
            // 递归查找左半区间
            return binarySearchByRecursion(arr, target, left, mid - 1);
        } else {
            // 递归查找右半区间
            return binarySearchByRecursion(arr, target, mid + 1, right);
        }
    }

    public static void main(String[] args) {
        // 二分查找必须使用【有序数组】
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int targetExist = 7;   // 存在的目标值
        int targetNotExist = 10; // 不存在的目标值

        // 测试迭代版二分查找
        System.out.println("===== 迭代版二分查找测试 =====");
        int index1 = binarySearchByIteration(sortedArray, targetExist);
        System.out.println("目标值 " + targetExist + " 的索引：" + index1);
        int index2 = binarySearchByIteration(sortedArray, targetNotExist);
        System.out.println("目标值 " + targetNotExist + " 的索引：" + index2);

        // 测试递归版二分查找
        System.out.println("\n===== 递归版二分查找测试 =====");
        int index3 = binarySearchByRecursion(sortedArray, targetExist, 0, sortedArray.length - 1);
        System.out.println("目标值 " + targetExist + " 的索引：" + index3);
        int index4 = binarySearchByRecursion(sortedArray, targetNotExist, 0, sortedArray.length - 1);
        System.out.println("目标值 " + targetNotExist + " 的索引：" + index4);
    }
}
```

- 迭代版是实际开发中首选方案，无递归栈溢出风险，空间复杂度为O(1)
- 递归版代码更简洁，适合理解算法逻辑，大数据量场景不推荐
- 中间索引的计算方式是算法优化点，必须避免整数溢出
- 测试用例覆盖了「目标值存在」和「目标值不存在」两种核心场景
- 运行结果：找到目标值返回对应数组索引，未找到返回-1

```
===== 迭代版二分查找测试 =====
目标值 7 的索引：3
目标值 10 的索引：-1

===== 递归版二分查找测试 =====
目标值 7 的索引：3
目标值 10 的索引：-1
```

### [35. 搜索插入位置](https://leetcode.cn/problems/search-insert-position/)

1. 题目描述：给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。请必须使用时间复杂度为 O(log n) 的算法。示例 1:输入: nums = [1,3,5,6], target = 5，输出: 2；示例 2:输入: nums = [1,3,5,6], target = 2，输出: 1；示例 3:输入: nums = [1,3,5,6], target = 7，输出: 4。提示:1 <= nums.length <= 104，-104 <= nums[i] <= 104，nums 为无重复元素的升序排列数组，-104 <= target <= 104
2. 算法思想+代码
- 算法思想：题目要求时间复杂度为O(log n)，因此采用二分查找算法，该算法适用于有序数组的高效查找。初始化左指针指向数组起始索引0，右指针指向数组末尾索引；循环计算中间索引，对比中间元素与目标值：若相等则直接返回中间索引；若中间元素小于目标值，说明目标值在右半区间，左指针右移；若中间元素大于目标值，说明目标值在左半区间，右指针左移；循环结束后，左指针的位置即为目标值不存在时的插入位置。
- Java代码
```java
public class Solution {
    public int searchInsert(int[] nums, int target) {
        // 定义左指针
        int left = 0;
        // 定义右指针
        int right = nums.length - 1;
        // 二分查找循环条件
        while (left <= right) {
            // 计算中间索引，避免整数溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 找到目标值，返回对应索引
                return mid;
            } else if (nums[mid] < target) {
                // 目标值在右侧区间，更新左指针
                left = mid + 1;
            } else {
                // 目标值在左侧区间，更新右指针
                right = mid - 1;
            }
        }
        // 未找到目标值，返回插入位置
        return left;
    }

    // 测试方法
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1,3,5,6};
        System.out.println(s.searchInsert(nums,5));
        System.out.println(s.searchInsert(nums,2));
        System.out.println(s.searchInsert(nums,7));
    }
}
```

### [74. 搜索二维矩阵](https://leetcode.cn/problems/search-a-2d-matrix/)

1. 题目描述
给你一个满足下述两条属性的 m x n 整数矩阵：每行中的整数从左到右按非严格递增顺序排列，每行的第一个整数大于前一行的最后一个整数。给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
示例 1：输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3，输出：true
示例 2：输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13，输出：false
提示：m == matrix.length，n == matrix[i].length，1 <= m, n <= 100，-10^4 <= matrix[i][j], target <= 10^4

2. 解法1：暴力遍历法
- 算法思想：直接遍历矩阵中的每一个元素，依次与目标值target进行比较，若找到相等的元素则立即返回true，遍历完所有元素仍未找到则返回false，该方法逻辑简单直观，适合小规模矩阵场景
- Java代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // 遍历矩阵的每一行
        for (int i = 0; i < matrix.length; i++) {
            // 遍历当前行的每一列元素
            for (int j = 0; j < matrix[i].length; j++) {
                // 找到目标值，返回true
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        // 遍历结束未找到目标值，返回false
        return false;
    }
}
```

3. 解法2：二分查找法（先定位目标行，再二分查找列）
- 算法思想：利用矩阵的有序特性，先通过二分查找确定target可能存在的行，再对该行进行二分查找定位元素，相比暴力法时间复杂度更低
- Java代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 第一步：二分查找确定目标所在的行
        int top = 0, bottom = row - 1;
        while (top <= bottom) {
            int midRow = top + (bottom - top) / 2;
            // 当前行首元素大于target，向上查找
            if (matrix[midRow][0] > target) {
                bottom = midRow - 1;
            } 
            // 当前行尾元素小于target，向下查找
            else if (matrix[midRow][col - 1] < target) {
                top = midRow + 1;
            } 
            // 找到目标行，开始二分查找列
            else {
                int left = 0, right = col - 1;
                while (left <= right) {
                    int midCol = left + (right - left) / 2;
                    if (matrix[midRow][midCol] == target) {
                        return true;
                    } else if (matrix[midRow][midCol] < target) {
                        left = midCol + 1;
                    } else {
                        right = midCol - 1;
                    }
                }
                // 目标行中无目标值
                return false;
            }
        }
        // 无匹配的行
        return false;
    }
}
```

4. 解法3：二分查找法（矩阵展平为一维数组）
- 算法思想：将整个二维矩阵视为一个有序的一维数组，通过坐标转换公式，直接对整个矩阵执行一次二分查找，是最优解法
- Java代码
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 定义一维数组的左右边界
        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 一维索引转换为二维矩阵的行和列
            int i = mid / col;
            int j = mid % col;
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
```

### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

1. 题目描述
给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。如果数组中不存在目标值 target，返回 [-1, -1]。你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
示例 1：输入：nums = [5,7,7,8,8,10], target = 8 输出：[3,4]
示例 2：输入：nums = [5,7,7,8,8,10], target = 6 输出：[-1,-1]
示例 3：输入：nums = [], target = 0 输出：[-1,-1]
提示：0 <= nums.length <= 10^5，-10^9 <= nums[i] <= 10^9，nums 是一个非递减数组，-10^9 <= target <= 10^9
2. 算法思想+代码
- 解法一：两次二分查找法（符合O(log n)时间复杂度要求）
算法思想：基于有序数组的特性，通过两次独立的二分查找分别定位目标值的左边界和右边界。左边界查找：当中间元素大于等于目标值时收缩右边界，最终锁定第一个等于目标值的索引；右边界查找：当中间元素小于等于目标值时收缩左边界，最终锁定最后一个等于目标值的索引。若查找后左边界越界或对应元素不是目标值，直接返回[-1,-1]。
Java代码：
```java
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        // 获取左边界
        int left = findLeft(nums, target);
        // 获取右边界
        int right = findRight(nums, target);
        // 边界判断：不存在目标值的情况
        if (left > nums.length - 1 || nums[left] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{left, right};
    }

    // 查找左边界
    private int findLeft(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // 查找右边界
    private int findRight(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    // 测试示例
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {5,7,7,8,8,10};
        int[] res1 = solution.searchRange(nums1, 8);
        System.out.println("[" + res1[0] + "," + res1[1] + "]"); // 输出[3,4]

        int[] nums2 = {5,7,7,8,8,10};
        int[] res2 = solution.searchRange(nums2, 6);
        System.out.println("[" + res2[0] + "," + res2[1] + "]"); // 输出[-1,-1]

        int[] nums3 = {};
        int[] res3 = solution.searchRange(nums3, 0);
        System.out.println("[" + res3[0] + "," + res3[1] + "]"); // 输出[-1,-1]
    }
}
```
- 解法二：二分查找+线性遍历（不满足O(log n)时间复杂度，仅作参考）
算法思想：先通过二分查找找到任意一个目标值的索引，若未找到则返回[-1,-1]；若找到目标值，从该索引向左遍历找到第一个目标值，向右遍历找到最后一个目标值。该方法最坏时间复杂度为O(n)，不符合题目强制要求。
Java代码：
```java
public class Solution2 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index = -1;
        // 二分查找任意一个目标值位置
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 未找到目标值
        if (index == -1) {
            return new int[]{-1, -1};
        }
        // 向左找第一个目标值
        int start = index;
        while (start > 0 && nums[start - 1] == target) {
            start--;
        }
        // 向右找最后一个目标值
        int end = index;
        while (end < nums.length - 1 && nums[end + 1] == target) {
            end++;
        }
        return new int[]{start, end};
    }

    // 测试示例
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums1 = {5,7,7,8,8,10};
        int[] res1 = solution.searchRange(nums1, 8);
        System.out.println("[" + res1[0] + "," + res1[1] + "]"); // 输出[3,4]
    }
}
```

### [33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

1. 题目描述
整数数组 nums 按升序排列，数组中的值互不相同。在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了向左旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标从 0 开始计数）。例如 [0,1,2,4,5,6,7] 在下标 3 处向左旋转后变为 [4,5,6,7,0,1,2]。给定旋转后的数组 nums 和一个整数 target，如果 nums 中存在目标值 target 则返回它的下标，否则返回 -1。必须设计时间复杂度为 O(log n) 的算法解决该问题。
示例 1：输入：nums = [4,5,6,7,0,1,2], target = 0，输出：4
示例 2：输入：nums = [4,5,6,7,0,1,2], target = 3，输出：-1
示例 3：输入：nums = [1], target = 0，输出：-1
提示：1 <= nums.length <= 5000，-10^4 <= nums[i] <= 10^4，nums 中的每个值独一无二，题目数据保证 nums 在预先未知的某个下标上进行了旋转，-10^4 <= target <= 10^4

2. 算法思想+代码
- 解法一：单次二分查找
  算法思想：旋转排序数组虽整体无序，但任意取中点分割后，左半区间或右半区间必定有一个是有序的。基于该特性执行二分查找，每次先判断中点左右哪一侧有序，再根据target与有序区间边界值的大小关系，确定target所在的区间，不断缩小查找范围，直到找到目标值或查找结束，时间复杂度为 O(log n)，空间复杂度为 O(1)。
  Java代码：
```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        // 二分查找核心循环
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 找到目标值，直接返回下标
            if (nums[mid] == target) {
                return mid;
            }
            // 判断左半区间是否有序
            if (nums[left] <= nums[mid]) {
                // 目标值在左半有序区间内，收缩右边界
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    // 目标值在右半区间，收缩左边界
                    left = mid + 1;
                }
            } 
            // 右半区间有序
            else {
                // 目标值在右半有序区间内，收缩左边界
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    // 目标值在左半区间，收缩右边界
                    right = mid - 1;
                }
            }
        }
        // 遍历结束未找到目标值
        return -1;
    }
}
```
- 解法二：先找旋转点再二分查找
  算法思想：首先通过二分查找找到数组的旋转点（即数组中最小元素的下标），旋转点将原数组分割为两个连续的升序子数组；之后分别在两个有序子数组中执行标准二分查找，判断target是否存在，找到则返回对应下标，未找到则返回-1，时间复杂度为 O(log n)，空间复杂度为 O(1)。
  Java代码：
```java
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        int left = 0;
        int right = n - 1;
        // 第一步：二分查找旋转点（最小元素的下标）
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 中点值大于右边界，旋转点在右侧
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int rotateIndex = left;
        // 第二步：在左半有序数组中二分查找
        left = 0;
        right = rotateIndex - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 第三步：在右半有序数组中二分查找
        left = rotateIndex;
        right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 两个区间都未找到目标值
        return -1;
    }
}
```

### [153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/)

1. 题目描述
已知一个长度为n的升序排列数组，经由1到n次旋转后得到输入数组，旋转一次的结果为数组最后一个元素移动到首位，其余元素依次后移一位。给定一个元素值互不相同的旋转后升序数组，需要设计时间复杂度为O(log n)的算法，找出并返回数组中的最小元素。
示例 1：输入：nums = [3,4,5,1,2]，输出：1
示例 2：输入：nums = [4,5,6,7,0,1,2]，输出：0
示例 3：输入：nums = [11,13,15,17]，输出：11
提示：n == nums.length，1 <= n <= 5000，-5000 <= nums[i] <= 5000，nums中的所有整数互不相同，nums原来是一个升序排序的数组，并进行了1至n次旋转
2. 算法思想+代码
解法一：二分查找法
算法思想：旋转后的排序数组会被分割为两个升序子数组，最小元素就是两个子数组的分界点。基于二分查找思想缩小查找范围，比较中间元素与右边界元素的大小关系：
- 当中点元素小于右边界元素时，说明最小值在左半区间（包含中点）
- 当中点元素大于右边界元素时，说明最小值在右半区间（不包含中点）
循环执行直至左右指针重合，此时指针指向的元素即为最小值，该算法时间复杂度为O(log n)，空间复杂度为O(1)
```java
class Solution {
    public int findMin(int[] nums) {
        // 定义左右指针
        int left = 0;
        int right = nums.length - 1;
        // 二分查找循环条件
        while (left < right) {
            // 计算中间索引，避免溢出
            int mid = left + (right - left) / 2;
            // 中间值小于右边界值，最小值在左区间
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                // 中间值大于右边界值，最小值在右区间
                left = mid + 1;
            }
        }
        // 左右指针重合，返回最小值
        return nums[left];
    }
}
```
解法二：暴力遍历法
算法思想：直接遍历整个数组，逐个比较元素大小，记录遍历过程中的最小元素，该算法逻辑简单，但时间复杂度为O(n)，不满足题目O(log n)的时间要求，仅作为基础解法参考
```java
class Solution {
    public int findMin(int[] nums) {
        // 初始化最小值为数组第一个元素
        int min = nums[0];
        // 遍历数组所有元素
        for (int num : nums) {
            // 更新最小值
            if (num < min) {
                min = num;
            }
        }
        return min;
    }
}
```

### [4. 寻找两个正序数组的中位数](https://leetcode.cn/problems/median-of-two-sorted-arrays/)

1. 题目描述：给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2，请你找出并返回这两个正序数组的中位数。算法的时间复杂度应该为 O(log (m+n))。示例 1：输入：nums1 = [1,3], nums2 = [2]，输出：2.00000，解释：合并数组 = [1,2,3] ，中位数 2。示例 2：输入：nums1 = [1,2], nums2 = [3,4]，输出：2.50000，解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5。提示：nums1.length == m，nums2.length == n，0 <= m <= 1000，0 <= n <= 1000，1 <= m + n <= 2000，-106 <= nums1[i], nums2[i] <= 106。
2. 算法思想+代码
- 解法一：暴力合并法
  算法思想：将两个有序数组通过双指针遍历合并为一个新的有序数组，根据合并后数组长度的奇偶性计算中位数；数组长度为奇数时取中间元素，偶数时取中间两个元素的平均值。该方法时间复杂度为O(m+n)，空间复杂度为O(m+n)，不满足题目时间复杂度要求，仅作为基础解法参考。
  Java代码：
  ```java
  class Solution {
      public double findMedianSortedArrays(int[] nums1, int[] nums2) {
          int m = nums1.length;
          int n = nums2.length;
          // 定义合并后的数组
          int[] merge = new int[m + n];
          int index = 0;
          int i = 0, j = 0;
          // 双指针合并两个有序数组
          while (i < m && j < n) {
              if (nums1[i] < nums2[j]) {
                  merge[index++] = nums1[i++];
              } else {
                  merge[index++] = nums2[j++];
              }
          }
          // 遍历nums1剩余元素
          while (i < m) {
              merge[index++] = nums1[i++];
          }
          // 遍历nums2剩余元素
          while (j < n) {
              merge[index++] = nums2[j++];
          }
          // 计算中位数
          int total = m + n;
          if (total % 2 == 1) {
              return merge[total / 2];
          } else {
              return (merge[total / 2 - 1] + merge[total / 2]) / 2.0;
          }
      }
  }
  ```
- 解法二：二分查找法（最优解，满足题目时间复杂度要求）
  算法思想：不合并数组，通过二分查找确定两个数组的分割位置，将数组分为左右两部分，保证左半部分所有元素小于等于右半部分所有元素；总长度为奇数时，左半部分的最大值即为中位数，偶数时取左半部分最大值和右半部分最小值的平均值。该方法时间复杂度为O(log(min(m,n)))，空间复杂度为O(1)，符合题目O(log(m+n))的要求。
  Java代码：
  
  ```java
  class Solution {
      public double findMedianSortedArrays(int[] nums1, int[] nums2) {
          // 确保nums1为较短数组，减少二分查找次数
          if (nums1.length > nums2.length) {
              return findMedianSortedArrays(nums2, nums1);
          }
          int m = nums1.length;
          int n = nums2.length;
          int left = 0;
          int right = m;
          // 左半部分总元素数量
          int totalLeft = (m + n + 1) / 2;
  
          while (left <= right) {
              // nums1的分割点
              int i = left + (right - left) / 2;
              // nums2的分割点
              int j = totalLeft - i;
  
              // 处理边界：分割点在数组开头/结尾时，赋值极值避免越界
              int left1Max = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
              int right1Min = i == m ? Integer.MAX_VALUE : nums1[i];
              int left2Max = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
              int right2Min = j == n ? Integer.MAX_VALUE : nums2[j];
  
              // 满足分割条件，计算中位数
              if (left1Max <= right2Min && left2Max <= right1Min) {
                  if ((m + n) % 2 == 1) {
                      return Math.max(left1Max, left2Max);
                  } else {
                      return (Math.max(left1Max, left2Max) + Math.min(right1Min, right2Min)) / 2.0;
                  }
              } else if (left1Max > right2Min) {
                  // nums1分割点左移
                  right = i - 1;
              } else {
                  // nums1分割点右移
                  left = i + 1;
              }
          }
          return 0.0;
      }
  }
  ```

## 栈

1. 栈的定义：栈是一种遵循**后进先出（LIFO, Last In First Out）**原则的线性数据结构，数据的插入和删除操作仅能在栈的一端（称为栈顶）完成，另一端为栈底，不支持随机访问元素，是最基础的线性数据结构之一。
2. 栈的常见操作
- 入栈（push）：向栈顶添加新元素
- 出栈（pop）：删除并返回栈顶的元素
- 查看栈顶（peek）：仅返回栈顶元素，不执行删除操作
- 判空（isEmpty）：判断栈中是否没有任何元素
- 获取大小（size）：统计并返回栈中元素的总数量
- 清空栈（clear）：删除栈内的所有元素

Java 官方推荐使用 `Deque` 接口实现栈（替代老旧的 `Stack` 类），以下是完整可运行的 Demo，覆盖所有核心操作：
```java
import java.util.Deque;
import java.util.LinkedList;

public class StackDemo {
    public static void main(String[] args) {
        // 1. 创建栈：使用 LinkedList 实现 Deque 接口
        Deque<String> stack = new LinkedList<>();

        // 2. 入栈操作 push()：向栈顶添加元素
        stack.push("Java");
        stack.push("栈");
        stack.push("数据结构");
        stack.push("Demo");
        System.out.println("入栈后栈内元素：" + stack);

        // 3. 获取栈大小 size()
        System.out.println("栈的元素个数：" + stack.size());

        // 4. 判断栈是否为空 isEmpty()
        System.out.println("栈是否为空：" + stack.isEmpty());

        // 5. 查看栈顶元素 peek()：不删除元素
        String topElement = stack.peek();
        System.out.println("栈顶元素：" + topElement);

        // 6. 出栈操作 pop()：删除并返回栈顶元素
        String popElement = stack.pop();
        System.out.println("出栈元素：" + popElement);
        System.out.println("出栈后栈内元素：" + stack);

        // 再次查看栈顶
        System.out.println("出栈后新的栈顶元素：" + stack.peek());

        // 7. 清空栈 clear()
        stack.clear();
        System.out.println("清空后栈内元素：" + stack);
        System.out.println("清空后栈是否为空：" + stack.isEmpty());
    }
}
```

运行结果

```
入栈后栈内元素：[Demo, 数据结构, 栈, Java]
栈的元素个数：4
栈是否为空：false
栈顶元素：Demo
出栈元素：Demo
出栈后栈内元素：[数据结构, 栈, Java]
出栈后新的栈顶元素：数据结构
清空后栈内元素：[]
清空后栈是否为空：true
```

### [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/)

1. 题目描述：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。有效字符串需满足：左括号必须用相同类型的右括号闭合；左括号必须以正确的顺序闭合；每个右括号都有一个对应的相同类型的左括号。
2. 算法思想+代码
- 算法思想：采用栈的后进先出特性解决括号匹配问题，核心逻辑如下
  - 首先判断字符串长度是否为奇数，若为奇数直接返回false（奇数长度无法实现括号完全匹配）
  - 遍历字符串中的每一个字符，遇到左括号'('、'{'、'['时，将其对应的右括号压入栈中
  - 遇到右括号时，若栈为空（无对应的左括号）或栈顶元素与当前右括号不相等，说明括号匹配失败，直接返回false
  - 若右括号与栈顶元素匹配，则将栈顶元素弹出，继续遍历下一个字符
  - 遍历完成后，若栈为空则代表所有括号都正确闭合，返回true；若栈不为空则存在未闭合的左括号，返回false
- Java代码
```java
import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public boolean isValid(String s) {
        // 奇数长度的字符串一定不满足条件
        if (s.length() % 2 != 0) {
            return false;
        }
        // 使用Deque实现栈结构，Java中推荐使用Deque替代Stack
        Deque<Character> stack = new LinkedList<>();
        // 遍历字符串的每个字符
        for (char ch : s.toCharArray()) {
            // 左括号入栈对应的右括号
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else {
                // 右括号：栈空或栈顶不匹配，直接返回false
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }
        // 最终栈必须为空，才是完全匹配
        return stack.isEmpty();
    }
}
```

### [155. 最小栈](https://leetcode.cn/problems/min-stack/)

1. 题目描述
155. 最小栈
设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
实现 MinStack 类:
MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。
示例 1:
输入：["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]
输出：[null,null,null,null,-3,null,0,-2]
解释：MinStack minStack = new MinStack(); minStack.push(-2); minStack.push(0); minStack.push(-3); minStack.getMin();   --> 返回 -3; minStack.pop(); minStack.top();      --> 返回 0; minStack.getMin();   --> 返回 -2
提示：-2^31 <= val <= 2^31 - 1，pop、top 和 getMin 操作总是在非空栈上调用，push, pop, top, and getMin最多被调用 3 * 10^4 次

2. 解法一：辅助栈法
- 算法思想：采用两个栈实现，一个数据栈用于存储所有入栈元素，保证push、pop、top的基础操作；一个最小栈用于同步存储当前栈内的最小元素，确保getMin操作能在常数时间完成。入栈时，数据栈直接压入元素，最小栈仅当新元素小于等于其栈顶元素时才压入；出栈时，数据栈弹出栈顶元素，若该元素与最小栈栈顶元素相等，最小栈同步弹出；获取栈顶元素直接取数据栈栈顶，获取最小元素直接取最小栈栈顶，所有操作的时间复杂度均为O(1)。
- Java代码
```java
import java.util.Deque;
import java.util.LinkedList;

class MinStack {
    // 数据栈：存储所有入栈元素
    Deque<Integer> dataStack;
    // 最小栈：存储当前栈内的最小元素
    Deque<Integer> minStack;

    // 初始化堆栈
    public MinStack() {
        dataStack = new LinkedList<>();
        minStack = new LinkedList<>();
        // 初始化最小栈，避免空栈判断
        minStack.push(Integer.MAX_VALUE);
    }

    // 元素入栈
    public void push(int val) {
        dataStack.push(val);
        // 最小栈压入当前最小值
        minStack.push(Math.min(minStack.peek(), val));
    }

    // 栈顶元素出栈
    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    // 获取栈顶元素
    public int top() {
        return dataStack.peek();
    }

    // 获取栈中最小元素
    public int getMin() {
        return minStack.peek();
    }
}
```

3. 解法二：单栈法（存储差值）
- 算法思想：仅使用一个栈和一个全局最小值变量实现，栈中存储当前入栈元素与当前最小值的差值，不存储实际元素。入栈时，计算新元素与当前最小值的差值并入栈，若新元素更小则更新最小值；出栈时，根据栈顶差值判断是否需要恢复上一个最小值；获取栈顶元素时，通过差值和当前最小值计算得到；获取最小元素直接返回全局最小值，无需额外辅助栈。
- Java代码
```java
import java.util.Deque;
import java.util.LinkedList;

class MinStack {
    // 单栈：存储元素与当前最小值的差值
    Deque<Long> stack;
    // 全局最小值变量
    long min;

    // 初始化堆栈
    public MinStack() {
        stack = new LinkedList<>();
    }

    // 元素入栈
    public void push(int val) {
        // 栈空时，直接存入差值0，最小值为当前元素
        if (stack.isEmpty()) {
            min = val;
            stack.push(0L);
        } else {
            // 计算差值，使用long避免整型溢出
            long diff = (long) val - min;
            stack.push(diff);
            // 差值小于0，更新最小值
            if (diff < 0) {
                min = val;
            }
        }
    }

    // 栈顶元素出栈
    public void pop() {
        long diff = stack.pop();
        // 出栈元素为最小值，恢复上一个最小值
        if (diff < 0) {
            min = min - diff;
        }
    }

    // 获取栈顶元素
    public int top() {
        long diff = stack.peek();
        // 根据差值计算栈顶实际元素
        return diff > 0 ? (int) (min + diff) : (int) min;
    }

    // 获取栈中最小元素
    public int getMin() {
        return (int) min;
    }
}
```

### [394. 字符串解码](https://leetcode.cn/problems/decode-string/)

1. 题目描述
给定一个经过编码的字符串，返回它解码后的字符串。编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。测试用例保证输出的长度不会超过 105。
示例 1：输入：s = "3[a]2[bc]" 输出："aaabcbc"
示例 2：输入：s = "3[a2[c]]" 输出："accaccacc"
示例 3：输入：s = "2[abc]3[cd]ef" 输出："abcabccdcdcdef"
示例 4：输入：s = "abc3[cd]xyz" 输出："abccdcdcdxyz"
提示：1 <= s.length <= 30，s 由小写英文字母、数字和方括号 '[]' 组成，s 保证是一个有效的输入。s 中所有整数的取值范围为 [1, 300]

2. 解法一：栈解法
- 算法思想：由于字符串存在嵌套的方括号结构，使用栈来存储每一层的重复次数和对应的上层字符串。遍历编码字符串时，数字用于累计重复次数k；遇到左括号[时，将当前的k和已拼接的字符串压入栈，随后重置当前字符串和k；遇到右括号]时，弹出栈顶的重复次数和上层字符串，将当前字符串重复对应次数后拼接到上层字符串后，作为新的当前字符串；遇到普通字母时，直接拼接到当前字符串末尾。遍历完成后，当前字符串即为解码结果。
- Java代码
```java
import java.util.Stack;

public class Solution {
    public String decodeString(String s) {
        // 存储每一层的重复次数
        Stack<Integer> countStack = new Stack<>();
        // 存储每一层的上层字符串
        Stack<String> strStack = new Stack<>();
        // 当前正在拼接的字符串
        StringBuilder currStr = new StringBuilder();
        // 累计的重复次数
        int k = 0;

        // 遍历字符串的每一个字符
        for (char c : s.toCharArray()) {
            // 处理数字字符，支持多位数
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } 
            // 遇到左括号，将当前次数和字符串入栈，重置状态
            else if (c == '[') {
                countStack.push(k);
                strStack.push(currStr.toString());
                // 重置当前字符串和重复次数
                currStr = new StringBuilder();
                k = 0;
            } 
            // 遇到右括号，出栈并拼接字符串
            else if (c == ']') {
                int repeatCount = countStack.pop();
                String prevStr = strStack.pop();
                // 将当前字符串重复指定次数后拼接
                for (int i = 0; i < repeatCount; i++) {
                    prevStr += currStr;
                }
                currStr = new StringBuilder(prevStr);
            } 
            // 普通字母直接拼接
            else {
                currStr.append(c);
            }
        }
        return currStr.toString();
    }
}
```

3. 解法二：递归解法
- 算法思想：利用递归处理嵌套的括号结构，定义全局索引记录遍历位置。遍历字符串时，数字累计重复次数k；遇到左括号[时，递归调用函数处理括号内的子串，得到子串后重复k次拼接到结果中；遇到右括号]时，返回当前拼接的字符串，结束当前层递归；遇到普通字母直接拼接到结果中。递归会自动处理嵌套的层级关系，无需手动维护栈结构。
- Java代码
```java
public class Solution {
    // 全局索引，标记字符串的遍历位置
    int index = 0;

    public String decodeString(String s) {
        return dfs(s);
    }

    // 递归处理字符串解码
    private String dfs(String s) {
        StringBuilder result = new StringBuilder();
        int k = 0;

        while (index < s.length()) {
            char c = s.charAt(index);
            // 处理数字，累计重复次数
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
                index++;
            } 
            // 遇到左括号，递归处理括号内的子串
            else if (c == '[') {
                index++;
                // 递归获取括号内的解码字符串
                String subStr = dfs(s);
                // 将子串重复k次拼接
                for (int i = 0; i < k; i++) {
                    result.append(subStr);
                }
                k = 0;
            } 
            // 遇到右括号，返回当前层的结果
            else if (c == ']') {
                index++;
                return result.toString();
            } 
            // 普通字母直接拼接
            else {
                result.append(c);
                index++;
            }
        }
        return result.toString();
    }
}
```

### [739. 每日温度](https://leetcode.cn/problems/daily-temperatures/)

1. 题目描述
给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
示例 1:
输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]
示例 2:
输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]
示例 3:
输入: temperatures = [30,60,90]
输出: [1,1,0]
提示：
1 <= temperatures.length <= 10⁵
30 <= temperatures[i] <= 100

2. 解法一：暴力解法
- 算法思想：通过两层for循环遍历数组，外层循环遍历每一天的温度，内层循环从当前天数的下一天开始查找，找到第一个温度高于当前温度的天数，计算两个天数的索引差值即为对应结果；若内层循环遍历结束都未找到更高温度，则结果为0。该方法逻辑简单直观，但时间复杂度为O(n²)，在数据量达到10⁵时会超出时间限制，仅适用于小规模数据测试。
- 代码
```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        // 结果数组
        int[] answer = new int[n];
        // 遍历每一天
        for (int i = 0; i < n; i++) {
            // 向后查找第一个更高温度
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
            // 未找到时，默认赋值0，数组初始化默认就是0，可省略
        }
        return answer;
    }
}
```

3. 解法二：单调栈解法（最优解）
- 算法思想：采用单调递减栈实现，栈中存储数组的下标索引，保证栈内索引对应的温度值始终保持递减顺序。遍历温度数组时，若当前温度大于栈顶索引对应的温度，说明找到了栈顶索引的下一个更高温度，弹出栈顶元素并计算索引差值；重复此操作直到栈为空或栈顶温度不小于当前温度，最后将当前索引压入栈中。遍历完成后，栈中剩余元素没有更高温度，结果默认为0。该方法时间复杂度为O(n)，每个元素仅入栈和出栈一次，空间复杂度为O(n)，可以高效处理题目最大数据量。
- 代码
```java
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        // 初始化单调栈，存储数组索引
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 当前温度大于栈顶温度，更新结果
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            // 当前索引入栈
            stack.push(i);
        }
        return answer;
    }
}
```

### [84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram/)

1. 题目描述
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积。
示例 1:
输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10
示例 2:
输入： heights = [2,4]
输出： 4
提示：
1 <= heights.length <=105
0 <= heights[i] <= 104

2. 解法一：暴力枚举法
- 算法思想：遍历数组中的每一根柱子，将其作为矩形的固定高度，分别向左和向右遍历，找到第一个高度小于当前柱子的位置，以此确定矩形的宽度，通过高度×宽度计算当前矩形面积，遍历过程中记录最大面积。该方法思路直观，但时间复杂度为O(n²)，在数据量较大时会超时，无法通过题目全部测试用例。
- 代码
```java
public class Solution {
    public int largestRectangleArea(int[] heights) {
        // 记录最大面积
        int maxArea = 0;
        int n = heights.length;
        // 枚举每一根柱子作为矩形的高度
        for (int i = 0; i < n; i++) {
            int currentHeight = heights[i];
            int left = i;
            // 向左遍历，找到第一个小于当前高度的柱子
            while (left > 0 && heights[left - 1] >= currentHeight) {
                left--;
            }
            int right = i;
            // 向右遍历，找到第一个小于当前高度的柱子
            while (right < n - 1 && heights[right + 1] >= currentHeight) {
                right++;
            }
            // 计算宽度和面积
            int width = right - left + 1;
            maxArea = Math.max(maxArea, currentHeight * width);
        }
        return maxArea;
    }
}
```

3. 解法二：单调栈最优解法
- 算法思想：采用单调递增栈存储柱子的索引，保证栈中索引对应的柱子高度严格递增。遍历数组时，若当前柱子高度小于栈顶索引对应的高度，说明找到了栈顶柱子的右边界，弹出栈顶元素作为矩形的高度，此时新的栈顶索引为左边界，计算矩形面积；遍历完成后，栈中剩余元素仍需按此规则计算面积，为简化边界处理，在数组末尾添加高度为0的哨兵元素。该方法时间复杂度为O(n)，每个元素仅入栈和出栈一次，空间复杂度为O(n)，是本题的最优解法。
- 代码
```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        // 扩容数组，末尾添加高度为0的哨兵，简化边界处理
        int[] newHeights = new int[heights.length + 1];
        System.arraycopy(heights, 0, newHeights, 0, heights.length);
        newHeights[heights.length] = 0;
        
        int maxArea = 0;
        // 单调递增栈，存储柱子的索引
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < newHeights.length; i++) {
            // 当前高度小于栈顶高度，弹出栈顶计算面积
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                // 弹出栈顶，作为矩形的高度索引
                int heightIndex = stack.pop();
                int height = newHeights[heightIndex];
                // 计算宽度，栈空则左边界为-1
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                // 更新最大面积
                maxArea = Math.max(maxArea, height * width);
            }
            // 当前索引入栈
            stack.push(i);
        }
        return maxArea;
    }
}
```

## 堆

1. 堆的定义：堆是基于完全二叉树实现的树形数据结构，分为大顶堆和小顶堆两类；完全二叉树要求除最后一层外其余层节点全满，且最后一层节点全部靠左排列；堆默认使用数组存储，节点索引遵循固定规则：索引为i的节点，左子节点索引=2i+1，右子节点索引=2i+2，父节点索引=(i-1)/2（整数除法）；大顶堆中每个父节点的值≥子节点值，堆顶为全局最大值；小顶堆中每个父节点的值≤子节点值，堆顶为全局最小值。
2. 堆的常见操作
   - 堆化：维持堆结构的核心基础操作，分为向上堆化（子节点向根节点调整）和向下堆化（父节点向子节点调整）
   - 建堆：将无序数组转换为符合堆特性的结构
   - 插入元素：向堆尾部添加新元素，通过向上堆化修复堆结构
   - 删除堆顶：移除根节点，将堆尾元素移至堆顶，通过向下堆化修复堆结构
   - 获取堆顶：直接读取根节点值，时间复杂度O(1)
   - 堆排序：基于堆结构实现数组排序，时间复杂度O(nlogn)

以下代码完整实现**大顶堆**的所有常用操作，包含堆化、建堆、插入、删除堆顶、获取堆顶、打印堆、堆排序功能，可直接运行：
```java
public class HeapDemo {
    private int[] heap; // 存储堆的数组
    private int size;   // 堆当前元素个数

    // 构造方法：初始化堆容量
    public HeapDemo(int capacity) {
        heap = new int[capacity];
        size = 0;
    }

    // ==================== 核心：堆化操作 ====================
    // 向上堆化（插入元素使用）：子节点比父节点大，向上交换调整
    private void siftUp(int index) {
        // 父节点索引
        int parentIndex = (index - 1) / 2;
        // 子节点大于父节点，交换并继续向上调整
        while (index > 0 && heap[index] > heap[parentIndex]) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // 向下堆化（删除堆顶/建堆使用）：父节点比子节点小，向下交换调整
    private void siftDown(int index) {
        // 最大元素索引，初始为当前父节点
        int largest = index;
        // 左、右子节点索引
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        // 找父节点、左子、右子中的最大值
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // 最大值不是父节点，交换后继续向下调整
        if (largest != index) {
            swap(index, largest);
            siftDown(largest);
        }
    }

    // ==================== 常用操作 ====================
    // 1. 建堆：将无序数组转为大顶堆
    public void buildHeap(int[] arr) {
        if (arr == null || arr.length == 0) return;
        // 复制数组到堆
        System.arraycopy(arr, 0, heap, 0, arr.length);
        size = arr.length;
        // 从最后一个非叶子节点开始，向下堆化
        int lastNonLeaf = (size - 2) / 2;
        for (int i = lastNonLeaf; i >= 0; i--) {
            siftDown(i);
        }
    }

    // 2. 插入元素
    public void insert(int val) {
        if (size >= heap.length) {
            System.out.println("堆已满，无法插入");
            return;
        }
        // 元素添加到堆尾
        heap[size] = val;
        // 向上堆化调整
        siftUp(size);
        size++;
    }

    // 3. 删除堆顶元素
    public Integer removeTop() {
        if (size == 0) {
            System.out.println("堆为空，无法删除");
            return null;
        }
        // 堆顶元素
        int top = heap[0];
        // 堆尾元素移到堆顶
        heap[0] = heap[size - 1];
        size--;
        // 向下堆化调整
        siftDown(0);
        return top;
    }

    // 4. 获取堆顶元素
    public Integer getTop() {
        if (size == 0) return null;
        return heap[0];
    }

    // 5. 堆排序
    public void heapSort() {
        int tempSize = size;
        // 循环删除堆顶，放到数组末尾，完成排序
        while (size > 0) {
            int top = removeTop();
            heap[size] = top;
        }
        // 恢复堆大小，打印排序结果
        size = tempSize;
        System.out.print("堆排序结果：");
        printHeap();
    }

    // ==================== 工具方法 ====================
    // 交换数组两个元素
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 打印堆
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // 测试主方法
    public static void main(String[] args) {
        // 初始化堆容量为10
        HeapDemo maxHeap = new HeapDemo(10);
        int[] arr = {3, 1, 5, 2, 4};

        // 1. 建堆
        maxHeap.buildHeap(arr);
        System.out.print("建堆后大顶堆：");
        maxHeap.printHeap();

        // 2. 获取堆顶
        System.out.println("堆顶元素：" + maxHeap.getTop());

        // 3. 插入元素
        maxHeap.insert(6);
        System.out.print("插入元素6后：");
        maxHeap.printHeap();

        // 4. 删除堆顶
        Integer removed = maxHeap.removeTop();
        System.out.println("删除的堆顶元素：" + removed);
        System.out.print("删除堆顶后堆：");
        maxHeap.printHeap();

        // 5. 堆排序
        maxHeap.heapSort();
    }
}
```

 代码运行结果

```
建堆后大顶堆：5 4 3 2 1 
堆顶元素：5
插入元素6后：6 4 5 2 1 3 
删除的堆顶元素：6
删除堆顶后堆：5 4 3 2 1 
堆排序结果：1 2 3 4 5 
```

1. 堆的核心是**完全二叉树+堆化操作**，大顶堆/小顶堆仅需调整堆化的比较规则即可实现互换；
2. 堆的高频操作中，**获取堆顶O(1)**，**插入/删除堆顶O(logn)**，**建堆O(n)**，是优先队列、堆排序的核心数据结构；
3. 上述Demo通过数组实现大顶堆，封装了所有常用操作，逻辑简洁且贴合算法底层原理。

### [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

1. 题目描述：给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
示例 1: 输入: [3,2,1,5,6,4], k = 2 输出: 5
示例 2: 输入: [3,2,3,1,2,4,5,5,6], k = 4 输出: 4
提示：1 <= k <= nums.length <= 10^5，-10^4 <= nums[i] <= 10^4

2. 算法思想+代码
- 解法一：快速选择算法
  算法思想：基于快速排序的分区思想，随机选择基准值将数组划分为大于基准、等于基准、小于基准三部分，通过判断第k大元素所在的区间，仅递归处理目标区间，无需对整个数组排序，平均时间复杂度为O(n)，随机选取基准可规避最坏时间复杂度O(n²)，是满足题目时间要求的最优解法之一。
  Java代码：
```java
import java.util.Random;

public class Solution {
    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        // 第k大元素 = 数组中索引为 len - k 的元素
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    // 快速选择核心方法
    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left >= right) {
            return nums[left];
        }
        // 随机选取基准值索引
        int pivotIndex = left + random.nextInt(right - left + 1);
        // 分区操作，返回基准值最终位置
        int curIndex = partition(nums, left, right, pivotIndex);
        // 递归判断目标区间
        if (curIndex == targetIndex) {
            return nums[curIndex];
        } else if (curIndex < targetIndex) {
            return quickSelect(nums, curIndex + 1, right, targetIndex);
        } else {
            return quickSelect(nums, left, curIndex - 1, targetIndex);
        }
    }

    // 分区函数：将小于基准的放左边，大于基准的放右边
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        // 先把基准值放到最右侧
        swap(nums, pivotIndex, right);
        int slow = left;
        for (int fast = left; fast < right; fast++) {
            if (nums[fast] < pivot) {
                swap(nums, slow, fast);
                slow++;
            }
        }
        // 把基准值放回最终位置
        swap(nums, slow, right);
        return slow;
    }

    // 交换数组元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

- 解法二：计数排序算法
  算法思想：利用题目限定的数值范围（-10^4 ~ 10^4），创建固定长度的计数数组统计每个数字的出现次数，从最大值向最小值遍历计数数组，累加计数次数直至等于k，当前数字即为第k个最大元素，时间复杂度O(n)，空间复杂度O(1)（计数数组长度固定）。
  Java代码：
```java
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 数值范围：-10^4 ~ 10^4，总长度20001，偏移量10000处理负数
        int offset = 10000;
        int[] count = new int[20001];
        
        // 统计每个数字出现次数
        for (int num : nums) {
            count[num + offset]++;
        }
        
        // 从大到小遍历，找到第k大元素
        for (int i = 20000; i >= 0; i--) {
            k -= count[i];
            if (k <= 0) {
                return i - offset;
            }
        }
        return -1;
    }
}
```

### [347. 前 K 个高频元素](https://leetcode.cn/problems/top-k-frequent-elements/)

1. 题目描述
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按任意顺序返回答案。
示例 1：输入：nums = [1,1,1,2,2,3], k = 2 输出：[1,2]
示例 2：输入：nums = [1], k = 1 输出：[1]
示例 3：输入：nums = [1,2,1,2,1,2,3,1,3,2], k = 2 输出：[1,2]
提示：1 <= nums.length <= 105；-104 <= nums[i] <= 104；k 的取值范围是 [1, 数组中不相同的元素的个数]；题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
进阶：你所设计算法的时间复杂度必须优于 O(n log n) ，其中 n 是数组大小。

2. 算法思想+代码
- 解法一：哈希表 + 排序
  算法思想：通过哈希表统计数组中每个元素的出现频率，将哈希表的键值对转换为列表后，按照元素频率进行降序排序，最后截取排序后列表的前k个元素作为结果。该方法实现简单，时间复杂度为O(n log n)。
  Java代码：
  
  ```java
  import java.util.*;
  
  class Solution {
      public int[] topKFrequent(int[] nums, int k) {
          // 哈希表统计每个数字出现的频率
          Map<Integer, Integer> frequencyMap = new HashMap<>();
          for (int num : nums) {
              frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
          }
  
          // 将哈希表的键值对转为列表，按频率降序排序
          List<Map.Entry<Integer, Integer>> frequencyList = new ArrayList<>(frequencyMap.entrySet());
          frequencyList.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());
  
          // 提取前k个高频元素
          int[] result = new int[k];
          for (int i = 0; i < k; i++) {
              result[i] = frequencyList.get(i).getKey();
          }
          return result;
      }
  }
  ```
- 解法二：哈希表 + 小顶堆（优先队列，进阶解法）
  算法思想：先通过哈希表统计元素出现频率，再使用小顶堆（优先队列）维护频率最高的k个元素。将堆的大小固定为k，遍历哈希表时，若堆未填满则直接将元素入堆；若当前元素频率大于堆顶元素的频率，弹出堆顶元素后将当前元素入堆。最终堆中存储的就是频率前k高的元素，时间复杂度为O(n log k)，优于O(n log n)，满足进阶要求。
  Java代码：
  ```java
  import java.util.*;
  
  class Solution {
      public int[] topKFrequent(int[] nums, int k) {
          // 哈希表统计每个数字出现的频率
          Map<Integer, Integer> frequencyMap = new HashMap<>();
          for (int num : nums) {
              frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
          }
  
          // 小顶堆：按照元素频率升序排列，堆顶为当前频率最小的元素
          PriorityQueue<Integer> minHeap = new PriorityQueue<>((num1, num2) -> frequencyMap.get(num1) - frequencyMap.get(num2));
  
          // 遍历哈希表，维护堆的大小为k
          for (Integer num : frequencyMap.keySet()) {
              minHeap.offer(num);
              // 堆大小超过k时，弹出频率最小的元素
              if (minHeap.size() > k) {
                  minHeap.poll();
              }
          }
  
          // 提取堆中元素作为结果
          int[] result = new int[k];
          for (int i = 0; i < k; i++) {
              result[i] = minHeap.poll();
          }
          return result;
      }
  }
  ```
  

### [295. 数据流的中位数](https://leetcode.cn/problems/find-median-from-data-stream/)

1. 题目描述
中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。例如 arr = [2,3,4] 的中位数是 3 。例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。实现 MedianFinder 类: MedianFinder() 初始化 MedianFinder 对象。void addNum(int num) 将数据流中的整数 num 添加到数据结构中。double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10^-5 以内的答案将被接受。
示例 1：输入["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"][[], [1], [2], [], [3], []]输出[null, null, null, 1.5, null, 2.0]解释MedianFinder medianFinder = new MedianFinder();medianFinder.addNum(1);    // arr = [1]medianFinder.addNum(2);    // arr = [1, 2]medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)medianFinder.addNum(3);    // arr[1, 2, 3]medianFinder.findMedian(); // return 2.0提示:-10^5 <= num <= 10^5在调用 findMedian 之前，数据结构中至少有一个元素最多 5 * 10^4 次调用 addNum 和 findMedian
2. 算法思想+代码
- 解法一：优先队列（大顶堆+小顶堆，最优解法）
  算法思想：采用两个堆分割数据流，大顶堆存储较小的一半元素，堆顶为较小半部分的最大值；小顶堆存储较大的一半元素，堆顶为较大半部分的最小值。维护堆的数量平衡：大顶堆元素数量要么与小顶堆相等，要么比小顶堆多1。添加元素时根据数值大小入堆，并调整堆的数量平衡；查找中位数时，总元素数为奇数则取大顶堆堆顶，为偶数则取两个堆堆顶的平均值。该解法添加元素时间复杂度O(logn)，查找中位数O(1)，适配大数据流场景。
  Java代码：
```java
import java.util.PriorityQueue;

class MedianFinder {
    // 大顶堆：存储数据流中较小的一半元素
    private PriorityQueue<Integer> maxHeap;
    // 小顶堆：存储数据流中较大的一半元素
    private PriorityQueue<Integer> minHeap;

    // 初始化两个堆
    public MedianFinder() {
        // 重写比较器实现大顶堆
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // 默认小顶堆
        minHeap = new PriorityQueue<>();
    }

    // 添加元素并维护堆的平衡
    public void addNum(int num) {
        // 元素小于等于大顶堆堆顶，放入大顶堆
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            // 否则放入小顶堆
            minHeap.offer(num);
        }

        // 调整平衡：大顶堆最多比小顶堆多1个元素
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        // 小顶堆元素不能多于大顶堆
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // 计算并返回中位数
    public double findMedian() {
        // 元素总数为奇数，中位数是大顶堆堆顶
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        // 元素总数为偶数，中位数是两个堆顶的平均值
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}
```
- 解法二：暴力排序法
  算法思想：使用动态数组存储所有流入元素，addNum方法直接追加元素；findMedian方法先对数组排序，再根据数组长度奇偶性计算中位数。实现简单，但查找中位数需要排序，时间复杂度O(nlogn)，仅适合小数据量场景。
  Java代码：
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MedianFinder {
    // 动态数组存储所有元素
    private List<Integer> nums;

    // 初始化数组
    public MedianFinder() {
        nums = new ArrayList<>();
    }

    // 直接添加元素到数组末尾
    public void addNum(int num) {
        nums.add(num);
    }

    // 排序后计算中位数
    public double findMedian() {
        // 对数组排序
        Collections.sort(nums);
        int size = nums.size();
        // 数组长度为奇数
        if (size % 2 == 1) {
            return nums.get(size / 2);
        }
        // 数组长度为偶数
        return (nums.get(size / 2 - 1) + nums.get(size / 2)) / 2.0;
    }
}
```

## 贪心算法

贪心算法是一种在每一步决策中都选择当前状态下局部最优的解决方案，从而期望通过一系列局部最优选择最终得到全局最优解的算法思想；该算法无需回溯和全局规划，执行效率高，但仅适用于满足贪心选择性质（全局最优解可由一系列局部最优选择推导得到）和最优子结构性质（问题的最优解包含其子问题的最优解）的场景，不满足这两个性质的问题无法通过贪心算法得到全局最优解。

贪心算法的常见操作：
1. 问题有效性校验：分析问题是否满足贪心选择性质和最优子结构性质，这是使用贪心算法的核心前提
2. 制定贪心策略：根据问题需求定义每一步的局部最优选择规则，这是贪心算法的核心步骤
3. 迭代执行选择：按照制定的贪心策略，遍历问题元素并逐步选择局部最优解，同时记录选择结果
4. 终止条件判定：当遍历完所有元素或满足问题的边界约束时，停止选择操作
5. 结果整合输出：将所有局部最优选择整合，得到最终的问题解

以下代码实现贪心算法最经典的两个场景：活动选择问题（选最多不重叠活动）、零钱兑换问题（用最少硬币凑金额），严格遵循上述常见操作：
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GreedyAlgorithmDemo {
    public static void main(String[] args) {
        // 1. 演示活动选择问题（贪心经典场景）
        System.out.println("===== 活动选择问题 =====");
        // 定义活动：数组格式[开始时间, 结束时间]
        int[][] activities = {{1, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 7}};
        List<int[]> selectActivities = activitySelect(activities);
        System.out.println("选中的不重叠活动（开始-结束）：");
        for (int[] act : selectActivities) {
            System.out.print(act[0] + "-" + act[1] + " ");
        }

        // 2. 演示零钱兑换问题（贪心常用场景）
        System.out.println("\n===== 零钱兑换问题 =====");
        // 硬币面额（降序排列，适配贪心策略）
        int[] coins = {5, 2, 1};
        // 目标金额
        int target = 11;
        int minCoinNum = coinChange(coins, target);
        System.out.println("凑成" + target + "元最少需要硬币数量：" + minCoinNum);
    }

    /**
     * 活动选择问题 - 贪心实现
     * 贪心策略：每次选择结束时间最早的活动，预留更多时间给后续活动
     */
    public static List<int[]> activitySelect(int[][] activities) {
        List<int[]> result = new ArrayList<>();
        if (activities == null || activities.length == 0) {
            return result;
        }

        // 步骤1：制定贪心策略 -> 按活动结束时间升序排序
        Arrays.sort(activities, Comparator.comparingInt(a -> a[1]));

        // 步骤2：迭代选择第一个活动（结束最早）
        result.add(activities[0]);
        int lastEndTime = activities[0][1];

        // 步骤3：遍历剩余活动，选择不重叠的局部最优解
        for (int i = 1; i < activities.length; i++) {
            int currentStart = activities[i][0];
            // 校验：当前活动开始时间 >= 上一个选中活动的结束时间
            if (currentStart >= lastEndTime) {
                result.add(activities[i]);
                lastEndTime = activities[i][1];
            }
        }

        // 步骤4：返回最终解
        return result;
    }

    /**
     * 零钱兑换问题 - 贪心实现
     * 贪心策略：每次选择当前最大面额硬币，减少总硬币数
     * 适用场景：面额满足倍数关系（如人民币）
     */
    public static int coinChange(int[] coins, int target) {
        if (coins == null || coins.length == 0 || target < 0) {
            return -1;
        }

        int count = 0;
        int remaining = target;

        // 步骤1：制定贪心策略 -> 按面额降序遍历
        for (int coin : coins) {
            // 步骤2：迭代选择当前最大面额硬币（局部最优）
            while (remaining >= coin) {
                remaining -= coin;
                count++;
            }
            // 步骤3：终止条件：金额凑完则退出
            if (remaining == 0) {
                break;
            }
        }

        // 无法凑出金额返回-1
        return remaining == 0 ? count : -1;
    }
}
```

代码运行说明：
- 活动选择问题：通过「按结束时间排序+选不重叠活动」的贪心策略，得到最多可参与的活动数量
- 零钱兑换问题：通过「优先用大面额硬币」的贪心策略，得到最少硬币数
- 代码完整覆盖贪心算法的所有常见操作：校验场景、制定策略、迭代选择、终止判定、结果输出

运行结果：
```
===== 活动选择问题 =====
选中的不重叠活动（开始-结束）：
1-3 3-5 5-7 
===== 零钱兑换问题 =====
凑成11元最少需要硬币数量：3
```

1. 贪心算法核心是**局部最优推导全局最优**，必须满足贪心选择性质和最优子结构
2. 核心流程固定为：校验场景→制定策略→迭代选择→终止判定→输出结果
3. Java实现中通过排序、遍历即可完成贪心逻辑，代码简洁、执行效率高

### [121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

1. 题目描述
给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。返回你可以从这笔交易中获取的最大利润，如果你不能获取任何利润，返回 0。

2. 算法思想+代码
- 解法一：暴力枚举法
  算法思想：通过双重循环枚举所有可能的买入和卖出组合，外层循环遍历买入日期，内层循环遍历卖出日期（卖出日期必须晚于买入日期），计算每一组的利润，不断更新最大利润值；若所有组合利润均为负，最终返回0。该方法逻辑简单直观，但时间复杂度较高，对于大数据量会超时。
  Java代码：
  ```java
  class Solution {
      public int maxProfit(int[] prices) {
          int maxProfit = 0;
          int n = prices.length;
          // 遍历所有买入日期
          for (int i = 0; i < n; i++) {
              // 遍历所有卖出日期（必须在买入之后）
              for (int j = i + 1; j < n; j++) {
                  int profit = prices[j] - prices[i];
                  // 更新最大利润
                  if (profit > maxProfit) {
                      maxProfit = profit;
                  }
              }
          }
          return maxProfit;
      }
  }
  ```

- 解法二：一次遍历法（贪心算法）
  算法思想：遍历数组的过程中，实时记录当前遇到的最低股票价格，同时计算以当前价格卖出所能获得的利润，用当前利润和历史最大利润对比，更新最大利润。仅需一次遍历数组，时间复杂度为O(n)，空间复杂度为O(1)，能够高效处理题目给定的大数据量。
  Java代码：
  ```java
  class Solution {
      public int maxProfit(int[] prices) {
          // 边界处理：天数小于2无法交易，利润为0
          if (prices == null || prices.length < 2) {
              return 0;
          }
          // 初始化最低价格为第一天的价格
          int minPrice = prices[0];
          // 初始化最大利润为0
          int maxProfit = 0;
          // 从第二天开始遍历
          for (int i = 1; i < prices.length; i++) {
              // 更新遍历过程中的最低价格
              minPrice = Math.min(minPrice, prices[i]);
              // 计算当前卖出利润，更新最大利润
              maxProfit = Math.max(maxProfit, prices[i] - minPrice);
          }
          return maxProfit;
      }
  }
  ```

### [55. 跳跃游戏](https://leetcode.cn/problems/jump-game/)

1. 题目描述
给你一个非负整数数组 nums ，你最初位于数组的第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
示例 1：输入：nums = [2,3,1,1,4]，输出：true，解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
示例 2：输入：nums = [3,2,1,0,4]，输出：false，解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
提示：1 <= nums.length <= 104，0 <= nums[i] <= 105

2. 算法思想+代码
- 解法一：贪心算法
  - 算法思想：维护当前能够到达的最远下标位置，遍历数组中的每一个位置，若当前位置在可到达范围内，则更新最远可到达位置；若最远可到达位置大于等于数组最后一个下标，直接返回true；若遍历过程中当前位置超出了最远可到达位置，说明无法继续前进，返回false。该算法时间复杂度为O(n)，空间复杂度为O(1)，是最优解法。
  - Java代码：
```java
public class Solution {
    public boolean canJump(int[] nums) {
        // 记录当前能到达的最远位置
        int maxReach = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 当前位置无法到达，直接返回false
            if (i > maxReach) {
                return false;
            }
            // 更新最远能到达的位置
            maxReach = Math.max(maxReach, i + nums[i]);
            // 已经能到达最后一个下标，提前返回true
            if (maxReach >= n - 1) {
                return true;
            }
        }
        // 遍历结束，判断是否能到达最后一位
        return maxReach >= n - 1;
    }

    // 测试示例
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2,3,1,1,4};
        System.out.println(solution.canJump(nums1));
        int[] nums2 = {3,2,1,0,4};
        System.out.println(solution.canJump(nums2));
    }
}
```
- 解法二：动态规划
  - 算法思想：定义布尔类型dp数组，dp[i]表示是否可以到达数组的下标i位置；初始化dp[0] = true（起始位置可达）；遍历数组，对于每个可达的位置i，将其能跳跃到的所有位置j标记为可达；最后判断dp数组最后一个元素的值即可。该算法时间复杂度为O(n²)，空间复杂度为O(n)。
  - Java代码：
```java
public class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // dp[i]：是否能到达下标i
        boolean[] dp = new boolean[n];
        // 初始位置可达
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            // 当前位置不可达，跳过
            if (!dp[i]) {
                continue;
            }
            // 标记从i位置能跳跃到的所有位置为可达
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                dp[i + j] = true;
                // 提前终止：已到达最后一个位置
                if (i + j == n - 1) {
                    return true;
                }
            }
        }
        return dp[n - 1];
    }

    // 测试示例
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2,3,1,1,4};
        System.out.println(solution.canJump(nums1));
        int[] nums2 = {3,2,1,0,4};
        System.out.println(solution.canJump(nums2));
    }
}
```

### [45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii/)

1. 题目描述
给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，可以跳转到任意 (i + j) 处：0 <= j <= nums[i] 且 i + j < n。返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
示例 1: 输入: nums = [2,3,1,1,4] 输出: 2 解释: 跳到最后一个位置的最小跳跃数是 2。从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
示例 2: 输入: nums = [2,3,0,1,4] 输出: 2
提示: 1 <= nums.length <= 104，0 <= nums[i] <= 1000，题目保证可以到达 n - 1。

2. 算法思想+代码
- 解法一：贪心算法
  算法思想：贪心策略的核心是每一步选择能跳得最远的位置，以此保证跳跃次数最少。遍历数组时维护三个变量：跳跃次数count、当前跳跃的边界end、当前能到达的最远位置maxReach。遍历过程中持续更新maxReach，当遍历到边界end时，执行一次跳跃，count加1并将end更新为maxReach。该解法时间复杂度O(n)，空间复杂度O(1)，为最优解法。
  Java代码：
  ```java
  class Solution {
      public int jump(int[] nums) {
          // 最小跳跃次数
          int count = 0;
          // 当前跳跃的边界
          int end = 0;
          // 当前能到达的最远位置
          int maxReach = 0;
          // 无需遍历最后一个元素，到达即终止
          for (int i = 0; i < nums.length - 1; i++) {
              // 更新最远可达位置
              maxReach = Math.max(maxReach, i + nums[i]);
              // 到达当前边界，必须跳跃
              if (i == end) {
                  count++;
                  // 更新边界为最远可达位置
                  end = maxReach;
              }
          }
          return count;
      }
  }
  ```
- 解法二：动态规划算法
  算法思想：定义dp数组，dp[i]表示到达下标i的最小跳跃次数。初始化dp数组为无穷大（代表不可达），起点dp[0]=0。遍历每个索引i，对i能跳跃到的所有位置j，更新dp[j]为dp[j]和dp[i]+1的最小值。最终dp[n-1]即为答案。该解法时间复杂度O(n²)，空间复杂度O(n)。
  Java代码：
  
  ```java
  class Solution {
      public int jump(int[] nums) {
          int n = nums.length;
          // dp[i]：到达下标i的最小跳跃次数
          int[] dp = new int[n];
          // 初始化数组为最大值，表示初始不可达
          for (int i = 1; i < n; i++) {
              dp[i] = Integer.MAX_VALUE;
          }
          // 起点无需跳跃
          dp[0] = 0;
          for (int i = 0; i < n; i++) {
              // 当前位置不可达，直接跳过
              if (dp[i] == Integer.MAX_VALUE) continue;
              // 遍历所有可跳跃到的位置
              for (int j = i + 1; j <= i + nums[i] && j < n; j++) {
                  dp[j] = Math.min(dp[j], dp[i] + 1);
              }
          }
          return dp[n - 1];
      }
  }
  ```
  

### [763. 划分字母区间](https://leetcode.cn/problems/partition-labels/)

1. 题目描述
给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。返回一个表示每个字符串片段的长度的列表。
示例 1：输入：s = "ababcbacadefegdehijhklij"，输出：[9,7,8]
解释：划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。每个字母最多出现在一个片段中。像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
示例 2：输入：s = "eccbbbbdec"，输出：[10]
提示：1 <= s.length <= 500，s 仅由小写英文字母组成

2. 算法思想+代码
- 算法思想：采用贪心算法实现，核心逻辑与跳跃游戏II的贪心策略一致
  1. 预处理字符串，创建长度为26的数组存储每个小写字母最后一次出现的下标位置，确保片段包含对应字母的所有出现
  2. 初始化两个边界变量，start表示当前片段的起始索引，end表示当前片段能覆盖的最远结束索引，同时创建列表存储最终结果
  3. 遍历字符串的每一个索引i：
     - 每次遍历更新最远结束索引end，取当前end与当前字符最后出现下标中的最大值
     - 当遍历到的索引i等于end时，代表当前片段划分完成，将片段长度加入结果列表，并将start更新为i+1，开始下一个片段的划分
- 代码实现
```java
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> partitionLabels(String s) {
        // 存储每个小写字母最后一次出现的索引
        int[] lastOccurrence = new int[26];
        int length = s.length();
        // 遍历字符串，记录每个字符的最后出现位置
        for (int i = 0; i < length; i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();
        // 初始化片段起始位置和最远结束位置
        int start = 0;
        int end = 0;
        // 遍历字符串划分片段
        for (int i = 0; i < length; i++) {
            // 更新当前片段的最远边界
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);
            // 到达当前片段的边界，完成一次划分
            if (i == end) {
                result.add(end - start + 1);
                // 更新起始位置为下一个片段的开头
                start = i + 1;
            }
        }
        return result;
    }
}
```

## 动态规划

1. 动态规划定义：动态规划（DP）是一种求解最优化问题的核心算法思想，它将大规模复杂问题拆解为若干存在关联的小规模子问题，依托**重叠子问题**和**最优子结构**两个关键特性，通过缓存子问题的解避免重复计算，最终逐步推导出原问题的最优解；其中重叠子问题指递归求解时会重复计算相同的子问题，最优子结构指原问题的最优解由子问题的最优解构成。
2. 动态规划常见操作（标准解题流程）：
   1. 定义状态：明确dp数组或变量的具体含义，确定每个状态代表的问题场景，是动态规划解题的基础
   2. 推导状态转移方程：建立当前状态与前置子状态的递推公式，是动态规划的核心逻辑
   3. 初始化边界值：确定无法通过递推计算的最小子问题的解，作为整个递推过程的起点
   4. 确定遍历顺序：根据状态之间的依赖关系，选择正向或反向遍历，保证计算当前状态时，所有依赖的前置状态已经完成求解
   5. 获取最终结果：根据状态的定义，从dp数组中提取出对应原问题的答案
3. Java实现的动态规划演示Demo，包含经典的爬楼梯问题和斐波那契数列，完整覆盖动态规划所有常用操作，代码可直接运行
```java
public class DynamicProgramDemo {
    public static void main(String[] args) {
        // 测试爬楼梯问题（动态规划核心案例）
        int stairNum = 10;
        System.out.println("爬" + stairNum + "阶楼梯的方法数（DP解法）：" + climbStairs(stairNum));
        System.out.println("爬" + stairNum + "阶楼梯的方法数（递归暴力解法）：" + climbStairsRecursion(stairNum));

        // 测试斐波那契数列（动态规划基础案例）
        int fibNum = 10;
        System.out.println("斐波那契数列第" + fibNum + "项（DP解法）：" + fibonacci(fibNum));
    }

    /**
     * 爬楼梯问题 - 动态规划标准实现
     * 问题：n阶楼梯，每次只能爬1阶或2阶，求总方法数
     * 严格对应动态规划5个常用操作
     */
    public static int climbStairs(int n) {
        // 边界特殊值处理
        if (n <= 1) return 1;
        // 操作1：定义状态 dp[i] = 爬i阶楼梯的总方法数
        int[] dp = new int[n + 1];
        // 操作3：初始化边界值（最小子问题的解）
        dp[1] = 1;
        dp[2] = 2;
        // 操作4：确定遍历顺序（正向遍历，依赖前序状态）
        for (int i = 3; i <= n; i++) {
            // 操作2：状态转移方程 dp[i] = dp[i-1] + dp[i-2]
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // 操作5：获取最终结果
        return dp[n];
    }

    /**
     * 爬楼梯 - 递归暴力解法（用于对比，存在大量重复计算）
     */
    public static int climbStairsRecursion(int n) {
        if (n <= 1) return 1;
        return climbStairsRecursion(n - 1) + climbStairsRecursion(n - 2);
    }

    /**
     * 斐波那契数列 - 动态规划基础实现
     * 定义：F(0)=0，F(1)=1，F(n)=F(n-1)+F(n-2)
     */
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        // 定义状态
        int[] dp = new int[n + 1];
        // 初始化边界
        dp[0] = 0;
        dp[1] = 1;
        // 遍历计算
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```
4. 代码说明：
   - 爬楼梯案例是动态规划的经典应用，代码中通过注释标注了每一步对应的动态规划常见操作，直观展示完整解题流程
   - 斐波那契数列是动态规划的入门案例，用于理解子问题解的缓存和递推逻辑
   - 提供递归暴力解法做对比，能清晰看到动态规划通过缓存子问题解，彻底解决了递归重复计算的效率问题
   - 直接运行main方法即可看到结果，修改stairNum和fibNum参数，可测试不同输入的输出结果

1. 动态规划核心是分解子问题+缓存解，依靠最优子结构和重叠子问题优化计算
2. 解题固定流程：定义状态→推导转移方程→初始化边界→确定遍历顺序→提取结果
3. 演示代码用Java实现了经典DP案例，严格贴合所有常用操作，可直接复用学习

### [70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

1. 题目描述
假设你正在爬楼梯，需要 n 阶你才能到达楼顶，每次你可以爬 1 或 2 个台阶，求有多少种不同的方法可以爬到楼顶。
示例 1：输入 n = 2，输出 2，解释为有两种方法爬到楼顶，分别是1阶 + 1阶、2阶。
示例 2：输入 n = 3，输出 3，解释为有三种方法爬到楼顶，分别是1阶 + 1阶 + 1阶、1阶 + 2阶、2阶 + 1阶。
提示：1 <= n <= 45

2. 解法一：暴力递归
- 算法思想：核心为递推关系，爬到第 n 阶的方法数等于爬到第 n-1 阶的方法数（最后一步爬1阶）加上爬到第 n-2 阶的方法数（最后一步爬2阶）；递归终止条件为 n=1 时只有1种方法，n=2 时有2种方法，该方法存在大量重复计算，效率较低
- Java代码
```java
public class Solution {
    public int climbStairs(int n) {
        // 递归终止条件
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        // 递推公式
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
```

3. 解法二：记忆化递归
- 算法思想：针对暴力递归的重复计算问题，使用数组存储已计算的结果，避免重复递归调用，将时间复杂度优化为 O(n)，空间复杂度为 O(n)
- Java代码
```java
public class Solution {
    public int climbStairs(int n) {
        // 创建数组存储中间结果
        int[] memo = new int[n + 1];
        return dfs(n, memo);
    }
    
    private int dfs(int n, int[] memo) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        // 若已计算过，直接返回结果
        if (memo[n] != 0) {
            return memo[n];
        }
        // 计算并存储结果
        memo[n] = dfs(n - 1, memo) + dfs(n - 2, memo);
        return memo[n];
    }
}
```

4. 解法三：基础动态规划
- 算法思想：采用自底向上的动态规划思路，定义 dp 数组，dp[i] 表示爬到第 i 阶的方法数；递推公式为 dp[i] = dp[i-1] + dp[i-2]，初始化 dp[1]=1、dp[2]=2，遍历计算得到 dp[n]，时间复杂度 O(n)，空间复杂度 O(n)
- Java代码
```java
public class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        // 定义dp数组
        int[] dp = new int[n + 1];
        // 初始化基础状态
        dp[1] = 1;
        dp[2] = 2;
        // 遍历计算
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
```

5. 解法四：滚动数组优化动态规划
- 算法思想：由于 dp[i] 仅依赖 dp[i-1] 和 dp[i-2] 两个状态，无需维护完整 dp 数组，用两个变量滚动更新前两个状态，将空间复杂度优化为 O(1)，时间复杂度仍为 O(n)
- Java代码
```java
public class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        // 定义两个变量存储前两个状态
        int first = 1;
        int second = 2;
        // 滚动更新
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
```

6. 解法五：数学公式法
- 算法思想：爬楼梯的递推关系符合斐波那契数列，直接使用斐波那契数列通项公式计算，时间复杂度 O(1)，空间复杂度 O(1)
- Java代码
```java
public class Solution {
    public int climbStairs(int n) {
        double sqrt5 = Math.sqrt(5);
        // 斐波那契通项公式
        double fibN = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) (fibN / sqrt5);
    }
}
```

### [118. 杨辉三角](https://leetcode.cn/problems/pascals-triangle/)

1. 题目描述
给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。在「杨辉三角」中，每个数是它左上方和右上方的数的和。
示例 1:
输入: numRows = 5
输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
示例 2:
输入: numRows = 1
输出: [[1]]
提示: 1 <= numRows <= 30

2. 算法思想+代码
- 解法一：迭代法（动态规划）
  算法思想：杨辉三角的每一行第一个和最后一个元素固定为1，中间的任意元素等于上一行中对应位置的前一个元素与当前位置元素的和。通过外层循环遍历行数，内层循环计算每行的中间元素，逐行构建杨辉三角。
  Java代码：
  ```java
  import java.util.ArrayList;
  import java.util.List;
  
  public class Solution {
      public List<List<Integer>> generate(int numRows) {
          // 存储最终结果的二维列表
          List<List<Integer>> res = new ArrayList<>();
          // 遍历生成每一行
          for (int i = 0; i < numRows; i++) {
              List<Integer> row = new ArrayList<>();
              // 每行元素个数等于行号+1
              for (int j = 0; j <= i; j++) {
                  // 首尾元素赋值为1
                  if (j == 0 || j == i) {
                      row.add(1);
                  } else {
                      // 中间元素 = 上一行左上方 + 右上方元素之和
                      row.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
                  }
              }
              res.add(row);
          }
          return res;
      }
  }
  ```

- 解法二：递归法
  算法思想：递归核心是通过前n-1行的结果推导第n行，递归终止条件为numRows=0返回空列表、numRows=1返回[[1]]。先递归生成前numRows-1行，再基于最后一行计算当前行，最终拼接所有行得到结果。
  Java代码：
  ```java
  import java.util.ArrayList;
  import java.util.List;
  
  public class Solution {
      public List<List<Integer>> generate(int numRows) {
          // 递归终止：0行返回空列表
          if (numRows == 0) {
              return new ArrayList<>();
          }
          // 递归生成前numRows-1行
          List<List<Integer>> preRows = generate(numRows - 1);
          // 构建当前行
          List<Integer> currRow = new ArrayList<>();
          for (int i = 0; i < numRows; i++) {
              // 首尾元素为1
              if (i == 0 || i == numRows - 1) {
                  currRow.add(1);
              } else {
                  // 中间元素通过前一行计算
                  currRow.add(preRows.get(numRows - 2).get(i - 1) + preRows.get(numRows - 2).get(i));
              }
          }
          preRows.add(currRow);
          return preRows;
      }
  }
  ```

### [198. 打家劫舍](https://leetcode.cn/problems/house-robber/)

1. 题目描述
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
示例 1：
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。
示例 2：
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。偷窃到的最高金额 = 2 + 9 + 1 = 12 。
提示：
1 <= nums.length <= 100
0 <= nums[i] <= 400
2. 算法思想+代码
- 解法一：动态规划（数组存储状态）
  算法思想：采用动态规划求解，定义dp数组，dp[i]表示遍历到第i间房屋时，能够偷窃到的最高金额。状态转移规则：对于第i间房屋，有两种选择，偷窃则不能偷第i-1间，总金额为dp[i-2] + nums[i]；不偷窃则总金额为dp[i-1]，取两者最大值作为dp[i]。边界条件：当只有1间房屋时，dp[0] = nums[0]；当有2间房屋时，dp[1] = Math.max(nums[0], nums[1])。最终dp数组最后一个元素即为答案。
  Java代码：
```java
public class Rob {
    public int rob(int[] nums) {
        // 处理空数组和单元素数组的边界情况
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // 定义dp数组，存储每一步的最大金额
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 遍历计算所有房屋的最大偷窃金额
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    // 测试示例
    public static void main(String[] args) {
        Rob rob = new Rob();
        int[] nums1 = {1,2,3,1};
        System.out.println(rob.rob(nums1));
        int[] nums2 = {2,7,9,3,1};
        System.out.println(rob.rob(nums2));
    }
}
```
- 解法二：动态规划（空间优化，滚动变量）
  算法思想：观察解法一的状态转移方程，dp[i]仅依赖dp[i-1]和dp[i-2]两个状态，无需维护整个dp数组，使用两个变量分别存储前两个状态的值，通过滚动更新实现空间复杂度优化，将空间复杂度从O(n)降低至O(1)。定义prev表示dp[i-2]，curr表示dp[i-1]，每次迭代计算新的curr值，最终curr即为答案。
  Java代码：
```java
public class RobOpt {
    public int rob(int[] nums) {
        // 处理空数组和单元素数组的边界情况
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // 定义滚动变量，替代dp数组
        int prev = nums[0];
        int curr = Math.max(nums[0], nums[1]);
        // 滚动更新变量，计算最大金额
        for (int i = 2; i < nums.length; i++) {
            int temp = curr;
            curr = Math.max(curr, prev + nums[i]);
            prev = temp;
        }
        return curr;
    }

    // 测试示例
    public static void main(String[] args) {
        RobOpt robOpt = new RobOpt();
        int[] nums1 = {1,2,3,1};
        System.out.println(robOpt.rob(nums1));
        int[] nums2 = {2,7,9,3,1};
        System.out.println(robOpt.rob(nums2));
    }
}
```

### [279. 完全平方数](https://leetcode.cn/problems/perfect-squares/)

1. 题目描述
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
示例 1：输入：n = 12，输出：3 ，解释：12 = 4 + 4 + 4
示例 2：输入：n = 13，输出：2，解释：13 = 4 + 9
提示：1 <= n <= 10^4

2. 算法思想+代码
- 解法一：动态规划
  算法思想：定义dp数组，其中dp[i]表示组成数字i的最少完全平方数数量；初始化dp数组为最大值，dp[0] = 0作为边界条件（数字0无需任何完全平方数）；遍历1到n的所有数字，对于每个数字i，再遍历所有小于等于i的完全平方数j*j，通过状态转移方程dp[i] = min(dp[i], dp[i-j*j]+1)更新最小值，最终dp[n]即为答案。
  ```java
  import java.util.Arrays;
  
  class Solution {
      public int numSquares(int n) {
          // dp[i]代表和为i的完全平方数的最少数量
          int[] dp = new int[n + 1];
          // 初始化数组为最大值，最大可能数量为n（全用1）
          Arrays.fill(dp, Integer.MAX_VALUE);
          // 边界条件：0需要0个完全平方数
          dp[0] = 0;
          
          for (int i = 1; i <= n; i++) {
              // 遍历所有小于等于i的完全平方数
              for (int j = 1; j * j <= i; j++) {
                  dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
              }
          }
          return dp[n];
      }
  }
  ```
- 解法二：广度优先搜索（BFS）
  算法思想：将问题转化为图的最短路径问题，每个数字为一个节点，若两个数字相差一个完全平方数，则两节点间存在边；从数字n出发，层级遍历所有节点，每一层代表使用的完全平方数数量，第一次到达数字0时的层级数，就是和为n的完全平方数的最少数量。
  ```java
  import java.util.LinkedList;
  import java.util.Queue;
  
  class Solution {
      public int numSquares(int n) {
          // 队列存储当前遍历的数字
          Queue<Integer> queue = new LinkedList<>();
          // 标记已访问的数字，避免重复遍历
          boolean[] visited = new boolean[n + 1];
          queue.offer(n);
          visited[n] = true;
          // 记录层级，即完全平方数的最少数量
          int level = 0;
          
          while (!queue.isEmpty()) {
              int size = queue.size();
              level++;
              // 遍历当前层的所有数字
              for (int i = 0; i < size; i++) {
                  int num = queue.poll();
                  // 遍历所有小于当前数字的完全平方数
                  for (int j = 1; j * j <= num; j++) {
                      int nextNum = num - j * j;
                      // 首次到达0，直接返回当前层级
                      if (nextNum == 0) {
                          return level;
                      }
                      // 未访问过的数字加入队列
                      if (!visited[nextNum]) {
                          visited[nextNum] = true;
                          queue.offer(nextNum);
                      }
                  }
              }
          }
          return level;
      }
  }
  ```

### [322. 零钱兑换](https://leetcode.cn/problems/coin-change/)

1. 题目描述：给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。计算并返回可以凑成总金额所需的最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。你可以认为每种硬币的数量是无限的。示例 1：输入：coins = [1, 2, 5], amount = 11，输出：3 ，解释：11 = 5 + 5 + 1；示例 2：输入：coins = [2], amount = 3，输出：-1；示例 3：输入：coins = [1], amount = 0，输出：0。提示：1 <= coins.length <= 12，1 <= coins[i] <= 2^31 - 1，0 <= amount <= 10^4
2. 解法一：动态规划（自底向上）
- 算法思想：属于完全背包问题的经典应用，硬币可无限次选取，目标是求凑成总金额的最小硬币数。定义dp数组，其中dp[i]表示凑成金额i所需要的最少硬币数；初始化dp数组为amount+1（一个大于最大可能硬币数的值），dp[0]=0（凑成金额0需要0个硬币）；依次遍历1到amount的所有金额，对每个金额遍历所有硬币，若硬币面额小于等于当前金额，则更新dp[i]为dp[i]和dp[i-coin]+1中的较小值；遍历完成后，若dp[amount]仍大于amount，说明无法凑成总金额，返回-1，否则返回dp[amount]。
- Java代码：
```java
import java.util.Arrays;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        // 定义最大初始值，超过amount的最大可能硬币数
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        // 初始化dp数组，所有值设为max
        Arrays.fill(dp, max);
        //  base case：金额为0时，需要0个硬币
        dp[0] = 0;
        // 遍历所有需要计算的金额
        for (int i = 1; i <= amount; i++) {
            // 遍历每种硬币
            for (int coin : coins) {
                // 只有硬币面额小于等于当前金额时，才能使用该硬币
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        // 判断是否能凑成总金额
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```
3. 解法二：广度优先搜索（BFS）
- 算法思想：将每个金额视为图的节点，每次添加一种硬币得到的新金额为当前节点的相邻节点，问题转化为求从节点0到节点amount的最短路径（路径长度即为最少硬币数）。使用队列实现BFS遍历，用布尔数组记录已访问的金额避免重复计算；每遍历一层队列代表使用一个硬币，层数即为硬币个数，当遍历到目标金额时，直接返回当前层数；若队列为空仍未找到目标金额，说明无法凑成，返回-1。
- Java代码：
```java
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        // 金额为0直接返回0
        if (amount == 0) {
            return 0;
        }
        // 队列存储当前遍历到的金额
        Queue<Integer> queue = new LinkedList<>();
        // 标记已访问的金额，防止重复遍历
        boolean[] visited = new boolean[amount + 1];
        queue.offer(0);
        visited[0] = true;
        // 记录使用的硬币个数（BFS的层数）
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 层数加1，对应硬币个数加1
            count++;
            // 遍历当前层的所有金额
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                // 遍历所有硬币，计算下一个金额
                for (int coin : coins) {
                    int nextAmount = current + coin;
                    // 找到目标金额，直接返回硬币个数
                    if (nextAmount == amount) {
                        return count;
                    }
                    // 下一个金额小于总金额且未被访问，加入队列
                    if (nextAmount < amount && !visited[nextAmount]) {
                        visited[nextAmount] = true;
                        queue.offer(nextAmount);
                    }
                }
            }
        }
        // 遍历结束未找到，返回-1
        return -1;
    }
}
```

### [139. 单词拆分](https://leetcode.cn/problems/word-break/)

1. 题目描述
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。注意：你可以重复使用字典中的单词。
示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
提示：
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s 和 wordDict[i] 仅由小写英文字母组成
wordDict 中的所有字符串 互不相同

1. 解法一：动态规划
- 算法思想：采用动态规划思路，定义布尔类型dp数组，dp[i]表示字符串s的前i个字符（即子串s[0..i-1]）能否由字典中的单词拼接而成；初始化dp[0] = true，代表空字符串可以被拼接；遍历字符串的每个位置i，再遍历0到i-1的位置j，若dp[j]为true且子串s[j..i-1]存在于字典中，则将dp[i]设为true；将字典列表转换为哈希集合，提升单词的查询效率；最终返回dp数组的最后一个元素，即dp[s.length()]。
```java
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 字典转为哈希集合，实现O(1)时间复杂度查询
        Set<String> wordSet = new HashSet<>(wordDict);
        int strLength = s.length();
        // dp数组：dp[i]标记字符串前i个字符是否可拼接
        boolean[] dp = new boolean[strLength + 1];
        // 空字符串默认可拼接
        dp[0] = true;
        
        for (int i = 1; i <= strLength; i++) {
            for (int j = 0; j < i; j++) {
                // 前j个字符可拼接 + j到i的子串在字典中 → 前i个字符可拼接
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[strLength];
    }
}
```

1. 解法二：广度优先搜索（BFS）
- 算法思想：将字符串的索引视为图的节点，若从索引i出发能匹配字典中的单词到达索引j，则i和j之间存在边；使用队列存储待遍历的索引，初始时将索引0加入队列；使用布尔数组标记已访问的索引，避免重复入队导致死循环；遍历队列中的索引，从该索引开始尝试匹配所有字典单词，若匹配后到达字符串末尾则返回true，否则将新的索引加入队列；若队列为空仍未到达末尾，返回false。
```java
import java.util.*;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int strLength = s.length();
        // 标记已访问的索引，防止重复处理
        boolean[] visited = new boolean[strLength];
        Queue<Integer> queue = new LinkedList<>();
        // 从索引0开始遍历
        queue.offer(0);
        
        while (!queue.isEmpty()) {
            int start = queue.poll();
            // 跳过已处理的索引
            if (visited[start]) {
                continue;
            }
            visited[start] = true;
            
            // 遍历所有可能的结束位置
            for (int end = start + 1; end <= strLength; end++) {
                String subStr = s.substring(start, end);
                if (wordSet.contains(subStr)) {
                    // 匹配到字符串末尾，直接返回true
                    if (end == strLength) {
                        return true;
                    }
                    // 将新的起始索引加入队列
                    queue.offer(end);
                }
            }
        }
        return false;
    }
}
```

1. 解法三：记忆化回溯
- 算法思想：采用回溯法递归处理字符串，从起始索引开始尝试匹配字典中的所有单词，匹配成功则递归处理剩余子串；普通回溯会重复计算子问题，因此添加记忆化数组存储索引的计算结果，memo[i] = 1代表从i开始的子串可拼接，memo[i] = 0代表不可拼接，memo[i] = -1代表未计算；通过记忆化跳过重复递归，大幅提升算法效率。
```java
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    // 记忆化数组：-1未计算，0不可拼接，1可拼接
    private int[] memo;
    private Set<String> wordSet;

    public boolean wordBreak(String s, List<String> wordDict) {
        wordSet = new HashSet<>(wordDict);
        memo = new int[s.length()];
        // 初始化所有索引为未计算状态
        Arrays.fill(memo, -1);
        return backtrack(s, 0);
    }

    // 回溯递归函数：判断从start索引开始的子串是否可拼接
    private boolean backtrack(String s, int start) {
        // 递归终止：索引到达字符串末尾，说明拼接成功
        if (start == s.length()) {
            return true;
        }
        // 记忆化剪枝：已计算过直接返回结果
        if (memo[start] != -1) {
            return memo[start] == 1;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String subStr = s.substring(start, end);
            if (wordSet.contains(subStr)) {
                // 后续子串可拼接，记录结果并返回
                if (backtrack(s, end)) {
                    memo[start] = 1;
                    return true;
                }
            }
        }
        // 所有单词都无法匹配，记录不可拼接
        memo[start] = 0;
        return false;
    }
}
```

### [300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)

1. 题目描述
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

示例 1：
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

示例 2：
输入：nums = [0,1,0,3,2,3]
输出：4

示例 3：
输入：nums = [7,7,7,7,7,7,7]
输出：1

提示：
- 1 <= nums.length <= 2500
- -10⁴ <= nums[i] <= 10⁴

进阶：你能将算法的时间复杂度降低到 O(n log(n)) 吗?

2. 解法一：动态规划
- 算法思想：
  - 定义 dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度
  - 对于每个 i，遍历 j 从 0 到 i-1，如果 nums[j] < nums[i]，则 dp[i] = max(dp[i], dp[j] + 1)
  - 初始状态：每个元素自身构成长度为1的子序列，即 dp[i] = 1
  - 最终结果为 dp 数组中的最大值
  - 时间复杂度：O(n²)，空间复杂度：O(n)

- Java 代码：
```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    int maxLen = 1;
    
    for (int i = 0; i < n; i++) {
        dp[i] = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        maxLen = Math.max(maxLen, dp[i]);
    }
    
    return maxLen;
}
```

3. 解法二：贪心 + 二分查找
- 算法思想：
  - 维护一个 tails 数组，其中 tails[i] 表示长度为 i+1 的递增子序列的最小末尾元素
  - 遍历数组中的每个数 num：
    - 如果 num 大于 tails 数组最后一个元素，直接追加到 tails 末尾
    - 否则，在 tails 数组中找到第一个大于等于 num 的位置，用 num 替换该位置的元素
  - tails 数组的长度即为最长递增子序列的长度
  - 时间复杂度：O(n log n)，空间复杂度：O(n)

- Java 代码：
```java
public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    int[] tails = new int[n];
    int len = 0;
    
    for (int num : nums) {
        int left = 0, right = len;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tails[mid] < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        tails[left] = num;
        if (left == len) {
            len++;
        }
    }
    
    return len;
}
```



### [152. 乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/)

1. 题目描述：给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。测试用例的答案是一个 32-位 整数。请注意，一个只包含一个元素的数组的乘积是这个元素的值。
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2，因为 [-2,-1] 不是子数组。
提示：1 <= nums.length <= 2 * 10^4，-10 <= nums[i] <= 10，nums 的任何子数组的乘积都保证是一个 32-位 整数。

2. 解法一：动态规划
算法思想：由于数组中存在负数，负负相乘会得到正数，因此仅维护当前的最大乘积是不够的，还需要维护当前的最小乘积。当遍历到负数元素时，当前的最小乘积（可能为负数）与该负数相乘会得到较大的正数，可能成为新的最大乘积。遍历数组过程中，每次计算当前元素与前一个最大乘积的乘积、当前元素与前一个最小乘积的乘积、当前元素本身这三个值的最大值作为新的当前最大乘积，最小值作为新的当前最小乘积，同时不断更新全局的最大乘积。
Java代码：
```java
public class Solution {
    public int maxProduct(int[] nums) {
        int maxResult = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int temp = currentMax;
            currentMax = Math.max(Math.max(currentMax * nums[i], currentMin * nums[i]), nums[i]);
            currentMin = Math.min(Math.min(temp * nums[i], currentMin * nums[i]), nums[i]);
            maxResult = Math.max(maxResult, currentMax);
        }
        return maxResult;
    }
}
```

3. 解法二：双向遍历法
算法思想：数组中的0会中断连续子数组的乘积计算，因此可以将数组按0拆分为多个非0的连续段。对于每个非0连续段，最大乘积的子数组要么从段首开始延伸到某个位置，要么从段尾开始延伸到某个位置（如果段中存在偶数个负数，整个段的乘积就是最大；如果是奇数个负数，要么去掉最左边的负数，要么去掉最右边的负数，对应正向遍历和反向遍历的结果）。因此可以分别从左到右、从右到左各遍历一次数组，遇到0时重置当前乘积，同时记录遍历过程中的最大乘积。
Java代码：
```java
public class Solution {
    public int maxProduct(int[] nums) {
        int maxResult = nums[0];
        int currentProduct = 1;
        
        for (int num : nums) {
            currentProduct *= num;
            maxResult = Math.max(maxResult, currentProduct);
            if (num == 0) {
                currentProduct = 1;
            }
        }
        
        currentProduct = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            currentProduct *= nums[i];
            maxResult = Math.max(maxResult, currentProduct);
            if (nums[i] == 0) {
                currentProduct = 1;
            }
        }
        
        return maxResult;
    }
}
```

### [416. 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum/)

1. 题目描述
给你一个只包含正整数的非空数组 nums。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

示例 1：
输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11]。

示例 2：
输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。

提示：
- 1 <= nums.length <= 200
- 1 <= nums[i] <= 100

2. 解法一：二维动态规划
- 算法思想：
  - 首先计算数组所有元素的总和，如果总和为奇数，直接返回false，因为无法分割成两个和相等的子集
  - 如果总和为偶数，目标就是判断是否存在子集的和等于总和的一半（target = sum / 2）
  - 这是典型的0-1背包问题，dp[i][j]表示前i个元素中是否存在子集和为j
  - 状态转移：对于第i个元素，有两种选择：选或不选
    - 不选：dp[i][j] = dp[i-1][j]
    - 选：如果j >= nums[i-1]，则dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]

```java
public boolean canPartition(int[] nums) {
    int n = nums.length;
    int sum = 0;
    for (int num : nums) {
        sum += num;
    }
    // 如果总和为奇数，无法分割
    if (sum % 2 != 0) {
        return false;
    }
    int target = sum / 2;
    // dp[i][j]表示前i个元素中是否存在子集和为j
    boolean[][] dp = new boolean[n + 1][target + 1];
    // 初始化：前0个元素和为0是可能的
    for (int i = 0; i <= n; i++) {
        dp[i][0] = true;
    }
    
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= target; j++) {
            // 不选第i个元素
            dp[i][j] = dp[i - 1][j];
            // 选第i个元素（如果容量足够）
            if (j >= nums[i - 1]) {
                dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
            }
        }
    }
    return dp[n][target];
}
```

3. 解法二：一维动态规划（空间优化）
- 算法思想：
  - 在二维动态规划的基础上进行空间优化
  - 观察到dp[i][j]只依赖于dp[i-1][...]，因此可以用一维数组从后往前遍历
  - dp[j]表示是否存在子集和为j
  - 逆序遍历是为了保证每个元素只被使用一次（避免重复使用同一元素）

```java
public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int num : nums) {
        sum += num;
    }
    // 如果总和为奇数，无法分割
    if (sum % 2 != 0) {
        return false;
    }
    int target = sum / 2;
    // dp[j]表示是否存在子集和为j
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;
    
    for (int num : nums) {
        // 逆序遍历，避免重复使用同一元素
        for (int j = target; j >= num; j--) {
            dp[j] = dp[j] || dp[j - num];
        }
    }
    return dp[target];
}
```

### [32. 最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses/)

1. 题目描述

给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

左右括号匹配，即每个左括号都有对应的右括号将其闭合的字符串是格式正确的，比如 "(()())"。

示例 1：
输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"

示例 2：
输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"

示例 3：
输入：s = ""
输出：0

提示：
- 0 <= s.length <= 3 * 10^4
- s[i] 为 '(' 或 ')'

2. 算法思想+代码

解法一：动态规划

- 定义 dp[i] 表示以下标 i 字符结尾的最长有效括号的长度
- 当 s[i] 为 ')' 且 s[i-1] 为 '(' 时，dp[i] = dp[i-2] + 2
- 当 s[i] 为 ')' 且 s[i-1] 为 ')' 时，如果 s[i - dp[i-1] - 1] 为 '('，则 dp[i] = dp[i-1] + dp[i - dp[i-1] - 2] + 2
- 遍历过程中记录最大值

```java
public int longestValidParentheses(String s) {
    int maxLen = 0;
    int n = s.length();
    int[] dp = new int[n];
    
    for (int i = 1; i < n; i++) {
        if (s.charAt(i) == ')') {
            if (s.charAt(i - 1) == '(') {
                dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
            } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
    }
    return maxLen;
}
```

解法二：栈

- 使用栈存储下标，初始时将 -1 压入栈作为基准
- 遇到 '(' 时将其下标压入栈
- 遇到 ')' 时弹出栈顶元素，若栈为空则将当前下标压入栈，否则计算当前下标与栈顶元素的差值即为有效括号长度
- 遍历过程中记录最大长度

```java
public int longestValidParentheses(String s) {
    int maxLen = 0;
    Deque<Integer> stack = new LinkedList<>();
    stack.push(-1);
    
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            stack.push(i);
        } else {
            stack.pop();
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                maxLen = Math.max(maxLen, i - stack.peek());
            }
        }
    }
    return maxLen;
}
```

解法三：双指针

- 使用 left 和 right 两个指针分别统计左右括号的数量
- 从左到右遍历：left == right 时计算长度，right > left 时重置两个指针
- 从右到左遍历：left == right 时计算长度，left > right 时重置两个指针
- 两次遍历取最大值

```java
public int longestValidParentheses(String s) {
    int left = 0, right = 0, maxLen = 0;
    
    // 从左到右遍历
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '(') {
            left++;
        } else {
            right++;
        }
        if (left == right) {
            maxLen = Math.max(maxLen, 2 * right);
        } else if (right > left) {
            left = right = 0;
        }
    }
    
    left = right = 0;
    
    // 从右到左遍历
    for (int i = s.length() - 1; i >= 0; i--) {
        if (s.charAt(i) == '(') {
            left++;
        } else {
            right++;
        }
        if (left == right) {
            maxLen = Math.max(maxLen, 2 * left);
        } else if (left > right) {
            left = right = 0;
        }
    }
    
    return maxLen;
}
```

## 多维动态规划

**定义**

多维动态规划（Multi-dimensional Dynamic Programming）是动态规划的一种扩展形式，指状态转移方程中包含两个或两个以上维度的状态变量的动态规划问题。

- 核心思想与一维动态规划一致：将复杂问题分解为重叠子问题，通过存储子问题的解避免重复计算

- 区别在于状态表示需要多个维度来刻画，常见的有二维、三维甚至更高维

- 状态通常用 `dp[i][j]`、`dp[i][j][k]` 等多维数组表示，每个维度对应问题中的一个约束条件或状态变量

- 适用场景包括但不限于：二维路径问题、字符串匹配、区间问题、背包问题扩展、矩阵链乘法等

- 时间复杂度通常为 O(n^m)，其中 n 为每维规模，m 为维度数量；空间复杂度同理

  

**常见操作**

1. **状态定义**：明确 dp 数组每个维度的含义
   - 确定每个维度代表的物理意义（如位置、长度、数量、状态标记等）
   - 确保状态定义能完整描述子问题的所有约束条件
   - 常见二维状态如 `dp[i][j]` 可表示：前 i 个元素中选 j 个的最优值、从起点到 (i,j) 的路径数等

2. **状态转移方程推导**：建立当前状态与之前状态的关系
   - 分析第 i 维状态由哪些前面的子状态转移而来
   - 考虑"选"与"不选"、"来自上方"还是"来自左方"等多种决策
   - 确保转移方程覆盖所有合法的转移路径，且无后效性

3. **初始化**：确定边界条件和 base case
   - 通常初始化 dp 数组的第 0 行、第 0 列或某些边界位置
   - 根据问题含义确定初始值（如 0、1、最大值/最小值等）
   - 注意区分"不可能状态"与"初始状态"的取值差异

4. **遍历顺序确定**：保证计算当前状态时所需的前置状态已被计算
   - 二维问题常见顺序：从上到下、从左到右（正序遍历）
   - 某些问题需要逆序遍历（如 01 背包的空间优化）
   - 区间 DP 通常按区间长度从小到大遍历
   - 三维及以上需逐层分析依赖关系

5. **返回结果提取**：确定最终答案在 dp 数组中的位置
   - 可能是 `dp[n][m]`（终点状态）
   - 也可能需要遍历整个 dp 数组取最大/最小值
   - 某些问题答案在最后一行/列的某个特定位置

6. **空间优化**：降低空间复杂度
   - 滚动数组：若第 i 层只依赖第 i-1 层，可将二维降为一维
   - 状态压缩：利用位运算或更紧凑的数据结构表示状态
   - 优化后需注意遍历顺序是否需要调整

7. **常见题型分类**
   - 路径类：矩阵中从起点到终点的路径数/最小路径和
   - 字符串类：最长公共子序列、编辑距离、正则匹配
   - 区间类：戳气球、最长回文子序列
   - 背包扩展类：二维费用背包、分组背包
   - 状态压缩类：旅行商问题（TSP）、位运算 DP

下面是一个完整的 Java 示例，包含三个经典多维动态规划问题的实现：

```java
public class MultiDimDPDemo {

    // ==================== 示例1：二维路径问题（不同路径）====================
    // 问题：一个 m x n 的网格，从左上角走到右下角，只能向右或向下走，有多少种不同路径
    public static int uniquePaths(int m, int n) {
        // 1. 状态定义：dp[i][j] 表示走到 (i,j) 位置的不同路径数
        int[][] dp = new int[m][n];
        
        // 3. 初始化：第一行和第一列都只有 1 种走法
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // 2. 状态转移 + 4. 遍历顺序：从上到下、从左到右
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 到达 (i,j) 的路径数 = 从上方来的路径数 + 从左方来的路径数
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        // 5. 返回结果：右下角的值
        return dp[m - 1][n - 1];
    }

    // ==================== 示例2：最长公共子序列（LCS）====================
    // 问题：求两个字符串的最长公共子序列的长度
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // 1. 状态定义：dp[i][j] 表示 text1 前 i 个字符和 text2 前 j 个字符的 LCS 长度
        int[][] dp = new int[m + 1][n + 1];
        
        // 3. 初始化：dp[0][j] 和 dp[i][0] 都为 0（空串的 LCS 长度为 0），数组默认值已满足
        
        // 2. 状态转移 + 4. 遍历顺序
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 两个字符相等，LCS 长度 = 两个各去掉最后一个字符的 LCS 长度 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 两个字符不相等，取去掉 text1 最后一个或去掉 text2 最后一个的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // 5. 返回结果
        return dp[m][n];
    }

    // ==================== 示例3：编辑距离（三维思想的二维体现）====================
    // 问题：将 word1 转换成 word2 最少需要多少次操作（插入、删除、替换）
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // 1. 状态定义：dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符的最少操作数
        int[][] dp = new int[m + 1][n + 1];
        
        // 3. 初始化
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // word2 为空，需要删除 i 次
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // word1 为空，需要插入 j 次
        }
        
        // 2. 状态转移 + 4. 遍历顺序
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 字符相等，不需要操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 三种操作取最小：删除(dp[i-1][j])、插入(dp[i][j-1])、替换(dp[i-1][j-1])
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        
        // 5. 返回结果
        return dp[m][n];
    }

    // ==================== 示例4：空间优化版 01 背包（二维降一维）====================
    // 问题：有 n 个物品，每个物品重量 w[i]，价值 v[i]，背包容量为 capacity，求最大价值
    public static int knapsack(int[] w, int[] v, int capacity) {
        int n = w.length;
        
        // 6. 空间优化：原本是 dp[i][j] 二维数组，优化为 dp[j] 一维数组
        // 状态定义：dp[j] 表示背包容量为 j 时的最大价值
        int[] dp = new int[capacity + 1];
        
        // 遍历每个物品
        for (int i = 0; i < n; i++) {
            // 注意：必须逆序遍历容量，避免同一物品被多次选择（01 背包特性）
            for (int j = capacity; j >= w[i]; j--) {
                // 选或不选第 i 个物品，取价值较大的
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        
        return dp[capacity];
    }

    // ==================== 主方法：演示运行 ====================
    public static void main(String[] args) {
        System.out.println("===== 多维动态规划 Demo 演示 =====");
        System.out.println();
        
        // 示例1：不同路径
        System.out.println("【示例1：不同路径】");
        int m = 3, n = 7;
        System.out.println("网格大小: " + m + " x " + n);
        System.out.println("不同路径数: " + uniquePaths(m, n));
        System.out.println();
        
        // 示例2：最长公共子序列
        System.out.println("【示例2：最长公共子序列 LCS】");
        String text1 = "abcde", text2 = "ace";
        System.out.println("text1: " + text1);
        System.out.println("text2: " + text2);
        System.out.println("LCS 长度: " + longestCommonSubsequence(text1, text2));
        System.out.println();
        
        // 示例3：编辑距离
        System.out.println("【示例3：编辑距离】");
        String word1 = "horse", word2 = "ros";
        System.out.println("word1: " + word1);
        System.out.println("word2: " + word2);
        System.out.println("最少操作数: " + minDistance(word1, word2));
        System.out.println();
        
        // 示例4：01 背包（空间优化版）
        System.out.println("【示例4：01 背包（空间优化版）】");
        int[] w = {2, 3, 4, 5};
        int[] v = {3, 4, 5, 6};
        int capacity = 8;
        System.out.print("物品重量: ");
        for (int x : w) System.out.print(x + " ");
        System.out.println();
        System.out.print("物品价值: ");
        for (int x : v) System.out.print(x + " ");
        System.out.println();
        System.out.println("背包容量: " + capacity);
        System.out.println("最大价值: " + knapsack(w, v, capacity));
    }
}
```

**运行结果说明**

将上述代码保存为 `MultiDimDPDemo.java` 后运行，输出如下：

```
===== 多维动态规划 Demo 演示 =====

【示例1：不同路径】
网格大小: 3 x 7
不同路径数: 28

【示例2：最长公共子序列 LCS】
text1: abcde
text2: ace
LCS 长度: 3

【示例3：编辑距离】
word1: horse
word2: ros
最少操作数: 3

【示例4：01 背包（空间优化版）】
物品重量: 2 3 4 5 
物品价值: 3 4 5 6 
背包容量: 8
最大价值: 10
```

### [62. 不同路径](https://leetcode.cn/problems/unique-paths/)

1. 题目描述
一个机器人位于一个 m x n 网格的左上角（起始点在下图中标记为 "Start"）。机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 "Finish"）。问总共有多少条不同的路径？

示例 1：
输入：m = 3, n = 7
输出：28

示例 2：
输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
- 向右 -> 向下 -> 向下
- 向下 -> 向下 -> 向右
- 向下 -> 向右 -> 向下

示例 3：
输入：m = 7, n = 3
输出：28

示例 4：
输入：m = 3, n = 3
输出：6

提示：
- 1 <= m, n <= 100
- 题目数据保证答案小于等于 2 * 10^9

2. 算法思想+代码

解法一：动态规划（二维数组）
- 定义 dp[i][j] 表示到达第 i 行第 j 列位置的不同路径数
- 由于机器人只能向下或向右移动，到达位置 (i,j) 的路径数等于到达其上方位置 (i-1,j) 的路径数加上到达其左侧位置 (i,j-1) 的路径数
- 状态转移方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
- 初始化：第一行和第一列的所有位置都只有 1 条路径，因为只能一直向右或一直向下走
- 时间复杂度：O(m*n)，空间复杂度：O(m*n)

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    
    // 初始化第一列
    for (int i = 0; i < m; i++) {
        dp[i][0] = 1;
    }
    
    // 初始化第一行
    for (int j = 0; j < n; j++) {
        dp[0][j] = 1;
    }
    
    // 填充dp数组
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }
    }
    
    return dp[m-1][n-1];
}
```

解法二：动态规划（一维数组优化空间）
- 观察二维动态规划的状态转移方程，发现计算 dp[i][j] 只需要上一行的数据（dp[i-1][j]）和当前行前一列的数据（dp[i][j-1]）
- 可以用一维数组来优化空间，dp[j] 表示当前行第 j 列的路径数
- 遍历每一行时，从左到右更新 dp[j] = dp[j] + dp[j-1]，其中 dp[j] 是上一行第 j 列的值，dp[j-1] 是当前行第 j-1 列的值
- 时间复杂度：O(m*n)，空间复杂度：O(n)

```java
public int uniquePaths(int m, int n) {
    int[] dp = new int[n];
    
    // 初始化第一行
    for (int j = 0; j < n; j++) {
        dp[j] = 1;
    }
    
    // 逐行更新
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j] + dp[j-1];
        }
    }
    
    return dp[n-1];
}
```

解法三：数学组合数
- 从左上角到右下角总共需要走 (m-1) + (n-1) = m+n-2 步
- 其中必须向下走 m-1 步，向右走 n-1 步
- 问题转化为：在总共 m+n-2 步中选择 m-1 步向下走（或选择 n-1 步向右走），有多少种选法
- 即求组合数 C(m+n-2, m-1) 或 C(m+n-2, n-1)
- 计算时可以从 1 开始逐步相乘，避免数值溢出（题目保证答案不超过 2*10^9）
- 时间复杂度：O(min(m,n))，空间复杂度：O(1)

```java
public int uniquePaths(int m, int n) {
    // 取较小值减少计算次数
    int k = Math.min(m - 1, n - 1);
    int totalSteps = m + n - 2;
    
    long result = 1;
    for (int i = 1; i <= k; i++) {
        // 等价于 result = result * (totalSteps - k + i) / i
        result = result * (totalSteps - k + i) / i;
    }
    
    return (int) result;
}
```

### [64. 最小路径和](https://leetcode.cn/problems/minimum-path-sum/)

1. 题目描述

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例 1：
输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。

示例 2：
输入：grid = [[1,2,3],[4,5,6]]
输出：12

提示：
- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 200
- 0 <= grid[i][j] <= 200

2. 算法思想+代码

解法一：二维动态规划

- 创建一个与原网格大小相同的 dp 数组，dp[i][j] 表示从左上角到达位置 (i,j) 的最小路径和
- 由于只能向右或向下移动，到达 (i,j) 只能从上方 (i-1,j) 或左方 (i,j-1) 而来
- 状态转移方程：dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
- 初始化：第一行只能从左边来，第一列只能从上面来
- 时间复杂度：O(m*n)，需要遍历整个网格一次
- 空间复杂度：O(m*n)，需要额外的 dp 数组

```java
public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m][n];
    
    // 初始化起点
    dp[0][0] = grid[0][0];
    
    // 初始化第一行
    for (int j = 1; j < n; j++) {
        dp[0][j] = dp[0][j - 1] + grid[0][j];
    }
    
    // 初始化第一列
    for (int i = 1; i < m; i++) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    
    // 填充 dp 数组
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
        }
    }
    
    return dp[m - 1][n - 1];
}
```

解法二：一维动态规划（空间优化）

- 观察到计算 dp[i][j] 只需要上一行的数据和当前行已计算的数据，因此可以用一维数组代替二维数组
- 使用一个长度为 n 的 dp 数组，dp[j] 表示当前行第 j 列的最小路径和
- 遍历每一行时，从左到右更新 dp 数组：dp[j] = grid[i][j] + min(dp[j], dp[j-1])
- 其中 dp[j] 保存的是上一行第 j 列的值（即从上方来的路径和），dp[j-1] 是当前行已计算的左边的值
- 时间复杂度：O(m*n)
- 空间复杂度：O(n)，只需要一维数组

```java
public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[] dp = new int[n];
    
    // 初始化第一行
    dp[0] = grid[0][0];
    for (int j = 1; j < n; j++) {
        dp[j] = dp[j - 1] + grid[0][j];
    }
    
    // 逐行计算
    for (int i = 1; i < m; i++) {
        // 更新当前行第一列（只能从上方来）
        dp[0] = dp[0] + grid[i][0];
        for (int j = 1; j < n; j++) {
            dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
        }
    }
    
    return dp[n - 1];
}
```

解法三：原地修改（空间优化 O(1)）

- 直接在原网格上进行修改，不使用额外空间
- 原理与二维动态规划相同，只是将计算结果存回原数组
- 遍历顺序：先初始化第一行和第一列，再按行或按列填充其余位置
- 时间复杂度：O(m*n)
- 空间复杂度：O(1)，直接在原数组上修改（会修改输入数据）

```java
public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    
    // 初始化第一行
    for (int j = 1; j < n; j++) {
        grid[0][j] += grid[0][j - 1];
    }
    
    // 初始化第一列
    for (int i = 1; i < m; i++) {
        grid[i][0] += grid[i - 1][0];
    }
    
    // 填充其余位置
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
        }
    }
    
    return grid[m - 1][n - 1];
}
```

### [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)













# 结尾

多维动态规划：整理算法内容，使用java编写一个demo演示常用操作，需要包含以下内容，注意不使用分级标题（可以使用有序列表和无序列表）

1.定义

2.常见操作



整理算法内容，使用java，需要包含以下内容，注意不使用分级标题（可以使用有序列表和无序列表），不要写总结

1.题目描述

2.算法思想+代码、

（如果有多种解法分开写）

