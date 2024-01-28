package com.example.codingtest;

import java.io.IOException;
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
            combination(i, n, r, arr, combList);
            arr.remove(Integer.valueOf(i));
        }
    }

    public static void main(String[] args) throws IOException {

        //연구소 배열에서, 1로 돼있는 곳은 어차피 못가는벽
        //2로 되어 있는 곳 중 M의 갯수만큼 활성할 수 있다.
        //활성바이러스의 상하좌우에 있는 0으로 복제가 된다
        //0이 없을 때까지 복제하며 복제한 카운트를 구한다
        //다 퍼뜨릴 수 없는 경우는 어떻게 구할까?

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int mapLength = Integer.parseInt(str.split(" ")[0]);
        int virusNumber = Integer.parseInt(str.split(" ")[1]);
        String[][] graph = new String[mapLength][mapLength];
        int idx = 0;
        while (idx < mapLength) {
            str = sc.nextLine();
            graph[idx++] = str.split(" ");
        }

        int result = Integer.MAX_VALUE;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        //1. 바이러스의 좌표를 모두 구해야하지 않을까..
        ArrayList<int[]> virusList = new ArrayList<>();
        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++) {
                if (graph[i][j].equals("2")) {
                    virusList.add(new int[]{i,j});
                }
            }
        }

        //2. 그다음 좌표의 부분조합을 구함
        //(e.g. 5개의 노드 중 3개를 선택하는 경우의 수)
        ArrayList<List<Integer>> combList = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        combination(0, virusList.size(), virusNumber, arr, combList);

        //3. 부분조합을 돌며, 상하좌우 1회 복사 -> 2회 복사 -> ... 를 BFS로 시도
        for (List<Integer> comb : combList) {
            //virusList[comb[i]]는 이번 turn 에 활성화 할 바이러스 위치
            boolean[][] visited = new boolean[mapLength][mapLength];
            ArrayDeque<int[]> queue = new ArrayDeque<>();

            for (Integer c : comb) {
                //활성화
                int curRow = virusList.get(c)[0];
                int curCol = virusList.get(c)[1];
                visited[curRow][curCol] = true;
                queue.add(new int[]{curRow, curCol, 0});
            }

            //바이러스 활성화 한 좌표에서 BFS
            int depth = 0;
            while (!queue.isEmpty()) {
                int[] currNode = queue.poll();
                int currRow = currNode[0];
                int currCol = currNode[1];
                depth = currNode[2];
                for (int i = 0; i < dr.length; i++) {
                    int nextRow = currRow + dr[i];
                    int nextCol = currCol + dc[i];
                    if (nextRow >= 0 && nextRow < mapLength && nextCol >= 0 && nextCol < mapLength) {
                        //그자리에서 상하좌우 보면서 visited 안했는데 0이라 바이러스 옮길 수 있으면?
                        if (graph[nextRow][nextCol].equals("0") && !visited[nextRow][nextCol]) {
                            visited[nextRow][nextCol] = true;
                            queue.add(new int[]{nextRow, nextCol, depth+1});
                        }
                    }
                }
            }

            //만약에 visited에 1,2 아닌 곳에 방문하지 못했으면? -1 아니면 마지막 depth
            for (int i = 0; i < mapLength; i++) {
                for (int j = 0; j < mapLength; j++) {
                    if (graph[i][j].equals("0") && !visited[i][j]) {
                        depth = -1;
                    }
                }
            }

            if (depth != -1) {
                result = depth < result ? depth : result;
            }
        }

        result = (result == Integer.MAX_VALUE) ? -1 : result;
        System.out.println(result);
    }
}
