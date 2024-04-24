package com.liveclass.codingtest.w3;

import java.util.*;

public class W3MovingBlockV2 {

    /*
    * [문제 분석]
    * 1. 로봇은 두 칸을 차지 하고 있고, 좌우 또는 상하 또는 회전으로 움직인다
    * 2. 0,0 에서 N,N 까지 도달하는 가장 작은 이동 횟수를 출력하라
    * 3. N은 5~100 범위이고 항상 첫번째 좌표에서 가로방향으로 놓인 상태에서 출발한다.
    *
    * [생각의 흐름]
    * 1. 로봇이 이동할 수 있는 경우의 수
    * 1-1. 가로로 놓인 경우 좌우, 세로로 놓인 경우 상하 --> 각 2가지
    * 1-2. 회전은 축1을 기준으로 2번, 축2를 기준으로 2번 --> 총 4가지
    * 2. 각 좌표에서 6가지 경우의 수를 BFS 탐색
    *
    * [코드 구현]
    * 1. 로봇이 가로로봇인지 세로로봇인지 판단한다
    * 2. 가로로봇이면 가로로봇상수, 세로로봇이면 세로로봇상수 순서대로 탐색한다
    * 3. BFS로 탐색하며 가장 빨리 N,N에 도달하는 경우를 구한다
    *
    * */

    //로봇위치, 이동횟수
    static int[][] bot = new int[][]{{0,0},{0,1},{0}};

    //가로로봇 상수
    final static int[] xRow = {0,0,-1,1,-1,1};   //좌,우,A기준상,A기준하,B기준상,B기준하 --가로로봇
    final static int[] xCol = {-1,1,-1,-1,1,1};  //좌,우,A기준상,A기준하,B기준상,B기준하 --가로로봇
                                            //대각선 체크: 오, 오, 왼, 왼

    //세로로봇 상수
    final static int[] yRow = {-1,1,-1,-1,1,1};  //상,하,A기준좌,A기준우,B기준좌,B기준우 --세로로봇
    final static int[] yCol = {0,0,-1,1,-1,1};   //상,하,A기준좌,A기준우,B기준좌,B기준우 --세로로봇
                                            //대각선 체크: 하, 하, 상, 상

    public static int solution(int[][] board) {
        int answer = 0;
        int m = board.length-1;

        ArrayList<String> visited = new ArrayList<String>();
        visited.add(Arrays.toString(bot[0]) + Arrays.toString(bot[1]));
        visited.add(Arrays.toString(bot[1]) + Arrays.toString(bot[0]));
        //피드백: Set 으로 변경 하면 시간복잡도 낮아져서 효율적일 것!!

        ArrayDeque<int[][]> queue = new ArrayDeque<>();
        queue.add(bot);

        while (!queue.isEmpty()) {
            int[][] currBot = queue.poll();
            if ((currBot[0][0] == m && currBot[0][1] == m) || (currBot[1][0] == m && currBot[1][1] == m)) {
                answer = currBot[2][0];
                break;
            }

            //항상 bot[0] 이 왼쪽이나 위쪽에 있도록 바꿔치기 해준다..
            if (currBot[1][1] < currBot[0][1] || currBot[1][0] < currBot[0][0]) {
                int[][] tmpBot = new int[][]{{currBot[1][0], currBot[1][1]},{currBot[0][0],currBot[0][1]},{currBot[2][0]}};
                currBot = new int[][]{{tmpBot[0][0], tmpBot[0][1]},{tmpBot[1][0],tmpBot[1][1]},{tmpBot[2][0]}};
            }

            char diagonalChk = 'x';

            if (isUpDownBot(currBot)) {
                for (int i = 0; i < 6; i++) {
                    int[][] nextBot = new int[3][2];
                    if (i == 2 || i == 3) { // B가 회전. nextBot[1]의 하 체크
                        diagonalChk = 'd';
                        nextBot[0][0] = currBot[0][0];
                        nextBot[0][1] = currBot[0][1];
                        nextBot[1][0] = currBot[1][0] + yRow[i]; // 아래에 있는 봇의 row
                        nextBot[1][1] = currBot[1][1] + yCol[i]; // 아래에 있는 봇의 col
                    } else if (i == 4 || i == 5) { // A가 회전. nextBot[1]의 상 체크
                        diagonalChk = 'u';
                        nextBot[0][0] = currBot[0][0] + yRow[i]; // 위에 있는 봇의 row
                        nextBot[0][1] = currBot[0][1] + yCol[i]; // 위에 있는 봇의 col
                        nextBot[1][0] = currBot[1][0];
                        nextBot[1][1] = currBot[1][1];
                    } else {
                        nextBot[0][0] = currBot[0][0] + yRow[i];
                        nextBot[0][1] = currBot[0][1] + yCol[i];
                        nextBot[1][0] = currBot[1][0] + yRow[i];
                        nextBot[1][1] = currBot[1][1] + yCol[i];
                    }
                    if (isValidBot(nextBot, board, visited, diagonalChk)) {
                        nextBot[2][0] = currBot[2][0] + 1;
                        queue.add(nextBot);
                        visited.add(Arrays.toString(nextBot[0]) + Arrays.toString(nextBot[1]));
                        visited.add(Arrays.toString(nextBot[1]) + Arrays.toString(nextBot[0]));
                    }
                }
            } else {
                for (int i = 0; i < 6; i++) {
                    int[][] nextBot = new int[3][2];
                    if (i == 2 || i == 3) { // B가 회전. nextBot[1]의 우 체크
                        diagonalChk = 'r';
                        nextBot[0][0] = currBot[0][0];
                        nextBot[0][1] = currBot[0][1];
                        nextBot[1][0] = currBot[1][0] + xRow[i]; // 오른쪽에 있는 봇의 row
                        nextBot[1][1] = currBot[1][1] + xCol[i]; // 오른쪽에 있는 봇의 col
                    } else if (i == 4 || i == 5) { // A가 회전. nextBot[1]의 좌 체크
                        diagonalChk = 'l';
                        nextBot[0][0] = currBot[0][0] + xRow[i]; // 왼쪽에 있는 봇의 row
                        nextBot[0][1] = currBot[0][1] + xCol[i]; // 왼쪽에 있는 봇의 col
                        nextBot[1][0] = currBot[1][0];
                        nextBot[1][1] = currBot[1][1];
                    } else {
                        nextBot[0][0] = currBot[0][0] + xRow[i];
                        nextBot[0][1] = currBot[0][1] + xCol[i];
                        nextBot[1][0] = currBot[1][0] + xRow[i];
                        nextBot[1][1] = currBot[1][1] + xCol[i];
                    }
                    if (isValidBot(nextBot, board, visited, diagonalChk)) {
                        nextBot[2][0] = currBot[2][0] + 1;
                        queue.add(nextBot);
                        visited.add(Arrays.toString(nextBot[0]) + Arrays.toString(nextBot[1]));
                        visited.add(Arrays.toString(nextBot[1]) + Arrays.toString(nextBot[0]));
                    }
                }
            }
        }
        return answer;
    }

