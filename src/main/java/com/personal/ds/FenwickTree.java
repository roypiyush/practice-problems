package com.personal.ds;

import java.util.Arrays;

/**
 * Binary Indexed Tree
 */
public class FenwickTree {
    private final int[] tree;

    public FenwickTree(final int[] arr) {
        tree = Arrays.copyOf(arr, arr.length + 1);
        // adjust
        int i = arr.length;
        while (i > 0) {
            tree[i] = tree[i - 1];
            i--;
        }

        tree[i] = 0;
        makeTree();
    }

    private void makeTree() {
        for (int i = 1; i < tree.length; i++) {
            int p = i + (i & -i); // parent
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

    public static void main(String[] args) {
        int[] arr = {2, -1, 3, 1, 4};
        final FenwickTree fenwickTree = new FenwickTree(arr);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d, ", fenwickTree.query(i));
        }
        System.out.println();
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
