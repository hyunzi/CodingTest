package com.example.codingtest;

import java.util.ArrayList;
import java.util.List;

public class W1PalindromePartitioning {

    /* https://leetcode.com/problems/palindrome-partitioning/description/ */

    public static List<List<String>> partition(String s) {
        List<List<String>> resultList = new ArrayList<>();

        //1. 문자열을 자를 수 있는 조합 모두 추출
        List<List<String>> combList = new ArrayList<>();
        if (s.length() == 1) {
            combList.add(new ArrayList<>(){
                {add(s);}
            });
        }

        //문자열의 길이가 1 또는 2인 경우 예외처리
        int length = (int)Math.pow(2,s.length()-1);
        length = (s.length() == 1) ? 0 : (s.length() == 2) ? 2 : length;

        for (int i = 0; i < length; i++) {
            char[] commaBinary = String.format("%"+(s.length()-1)+"s",Integer.toBinaryString(i)).replace(' ', '0').toCharArray();

            String text = ""+s.charAt(0);
            List<String> strList = new ArrayList<String>();
            for (int j = 0; j < commaBinary.length; j++) {
                if (commaBinary[j] == '0') {
                    strList.add(text);
                    text = s.substring(j+1, j+2);
                } else {
                    text += s.substring(j+1, j+2);
                }
            }
            strList.add(text);
            combList.add(strList);
        }

        //2. 조합별로 palindrome을 만족하는지 확인
        for (List<String> comb : combList) {
            boolean flag = true;
            for (String str : comb) {
                if (!isPalindrome(str)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                resultList.add(comb);
            }
        }
        return resultList;
    }

    public static boolean isPalindrome(String str) {
        return str.equals(new StringBuffer(str).reverse().toString());
    }

    public static void main(String[] args) {

        List<List<String>> result = partition("aab");
        System.out.println(result);
        result = partition("abab");
        System.out.println(result);
        result = partition("aaaaab");
        System.out.println(result);
    }
}
