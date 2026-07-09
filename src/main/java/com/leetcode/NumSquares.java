package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class NumSquares {
    public static void main(String[] args) {
        System.out.println(new NumSquares().numSquares(1));
    }

    public int numSquares(int n) {
        int[] squares = generatePerfectSquares(n);
        Integer[] dp = new Integer[n + 1];
        dp[0] = 0;
        return minSquares(squares, n, dp);
    }

    int minSquares(int[] squares, int n, Integer[] dp) {

        if (dp[n] != null) {
            return dp[n];
        }

        int min = Integer.MAX_VALUE;
        for (final int square : squares) {

            int r = n - square;
            if (r < 0) {
                // remaining is -ve which means invalid choices from this point
                break;
            }

            int value = minSquares(squares, n - square, dp);

            if (value == -1) {
                continue;
            }

            min = Math.min(min, value + 1);
        }

        dp[n] = min;
        return min;
    }

    int[] generatePerfectSquares(int n) {

        List<Integer> list = new ArrayList<>();
        int cur = 1;
        while ((cur * cur) <= n) {
            list.add(cur * cur);
            cur++;
        }

        int[] arr = new int[list.size()];
        int i = 0;
        for (int l : list) {
            arr[i++] = l;
        }

        return arr;
    }
}
