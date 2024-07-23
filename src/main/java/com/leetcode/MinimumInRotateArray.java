package com.leetcode;

public class MinimumInRotateArray {
    public static void main(String[] args) {
        System.out.println(new MinimumInRotateArray().findMin(new int[]{3, 1, 3}));
        System.out.println(new MinimumInRotateArray().findMin(new int[]{3, 3, 3, -1, 2}));
    }

    public int findMin(int[] nums) {

        int s = 0;
        int e = nums.length - 1;

        if (nums[s] < nums[e]) {
            return nums[s];
        }

        // It is rotated array

        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (getValue(nums, mid - 1) > getValue(nums, mid)
                    && getValue(nums, mid) <= getValue(nums, mid + 1)) {

                return nums[mid];

            } else if (nums[mid] < nums[0]) {

                e = mid - 1;

            } else {

                s = mid + 1;

            }
        }

        return -1;
    }

    int getValue(int[] nums, int pos) {

        return (pos < 0 || pos >= nums.length)
                ? Integer.MAX_VALUE : nums[pos];
    }
}
