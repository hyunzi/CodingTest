package com.example.codingtest.w2;

import java.util.ArrayDeque;

public class W2ValidParentheses {

    /* https://leetcode.com/problems/valid-parentheses/description/ */

    public static boolean isValid(String s) {

        ArrayDeque<Character> list = new ArrayDeque<>();

        for (char c: s.toCharArray()) {
            if (c == '(') {
                list.push(')');
            } else if (c == '{') {
                list.push('}');
            } else if (c == '[') {
                list.push(']');
            } else if (!list.isEmpty() && c == list.pop()) {
                //System.out.println("pop");
            } else {
                return false;
            }
        }

        return list.isEmpty();

    }
    public static void main(String[] args) {
        boolean result = isValid("()())())(){}}{}");
        System.out.println(result);
        result = isValid("()(){}{}");
        System.out.println(result);
        result = isValid("{}[]{}[]()");
        System.out.println(result);
    }
}
