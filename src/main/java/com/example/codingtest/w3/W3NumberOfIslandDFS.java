package com.example.codingtest.w3;

import java.util.ArrayDeque;
import java.util.Arrays;

public class W3NumberOfIslandDFS {

    public static int numIslands(char[][] grid) {

        //암시적 그래프의 DFS 탐색으로 풀어보자
        //grid의 0번 노드부터 출발할 것임
        //1이 있는 노드를 만났을 때 4개의 방향을 봐야한다
        //만약, 본 방향이 isValid (즉, 1이라면) 그방향으로 깊이우선탐색을 들어간다
        //끝까지 들어가고 돌아나오면서 방문한 곳은 visited에 체크한다
        //visited에 체크가 다 됐다면? 아직 visited 안한 곳 중에 1이 있는지 확인하고
        //발견했을 때 다시 dfs를 돌린다
        //그래프 마지막까지 탐색한 후, dfs를 수행한 횟수를 return한다!

        int result = 0;
        int rowLength = grid.length;
        int colLength = grid[0].length;
        boolean[][] visited = new boolean[rowLength][colLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    result++;
                    dfs(i, j, grid, visited);
                }
            }
        }

        System.out.println(Arrays.toString(visited));
        System.out.println(result);
        return result;
    }

    //아래 2개로 상하좌우 탐색
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[] txt = {'상', '하', '좌', '우'};

    public static void dfs(int r, int c, char[][] grid, boolean[][] visited) {
        visited[r][c] = true;
        System.out.println(r+","+c+" visited");
        for (int i = 0; i < dr.length; i++) {
            int currRow = (r + dr[i]) < 0 ? 0 : r + dr[i];
            currRow = currRow >= grid.length-1 ? grid.length-1 : currRow;
            int currCol = (c + dc[i]) < 0 ? 0 : c + dc[i];
            currCol = currCol >= grid[0].length-1 ? grid[0].length-1 : currCol;
            System.out.println(txt[i]+":"+currRow+","+currCol);
            if (grid[currRow][currCol] == '1' && !visited[currRow][currCol]) {
                //바라본 방향이 1이고 아직 탐색하지 않았음. dfs 들어가야함
                dfs(currRow, currCol, grid, visited);
            }
        }
    }

    public static void main(String[] args) {

        numIslands(new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}});
        numIslands(new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}});
    }
}
