package com.example.codingtest.w1;

import java.util.ArrayList;
import java.util.List;

public class W1CandidateKey {

    public static int solution(String[][] relation) {
        int answer = 0;

        //1. 중복값이 없는 컬럼과 있는 컬럼 구분
        List<Integer> duplColumns = new ArrayList<Integer>();

        for (int i = 0; i < relation[0].length; i++) { // 배열 세로로 접근

            List<String> dataList = new ArrayList<String>();
            for(int j = 0; j < relation.length; j++) {

                if (!dataList.contains(relation[j][i])) {
                    //list에 값이 없으면 add
                    dataList.add(relation[j][i]);
                } else {
                    //list에 값이 있으면 해당 컬럼 index 저장
                    duplColumns.add(i);
                    break;
                }
            }

            //컬럼의 모든 값이 list에 담긴 경우 중복값이 없는 것이므로 후보키
            if (dataList.size() == relation.length) {
                answer++;
            }
        }

        //2. 중복값 있는 컬럼 리스트로 모든 부분집합을 추출 (백트래킹)
        List<List<Integer>> combList = new ArrayList<>();
        for (int i = 2; i <= duplColumns.size(); i++) {
            //duplColumns 에서 i개의 원소로 만들 수 있는 집합을 반환. 2개 원소부터 모든 원소까지.
            backtrack(0, new ArrayList<>(), duplColumns, i, combList);
        }

        //3. combList 의 각 조합이 유일성과 최소성을 만족하는지 확인
        List<List<Integer>> answers = new ArrayList<>();
        for (List<Integer> columns : combList) {

            //유일성 체크
            ArrayList<String> strList = new ArrayList<String>();
            for (int i = 0; i < relation.length; i++) {
                String str = "";
                for(int j : columns) {
                    str += relation[i][j];
                }
                if (!strList.contains(str)) {
                    //list에 값이 없으면 add
                    strList.add(str);
                } else {
                    //list에 값이 있으면 조합이 중복이므로 후보키가 될 수 없음
                    break;
                }
            }

            //컬럼의 모든 값이 list에 담긴 경우 중복값이 없는 것이므로 유일성은 만족
            //최소성 체크
            if (strList.size() == relation.length) {
                long containCnt = answers.stream()
                        .filter(c -> columns.containsAll(c))
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


    private static void backtrack(int start, List<Integer> path, List<Integer> nums, int count, List<List<Integer>> result) {
        if (path.size() == count) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.size(); i++) {
            path.add(nums.get(i));
            backtrack(i + 1, path, nums, count, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int result = solution(new String[][]{
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        });
        System.out.println(result);


    }
}
