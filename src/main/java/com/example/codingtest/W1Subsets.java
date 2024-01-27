package com.example.codingtest;

import java.util.ArrayList;
import java.util.List;

public class W1Subsets {

    /* https://leetcode.com/problems/subsets/description/ */
    /*
    * 부분집합 구하기!
    * 1,2,3 이 들어오면 총 8가지 경우의 수 있음
    * 역시나 백트래킹으로 가보자고~!
    * []
    * [1]
    * [1,2]
    * [1,2,3]
    * [2]
    * [2,3]
    * [3]
    * [1,2,3]
    * */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack();
        return result;
    }

    static void backtrack() {

    }
    public static void main(String[] args) {
        List<List<Integer>> result = subsets(new int[]{1,2,3});
        System.out.println(result);
    }
}
