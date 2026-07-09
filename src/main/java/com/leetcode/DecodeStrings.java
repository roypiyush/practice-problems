package com.leetcode;

import com.lib.Pair;

public class DecodeStrings {
    public static void main(String[] args) {
        System.out.println(new DecodeStrings().decodeString("3[a]2[bc]"));
    }

    public String decodeString(String s) {
        return expand(s, 0, s.length()).getValue();
    }

    Pair<Integer, String> expand(String s, int i, int size) {

        StringBuilder sb = new StringBuilder();
        StringBuilder number = new StringBuilder();

        while (i < size) {

            char ch = s.charAt(i);

            if (ch == '[') {

                Pair<Integer, String> internal = expand(s, i + 1, size);
                int k = number.length() == 0 ? 1 : Integer.parseInt(number.toString());
                number.setLength(0);

                String expanded = expandKTimes(internal.getValue(), k);
                sb.append(expanded);

                i = internal.getKey() + 1;

            } else if (ch == ']') {

                // processed till i
                return new Pair<Integer, String>(i, sb.toString());

            } else if (ch >= 97 && ch <= 122) { // hack for ascii

                sb.append(ch);
                i++;

            } else {
                // digits anyway
                number.append(ch);
                i++;
            }
        }

        return new Pair<Integer, String>(i, sb.toString());
    }

    String expandKTimes(String str, int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(str);
            n--;
        }
        return sb.toString();
    }
}
