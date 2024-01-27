package com.example.codingtest;

import java.util.stream.IntStream;

public record W3MazeEscapeDFS() {

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        /* 해석의 주석 참고 */
        /*
        * DFS: 깊이 우선 탐색
        * 출구까지의 거리를 제외하고 남은 거리가 2로 나누어지지 않으면 불가능하다.
        * k가 출구까지의 최단거리보다 작아도 불가능하다.
        * 목적지까지 dfs를 수행한다. dfs의 수행순서는 사전순으로 빠른 순서이다.
        * 중간에 더 이상 불가능한 경우를 걸러냄으로써 시간초과를 해결한다.
        * 목적지에 도달했다면 true를 리턴한다.
        * 범위를 벗어났거나 이동 거리가 k 보다 커지면 false를 리턴한다.
        * 이동할 수 있는 거리가 출구까지의 거리보다 멀어지면 false를 리턴한다.
        * */

        // x,y 에서 r,c로 이동하는 최단거리는? r-x + c-y
        // 해설코드에서 Math.abs() 로 절대값을 구하는 방식으로 사용함
                y++;
        return answer;
    }

    public static void main(String[] args) {
        //String result = solution(3,4,2,3,3,1,5);
        String result = solution(2,2,1,1,2,2, 2);
        System.out.println(result);
    }
}
