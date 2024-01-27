package com.example.codingtest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class W2TwoSum {

    /* https://leetcode.com/problems/two-sum/description/ */
    public static int[] twoSum(int[] nums, int target) {

        //이전에는 완전탐색을 해서 풀었던 문제..
        //완전탐색은 시간복잡도상 n^2 인데, max 10^4 가 들어오면 10^8 이므로 간당간당
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if ((nums[i] + nums[j]) == target) {
                return new int[]{list.indexOf(nums[i]), list.lastIndexOf(nums[j])};
            } else if ((nums[i] + nums[j]) > target) {
                j--;
            } else if ((nums[i] + nums[j]) < target) {
                i++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] result = twoSum(new int[]{1,2,3,4,5,6,7}, 13);
        System.out.println(Arrays.toString(result));
    }
}
