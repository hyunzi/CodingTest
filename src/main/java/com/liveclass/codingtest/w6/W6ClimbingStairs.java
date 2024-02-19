package com.liveclass.codingtest.w6;

public class W6ClimbingStairs {

    public static int climbStairs(int n) {

        int[] result = new int[n];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i == 2)
                count = i;
            else
                count = result[i-2] + result[i-3];
            result[i-1] = count;
        }

        return result[n-1];
    }

    public static void main(String[] args) {

        int result = climbStairs(10);
        System.out.println("result = " + result);

    }
}
