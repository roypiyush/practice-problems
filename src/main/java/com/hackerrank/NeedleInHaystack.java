package com.hackerrank;

public class NeedleInHaystack {
    public static void main(String[] args) {
        String haystack = "ababaababaaa", needle = "bba";
        System.out.println(strStr(haystack, needle));
    }
    static int strStr(String haystack, String needle) {
        int position = 0;
        StringBuilder builder = new StringBuilder(haystack.substring(position, needle.length()));
        while (position + needle.length() < haystack.length()) {
            if (builder.toString().equals(needle)) {
                return position;
            }
            position++;
            if (position + needle.length() < haystack.length()) {
                builder
                        .deleteCharAt(0)
                        .append(haystack.charAt(position + needle.length() - 1));
            }
        }
        return -1;
    }
}
