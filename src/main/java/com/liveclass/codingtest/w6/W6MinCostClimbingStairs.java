package com.liveclass.codingtest.w6;

public class W6MinCostClimbingStairs {

    /* https://leetcode.com/problems/min-cost-climbing-stairs/description/ */

    public static int minCostClimbingStairs(int[] cost) {

        //5,4,1,3,2,100,2,100,6
        //1,5,1,1,4,...

        //0,3 - 1칸 넘어가버리기
        //1,3 - 2칸 넘어가버리기
        //2,6 - 2,1칸 넘어가버리기
        //3,6 - 2,2칸 넘어가버리기
        //4,8 - 2,1,2칸 넘어가버리기
        //5,8 - 2,1,2,1칸 넘어가버리기
        //6,8 - 2,1,2,1칸 넘어가버리기
        //7,16 - 2,1,2,2칸 넘어가버리기

        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        //co = {1,2,3,4,5,6,7,8,9,1}
        //dp = {0,0,1,2,4,6,9,12,16,20,21} // 1칸을 더 구함!
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }

        return dp[cost.length];
    }

    public static void main(String[] args) {

        int result1 = minCostClimbingStairs(new int[]{10,15,20});
        System.out.println("result1 = " + result1);
        int result2 = minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1});
        System.out.println("result2 = " + result2);
        int result3 = minCostClimbingStairs(new int[]{1,2,3,4,5,6,7,8,9,1});
        System.out.println("result3 = " + result3);
    }
}
