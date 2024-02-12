package com.liveclass.codingtest.w4;

import java.util.*;

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
    * 6. 근데 이렇게 하면… 50000*50000 = 2,500,000,000 (25억번..?!)
    * 7. 다익스트라로 출입구에서 모든 노드로의 최소 가중치 거리를 구한 후,
    * 도착 노드가 산봉우리이고 중간에 다른 출입구나 산봉우리가 없는 경로 중에 최소 intensity 를 가지는 것을 구하면?!
    *
    * */

    static Map<Integer, List<Edge>> map = new HashMap<>();
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        
        /* 1. 인접 리스트로 만들어보자 (2에서 {3까지는 5}, {4까지는 2} 필요) */
        for (int[] path : paths) {
            map.putIfAbsent(path[0], new ArrayList<>());
            map.putIfAbsent(path[1], new ArrayList<>());
            map.get(path[0]).add(new Edge(path[1], path[2]));
            map.get(path[1]).add(new Edge(path[0], path[2]));
        }
        //System.out.println("map = " + map);
        for (int gate : gates) {
            dijkstra(n, gate, gates, summits);
        }
        
        return answer;
    }

    /* 다익스트라의 원리.
     * 모든 노드를 INT MAX 값으로 설정 후, 시작점만 0으로 설정
     * 가장 작은 라벨 가져와서, 사용처리 하고 인접점 업데이트
     * 끝까지 업데이트 하고 나면, 시작점에서 각 지점까지의 최단거리가 나옴
     */
    private static int[] dijkstra(int n, int start, int[] gates, int[] summits) {
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        Queue<Entry> pq = new PriorityQueue<>(); //Min heap
        pq.add(new Entry(start, 0));
        Map<Integer, List<Integer>> paths = new HashMap<>();

        while (!pq.isEmpty()) {
            Entry curr = pq.remove();
            //paths 잘 구해진당! 다만 돌아와서 다시 구하는 경우를 잘 봐야함~

            if (map.containsKey(curr.node)) {
                for (Edge edge : map.get(curr.node)) {
                    int newCost = curr.cost + edge.cost;
                    if (newCost < costs[edge.node]) { //여기서 코스트의 합이 아니라.. 지금까지 온 길의 maxCost가 더 작은걸 봐야할듯..?
                        costs[edge.node]= curr.cost + edge.cost;
                        pq.add(new Entry(edge.node, newCost));

                        paths.putIfAbsent(edge.node, new ArrayList<>());
                        if (paths.containsKey(curr.node)) {
                            for (int prevNode : paths.get(curr.node)) {
                                if (prevNode == start) {
                                    paths.remove(edge.node);
                                    paths.putIfAbsent(edge.node, new ArrayList<>());
                                }
                                paths.get(edge.node).add(prevNode);
                            }
                        }
                        paths.get(edge.node).add(curr.node);
                    }
                }
            }
        }

        /* 도착 노드가 산봉우리이고 중간에 다른 출입구나 산봉우리가 없는 경로 중에 최소 intensity 를 가지는 것을 구하면?!*/
        for (int summit : summits) {
            //다른출입구나 산봉우리 있는지 검사
            int maxCost = 0;
            int prevCost = 0;
            for (int path : paths.get(summit)) {
                System.out.print(path+"("+(costs[path]-prevCost)+") > ");
                maxCost = Math.max(maxCost,costs[path]-prevCost);
                prevCost = costs[path];
            }
            maxCost = Math.max(maxCost,costs[summit]-prevCost);
            System.out.println(summit+"("+(costs[summit]-prevCost)+") / max:"+maxCost);
        }
        //System.out.println("costs = " + Arrays.toString(costs));

        return costs;
    }

    private static class Edge {
        int node;
        int cost;
        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        @Override
        public String toString() {
            return "n:" + node + ",c:" + cost;
        }
    }

    private static class Entry implements Comparable<Entry> {
        int node;
        int cost;
        int maxCost;
        public Entry(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        @Override
        public int compareTo(Entry o) {
            //return this.cost - o.cost;
            return Integer.compare(this.cost, o.cost);
        }
        @Override
        public String toString() {
            return "n:" + node + "/c:" + cost;
        }
    }

    public static void main(String[] args) {

        int[] result1 = solution(6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1, 3}, new int[]{5});
        System.out.println("result1 = " + Arrays.toString(result1)); // 5,3
//        int[] result2 = solution(7, new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}}, new int[]{1}, new int[]{2, 3, 4});
//        System.out.println("result2 = " + Arrays.toString(result2)); // 3,4
//        int[] result3 = solution(7, new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}}, new int[]{3, 7}, new int[]{1, 5});
//        System.out.println("result3 = " + Arrays.toString(result3)); // 5,1
//        int[] result4 = solution(5, new int[][]{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}}, new int[]{1, 2}, new int[]{5});
//        System.out.println("result4 = " + Arrays.toString(result4)); // 5,6
    }
}
