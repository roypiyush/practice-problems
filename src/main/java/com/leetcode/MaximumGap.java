package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

public class MaximumGap {
    public static void main(String[] args) {

        int[] arr = {100, 3, 2, 1};
        System.out.println(new MaximumGap().maximumGap(arr));
    }

    private int pos(int i, int min, int max, int numBucket) {
        return (int) ((double) (i - min + 1) / (max - min + 1) * (numBucket - 1));
    }

    public int maximumGap(int[] nums) {
        int[][] bucket = new int[nums.length][2];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i][0] = -1;
            bucket[i][1] = -1;
        }

        int minElement = Arrays.stream(nums).reduce(Integer.MAX_VALUE, (left, right) -> Math.min(left, right));
        int maxElement = Arrays.stream(nums).reduce(Integer.MIN_VALUE, (left, right) -> Math.max(left, right));

        for (int num : nums) {
            int posBucket = pos(num, minElement, maxElement, nums.length);

            int[] range = bucket[posBucket];
            if (range[0] == -1) {
                // not set
                range[0] = num;
                range[1] = num;
            } else {
                range[0] = Math.min(range[0], num);
                range[1] = Math.max(range[1], num);
            }
        }

        int prev = 0;
        while (prev < bucket.length && bucket[prev][1] == -1) {
            prev++;
        }
        int max = bucket[prev][1] - bucket[prev][0];

        int next = prev + 1;
        while (next < bucket.length && bucket[next][1] == -1) {
            next++;
        }

        new HashMap<Integer, String>().computeIfAbsent(1, k -> null);
        while (next < bucket.length) {
            int m = bucket[next][0] - bucket[prev][1];
            max = Math.max(max, m);

            prev = next;
            next++;
            while (next < bucket.length && bucket[next][1] == -1) {
                next++;
            }
        }

        return max;
    }
}
