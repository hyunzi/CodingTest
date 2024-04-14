package com.liveclass.codingtest.w2;

import java.util.ArrayDeque;
import java.util.Queue;

public class W2CoinChangeV2 {

    /* Site URL: https://leetcode.com/problems/coin-change */
    public static int coinChange(int[] coins, int amount) {

        if (amount == 0) return 0;

        boolean[] visited = new boolean[amount + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{amount, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int coin : coins) {
                int nextAmount = cur[0] - coin;
                if (nextAmount == 0) {
                    return cur[1] + 1; //이걸 for 문 밖으로 빼면 오버플로우가 발생하는 경우가 생김
                    // integer.MAXVALUE 값 때문에!!
                } else if (nextAmount > 0 && !visited[nextAmount]) {
                    //위 조건문에서 순서를 바꾸면 visited[-1] 이런 식으로 들어갈 수 있어서 오류..
                    queue.add(new int[]{nextAmount, cur[1] + 1});
                    visited[nextAmount] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("결과: " + coinChange(new int[]{3, 4, 5}, 10));
        System.out.println("결과: " + coinChange(new int[]{2}, 3));
        System.out.println("결과: " + coinChange(new int[]{1, 2, 3}, 15));
        System.out.println("결과: " + coinChange(new int[]{1, 1, 1}, 15));
    }

    // 안쓰고 그냥 배열이 더 편함..
    static class Entry {
        int amount;
        int count;

        public Entry(int amount, int count) {
            this.amount = amount;
            this.count = count;
        }
    }

}