package com.example.codingtest.w2;

import java.util.ArrayList;
import java.util.List;

public class W2KeysAndRooms {

    /* https://leetcode.com/problems/daily-temperatures/description/ */

    public static boolean[] visited;
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {

        //rooms 는 0~n 까지 있고, 각 room에는 다른 room을 열 key 가 있음
        //0번 room 부터 갔을 때, 나머지 모든 room을 열 수 있는지 T/F로 리턴해라

        //bfs, dfs로 풀 수 있을듯
        //인접리스트?

        //              [1,3]
        //[3,0,1]                   [0]

        // 0번방 들어감 -> 1번방 들어감 -> 3번방 들어감 -> 0번방 이미 갔던곳이라 나옴
        //              ->              -> 0번방 이미 갔던 곳이라 나옴
        //              ->              -> 1번방 이미 갔던 곳이라 나옴
        //              -> 3번방 이미 갔던 곳이라 나옴
        //              -> dfs 종료됨
        // 0,1,2,3 방 중에 아직 방문하지 못한 2번 방이 있음.. false

        // 0번방 들어감 -> 1번방 들어감 -> 2번방 들어감 -> 3번방 들어감 -> 암것도 없음 -> 모두 나와서 종료

        visited = new boolean[rooms.size()];
        dfs(rooms, 0);

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(List<List<Integer>> rooms, int curVertex) {
        visited[curVertex] = true;
        for(Integer nextVertex : rooms.get(curVertex)) {
            if (visited[nextVertex] == false) {
                dfs(rooms, nextVertex);
            }
        }
    }
    public static void main(String[] args) {
        /*List<List<Integer>> list = new ArrayList<List<Integer>>() {{
            add(new ArrayList<>(){{ add(1); add(3);}});
            add(new ArrayList<>(){{ add(3); add(0); add(1);}});
            add(new ArrayList<>(){{ add(2);}});
            add(new ArrayList<>(){{ add(0);}});
        }};*/

        List<List<Integer>> list = new ArrayList<List<Integer>>() {{
            add(new ArrayList<>(){{ add(1); }});
            add(new ArrayList<>(){{ add(2);}});
            add(new ArrayList<>(){{ add(3);}});
            add(new ArrayList<>(){{ }});
        }};
        boolean result = canVisitAllRooms(list);
        System.out.println(result);
    }
}
