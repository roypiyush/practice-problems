package com.leetcode;

import java.util.PriorityQueue;

@SuppressWarnings("ALL")
public class StoneGame2 {

    public static void main(String[] args) {
        System.out.println(new StoneGame2().stoneGameII(new int[]{2, 7, 9, 4, 4}));
    }

    // FIXME

    public int stoneGameII(int[] piles) {
        return bruteForce(piles, 1, -1, true, 0, 0);
    }

    int bruteForce(int[] piles, int M, int start, boolean isAlice, int alice, int bob) {
        if (start >= piles.length) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((integer, t1) -> 0);
        // completed till start - 1

        int choices = 2 * M;

        int max = 0;
        int sum = 0;
        for (int i = 1; i <= choices && (start + i) < piles.length; i++) {
            sum += piles[start + i];

            if (isAlice) {
                max = Math.max(max, alice + bruteForce(piles, Math.max(M, i), start + i, !isAlice, alice + sum, bob));
            } else {
                max = Math.max(max, bob + bruteForce(piles, Math.max(M, i), start + i, !isAlice, alice, bob + sum));
            }
        }

        return max;

    }
}
