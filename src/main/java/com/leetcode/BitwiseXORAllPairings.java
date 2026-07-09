package com.leetcode;

public class BitwiseXORAllPairings {

    public static void main(String[] args) {
        int[] nums1 = {2, 1, 3};
        int[] nums2 = {10, 2, 5, 0};

		System.out.println(new BitwiseXORAllPairings().xorAllNums(nums1, nums2));
    }

	private int xorAllNums(int[] nums1, int[] nums2) {

        int res1 = 0;
        int res2 = 0;

        if (nums2.length % 2 == 1) {
            for (int n : nums1) {
                res1 ^= n;
            }
        }

        if (nums1.length % 2 == 1) {
            for (int n : nums2) {
                res2 ^= n;
            }
        }

        return res1 ^ res2;

	}

}
