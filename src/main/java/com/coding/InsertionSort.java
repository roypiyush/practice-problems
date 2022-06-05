package com.coding;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {3, 6, 1, 334, 34, 234, 2, 2, 1, 6, 3, 7, 2, 734, 7854, 123, 7, 8, 12123, 5, 435, 234, 34};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertionSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int j = i++;
            while (j > 0) {
                if (arr[j - 1] > arr[j]) {
                    int t = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = t;
                    j--;
                } else {
                    break;
                }
            }
        }
    }
}
