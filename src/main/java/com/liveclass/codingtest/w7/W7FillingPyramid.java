package com.liveclass.codingtest.w7;

import java.util.*;

public class W7FillingPyramid {

    //문제 이해
    //각 블록이 아래 두 블록의 값을 합한 값인 피라미드가 있다
    //일부 블록의 값이 주어지고, 전체 블록의 값을 알아내라
    //위에서부터 층별로 하나의 블록 숫자가 주어진다
    //왼쪽에서 몇 번째 블록인지 정보도 주어진다

    //생각의 흐름
    //어떤 알고리즘과 관련이 있을까..?
    //피라미드 규칙 상 각 행의 길이는 +1 씩 증가한다 1,2,3,4,5...
    //특정 블록의 값을 구할 때 그 상위랑, 좌/우 칸은 있음
    //한 행을 다 구하고 다음 행으로 가면 되겠지?
    //blocks 길이가 6이라면? 6+5+4+3+2+1 = 
    public static int[] solve(int[][] blocks) {

        //result 길이
        int length = pyramidLength(blocks.length);
        int[] result = new int[length];
        Arrays.fill(result, Integer.MIN_VALUE);

        //첫 번째 원소는 무조건 꼭대기층
        //나머지 원소도 result에 넣어두고 돌릴까?!
        Queue<int[]> queue = new ArrayDeque<>();
        for (int row = 0; row < blocks.length; row++) {
            int currIdx = pyramidLength(row) + blocks[row][0];
            result[currIdx] = blocks[row][1];
        }

        int blockIdx = 1;
        queue.add(new int[]{blockIdx,blocks[blockIdx][0], blocks[blockIdx][1]});

        while (!queue.isEmpty() && blockIdx < blocks.length-1) {
            int[] block = queue.poll();
            System.out.println("Arrays.toString(block) = " + Arrays.toString(block));
            int row = block[0];
            int col = block[1];
            int val = block[2];

            //col 위치로 왼쪽 값을 구할지 오른쪽 값을 구할지 계산
            //parent가 [row-1][col-1] 에 값이 있거나 col가 row랑 같은. 가장 오른쪽 위치인 경우 왼쪽을 채움
            //parent가 [row-1][col] 에 값이 있거나 col가 0인. 가장 왼쪽 위치인 경우 오른쪽을 채움
            int currIdx = pyramidLength(row) + col;
            int leftIdx = pyramidLength(row - 1) + col - 1;
            int rightIdx = pyramidLength(row - 1) + col;

            //leftIdx, rightIdx 둘 중에 result 에 값이 있는 것을 찾기
            int parent = 0;
            int child = 0;

            if (col == 0) {
                parent = result[rightIdx];
                child = currIdx + 1;
            } else if (col == row) {
                parent = result[leftIdx];
                child = currIdx - 1;
                col--;
            } else if (result[leftIdx] == Integer.MIN_VALUE) { //오른쪽을 채움
                parent = result[rightIdx];
                child = currIdx + 1;
            } else if (result[rightIdx] == Integer.MIN_VALUE) { //왼쪽을 채움
                parent = result[leftIdx];
                child = currIdx - 1;
                col--;
            } else if (result[currIdx - 1] == Integer.MIN_VALUE) {
                parent = result[leftIdx];
                child = currIdx - 1;
                col--;
            } else if (result[currIdx + 1] == Integer.MIN_VALUE) {
                parent = result[rightIdx];
                child = currIdx + 1;
            }
            if (result[child] == Integer.MIN_VALUE) {
                result[child] = parent - val;
                queue.add(new int[]{row, col, result[child]});
            } else {
                //해당 row를 다 채워서, 다음 row로 넘어가야할 때..
                queue.add(new int[]{++blockIdx,blocks[blockIdx][0], blocks[blockIdx][1]});
            }
            System.out.println("parent = " + parent);
        }

        return result;
    }

    public static int pyramidLength(int n) {
        int length = (1 + n) * (n / 2);
        length += (n % 2 == 0) ? 0 : (n / 2 + 1);
        return length;
    }

    public static void main(String[] args) {

        int[] result1 = solve(new int[][]{
                {0, 50}, {0, 22}, {2, 10}, {1, 4}, {4, -13}
        });
        System.out.println("result1 = " + Arrays.toString(result1));
        int[] result2 = solve(new int[][]{
                {0, 92}, {1, 20}, {2, 11}, {1, -81}, {3, 98}
        });
        System.out.println("result2 = " + Arrays.toString(result2));
    }
}
