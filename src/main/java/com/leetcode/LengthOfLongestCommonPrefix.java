package com.leetcode;

import java.util.Arrays;

public class LengthOfLongestCommonPrefix {

    public static void main(String[] args) {
        int[] arr1 = {657, 1916, 3207, 555, 701, 657, 1312, 3209, 2601, 1319, 4988, 4131, 4072, 2084, 576, 581, 2955, 2539, 4809, 2579, 1761, 3231, 1400, 4359, 60, 1058, 2801, 1126, 2660, 154, 515, 3321, 1040, 397, 2427, 4127, 4725, 804, 707, 1960, 3956, 2583, 4333, 4796, 3286, 2772, 4126, 2470, 1683, 2991};
        int[] arr2 = {1724, 1423, 3422, 210, 484, 491, 796, 246, 3577, 38, 4639, 3456, 2857, 299, 1486, 4928, 3738, 1285, 3849, 4837, 2278, 754, 2272, 1359, 2821, 3027, 89, 1164, 2859, 449, 3436, 125, 688, 2707, 4401, 2511, 1636, 4214, 4209, 3431, 850, 4044, 4571, 1454, 3188, 1432, 4150, 2105, 4196, 821};
        System.out.println(new LengthOfLongestCommonPrefix().longestCommonPrefix(arr1, arr2));
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {

        /*
         Time: O(n) + O(m) + O(nlog(n)) + O(mlog(m)) + O(min(m, n))
         Space: O(n) + O(m) : This can be reduced easily.
         */

        Integer[] a1 = new Integer[arr1.length];
        Integer[] a2 = new Integer[arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            a1[i] = arr1[i];
        }

        for (int i = 0; i < arr2.length; i++) {
            a2[i] = arr2[i];
        }

        Arrays.sort(a1, (i1, i2) -> ("" + i1 + i2).compareTo("" + i2 + i1));
        Arrays.sort(a2, (i1, i2) -> ("" + i1 + i2).compareTo("" + i2 + i1));

        int size1 = a1.length;
        int size2 = a2.length;

        int longestCommonPrefix = 0;

        int i = 0;
        int j = 0;

        while (i < size1 && j < size2) {
            int x1 = a1[i];
            int x2 = a2[j];

            int prefixLength = commonPrefix(x1, x2);
            longestCommonPrefix = Math.max(longestCommonPrefix, prefixLength);

            int compare = ("" + x1 + x2).compareTo("" + x2 + x1);
            if (compare <= 0) {
                // x1 <= x2
                i++;
            } else {
                j++;
            }
        }


        return longestCommonPrefix;
    }

    int commonPrefix(int a1, int a2) {

        int s1 = lengthOfInt(a1);
        int s2 = lengthOfInt(a2);

        if (s1 < s2) {

            int m = s2 - s1;
            int d = 1;
            while (m > 0) {
                d *= 10;
                m--;
                s2--;
            }

            a2 = a2 / d;
        }

        if (s1 > s2) {

            int m = s1 - s2;
            int d = 1;
            while (m > 0) {
                d *= 10;
                m--;
                s1--;
            }

            a1 = a1 / d;
        }


        while (a1 != a2) {
            a1 /= 10;
            a2 /= 10;
            s1--;
        }

        return s1;

    }

    int lengthOfInt(int i) {
        int size = 0;
        while (i > 0) {
            i /= 10;
            size++;
        }

        return size;
    }
}
