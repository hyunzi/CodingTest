package com.liveclass.codingtest.w2;

import java.util.*;

public class W2SheepAndWolfNew {

    /* Site URL: https://school.programmers.co.kr/learn/courses/30/lessons/92343 */

    /*
    * [문제 분석]
    * 1. info[0,0,1,1,1,...] : 양/양/늑/늑/늑 정보를 담고 있는 배열
    * 2. edge[{0,1},{1,2},{1,4},]: 이진트리를 나타냄
    *
    * [생각의 흐름]
    * 1. 백트래킹 써야할 듯 진행하다가 아니면 되돌아 오는
    * 2. 루트에서 출발, 다음 이동할 곳의 양/늑 카운트 했을 때 양 <= 늑 이면 진행하면 안됨
    * 3. 양 > 늑 경우로만 진행
    *
    * [코드 설계]
    * 1. visited 배열 선언 (노드의 사이즈만큼)
    * 2. 0번 노드 visit 체크
    * 3. DFS + 백트래킹
    * 4. 모든 edges 를 돌면서 확인
    *   4-1. edge[부모엣지, 자식엣지] 에서 부모엣지 visit 이면서 자식엣지 !visit 인 경우 진행
    *   4-2. 백트래킹
    *      4-2-1. 자식노드 방문표시
    *      4-2-2. 양이라면 양 + 1 해서 dfs 진행
    *      4-2-3. 늑대라면 늑대 + 1 해서 dfs 진행
    *      4-2-4. 자식노드 방문표시 해제
    *      4-2-5. dfs 의 결과는 maxSheep
    * */

    public static int sheepAndWolf(int[] info, int[][] edge) {

        boolean visited[] = new boolean[info.length];
        visited[0] = true;
        return dfs(info, edge, visited, 1, 0);
    }

    private static int dfs(int[] info, int[][] edges, boolean[] visited, int sheep, int wolf) {

        if (sheep == wolf) return sheep; //같아지면 바로 빠져나옴
        int maxSheep = sheep;

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if (visited[parent] && !visited[child]) {
                visited[child] = true;
                if (info[child] == 0) {
                    maxSheep = Math.max(maxSheep, dfs(info, edges, visited, sheep+1, wolf));
                } else {
                    maxSheep = Math.max(maxSheep, dfs(info, edges, visited, sheep, wolf+1));
                }
            }
        }

        return maxSheep;
    }
    
    public static void main(String[] args) {
        int result1 = sheepAndWolf(new int[]{0,0,1,1,1,0,1,0,1,0,1,1}, new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}});
        System.out.println("result1 = " + result1);
        int result2 = sheepAndWolf(new int[]{0,1,0,1,1,0,1,0,0,1,0}, new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}});
        System.out.println("result2 = " + result2);
    }
}