package com.example.codingtest;

import java.util.ArrayDeque;

public class W3ShortestPathInBinaryMatrix {

    public static int shortestPathBinaryMatrix(int[][] grid) {
        //왼쪽 위에서 오른쪽 아래의 노드로 이동하는 최단거리 구하기
        //DFS 로도 풀 수 있으나 BFS로 풀어야 효율적임
        //8개의 방향을 보며 탐색함
        //0번 노드부터, 8개의 방향을 탐색함. 1이 있는 곳을 모두 탐색
        //오른쪽 아래를 처음 만났을 때 이동한 거리를 리턴하면 됨

        int rowLength = grid.length;
        int colLength = grid[0].length;

        int result = -1;
        if (grid[0][0] != 0 || grid[rowLength-1][colLength-1] != 0) {
            return result;
        }

        boolean[][] visited = new boolean[rowLength][colLength];
        int[] dr = {0,1,1,1,0,-1,-1,-1};
        int[] dc = {1,1,0,-1,-1,-1,0,1};

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0,1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            int curRow = curPos[0];
            int curCol = curPos[1];
            int curLength = curPos[2];

            if (curRow == rowLength - 1 && curCol == colLength - 1) {
                result = curLength;
                return result;
            }
            for (int i = 0; i < 8; i++) {
                int nextRow = curRow + dr[i];
                int nextCol = curCol + dc[i];
                if (nextRow >= 0 && nextRow <= rowLength-1 && nextCol >= 0 && nextCol <= colLength - 1) {
                    System.out.println(nextRow+","+nextCol+" 탐색");
                    if (grid[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]) {
                        queue.offer(new int[]{nextRow, nextCol, curLength+1});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}});
        int result = shortestPathBinaryMatrix(new int[][]{{0,0,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}});
        System.out.println(result);
    }
}
