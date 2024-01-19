package com.example.codingtest;

import java.util.*;

public class W3MovingBlock {

    public static int solution(int[][] board) {
        int answer = 0;


        //로봇이 이동할 수 있는 경우의 수
        //가로로 놓인 경우 좌우, 세로로 놓인 경우 상하 --> 각 2가지
        //축1을 기준으로 3번, 축2를 기준으로 3번 --> 총 6가지

        //[0,0] [0,1]에서 출발하여 각 좌표당 8가지 경우의 수를 탐색
        //BFS 탐색. 가로인지 세로인지 판단 후 진행
        //Depth 도 같이 저장. N,N에 도달했을 때 Depth가 가장 작은 수를 출력

        int boardLen = board.length;

        int[] xRow = {0,0,-1,1,0,-1,1,0};   //좌,우,A상,A하,A좌,B상,B하,B우
        int[] xCol = {-1,1,0,0,-1,0,0,1};   //좌,우,A상,A하,A좌,B상,B하,B우
        int[] yRow = {-1,1,0,-1,0,0,1,0};   //상,하,A좌,A상,A우,B좌,B하,B우
        int[] yCol = {0,0,-1,0,1,-1,0,1};   //상,하,A좌,A상,A우,B좌,B하,B우


        int[][] bot = new int[][]{{0,0},{0,1}};
        for (int i = 0; i < boardLen; i++) {
            for (int j = 0; j < boardLen; j++) {
                LinkedHashSet<int[][]> queue = new LinkedHashSet<>();
                queue.add(bot);
                if (isUpDownBot(bot)) { //세로모양 로봇
                    //yRow, yCol을 써서 8가지 돌린다
                    for (int k = 0; k < xRow.length; k++) {
                        bot[0][0] + xRow[k] // 위에 있는 봇의 row
                        bot[0][1] + xCol[k] // 위에 있는 봇의 col
                        bot[1][0] + xRow[k] // 아래에 있는 봇의 row
                        bot[1][1] + xCol[k] // 아래에 있는 봇의 col
                    }

                } else { //가로모양 로봇
                    //xRow, xCol을 써서 8가지 돌린다
                    for (int k = 0; k < xRow.length; k++) {
                        bot[0][0] + xRow[k] // 왼쪽에 있는 봇의 row
                        bot[0][1] + xCol[k] // 왼쪽에 있는 봇의 col
                        bot[1][0] + xRow[k] // 오른쪽에 있는 봇의 row
                        bot[1][1] + xCol[k] // 오른쪽에 있는 봇의 col
                    }
                }
            }
        }
        return answer;
    }
    public static boolean isUpDownBot(int[][] bot) {
        if (bot[0][0] == bot[1][0] && bot[0][1] != bot[1][1]) {
            //가로 Bot
            return false;
        } else if (bot[0][0] != bot[1][0] && bot[0][1] == bot[1][1]) {
            //세로 Bot
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
