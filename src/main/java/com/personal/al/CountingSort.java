package com.personal.al;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {8, 6, 3, 1, 5, 7, 2, 7, 2, 1, 6, 7, 2, 9, 1, 0, 0};
        linearSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void linearSort(final int[] arr) {
        int max = 9; // already known
        int[] countingArray = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            countingArray[arr[i]] += 1;
        }

        // cumulative frequencies
        for (int i = 1; i < countingArray.length; i++) {
            countingArray[i] = countingArray[i] + countingArray[i - 1];
        }

        int sortedArray[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            sortedArray[countingArray[arr[i]] - 1] = arr[i];
            countingArray[arr[i]] -= 1;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sortedArray[i];
        }
    }
}
