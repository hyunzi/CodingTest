package com.example.codingtest.w1;

import java.util.ArrayList;
import java.util.List;

public class W1Permutations {

    /* https://leetcode.com/problems/permutations/description/ */
    // 배열에 있는 숫자의 순열 구하기 (부분집합, 조합과 다르다!)
    // 이것도 백트래킹 (재귀)로 구하면 편하다
    // [1,2,3]
    /*
    * 1 2 3
    * 1 3 2
    * 2 1 3
    * 2 3 1
    * 3 2 1
    * 3 1 2
    * */

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, result);
        return result;

    }

    public static void backtrack(ArrayList<Integer> currList, int[] nums, List<List<Integer>> result) {
        if (currList.size() == nums.length) {
            result.add(new ArrayList<>(currList));
            return;
        }

        for (int num : nums) {
            if (!currList.contains(num))  {
                currList.add(num);
                backtrack(currList, nums, result);
                currList.remove(currList.size() - 1);
            }
        }

    }
    public static void main(String[] args) {
        List<List<Integer>> result = permute(new int[]{3,4,2,1});
        System.out.println(result);
    }
}
