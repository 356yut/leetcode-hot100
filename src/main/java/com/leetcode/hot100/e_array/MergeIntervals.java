package com.leetcode.hot100.e_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
示例 1：输入：intervals = [[1,3],[2,6],[8,10],[15,18]]，输出：[[1,6],[8,10],[15,18]]，解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]
示例 2：输入：intervals = [[1,4],[4,5]]，输出：[[1,5]]，解释：区间 [1,4] 和 [4,5] 可被视为重叠区间
示例 3：输入：intervals = [[4,7],[1,4]]，输出：[[1,7]]，解释：区间 [1,4] 和 [4,7] 可被视为重叠区间
提示：1 <= intervals.length <= 104，intervals[i].length == 2，0 <= starti <= endi <= 104*/
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res=merge(intervals);
        System.out.println(Arrays.deepToString(res));
    }
    public static int[][] merge(int[][] intervals) {
        if (intervals==null|| intervals.length == 0 ) return new int[0][0];
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        List<int[]> res = new ArrayList<>();
        for (int[] interval : intervals) {
            int curStart = interval[0];
            int curEnd = interval[1];
            if(res.isEmpty()){
                res.add(new int[]{curStart, curEnd});
            }else {
                int[] lastInterval = res.get(res.size()-1);
                if(curStart <= lastInterval[1]){
                    lastInterval[1] = Math.max(curEnd, lastInterval[1]);
                }else {
                    res.add(new int[]{curStart, curEnd});
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
