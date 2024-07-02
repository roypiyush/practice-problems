package com.coding;

import java.util.Arrays;

public class Permutation {

    private void swap(final char[] s, final int i, final int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }

    public void permute(final char[] s, final int i, final int j) {

        if (i == j) {
            System.out.println(new String(s));
            return;
        }

        for (int k = i; k <= j; k++) {
            swap(s, i, k);
            permute(s, i + 1, j);
            swap(s, i, k);
        }
    }

    public static void main(String[] args) {
        final char[] s = {'a', 'b', 'c'};
        new Permutation().permute(s, 0, s.length - 1);


        int[] arr = {1, 3, 2, 1};
        Arrays.sort(arr, 1, 4);
        System.out.println(Arrays.toString(arr));
    }
}
