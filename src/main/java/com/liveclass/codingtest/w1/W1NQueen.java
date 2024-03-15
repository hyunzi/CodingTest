package com.liveclass.codingtest.w1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class W1NQueen {

    /* https://leetcode.com/problems/n-queens/description/ */
    public static List<List<String>> solveNQueens(int n) {

        /*
        * 문제 해석
        * 1. 주어진 n * n 의 배열에 n개의 퀸을 배치할 수 있는 모든 경우의 수
        * 2. 퀸은 가로/세로/대각선으로 모두 이동 가능
        *
        * 생각의 흐름
        * 1. 완전 탐색...각 i,j 위치에 퀸을 놓는 경우에 대해 모두 탐색해야 할까?
        * 2. 근데.. i,j 에 놓고 두 번째 퀸을 어디 놓느냐도 완전 탐색 해야 하나?
        * 3. 재귀를 이용해야할 듯
        *
        * 코드 구현
        * 1. n*n의 배열 생성
        * 2. 이중 for 문
        * 3. i,j에 첫번째 퀸 배치하고, 상하좌우대각선을 .으로 변경
        * */

        List<List<String>> answer = new ArrayList<>();
        char[][] chess = new char[n][n];
        for (char[] row : chess) Arrays.fill(row, '.');

        Queens(0, chess, answer, n);

        return answer;
    }

    private static void Queens(int r, char[][] chess, List<List<String>> answer, int n) {
        if (r == n) {
            List<String> result = new ArrayList<>();
            for (char[] row : chess) {
                result.add(new String(row));
            }
            answer.add(result);
            return;
        }

        for (int c = 0; c < n; c++) {
            if (isValid(chess, r, c, n)) {
                chess[r][c] = 'Q';
                Queens(r+1, chess, answer, n);
                chess[r][c] = '.';
            }
        }
    }

    private static boolean isValid(char[][] chess, int r, int c, int n) {

        for (int i = 0; i < r; i++) {
            if (chess[i][c] == 'Q') return false;
        }

        //위 왼쪽 대각선
        for (int i = r-1, j = c-1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') return false;
        }

        //위 오른쪽 대각선
        for (int i = r-1, j = c+1; i >= 0 && j < n; i--, j++) {
            if (chess[i][j] == 'Q') return false;
        }

        return true;

    }

    private static void changeToX(int n, int x, int y, char[][] chessBoard) {
        //상
        for(int delIndex = y-1; delIndex >= 0; delIndex--)
            chessBoard[x][delIndex] = '.';
        //하
        for(int delIndex = y+1; delIndex < n; delIndex++)
            chessBoard[x][delIndex] = '.';
        //좌
        for(int delIndex = x-1; delIndex >= 0; delIndex--)
            chessBoard[delIndex][y] = '.';
        //우
        for(int delIndex = x+1; delIndex < n; delIndex++)
            chessBoard[delIndex][y] = '.';
        //좌상
        for(int delX = x-1, delY = y+1; delX >= 0 && delY < n; delX--, delY++)
            chessBoard[delX][delY] = '.';
        //우상
        for(int delX = x+1, delY = y+1; delX < n && delY < n; delX++, delY++)
            chessBoard[delX][delY] = '.';
        //좌하
        for(int delX = x-1, delY = y-1; delX >= 0 && delY >= 0; delX--, delY--)
            chessBoard[delX][delY] = '.';
        //우하
        for(int delX = x+1, delY = y-1; delX < n && delY >= 0; delX++, delY--)
            chessBoard[delX][delY] = '.';
    }

    public static void main(String[] args) {

        List<List<String>> result1 = solveNQueens(8);
        System.out.println("result1 = " + result1);

        List<List<String>> result2 = solveNQueens(6);
        System.out.println("result2 = " + result2);

    }
}
