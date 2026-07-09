package com.personal.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        new RadixSort().radixSort();
    }

    private void radixSort() {

        int[] arr = {212, 323, 531, 989, 989, 193, 175, 471, 1000};
        for (int pos = 0; pos < 4; pos++) {
            countingSortRadix(arr, pos);
        }
        System.out.println(Arrays.toString(arr));
    }

    int digitAt(int num, int pos) {
        int rem = num % 10;
        while (pos-- > 0) {
            num = num / 10;
            rem = num % 10;
        }
        return rem;
    }
    private void countingSortRadix(final int[] arr, final int pos) {
        int[] aux = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int d = digitAt(arr[i], pos);
            aux[d] = aux[d] + 1;
        }

        for (int i = 1; i < aux.length; i++) {
            aux[i] += aux[i - 1];
        }

        int[] sorted = new int[arr.length];
        for (int i = arr.length - 1; i >= 0 ; i--) {
            int d = digitAt(arr[i], pos);
            sorted[aux[d] - 1] = arr[i];
            aux[d]--;
        }

        System.arraycopy(sorted, 0, arr, 0, arr.length);
    }

}
