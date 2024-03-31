package com.liveclass.codingtest.w2;

import java.util.ArrayDeque;
import java.util.Queue;

public class W2CoinChangeNew {

    /* Site URL: https://leetcode.com/problems/coin-change */
    public static int coinChange(int[] coins, int amount) {

        if (amount == 0) return 0;

        Queue<Entry> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[amount + 1];

        queue.add(new Entry(amount, 0));
        while (!queue.isEmpty()) {

            Entry cur = queue.remove();

            for (int i = 0; i < coins.length; i++) {

                int nextAmount = cur.amount - coins[i];
                if (nextAmount == 0) {
                    return cur.count + 1;
                } else if (nextAmount > 0 && !visited[nextAmount]) {
                    queue.add(new Entry(nextAmount, cur.count + 1));
                    visited[nextAmount] = true;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("결과: "+coinChange(new int[]{3,4,5}, 10));
        System.out.println("결과: "+coinChange(new int[]{3,4,5}, 15));
        System.out.println("결과: "+coinChange(new int[]{1,2,3}, 15));
        System.out.println("결과: "+coinChange(new int[]{1,1,1}, 15));
    }

    static class Entry {
        int amount;
        int count;

        public Entry(int amount, int count) {
            this.amount = amount;
            this.count = count;
        }
    }

}