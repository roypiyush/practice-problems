package com.leetcode;

import java.util.Arrays;

public class CanArrange {

    public static void main(String[] args) {
        final int[] arr = {75,5,-5,75,-2,-3,88,10,10,87};

        System.out.println(new CanArrange().hashMethod(arr, 85));
        System.out.println(new CanArrange().twoPointerWithSorting(arr, 85));

        System.out.println(new CanArrange().hashMethod(arr, 10));
        System.out.println(new CanArrange().twoPointerWithSorting(arr, 10));
    }

    boolean hashMethod(int[] arr, int k) {

        Integer[] numToCount = new Integer[k + 1];
        boolean isZeroFlip = false;

        int valueCount = 0;
        for (int n : arr) {

            int norm = normalize(k, n);

            if (norm == 0) {
                isZeroFlip = !isZeroFlip;
                norm = isZeroFlip ? k : norm;
            }

            int complement = k - norm;
            Integer count = numToCount[complement];

            if (count == null) {
                numToCount[norm] = (numToCount[norm] == null ? 0 : numToCount[norm]) + 1;
                valueCount++;
            } else {
                if (count - 1 == 0) {
                    numToCount[complement] = null;
                } else {
                    numToCount[complement]--;
                }
                valueCount--;
            }
        }

        return valueCount == 0;
    }

    int normalize(int k, int n) {
        int n1 = n % k;

        if (n1 >= 0) {
            return n1 % k;
        } else {
            return k + n1;
        }
    }

    boolean twoPointerWithSorting(int[] arr, int k) {

        int size = arr.length;
        if (size % 2 == 1) {
            return false;
        }

        normalizeArray(arr, size, k);

        Arrays.sort(arr);
        int s = 0; int e = size - 1;
        while (s < e) {
            if (arr[s] + arr[e] != k) {
                return false;
            }

            s++; e--;
        }

        return true;
    }

    void normalizeArray(int[] arr, int size, int k) {
        boolean isZeroFlip = false;
        for (int i = 0; i < size; i++) {
            int norm = normalize(k, arr[i]);
            if (norm == 0) {
                isZeroFlip = !isZeroFlip;
                norm = isZeroFlip ? k - norm : norm;
            }
            arr[i] = norm;
        }
    }


}
