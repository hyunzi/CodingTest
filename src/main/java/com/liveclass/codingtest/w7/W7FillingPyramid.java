package com.liveclass.codingtest.w7;

import java.util.*;
import java.util.stream.Stream;

public class W7FillingPyramid {

    //문제 이해
    //각 블록이 아래 두 블록의 값을 합한 값인 피라미드가 있다
    //일부 블록의 값이 주어지고, 전체 블록의 값을 알아내라
    //위에서부터 층별로 하나의 블록 숫자가 주어진다
    //왼쪽에서 몇 번째 블록인지 정보도 주어진다

    //생각의 흐름
    //어떤 알고리즘과 관련이 있을까..?
    //피라미드 규칙 상 각 행의 길이는 +1 씩 증가한다 1,2,3,4,5...
    //특정 블록의 값을 구할 때 그 상위랑, 좌/우 칸은 있음
    //한 행을 다 구하고 다음 행으로 가면 되겠지?
    //blocks 길이가 6이라면? 6+5+4+3+2+1 = 
    public static int[] solve(int[][] blocks) {

        int[][] result = new int[blocks.length][blocks.length];

        for (int i = 0; i < blocks.length; i++) {

            final int length = i+1;
            final int startIndex = blocks[i][0];
            final int startValue = blocks[i][1];
            result[i][startIndex] = startValue;

            for (int j = startIndex - 1; j >= 0; j--) {
                result[i][j] = result[i-1][j] - result[i][j+1];
            }
            for (int j = startIndex + 1; j < length; j++) {
                result[i][j] = result[i-1][j-1] - result[i][j-1];
            }
        }

        int k = 0;
        int[] answer = new int[pyramidLength(blocks.length)];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < i+1; j++) {
                answer[k++] = result[i][j];
            }
        }
        return answer;
    }

    public static int pyramidLength(int n) {
        int length = (1 + n) * (n / 2);
        length += (n % 2 == 0) ? 0 : (n / 2 + 1);
        return length;
    }

    public static void main(String[] args) {

        int[] result1 = solve(new int[][]{
                {0, 50}, {0, 22}, {2, 10}, {1, 4}, {4, -13}
        });
        System.out.println("result1 = " + Arrays.toString(result1));
        int[] result2 = solve(new int[][]{
                {0, 63},{0, 77},{1, -80},{3, 12},{4, 37}
        });
        System.out.println("result2 = " + Arrays.toString(result2));
    }
}
