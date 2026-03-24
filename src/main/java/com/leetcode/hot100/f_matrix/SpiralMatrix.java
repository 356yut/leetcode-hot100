package com.leetcode.hot100.f_matrix;

import java.util.ArrayList;
import java.util.List;

/*给你一个 m 行 n 列的矩阵 matrix，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
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
-100 <= matrix[i][j] <= 100*/

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> res = spiralOrder(matrix);
        System.out.println(res);
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        int m = matrix.length;
        int n = matrix[0].length;
        if (matrix==null||m == 0 || n == 0) {
            return res;
        }
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        while (true) {
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            top++;
            if(top>bottom) break;
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if(right<left) break;
            for (int j = right; j >= left; j--) {
                res.add(matrix[bottom][j]);
            }
            bottom--;
            if(top>bottom) break;
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            if(left>right) break;
        }
        return res;
    }

}
