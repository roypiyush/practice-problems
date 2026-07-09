package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {

    public static void main(String[] args) {

        int[] nums = {6,4,3,1};
        int k = 10;

        System.out.println(new SubArraySumEqualsK().prefixSumApproach(nums, k));
    }

    int prefixSumApproach(int[] nums, int k) {
        int size = nums.length;

        int prefix = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(prefix, 1);

        int count = 0;

        for (int i = 0; i < size; i++) {

            prefix += nums[i];
            count += freq.getOrDefault(prefix - k, 0);

            freq.put(prefix, freq.getOrDefault(prefix, 0) + 1);
        }

        return count;
    }
}
