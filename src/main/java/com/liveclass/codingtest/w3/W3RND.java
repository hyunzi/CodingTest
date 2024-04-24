package com.liveclass.codingtest.w3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class W3RND {

    /*
     * [문제 분석]
     * 1. 연구소는 직사각형 (크기 3~8)
     * 2. 0:빈 칸(최소 3개) / 1:벽 / 2:바이러스(갯수 2~10)
     * 3. 바이러스는 상하좌우 4개 방향으로 퍼질 수 있음
     * 4. 벽을 3개 세워서 바이러스가 퍼진 후 빈 칸이 가장 많은 경우를 구해야함
     * 5. 빈 칸의 갯수를 리턴한다
     *
     * [생각의 흐름]
     * 1. 빈 칸에 3개의 벽을 세우는 모든 조합을 몇 개일까?
     *   1-1.8*8=64, 64-2=62, (62!/59!3!)
     *       62*61*60
     *       62!/3!(62-3)!
     *       226920/37820
     * 순열의 경우의 수 226920을 3! 로 나누면 조합의 경우의 수 나옴
     * 2. 최대 37820 경우의 수에 대해, 바이러스가 퍼지도록 DFS
     * 3. DFS 종료된 후 0이 몇 개 인지 구하여 Math.max
     *
     * [코드 구현]
     * 1. 데이터 입력 받으며 빈 칸의 좌표 저장
     * 2. 빈 칸의 좌표로 combination
     * 3. 각 comb 탐색
     * 3-1. comb[i] 의 3개 좌표를 1로 치환
     * 3-2. 2(바이러스) 좌표에서 DFS
     * 3-3. 0의 갯수 확인
     * 4. 0이 최대인 갯수 리턴
     * */
    static char[][] graph;
    static List<int[]> emptyList, virusList;

    public static void main(String[] args) throws IOException {

        /* 1. 데이터 입력 받기 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int row = Integer.parseInt(line[0]);
        int col = Integer.parseInt(line[1]);
        graph = new char[row][col];
        emptyList = new ArrayList<>();
        virusList = new ArrayList<>();

        for (int r = 0; r < row; r++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int c = 0; c < col; c++) {
                graph[r][c] = st.nextToken().charAt(0);
                if (graph[r][c] == '0') emptyList.add(new int[]{r, c});
                if (graph[r][c] == '2') virusList.add(new int[]{r, c});
            }
        }

        List<List<int[]>> combinations = new ArrayList<>();
        List<int[]> arr = new ArrayList<>();
        combination(0, emptyList.size(), 3, arr, combinations);

        int result = 0;
        for (List<int[]> combination : combinations) {
            char[][] map = new char[row][col];
            for (int i = 0; i < row; i++) {
                System.arraycopy(graph[i], 0, map[i], 0, col);
            }

            for (int[] comb : combination) {
                //comb[0], comb[1] 좌표를 1로 치환
                map[comb[0]][comb[1]] = '1';
            }

            boolean[][] visited = new boolean[row][col];
            for (int[] virus : virusList) {
                bfs(virus, map, visited);
            }
            result = Math.max(result, getCountEmptyCell(map));
            /* 필요없!
            for (int[] comb : combination) {
                //comb[0], comb[1] 좌표를 0로 치환
                map[comb[0]][comb[1]] = '0';
            }*/
        }
        System.out.println(result);
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void bfs(int[] startNode, char[][] map, boolean[][] visited) {

        int r = startNode[0];
        int c = startNode[1];
        visited[r][c] = true;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{r, c});

        while (!queue.isEmpty()) {
            int[] currNode = queue.poll();

            for (int i = 0; i < dr.length; i++) {
                int currRow = currNode[0] + dr[i];
                currRow = Math.max(currRow, 0);
                currRow = Math.min(currRow, map.length - 1);
                int currCol = currNode[1] + dc[i];
                currCol = Math.max(currCol, 0);
                currCol = Math.min(currCol, map[0].length - 1);

                if (map[currRow][currCol] == '0' && !visited[currRow][currCol]) {
                    visited[currRow][currCol] = true;
                    map[currRow][currCol] = '2';
                    queue.add(new int[]{currRow, currCol});
                }
            }
        }

    }

    public static int getCountEmptyCell(char[][] map) {
        int result = 0;
        for (char[] row : map) {
            for (char c : row) {
                if (c == '0') result++;
            }
        }
        return result;
    }

    public static void combination(int start, int n, int r, List<int[]> arr, List<List<int[]>> combList) {
        if (arr.size() >= r) {
            combList.add(new ArrayList<>(arr));
            return;
        }

        for (int i = start; i < n; i++) {
            arr.add(emptyList.get(i));
            combination(i + 1, n, r, arr, combList);
            arr.remove(emptyList.get(i));
        }
    }

/* 데이터 예시
4 6
0 0 0 0 0 0
1 0 0 0 0 2
1 1 1 0 0 2
0 0 0 0 0 2

*/

}
