package com.leetcode;

@SuppressWarnings("ALL")
public class ShortestDistance {
    public static void main(String[] args) {
        final int n = 15;
        int[][] queries = {{6, 11}, {7, 11}, {11, 14}};
        new ShortestDistance().shortestDistanceAfterQueries(n, queries);
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {

        int[] distances = new int[n];
        int[] references = new int[n];

        for (int i = 0; i < n; i++) {

            distances[i] = n - i - 1;

            if (n - i - 1 > 0) {
                references[n - i - 1] = n - i - 2;
            }
            if (n - i - 1 == 0) {
                references[0] = -1;
            }
        }

        int[] answers = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int[] query = queries[i];
            int src = query[0];
            int dst = query[1];

            updateReferences(distances, references, src, dst);

            answers[i] = distances[0];
        }

        return answers;
    }

    void updateReferences(int[] distances, int[] references, int src, int dst) {

        if (src >= dst) {
            return;
        }

        references[dst] = src;
        // if (distances[dst] + 1 >= distances[src]) {
        //     return;
        // }

        while (src >= 0) {
            distances[src] = Math.min(distances[src], distances[dst] + 1);
            dst = src;
            src = references[src];
        }
    }
}
