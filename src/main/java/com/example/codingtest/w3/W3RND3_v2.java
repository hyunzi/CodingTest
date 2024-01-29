package com.example.codingtest.w3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class W3RND3_v2 {

    public static void combination(int start, int n, int r, ArrayList<Integer> arr, ArrayList<List<Integer>> combList) {

        if (arr.size() >= r) {
            combList.add(new ArrayList<>(arr));
            return;
        }

        for (int i = start; i < n; i++) {
            arr.add(i);
            combination(i+1, n, r, arr, combList);
            arr.remove(Integer.valueOf(i));
        }
    }

    static char[][] board = null;
    static int result = Integer.MAX_VALUE;
    static int[][] rcList = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static ArrayList<int[]> virusList = null;
    public static void bfs(List<Integer> comb) {
        int n = board.length;
        char[][] copied_board = new char[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i],0,copied_board[i],0, n);
        }

        int count = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int c : comb) {
            queue.add(new int[]{virusList.get(c)[0],virusList.get(c)[1], 0});
        }

        while(!queue.isEmpty()) {
            int[] currNode = queue.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];
            int depth = currNode[2];
            for (int[] rc : rcList) {
                int nextRow = currRow + rc[0];
                int nextCol = currCol + rc[1];
                int nextDepth = depth + 1;
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                    if (copied_board[nextRow][nextCol] == '0') {
                        copied_board[nextRow][nextCol] = '3';
                        count = Math.max(count, nextDepth);
                        queue.add(new int[]{nextRow, nextCol, nextDepth});
                    } else if (copied_board[nextRow][nextCol] == '2') {
                        copied_board[nextRow][nextCol] = '3';
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

        result = Math.min(result, count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int mapLength = Integer.parseInt(line[0]);
        int virusNumber = Integer.parseInt(line[1]);

        board = new char[mapLength][mapLength];
        virusList = new ArrayList<>();
        for (int r = 0; r < mapLength; r++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int c = 0; c < mapLength; c++) {
                board[r][c] = st.nextToken().charAt(0);
                if (board[r][c] == '2') virusList.add(new int[]{r, c});
            }
        }

        ArrayList<List<Integer>> combList = new ArrayList<>();
        ArrayList<Integer> arr = new ArrayList<>();
        combination(0, virusList.size(), virusNumber, arr, combList);

        for (List<Integer> comb : combList) {
            for (Integer k : comb) {
                int r = virusList.get(k)[0];
                int c = virusList.get(k)[1];
                board[r][c] = '3';
            }
            bfs(comb);
            for (Integer k : comb) {
                int r = virusList.get(k)[0];
                int c = virusList.get(k)[1];
                board[r][c] = '2';
            }
        }

        System.out.println((result == Integer.MAX_VALUE) ? -1 : result);
    }
}
