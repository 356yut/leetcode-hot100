package com.leetcode.hot100.j_backtrack;

import java.util.ArrayList;
import java.util.List;

class WordSearch {
    private int[][] dirs={{-1,0},{1,0},{0,1},{0,-1}};
    private int row,col;
    private char[] words;
    public boolean exist(char[][] board, String word) {
        words = word.toCharArray();
        row = board.length;
        col = board[0].length;
        if(words.length > row*col) return false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(backtrack(board,0,i,j)) return true;
            }
        }
        return false;
    }
    public boolean backtrack(char[][] board, int index,int i,int j) {
        if(index == words.length) return true;
        if(i<0 || i>=row || j<0 || j>=col||board[i][j] != words[index]) return false;
        char temp = board[i][j];
        board[i][j] = '#';
        for(int[] dir : dirs) {
            if(backtrack(board,index+1,i+dir[0],j+dir[1])) return true;
        }
        board[i][j] = temp;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'},
                {'A','D','F','E'}
        };
        WordSearch obj = new WordSearch();
        System.out.println(obj.exist(board,"ABCCED"));
    }
}
