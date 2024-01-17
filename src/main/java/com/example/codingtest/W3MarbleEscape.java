package com.example.codingtest;

import java.util.Arrays;
import java.util.Scanner;

public class W3MarbleEscape {

    public static void main(String args[]) {

        // 빨간구슬이랑 파란구슬의 최단거리를 구해서 비교하면 되는 것 아닐까?
        // 빨간구슬의 최단거리를 구하며, 파란 구슬을 똑같이 움직였을 때, 겹치거나 먼저 빠져나가는지 검사해야할듯?
        //음... 꼭 최단거리여야 할까 그냥 이동하는 방법 중에 파란구슬이 도달하기 전이기만 하면 되지 않을까?

        /* 1. 데이터 입력 받기 */
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int rowLength = Integer.parseInt(str.split(" ")[0]);
        int colLength = Integer.parseInt(str.split(" ")[1]);
        char[][] graph = new char[rowLength][colLength];
        int[] redMarble = {};
        int[] blueMarble = {};
        int[] exit = {};
        for (int i = 0; i < rowLength; i++) {
            char[] lineArr = sc.nextLine().toCharArray();
            for (int j = 0; j < lineArr.length; j++) {
                graph[i][j] = lineArr[j];
                if (lineArr[j] == 'R') {
                    redMarble = new int[]{i,j};
                } else if (lineArr[j] == 'B') {
                    blueMarble = new int[]{i,j};
                } else if (lineArr[j] == 'O') {
                    exit = new int[]{i,j};
                }
            }
        }

        /* 2. 빨간구슬 좌표에서 구멍 좌표로 이동하는 거리 구하기 */
        System.out.println(Arrays.toString(redMarble));

    }

}
