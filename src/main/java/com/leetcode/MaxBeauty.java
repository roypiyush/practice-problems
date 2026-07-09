package com.leetcode;

import java.util.Arrays;

public class MaxBeauty {

    public static void main(String[] args) {
        final int k = 2;
        final int[] nums = {4, 6, 1, 2};
        System.out.println(new MaxBeauty().maximumBeauty(nums, k));
    }
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);

        int[][] ranges = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            ranges[i] = new int[] {nums[i] - k, nums[i] + k};
        }

        int longestOverlapping = 1;
        int currentOverlapping = 1;
        for (int i = 1; i < nums.length; i++) {
            if (isOverlapping(ranges[i - 1], ranges[i])) {
                currentOverlapping++;
            } else {
                currentOverlapping = 1;
            }

            if (currentOverlapping > longestOverlapping) {
                longestOverlapping = currentOverlapping;
            }
        }

        return longestOverlapping;
    }

    boolean isOverlapping(int[] a, int[] b) {
        return (a[0] <= b[0] && b[0] <= a[1])
                || (a[0] <= b[1] && b[1] <= a[1]);
    }

}
