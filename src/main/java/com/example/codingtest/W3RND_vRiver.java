package com.example.codingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class W3RND_vRiver {

    public static void combination(int start, int n, int r, ArrayList<Integer> arr, ArrayList<List<Integer>> combList) {

        if (arr.size() >= r) {
            combList.add(new ArrayList<>(arr));
            return;
        }

        for (int i = start; i < n; i++) {
            arr.add(i);
            combination(i, n, r, arr, combList);
            arr.remove(Integer.valueOf(i));
        }
    }

    public static void bfs(char[][] board, List<Integer> comb, ArrayDeque<int[]> queue) {
        char[][] copied_board = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(copied_board[i],0,board[i],0, board[i].length);
        }

        int count = 0;
        int n = board.length, m = board[0].length;
        int result = Integer.MAX_VALUE;
        int[][] rcList = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        while(!queue.isEmpty()) {
            int[] currNode = queue.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            int depth = currNode[2];
            for (int[] rc : rcList) {
                int nextRow = currRow + rc[0];
                int nextCol = currCol + rc[1];
                int nextDepth = depth + 1;
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
                    if (copied_board[nextRow][nextCol] == 0) {
                        copied_board[nextRow][nextCol] = 3;
                        count = count < nextDepth ? nextDepth : count;
                        queue.add(new int[]{nextRow, nextCol, nextDepth});
                    } else if (copied_board[nextRow][nextCol] == 2) {
                        copied_board[nextRow][nextCol] = 3;
                        queue.add(new int[]{nextRow, nextCol, nextDepth});
                    }
                }
            }
        }

        for (char[] copied : copied_board) {
            for (char c : copied) {
                if (c == '0') return;
            }
        }

        result = (result == Integer.MAX_VALUE) ? -1 : result;
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int mapLength = Integer.parseInt(line[0]);
        int virusNumber = Integer.parseInt(line[1]);

        char[][] graph = new char[mapLength][mapLength];
        ArrayList<int[]> virusList = new ArrayList<>();
        for (int r = 0; r < mapLength; r++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int c = 0; c < mapLength; c++) {
                graph[r][c] = st.nextToken().charAt(0);
                if (graph[r][c] == '2') virusList.add(new int[]{r, c});
            }
        }

        ArrayList<List<Integer>> combList = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        combination(0, virusList.size(), virusNumber, arr, combList);

        for (List<Integer> comb : combList) {
            ArrayDeque<int[]> queue = new ArrayDeque<>();

            for (Integer k : comb) {
                int r = virusList.get(k)[0];
                int c = virusList.get(k)[1];
                graph[r][c] = 3;
                queue.add(new int[]{r, c, 0});
            }
            bfs(graph, comb, queue);
            for (Integer k : comb) {
                int r = virusList.get(k)[0];
                int c = virusList.get(k)[1];
                graph[r][c] = 2;
            }
        }


    }
}
