package com.leetcode;

/*
# Logical Progression: Number of Stable Binary Arrays

## Phase 1: The Intuitive 4D State (The Foundation)
**The Concept:** Track every single variable physically happening in the array at any given time.
**The State:** `dp[i][j][k][l]`
* `i` = Zeros placed
* `j` = Ones placed
* `k` = Remaining consecutive zero streak allowed
* `l` = Remaining consecutive one streak allowed
**The Logic:** Placing a '0' reduces `k` by 1 and resets `l` to the maximum limit. Placing a '1' reduces `l` by 1 and resets `k` to the maximum limit.
**Complexity:** Time: $O(Z \times O \times L^2)$ | Space: $O(Z \times O \times L^2)$
**The Problem:** Massively memory-intensive and too slow for anything beyond basic constraints.

---

## Phase 2: The 3D State Optimization (Streak Tracking)
**The Concept:** Realize that it is mathematically impossible to diminish both streaks simultaneously. When placing a digit, the opposite digit's streak is instantly broken. Thus, we only need to track the streak length of the *last placed digit*.
**The State:** `dp0[i][j][k]` and `dp1[i][j][k]`
* `dp0` = Sequences ending in 0. `k` = current streak length of 0s.
**The Logic:** * To extend a streak ($k > 1$): `dp0[i][j][k] = dp0[i - 1][j][k - 1]`
* To start a new streak ($k = 1$): Sum all valid streaks of the opposite digit from the previous step.
**Complexity:** Time: $O(Z \times O \times L)$ | Space: $O(Z \times O \times L)$
**The Problem:** Passes standard constraints, but the inner loop running `limit` times causes Time Limit Exceeded (TLE) on large constraints.

---

## Phase 3: 2D Inclusion-Exclusion DP (The Algorithmic Peak)
**The Concept:** Abandon tracking streak lengths step-by-step. Instead, track the *total* number of valid sequences. Assume you can safely append a digit to any previous valid sequence, and then mathematically subtract the exact boundary where a sequence becomes illegal.
**The State:** `dp0[i][j]` and `dp1[i][j]` (Total valid sequences ending in 0 or 1).
**The Logic:** An illegal sequence ending in a new '0' looks like: `[Valid prefix ending in 1] + [limit + 1 zeros]`.
If we chop off the zeros, the prefix has `i - 1 - limit` zeros and `j` ones.
* **Include:** `dp0[i][j] = dp0[i - 1][j] + dp1[i - 1][j]`
* **Exclude:** `dp0[i][j] -= dp1[i - 1 - limit][j]`
**Complexity:** Time: $O(Z \times O)$ | Space: $O(Z \times O)$
**The Problem:** Highly optimal, but memory and time scale quadratically. If constraints jump to $10^5$, an $O(N^2)$ algorithm will still fail.

---

## Phase 4: Pure Combinatorics (The Mathematical Ceiling)
**The Concept:** Abandon Dynamic Programming entirely. Instead of placing digits one by one, group the array into alternating "blocks" of zeros and ones. Use combinatorics to distribute digits into these blocks.
**The Logic:**
1.  **Stars and Bars:** Calculate the total combinations to divide `Z` zeros into `k` blocks using $\binom{Z - 1}{k - 1}$.
2.  **Principle of Inclusion-Exclusion (PIE):** Use an alternating summation formula to subtract the combinations where 1 block breaks the limit, add back where 2 blocks break the limit, subtract 3, etc.
3.  **Modular Arithmetic:** Precompute factorials and their modular inverses using Fermat's Little Theorem to handle massive numbers in division.
4.  **Merge:** Iterate through the valid number of alternating blocks and multiply the zero-block combinations by the one-block combinations.
**Complexity:** Time: Roughly $O(N^2 / L)$ or $O(N \log MOD)$ | Space: $O(N)$
**The Result:** Defeats any time and memory constraints by calculating the answer purely through discrete math formulas rather than simulating states.
 */
public class FindAllPossibleStableBinaryArraysI {
    public static void main(String[] args) {
        FindAllPossibleStableBinaryArraysI main = new FindAllPossibleStableBinaryArraysI();
        Solution1 sol1 = main.new Solution1();
        Solution2 sol2 = main.new Solution2();
        Solution3 sol3 = main.new Solution3();
        Solution4 sol4 = main.new Solution4();
        Solution5 sol5 = main.new Solution5();
        Solution6 sol6 = main.new Solution6();
        System.out.println(sol1.numberOfStableArrays(3, 1, 2));
        System.out.println(sol2.numberOfStableArrays(3, 1, 2));
        System.out.println(sol3.numberOfStableArrays(3, 1, 2));
        System.out.println(sol4.numberOfStableArrays(3, 1, 2));
        System.out.println(sol5.numberOfStableArrays(3, 1, 2));
        System.out.println(sol6.numberOfStableArrays(3, 1, 2));
    }

