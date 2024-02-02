package com.liveclass.codingtest.w4;

public class W4SharedTaxiFare {

    /* https://school.programmers.co.kr/learn/courses/30/lessons/72413 */

    /*
    * 1. N 개의 노드와 노드 사이의 간선에 적용되는 가중치가 제공된다
    * 2. 출발지에서 두 도착지를 모두 방문하는 최저 요금(=가중치)를 계산해야 한다
    * 3. N개의 노드는 1~N 으로 숫자가 표시된다
    * 4. 이동방향에 따라 가중치가 달라지지 않는다
    * 5. 주어지는 값: N(3~200), 출발지, 도착지1, 도착지2, 가중치 리스트
    * 6. 출도착지는 서로 겹치지 않는다
    * 7. 가중치 리스트의 형태는 [c,d,f]로 c와 d사이가 f 가중치라는 것
    * */

    //최단거리가 아닌 가중치 리스트에 따라 최소 비용 거리를 세야한다
    //현재 위치에서 가장 적은 가중치를 가진쪽으로 이동한다.


    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;


        return answer;
    }

    public static void main(String[] args) {
        int result = solution(6,4,6,2,new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});

        System.out.println(result);
    }
}
