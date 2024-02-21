package com.liveclass.codingtest.w6;

public class W6MaximalSquare {

    /* https://leetcode.com/problems/maximal-square/description/ */

    public static int maximalSquare(char[][] matrix) {

        int result = 0;
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') { // 현재 좌표가 1인지 확인
                    int k = 1;
                    int cnt = 0;

                    while (i + k <= row && j + k <= col) {
                        boolean flag = true;
                        cnt = 0;
                        for (int x = i; x < i + k; x++) {
                            if (matrix[x][j + k - 1] != '1') {
                                flag = false;
                                break;
                            }
                            cnt++;
                        }
                        for (int y = j; y < j + k; y++) {
                            if (matrix[i + k - 1][y] != '1') {
                                flag = false;
                                break;
                            }
                            cnt++;
                        }
                        if (!flag) break;
                        k += 1;

                    }

                    if (cnt == k * k) {
                        result = Math.max(result, cnt);
                    } else {
                        result = Math.max(result, (k - 1) * (k - 1));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int result1 = maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}});
        System.out.println("result1 = " + result1);
        int result2 = maximalSquare(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0'}});
        System.out.println("result2 = " + result2);
        int result3 = maximalSquare(new char[][]{
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}});
        System.out.println("result3 = " + result3);
        int result4 = maximalSquare(new char[][]{
                {'0', '1'},
                {'1', '0'}});
        System.out.println("result4 = " + result4);
    }
}
