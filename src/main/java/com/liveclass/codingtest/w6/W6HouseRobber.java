package com.liveclass.codingtest.w6;

import java.util.Arrays;

public class W6HouseRobber {

    /* https://leetcode.com/problems/house-robber/description/ */

    public static int rob(int[] nums) {

        int[] dp = new int[nums.length];

        if (nums.length > 0)
            dp[0] = nums[0];
        if (nums.length > 1)
            dp[1] = Math.max(nums[0], nums[1]);
        if (nums.length > 2) {
            dp[2] = Math.max(nums[0] + nums[2], nums[1]);
            for (int i = 3; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 3] + nums[i]);
            }
        }
        Arrays.sort(dp);
        System.out.println("dp = " + Arrays.toString(dp));
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        int result1 = rob(new int[]{1,2,3,1});
        System.out.println("result1 = " + result1);
        int result2 = rob(new int[]{2,7,9,3,1});
        System.out.println("result2 = " + result2);
    }
}
