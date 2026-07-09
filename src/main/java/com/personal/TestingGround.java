package com.personal;

public class TestingGround {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 3, 2, 1};
        System.out.println(nums[new TestingGround().findPeak(nums)]);
    }

    public int findPeak (int[] nums) {

        int n = nums.length;
        int i = 0;
        int j = n - 1;

        while (i <= j) {
            int mid = i + (j - i) / 2;

            // mid greater than neighboring values;
            if (getValue(nums, mid, n) >= getValue(nums, mid - 1, n)
                    && getValue(nums, mid, n) > getValue(nums, mid + 1, n)) {
                return mid;
            } else if (getValue(nums, mid - 1, n) < getValue(nums, mid, n)) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return -1;
    }

    public int getValue(int[] nums, int i, int n) {
        if (i >= n || i < 0) {
            return Integer.MIN_VALUE;
        }

        return nums[i];
    }


}
