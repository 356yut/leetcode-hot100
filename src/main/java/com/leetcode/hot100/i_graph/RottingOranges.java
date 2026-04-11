package com.leetcode.hot100.i_graph;


import java.util.LinkedList;
import java.util.Queue;

class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int res=0;
        int freshCount=0;
        int m=grid.length;
        int n=grid[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1) freshCount++;
                if(grid[i][j]==2) queue.add(new int[]{i,j});
            }
        }
        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        while(!queue.isEmpty()&&freshCount>0){
            int queueSize = queue.size();
            for(int i=0;i<queueSize;i++){
                int[] cur = queue.poll();
                int x=cur[0];
                int y=cur[1];
                for(int[] direction : directions){
                    int newX=x+direction[0];
                    int newY=y+direction[1];
                    if(newX>=0&&newX<m&&newY>=0&&newY<n&&grid[newX][newY]==1){
                        grid[newX][newY]=0;
                        queue.add(new int[]{newX,newY});
                        freshCount--;
                    }
                }
            }
            res++;
        }
        return freshCount==0? res:-1;
    }
}
