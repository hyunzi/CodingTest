package com.liveclass.codingtest.w4;

import java.util.*;

public class W4CourseSchedule {

    /* https://leetcode.com/problems/course-schedule/description/ */
    /*
    * numCourses 갯수는 들어야 할 강좌
    * 먼저 수강해야 하는 강의가 배열로 제공됨
    * prerequisites[i] = [a,  b] .... << 이는 b를 들으려면 a를 들어야함을 나타낸다.
    * 모든 과정을 이수할 수 있으면 true 아니면 false
    * 우선순위 큐를 사용하여 해결할 방법은?
    * numCourses = 3, prerequisites = [[0,1], [1,3], [2,4], [5,4], [0,2]]
    * 0-> 1,2,5-> 3,4
    * */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean result = false;

        //과목 간 선수강 조건이 A->B, B->A 가 되면 안된다
        //위상정렬: 사이클이 없는 방향 그래프의 모든 노드를 방향성을 거스르지 않도록 순서대로 나열하는 데 사용
        //진입차수/진출차수가 있음.
        //진입차수가 0인 그러니까 나를 가르키는 모든 노드를 큐에 삽입한다
        //큐에서 노드를 하나 꺼내 출발하는 모든 간선을 제거. 이로 인해 진입차수가 0이된 모든 노드를 큐에 삽입
        //같은 레벨에서 진입차수 0인걸 확인할 수 있다
        //위상정렬의 결과가 총 numCourses 만큼만 나오면 된다

        //1. 입력을 방향 그래프로 저장
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses]; // 진입차수를 뜻하는 단어
        for (int[] edge : prerequisites) {
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }

        //2. 위상정렬
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[numCourses];
        int count = 0;
        for (int c = 0; c < numCourses; c++) {
            if (indegree[c] == 0) {
                queue.add(c);
                visited[c] = true;
                count++;
            }
        }




        System.out.println(graph);
        return result;
    }

    public static void main(String[] args) {
        canFinish(2, new int[][]{{0,1}, {1,3}, {2,4}, {5,4}, {0,2}});

    }
}
