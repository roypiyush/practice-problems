package com.leetcode;

public class WaysToExpressPower {

    int MOD = 1_000_000_007;

    public static void main(String[] args) {
        // TODO optimisation
        WaysToExpressPower sol = new WaysToExpressPower();
        System.out.println(sol.numberOfWays(4, 1));
    }

    public int numberOfWays(int n, int x) {
        return count(n, x, 0, 0);
    }

    int count(int n, int x, int start, int curSum) {
        if (curSum > n) {
            return 0;
        }
        if (curSum == n) {
            return 1;
        }

        int count = 0;
        for (int i = start + 1; i <= n; i++) {
            int rem = (int) Math.pow(i, x);
            count += count(n, x, i, curSum + rem);
        }

        return count % MOD;
    }
}
