package com.liveclass.codingtest.w4;

import java.util.Arrays;

public class W4HikingCourse {

    /* https://school.programmers.co.kr/learn/courses/30/lessons/118669 */

    /*
    * 1. 노드들은 출입구, 쉼터, 산봉우리 중 하나고, 그 사이에 가중치가 다른 엣지들이 존재한다
    * 2. intensity : 쉼터나 산봉우리 방문 없이 이동해야 하는 가장 긴 시간
    * 3. 출입구에서 출발하여, 산봉우리 하나만 방문하고, 다시 출입구로 오는 최소 intensity 코스
    * 4. intensity 최소인 경우가 여러 개라면 산봉우리 번호가 가장 낮은 코스로 리턴
    * 5. 노드 수 n, 출입구 배열 gates, 산봉우리 배열 summits, 등산로 정보 paths (노드,노드,가중치)
    * 6. 리턴은 [산봉우리 번호, intensity 최솟값] 배열을 리턴
    * 7. 서로 다른 두 지점을 직접 연결하는 등산로는 최대 1개임
    * */

    /*
    * 1. 출입구 a에서 출발해 산봉우리 A까지 가는 방법 중 intensity 가 가장 작은 경우를 찾는다
    * 2. 출입구 a, b, c가 있고 산봉우리 A, B, C가 있다면? 3*3 = 9개의 [최소 intensity, 산봉우리] 나옴
    * 3. 올라가는 것만 구하면, 내려오는 것은 동일한 코스를 이용하면 되므로 무시해도 될 듯
    * 4. a~A 까지의 최단 가중치 코스를 다익스트라로 구할 때 gates, summits 의 노드는 무시해야함
    * 5. 구한 [intensity, 산봉우리] 리스트에서 intensity 최솟값, 산봉우리 노드의 최솟값 순으로 첫 번째 결과를 리턴한다
    * */

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        return answer;
    }

    public static void main(String[] args) {

        int[] result1 = solution(6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1, 3}, new int[]{5});
        System.out.println("result1 = " + Arrays.toString(result1));
        /*int[] result2 = solution(7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{1}, new int[]{2, 3, 4});
        System.out.println("result2 = " + Arrays.toString(result2));
        int[] result3 = solution(7, new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}}, new int[]{3, 7}, new int[]{1, 5});
        System.out.println("result3 = " + Arrays.toString(result3));
        int[] result4 = solution(5, new int[][]{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}}, new int[]{1, 2}, new int[]{5});
        System.out.println("result4 = " + Arrays.toString(result4));*/
    }
}
