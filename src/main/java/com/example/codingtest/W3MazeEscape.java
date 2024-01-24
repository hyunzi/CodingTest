package com.example.codingtest;

import java.util.stream.IntStream;

public record W3MazeEscape() {

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "test";

        /* 해석의 주석 참고 */
        /* 그리디: 앞의 선택이 이후의 선택에 영향을 주지 않는 경우!
        * 출구까지의 거리를 제외하고 남은 거리가 2로 나누어지지 않으면 불가능하다.
        * k가 출구까지의 최단거리보다 작아도 불가능하다.
        * 앞으로 이동해야 하는 거리가 출구까지의 거리보다 크다면:
        * 1. 맨 아래로 이동한다.
        * 2. 맨 왼쪽으로 이동한다.
        * 3. 오른쪽으로 갔다가 다시 왼쪽으로 가기를 반복한다.
        * 남은 거리는 사전순으로 최소가 되도록 목적지까지 이동한다.
        *
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
        int minK = Math.abs(r-x) + Math.abs(c-y);
        if (((k - minK) % 2 != 0) || k < minK) {
            return "impossible";
        }

        StringBuilder sb = new StringBuilder();
        int dist = 0;
        // 5 * 4 의 미로라면?
        /*
        * * * S *
        * * * * *
        * * E * *
        * * * * * -> 7번 만에 가야해
        * */
        while ((k - dist) > Math.abs(r-x) + Math.abs(c-y)) {
            if (x < n) { // x가 더 오른쪽으로 갈 수 있다면?
                x++;
                sb.append('d');
            } else if (y > 1) { // y가 더 아래로 갈 수 있다면?
                y--;
                sb.append('l');
            } else { // 둘 다 안된다면?
                y++;
                sb.append('r');
            }
            dist++;
        }


        //그 다음 남은 거리는 사전순서인 dlru 순서로 이동해야함
        //해설코드에서 IntStream.range()를 사용했는데 아주 유용해보임!!!
        if (x < r) {
            IntStream.range(0, r - x).forEach(i -> sb.append('d'));
        }
        if (y > c) {
            IntStream.range(0, y - c).forEach(i -> sb.append('l'));
        }
        if (y < c) {
            IntStream.range(0, c - y).forEach(i -> sb.append('r'));
        }
        if (x > r) {
            IntStream.range(0, x - r).forEach(i -> sb.append('u'));
        }

        answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        //String result = solution(3,4,2,3,3,1,5);
        String result = solution(2,2,1,1,2,2, 2);

        System.out.println(result);
    }
}
