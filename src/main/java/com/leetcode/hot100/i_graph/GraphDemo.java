package com.leetcode.hot100.i_graph;

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
