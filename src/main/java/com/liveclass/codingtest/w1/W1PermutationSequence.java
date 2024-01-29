package com.liveclass.codingtest.w1;

public class W1PermutationSequence {

    /* https://leetcode.com/problems/permutation-sequence */

    static int cnt = 0;
    public static String getPermutation(int n, int k) {
        cnt = 0;
        String str = "";
        for (int i = 1; i <= n; i++) {
            str += i;
        }
        return permutation("", str, k); //"", "1234", 5
    }


    public static String permutation(String prefix, String str, int k) {
        if (str.equals("")) {
            cnt++;
            //아래 print 하면 Time Limit Exceeded 발생..
            //System.out.println(prefix);
            return prefix;
        }

        String result;
        for (int i = 0; i < str.length(); i++) {
            result = permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, str.length()), k);
            if (cnt == k) {
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String result = getPermutation(3,3);
        System.out.println(result);
        result = getPermutation(4,9);
        System.out.println(result);
        result = getPermutation(3,1);
        System.out.println(result);
    }

}
