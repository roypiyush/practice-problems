package com.leetcode;

public class RepeatedSubstringPattern {
    public static void main(String[] args) {
        final RepeatedSubstringPattern mainClass = new RepeatedSubstringPattern();
        System.out.println(mainClass.repeatedSubstringPattern("abab"));
    }

    public boolean repeatedSubstringPattern(String str) {

        if (str.length() == 1) {
            return false;
        }

        char[] s = str.toCharArray();

        int limit = s.length / 2 + s.length % 2;

        for (int i = 1; i <= limit; i++) {

            int k = i;
            while (k < s.length) {
                k = checkMatch(s, i, k);
                if (k == i) {
                    break;
                }
            }

            if (k == s.length) {
                return true;
            }
        }
        return false;
    }

    int checkMatch(char[] s, int i, int k) {
        int j = 0;
        while (j < i && s[j] == s[k]) {
            j++;
            k++;
        }
        return k;
    }
}
