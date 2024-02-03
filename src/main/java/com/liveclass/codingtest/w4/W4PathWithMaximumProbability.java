package com.liveclass.codingtest.w4;

import java.util.*;

public class W4PathWithMaximumProbability {

    /* https://leetcode.com/problems/path-with-maximum-probability/description/ */

    /*
    * n개의 노드를 갖는 무방향 그래프가 주어진다
    * 가중치 리스트도 따로 제공된다
    * n=3, edge=[[0,1], [1,2], [0,2]], succProb=[0.5, 0.5, 0.2], s=0, e=2
    * 최대 성공 확률을 구해야 한다... 따라서 max heap 을 사용해야 한다
    *
    * 다익스트라. 모든 노드를 max로 설정한 후 시작점만 0으로 설정
    * 가장 큰 max heap 의 노드를 하나 가져와서, 사용처리하고, 인접 노드의 값 업데이트
    * 끝까지 업데이트 하면 시작~종료 지점의 최대가 나오지 않을까?
    *
    * */


    public static void main(String[] args) {

        double result = maxProbability(3, new int[][]{{0,1}, {1,2}, {0,2}}, new double[]{0.5, 0.5, 0.2}, 0, 2);
        System.out.println("result = " + result);

    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {

        double result = 0;

        //내가 쓰기 편한 맵으로 만든다 (무향 그래프)
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i = 0; i < edges.length; i++) {
            graph.putIfAbsent(edges[i][0], new ArrayList<>()); //0, 0 / 1, 1 / 2, 0
            graph.putIfAbsent(edges[i][1], new ArrayList<>()); //0, 1 / 1, 2 / 2, 2

            graph.get(edges[i][0]).add(new Edge(edges[i][1], succProb[i]));
            graph.get(edges[i][1]).add(new Edge(edges[i][0], succProb[i]));

        }

        //시작지점을 우선순위 큐에 넣는다
        Queue<Entry> queue = new PriorityQueue<>();
        queue.add(new Entry(start_node, 1.0));
        double[] probability = new double[n];

        //확률을 기준(맥스힙)으로 다익스트라를 진행한다
        while (!queue.isEmpty()) {
            Entry curr = queue.remove();
            if (probability[curr.node] > curr.distance) continue;
            if (!graph.containsKey(curr.node)) continue;

            for (Edge edge : graph.get(curr.node)) {
                double nextProb = curr.distance * edge.distance;
                if (probability[edge.node] < nextProb) {
                    probability[edge.node] = nextProb;
                    queue.add(new Entry(edge.node, nextProb));
                }
            }
        }

        //종료노드의 확률 값을 반환한다
        return probability[end_node];
    }

    private static class Edge {
        private int node;
        private double distance;

        public Edge(int node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return node+","+distance;
        }
    }

    private static class Entry implements Comparable<Entry> {
        private int node;
        private double distance;

        public Entry(int node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Entry o) {
            return (o.distance == this.distance) ? 0 : ((o.distance < this.distance) ? -1 : 1);
        }

        @Override
        public String toString() {
            return node+","+distance;
        }
    }
}
