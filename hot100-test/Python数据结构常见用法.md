# Python 数据结构常见用法

这份笔记面向 LeetCode Hot100 刷题，重点放在常见数据结构的使用场景、常用操作和典型写法。

## 1. list 列表

`list` 是最常用的序列结构，有序、可变、允许重复元素。常用于数组、栈、路径记录、动态规划数组。

### 常见操作

```python
nums = [1, 2, 3]

nums.append(4)      # 末尾添加: [1, 2, 3, 4]
nums.pop()          # 删除并返回末尾元素
nums.insert(1, 9)   # 指定位置插入
nums.remove(9)      # 删除第一个值为 9 的元素

nums[0]             # 访问元素
nums[-1]            # 访问最后一个元素
nums[1:3]           # 切片
len(nums)           # 长度
```

### 栈写法

```python
stack = []

stack.append(1)
stack.append(2)

top = stack[-1]     # 查看栈顶
x = stack.pop()     # 弹出栈顶
```

### 排序和反转

```python
nums = [3, 1, 2]

nums.sort()                 # 原地升序: [1, 2, 3]
nums.sort(reverse=True)     # 原地降序

new_nums = sorted(nums)     # 返回新列表
nums.reverse()              # 原地反转
```

### 数据排序

`sort()` 会修改原列表，`sorted()` 不修改原列表，而是返回一个新列表。

```python
nums = [3, 1, 2]

nums.sort()                         # 原地升序: [1, 2, 3]
nums.sort(reverse=True)             # 原地降序: [3, 2, 1]

new_nums = sorted(nums)             # 返回新列表
new_nums_desc = sorted(nums, reverse=True)
```

二维数组默认会先比较第一个元素，第一个元素相同再比较第二个元素。

```python
arr = [[3, 5], [1, 9], [2, 4], [1, 3]]

arr.sort()
# [[1, 3], [1, 9], [2, 4], [3, 5]]
```

如果只想按某一列排序，使用 `key`。

```python
arr = [[3, 5], [1, 9], [2, 4]]

arr.sort(key=lambda x: x[0])         # 按第 1 个数字升序
# [[1, 9], [2, 4], [3, 5]]

arr.sort(key=lambda x: x[1])         # 按第 2 个数字升序
# [[2, 4], [3, 5], [1, 9]]

arr.sort(key=lambda x: x[0], reverse=True)  # 按第 1 个数字降序
```

多条件排序可以让 `key` 返回一个元组。

```python
arr = [[1, 9], [1, 3], [2, 4], [2, 1]]

# 先按第 1 个数字升序；如果相同，再按第 2 个数字升序
arr.sort(key=lambda x: (x[0], x[1]))
# [[1, 3], [1, 9], [2, 1], [2, 4]]

# 先按第 1 个数字升序；如果相同，再按第 2 个数字降序
arr.sort(key=lambda x: (x[0], -x[1]))
# [[1, 9], [1, 3], [2, 4], [2, 1]]
```

### 翻转数组

```python
nums = [1, 2, 3, 4, 5]

nums.reverse()       # 原地翻转
# [5, 4, 3, 2, 1]

new_nums = nums[::-1]  # 返回翻转后的新列表
```

翻转区间 `[0, k]` 时，右端点 `k` 包含在区间内。

```python
nums = [1, 2, 3, 4, 5]
k = 2

# 使用切片翻转
nums[:k + 1] = reversed(nums[:k + 1])
# [3, 2, 1, 4, 5]
```

也可以使用双指针原地翻转，更适合刷题。

```python
nums = [1, 2, 3, 4, 5]
k = 2
left, right = 0, k

while left < right:
    nums[left], nums[right] = nums[right], nums[left]
    left += 1
    right -= 1
# [3, 2, 1, 4, 5]
```

翻转任意区间 `[left, right]`：

```python
while left < right:
    nums[left], nums[right] = nums[right], nums[left]
    left += 1
    right -= 1
```

### 常见刷题场景

- 双指针
- 栈
- 排序
- 动态规划数组
- 前缀和数组

```python
# 前缀和
nums = [1, 2, 3, 4]
prefix = [0]

for x in nums:
    prefix.append(prefix[-1] + x)

# 区间 [left, right] 的和
left, right = 1, 3
range_sum = prefix[right + 1] - prefix[left]
```

## 2. tuple 元组

`tuple` 有序、不可变，常用于表示固定结构的数据，比如坐标、状态、字典的 key。

### 常见操作

```python
point = (2, 3)

x = point[0]
y = point[1]

a, b = point        # 解包
len(point)
```

### 作为 dict / set 的 key

```python
visited = set()
visited.add((0, 0))

dist = {}
dist[(0, 0)] = 0
```

### 常见刷题场景

- BFS / DFS 中记录坐标
- 哈希表中记录状态
- 堆中存储多字段元素

```python
directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]

for dx, dy in directions:
    nx = x + dx
    ny = y + dy
```

