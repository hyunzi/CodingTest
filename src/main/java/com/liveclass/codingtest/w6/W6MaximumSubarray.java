package com.liveclass.codingtest.w6;

import java.util.Arrays;

public class W6MaximumSubarray {

/*
    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
    Output: 6
    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
    Example 2:

    Input: nums = [1]
    Output: 1
    Explanation: The subarray [1] has the largest sum 1.
    Example 3:

    Input: nums = [5,4,-1,7,8]
    Output: 23
    Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
*/

    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }

        int max = dp[0];
        for (int num : dp) {
            max = Math.max(max, num);
        }
        return max;
    }

    public static int maxSubArray_fail(int[] nums) {

        int[] dpMax = new int[nums.length];
        Arrays.fill(dpMax, Integer.MIN_VALUE);

        for (int left = 0; left < nums.length; left++) {
            int right = nums.length;
            if (nums[left] < 1) {
                continue;
            } else {
                while (left < right) {
                    right--;
                    if (nums[right] < 1) {
                        continue;
                    } else {
                        int sum = 0;
                        for (int i = left; i <= right ; i++) {
                            sum += nums[i];
                        }
                        dpMax[left] = Math.max(dpMax[left], sum);
                    }
                }
            }
        }

        Arrays.sort(dpMax);
        //System.out.println("dpMax = " + Arrays.toString(dpMax));
        int result = Integer.MIN_VALUE;
        if (dpMax[dpMax.length-1] == Integer.MIN_VALUE) {
            for (int i = 0; i < nums.length; i++) {
                result = Math.max(result, nums[i]);
            }
        } else {
            result = dpMax[dpMax.length-1];
        }

        return result;
    }

    public static void main(String[] args) {

        int result1 = maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println("result1 = " + result1);
        int result2 = maxSubArray(new int[]{-1});
        System.out.println("result2 = " + result2);
        int result3 = maxSubArray(new int[]{5,4,-1,7,8});
        System.out.println("result3 = " + result3);
        int result4 = maxSubArray(new int[]{1,2});
        System.out.println("result4 = " + result4);
        int result5 = maxSubArray(new int[]{8,-19,5,-4,20});
        System.out.println("result5 = " + result5);

    }
}
