package com.example.codingtest;

import java.util.ArrayDeque;

public class W3Minesweeper {
    public static char[][] updateBoard(char[][] board, int[] click) {

        //지뢰찾기 게임!
        //board 크기  최대 50*50
        int m = board.length;
        int n = board[0].length;
        //M(안터진폭탄) / X(터진폭탄) / E(안누른사각형) / B(누른사각형) / 1-8(인접지뢰수)
        //click = {3,5} 누르는 위치 배열
        //그 위치를 누른 후의 배열을 반환하라!

        //1. 누른 위치가 M이면 X로 변환만 하면 됨
        //2. 누른 위치가 E이면 주변에 M이 없다면 B로 바꿈 -> 이 경우에는 그 주변까지 또 연쇄적으로 터질 수 있음
        //3. 누른 위치가 E인데 주변에 M이 있다면 숫자로 바꿈
        //4. 더 이상 노출할 값이 없다면 반환!

        int x = click[0];
        int y = click[1];
        if (board[x][y] == 'M') { //1 번 조건
            board[x][y] = 'X';
            return board;
        } else if (board[x][y] == 'X' || board[x][y] == 'B' || Character.isDigit(board[x][y])) { // 발생하지 않을 경우임
            return board;
        } else if (board[x][y] == 'E') {
            //2,3번 조건은 주변을 계속 탐색해야함
            //탐색하는 좌표에 숫자 또는 B를 채워야함
            //탐색하는 좌표 주변에 지뢰가 있으면 그 갯수를, 없으면 B를
            //8방향 확인을 BFS로 하기
            checkXY(x,y,board);
        }

        return board;
    }

    //상,하,좌,우,좌상,좌하,우하,우상
    static int[][] rcList = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,1},{-1,1}};
    public static int checkXY(int x, int y, char[][] board) {

        int bomb = 0;
        int row = board.length;
        int col = board[0].length;

        System.out.println(x+","+y+" 들어옴");

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x,y});

        while (!queue.isEmpty()) {

        }

        for (int[] rc : rcList) {
            int currRow = x + rc[0];
            int currCol = y + rc[1];

            if (currRow >= 0 && currRow < row && currCol >= 0 && currCol < col) {

                if (board[currRow][currCol] == 'M') {
                    //인접한 곳에 있는 폭탄 갯수 +1
                    bomb++;
                } else if (board[currRow][currCol] == 'X') {
                    // 발생하지 않을 경우임
                } else if (board[currRow][currCol] == 'B') {
                    // 안들어가면 됨. 안쪽일 것
                } else if (Character.isDigit(board[currRow][currCol])) {
                    // 안들어가면 됨. 이미 계산한 것
                } else if (board[currRow][currCol] == 'E') {
                    // 여기서 다시 checkXY 들어가면 됨
                    //주변을 봤을 때 M이 없으면 B를 넣으면 되네. 있으면 숫자
                    System.out.println(currRow+","+currCol+" 진행함");
                    // 어라 여기서 들어가버리면 DFS인데..?
                    int cnt = checkXY(currRow, currCol, board);

                    board[currRow][currCol] = cnt == 0 ? 'B' : Character.forDigit(cnt, 10);
                } else {
                    // 발생하지 않을 경우임
                    return 0;
                }
            }

        }

        return bomb;
    }

    public static void print(char[][] board) {
        for (char[] b : board) {
            for (char c : b)
                System.out.print(Character.valueOf(c)+' ');
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] result = updateBoard(new char[][]{
                {'B','1','E','1','B'},
                {'B','1','M','1','B'},
                {'B','1','1','1','B'},
                {'B','B','B','B','B'}
        }, new int[]{1,2});
        //print(result);

        result = updateBoard(new char[][]{
                {'E','E','E','E','E'},
                {'E','E','M','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'}
        }, new int []{3,0});
        //print(result);

    }
}
