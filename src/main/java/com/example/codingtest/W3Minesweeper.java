package com.example.codingtest;

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
            //8방향 확인을 BFS로
        }

        return new char[][]{};
    }

    //상,하,좌,우,좌상,좌하,우하,우상
    int[][] rc = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,1},{-1,1}};
    public static char[][] checkXY(int x, int y, char[][] board) {



        return new char[][]{};
    }

    public static void main(String[] args) {

    }
}
