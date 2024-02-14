package com.liveclass.codingtest.w5;

import java.util.HashSet;

public class W5LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {

        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums)
            numSet.add(num);

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int currCnt = 1;
            if (numSet.contains(nums[i]-1)) continue;
            while (numSet.contains(nums[i]+currCnt)) {
                currCnt++;
            }
            result = Math.max(result, currCnt);
        }
        return result;
    }

    public static void main(String[] args) {
        int result1 = longestConsecutive(new int[]{100,4,200,1,3,2});
        System.out.println("result1 = " + result1);
        int result2 = longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1});
        System.out.println("result2 = " + result2);
    }
}