    class Solution6 {
        public int numberOfStableArrays(int zero, int one, int limit) {
            int[][] dp0 = new int[zero + 1][one + 1];
            int[][] dp1 = new int[zero + 1][one + 1];

            int MOD = 1_000_000_007;

            // fill up base case
            dp0[1][0] = 1;
            dp1[0][1] = 1;

            for (int i = 0; i <= zero; i++) {
                for (int j = 0; j <= one; j++) {
                    // place 0
                    if (i > 0) {
                        dp0[i][j] += dp1[i - 1][j] + dp0[i - 1][j];
                        dp0[i][j] -= i - 1 - limit >= 0 ? dp1[i - 1 - limit][j] : 0;
                        dp0[i][j] %= MOD;
                    }

                    // place 1
                    if (j > 0) {
                        dp1[i][j] += dp1[i][j - 1] + dp0[i][j - 1];
                        dp1[i][j] -= j - 1 - limit >= 0 ? dp0[i][j - 1 - limit] : 0;
                        dp1[i][j] %= MOD;
                    }
                }
            }

            return dp0[zero][one] + dp1[zero][one];
        }

    }

    class Solution5 {
        // use 2D prefix sum over Solution4
        public int numberOfStableArrays(int zero, int one, int limit) {
            int[][][] dp0 = new int[zero + 1][one + 1][limit + 1];
            int[][][] dp1 = new int[zero + 1][one + 1][limit + 1];

            int[][] prefixSum0 = new int[zero + 1][one + 1];
            int[][] prefixSum1 = new int[zero + 1][one + 1];

            int MOD = 1_000_000_007;

            // fill up base case
            dp0[1][0][1] = 1;
            dp1[0][1][1] = 1;

            for (int i = 0; i <= zero; i++) {
                for (int j = 0; j <= one; j++) {
                    for (int k = 1; k <= limit; k++) {

                        // place 0
                        if (i > 0) {
                            if (k == 1) {
                                dp0[i][j][k] += prefixSum1[i - 1][j];
                                dp0[i][j][k] %= MOD;
                            } else {
                                dp0[i][j][k] += dp0[i - 1][j][k - 1];
                                dp0[i][j][k] %= MOD;
                            }
                        }

                        // place 1
                        if (j > 0) {
                            if (k == 1) {
                                dp1[i][j][k] += prefixSum0[i][j - 1];
                                dp1[i][j][k] %= MOD;
                            } else {
                                dp1[i][j][k] += dp1[i][j - 1][k - 1];
                                dp1[i][j][k] %= MOD;
                            }
                        }

                        prefixSum0[i][j] += dp0[i][j][k];
                        prefixSum0[i][j] %= MOD;
                        prefixSum1[i][j] += dp1[i][j][k];
                        prefixSum1[i][j] %= MOD;
                    }
                }
            }

            return prefixSum0[zero][one] + prefixSum1[zero][one];
        }

    }

    class Solution4 {
        // use prefix sum over Solution3
        public int numberOfStableArrays(int zero, int one, int limit) {
            int[][][] dp0 = new int[zero + 1][one + 1][limit + 1];
            int[][][] dp1 = new int[zero + 1][one + 1][limit + 1];

            // i - 1
            int[] prefixSum0 = new int[zero + 1];
            // j - 1
            int[] prefixSum1 = new int[one + 1];

            int MOD = 1_000_000_007;

            // fill up base case
            dp0[1][0][1] = 1;
            dp1[0][1][1] = 1;

            for (int i = 0; i <= zero; i++) {
                for (int j = 0; j <= one; j++) {
                    int curSum0 = 0;
                    int curSum1 = 0;

                    for (int k = 1; k <= limit; k++) {

                        // place 0
                        if (i > 0) {
                            if (k == 1) {
                                dp0[i][j][k] += prefixSum1[j];
                                dp0[i][j][k] %= MOD;
                            } else {
                                dp0[i][j][k] += dp0[i - 1][j][k - 1];
                                dp0[i][j][k] %= MOD;
                            }
                        }

                        // place 1
                        if (j > 0) {
                            if (k == 1) {
                                dp1[i][j][k] += prefixSum0[i];
                                dp1[i][j][k] %= MOD;
                            } else {
                                dp1[i][j][k] += dp1[i][j - 1][k - 1];
                                dp1[i][j][k] %= MOD;
                            }
                        }

                        curSum0 += dp0[i][j][k];
                        curSum0 %= MOD;
                        curSum1 += dp1[i][j][k];
                        curSum1 %= MOD;
                    }
                    prefixSum1[j] = curSum1;
                    prefixSum0[i] = curSum0;
                }
            }

            int result = 0;
            for (int k = 1; k <= limit; k++) {
                result += dp0[zero][one][k];
                result %= MOD;
                result += dp1[zero][one][k];
                result %= MOD;
            }
            return result;
        }

    }

