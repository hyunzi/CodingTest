package com.liveclass.codingtest.w4;

import java.util.*;

public class W4SharedTaxiFare {

    /* https://school.programmers.co.kr/learn/courses/30/lessons/72413 */

    /*
     * 1. N 개의 노드와 노드 사이의 간선에 적용되는 가중치가 제공된다
     * 2. 출발지에서 두 도착지를 모두 방문하는 최저 요금(=가중치)를 계산해야 한다
     * 3. N개의 노드는 1~N 으로 숫자가 표시된다
     * 4. 이동 방향에 따라 가중치가 달라지지 않는다
     * 5. 주어지는 값: N(3~200), 출발지, 도착지1, 도착지2, 가중치 리스트
     * 6. 출도착지는 서로 겹치지 않는다
     * 7. 가중치 리스트의 형태는 [c,d,f]로 c와 d사이가 f 가중치라는 것
     * */

    /*
     * 최단 거리가 아닌 가중치 리스트에 따라 최소 비용 거리를 세야 한다
     * 현재 위치에서 가장 적은 가중치를 가진 쪽으로 이동해 나가야 할까?
     *
     * 다익스트라의 원리.
     * 모든 노드를 INT MAX 값으로 설정 후, 시작점만 0으로 설정
     * 가장 작은 라벨 가져와서, 사용처리 하고 인접점 업데이트
     * 끝까지 업데이트 하고 나면, 시작점에서 각 지점까지의 최단거리가 나옴
     *
     * 3개의 지점에서 x까지 도달하는 최소 비용을 구한다 (다익스트라)
     * 이 합이 최소가 되는 경우를 구하면 된다!
     * */


    static int[][] graph = null;
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        graph = new int[n+1][n+1];
        for (int[] fare : fares) {
            graph[fare[0]][fare[1]] = fare[2];
            graph[fare[1]][fare[0]] = fare[2];
        }

/*
        for(int[] fare : graph)
            System.out.println(Arrays.toString(fare));
*/

        for (int x = 1; x <= n; x++) {

            int distX = dijkstra(s, x);
            int distA = dijkstra(a, x);
            int distB = dijkstra(b, x);

            answer = Math.min(distA+distB+distX, answer);
            /*
            System.out.println("distA = " + distA);
            System.out.println("distB = " + distB);
            System.out.println("distX = " + distX);
            System.out.println("answer = " + answer)
            */;
        }


        return answer;
    }

    public static int dijkstra(int start, int end) {

        int[] distance = new int[graph.length];
        Arrays.fill(distance, 100000);
        distance[start] = 0; //[max, 0, max, max, max, max, max]


        Queue<Entry> pq = new PriorityQueue<>();
        pq.add(new Entry(start, 0)); //시작 노드, 시작 비용(0)

        while (!pq.isEmpty()) {
            Entry currNode = pq.remove(); // 큐에서 꺼낸 노드와 연결된 노드들을 살펴본다
            if (distance[currNode.node] < currNode.distance) continue; //꺼낸 노드에 저장된 값 보다, 꺼낸 노드 비용이 더 크면?

            int[] edge = graph[currNode.node];
            for (int i = 0; i < edge.length; i++) {
                int dist = edge[i];
                if (dist != 0) {
                    int newDist = dist + currNode.distance;
                    if (newDist < distance[i]) {
                        distance[i] = newDist;
                        pq.add(new Entry(i, newDist));
                    }
                }

            }

        }

        return distance[end];
    }

    private static class Edge {
        private int to;
        private int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return to+","+distance;
        }
    }

    private static class Entry implements Comparable<Entry> {
        private int node;
        private int distance;

        public Entry(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Entry o) {
            return this.distance - o.distance;
        }

        @Override
        public String toString() {
            return node+","+distance;
        }
    }

    public static void main(String[] args) {

        int result = solution(6, 4, 6, 2,
                new int[][]{{4, 1, 10}
                        , {3, 5, 24}
                        , {5, 6, 2}
                        , {3, 1, 41}
                        , {5, 1, 24}
                        , {4, 6, 50}
                        , {2, 4, 66}
                        , {2, 3, 22}
                        , {1, 6, 25}});


/*
        int result = solution(7,3,4,1,
                new int[][]{{5,7,9}
                        , {4,6,4}
                        , {3,6,1}
                        , {3,2,3}
                        , {2,1,6}});
*/
/*

        int result = solution(6,4,5,6,
                new int[][]{{2,6,6}
                        , {6,3,7}
                        , {4,6,7}
                        , {6,5,11}
                        , {2,5,12}
                        , {5,3,20}
                        , {2,4,8}
                        , {4,3,9}});
*/

        System.out.println(result);
    }

}
