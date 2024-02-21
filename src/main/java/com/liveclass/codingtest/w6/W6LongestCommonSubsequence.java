package com.liveclass.codingtest.w6;

public class W6LongestCommonSubsequence {

    /* https://leetcode.com/problems/longest-common-subsequence/ */

    public static int longestCommonSubsequence(String text1, String text2) {

        char[] textArr1 = text1.toCharArray();
        char[] textArr2 = text2.toCharArray();
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int i = 1; i <= textArr1.length; i++) {
            for (int j = 1; j <= textArr2.length; j++) {
                if (textArr1[i-1] == textArr2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        int result1 = longestCommonSubsequence("cabcdebcde", "bbcd");
        System.out.println("result1 = " + result1);
        int result2 = longestCommonSubsequence("abc", "abc");
        System.out.println("result2 = " + result2);
        int result3 = longestCommonSubsequence("abc", "def");
        System.out.println("result3 = " + result3);
    }
}
