package com.leetcode;

import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {

    Random rand = new Random();
    int[] nums;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int[] copy = Arrays.copyOf(nums, nums.length);

        for (int i = nums.length - 1; i > 0; i--) {
            int n = rand.nextInt(i);
            swap(copy, i, n);
        }

        return copy;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        final ShuffleArray shuffleArray = new ShuffleArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        System.out.println(Arrays.toString(shuffleArray.shuffle()));
        System.out.println(Arrays.toString(shuffleArray.reset()));
        System.out.println(Arrays.toString(shuffleArray.shuffle()));

    }
}
