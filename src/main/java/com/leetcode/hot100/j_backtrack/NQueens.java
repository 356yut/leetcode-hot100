package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    List<List<String>> res=new ArrayList<>();
    boolean[] usedCol;
    boolean[] usedLeftDiag;
    boolean[] usedRightDiag;
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.n=n;
        usedCol =new boolean[n];
        usedLeftDiag =new boolean[n*2];
        usedRightDiag =new boolean[n*2];
        for (int i = 0; i < n; i++) {
            usedCol[i]=false;
            usedLeftDiag[i]=false;
            usedRightDiag[i]=false;
            usedLeftDiag[i*2+1]=false;
            usedRightDiag[i*2+1]=false;
        }
        char[][] board=new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j]='.';
            }
        }
        backtrack(board,0);
        return res;
    }

    private void backtrack(char[][] board, int i) {
        if(i==n){
            List<String> list=new ArrayList<>();
            for(int j=0;j<n;j++){
                list.add(String.valueOf(board[j]));
            }
            res.add(list);
            return;
        }
        for(int j=0;j<n;j++){
            if(usedLeftDiag[i+j]|| usedRightDiag[j-i+n]|| usedCol[j]){
                continue;
            }
            board[i][j]='Q';
            usedCol[j]=true;
            usedLeftDiag[i+j]=true;
            usedRightDiag[j-i+n]=true;
            backtrack(board,i+1);
            usedCol[j]=false;
            usedLeftDiag[i+j]=false;
            usedRightDiag[j-i+n]=false;
            board[i][j]='.';
        }
    }

    public static void main(String[] args) {
        NQueens nq=new NQueens();
        List<List<String>> solveNQueens = nq.solveNQueens(4);
        for (List<String> list : solveNQueens) {
            System.out.println(list);
            System.out.println("---------------");
        }
    }
}