    private static boolean isValidBot(int[][] chkBot, int[][] board, ArrayList<String> visited, char diagonalChk) {
        int len = board.length;

        boolean result = false;
        if ((0 <= chkBot[0][0] && chkBot[0][0] < len && 0 <= chkBot[0][1] && chkBot[0][1] < len)
            && (0 <= chkBot[1][0] && chkBot[1][0] < len && 0 <= chkBot[1][1] && chkBot[1][1] < len)
            && board[chkBot[0][0]][chkBot[0][1]] == 0 && board[chkBot[1][0]][chkBot[1][1]] == 0
            && !visited.contains(Arrays.toString(chkBot[0]) + Arrays.toString(chkBot[1]))) {
            result = true;
        }

        if (result) {
            /* 대각선도 검사해야함 */
            switch (diagonalChk) {
                case 'u':
                    if (board[chkBot[1][0] - 1][chkBot[1][1]] != 0) result = false;
                    break;
                case 'd':
                    if (board[chkBot[1][0] + 1][chkBot[1][1]] != 0) result = false;
                    break;
                case 'r':
                    if (board[chkBot[1][0]][chkBot[1][1] + 1] != 0) result = false;
                    break;
                case 'l':
                    if (board[chkBot[1][0]][chkBot[1][1] - 1] != 0) result = false;
                    break;
                default:
                    result = true;
            }
        }

        return result;
    }

    public static boolean isUpDownBot(int[][] chkBot) {
        if (chkBot[0][0] == chkBot[1][0] && chkBot[0][1] != chkBot[1][1]) { //가로 Bot
            return false;
        } else if (chkBot[0][0] != chkBot[1][0] && chkBot[0][1] == chkBot[1][1]) { //세로 Bot
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int result1 = solution(new int[][]{
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1},
            {1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0}
        });
        System.out.println(result1);
        /*int result2 = solution(new int[][]{
                {0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}
        });
        System.out.println(result2);*/
        int result3 = solution(new int[][]
                {{0, 0, 1, 0, 0, 0, 0, 0, 0, 0}
                ,{0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
                ,{1, 0, 0, 0, 1, 0, 0, 0, 0, 0}
                ,{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                ,{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                ,{0, 0, 1, 1, 1, 0, 0, 0, 0, 0}
                ,{0, 0, 0, 0, 1, 0, 0, 0, 0, 0}
                ,{0, 1, 0, 0, 0, 0, 0, 0, 0, 0}
                ,{0, 0, 0, 0, 0, 0, 1, 1, 0, 1}
                ,{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}
            );
        System.out.println("result3 = " + result3);
    }
}
