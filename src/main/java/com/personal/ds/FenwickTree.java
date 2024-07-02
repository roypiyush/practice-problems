package com.personal.ds;

import java.util.Arrays;

/**
 * Binary Indexed Tree
 */
public class FenwickTree {
    private final int[] tree;

    public FenwickTree(final int[] arr) {
        tree = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            tree[i] = arr[i - 1];
        }

        // prepare the tree
        for (int i = 1; i < tree.length; i++) {
            int p = i + (i & -i);
            // update next parent only. The parent, p will cumulate its parent when called
            if (p < tree.length) {
                tree[p] += tree[i];
            }
        }
    }

    private void update(final int value, final int i) {
        int cur = i + 1;
        tree[cur] += value;

        while (cur < tree.length) {
            // keep updating parents
            final int p = cur + (cur & -cur);
            if (p < tree.length) {
                tree[p] += tree[i];
            }
            cur = p;
        }
    }

    private int query(final int i) {
        int cur = i + 1;
        int sum = 0;
        while (cur > 0) {
            sum += tree[cur];
            cur -= cur & -cur;
        }
        return sum;
    }

    private int rangeQuery(final int i, final int j) {
        return query(j) - query(i - 1);
    }

    private int elementAt(final int i) {
        return query(i) - query(i - 1);
    }

    public static void main(String[] args) {
        int[] arr = {2, -1, 3, 1, 4};
        final FenwickTree fenwickTree = new FenwickTree(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d, ", fenwickTree.query(i));
        }
        System.out.println(" -> Fenwick Tree");

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d, ", fenwickTree.elementAt(i));
        }
        System.out.println(" -> Original Array");

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                System.out.printf("Range query [%d, %d] is %d%n", i, j, fenwickTree.rangeQuery(i, j));
            }
        }
        fenwickTree.update(2, 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("Sum [0, %d] is %d%n", i, fenwickTree.query(i));
        }
    }
}
