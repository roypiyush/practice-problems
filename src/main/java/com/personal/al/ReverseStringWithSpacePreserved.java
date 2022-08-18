package com.personal.al;

public class ReverseStringWithSpacePreserved {
    private static String reverse(String str)
    {
        int s = 0;
        int e = str.length() - 1;

        final char[] chars = str.toCharArray();
        while (s < e) {
            while (chars[s] == ' ' && s < e) {
                s++;
            }
            while (chars[e] == ' ' && s < e) {
                e--;
            }
            char cs = chars[s];
            char ce = chars[e];

            chars[e] = cs;
            chars[s] = ce;

            s++; e--;
        }
        return new String(chars);
    }

    public static void main(String[] args)
    {
        System.out.println(reverse("a quick brown fox jumps over the lazy dog"));
    }
}
