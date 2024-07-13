package com.coding;

import java.util.Arrays;

public class BucketSort {

    private static int hash(int i, int min, int max, int numberOfBuckets) {
        return (int) ((double) (i - min) / max * (numberOfBuckets));
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int min = Arrays.stream(arr).reduce(Integer.MAX_VALUE, (left, right) -> Math.min(left, right));
        int max = Arrays.stream(arr).reduce(Integer.MIN_VALUE, (left, right) -> Math.max(left, right));
        System.out.printf("min=%s max=%s\n", min, max);

        for (int i = 1; i <= arr.length; i++) {
            printArrayWithNumBuckets(arr, min, max, i);
        }

    }

    private static void printArrayWithNumBuckets(final int[] arr, final int min, final int max, int numberOfBuckets) {
        System.out.println("\nNumber of Buckets = " + numberOfBuckets);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(hash(arr[i], min, max, numberOfBuckets) + " ");
        }
        System.out.println();
    }
}
