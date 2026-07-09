/**
 *
 */
package com.personal.dynamicprogramming;

import java.util.Arrays;

/**
 * @author piyush
 */
public class MatrixChainMultiplication {


    private static int computeRecursive(int[] arr, int i, int j) {

        if (i == j) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            min = Math.min(min, computeRecursive(arr, i, k) + computeRecursive(arr, k + 1, j)
                    + arr[i - 1] * arr[k] * arr[j]);
        }

        return min;
    }

    private static int bottomUpDp(int[] arr) {
        int size = arr.length - 1;
        int[][] dp = new int[size + 1][size + 1];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 1; i <= size; i++) {
            dp[i][i] = 0;
        }

        for (int l = 2; l <= size; l++) {
            for (int i = 1; i <= size - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]
                            + arr[i - 1] * arr[k] * arr[j]);
                }
            }
        }

        return dp[1][size];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {40, 20, 30, 10, 30};
        System.out.println(computeRecursive(arr, 1, arr.length - 1)); // 26000
        System.out.println(bottomUpDp(arr));

        int[] arr1 = {10, 20, 30, 40, 30};
        System.out.println(computeRecursive(arr1, 1, arr1.length - 1)); // 30000
        System.out.println(bottomUpDp(arr1));

        int[] arr2 = {10, 20, 30};
        System.out.println(computeRecursive(arr2, 1, arr2.length - 1)); // 6000
        System.out.println(bottomUpDp(arr2));

        int[] arr3 = {10, 30, 5, 60};
        System.out.println(computeRecursive(arr3, 1, arr3.length - 1)); // 4500
        System.out.println(bottomUpDp(arr3));

    }

}
