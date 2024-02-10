package com.liveclass.codingtest.w3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class W3MarbleEscape {

    /*https://www.acmicpc.net/problem/13459*/

    public static void main(String args[]) throws IOException {

        // 빨간구슬이랑 파란구슬의 최단거리를 구해서 비교하면 되는 것 아닐까?
        // 빨간구슬의 최단거리를 구하며, 파란 구슬을 똑같이 움직였을 때, 겹치거나 먼저 빠져나가는지 검사해야할듯?
        // 음... 꼭 최단거리여야 할까 그냥 이동하는 방법 중에 파란구슬이 도달하기 전이기만 하면 되지 않을까?

        /* 1. 데이터 입력 받기 */
        /*
        String str = sc.nextLine();
        Scanner sc = new Scanner(System.in);
        int rowLength = Integer.parseInt(str.split(" ")[0]);
        int colLength = Integer.parseInt(str.split(" ")[1]);
        */

        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        String[] line = reader.readLine().split(" ");
        final int rowLength = Integer.parseInt(line[0]);
        final int colLength = Integer.parseInt(line[1]);
        char[][] graph = new char[rowLength][colLength];

        /* 2. 지도와 구슬의 좌표를 저장하기 */
        int redR = 0, redC = 0, blueR = 0, blueC = 0;
        for (int i = 0; i < rowLength; i++) {
            char[] chars = reader.readLine().toCharArray();
            for (int j = 0; j < colLength; j++) {
                graph[i][j] = chars[j];
                switch (graph[i][j]) {
                    case 'R': redR = i; redC = j; break;
                    case 'B': blueR = i; blueC = j; break;
                }
            }
        }

        /* 2. 빨간구슬 좌표에서 구멍 좌표로 이동하는 거리 구하기 - DFS 탐색
         * 한 번에 기울인 방향의 . 으로는 쭉 이동할 수 있음!
         * 빨간구슬 상하좌우에 . 이 있는 곳을 발견
         * 그 방향으로 빨간구슬 먼저 모두 보낸 후, 파란구슬을 빨간구슬 앞 또는 장애물 앞까지 보냄
         * 파란구슬이 구멍에 도달하거나 10회 이상이 걸리면 0을 리턴한다
        */

        final int[] dr = {-1, 1, 0, 0}; //상, 하, 좌, 우
        final int[] dc = {0, 0, -1, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{redR, redC, blueR, blueC, 0});

        while(!queue.isEmpty()) {
            int[] cur = queue.remove();
            int curRedR = cur[0], curRedC = cur[1], curBlueR = cur[2], curBlueC = cur[3], cnt = cur[4];
            if (cnt == 10) continue;

            for (int i = 0; i < dr.length; i++) {

                int nextRedR = curRedR + dr[i];
                int nextRedC = curRedC + dc[i];
                int redMove = 0;
                boolean redFlag = false;
                while (graph[nextRedR][nextRedC] != '#') {
                    redMove++;
                    if (graph[nextRedR][nextRedC] == 'O') {
                        nextRedR += dr[i];
                        nextRedC += dc[i];
                        redFlag = true;
                        break;
                    }
                    nextRedR += dr[i];
                    nextRedC += dc[i];
                }

                int nextBlueR = curBlueR + dr[i];
                int nextBlueC = curBlueC + dc[i];
                int blueMove = 0;
                boolean blueFlag = false;
                while (graph[nextBlueR][nextBlueC] != '#') {
                    blueMove++;
                    if (graph[nextBlueR][nextBlueC] == 'O') {
                        nextBlueR += dr[i];
                        nextBlueC += dc[i];
                        blueFlag = true;
                        break;
                    }
                    nextBlueR += dr[i];
                    nextBlueC += dc[i];
                }

                if (dr[i] == 0 && nextRedR == nextBlueR && nextRedC == nextBlueC) {
                    if (redMove > blueMove) {
                        nextRedC -= dc[i];
                    } else {
                        nextBlueC -= dc[i];
                    }
                }
                if (dc[i] == 0 && nextRedR == nextBlueR && nextRedC == nextBlueC) {
                    if (redMove > blueMove) {
                        nextRedR -= dr[i];
                    } else {
                        nextBlueR -= dr[i];
                    }
                }

                nextRedR -= dr[i]; nextRedC -= dc[i]; nextBlueR -= dr[i]; nextBlueC -= dc[i];
                if (curRedR == nextRedR && curRedC == nextRedC
                && curBlueR == nextBlueR && curBlueC == nextBlueC) continue;

                if (redFlag && !blueFlag) {
                    System.out.println(1);
                    return;
                }
                if (!blueFlag) {
                    queue.add(new int[]{nextRedR, nextRedC, nextBlueR, nextBlueC, cnt+1});
                }
            }
        }

        System.out.println(0);
    }
}
