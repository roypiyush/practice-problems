package com.leetcode;

public class BurstBalloons {
    public static void main(String[] args) {
        int[] balloons = new int[]{3, 1, 5, 8};
        System.out.println(new BurstBalloons().maxCoins(balloons));
    }

    public int maxCoins(int[] nums) {

        int size = nums.length;
        nums = convertToPadded(nums);
        int[][] dp = new int[size + 2][size + 2];

        for (int len = 1; len <= size; len++) {               // len balloons → interval size len
            for (int i = 1; i <= size - len + 1; i++) {       // start index i
                int j = i + len - 1;                       // end index j
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1]
                            + dp[k + 1][j]
                            + burst(nums, i - 1, k, j + 1));
                }
            }
        }

        return dp[1][size];
    }

    int burst(int[] nums, int i, int k, int j) {
        return nums[i] * nums[k] * nums[j];
    }

    int[] convertToPadded(int[] balloons) {
        int[] nums = new int[balloons.length + 2];
        nums[0] = nums[balloons.length + 1] = 1;
        int i = 1;
        for (int b : balloons) nums[i++] = b;
        return nums;
    }

}
