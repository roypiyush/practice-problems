package com.personal.al;

import java.util.Arrays;

public class CountingSort {
    
    public static void main(String[] args) {

        int[] arr = {8, 5, 2, 8, 0, 5, 3, 9, 1, 7, 3, 3, 6, 1, 8, 0, 
            9, 3, 5, 3, 2, 8, 6, 9, 7, 8, 0, 5, 9, 6, 9, 8, 8, 1, 0,
            9, 3, 0, 2, 5, 3, 0, 7, 4, 8, 2, 8, 9, 8, 8, 2, 4, 2, 9, 
            7, 6, 1, 8, 9, 8, 8, 7, 8, 9, 4, 4, 3, 2, 1, 4, 7, 7, 2, 
            6, 1, 2, 4, 5, 1, 3, 2, 1, 3, 1, 6, 8, 5, 1, 1, 5, 1, 7, 
            2, 6, 5, 8, 7, 6, 4, 7};

        arr = sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] sort(int[] arr) {
        int min = 0;
        int max = 9;

        // count frequencies to know how many times each number appear
        int[] freq = new int[max - min + 1];
        for (int a : arr) {
            freq[a]++;
        }

        // comulative sum will show where this number will land
        for (int i = min + 1; i <= max; i++) {
            freq[i] += freq[i - 1];
        }

        // now write to final array
        int[] result = new int[arr.length];

        // using reverse because freq[arr[j]] is already at max position 
        // for that element
        for (int j = arr.length - 1; j >= 0; j--) {
            int pos = freq[arr[j]];
            // adjust index because position is always +1
            result[pos - 1] = arr[j];

            // elements which don't appear in arr
            // remain untouched
            freq[arr[j]] = freq[arr[j]] - 1;
        }

        return result;
    }
}
