package com.example.codingtest;

import java.util.ArrayList;
import java.util.stream.IntStream;

public record W3MazeEscapeGreedy() {

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
 
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

        //x,y -> r,c 까지의 경로! 중복 방무 가능
        //영어 사전순으로 가장 빠른건? dlru 하 좌 우 상
        int[][] rcList = {{1,0}, {0,-1}, {0,1}, {-1,0}};

        //k가 최단거리보다 클거면 2의 배수로 커야함
        //k가 최단거리보다 작으면 impossible
        int minPath = Math.abs(x-r) + Math.abs(y-c);
        if (k < minPath) {
            return "impossible";
        }
        if ((k-minPath) % 2 != 0) {
            return "impossible";
        }

        ArrayList<String> result = new ArrayList<>();

        int cnt = 0;
        while (cnt < k) {
            System.out.println(x+","+y+","+r+","+c);
            //k가 최단거리와 같을 때 이동할 방향은?
            if (x < r) { // 아래로 가야할 수도 있고
                result.add("d");
                x++;
            }
            if (y > c) { // 왼쪽으로 가야할 수도 있어
                result.add("l");
                y--;
            }
            if (y < c) { // 오른쪽으로 가야할 수도 있고
                result.add("r");
                y++;
            }
            if (x > r) { // 위로 가야할 수도 있고
                result.add("u");
                x--;
            }
            //여기서 추가로 클 때 이동할 방향은?
            //n과 m을 벗어나지 않는 선에서 하좌우상


            cnt++;
        }

        //k가 최단거리보다 클 때 이동할 방향은?
        //일단 최단거리 + @로 이동해야 하는 경우임!
        cnt = 0;
        if ((k-cnt) > (Math.abs(x-r) + Math.abs(y-c))) { // 최단거리보다 큰 만큼만!
            cnt++;
            if (x < r) { // 아래로 가야할 수도 있고
                result.add("d");
                x++;
            }
            if (y > c) { // 왼쪽으로 가야할 수도 있어
                result.add("l");
                y--;
            }
            if (y < c) { // 오른쪽으로 가야할 수도 있고
                result.add("r");
                y++;
            }
            if (x > r) { // 위로 가야할 수도 있고
                result.add("u");
                x--;
            }
        }



        if ((x == r) && (y == c) && (result.size() == k)) {
            answer = result.toString();
        } else {
            answer = "impossible";
        }

        return answer;
    }

    public static void main(String[] args) {
        String result = solution(3,4,2,3,3,1,5);
        //String result = solution(2,2,1,1,2,2, 2);

        System.out.println(result);
    }
}
