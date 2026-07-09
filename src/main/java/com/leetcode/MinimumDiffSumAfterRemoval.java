package com.leetcode;

import java.util.PriorityQueue;

public class MinimumDiffSumAfterRemoval {
    public static void main(String[] args) {
        
        MinimumDiffSumAfterRemoval sol = new MinimumDiffSumAfterRemoval();
        int[] nums = new int[] {2, 9, 4, 1, 2, 6};
        System.out.println(sol.minimumDifference(nums));
    }

    public long minimumDifference(int[] nums) {

        int size = nums.length;
        int n = size / 3;

        PriorityQueue<Integer> leftHolder = new PriorityQueue<>((a, b) -> (b - a));
        PriorityQueue<Integer> rightHolder = new PriorityQueue<>();

        long min = Long.MAX_VALUE;
        long[] partition = new long[n + 1];
       
        int k = 0;
        long leftSum = 0;
        for (int j = 0; j < n; j++) {
            leftHolder.offer(nums[j]);
            leftSum += nums[j];
        }

        partition[k++] = leftSum;

        for (int j = n; j < 2*n; j++) {
            leftHolder.offer(nums[j]);
            leftSum += nums[j];
            leftSum -= leftHolder.poll();
            partition[k++] = leftSum;
        }
        
        long rightSum = 0;
        for (int j = size - 1; j >= 2 * n; j--) {
            rightHolder.offer(nums[j]);
            rightSum += nums[j];
        }

        min = Math.min(min, partition[--k] - rightSum);

        for (int j = 2 * n - 1; j >= n; j--) {
            rightHolder.offer(nums[j]);
            rightSum += nums[j];
            rightSum -= rightHolder.poll();

            long diff = partition[--k] - rightSum;
            min = Math.min(min, diff);
        }

        return min;
    }
}
