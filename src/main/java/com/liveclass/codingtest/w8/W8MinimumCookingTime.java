package com.liveclass.codingtest.w8;

import java.util.*;

public class W8MinimumCookingTime {

    /*
    * 문제 분석
    * 1. 요리에 걸리는 시간과, 요리의 선행 관계가 주어진다
    * 2. k 단계를 수행하기 전에 반드시 완료해야 하는 단계의 개수와 최소 시간 리턴
    * 3. 선후관계는 [a,b] b를 수행하기 전에 a를 수행해야 한다
    * 4. 요리는 동시에 진행될 수 있다..
    *
    * 생각의 흐름
    * 1. SJF 알고리즘 했던 것과 위상정렬을 참고해서 풀어야겠다
    * 2. 선 후행 관계의 연결을 먼저 구해야함
    * 3. 그 다음 k 단계까지 오기 위한 시간을 구해야 함
    *
    * 코드 구현
    *
    *
    * */

    public static int[] solve(int[] cook_times, int[][] order, int k) {


        Map<Integer, List<Integer>> orderMap = new HashMap<>();
        int[] indegree = new int[cook_times.length];
        for (int[] ord : order) {
            orderMap.putIfAbsent(ord[1], new ArrayList<>());
            orderMap.get(ord[1]).add(ord[0]);
            indegree[ord[0]]++;
        }
        System.out.println("orderMap = " + orderMap);
        System.out.println("Arrays.toString(indegree) = " + Arrays.toString(indegree));
        //orderMap = {4=[2, 3], 5=[2, 3], 6=[1, 4, 5], 7=[6]}
        //6 구하려면 1,4,5 들어야 하고 1,4,5 들으려면 2,3
        //1,4,5 중에 max 종료시간 구하고
        //2,3 중에 max 종료시간 구해
        
        

        //System.out.println("answer = " + answer);


        orderMap.get(k);


        return new int[]{ 0 };
    }


    public static void main(String[] args) {

        int[] result1 = solve(new int[]{5, 30, 15, 30, 35, 20, 4}, new int[][]{
                {2,4},{2,5},{3,4},{3,5},{1,6},{4,6},{5,6},{6,7}
        }, 6);
        System.out.println("result1 = " + Arrays.toString(result1));
        int[] result2 = solve(new int[]{5, 30, 15, 30, 35, 20, 4, 50, 40}, new int[][]{
                {2,4},{2,5},{3,4},{3,5},{1,6},{4,6},{5,6},{6,7},{8,9}
        }, 9);
        System.out.println("result2 = " + Arrays.toString(result2));
        int[] result3 = solve(new int[]{5, 3, 2}, new int[][]{
                {1,2},{2,3},{1,3}
        }, 3);
        System.out.println("result3 = " + Arrays.toString(result3));

    }
}
