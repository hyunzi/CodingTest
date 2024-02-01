package com.liveclass.codingtest.w4;

import java.util.*;

public class W4SmallestRange {

    /* Smallest Range Covering Elements From K Lists */

    public static int[] smallestRange(List<List<Integer>> nums) {

        int result[] = {Integer.MIN_VALUE,Integer.MAX_VALUE};

        //min-max 범위를 저장
        int range[] = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        Queue<int[]> pQueue = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);

        for (int i = 0; i < nums.size(); i++) {
            pQueue.add(new int[]{nums.get(i).get(0), i, 0});
            range[0] = Math.min(nums.get(i).get(0),range[0]);
            range[1] = Math.max(nums.get(i).get(0),range[1]);
        }
        result[0] = range[0];
        result[1] = range[1];
        if (result[0] == result[1]) return result;

        //세 개 중에 최솟값을 뽑고
        while (!pQueue.isEmpty()) {
            //뽑은 값을 가진 리스트의 다음 값을 넣고
            int[] currMin = pQueue.poll();
            int currList = currMin[1];
            //이렇게 가져오면 같은 값이 두 개 있을 때 문제가 생기니 그냥 인덱스 저장하자!
            //int currValIndex = nums.get(currList).indexOf(Integer.valueOf(currMin[0]));
            int nextValIndex = currMin[2]+1;

            //현재 min 이었던 리스트의 다음 값을 큐에 넣음
            if (nextValIndex < nums.get(currMin[1]).size()) {
                int newValue = nums.get(currList).get(nextValIndex);
                pQueue.add(new int[]{nums.get(currList).get(nextValIndex), currMin[1], nextValIndex});
                range[0] = pQueue.peek()[0];
                range[1] = Math.max(range[1], newValue);
                if ((range[1] - range[0]) < (result[1] - result[0])) {
                    result[0] = range[0];
                    result[1] = range[1];
                }
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        /*
        List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(4,10,15,24,26),
            Arrays.asList(0,9,12,20),
            Arrays.asList(5,18,22,30));

        */
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(1,2,3),
                Arrays.asList(1,2,3));

        /*
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(10,10),
                Arrays.asList(11,11));
        */
        /*
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(10),
                Arrays.asList(11));
        */
        /*
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(-5,-4,-3,-2,-1),
                Arrays.asList(1,2,3,4,5));
        */
        /*
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(1,3,5,7,9),
                Arrays.asList(2,4,6,8,10));
        */
        /*
        List<List<Integer>> nums = Arrays.asList(
            Arrays.asList(11,38,83,84,84,85,88,89,89,92),
            Arrays.asList(28,61,89),
            Arrays.asList(52,77,79,80,81),
            Arrays.asList(21,25,26,26,26,27),
            Arrays.asList(9,83,85,90),
            Arrays.asList(84,85,87),
            Arrays.asList(26,68,70,71),
            Arrays.asList(36,40,41,42,45),
            Arrays.asList(-34,21),
            Arrays.asList(-28,-28,-23,1,13,21,28,37,37,38),
            Arrays.asList(-74,1,2,22,33,35,43,45),
            Arrays.asList(54,96,98,98,99),
            Arrays.asList(43,54,60,65,71,75),
            Arrays.asList(43,46),
            Arrays.asList(50,50,58,67,69),
            Arrays.asList(7,14,15),
            Arrays.asList(78,80,89,89,90),
            Arrays.asList(35,47,63,69,77,92,94));
        */
        /*
        List<List<Integer>> nums = Arrays.asList(
                Arrays.asList(-1,1),
                Arrays.asList(-2,2),
                Arrays.asList(-3,3),
                Arrays.asList(-4,4),
                Arrays.asList(-5,5),
                Arrays.asList(-6,6),
                Arrays.asList(-7,7),
                Arrays.asList(-8,8),
                Arrays.asList(-9,9),
                Arrays.asList(-10,10),
                Arrays.asList(-11,11),
                Arrays.asList(-12,12),
                Arrays.asList(-13,13),
                Arrays.asList(-14,14),
                Arrays.asList(-15,15),
                Arrays.asList(-16,16),
                Arrays.asList(-17,17),
                Arrays.asList(-18,18),
                Arrays.asList(-19,19),
                Arrays.asList(-20,20),
                Arrays.asList(-21,21),
                Arrays.asList(-22,22),
                Arrays.asList(-23,23),
                Arrays.asList(-24,24),
                Arrays.asList(-25,25),
                Arrays.asList(-26,26),
                Arrays.asList(-27,27),
                Arrays.asList(-28,28),
                Arrays.asList(-29,29),
                Arrays.asList(-30,30),
                Arrays.asList(-31,31),
                Arrays.asList(-32,32),
                Arrays.asList(-33,33),
                Arrays.asList(-34,34),
                Arrays.asList(-35,35),
                Arrays.asList(-36,36),
                Arrays.asList(-37,37),
                Arrays.asList(-38,38),
                Arrays.asList(-39,39),
                Arrays.asList(-40,40),
                Arrays.asList(-41,41),
                Arrays.asList(-42,42),
                Arrays.asList(-43,43),
                Arrays.asList(-44,44),
                Arrays.asList(-45,45),
                Arrays.asList(-46,46),
                Arrays.asList(-47,47),
                Arrays.asList(-48,48),
                Arrays.asList(-49,49),
                Arrays.asList(-50,50),
                Arrays.asList(-51,51),
                Arrays.asList(-52,52),
                Arrays.asList(-53,53),
                Arrays.asList(-54,54),
                Arrays.asList(-55,55)); // -55,-1 이 나와야 함...
         */
        int[] result = smallestRange(nums);
        System.out.println(Arrays.toString(result));
    }
}
