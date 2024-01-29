package com.example.codingtest.w2;

import java.util.*;
public class W2LongestValidParentheses {

    /* https://leetcode.com/problems/longest-valid-parentheses */
    public static int longestValidParentheses(String s) {

        int count = 0;
        int result = 0;
        char arr[] = s.toCharArray();

        Deque<Integer> list = new LinkedList<>();
        list.push(-1); //처음에 값 하나가 있어야함

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                list.push(i);
            } else {
                list.pop();
                if (list.isEmpty()) {
                    list.push(i);
                } else {
                    count = i - list.peek();
                    result = (count > result) ? count: result;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int result = longestValidParentheses("()(()");
        System.out.println(result);
        result = longestValidParentheses("(()");
        System.out.println(result);
        result = longestValidParentheses(")()())");
        System.out.println(result);
    }
}
