package com.personal;

import com.lib.Utils;

import java.util.Arrays;

public class Permutation {

    public void permute(final char[] s, final int i, final int j) {

        if (i == j) {
            System.out.println(new String(s));
            return;
        }

        for (int k = i; k <= j; k++) {
            Utils.swap(s, i, k);
            permute(s, i + 1, j);
            Utils.swap(s, i, k);
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
