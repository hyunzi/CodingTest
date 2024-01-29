package com.example.codingtest.w1;

public class W1BinarySearch {

    /* https://leetcode.com/problems/binary-search/description/ */
    /*
    * 이진탐색!
    * 이미 정렬된 배열이 들어오므로 정렬할 필요 없이 가운데부터 보면 됨
    * 존재하면 해당 인덱스, 없으면 -1을 반환하면 됨
    * */
    public static int search(int[] nums, int target) {
        int result = -1;
        /*
        0,1,2,3,4,5,6,7,8,9,10,11;
        mid = 12/6;
        mid = (7+11)/2 = 9.5; => 9
        mid = (0+5)/2 = 2.5; => 2
        */

        result = binarySearch(nums, 0, nums.length-1, target);
        return result;
    }

    static int binarySearch(int[] nums, int start, int end, int target) {
        int result = -1;
        int mid = (start+end)/2;
        if (start <= end) {
            if (nums[mid] == target) {
                result = mid;
            } else if (nums[mid] < target) {
                result = binarySearch(nums, mid + 1, end, target);
            } else if (nums[mid] > target) {
                result = binarySearch(nums, start, mid - 1, target);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int result = search(new int[]{-1,0,3,5,9,12}, 9);
        System.out.println(result);
    }
}
