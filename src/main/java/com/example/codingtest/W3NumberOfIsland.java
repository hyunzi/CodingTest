package com.example.codingtest;

import java.util.*;

public class W3NumberOfIsland {

    public static int numIslands(char[][] grid) {

        //암시적 그래프의 BFS, DFS
        //BFS 너비우선탐색을 할 때
        //0번 노드부터 넣고
        //4개 또는 8개의 방향으로 다음 vertex 를 탐색한다
        //만약 다음 vertex가 있으면 visited에 넣어둔다
        int result = 0;

        int rowLength = grid.length;
        int colLength = grid[0].length;

        boolean[][] visited = new boolean[rowLength][colLength];

        for(int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if ((grid[i][j] == '1') && (!visited[i][j])) {
                    bfs(i,j,grid,visited);
                    result++;
                }
            }
        }

        System.out.println(Arrays.toString(visited));
        return result;
    }

    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};

    public static boolean isValid(int r, int c, char[][] grid) {
        return grid[r][c] == '1';
    }
    public static void bfs(int r, int c, char[][] grid, boolean[][] visited) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];

            for (int i = 0; i < dr.length; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];
                if (isValid(nextRow, nextCol, grid)) {
                    if (!visited[nextRow][nextCol]) {
                        queue.offer(new int[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}});
    }
}
