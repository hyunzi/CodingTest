package com.liveclass.codingtest.w5;

import java.util.*;

public class W5GroupAnagrams {

    /* https://leetcode.com/problems/group-anagrams/ */

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> strMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String anagram = new String(charArr);
            strMap.putIfAbsent(anagram, new ArrayList<>());
            strMap.get(anagram).add(strs[i]);
        }

        return  new ArrayList<>(strMap.values());
    }

    public static void main(String[] args) {

        List<List<String>> result1 = groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
        System.out.println("result1 = " + result1);
        List<List<String>> result2 = groupAnagrams(new String[]{""});
        System.out.println("result2 = " + result2);
        List<List<String>> result3 = groupAnagrams(new String[]{"a"});
        System.out.println("result3 = " + result3);
    }
}
