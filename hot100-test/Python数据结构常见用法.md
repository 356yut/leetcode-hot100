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

## 2. 矩阵（二维 list）

Python 中通常使用嵌套 `list` 表示矩阵。`matrix[i][j]` 表示第 `i` 行、第 `j` 列的元素，下标都从 `0` 开始。

### 创建矩阵

```python
# 直接创建
matrix = [
    [1, 2, 3],
    [4, 5, 6],
]

m = len(matrix)                # 行数
n = len(matrix[0]) if m else 0 # 列数，注意空矩阵

# 创建 m 行 n 列的全 0 矩阵
matrix = [[0] * n for _ in range(m)]
```

不要使用 `[[0] * n] * m` 创建矩阵，因为每一行引用的是同一个列表，修改一行会影响其他行。

```python
matrix = [[0] * 3] * 2
matrix[0][0] = 1
# [[1, 0, 0], [1, 0, 0]]

matrix = [[0] * 3 for _ in range(2)]
matrix[0][0] = 1
# [[1, 0, 0], [0, 0, 0]]
```

### 访问和修改元素

```python
matrix = [[1, 2, 3], [4, 5, 6]]

matrix[0][1]       # 第 0 行、第 1 列: 2
matrix[-1][-1]     # 最后一行、最后一列: 6

matrix[0][1] = 9   # 修改元素
row = matrix[0]    # 获取第 0 行
```

### 遍历矩阵

需要修改元素或使用坐标时，遍历下标；只读取元素时，可以直接遍历每一行。

```python
# 按行遍历
for row in matrix:
    for value in row:
        print(value)

# 带下标遍历
for i in range(len(matrix)):
    for j in range(len(matrix[0])):
        matrix[i][j] += 1

# 使用 enumerate 同时获取下标和元素
for i, row in enumerate(matrix):
    for j, value in enumerate(row):
        print(i, j, value)
```

### 行、列和边界

```python
matrix = [[1, 2, 3], [4, 5, 6]]

row = matrix[0]                          # 第 0 行: [1, 2, 3]
column = [matrix[i][1] for i in range(2)] # 第 1 列: [2, 5]

# 判断坐标是否在矩阵内
def in_bounds(i, j, m, n):
    return 0 <= i < m and 0 <= j < n
```

矩阵可能为空，或者行数不为零但列数为零，因此使用 `matrix[0]` 前应先判断 `matrix` 是否为空。以下写法适用于规则矩阵：

```python
m = len(matrix)
n = len(matrix[0]) if m else 0
```

### 方向数组和相邻位置

```python
# 上、右、下、左，常用于网格 BFS / DFS 和螺旋遍历
directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]

for di, dj in directions:
    ni = i + di
    nj = j + dj
    if 0 <= ni < m and 0 <= nj < n:
        # matrix[ni][nj] 是合法相邻位置
        pass
```

如果需要包含对角线，可以使用 8 个方向：

```python
directions = [
    (-1, -1), (-1, 0), (-1, 1),
    (0, -1),           (0, 1),
    (1, -1),  (1, 0),  (1, 1),
]
```

### 转置矩阵

转置会交换行和列。`zip(*matrix)` 可以按列取出元素，但返回的是元组，通常用 `map(list, ...)` 转为列表。

```python
matrix = [[1, 2, 3], [4, 5, 6]]

transposed = [list(row) for row in zip(*matrix)]
# [[1, 4], [2, 5], [3, 6]]
```

方阵可以原地转置，只交换主对角线一侧的元素：

```python
n = len(matrix)

for i in range(n):
    for j in range(i + 1, n):
        matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]
```

### 顺时针旋转 90 度

方阵顺时针旋转 90 度等价于“先转置，再将每一行反转”，可以原地完成。

```python
def rotate(matrix):
    n = len(matrix)

    # 转置
    for i in range(n):
        for j in range(i + 1, n):
            matrix[i][j], matrix[j][i] = matrix[j][i], matrix[i][j]

    # 每行反转
    for row in matrix:
        row.reverse()
```

### 螺旋遍历

用四个边界表示当前未遍历区域：上边界 `top`、下边界 `bottom`、左边界 `left`、右边界 `right`。每遍历完一条边，都要收缩对应边界；遍历下边和左边前要再次判断边界，避免重复访问。

```python
def spiral_order(matrix):
    if not matrix or not matrix[0]:
        return []

    top, bottom = 0, len(matrix) - 1
    left, right = 0, len(matrix[0]) - 1
    ans = []

    while top <= bottom and left <= right:
        for j in range(left, right + 1):
            ans.append(matrix[top][j])
        top += 1

        for i in range(top, bottom + 1):
            ans.append(matrix[i][right])
        right -= 1

        if top <= bottom:
            for j in range(right, left - 1, -1):
                ans.append(matrix[bottom][j])
            bottom -= 1

        if left <= right:
            for i in range(bottom, top - 1, -1):
                ans.append(matrix[i][left])
            left += 1

    return ans
```

### 矩阵置零

如果某个元素为 `0`，将其所在行和列全部置为 `0`。需要原地修改时，可以用第一行和第一列记录标记，额外空间为 `O(1)`。

```python
def set_zeroes(matrix):
    m, n = len(matrix), len(matrix[0])
    first_row_zero = any(matrix[0][j] == 0 for j in range(n))
    first_col_zero = any(matrix[i][0] == 0 for i in range(m))

    # 用首行、首列记录其他行列是否需要置零
    for i in range(1, m):
        for j in range(1, n):
            if matrix[i][j] == 0:
                matrix[i][0] = 0
                matrix[0][j] = 0

    for i in range(1, m):
        for j in range(1, n):
            if matrix[i][0] == 0 or matrix[0][j] == 0:
                matrix[i][j] = 0

    if first_row_zero:
        for j in range(n):
            matrix[0][j] = 0

    if first_col_zero:
        for i in range(m):
            matrix[i][0] = 0
```

### 常见刷题场景

- 网格 BFS / DFS：用 `(i, j)` 表示坐标，用方向数组访问邻居
- 矩阵旋转：转置、反转行或列
- 螺旋矩阵：维护四个边界
- 搜索二维有序矩阵：根据行列单调性移动指针
- 原地标记：利用首行、首列或特殊值记录状态

矩阵遍历通常需要 `O(m * n)` 时间；转置、旋转和置零若原地操作，额外空间可以做到 `O(1)`（不计算输出结果或递归栈）。

## 3. tuple 元组

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

## 4. dict 字典

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

## 5. set 集合

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

## 6. str 字符串

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

## 7. collections.deque 双端队列

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

## 8. collections.Counter 计数器

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

## 9. collections.defaultdict 默认字典

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

## 10. heapq 堆

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

## 11. queue.Queue / PriorityQueue

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

## 12. 常见选择建议

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

## 13. Hot100 常用导入模板

```python
from collections import defaultdict, Counter, deque
import heapq
```

## 14. 时间复杂度速查

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
