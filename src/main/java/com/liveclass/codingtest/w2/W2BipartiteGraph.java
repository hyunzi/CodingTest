package com.liveclass.codingtest.w2;

import java.util.*;

public class W2BipartiteGraph {
    public static boolean isBipartite(int[][] graph) {
        //이분그래프 판단 방법
        //0번째 노드에서 시작해서, DFS로 탐색
        //0번째를 빨간색으로 칠하고
        //1번째를 파란색으로 칠하고 이렇게
        //빨->파 순으로 칠해감
        //이미 색이 칠해져 있는데, 내가 칠해야 할 색이 다를 때? false

        //문제에서 주어진 건 이미 인접리스트
        //노드 0에서 시작, visited[]에 진행할 노드의 색을 바꾼다
        //그 다음 노드는!
        String[] visited = new String[graph.length];
        bfs(0, graph, visited, "red");
        System.out.println(Arrays.toString(visited));

        boolean result = true;
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == null) result = false;
            if (visited[i-1] == visited[i]) result = false;
        }
        System.out.println(result);
        return result;
    }

    public static void bfs(int currNode, int[][] graph, String[] visited, String type) {

        visited[currNode] = type;
        for(int nextNode : graph[currNode]) {
            if (visited[nextNode] != null) {
                //이미 방문했던 노드
                if (visited[nextNode].equals(type)) {
                    //바꿀 색이랑 같으면? 정상
                    return;
                } else {
                    // 여기서 리턴!!
                    //바꿀 색이랑 다르면? 이분그래프 안됨
                }
            } else {
                //방문하지 않은 노드
                type = (type == "red") ? "blue" : "red"; //반대의 타입을 넣어줌
                bfs(nextNode, graph, visited, type);
            }
        }
    }

    public static void main(String[] args) {

        isBipartite(new int[][]{{1,2},{0,2},{0,1}});
        isBipartite(new int[][]{{4,1},{0,2},{1,3},{2,4},{3,0}});
        isBipartite(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}});
        isBipartite(new int[][]{{1,3},{0,2},{1,3},{0,2}});
    }
}
