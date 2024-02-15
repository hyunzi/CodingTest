package com.liveclass.codingtest.w5;

import java.util.HashSet;

public class W5StringCompression {

    /* https://school.programmers.co.kr/learn/courses/30/lessons/60057 */

    /*
    * 1. HashMap이나 HashSet을 이용하면 될거야
    * 2. abcabcabcabcdededededede
    * 3. 2abcabc2dedede 이게 제일 짧은거네 ㄷㄷ
    * 4. 자르는 단위는 중간에 바뀌면 안되는군…
    * 5. s의 길이가 1000 이하라는 것은 자를 수 있는 경우의 수가 1000개?
    * 6. aaaaaaaaaaaaaaaaaaaa 를 a,a,a,a,a,… 또는 aa, aa, aa, aa, aa, … 또는 aaa, aaa, aaa, …
    * 7. 단위가 n 이면 다음 n개가 일치할 때, 압축 가능
    * 8. n으로 잘라서, 다음 n개가 일치하면, 또 그다음 n개가 기준이 되어서, n개가 다시 일치하는지봄
    * */

    public static int solution(String s) {
        int answer = Integer.MAX_VALUE;

        if (s.length() == 1) answer = s.length();

        for (int i = 1; i < s.length(); i++) {
            String currWord = s.substring(0,i);

            int cnt = 1;
            int start = i;
            String compress = "";
            int length = 0;

            while (start+i <= s.length()) {
                String nextWord = s.substring(start, start + i);
                if (currWord.equals(nextWord)) {
                    cnt++;
                } else {
                    if (cnt > 1) {
                        compress += cnt;
                        length += cnt*currWord.length();
                    } else {
                        length += currWord.length();
                    }
                    compress += currWord;
                    currWord = nextWord;
                    cnt = 1;
                }
                start += i;
            }
            if (cnt > 1) {
                compress += cnt;
                length += cnt*currWord.length();
            } else {
                length += currWord.length();
            }
            compress += currWord;
            length += s.length()-start;
            compress += s.substring(start,s.length());

            if (!compress.isEmpty() && s.length() == length) {
                answer = Math.min(answer, compress.length());
                //System.out.println("compress = " + compress + ", len = "+compress.length());
            }
        }
        return answer;
    }


    public static void main(String[] args) {

        int result1 = solution("aabbaccc");
        System.out.println("result = " + result1);
        int result2 = solution("ababcdcdababcdcd");
        System.out.println("result = " + result2);
        int result3 = solution("abcabcdede");
        System.out.println("result = " + result3);
        int result4 = solution("abcabcabcabcdededededede");
        System.out.println("result = " + result4);
        int result5 = solution("xababcdcdababcdcd");
        System.out.println("result = " + result5);
        int result6 = solution("x");
        System.out.println("result = " + result6);

    }
}
