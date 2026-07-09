package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WaterAndJug {

    public static void main(String[] args) {
        WaterAndJug waterAndJug = new WaterAndJug();
        System.out.println(waterAndJug.canMeasureWater(3, 5, 4));
        System.out.println(waterAndJug.canMeasureWaterGCD(3, 5, 4));
    }

    public boolean canMeasureWaterGCD(int x, int y, int target) {
        int g = gcd(x, y);
        return target % g == 0;
    }

    int gcd(int x, int y) {
        while (y != 0) {
            int r = x % y;
            x = y;
            y = r;
        }
        return x;
    }

    public boolean canMeasureWater(int x, int y, int target) {
        return reachTarget(new HashSet<>(), x, y, target, new int[] { 0, 0 });
    }

    boolean reachTarget(Set<String> visited, int x, int y, int target, int[] buckets) {

        if (sum(buckets) == target) {
            return true;
        }

        String id = createId(buckets);
        if (visited.contains(id)) {
            return false;
        }

        visited.add(id);

        List<int[]> choices = enumerateChoices(x, y, buckets);
        for (int[] choice : choices) {
            int[] newBucket = applyChoice(buckets, choice);
            if (newBucket[0] < 0 || newBucket[1] < 0) {
                continue;
            }

            if (visited.contains(createId(newBucket))) {
                continue;
            }

            if (reachTarget(visited, x, y, target, newBucket)) {
                return true;
            }
        }

        return false;
    }

    int sum(int[] buckets) {
        return buckets[0] + buckets[1];
    }

    String createId(int[] buckets) {
        return String.format("%s-%s", buckets[0], buckets[1]);
    }

    List<int[]> enumerateChoices(int x, int y, int[] buckets) {
        List<int[]> choices = new ArrayList<>();

        // fill either
        choices.add(new int[] { x - buckets[0], 0 });
        choices.add(new int[] { 0, y - buckets[1] });

        // empty either
        choices.add(new int[] { -buckets[0], 0 });
        choices.add(new int[] { 0, -buckets[1] });

        // move
        int pourTo1 = Math.min(buckets[0], y - buckets[1]);
        choices.add(new int[] { -pourTo1, pourTo1 });

        int pourTo2 = Math.min(x - buckets[0], buckets[1]);
        choices.add(new int[] { pourTo2, -pourTo2 });

        return choices;
    }

    int[] applyChoice(int[] buckets, int[] choice) {
        return new int[] { buckets[0] + choice[0], buckets[1] + choice[1] };
    }
}
