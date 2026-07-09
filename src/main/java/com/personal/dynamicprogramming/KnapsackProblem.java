package com.personal.dynamicprogramming;

public class KnapsackProblem {

    public static void main(String[] args) {

        int val[] = {30, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;

        long start = System.nanoTime();
        int value = knapsack(val, wt, W);
        long end = System.nanoTime();

        System.out.printf("Knapsack without DP Max Value = %d    Running time: %d%n",
                value, end - start);

        int R[] = new int[W + 1];
        for (int i = 0; i <= W; i++) {
            if (i > 0)
                R[i] = -1;
            else
                R[i] = 0;
        }

        start = System.nanoTime();
        value = knapsackWithDP(val, wt, W, R);
        end = System.nanoTime();

        System.out.printf("Knapsack with DP Max Value = %d    Running time: %d%n",
                value, end - start);

        System.out.printf("knapsack2DIterative %d \n", knapsackWith2DPIterative(val, wt, W));
    }

    private static int knapsackWith2DPIterative(int[] v, int[] wt, int W) {
        int size = wt.length;
        int[][] dp = new int[size + 1][W + 1];

        for (int i = 1; i <= size; i++) {
            for (int w = 0; w <= W; w++) {
                if (w - wt[i - 1] >= 0) {
                    dp[i][w] = Math.max(dp[i - 1][w - wt[i - 1]] + v[i - 1], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[size][W];
    }

    private static int knapsackWithDP(int[] val, int[] wt, int w, int[] r) {

        if (w < 0) return Integer.MIN_VALUE;

        if (r[w] >= 0)
            return r[w];

        int value = 0;

        for (int i = 0; i < val.length; i++) {
            value = Math.max(value, val[i] + knapsackWithDP(val, wt, w - wt[i], r));
        }

        r[w] = value;

        return value;
    }

    private static int knapsack(int[] val, int[] wt, int w) {

        if (w == 0) return 0;

        if (w < 0) return Integer.MIN_VALUE;

        int value = 0;

        for (int i = 0; i < val.length; i++) {
            value = Math.max(value, val[i] + knapsack(val, wt, w - wt[i]));
        }

        return value;
    }

}
