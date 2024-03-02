package com.liveclass.codingtest.w7;

import java.util.*;

public class W7DivideParkinglot {

    public static int solve(int n, int[] cars, int[][] links) {

        // 문제 이해
        // 연결된 주차장 간선 하나를 제거해서 전체 주차장을 두 개로 나눔
        // 주차가능대수의 차이가 가장 작은 경우를 구하고 그 차이의 절대값을 리턴
        // n = 노드의 개수 (주차장의 개수)
        // cars = 노드당 주차 가능 대수
        // links = 간선 리스트

        // 생각의 흐름
        // BFS 나 DFS 문제가 아닐까?
        // 일단.. 1개의 그룹을 2개로 나누기 위해..모든 간선을 하나씩 끊어봐야하나
        // 최대 99,999개의 경우가 나옴
        // 간선 1을 끊고 나뉜 양쪽 그룹의 합을 각각 구하고 차를 구함
        // 간선 2를 끊고 나뉜 양쪽 그룹의 합을 각각 구하고 차를 구함
        // 차를 Math.min 으로 저장하면? 최소가 되는 경우만 나옴

        // 코드 구현
        // 데이터를 어떤 식으로 저장해야 보기 편할까?
        // 배열 -> 인접리스트로 바꾸자
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            graph.putIfAbsent(links[i][0], new ArrayList<>());
            graph.get(links[i][0]).add(links[i][1]);
            graph.putIfAbsent(links[i][1], new ArrayList<>());
            graph.get(links[i][1]).add(links[i][0]);
        }

        int result = Integer.MAX_VALUE;
        for (int[] link : links) {
            //System.out.println("link = " + Arrays.toString(link));
            //현재 제거할 간선
            if (graph.containsKey(link[0])) {
                graph.get(link[0]).remove(Integer.valueOf(link[1]));
            }
            if (graph.containsKey(link[1])) {
                graph.get(link[1]).remove(Integer.valueOf(link[0]));
            }
            //System.out.println("graph = " + graph);

            //link[0] 에서 출발하는 그래프 합
            int sumLinkA = sumLinkFrom(link[0], cars, graph);
            //link[1] 에서 출발하는 그래프 합
            int sumLinkB = sumLinkFrom(link[1], cars, graph);
            
            //두 그래프의 합의 차를 구하고, 절댓값의 최솟값을 저장함
            result = Math.min(result, Math.abs(sumLinkA-sumLinkB));
            //제거한 간선 다시 넣어줌
            if (graph.containsKey(link[0])) {
                graph.get(link[0]).add(link[1]);
            }
            if (graph.containsKey(link[1])) {
                graph.get(link[1]).add(link[0]);
            }
        }

        return result;
    }

    private static int sumLinkFrom(int n, int[] cars, Map<Integer, List<Integer>> graph) {
        
        int result = 0;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        
        boolean[] visited = new boolean[cars.length];
        visited[n-1] = true;
        
        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            if (graph.containsKey(currNode) && !graph.get(currNode).isEmpty()) {
                for (int k : graph.get(currNode)) {
                    if (!visited[k-1]) {
                        queue.add(k);
                        visited[k-1] = true;
                    }
                }
            }
            result += cars[currNode-1];
        }
        //System.out.println("result = " + result);
        
        return result;
    }

    public static void main(String[] args) {

        int result1 = solve(13, new int[]{22,9,1,15,8,6,20,7,11,5,10,4,1},
                new int[][]{
                {4,7},{13,10},{6,3},{7,1},{6,12},{5,11},{5,6},{5,10},{9,8},{8,11},{8,2},{7,8}
                });
        System.out.println("result1 = " + result1);
        int result2 = solve(6, new int[]{6,4,10,9,8,4},
                new int[][]{{4,1},{3,2},{1,6},{3,5},{5,1}});
        System.out.println("result2 = " + result2);

    }
}
