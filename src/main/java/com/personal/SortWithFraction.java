package com.personal;

import com.lib.Utils;

import java.util.Arrays;

public class SortWithFraction {

    public static void main(String[] args) {
        int[] arr = new int[20];
        Utils.populateWithRandomValues(arr);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int a : arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }

        int[] temp = new int[arr.length];
        for (int a : arr) {
            double n = a - min;
            double d = max - min;
            double r = arr.length * n / d;
            int t = (int) (r);
            temp[t] = a;

        }

        // FIXME

        System.out.println(Arrays.toString(temp));

    }


}
