package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * MapHighestPeak
 */
public class MapHighestPeak {

    public static void main(final String[] args) {

        final MapHighestPeak mapHighestPeak = new MapHighestPeak();
        final int[][] isWater = { 
            {1, 0, 0, 0, 1},
            {0, 1, 0, 0, 0}
        };
        final int[][] result = mapHighestPeak.highestPeak(isWater);
        for (final int[] res : result) {
            System.out.println(Arrays.toString(res));
        }
    }

    private int[][] highestPeak(final int[][] isWater) {

        final Queue<int[]> queue = new LinkedList<>();

        final int rSize = isWater.length;
        final int cSize = isWater[0].length;

        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(new int[] { i, j });
                    isWater[i][j] = 0;
                } else {
                    isWater[i][j] = -1; // to set
                }
            }
        }

        while (!queue.isEmpty()) {
            final int[] node = queue.poll();
            final int i = node[0];
            final int j = node[1];

            if (i + 1 < rSize && isWater[i + 1][j] == -1) {
                isWater[i + 1][j] = isWater[i][j] + 1;
                queue.offer(new int[] { i + 1, j });
            }

            if (i - 1 >= 0 && isWater[i - 1][j] == -1) {
                isWater[i - 1][j] = isWater[i][j] + 1;
                queue.offer(new int[] { i - 1, j });
            }

            if (j + 1 < cSize && isWater[i][j + 1] == -1) {
                isWater[i][j + 1] = isWater[i][j] + 1;
                queue.offer(new int[] { i, j + 1 });
            }

            if (j - 1 >= 0 && isWater[i][j - 1] == -1) {
                isWater[i][j - 1] = isWater[i][j] + 1;
                queue.offer(new int[] { i, j - 1 });
            }

        }

        return isWater;

    }
}
