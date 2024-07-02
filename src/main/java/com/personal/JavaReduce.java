package com.personal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JavaReduce {

    private void swap(final int[] inputArray, final int i, final int j) {
        int t = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = t;
    }

    void permute(List<List<Integer>> result, int[] nums, int i, int j) {

        int size = nums.length;
        if (i == j) {
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }

        int k = i - 1;

        while (++k < nums.length) {
            if (isPreviousEqual(nums, k)) {
                continue;
            }

            swap(nums, i, k);
            permute(result, nums, i + 1, j);
            swap(nums, i, k);
        }
    }

    private boolean isPreviousEqual(int[] nums, int i) {
        if (i - 1 < 0) {
            return false;
        }

        return nums[i - 1] == nums[i];
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        permute(result, nums, 0, nums.length - 1);
        return result;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(new JavaReduce().permuteUnique(nums));
    }
}
