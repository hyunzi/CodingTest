package com.liveclass.codingtest.w4;

import java.util.*;

public class W4SmallestRange {

    /* SmallestRangeCoveringElementsFromKLists */

    public static int[] smallestRange(List<List<Integer>> nums) {
        Queue<int[]> pQueue = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);

/*      int n0 = nums.get(0).get(0); //배열이 1 ~ 3500개임. 무조건 세 개라고 생각하고 구현하면 안됨
        int n1 = nums.get(1).get(0);
        int n2 = nums.get(2).get(0);

        //큐에 세 값의 "인덱스"를 넣고
        pQueue.add(new int[]{n0, 0});
        pQueue.add(new int[]{n1, 1});
        pQueue.add(new int[]{n2, 2});
*/


        //min-max 범위를 저장
        int result[] = {Integer.MAX_VALUE, 0};
        int range[] = {Integer.MAX_VALUE, 0};

        for (int i = 0; i < nums.size(); i++) {
            pQueue.add(new int[]{nums.get(i).get(0), i});
        }

        range[0] = Math.min(Math.min(n0, n1), n2); //배열이 1 ~ 3500개임.
        range[1] = Math.max(Math.max(n0, n1), n2);


        if (range[0]==range[1]) return range;

        //세 개 중에 최솟값을 뽑고
        while (!pQueue.isEmpty()) {
            //뽑은 값을 가진 리스트의 다음 값을 넣고
            int[] currMin = pQueue.poll();
            int currList = currMin[1];
            int currValIndex = nums.get(currList).indexOf(Integer.valueOf(currMin[0]));
            int nextValIndex = currValIndex+1;

            //현재 min 이었던 리스트의 다음 값을 큐에 넣음
            if (nextValIndex < nums.get(currMin[1]).size()) {
                int newValue = nums.get(currList).get(nextValIndex);
                pQueue.add(new int[]{nums.get(currList).get(nextValIndex), currMin[1]});

                int min = pQueue.peek()[0];
                int max = Math.max(range[1], newValue);
                range[0] = min;
                range[1] = max;
                int nextRange = max - min;
                if (nextRange < (result[1] - result[0])) {
                    result[0] = range[0];
                    result[1] = range[1];
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {

        /*List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(4,10,15,24,26),
            Arrays.asList(0,9,12,20),
            Arrays.asList(5,18,22,30));*/
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(1,2,3),
                Arrays.asList(1,2,3));
        int[] result = smallestRange(nums);
        System.out.println(Arrays.toString(result));

    }
}
