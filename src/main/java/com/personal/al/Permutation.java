package com.personal.al;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Permutation {
    private static final Map<String, Integer> duplicates = new HashMap<>();

    public static void main(String[] args) {
        int[] value = {1, 1, 2, 2, 3, 3, 6, 6, 6, 6};
        printPermutations(value, 0, value.length - 1);
        int totalCount = 0;
        for (Map.Entry<String, Integer> e : duplicates.entrySet()) {
            //System.out.println(e.getKey());
            totalCount += e.getValue();
        }
        System.out.printf("Total: %s, Unique: %s\n", totalCount, duplicates.size());
    }

    private static void swap(final int[] value, final int s, final int e) {
        int t = value[s];
        value[s] = value[e];
        value[e] = t;
    }

    private static void printPermutations(final int[] value, final int s, final int e) {
        if (s == e) {
            final String str = Arrays.toString(value);
            duplicates.computeIfAbsent(str, k -> k.length() - k.length());
            duplicates.put(str, duplicates.get(str) + 1);
            return;
        }

        for (int i = s; i <= e;) {
            swap(value, s, i);
            printPermutations(value, s + 1, e);
            swap(value, s, i);
            while (i + 1 <= e && value[i] == value[i + 1]) {
                i++;
            }
            i++;
        }
    }

    private static boolean isSame(final int[] value, final int s, final int i) {
        return s != i && value[s] == value[i];
    }
}
