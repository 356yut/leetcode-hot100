package com.leetcode.hot100.f_matrix;

public class MatrixDemo {

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

        // 其他操作
        System.out.println(matrixA.length);
        int[][] matrixD=matrixA.clone();
        printMatrix(matrixD);
    }
}