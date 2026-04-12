package com.leetcode.hot100.k_binarysearch;

class SearchA2dMatrix {
    int[][] matrix;
    int target,row,col;
    public boolean searchMatrix(int[][] matrix, int target) {
        this.matrix = matrix;
        this.target = target;
        row = matrix.length;
        col = matrix[0].length;
        return binarySearch(0,col-1);
    }

    private boolean binarySearch(int i, int j) {
        if(i>row-1||j<0) return false;
        if(matrix[i][j] == target) return true;
        else if(matrix[i][j] > target) return binarySearch(i,j-1);
        else return binarySearch(i+1,j);
    }

    public static void main(String[] args) {
        SearchA2dMatrix sol = new SearchA2dMatrix();
        int[][] matrix = new int[][]{
                {1},
        };
        System.out.println(sol.searchMatrix(matrix,12));
    }
}
