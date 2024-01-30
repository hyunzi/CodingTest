package com.liveclass.codingtest.w4;

import java.util.*;

public class W4DiskController {

    public static void main(String[] args) {
        int result = solution(new int[][]{{0, 3}, {1, 9}, {2, 6}});
        System.out.println(result);
    }

    public static int solution(int[][] jobs) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return Integer.compare(o1[0], o2[0]);
                }
                return Integer.compare(o1[1], o2[1]);
            }
        });
        for (int[] job : jobs) {
            pq.offer(job);
        }
        int t = 0;
        int n = jobs.length;
        for(int i = 0; i < n; i++) {
            for (int[] q : pq) {
                if (t == q[0]) {
                    t += q[1];
                }
            }
        }
        return answer;
    }
}
