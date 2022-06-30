package com.personal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {

    private static int sum(int[] arr, int i, int j, int k) {
        return arr[i] + arr[j] + arr[k];
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
//        int[] nums = {-2, 0, 1, 1, 2};
//        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> triplets = threeSum(nums);
        System.out.println(triplets);
    }

    private static List<List<Integer>> threeSum(final int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> triplets = new LinkedList<>();

        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            int k = nums.length - 1;

            // Problem is reduced to 2-sum and apply same logic
            while (j < k && j < nums.length && k >= 0) {
                final int sum = sum(nums, i, j, k);
                if (sum == 0) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    triplets.add(list);
                    // skip of repeating numbers. Handle duplicate triplets.
                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    j++;
                } else if (sum < 0) {
                    // skip of repeating numbers. Handle duplicate triplets.
                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    j++;
                } else {
                    // skip of repeating numbers. Handle duplicate triplets.
                    while (k - 1 >= 0 && nums[k - 1] == nums[k]) {
                        k--;
                    }
                    k--;
                }
            }
            // skip of repeating numbers. Handle duplicate triplets.
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
            i++;
        }
        return triplets;
    }


}
