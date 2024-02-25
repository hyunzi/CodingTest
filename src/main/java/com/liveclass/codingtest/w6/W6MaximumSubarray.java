package com.liveclass.codingtest.w6;

import java.util.ArrayList;
import java.util.List;

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

        List<int[]> dp = new ArrayList<>();
        /*
        int left = 0;
        int right = nums.length-1;

        while (left < (nums.length / 2)) {
            if (nums[left] >= 0 && nums[right] >= 0 && right >= left) {
                dp.add(new int[]{left, right});
                left++;
                right--;
            } else if (nums[left] > 0) {
                right--;
            } else if (nums[right] > 0) {
                left++;
            } else {
                left++;
                right--;
            }
        }*/

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
                        dp.add(new int[]{left, right});
                        //System.out.print("left = " + left);
                        //System.out.println(" / right = " + right);
                    }
                }
            }
        }

        int result = Integer.MIN_VALUE;
        if (dp.isEmpty()) {
            for (int i = 0; i < nums.length; i++) {
                result = Math.max(result, nums[i]);
            }
        }
        for (int[] d : dp) {
            int sum = 0;
            for (int i = d[0]; i <= d[1]; i++) {
                sum += nums[i];
            }
            //System.out.println("sum = " + sum);
            result = Math.max(result, sum);
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
