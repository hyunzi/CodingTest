package com.liveclass.codingtest.w1;

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
    * [1,3]
    * */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(new ArrayList<Integer>(), nums, 0, result);
        return result;
    }

    static void backtrack(ArrayList<Integer> curr, int[] nums, int n, List<List<Integer>> result) {
        /* 여기가 핵심인데, 조합과 다르게 정해진 원소 갯수 없이 모두 넣어야 하기 때문 */
        result.add(new ArrayList<Integer>(curr));

        for (int i = n; i < nums.length; i++) {
            if (!curr.contains(nums[i])) {
                curr.add(nums[i]);
                backtrack(curr, nums, i+1, result);
                curr.remove(Integer.valueOf(nums[i]));
            }
        }
    }
    public static void main(String[] args) {
        List<List<Integer>> result = subsets(new int[]{1,2,3});
        System.out.println(result);
    }
}
