package com.liveclass.codingtest.w4;

import java.util.*;

public class W4PathWithMaximumProbability {

    /* https://leetcode.com/problems/path-with-maximum-probability/description/ */

    /*
    * n개의 노드를 갖는 무방향 그래프가 주어진다
    * 가중치 리스트도 따로 제공된다
    * n=3, edge={{0,1}, {1,2}, {0,2}}, succProb={0.5, 0.5, 0.2}, s=0, e=2
    * 최대 성공 확률을 구해야 한다... 따라서 max heap 을 사용해야 한다
    *
    * 다익스트라.
    * 지점 A에서 B까지 가는 최소(최대) 비용을 구하는 방법
    * 노드 수 크기의 배열을 만들고 모든 노드를 max로 설정한 후 시작점만 0으로 설정
    * 가장 가중치가 큰 max heap 의 노드를 하나 가져와서, 사용처리하고, 인접 노드의 값을 가중치 + 둘 사이의 거리로 업데이트
    * 끝까지 업데이트 하면 시작~종료 지점의 최대가 나오지 않을까?
    *
    * */

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {

        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(new Edge(edge[1], succProb[i]));
            graph.get(edge[1]).add(new Edge(edge[0], succProb[i]));
        }

        double[] probabilities = new double[n];
        Queue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(start_node, 1.0)); //시작 좌표를 넣고, 여기로 부터 인접한 노드들 중 가장 우선순위가 높은 곳!
        probabilities[start_node] = 1.0;

        while(!pq.isEmpty()) {
            Entry curr = pq.remove();

            if (probabilities[curr.node] > curr.prob) continue;
            if (!graph.containsKey(curr.node)) continue;

            for (Edge edge : graph.get(curr.node)) {
                double nextProb = curr.prob * edge.weight;
                if (probabilities[edge.node] < nextProb) {
                    probabilities[edge.node] = nextProb;
                    pq.add(new Entry(edge.node, nextProb));
                }
            }

        }
        return probabilities[end_node];
    }

    private static class Edge {
        int node;
        double weight;
        public Edge(int node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    private static class Entry implements Comparable<Entry> {
        int node;
        double prob;

        public Entry(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }

        @Override
        public int compareTo(Entry o) { //현재 객체 < 파라미터로 넘어온 객체 → 음수 리턴 (오름차순)
                                        //                              → 양수 리턴 (내림차순)
            return (this.prob == o.prob) ? 0 : (o.prob < this.prob) ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        double result = maxProbability(3, new int[][]{{0,1}, {1,2}, {0,2}}, new double[]{0.5, 0.5, 0.2}, 0, 2);
        System.out.println("result = " + result);
    }
}
