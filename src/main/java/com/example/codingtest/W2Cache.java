package com.example.codingtest;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Locale;

public class W2Cache {

    public static int solution(int cacheSize, String[] cities) {


        //캐시사이즈가 3이면, 3개의 도시 이름을 담을 수 이음
        //도시 이름이 캐시에 있으면 실행시간 1이 걸리고, 없으면 5가 걸림
        //캐시에는 LRU 알고리즘이 적용되어, 가장 오래 참조되지 않은 것이 교체된다

        //첫번째 케이스는 모든 항목이 cache miss 로 실행시간이 50 걸림...

        //도시이름은 대소문자 구분하지 않고 비교함!!! 중요

        //1. 캐시크기 사이즈의 큐에 도시 이름을 순서대로 넣음
        //2. 다음 도시 이름을 찾을 때, 큐에서 찾게 되면 해당 인덱스를 LRU큐에 인덱스를 넣음
        //3. LRU 큐 사이즈가 캐시크기 사이즈보다 크면? LRU 큐의 first index를 pop하고
        //4. LRU 큐 사이즈가 캐시크기 사이즈보다 작으면? 그냥 도시이름큐의 first index를 pop
        //5. 큐에 있었을 때 1, 없었을 때 5를 센당!


        int answer = 0;
        ArrayDeque<String> cacheList = new ArrayDeque<>();
        for (String city : cities) {
            if (cacheList.contains(city.toUpperCase())) {
                cacheList.remove(city.toUpperCase());
                cacheList.add(city.toUpperCase());
                answer += 1;
            } else {
                if (cacheSize != 0 && cacheList.size() == cacheSize) {
                    cacheList.poll();
                }
                cacheList.add(city.toUpperCase());
                answer += 5;
            }
            System.out.println(cacheList);
        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {

        //solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
        //solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
        //solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        solution(6, new String[]{"Jeju", "Seoul", "Jeju", "TEST", "Seoul"});
    }

}
