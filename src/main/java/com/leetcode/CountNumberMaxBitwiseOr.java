package com.leetcode;

public class CountNumberMaxBitwiseOr {
    public static void main(String[] args) {

        int[] nums = new int[]{3, 2, 1, 5};
        System.out.println(new CountNumberMaxBitwiseOr().countMaxOrSubsets(nums));
    }

    public int countMaxOrSubsets(int[] nums) {

        if (nums.length == 1) {
            return 1;
        }

        int count = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < (1 << nums.length); i++) {
            int cur = getBitwiseOr(nums, i);
            if (cur > max) {
                count = 1;
                max = cur;
            } else if (cur == max) {
                count++;
            }
        }

        return count;
    }

    int getBitwiseOr(int[] nums, int i) {
        int or = 0;
        int pos = 0;

        while (i > 0) {
            if ((i & 1) == 1) {
                or = or | nums[pos];
            }
            pos++;
            i = i >> 1;
        }

        return or;
    }
}