## 3. dict 字典

`dict` 是哈希表，存储键值对。查询、插入、删除平均时间复杂度为 `O(1)`。

### 常见操作

```python
mp = {}

mp["a"] = 1
mp["b"] = 2

mp["a"]             # 访问 key 对应的 value
mp.get("c", 0)      # 如果 key 不存在，返回默认值 0

"a" in mp           # 判断 key 是否存在
del mp["a"]         # 删除 key
```

### 遍历

```python
mp = {"a": 1, "b": 2}

for key in mp:
    print(key, mp[key])

for key, value in mp.items():
    print(key, value)

for value in mp.values():
    print(value)
```

### 排序

`dict` 本身按插入顺序保存元素。刷题时如果需要排序，通常对 `mp.items()` 排序，结果是列表。

```python
mp = {"b": 2, "a": 3, "c": 1}

# 按 key 升序
by_key = sorted(mp.items())
# [('a', 3), ('b', 2), ('c', 1)]

# 按 key 降序
by_key_desc = sorted(mp.items(), reverse=True)
# [('c', 1), ('b', 2), ('a', 3)]

# 按 value 升序
by_value = sorted(mp.items(), key=lambda item: item[1])
# [('c', 1), ('b', 2), ('a', 3)]

# 按 value 降序
by_value_desc = sorted(mp.items(), key=lambda item: item[1], reverse=True)
# [('a', 3), ('b', 2), ('c', 1)]

# 如果还想转回 dict，Python 3.7+ 会保留插入顺序
sorted_mp = dict(by_value)
```

### 计数

```python
count = {}

for x in nums:
    count[x] = count.get(x, 0) + 1
```

### 两数之和套路

```python
def two_sum(nums, target):
    seen = {}

    for i, x in enumerate(nums):
        need = target - x
        if need in seen:
            return [seen[need], i]
        seen[x] = i
```

### 常见刷题场景

- 快速查找
- 计数
- 记录下标
- 前缀和 + 哈希表
- 滑动窗口

```python
# 前缀和 + 哈希表: 和为 k 的子数组数量
def subarray_sum(nums, k):
    prefix = 0
    count = {0: 1}
    ans = 0

    for x in nums:
        prefix += x
        ans += count.get(prefix - k, 0)
        count[prefix] = count.get(prefix, 0) + 1

    return ans
```

## 4. set 集合

`set` 是哈希集合，元素不重复，常用于去重和快速判断元素是否存在。

### 常见操作

```python
s = set()

s.add(1)
s.add(2)

1 in s              # 判断是否存在
s.remove(1)         # 删除元素，不存在会报错
s.discard(3)        # 删除元素，不存在不报错
len(s)
```

### 集合运算

```python
a = {1, 2, 3}
b = {3, 4, 5}

a & b       # 交集: {3}
a | b       # 并集: {1, 2, 3, 4, 5}
a - b       # 差集: {1, 2}
```

### 去重

```python
nums = [1, 2, 2, 3]
unique = set(nums)
```

### 常见刷题场景

- 判断是否重复
- 滑动窗口维护窗口内元素
- 图搜索记录 visited
- 最长连续序列

```python
# 最长连续序列
def longest_consecutive(nums):
    num_set = set(nums)
    ans = 0

    for x in num_set:
        if x - 1 not in num_set:
            cur = x
            length = 1

            while cur + 1 in num_set:
                cur += 1
                length += 1

            ans = max(ans, length)

    return ans
```

## 5. str 字符串

`str` 是不可变字符序列。很多字符串题会结合列表、哈希表、双指针来做。

### 常见操作

```python
s = "leetcode"

s[0]            # 'l'
s[-1]           # 'e'
s[1:4]          # 'eet'
len(s)

s.find("code")  # 找不到返回 -1
s.count("e")    # 统计字符出现次数
```

### 分割和拼接

```python
text = "a,b,c"
parts = text.split(",")     # ['a', 'b', 'c']

result = "-".join(parts)    # 'a-b-c'
```

### 字符串不可变

```python
s = "abc"

# 不能直接 s[0] = "x"
chars = list(s)
chars[0] = "x"
s = "".join(chars)
```

### 常见刷题场景

- 回文判断
- 滑动窗口
- 子串匹配
- 字符计数
- 字符串重排

```python
# 判断回文
def is_palindrome(s):
    left, right = 0, len(s) - 1

    while left < right:
        if s[left] != s[right]:
            return False
        left += 1
        right -= 1

    return True
```

## 6. collections.deque 双端队列

`deque` 支持两端快速插入和删除，适合队列、单调队列、BFS、滑动窗口。

```python
from collections import deque

q = deque()

q.append(1)         # 右侧入队
q.appendleft(0)     # 左侧入队

q.pop()             # 右侧出队
q.popleft()         # 左侧出队
```

### BFS 队列

