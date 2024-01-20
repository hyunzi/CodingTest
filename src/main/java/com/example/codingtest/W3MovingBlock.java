package com.example.codingtest;

import java.util.*;
import java.util.stream.Collectors;

public class W3MovingBlock {

    public static int solution(int[][] board) {
        int answer = 0;

        //로봇이 이동할 수 있는 경우의 수
        //가로로 놓인 경우 좌우, 세로로 놓인 경우 상하 --> 각 2가지
        //축1을 기준으로 2번, 축2를 기준으로 2번 --> 총 4가지

        //[0,0] [0,1]에서 출발하여 각 좌표당 6가지 경우의 수를 탐색
        //BFS 탐색. 가로인지 세로인지 판단 후 진행
        //Depth 도 같이 저장. N,N에 도달했을 때 Depth가 가장 작은 수를 출력


        int[][] bot = new int[][]{{0,0},{0,1},{0}};

        ArrayList<String> visited = new ArrayList<String>();
        visited.add(Arrays.toString(bot[0]) + Arrays.toString(bot[1]));
        visited.add(Arrays.toString(bot[1]) + Arrays.toString(bot[0]));
        ArrayDeque<int[][]> queue = new ArrayDeque<>();
        queue.add(bot);

        while (!queue.isEmpty()) {
            System.out.print("queue: ");
            for (int[][] q : queue)
                System.out.print(Arrays.toString(q[0])+","+Arrays.toString(q[1])+","+Arrays.toString(q[2])+" -> ");
            System.out.println();

            int[][] currBot = queue.poll();
            int[][] nextBot = new int[currBot.length][currBot[0].length];

            int[] xRow = {0,0,-1,1,-1,1};   //좌,우,A기준상,A기준하,B기준상,B기준하 --가로로봇
            int[] xCol = {-1,1,-1,-1,1,1};  //좌,우,A기준상,A기준하,B기준상,B기준하 --가로로봇
            int[] yRow = {-1,1,-1,-1,1,1};  //상,하,A기준좌,A기준우,B기준좌,B기준우 --세로로봇
            int[] yCol = {0,0,-1,1,-1,1};   //상,하,A기준좌,A기준우,B기준좌,B기준우 --세로로봇


            for (int k = 0; k < xRow.length; k++) {
                if (isUpDownBot(currBot)) {
                    if (k == 2 || k == 3) { //B가 움직임
                        nextBot[0][0] = currBot[0][1] < currBot[1][1] ? currBot[0][0] : currBot[1][0];
                        nextBot[0][1] = currBot[0][1] < currBot[1][1] ? currBot[0][1] : currBot[1][1];
                        nextBot[1][0] = currBot[0][1] < currBot[1][1] ? currBot[1][0] + yRow[k] : currBot[0][0] + yRow[k]; // 아래에 있는 봇의 row
                        nextBot[1][1] = currBot[0][1] < currBot[1][1] ? currBot[1][1] + yCol[k] : currBot[0][1] + yCol[k]; // 아래에 있는 봇의 col
                    } else if (k == 4 || k == 5) { //A가 움직임
                        nextBot[0][0] = currBot[0][1] < currBot[1][1] ? currBot[0][0] + yRow[k] : currBot[1][0] + yRow[k]; // 위에 있는 봇의 row
                        nextBot[0][1] = currBot[0][1] < currBot[1][1] ? currBot[0][1] + yCol[k] : currBot[1][1] + yCol[k]; // 위에 있는 봇의 col
                        nextBot[1][0] = currBot[0][1] < currBot[1][1] ? currBot[1][0] : currBot[0][0];
                        nextBot[1][1] = currBot[0][1] < currBot[1][1] ? currBot[1][1] : currBot[0][1];
                    } else {
                        nextBot[0][0] = currBot[0][0] + yRow[k]; // 위에 있는 봇의 row
                        nextBot[0][1] = currBot[0][1] + yCol[k]; // 위에 있는 봇의 col
                        nextBot[1][0] = currBot[1][0] + yRow[k]; // 아래에 있는 봇의 row
                        nextBot[1][1] = currBot[1][1] + yCol[k]; // 아래에 있는 봇의 col
                    }
                } else {
                    if (k == 2 || k == 3) { //B가 움직임
                        nextBot[0][0] = currBot[0][1] < currBot[1][1] ? currBot[0][0] : currBot[1][0];
                        nextBot[0][1] = currBot[0][1] < currBot[1][1] ? currBot[0][1] : currBot[1][1];
                        nextBot[1][0] = currBot[0][1] < currBot[1][1] ? currBot[1][0] + xRow[k] : currBot[0][0] + xRow[k] ; // 오른쪽에 있는 봇의 row
                        nextBot[1][1] = currBot[0][1] < currBot[1][1] ? currBot[1][1] + xCol[k] : currBot[0][1] + xCol[k] ; // 오른쪽에 있는 봇의 col
                    } else if (k == 4 || k == 5) { //A가 움직임
                        nextBot[0][0] = currBot[0][1] < currBot[1][1] ? currBot[0][0] + xRow[k] : currBot[1][0] + xRow[k]; // 왼쪽에 있는 봇의 row
                        nextBot[0][1] = currBot[0][1] < currBot[1][1] ? currBot[0][1] + xCol[k] : currBot[1][1] + xCol[k]; // 왼쪽에 있는 봇의 col
                        nextBot[1][0] = currBot[0][1] < currBot[1][1] ? currBot[1][0] : currBot[0][0];
                        nextBot[1][1] = currBot[0][1] < currBot[1][1] ? currBot[1][1] : currBot[0][1];
                    } else {
                        nextBot[0][0] = currBot[0][0] + xRow[k]; // 왼쪽에 있는 봇의 row
                        nextBot[0][1] = currBot[0][1] + xCol[k]; // 왼쪽에 있는 봇의 col
                        nextBot[1][0] = currBot[1][0] + xRow[k]; // 오른쪽에 있는 봇의 row
                        nextBot[1][1] = currBot[1][1] + xCol[k]; // 오른쪽에 있는 봇의 col
                    }
                }
                if (isValid(nextBot, board, visited)) {
                    int[][] tmpBot = new int[nextBot.length][nextBot[0].length];
                    for (int t = 0; t < nextBot.length; t++) {
                        System.arraycopy(nextBot[t], 0, tmpBot[t], 0, tmpBot[t].length);
                    }
                    tmpBot[2][0] = currBot[2][0]+1;
                    queue.add(tmpBot);
                    visited.add(Arrays.toString(nextBot[0]) + Arrays.toString(nextBot[1]));
                    visited.add(Arrays.toString(nextBot[1]) + Arrays.toString(nextBot[0]));
                }
            }
        }

        return answer;
    }
    public static boolean isUpDownBot(int[][] bot) {
        if (bot[0][0] == bot[1][0] && bot[0][1] != bot[1][1]) { //가로 Bot
            return false;
        } else if (bot[0][0] != bot[1][0] && bot[0][1] == bot[1][1]) { //세로 Bot
            return true;
        }
        return false;
    }

    public static boolean isValid(int[][] bot, int[][] board, ArrayList<String> visited) {
        int len = board.length;
        if ((bot[0][0] >=0 && bot[0][0] < len && bot[0][1] >= 0 && bot[0][1] < len)
            && (bot[1][0] >=0 && bot[1][0] < len && bot[1][1] >= 0 && bot[1][1] < len)
            && board[bot[0][0]][bot[0][1]] == 0 && board[bot[1][0]][bot[1][1]] == 0
            && !visited.contains(Arrays.toString(bot[0]) + Arrays.toString(bot[1]))) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {

        solution(new int[][]{
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1},
            {1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0}
        });

    }
}