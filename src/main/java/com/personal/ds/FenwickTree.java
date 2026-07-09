package com.personal.ds;

import java.util.Arrays;

/**
 * Binary Indexed Tree
 */
public class FenwickTree {
    private final int[] tree;

    public FenwickTree(final int[] arr) {
        tree = new int[arr.length + 1];
        for (int i = 1; i < tree.length; i++) {
            _update(arr[i - 1], i);
        }
    }

    /**
     * accepts "delta" which is then propagated
     *
     * @param delta - change which is to be passed
     * @param i
     */
    private void _update(final int delta, final int i) {
        int j = i;
        while (j < tree.length) {
            tree[j] += delta;
            j += (j & -j);
        }
    }

    public void update(final int delta, final int i) {
        _update(delta, i + 1);
    }

    /**
     * Sum of all numbers from 1 to i
     * @param i - upper bound (inclusive)
     * @return sum of all numbers from 1 to i
     */
    private int query(final int i) {
        int j = i;
        int sum = 0;
        while (j > 0) {
            sum += tree[j];
            j -= (j & -j);
        }
        return sum;
    }

    private int prefixSum(int index) {
        return query(index + 1);
    }

    private int rangeQuery(final int i, final int j) {
        return prefixSum(j) - prefixSum(i - 1);
    }

    private int elementAt(final int i) {
        return rangeQuery(i, i);
    }

    public static void main(String[] args) {
        int[] arr = {2, -1, 3, 1, 4};
        final FenwickTree fenwickTree = new FenwickTree(arr);

        int[] computedArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            computedArray[i] = fenwickTree.elementAt(i);
        }

        if (!Arrays.equals(computedArray, arr)) {
            throw new RuntimeException("Recreated array has error");
        }

        int[] expectedRange = {2, 1, 4, 5, 9, -1, 2, 3, 7, 3, 4, 8, 1, 5, 4};
        int[] actualRange = new int[expectedRange.length];

        int ptr = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                actualRange[ptr++] = fenwickTree.rangeQuery(i, j);
            }
        }

        if (!Arrays.equals(expectedRange, actualRange)) {
            throw new RuntimeException("Array range queries unsuccessful");
        }

        System.out.println("Updating element with index=2 by 3");
        fenwickTree.update(2, 3);
        int[] expectedUpdatedArray = {2, -1, 3, 3, 4};
        int[] actualArray = new int[expectedUpdatedArray.length];

        ptr = 0;
        for (int i = 0; i < arr.length; i++) {
            actualArray[ptr++] = fenwickTree.elementAt(i);
        }

        if (!Arrays.equals(expectedUpdatedArray, actualArray)) {
            throw new RuntimeException("Array mismatch after change");
        }
    }
}
