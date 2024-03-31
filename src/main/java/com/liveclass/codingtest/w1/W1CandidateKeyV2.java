package com.liveclass.codingtest.w1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class W1CandidateKeyV2 {

    public static int solution(String[][] relation) {

        /*
        * 생각의 흐름
        * 1. 컬럼으로 후보키의 조합을 구하자 (후보키가 될 수 있는 목록을 구해야 함)
        * 2. 그 키가 전체 튜플을 유일하게 식별하는지 확인하자
        * 3. 단, 모든 튜플을 식별하는데 다른 조합에 포함되는 조합이면 빠져야함 (최소성!)
        * */

        int answer = 0;

        //1. 배열을 세로로 접근하여 중복값이 있는 컬럼과 없는 컬럼 구분
        //  ㄴ 중복값이 없으면 후보키가 되는 것인데 이 때 answer 을 + 1 해준다
        List<Integer> duplColumns = new ArrayList<>();
        for (int i = 0; i < relation[0].length; i++) {
            List<String> dataList = new ArrayList<>();
            for (int j = 0; j < relation.length; j++) {
                if (!dataList.contains(relation[j][i])) {
                    dataList.add(relation[j][i]);
                } else {
                    duplColumns.add(i);
                    break;
                }
            }

            //리스트에 컬럼의 모든 값이 담긴 경우는 중복값이 없는 컬럼이므로 후보키가 된다
            if (dataList.size() == relation.length) {
                answer++;
            }
        }

        //2. 중복값이 있는 컬럼들로 모든 부분집합을 추출한다
        //  ㄴ 백트래킹을 사용한다
        List<List<Integer>> combList = new ArrayList<>();
        for (int i = 2; i <= duplColumns.size(); i++) {
            combine(0, new ArrayList<>(), duplColumns, i, combList);
        }

        //3. 유일성과 최소성을 만족하는지 확인
        List<List<Integer>> answers = new ArrayList<>();
        for(List<Integer> columns : combList) {

            //유일성 체크
            HashSet<String> tupleList = new HashSet<>();
            for (int i = 0; i < relation.length; i++) {
                String str = "";
                for (int j : columns) {
                    str += relation[i][j];
                }
                if (!tupleList.contains(str)) {
                    tupleList.add(str);
                } else {
                    break; //후보키 안됨
                }
            }

            //최소성 체크 (유일성 만족하는 것 중에)
            if (tupleList.size() == relation.length) {
                long containCnt = answers.stream()
                        .filter(columns::containsAll)
                        .count();

                //다른 answers에 포함되지 않는 경우만 후보키가 됨
                if (containCnt == 0) {
                    answers.add(columns);
                    answer++;
                }
            }
        }


        return answer;
    }


    public static void combine(int start, List<Integer> path, List<Integer> nums, int count, List<List<Integer>> result) {
        if (path.size() == count) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.size(); i++) {
            path.add(nums.get(i));
            combine(i + 1, path, nums, count, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {

        int result1 = solution(new String[][]{
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        });
        System.out.println("result1 = " + result1);

    }
}
