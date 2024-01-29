package com.liveclass.codingtest.w3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class W3RND3 {
    /* https://www.acmicpc.net/problem/17142 */

    //부분조합 만들기 - DFS
    public static void combination(int start, int n, int r, ArrayList<Integer> arr, ArrayList<List<Integer>> combList) {
        if (arr.size() >= r) {
            combList.add(new ArrayList<>(arr));
            return;
        }

        for (int i = start; i < n; i++) {
            arr.add(i);
            combination(i+1, n, r, arr, combList);
            arr.remove(Integer.valueOf(i));
        }
    }

    static char[][] graph = null;
    static int result = Integer.MAX_VALUE;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        //연구소 배열에서, 1로 돼있는 곳은 어차피 못가는벽
        //2로 되어 있는 곳 중 M의 갯수만큼 활성할 수 있다.
        //활성바이러스의 상하좌우에 있는 0으로 복제가 된다
        //0이 없을 때까지 복제하며 복제한 카운트를 구한다
        //다 퍼뜨릴 수 없는 경우는 어떻게 구할까?

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int mapLength = Integer.parseInt(line[0]);
        int virusNumber = Integer.parseInt(line[1]);

        graph = new char[mapLength][mapLength];
        ArrayList<int[]> virusList = new ArrayList<>();
        for (int r = 0; r < mapLength; r++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int c = 0; c < mapLength; c++) {
                graph[r][c] = st.nextToken().charAt(0);
                //1. 바이러스의 좌표를 모두 구해야하지 않을까..
                if (graph[r][c] == '2') virusList.add(new int[]{r,c});
            }
        }

        //2. 그다음 좌표의 부분조합을 구함 (e.g. 5개의 노드 중 3개를 선택하는 경우의 수)
        ArrayList<List<Integer>> combList = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        combination(0, virusList.size(), virusNumber, arr, combList);

        //3. 부분조합을 돌며, 상하좌우 1회 복사 -> 2회 복사 -> ... 를 BFS로 시도
        for (List<Integer> comb : combList) {
            //virusList[comb[i]]는 이번 turn 에 활성화 할 바이러스 위치
            boolean[][] visited = new boolean[mapLength][mapLength];
            ArrayDeque<int[]> queue = new ArrayDeque<>();

            for (Integer c : comb) { //comb={0,1,2} 의 바이러스를 활성화시켜라
                visited[virusList.get(c)[0]][virusList.get(c)[1]] = true;
                queue.add(new int[]{virusList.get(c)[0], virusList.get(c)[1], 0});
            }
            bfs(comb, visited, queue);
        }

        System.out.println((result == Integer.MAX_VALUE) ? -1 : result);

    }

    private static void bfs(List<Integer> comb, boolean[][] visited, ArrayDeque<int[]> queue) {

        int count = 0;
        int mapLength = graph.length;
        //4. 바이러스 활성화 한 좌표에서 BFS
        while (!queue.isEmpty()) {
            int[] currNode = queue.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            int currDepth = currNode[2];
            for (int i = 0; i < dr.length; i++) {
                int nextRow = currRow + dr[i];
                int nextCol = currCol + dc[i];
                int nextDepth = currDepth + 1;
                if (nextRow >= 0 && nextRow < mapLength && nextCol >= 0 && nextCol < mapLength && !visited[nextRow][nextCol]) {
                    //그 자리에서 상하좌우 보면서 visited 안했는데 0이라 바이러스 옮길 수 있으면?
                    if (graph[nextRow][nextCol] == '0') {
                        visited[nextRow][nextCol] = true;
                        queue.add(new int[]{nextRow, nextCol, nextDepth});
                        count = Math.max(count, nextDepth);
                    } else if (graph[nextRow][nextCol] == '2') {
                        visited[nextRow][nextCol] = true;
                        queue.add(new int[]{nextRow, nextCol, nextDepth});
                    }
                }
            }
        }

        //만약에 visited에 1,2 아닌 곳에 방문하지 못했으면? -1 아니면 마지막 depth
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++) {
                if (graph[i][j] == '0' && !visited[i][j]) {
                    return;
                }
            }
        }

        result = Math.min(result, count);
    }
}
