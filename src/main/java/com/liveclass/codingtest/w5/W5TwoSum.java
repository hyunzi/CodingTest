package com.liveclass.codingtest.w5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class W5TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numMap.containsKey(target-nums[i]))
                return new int[]{numMap.get(target-nums[i]), i};
            numMap.put(nums[i], i);
        }
        return new int[]{0,0};
    }

    public static void main(String[] args) {
        int[] result1 = twoSum(new int[]{2,7,11,15}, 9);
        System.out.println("Arrays.toString(result1) = " + Arrays.toString(result1));
        int[] result2 = twoSum(new int[]{3,2,4}, 6);
        System.out.println("Arrays.toString(result2) = " + Arrays.toString(result2));
        int[] result3 = twoSum(new int[]{3,3}, 6);
        System.out.println("Arrays.toString(result3) = " + Arrays.toString(result3));
    }
}
