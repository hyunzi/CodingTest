package com.liveclass.codingtest.w7;

public class W7DecryptMessage {

    public static String solution(String m, String k) {

        //String result = "";
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < m.length(); i++) {
            if (j < k.length() && m.charAt(i) == k.charAt(j)) {
                j++;
            } else {
                result.append(m.charAt(i));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String result1 = solution("kkaxbycyz", "abc");
        System.out.println("result1 = " + result1);
        String result2 = solution("acbbcdc", "abc");
        System.out.println("result2 = " + result2);
        String result3 = solution("newtext", "et");
        System.out.println("result3 = " + result3);

    }
}
