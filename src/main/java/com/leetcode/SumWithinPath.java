package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumWithinPath {

    public static void main(String[] args) {
        int[] arr = new int[]{-9, -1, -3, -1, 5, 3, -5, 6};
        SumWithinPath sumWithinPath = new SumWithinPath();
        sumWithinPath.pathSum(arr, -4);
        sumWithinPath.pathSum(arr, 4);
        sumWithinPath.pathSum(arr, 8);
        sumWithinPath.pathSum(arr, 3);
    }

    private void pathSum(int[] arr, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        int prefixSum = 0;
        int pos = 0;
        map.put(prefixSum, pos++);

        for (int a : arr) {
            prefixSum += a;
            map.put(prefixSum, pos);

            Integer exVal = map.get(prefixSum - targetSum);
            if (exVal != null) {
                System.out.printf("Found %d between [%d, %d] %s\n", targetSum, exVal, pos - 1, Arrays.toString(Arrays.copyOfRange(arr, exVal, pos)));
            }
            pos++;
        }
    }
}
