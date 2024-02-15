package com.liveclass.codingtest.w5;

public class W5RecommendID {

    public static String solution(String new_id) {
        String answer = "";

        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[~!@#$%^&*()=+\\[{\\]}:?,<>/]", "");

        StringBuilder sb = new StringBuilder();
        char prev = new_id.charAt(0);
        for (char c : new_id.toCharArray()) {
            if (!(prev == '.' && prev == c)) {
                sb.append(c);
                prev = c;
            }
        }
        new_id = sb.toString();
        new_id = new_id.replaceAll("^[.]|[.]$", "");
        if (new_id.isEmpty()) new_id = "a";
        if (new_id.length() > 15) new_id = new_id.substring(0,15);
        new_id = new_id.replaceAll("[.]$", "");
        while (new_id.length() < 3) {
            new_id += new_id.charAt(new_id.length() - 1);
        }

        answer = new_id;
        return answer;
    }

    public static void main(String[] args) {

        String result1 = solution("...!@BaT#*..y.abcdefghijklm");
        System.out.println("result1 = " + result1);
        String result2 = solution("z-+.^.");
        System.out.println("result2 = " + result2);
        String result3 = solution("=.=");
        System.out.println("result3 = " + result3);
        String result4 = solution("123_.def");
        System.out.println("result4 = " + result4);
        String result5 = solution("abcdefghijklmn.p");
        System.out.println("result5 = " + result5);
        String result6 = solution("-.~!@#$%&*()=+[{]}:?,<>/.-");
        System.out.println("result6 = " + result6);
    }
}
