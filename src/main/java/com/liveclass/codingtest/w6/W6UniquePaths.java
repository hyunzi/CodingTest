package com.liveclass.codingtest.w6;

import java.util.Arrays;

public class W6UniquePaths {

    public static int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        /*
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        */
        return dp[m-1][n-1];

    }

    public static void main(String[] args) {

        int result1 = uniquePaths(3,2);
        System.out.println("result1 = " + result1);
        int result2 = uniquePaths(3,7);
        System.out.println("result2 = " + result2);
        int result3 = uniquePaths(5,6);
        System.out.println("result3 = " + result3);
    }
}
