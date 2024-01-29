package com.liveclass.codingtest.w3;

import java.io.IOException;
import java.util.Scanner;

public class W3RND {


    public static void main(String[] args) throws IOException {

        /* 1. 데이터 입력 받기 */
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int mapLength = Integer.parseInt(str.split(" ")[0]);
        int virusNumber = Integer.parseInt(str.split(" ")[1]);
        String[][] graph = new String[mapLength][mapLength];
        int idx = 0;
        while (idx < mapLength) {
            graph[idx++] = sc.nextLine().split(" ");
        }

        //만약에 2가 10개더라도, 2의 상하좌우에 벽을 3개 세우는 케이스는
        //10 * 4 = 40개 중에 3개씩 부분조합을 구하는 경우임
        //이 경우의 수를 모두 탐색..?
        //헉 더 많이... 0이 있는 모든 곳의 부분조합을 구하는 방법으로 풀어야함 ㄷㄷ
    }
}
