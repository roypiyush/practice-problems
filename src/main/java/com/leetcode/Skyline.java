package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Skyline {

    public static void main(String[] args) {
        Skyline solution = new Skyline();
        int[][] buildings1 = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] buildings2 = { {2,9,10},{9,12,15}};
        System.out.println(solution.getSkyline(buildings1));
        System.out.println(solution.getSkyline(buildings2));
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {

        int[][] events = new int[buildings.length * 2][2];
        int i = 0;
        for (int[] building : buildings) {
            events[i][0] = building[0];
            events[i][1] = building[2];
            i++;
            events[i][0] = building[1];
            events[i][1] = -building[2];
            i++;
        }

        Arrays.sort(events, (a, b) -> a[0] == b[0]
                ? b[1] - a[1] // prefer higher buildings
                : a[0] - b[0]);

        SkylineStore store = new SkylineStore();

        List<List<Integer>> result = new ArrayList<>();

        int prevHeight = 0;
        for (i = 0; i < events.length; i++) {

            while (isNextSamePoint(events, i)) {
                consume(events, i, store);
                i++;
            }

            boolean isStart = events[i][1] > 0;

            if (isStart) {
                store.offer(events[i][1]);
                int height = store.peek();
                if (prevHeight < height) {
                    result.add(List.of(events[i][0], height));
                    prevHeight = height;
                }
            } else {
                store.remove(-events[i][1]);
                int height = store.peek();
                if (prevHeight > height) {
                    result.add(List.of(events[i][0], height));
                    prevHeight = height;
                }
            }
        }

        return result;
    }

    boolean isNextSamePoint(int[][] events, int i) {
        if (i + 1 == events.length) {
            return false;
        }

        return events[i][0] == events[i + 1][0];
    }

    void consume(int[][] events, int i, SkylineStore store) {
        boolean isStart = events[i][1] > 0;
        if (isStart) {
            store.offer(events[i][1]);
        } else {
            store.remove(-events[i][1]);
        }
    }

    class SkylineStore {

        List<Integer> list = new ArrayList<>();

        public void offer(int key) {
            int pos = Collections.binarySearch(list, key);
            if (pos < 0) {
                int i = -pos - 1;
                if (list.size() == i) {
                    list.add(key);
                } else {
                    list.add(i, key);
                }
            } else {
                list.add(pos, key);
            }
        }

        public void remove(int key) {
            int pos = Collections.binarySearch(list, key);
            if (pos < 0) {
                return;
            }
            list.remove(pos);
        }

        public int peek() {
            int size = list.size();
            if (size == 0) {
                return 0;
            } else {
                return list.get(size - 1);
            }
        }
    }
}
