package com.liveclass.codingtest.w8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class W8HandOutGiftCard {


    /*
    * 문제 분석
    * 1. 한 사람당 하나의 상품권이 있고, 다른 사람과 교환할 수 있다.
    * 2. 원하는 상품을 받는 사람이 최대가 되도록 하고, 못받는 사람의 수를 리턴하라
    * 3. 상품권과 원하는 상품 리스트의 최대 값은 100,000 이다
    *
    *
    * 생각의 흐름
    * 1. 간단한 완전탐색 문제일까? 해시셋이나 해시맵을 이용해야 하는 걸까?
    * 2. 해시셋에 넣어둔다면... 내가 원하는 상품이 있으면 바꿔버리면 될 듯?!
    * 3. 그리고 못 바꾼 경우의 카운트를 리턴
    *
    * 코드 구현
    *
    *
    * */


    public static int solve(int[] gift_cards, int[] wants) {

        final int n = gift_cards.length;
        //✅ 각 상품별 총 개수 구하기
        int[] count = new int[n+1];
        for (int gift_card : gift_cards) {
            count[gift_card]++;
        }
        int result = 0;
        //✅ 원하는 상품을 하나씩 가져가기
        for (int want : wants) {
            //✅ 원하는 상품을 가져가면 개수 - 1
            if (count[want] > 0) {
                count[want]--;
                //✅ 원하는 상품을 가져가지 못했다면 result + 1
            } else {
                result++;
            }
        }
        //✅ result 반환
        return result;
    }

    public static void main(String[] args) {

        int result1 = solve(new int[]{9, 4, 7, 6, 1, 9, 2, 2, 9}, new int[]{4, 1, 8, 4, 1, 7, 9, 7, 5});
        System.out.println("result1 = " + result1);
        int result2 = solve(new int[]{5,4,5,4,5}, new int[]{1,2,3,5,4});
        System.out.println("result2 = " + result2);

    }
}
