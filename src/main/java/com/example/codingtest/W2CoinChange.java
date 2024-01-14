package com.example.codingtest;

import java.util.ArrayDeque;
import java.util.Arrays;
public class W2CoinChange {

    /* Site URL: https://leetcode.com/problems/coin-change */
    public static int coinChange(int[] coins, int amount) {

        //amount 보다 작은 동전으로, 큰 수부터 빼면 되지 않나
        //[1,2,3], 17 이라면? 17-3-3-3-3-3-2
        //이걸 탐욕법이라고 하는데, 이는 이 경우에 어울리지 않음. 앞의 선택 조건이 뒤에 영향을 미침
        //제약조건을 보고, 2^31-1 은 int 안에 담을 수 있겠다~ 이런걸 생각해야함

        Arrays.sort(coins);
        int startIndex = 0;
        for (int i = coins.length-1; i >= 0; i--) {
            if (amount >= coins[i]) {
                startIndex = i;
                break;
            }
        }

        int count = 0;
        while (amount >= 0 && startIndex >= 0) {
            if (amount >= coins[startIndex]) {
                System.out.println(amount+","+coins[startIndex]);
                amount -= coins[startIndex];
                count++;
            } else if (amount < coins[startIndex]) {
                //System.out.println(amount+","+coins[startIndex]);
                //amount += coins[startIndex];
                startIndex--;
            } else {
                startIndex--;
            }
        }

        return (amount == 0) ? count : -1;
    }

    public static int newCoinChange(int[] coins, int amount) {
        //[1,4,5] 12
        //12 부터 시작해서 -1, -4, -5
        //11,1,4,5 이걸 다시 돌면서 -1, -4, -5
        //10,7,6,0,X,X, 여기서 0을 만나보림!

        //[2,4,7] 25
        //23,21,18 을 돌면서 -2,-4,-7
        //21,19,16 / 19,17,14 / 16,14,11 그다음 이걸 다시 돌면서!
        // 하니씩~~ 0을 찾을 떄까지!!

        int count = 0;
        ArrayDeque<Integer> list = new ArrayDeque<>();

        list.add(amount);
        while (!list.isEmpty()) {
            amount = list.getFirst();
            for (int c : coins) {
                int diff = amount - c;
                if (diff > 0) {
                    list.add(diff);
                } else if (diff < 0) {
                    //System.out.println("마이너스: " + diff);
                } else {
                    //0일 때
                    return count;
                }
            }
            count++;
            list.remove(amount);
            System.out.println(list);
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println("결과: "+newCoinChange(new int[]{3,4,5}, 10));
    }
}