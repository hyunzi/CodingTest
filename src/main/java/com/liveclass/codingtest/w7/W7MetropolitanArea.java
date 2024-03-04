package com.liveclass.codingtest.w7;

import java.util.*;

public class W7MetropolitanArea {

    /*문제 분석
     * 1. 노드와 가중치가 있는 간선이 주어짐
     * 2. 일부 노드는 수도이고, 여러 개의 수도가 있다 (capitals)
     * 3. 각 도시에서 출발하여 수도까지 k 시간 이하로 걸리면 수도권 도시
     * 4. 수도권 도시의 번호들을 오름차순으로 정렬하여 리턴하기
     *
     *
     * 생각의 흐름
     * 1. 전형적인 Dijkstra 문제구나!
     * 2. 수도는 최대 49,999개
     * 3. 두 도시 사이에 도로가 여러 개 있을 수 있다?! -> 최단 거리 선택해야함
     * 4. 수도를 모두 돌면서, 나머지 노드까지의 최단 거리를 구함.
     * 5. 이미 수도권이 된 도시나, 수도는 제외하고 최단 거리를 구함
     *
     *
     * 코드 구현
     * 1. edges 를 인접행렬 또는 인접리스트로 변환
     * 2. capitals 를 돌며 n 까지의 다익스트라
     * 3. 1~n 까지의 노드 중, capitals 에 포함되거나 수도권에 포함된 노드는 탐색 제외
     * 4. 수도권에 포함된 노드의 리스트 오름차순 정렬
     *
     * */
    static Map<Integer, List<Edge>> map;

    public static int[] solve(int n, int k, int[] capitals, int[][] edge) {
        map = new HashMap<>();
        for (int[] e : edge) {
            map.putIfAbsent(e[0], new ArrayList<>());
            map.get(e[0]).add(new Edge(e[1], e[2]));
            map.putIfAbsent(e[1], new ArrayList<>());
            map.get(e[1]).add(new Edge(e[0], e[2]));
        }

        HashSet<Integer> metropolitan = new HashSet<>();
        for (int capital : capitals) {
            dijkstra(capital, n, k, metropolitan);
        }
        for (int capital : capitals) {
            metropolitan.remove(Integer.valueOf(capital));
        }
        //metropolitan.stream().sorted();
        return metropolitan.stream().mapToInt(Number::intValue).toArray();
    }

    private static void dijkstra(int capital, int n, int k, HashSet<Integer> metropolitan) {

        int[] dijkstra = new int[n + 1];
        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        dijkstra[capital] = 0;

        Queue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(capital, 0));

        while (!pq.isEmpty()) {
            Entry curr = pq.poll();
            if (dijkstra[curr.node] < curr.time) continue;

            for (Edge edge : map.get(curr.node)) {
                if (metropolitan.contains(Integer.valueOf(edge.node))) continue;

                int newTime = curr.time + edge.time;
                if (newTime < dijkstra[edge.node]) {
                    dijkstra[edge.node] = newTime;
                    if (dijkstra[edge.node] <= k) {
                        metropolitan.add(edge.node);
                    }
                    pq.add(new Entry(edge.node, newTime));
                }
            }
        }

    }

    private static class Edge {
        int node;
        int time;

        public Edge(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    private static class Entry implements Comparable<Entry> {
        int node;
        int time;

        public Entry(int node, int time) {
            this.node = node;
            this.time = time;
        }

        @Override
        public int compareTo(Entry o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) {
        int[] result1 = solve(13, 5, new int[]{1, 9}, new int[][]{
                {1, 2, 3}, {2, 4, 4}, {3, 2, 1}, {1, 6, 6}, {1, 5, 6}, {1, 7, 6},
                {6, 7, 2}, {5, 7, 5}, {7, 8, 2}, {9, 7, 3}, {9, 10, 6}, {9, 11, 3}, {11, 12, 2}, {11, 13, 4}
        });
        System.out.println("result1 = " + Arrays.toString(result1));

        int[] result2 = solve(7, 10, new int[]{2}, new int[][]{
                {1, 2, 11}, {1, 5, 1}, {2, 4, 5}, {5, 4, 4}, {4, 3, 7}, {4, 6, 8}, {4, 7, 3}, {6, 7, 3}
        });
        System.out.println("result2 = " + Arrays.toString(result2));
    }
}
