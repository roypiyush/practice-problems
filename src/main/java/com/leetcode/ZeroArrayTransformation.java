package com.leetcode;

public class ZeroArrayTransformation {

    public static void main(String[] args) {
        int[] nums = { 1,1,1,1,0, 2 };
        int[][] queries = { { 0, 2 }, { 3, 5} };
        System.out.println(new ZeroArrayTransformation().isZeroArray(nums, queries));
    }

    public boolean isZeroArray(int[] nums, int[][] queries) {
        
        int[] eventTrackers = new int[nums.length + 1];

        for (int[] query : queries) {
            // increase event count to denote processing -1 within the range
            eventTrackers[query[0]]++;
            // increase event count to denote end of processing -1 within the range
            eventTrackers[query[1] + 1]--;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // track sum for range
            sum += eventTrackers[i];
            if (sum < nums[i]) {
                return false;
            }
        }

        return true;
    }
}
