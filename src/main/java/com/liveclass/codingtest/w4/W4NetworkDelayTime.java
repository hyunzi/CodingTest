package com.liveclass.codingtest.w4;

import java.util.*;
import java.util.stream.Collectors;

public class W4NetworkDelayTime {

    /* https://leetcode.com/problems/network-delay-time/description/ */
    /*
    * 시작점으로부터 모든 노드에 도착하기 위한 최소시간
    * 각 edge는 [시작점, 도착점, 가중치] 형태다
    *
    * n개의 노드가 주어진다. 그리고, 각 노드 사이의 가중치가 배열로 주어진다
    * k 노드에 신호를 보냈을 때 모든 노드에 도착하는 최소시간
    * k 노드로부터 n 개의 노드에 도착하는 시간의 최댓값을 구하면 될 듯
    *
    * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
    * Output: 2
    * Input: times = [[1,2,1]], n = 2, k = 1
    * Output: 1
    * Input: times = [[1,2,1]], n = 2, k = 2
    * Output: -1
    * */

    public static void main(String[] args) {
        int result = networkDelayTime(new int[][]{{2,1,1}, {2,3,1}, {3,4,1}}, 4,2);
        System.out.println(result);
        result = networkDelayTime(new int[][]{{1,2,1}}, 2,1);
        System.out.println(result);
        result = networkDelayTime(new int[][]{{1,2,1}}, 2,2);
        System.out.println(result);
    }
    public static int networkDelayTime(int[][] times, int n, int k) {

        //1. 방향 그래프로 만들기
        Map<Integer, List<int[]>> edges = Arrays.stream(times)
                .collect(Collectors.groupingBy(t -> t[0]));
        //이렇게 하면 {2,{1,3}}, {3,{4}} 이런식으로 됨..

        int[] visited = new int[n+1];
        Arrays.fill(visited, Integer.MAX_VALUE);

        //2. 큐에 정점까지 가는 거리를 넣기
        Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        pq.add(new int[]{k,0}); // k에서 시작할거다
        visited[k] = 0;

        //3. k로부터 모든 노드들의 최단거리 구하기 -> 다익스트라 알고리즘 수행
        int maxTime = 0;
        int visitCount = 1;
        while (!pq.isEmpty()) {
            int[] cur = pq.remove();
            int u = cur[0];
            int time = cur[1];
            if (visited[u] < time) continue;
            maxTime = time;

            //연결된 모든 간선을 탐색한다.
            if (!edges.containsKey(u)) continue;
            for (int[] edge : edges.get(u)) {
                int v = edge[1], w = edge[2];
                //이미 더 짧은 거리로 방문한 적이 있는 경우 건너뛴다.
                if (time + w >= visited[v]) continue;

                //처음 방문했다면 visitCount
                if (visited[v] == Integer.MAX_VALUE) visitCount++;
                visited[v] = time + w;
                pq.add(new int[] {v, time + w});
            }
        }
        return visitCount == n ? maxTime : -1;
    }
}