```python
from collections import deque

def bfs(start):
    q = deque([start])
    visited = {start}

    while q:
        node = q.popleft()

        for nxt in graph[node]:
            if nxt not in visited:
                visited.add(nxt)
                q.append(nxt)
```

### 单调队列

```python
from collections import deque

def max_sliding_window(nums, k):
    q = deque()
    ans = []

    for i, x in enumerate(nums):
        while q and nums[q[-1]] <= x:
            q.pop()

        q.append(i)

        if q[0] <= i - k:
            q.popleft()

        if i >= k - 1:
            ans.append(nums[q[0]])

    return ans
```

## 7. collections.Counter 计数器

`Counter` 是专门用来统计元素出现次数的字典子类。

```python
from collections import Counter

nums = [1, 2, 2, 3, 3, 3]
cnt = Counter(nums)

cnt[3]              # 3
cnt[4]              # 0，不存在时默认返回 0
cnt.most_common(2)  # 出现次数最多的 2 个元素
```

### 字符计数

```python
from collections import Counter

s = "anagram"
t = "nagaram"

Counter(s) == Counter(t)
```

### 常见刷题场景

- 字母异位词
- 高频元素
- 滑动窗口字符统计

```python
# 按字母异位词分组
from collections import defaultdict

def group_anagrams(strs):
    groups = defaultdict(list)

    for s in strs:
        key = tuple(sorted(s))
        groups[key].append(s)

    return list(groups.values())
```

## 8. collections.defaultdict 默认字典

`defaultdict` 可以给不存在的 key 自动创建默认值，常用于分组、计数、建图。

```python
from collections import defaultdict

groups = defaultdict(list)
groups["a"].append("apple")
groups["a"].append("ant")

count = defaultdict(int)
count["x"] += 1
```

### 建图

```python
from collections import defaultdict

graph = defaultdict(list)
edges = [[1, 2], [1, 3], [2, 4]]

for a, b in edges:
    graph[a].append(b)
    graph[b].append(a)
```

### 常见刷题场景

- 图的邻接表
- 字符串分组
- 哈希表计数

## 9. heapq 堆

`heapq` 默认是小根堆，堆顶永远是最小元素。常用于优先队列、Top K、合并多个有序链表。

```python
import heapq

heap = []

heapq.heappush(heap, 3)
heapq.heappush(heap, 1)
heapq.heappush(heap, 2)

heap[0]                 # 查看堆顶: 1
smallest = heapq.heappop(heap)
```

### 最大堆写法

Python 标准库没有直接的最大堆，通常存相反数。

```python
import heapq

max_heap = []

heapq.heappush(max_heap, -3)
heapq.heappush(max_heap, -1)
heapq.heappush(max_heap, -2)

largest = -heapq.heappop(max_heap)
```

### Top K 高频元素

```python
from collections import Counter
import heapq

def top_k_frequent(nums, k):
    cnt = Counter(nums)
    heap = []

    for num, freq in cnt.items():
        heapq.heappush(heap, (freq, num))

        if len(heap) > k:
            heapq.heappop(heap)

    return [num for freq, num in heap]
```

### 常见刷题场景

- Top K
- 优先队列
- 合并 K 个有序链表
- 数据流中位数

## 10. queue.Queue / PriorityQueue

`queue` 模块提供线程安全队列，普通算法题中用得比 `deque` 少。刷题时一般优先使用 `deque` 和 `heapq`。

```python
from queue import Queue, PriorityQueue

q = Queue()
q.put(1)
q.put(2)
q.get()

pq = PriorityQueue()
pq.put((2, "task2"))
pq.put((1, "task1"))
pq.get()            # (1, "task1")
```

## 11. 常见选择建议

| 需求 | 推荐结构 |
| --- | --- |
| 快速按下标访问 | `list` |
| 栈 | `list` |
| 队列 / BFS | `deque` |
| 去重 / 判断是否存在 | `set` |
| key-value 映射 | `dict` |
| 计数 | `Counter` / `dict` / `defaultdict(int)` |
| 分组 | `defaultdict(list)` |
| 优先队列 / Top K | `heapq` |
| 固定状态，如坐标 | `tuple` |
| 字符序列 | `str` |

## 12. Hot100 常用导入模板

```python
from collections import defaultdict, Counter, deque
import heapq
```

## 13. 时间复杂度速查

| 数据结构 | 常见操作 | 平均复杂度 |
| --- | --- | --- |
| `list` | 下标访问 | `O(1)` |
| `list` | 末尾添加 / 删除 | `O(1)` |
| `list` | 中间插入 / 删除 | `O(n)` |
| `dict` | 查询 / 插入 / 删除 | `O(1)` |
| `set` | 查询 / 插入 / 删除 | `O(1)` |
| `deque` | 两端插入 / 删除 | `O(1)` |
| `heapq` | 插入 / 删除堆顶 | `O(log n)` |
| `Counter` | 统计 n 个元素 | `O(n)` |
