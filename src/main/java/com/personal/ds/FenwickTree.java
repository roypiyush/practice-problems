package com.personal.ds;

import java.util.Arrays;

/**
 * Binary Indexed Tree
 */
public class FenwickTree {
    private int tree[];

    void constructBIT(int[] arr) {
        tree = new int[arr.length + 1];
        int i = 1;
        while (i < arr.length + 1) {
            tree[i] = arr[i - 1];
            i++;
        }

        for (i = 1; i < arr.length + 1; i++) {
            int j = i + (i & -i);
            if (j <= arr.length) {
                tree[j] += tree[i];
            }
        }
    }

    void update(int value, int i) {
        i += 1;
        while (i < tree.length) {
            tree[i] += value;
            i = i + (i & -i);
        }
    }

    int query(int i) {
        i += 1;
        int sum = 0;
        while (i > 0) {
            sum += tree[i];
            i = i - (i & -i);
        }
        return sum;
    }

    int rangeQuery(int i, int j) {
        return query(j) - query(i - 1);
    }


    public static void main(String[] args) {
        int arr[] = {2, -1, 3, 1, 4};
        FenwickTree fenwickTree = new FenwickTree();
        fenwickTree.constructBIT(arr);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d, ", fenwickTree.query(i));
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                System.out.printf("Range query %d-%d is %d\n", i, j, fenwickTree.rangeQuery(i, j));
            }
        }
        fenwickTree.update(2, 3);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d, ", fenwickTree.query(i));
        }
    }
}
