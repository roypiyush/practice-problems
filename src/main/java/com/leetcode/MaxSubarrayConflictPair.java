package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxSubarrayConflictPair {

    public static void main(String[] args) {
        MaxSubarrayConflictPair sol = new MaxSubarrayConflictPair();
        long answer = sol.maxSubarrays(5, new int[][] { {1, 2}, {2, 5}, {3, 5} });
        System.out.println(answer);
    }

    public long maxSubarrays(int n, int[][] conflictingPairs) {
        Map<String, List<String>> pairToRanges = new HashMap<>();
        Map<String, Integer> rangeToPairFreq = new HashMap<>();

        populateMap(n, conflictingPairs, pairToRanges, rangeToPairFreq);

        long nonOverlaps = n * (n + 1) / 2 - rangeToPairFreq.keySet().size();
        long result = 0;
        for (int[] pair : conflictingPairs) {
            long subarrayCount = nonOverlaps + exclusiveCount(pairToRanges, rangeToPairFreq, pair);
            result = Math.max(result, subarrayCount);
        }

        return result;
    }

    int exclusiveCount(Map<String, List<String>> pairToRanges, Map<String, Integer> rangeToPairFreq, int[] pair) {
        int p0 = Math.min(pair[0], pair[1]);
        int p1 = Math.max(pair[0], pair[1]);

        String pairStr = String.format("%s-%s", p0, p1);
        int count = 0;

        for (String rangeStr : pairToRanges.getOrDefault(pairStr, Collections.emptyList())) {
            Integer c = rangeToPairFreq.getOrDefault(rangeStr, 0);
            if (c == 1) {
                count++;
            }
        }

        return count;
    }

    void populateMap(int n, int[][] conflictingPairs, Map<String, List<String>> pairToRanges,
            Map<String, Integer> rangeToPairFreq) {

        for (int[] pair : conflictingPairs) {
            int length = Math.max(pair[1], pair[0]) - Math.min(pair[0], pair[1]) + 1;
            while (length <= n) {
                rangeOfLength(pairToRanges, rangeToPairFreq, n, pair, length);
                length++;
            }
        }
    }

    void rangeOfLength(Map<String, List<String>> pairToRanges, Map<String, Integer> rangeToPairFreq, int n, int[] pair,
            int l) {
        int p0 = Math.min(pair[0], pair[1]);
        int p1 = Math.max(pair[0], pair[1]);

        int i = Math.max(1, p1 - l + 1);
        int j = i + l - 1;

        String pairStr = String.format("%s-%s", p0, p1);
        while (i <= p0 && j <= n) {
            // range (i, j)
            String rangeStr = String.format("%s-%s", i, j);
            pairToRanges.computeIfAbsent(pairStr, k -> new ArrayList<>()).add(rangeStr);
            rangeToPairFreq.compute(rangeStr, (k, v) -> v == null ? 1 : v + 1);
            i++;
            j++;
        }
    }
}

/*
  
Restate : how many of subarrays don't have conflicting pairs?

Representing Subarrays two options
1. Range [r1, r2]
2. Length [r1, l] where l = [1 n] possible values of length
r1 = [1 to n - l + 1]
r2 = r1 + l - 1

two blockers
1. all invalid subarrays
2. exclusive sub arrays

for a pair [a b] and n, there are m = a * (n - b - 1) subarrays for a < b
Representing subarrays
[1 1] [1 2] [1 3] [1 4] [1 5] [1 6] [2 2] [2 3] [2 4] [2 5] [2 6] [3 3] [3 4] [3 5] [3 6] [4 4] [4 5] [4 6] [5 5] [5 6] [6 6]


n = 6
[3 5] = 3 * (6 - 5 + 1) = 6 =>                   [1 5] [1 6]       [2 5] [2 6] [3 5] [3 6]
[1 2] = 1 * (6 - 2 + 1) = 5 => [1 2] [1 3] [1 4] [1 5] [1 6]
[2 4] = 2 * (6 - 4 + 1) = 6 =>             [1 4] [1 5] [1 6] [2 4] [2 5] [2 6]
[2 6] = 2 * (6 - 6 + 1) = 2 =>                         [1 6]             [2 6]



[1 2] = 1 * (6 - 2 + 1) = 5 => [1 2] [1 3] [1 4] [1 5] [1 6]
[2 4] = 2 * (6 - 4 + 1) = 6 =>             [1 4] [1 5] [1 6] [2 4] [2 5] [2 6]
[2 6] = 2 * (6 - 6 + 1) = 2 =>                         [1 6]             [2 6]
[3 5] = 3 * (6 - 5 + 1) = 6 =>                   [1 5] [1 6]       [2 5] [2 6] [3 5] [3 6]



Overlap between [1 2] [3 5] = [1 5] = 1 * (6 - 5 + 1) = 2 
Non-overlap [1 4]
Overlap between [1 2] [2 4] = [1 4] = 1 * (6 - 4 + 1) = 3  
Overlap between [2 4] [3 5] = [2 5] = 2 * (6 - 5 + 1) = 4
  
*/
