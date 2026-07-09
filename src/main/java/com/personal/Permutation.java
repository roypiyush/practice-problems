package com.personal;

import com.lib.Utils;

public class Permutation {

    public static void main(String[] args) {
        final char[] s = {'a', 'b', 'c'};
        new Permutation().permute(s, 0, s.length - 1);
    }

    public void permute(final char[] s, final int i, final int j) {

        if (i == j) {
            System.out.println(new String(s));
            return;
        }

        for (int k = i; k <= j; k++) {

            // swap first element with kth element
            Utils.swap(s, i, k);
            // s[i] done now permute remaining s[i + 1..j]
            permute(s, i + 1, j);
            // backtrack
            Utils.swap(s, i, k);
        }
    }
}
