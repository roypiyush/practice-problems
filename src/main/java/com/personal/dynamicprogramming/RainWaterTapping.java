package com.personal.dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;

public class RainWaterTapping {

    private static int getTrappedWater(int[] buildings) {
        int storedWater = 0;
        int ci = 0;

        int maxTowerSoFar = ci;
        final Deque<Integer> maxTowerPriorityQueue = new LinkedList<>();
        maxTowerPriorityQueue.push(ci);
        ci++;
        for (; ci < buildings.length; ci++) {
            if (buildings[ci] >= buildings[maxTowerSoFar]) {
                storedWater = storedWater + calculateTrappedWater(buildings, maxTowerSoFar, ci);
                maxTowerSoFar = ci;
                maxTowerPriorityQueue.clear();
                maxTowerPriorityQueue.push(ci);
            } else {
                while (maxTowerPriorityQueue.peekFirst() != null && buildings[ci] > buildings[maxTowerPriorityQueue.peekFirst()]) {
                    maxTowerPriorityQueue.pop();
                }
                maxTowerPriorityQueue.push(ci);
            }
        }
        int lastElement = maxTowerPriorityQueue.isEmpty() ? 0 : maxTowerPriorityQueue.pop();
        while (!maxTowerPriorityQueue.isEmpty()) {
            int current = maxTowerPriorityQueue.pop();
            storedWater += calculateTrappedWater(buildings, current, lastElement);
            lastElement = current;
        }
        return storedWater;
    }

    private static int calculateTrappedWater(final int[] buildings, final int last, final int current) {

        if (current - last - 1 <= 0) {
            return 0;
        }
        int i = last + 1;
        int sum = 0;
        while (i < current) {
            sum += buildings[i];
            i++;
        }
        return Math.min(buildings[last], buildings[current]) * (current - last - 1) - sum;
    }

    public static void main(String[] args) {
        System.out.println(getTrappedWater(new int[]{5, 4, 4, 3, 1, 6, 3, 1, 4, 3, 1, 5, 4}));
        System.out.println(getTrappedWater(new int[]{2, 5, 3, 2, 5, 6, 3}));
        System.out.println(getTrappedWater(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
