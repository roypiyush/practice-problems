package com.leetcode;

public class SubstringDifferByOne {
    public static void main(String[] args) {
        System.out.println(new SubstringDifferByOne().countSubstrings("aba", "baba"));
    }

    public int countSubstrings(String s, String t) {

        int count = 0;
        for (int l = 1; l <= Math.min(s.length(), t.length()); l++) {

            for (int i = 0; i < s.length() - l + 1; i++) {
                String a = s.substring(i, i + l);

                for (int j = 0; j < t.length() - l + 1; j++) {
                    String b = t.substring(j, j + l);

                    count += (differByOne(a, b) ? 1 : 0);
                }
            }
        }

        return count;
    }

    boolean differByOne(String s, String t) {
        int differ = 0;
        int i = 0;

        while (i < s.length()) {

            if (s.charAt(i) != t.charAt(i)) {
                if (differ == 1) {
                    return false;
                }
                differ++;
            }
            i++;
        }

        return differ == 1;
    }
}
