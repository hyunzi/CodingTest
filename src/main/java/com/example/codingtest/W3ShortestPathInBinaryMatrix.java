package com.example.codingtest;

import java.util.ArrayList;

public class W3ShortestPathInBinaryMatrix {

    public static int shortestPathBinaryMatrix(int[][] grid) {
        //BFS나 DFS로 풀 수 있음
        //8개의 방향을 보며 탐색함
        //왼쪽 위에서 오른쪽 아래의 노드로 이동하는 최단거리 구하기
        //0번 노드부터, 8개의 방향을 탐색함. 1이 있는 쪽으로 이동하여 DFS
        //오른쪽 아래를 만났을 때 이동한 거리를 출력
        //거리가 가장 짧은 걸 출력

        int rowLength = grid.length;
        int colLength = grid[0].length;

        int result = -1;
        if (grid[0][0] != 0 || grid[rowLength-1][colLength-1] != 0) {
            return result;
        }

        boolean[][] visited = new boolean[rowLength][colLength];
        ArrayList<Integer> pathList = new ArrayList<Integer>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                dfs(i,j,grid,visited,pathList);
            }
        }

        return result;
    }

    static int[] dr = {0,1,1,1,0,-1,-1,-1};
    static int[] dc = {1,1,0,-1,-1,-1,0,1};

    public static void dfs(int r, int c, int[][] grid, boolean[][] visited, ArrayList<Integer> pathList) {

        visited[r][c] = true;
        System.out.println(r+","+c+"탐색");
        if ((r == grid.length - 1) && (c == grid[0].length - 1)) {
            System.out.println("경로찾았당");
            return;
        }
        for (int i = 0; i < dr.length; i++) {
            int currLow = r + dr[i];
            int currCol = c + dc[i];
            if (currLow >= 0 && currLow < grid.length && currCol >= 0 && currCol < grid[0].length) {
                System.out.println(currLow+","+currCol+" 가보자고");
                if (grid[currLow][currCol] == 0 && !visited[currLow][currCol]) {
                    dfs(currLow, currCol, grid, visited, pathList);
                }
            }
        }
    }

    public static void main(String[] args) {

        //shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}});
        shortestPathBinaryMatrix(new int[][]{{0,0,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}});

    }
}
