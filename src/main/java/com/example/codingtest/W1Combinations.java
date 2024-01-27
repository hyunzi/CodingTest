package com.example.codingtest;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class W1Combinations {

    /* https://leetcode.com/problems/combinations/ */
    /*
    * n = 4, k = 2 가 들어오면 k 갯수의 원소를 쓰는 조합을 추출
    * 1,2
    * 1,3
    * 1,4
    * 2,3
    * 2,4
    * 3,4
    * */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(new ArrayList<Integer>(), 1, n, k, result);
        return result;
    }
    public static void backtrack(ArrayList<Integer> curr, int start, int n, int k, List<List<Integer>> result) {
        if (curr.size() == k) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i <= n; i++) {
            if (!curr.contains(i)) {
                curr.add(i);
                backtrack(curr,i+1, n, k, result);
                curr.remove(Integer.valueOf(i));
            }
        }
    }
    public static void main(String[] args) {
        List<List<Integer>> combine = new ArrayList<>();
        combine = combine(4,2);
        System.out.println(combine);
    }
}
