package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public static void main(String[] args) {

        final int[] nums = { 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0,
                0,
                1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0,
                0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1 };

        final ContiguousArray contiguousArray = new ContiguousArray();
        System.out.println(contiguousArray.bruteForce(nums));
        System.out.println(contiguousArray.findMaxLength(nums));
    }

    public int findMaxLength(int[] nums) {
        int prefixSum = 0;
        int max = 0;

        Map<Integer, Integer> map = new HashMap<>();

        map.put(prefixSum, -1);

        for (int i = 0; i < nums.length; i++) {

            prefixSum += (nums[i] == 0 ? -1 : 1);

            Integer pos = map.get(prefixSum);

            if (pos == null) {
                map.put(prefixSum, i);

            } else {

                max = Math.max(max, i - pos);
            }

        }

        return max;
    }

    int binarySearch(int[] arr, int key) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            int m = i + (j - i) / 2;
            if (arr[m] < key) {
                i = m + 1;
            } else {
                j = m;
            }
        }

        return i;
    }

    int bruteForce(int[] nums) {
        int max = 0;

        for (int i = 0; i < nums.length; i++) {

            int c0 = 0;
            int c1 = 0;

            for (int j = i; j < nums.length; j++) {

                if (nums[j] == 0) {
                    c0++;
                } else {
                    c1++;
                }

                if (c0 == c1) {
                    if (max < j - i + 1) {
                        max = j - i + 1;
                        System.out.printf("starting from %s to %s len=%s\n", i, j, max);
                    }
                    max = Math.max(max, j - i + 1);
                }
            }
        }

        return max;
    }
}
