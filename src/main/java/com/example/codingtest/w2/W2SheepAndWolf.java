package com.example.codingtest.w2;

import java.util.*;

public class W2SheepAndWolf {

    /* Site URL: https://school.programmers.co.kr/learn/courses/30/lessons/92343 */
    public static int sheep = 0;
    public static int wolf = 0;

    public static int sheepAndWolf(int[] info, int[][] edge) {

        // 1. 백트래킹을 이용한 완전탐색을 해야할 듯
        // 2. 주어진 배열을 인접리스트로 바꿈
        // [0]: [1,8]
        // [1]: [4,2]
        // [2]: [9,7] 이런 식으로...
        // 인접리스트를 내려가면서, 양 > 늑대일 때만 다음을 탐색

        //[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]
        //{0=[1, 8], 1=[2, 4], 4=[3, 6], 6=[5], 8=[7, 9], 9=[10, 11]} 로 변환
        Map<Integer, List<Integer>> list = new HashMap<>();
        for (int[] arr : edge) {
            if (list.get(arr[0]) == null) {
                list.put(arr[0], new ArrayList<>());
            }
            list.get(arr[0]).add(arr[1]);
        }



        boolean[] visited = new boolean[info.length];
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                backtracking(i, info, list, visited);
            }
        }

        System.out.println("sheep:"+sheep+"/visited:"+Arrays.toString(visited));
        return sheep;
    }

    public static void backtracking(int index, int[] info, Map<Integer, List<Integer>> list, boolean[] visited) {

        visited[index] = true;
        if (info[index] == 0) {
            sheep++;
            System.out.println(index+"양이다!");
        } else {
            System.out.println(index+"늑대다!");
            if (list.get(index) == null || sheep <= wolf) {
                return;
            } else {
                wolf++;
            }
        }
        System.out.println("sheep: "+sheep +", wolf:"+wolf);

        if (list.get(index) == null) {
            return;
        } else {
            //더 진행해
            for (int i : list.get(index)) {
                backtracking(i, info, list, visited);
            }
        }
    }

    public static void main(String[] args) {
        sheepAndWolf(new int[]{0,0,1,1,1,0,1,0,1,0,1,1}, new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}});
        sheep = 0;
        wolf = 0;
        System.out.println("-------------------------");
        sheepAndWolf(new int[]{0,1,0,1,1,0,1,0,0,1,0}, new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}});
    }
}