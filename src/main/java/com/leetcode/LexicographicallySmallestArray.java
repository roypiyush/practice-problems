package com.leetcode;

import java.util.Arrays;

public class LexicographicallySmallestArray {
    public static void main(String[] args) {
        LexicographicallySmallestArray smallestArray = new LexicographicallySmallestArray();
        int[] nums = {4,52,38,59,71,27,31,83,88,10};
        int limit = 14;
        System.out.println(Arrays.toString(smallestArray.lexicographicallySmallestArray(nums, limit)));
    }

    private int[] lexicographicallySmallestArray(final int[] nums, final int limit) {

        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;

            for (int j = i + 1; j < nums.length; j++) {
                if (isMin(nums[minIndex], nums[j], limit)) {
                    minIndex = j;
                }
            }
            int t = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = t;
        }

        return nums;
    }

    boolean isMin(int min, int cur, int limit) {
        return cur < min && min - cur <= limit;
    }
}
