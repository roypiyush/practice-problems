package com.leetcode;

public class NextPermutation {

    private void swap(final int[] inputArray, final int i, final int j) {
        int t = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = t;
    }

    void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    public void nextPermutation(int[] nums) {
        final int arraySize = nums.length;
        int i = arraySize - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i > -1) {
            int j = arraySize - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, arraySize - 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        new NextPermutation().nextPermutation(nums);
    }
}
