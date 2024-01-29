package com.liveclass.codingtest.w2;

import java.util.Arrays;

public class W2TwoQueue {

    /* https://school.programmers.co.kr/learn/courses/30/lessons/118667 */

    public static int solution(int[] queue1, int[] queue2) {

        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        int count = 0;

        for (int i=0,j=0; i < queue1.length*4 || j < queue2.length*4; i=i) {
            if (sum1 == sum2) {
                count = i+j;
                break;
            } else if (sum1 > sum2) {
                //i가 length보다 크면 다시 j를 0으로 바꿔서 봐야하고
                //j가 length보다 크면 다시 i를 0으로 바꿔서 봐야함
                if ((i/queue1.length) % 2 == 0) {
                    sum1 -= queue1[i%queue1.length];
                    sum2 += queue1[i%queue1.length];
                } else {
                    sum1 -= queue2[i%queue1.length];
                    sum2 += queue2[i%queue1.length];
                }
                i++;
            } else if (sum1 < sum2) {
                if ((j/queue2.length) % 2 == 0) {
                    sum2 -= queue2[j%queue2.length];
                    sum1 += queue2[j%queue2.length];
                } else {
                    sum2 -= queue1[j%queue2.length];
                    sum1 += queue1[j%queue2.length];
                }
                j++;
            }
        }

        return (sum1 != sum2) ? -1 : count;
    }

    public static void main(String[] args) {
        int result = solution(new int[]{3,2,7,2}, new int[]{4,6,5,1});
        System.out.println(result);
        result = solution(new int[]{1,2,1,2}, new int[]{1,10,1,2});
        System.out.println(result);
        result = solution(new int[]{1,1}, new int[]{1,5});
        System.out.println(result);
    }
}
