package com.leetcode;

public class BestSightSeeingPairs {
    public static void main(String[] args) {

        int[] values = {2, 5, 4, 2, 8};

        int result = 0;
        int maxSoFar = values[0];


        for (int i = 1; i < values.length; i++) {

            result = Math.max(result, maxSoFar + values[i] - i);

            maxSoFar = Math.max(maxSoFar, values[i] + i);
        }

        System.out.println(result);
    }
}
