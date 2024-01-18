package com.example.codingtest;

import java.util.Arrays;



public class W2MatrixOperations {

        public static int[][] solution(int[][] rc, String[] operations) {
            int[][] answer = {};

            for (String s : operations) {
                if (s.equals("ShiftRow")) {
                    answer = shiftRow(rc);
                } else if (s.equals("Rotate")) {
                answer = rotate(rc);
            }
            print(answer);
            for (int i = 0; i < rc.length; i++) {
                System.arraycopy(answer[i],0,rc[i],0, rc[i].length);
            }
        }

        return answer;
    }

    public static int[][] shiftRow(int[][] rc) {
        System.out.println("Operation ShiftRow");

        int[][] answer = new int[rc.length][rc[0].length];
        for (int j = 0; j < rc[0].length; j++) {
            answer[0][j] = rc[rc.length-1][j];
        }

        for (int i = 0; i < rc.length-1; i++) {
            for (int j = 0; j < rc[i].length; j++) {
                answer[i+1][j] = rc[i][j];
            }
        }
        return answer;
    }

    public static int[][] rotate(int[][] rc) {
        System.out.println("Operation Rotate");
        //가장 바깥쪽만 움직임...
        int[][] answer = new int[rc.length][rc[0].length];
        //첫행처리
        for (int j = 0; j < rc[0].length-1; j++) {
            answer[0][j+1] = rc[0][j];
        }
        //끝열처리
        for (int i = 0; i < rc.length - 1; i++) {
            answer[i+1][rc[i].length-1] = rc[i][rc[i].length-1];
        }
        //끝행처리
        for (int j = rc[0].length-1; j > 0; j--) {
            answer[rc.length-1][j-1] = rc[rc.length-1][j];
        }
        //첫열처리
        for (int i = rc.length-1; i > 0; i--) {
            answer[i-1][0] = rc[i][0];
        }
        //가운데 넣기
        for (int i = 1; i < rc.length-1; i++) {
            for (int j = 1; j < rc[0].length-1; j++) {
                answer[i][j] = rc[i][j];
            }
        }
        return answer;
    }

    public static void print(int[][] answer) {
        for (int[] row : answer) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {

        //solution(new int[][]{{1, 2, 3, 4}, {5,6,7,8}, {9,10,11,12}}, new String[]{"ShiftRow", "ShiftRow", "ShiftRow"});
        solution(new int[][]{{1, 2, 3, 4}, {5,6,7,8}, {9,10,11,12}}, new String[]{"Rotate"});
        solution(new int[][]{{1, 2, 3, 4, 5}, {6,7,8,9,10}, {11,12,13,14,15}}, new String[]{"Rotate"});
        solution(new int[][]{{1, 2, 3}, {4,5,6}, {7,8,9}}, new String[]{"Rotate", "ShiftRow"});
    }
}
