package com.leetcode;

import java.util.Arrays;

public class OnesAndZeros {
    public static void main(String[] args) {
        String[] strs = {"0", "1"};
        System.out.println(new OnesAndZeros().findMaxForm(strs, 1, 1));
    }

    public int findMaxForm(String[] strs, int M, int N) {
        int size = strs.length;
        int[][] dp = new int[M + 1][N + 1];

        for (int i = 1; i <= size; i++) {

            int[] count = count01s(strs[i - 1]);
            int zeroCount = count[0];
            int oneCount = count[1];

            for (int m = M; m >= zeroCount; m--) {
                for (int n = N; n >= oneCount; n--) {
                    dp[m][n] = Math.max(dp[m - zeroCount][n - oneCount] + 1, dp[m][n]);
                }
            }
        }

        return dp[M][N];
    }

    int[] count01s(String str) {
        int[] count = new int[2];
        for (char c : str.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }

    void printArray(int[][] dp) {
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
    }
}
