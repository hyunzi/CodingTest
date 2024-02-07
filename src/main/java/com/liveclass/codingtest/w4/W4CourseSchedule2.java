package com.liveclass.codingtest.w4;

import java.util.*;

public class W4CourseSchedule2 {

    /* https://leetcode.com/problems/course-schedule-ii/description/ */

    /*
    * 위상정렬을 이용해야함!
    * 그리고 유효한 수강 순서를 출력하는 것이 목표~!
    * */

    //[a,b] = a를 들으려면 b를 먼저들어라
    //0,[1,2]
    //1,[2,3] 이런 식으로 먼저 만들자

    //위상정렬의 순서!
    //진입차수가 0인 노드들을 모두 큐에 넣는다
    //0인 노드와 인접한 노드에서 차수를 하나씩 뺀다
    //그 다음 진입차수가 0인 노드들을 넣는다
    //큐가 비게 되면 끝!

    public static int[] findOrder(int numCourses, int[][] prerequisites) {


        Map<Integer, List<Integer>> courses = new HashMap<>();
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            courses.putIfAbsent(prerequisite[1], new ArrayList<>());
            courses.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }
        System.out.println("courses = " + courses);

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int cnt = 0;
        List<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            answer.add(curr);
            if (courses.containsKey(curr)) {
                courses.get(curr).forEach(
                        integer -> {
                            if (--indegree[integer] == 0) {
                                queue.add(integer);
                            }
                        }
                );
            }
        }

        if (answer.size() != numCourses)
            return new int[]{};
        else
            return answer.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {

        //int[] result = findOrder(4,new int[][]{{1,0},{2,0},{3,1},{3,2}} );
        int[] result = findOrder(2,new int[][]{{1,0},{0,1}});
        System.out.println("result = " + Arrays.toString(result));

    }
}
