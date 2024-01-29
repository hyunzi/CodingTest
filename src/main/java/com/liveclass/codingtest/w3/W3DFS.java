package com.liveclass.codingtest.w3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class W3DFS{

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0,new ArrayList<>());
        graph.put(1,new ArrayList<>());
        graph.put(2,new ArrayList<>());
        graph.put(3,new ArrayList<>());
        graph.get(0).add(2);
        graph.get(0).add(3);
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(0);
        graph.get(2).add(1);
        graph.get(3).add(0);

        Map<Integer, Boolean> visited = new HashMap<>();
        dfs(0, graph, visited);
        System.out.println(visited);

    }


    public static void dfs(int curVertex, Map<Integer, List<Integer>> graph, Map<Integer, Boolean> visited) {

        visited.put(curVertex, true);
        for(int nextVertex : graph.get(curVertex)) {
            if (!visited.containsKey(nextVertex)) {
                System.out.println(curVertex+" -> "+nextVertex);
                dfs(nextVertex, graph, visited);
            }
        }

    }

}
