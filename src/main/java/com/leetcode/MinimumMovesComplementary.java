package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lib.Pair;

public class MinimumMovesComplementary {
    public static void main(String[] args) {
        MinimumMovesComplementary solution = new MinimumMovesComplementary();
        int[] nums = {1, 2, 4, 3};
        int limit = 4;
        System.out.println(solution.minMoves(nums, limit));
    }

    public int minMoves(int[] nums, int limit) {
        // 1. sum and indices making the sum
        // 2. take the highest frequency to find complementary number with the highest sum
        Pair<Integer, Map<Integer, List<Integer>>> complementarySumIndices = computeComplementarySumIndices(nums);

        int complementarySum = complementarySumIndices.getKey();
        Map<Integer, List<Integer>> indices = complementarySumIndices.getValue();
        indices.remove(complementarySum);

        int moveCount = 0;
        for (Map.Entry<Integer, List<Integer>> e : indices.entrySet()) {

            if (complementarySum == 2 * limit) {
                // 3. if complementary sum is equal to 2 * limit than both the values will be changed
                moveCount += 2;
            } else {
                // 4. if complementary sum is less than 2 * limit than one value will be changed
                moveCount += 1;
            }
        }
        return moveCount;
    }

    private Pair<Integer, Map<Integer, List<Integer>>> computeComplementarySumIndices(int[] nums) {
        int sum = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length / 2; i++) {
            int s = sumPairs(nums, i);
            sum = Math.max(sum, s);
            map.computeIfAbsent(s, k -> new ArrayList<>()).add(i);
        }
        return Pair.of(sum, map);
    }

    private int sumPairs(int[] nums, int i) {
        return nums[i] + nums[nums.length - i - 1];
    }

}
