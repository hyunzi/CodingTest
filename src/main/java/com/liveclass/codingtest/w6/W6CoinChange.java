package com.liveclass.codingtest.w6;

import java.util.Arrays;

public class W6CoinChange {

    /* https://leetcode.com/problems/coin-change/description/ */

    public static int coinChange(int[] coins, int amount) {
        final int LIMIT = 10001;

        //amount+1 크기의 dp 배열을 만들고 10001로 초기화한다 (제약조건: 0 <= amount <= 10^4 이기 때문)
        int[] dp = new int[amount+1];
        Arrays.fill(dp, LIMIT);

        //dp[0] = 0 으로 초기화 한다
        dp[0] = 0;

        //0부터 amount 까지 순회한다
        for (int i = 0; i < amount; i++) {
            //coins 의 각 동전을 살펴본다
            if (dp[i] == LIMIT) continue;
            for (int coin : coins) {
                if (i + coin > amount || i + coin < 0) continue;
                //현재 값과 선택한 동전의 합이 목표 금액 이하인 경우
                if (dp[i + coin] > dp[i] + 1) {
                    //점화식에 따라 '현재 값과 선택한 동전의 합'의 조합 가능 최소 동전 개수를 저장한다
                    dp[i + coin] = dp[i] + 1;
                }
            }
        }

        //목표 금액에 대해 조합 가능 최소 동전 개수가 10001이면, -1을 반환한다
        //아니라면, 계산한 목표 금액에 대해 조합 가능 최소 동전 개수를 반환한다
        return (dp[amount] == LIMIT) ? -1 : dp[amount];
    }

    public static void main(String[] args) {

        int result1 = coinChange(new int[]{1,2,5}, 11);
        System.out.println("result1 = " + result1);
        int result2 = coinChange(new int[]{2}, 3);
        System.out.println("result2 = " + result2);
        int result3 = coinChange(new int[]{1}, 0);
        System.out.println("result3 = " + result3);
    }
}
