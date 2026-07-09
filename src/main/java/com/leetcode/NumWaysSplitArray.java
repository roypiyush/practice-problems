package com.leetcode;

public class NumWaysSplitArray {


    public static void main(String[] args) {

        int[] nums = {10, 4, -8, 7};
        System.out.println(new NumWaysSplitArray().waysToSplitArray(nums));
    }


    public int waysToSplitArray(int[] nums) {
        int size = nums.length;

        int rightSum = 0;
        for (int i = 0; i < size; i++) {
            rightSum += nums[i];
        }

        int count = 0;
        int leftSum = 0;
        for (int i = 0; i < size - 1; i++) {
            leftSum += nums[i];
            rightSum -= nums[i];

            count += leftSum >= rightSum ? 1 : 0;
        }

        return count;
    }
}
