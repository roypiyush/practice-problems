package com.leetcode;

import java.util.stream.IntStream;

public class TargetSum {

    public static void main(String[] args) {
        final int target = 0;
        final int[] nums = {1, 1, 1, 1};

        System.out.println(new TargetSum().recursionWithMemoization(nums, target));
        System.out.println(new TargetSum().dynamicProgrammingWith2DArray(nums, target));
    }

    public int dynamicProgrammingWith2DArray(int[] nums, int target) {
        int totalSum = IntStream.of(nums).sum();
        int rowNum = 2 * totalSum + 1;

        int[][] dp = new int[rowNum][nums.length];

        // seeding
        dp[totalSum - nums[0]][0] = 1;
        dp[totalSum + nums[0]][0] += 1;

        for (int i = 1; i < nums.length; i++) {

            for (int sum = 0; sum < rowNum; sum++) {

                if (sum - nums[i] >= 0) {
                    dp[sum][i] += dp[sum - nums[i]][i - 1];
                }
                if (sum + nums[i] < rowNum) {
                    dp[sum][i] += dp[sum + nums[i]][i - 1];
                }
            }
        }

        int result = totalSum + target;

        if (0 <= result && result < rowNum) {
            return dp[result][nums.length - 1];
        } else {
            return 0;
        }
    }

    public int recursionWithMemoization(int[] nums, int target) {
        int totalSum = IntStream.of(nums).sum();

        Integer[][] memoization = new Integer[nums.length + 1][2 * totalSum + 1];
        return compute(nums, totalSum, target, 0, 0, memoization);
    }

    int compute(int[] nums, int totalSum, int target, int curSum, int pos, Integer[][] memoization) {

        int t = curSum + totalSum;
        if (pos == nums.length) {
            if (target == curSum) {
                memoization[pos][t] = 1;
                return 1;
            }
            return 0;
        }

        if (memoization[pos][t] != null) {
            return memoization[pos][t];
        }

        int n = nums[pos];
        int r = compute(nums, totalSum, target, curSum - n, pos + 1, memoization)
                + compute(nums, totalSum, target, curSum + n, pos + 1, memoization);
        memoization[pos][t] = r;
        return r;

    }
}
