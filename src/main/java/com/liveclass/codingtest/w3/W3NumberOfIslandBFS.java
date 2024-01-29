package com.liveclass.codingtest.w3;

import java.util.*;

public class W3NumberOfIslandBFS {

    public static int numIslands(char[][] grid) {

        //암시적 그래프의 BFS 탐색으로 풀어보자
        //0번 노드부터 시작해서 너비우선탐색을 할거다
        //현재의 노드에서 네 방향으 바라보며 1이 있는지 찾는다
        //1이 있는 곳은 모두 visited 로 체크하고 큐에 넣는다
        //큐에서 다음 값을 pop 하여 그 노드에서 다시 네 방향을 바라본다
        //큐가 Empty 할 때까지 진행하고, result(섬갯수) 카운트를 +1 한다
        //방문하지 않은 다음 노드로 이동하며 1이면 다시 BFS 탐색을 한다

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
        System.out.println(result);
        return result;
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    public static void bfs(int row, int col, char[][] grid, boolean[][] visited) {

        visited[row][col] = true;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{row,col});

        while (!queue.isEmpty()) {
            int[] currNode = queue.poll();

            for (int i = 0; i < dr.length; i++) {
                int currRow = currNode[0] + dr[i];
                currRow = currRow < 0 ? 0 : currRow;
                currRow = currRow >= grid.length-1 ? grid.length-1 : currRow;
                int currCol = currNode[1] + dc[i];
                currCol = currCol < 0 ? 0 : currCol;
                currCol = currCol >= grid[0].length-1 ? grid[0].length-1 : currCol;

                if (grid[currRow][currCol] == '1' && !visited[currRow][currCol]) {
                    visited[currRow][currCol] = true;
                    queue.add(new int[]{currRow, currCol});
                }
            }
        }
    }

    public static boolean isValid(int r, int c, char[][] grid) {
        return grid[r][c] == '1';
    }

    public static void main(String[] args) {
        numIslands(new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}});

        numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}});
    }
}
