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
































# 结尾
