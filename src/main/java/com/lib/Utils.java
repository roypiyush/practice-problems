package com.lib;

import java.util.Arrays;
import java.util.Random;

public class Utils {
    public static void populateWithRandomValues(int[] nums) {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(1000);
        }
        System.out.printf("%s -> Generated Array\n", Arrays.toString(nums));
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void swap(final char[] s, final int i, final int j) {
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
    }
}
