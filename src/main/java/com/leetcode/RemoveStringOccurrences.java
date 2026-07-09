package com.leetcode;

import java.util.LinkedList;

public class RemoveStringOccurrences {

    public static void main(String[] args) {
        System.out.println(new RemoveStringOccurrences().removeOccurrences("daabcbaabcbc", "abc"));

    }
    public String removeOccurrences(String str, String part) {

        char[] t = str.toCharArray();
        int n = t.length;

        char[] p = part.toCharArray();
        int m = p.length;

        int[] pf = prefixFunction(p, m);

        int len = 0;

        LinkedList<int[]> stack = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            while (len > 0 && t[i] != p[len]) {
                len = pf[len - 1];
            }

            if (t[i] == p[len]) {
                len++;
            }

            stack.push(new int[]{t[i], len});
            if (len == m) {
                // match found
                int d = len;
                while (d-- > 0) {
                    stack.pop();
                }
                len = stack.isEmpty() ? 0 : stack.peek()[1];
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append((char) stack.pop()[0]);
        }
        sb.reverse();
        return sb.toString();

    }

    int[] prefixFunction(char[] p, int m) {

        int[] pf = new int[m];
        int len = 0;

        for (int i = 1; i < m; i++) {

            while (len > 0 && p[i] != p[len]) {
                len = pf[len - 1];
            }

            if (p[i] == p[len]) {
                len++;
            }

            pf[i] = len;
        }

        return pf;

    }
}