    class Solution3 {
        public int numberOfStableArrays(int zero, int one, int limit) {
            int[][][] dp0 = new int[zero + 1][one + 1][limit + 1];
            int[][][] dp1 = new int[zero + 1][one + 1][limit + 1];

            int MOD = 1_000_000_007;

            // fill up base case
            dp0[1][0][1] = 1;
            dp1[0][1][1] = 1;

            for (int i = 0; i <= zero; i++) {
                for (int j = 0; j <= one; j++) {
                    for (int k = 1; k <= limit; k++) {

                        // place 0
                        if (i > 0) {
                            if (k == 1) {
                                for (int l = 1; l <= limit; l++) {
                                    dp0[i][j][k] += dp1[i - 1][j][l];
                                    dp0[i][j][k] %= MOD;
                                }
                            } else {
                                dp0[i][j][k] += dp0[i - 1][j][k - 1];
                                dp0[i][j][k] %= MOD;
                            }
                        }

                        // place 1
                        if (j > 0) {
                            if (k == 1) {
                                for (int l = 1; l <= limit; l++) {
                                    dp1[i][j][k] += dp0[i][j - 1][l];
                                    dp1[i][j][k] %= MOD;
                                }
                            } else {
                                dp1[i][j][k] += dp1[i][j - 1][k - 1];
                                dp1[i][j][k] %= MOD;
                            }
                        }
                    }
                }
            }

            int result = 0;
            for (int k = 1; k <= limit; k++) {
                result += dp0[zero][one][k];
                result %= MOD;
                result += dp1[zero][one][k];
                result %= MOD;
            }
            return result;

        }
    }

    class Solution2 {

        public int numberOfStableArrays(int zero, int one, int limit) {
            int[][][][] dp = new int[zero + 1][one + 1][2][limit + 1];
            int MOD = 1_000_000_007;

            // fill up base case
            dp[1][0][0][1] = 1;
            dp[0][1][1][1] = 1;

            for (int i = 0; i <= zero; i++) {
                for (int j = 0; j <= one; j++) {
                    for (int k = 1; k <= limit; k++) {

                        // place 0
                        if (i > 0) {
                            if (k == 1) {
                                for (int l = 1; l <= limit; l++) {
                                    dp[i][j][0][k] += dp[i - 1][j][1][l];
                                    dp[i][j][0][k] %= MOD;
                                }
                            } else {
                                dp[i][j][0][k] += dp[i - 1][j][0][k - 1];
                                dp[i][j][0][k] %= MOD;
                            }
                        }

                        // place 1
                        if (j > 0) {
                            if (k == 1) {
                                for (int l = 1; l <= limit; l++) {
                                    dp[i][j][1][k] += dp[i][j - 1][0][l];
                                    dp[i][j][1][k] %= MOD;
                                }
                            } else {
                                dp[i][j][1][k] += dp[i][j - 1][1][k - 1];
                                dp[i][j][1][k] %= MOD;
                            }
                        }
                    }
                }
            }

            int result = 0;
            for (int k = 1; k <= limit; k++) {
                for (int current = 0; current < 2; current++) {
                    result += dp[zero][one][current][k];
                    result %= MOD;
                }
            }
            return result;
        }
    }

    class Solution1 {

        public int numberOfStableArrays(int zero, int one, int limit) {

            int[][][][] dp = new int[zero + 1][one + 1][limit + 1][limit + 1];
            int MOD = 1_000_000_007;

            // base case
            for (int i = 0; i <= limit; i++) {
                for (int j = 0; j <= limit; j++) {
                    dp[0][0][i][j] = 1;
                }
            }

            for (int i = 0; i <= zero; i++) {
                for (int j = 0; j <= one; j++) {

                    if (i == 0 && j == 0) continue;

                    for (int k = 0; k <= limit; k++) {
                        for (int l = 0; l <= limit; l++) {

                            int takezero = (i > 0 && k > 0) ? dp[i - 1][j][k - 1][limit] : 0;
                            int takeone = (j > 0 && l > 0) ? dp[i][j - 1][limit][l - 1] : 0;

                            dp[i][j][k][l] = (takezero + takeone) % MOD;
                        }
                    }
                }
            }

            return dp[zero][one][limit][limit];
        }
    }
}
