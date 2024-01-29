package com.liveclass.codingtest.w2;

public class W2TrappingRainWater {

    public static int trap(int[] height) {
        int result = 0;

        int left = 0;
        int curr = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                curr = height[left];
                if (leftMax > curr) {
                    result += leftMax - curr;
                } else {
                    leftMax = curr;
                }
            } else {
                right--;
                curr = height[right];
                if (rightMax > curr) {
                    result += rightMax - curr;
                } else {
                    rightMax = curr;
                }
            }
            System.out.println(result);
        }

        //왼쪽 포인터, 오른쪽 포인터가 있음
        //왼쪽보다 오른쪽이 높거나 같은 경우
            //왼쪽을 한 칸 이동한 곳이 더 낮으면? 물을 채움 왼쪽-현재
            //왼쪽을 한 칸 이동한 곳이 더 높거나 같으면? 왼쪽Max=현재
        //왼쪽이 오른쪽보다 높은 경우
            //오른쪽을 한 칸 이동한 곳이 더 낮으면? 물을 채움 오른쪽-현재
            //오른쪽을 한 칸 이동한 곳이 더 높거나 같으면? 오른쪽Max = 현재
        //이렇게 왼쪽이 오른쪽을 넘기 전까지 진행!

        return result;
    }

    public static void main(String[] args) {

        trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }

}