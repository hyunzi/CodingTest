package com.liveclass.codingtest.w7;

import java.util.Arrays;

public class W7Escalator {

    public static int solution(int[][] escalator) {
        int LIMIT = 10000000;

        escalator[0][0] = escalator[0][0] == 1 ? LIMIT : 1;
        escalator[0][1] = escalator[0][1] == 1 ? LIMIT : 0;
        escalator[0][2] = escalator[0][2] == 1 ? LIMIT : 1;

        for (int r = 1; r < escalator.length; r++) {
            for (int c = 0; c < 3; c++) {
                if (escalator[r][c] == 1) escalator[r][c] = LIMIT;
                else {
                    int min = LIMIT;
                    for (int k = 0; k < 3; k++) {
                        min = Math.min(min, escalator[r-1][k] + Math.abs(k - c));
                    }
                    escalator[r][c] = min;
                }
            }
        }

        return Arrays.stream(escalator[escalator.length-1]).min().getAsInt();
    }

    public static void main(String[] args) {

        int result1 = solution(new int[][]{
                {0,1,0},
                {1,0,0},
                {0,1,0},
                {0,0,1},
                {0,1,0}
        });
        System.out.println("result1 = " + result1);
        /*int result2 = solution(new int[][]{
                {0,1,1},
                {1,0,0},
                {0,0,1},
                {1,1,0},
                {0,1,0},
                {1,0,1},
                {1,1,0}
        });
        System.out.println("result2 = " + result2);*/
    }
}
