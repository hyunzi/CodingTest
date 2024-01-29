package com.liveclass.codingtest.w2;

import java.util.ArrayDeque;
import java.util.Arrays;

public class W2DailyTemperatures {

    /* https://leetcode.com/problems/daily-temperatures/ */
    public static int[] dailyTemperatures(int[] temperatures) {

        //큐에 값을 하나씩 넣으며, 이전값과 대조
        //기온이 올랐을 때, 해당 인덱스 - 이전값 인덱스
        ArrayDeque<Integer> list = new ArrayDeque<>();
        int[] arr = new int[temperatures.length];

        for (int i=0; i < temperatures.length; i++) {
            while (list.peek() != null && temperatures[i] > temperatures[list.peek()]) {
                arr[list.peek()] = i - list.peek(); //여기서 인덱스를 빼도록 해야함
                list.pop();
            }
            list.push(i);
        }
        //대박 진짜 한 끝 차이였음 인덱스를 애초에 넣어서 쓰는 거였음
        return arr;
    }
    public static void main(String[] args) {
        int[] result = dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
        System.out.println(Arrays.toString(result));
        result = dailyTemperatures(new int[]{30,40,50,60});
        System.out.println(Arrays.toString(result));
        result = dailyTemperatures(new int[]{30,60,90});
        System.out.println(Arrays.toString(result));
    }
}
