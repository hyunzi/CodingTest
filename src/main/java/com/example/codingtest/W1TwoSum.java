package com.example.codingtest;

import java.util.Arrays;

public class W1TwoSum {

    /* https://leetcode.com/problems/two-sum/ */
    /*
    * nums=[2,4,11,25], target=36 주어지면 두 합이 되는 index 반환
    * 즉, 위의 경우에는 return [2,3] 이고 순서는 상관 없음
    * O(n^2) 보다 적은 시간복잡도 생각하기
    * */
    public static  int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }

        }

        return new int[]{};
    }

    /* static이 앞에 붙는 변수나 메서드는 어떤 객체에 소속되는 것이 아닌, 클래스에 고정되어 있는 변수나 메서드 */
    /* 객체 생성 없이 사용할 수 있고, 프로그램 시작 시 메모리의 static 영역에 적재됐다가 프로그램 종료 시 해제된다 */
    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2,3,4,5}, 9);
        System.out.println(Arrays.toString(result));
    }

}
