package com.example.codingtest;

public class W1WordSearch {

    /* https://leetcode.com/problems/word-search */
    /*
    * board에서 인접한 칸의 문자로 word를 만들 수 있는지 검사
    * 상하좌우로만 이동 가능하고 한 번 쓰면 다시 쓸 수 없음
    * 정답일 때만 앞으로 가는 백트래킹을 이용해보자!!
    * */
    public static boolean exist(char[][] board, String word) {
        boolean result = false;

        int m = board.length;
        int n = board[0].length;

        int k = 0;
        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != word.charAt(0)) continue;

                char buffer = board[i][j];
                board[i][j] = '.';
                if (backtrack(board, i, j, word, 0)) return true;
                board[i][j] = buffer;
            }
        }
        return result;
    }

    static int[][] rcList = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    static boolean backtrack(char[][] board, int r, int c, String word, int count) {
        if (count + 1 == word.length()) return true;

        int m = board.length;
        int n = board[0].length;

        for (int[] rc : rcList) {
            int currRow = r + rc[0];
            int currCol = c + rc[1];
            if (currRow >= 0 && currRow < m && currCol >= 0 && currCol < n) {
                if (board[currRow][currCol] == word.charAt(count+1)) {
                    char buffer = board[currRow][currCol];
                    board[currRow][currCol] = '.';
                    if (backtrack(board, currRow, currCol, word, count+1)) return true;
                    board[currRow][currCol] = buffer;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        boolean result = exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED");
        System.out.println(result);
        result = exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "DEE");
        System.out.println(result);

    }
}
