package com.leetcode;

import java.util.Arrays;

public class CountFairPairs {

    public static void main(String[] args) {

        final int[] array = new int[] { 33474, 3048, 55017, 6445, 83617, 84128, 58441, 57814, 84786, 86049, 44898,
                26268, 80222, 20088, 85422, 69658, 11264, 42707, 98979, 36518, 7115, 2473, 98444, 83343 };
        Arrays.sort(array);
        final int lower = 3048;
        final int upper = 58441;

        long count = findLower(array, 0, array.length - 1, upper + 1) - findLower(array, 0, array.length - 1, lower);
        System.out.println(count);
        count = findLower(array, 1, array.length - 1, upper + 1) - findLower(array, 0, array.length - 1, lower);
        System.out.println(count);
    }

    static long findLower(int[] array, int i, int j, int target) {
        long result = 0;
        while (i < j) {
            int sum = array[i] + array[j];
            if (sum < target) {
                result += (j - i);
                i++;
            } else {
                j--;
            }
        }

        return result;
    }

    static int lowerBoundUsingBinarySearch(int[] array, int start, int end, int key) {
        /*
         * len = 6 key = 3
         * 0,1,3,4,5,7
         * s = 1, e = 5, m = 3
         * s = 1, e = 2, m = 1
         * s = 2, e = 2, m = 2
         * s = 2, e = 1, m = 2
         */
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    static int binarySearchLowerBound(int[] array, int start, int end, int key) {
        // 0,1,4,4,5,7
        // l = 3 h = 6
        while (start <= end) {

            int mid = start + ((end - start) / 2);

            // array[i - 1] > l && array[i] <= l
            if ((start <= mid - 1 ? array[mid - 1] : Integer.MIN_VALUE) < key && key <= array[mid]) {

                return mid;

            } else if (array[mid] < key) {

                start = mid + 1;

            } else {

                end = mid - 1;
            }
        }
        return -1;
    }

    static int binarySearchUpperBound(int[] array, int start, int end, int key) {

        while (start <= end) {

            int mid = start + ((end - start) / 2);

            // array[i] <= h && h < array[i + 1]
            if (array[mid] <= key && key < (mid + 1 <= end ? array[mid + 1] : Integer.MAX_VALUE)) {

                return mid;

            } else if (array[mid] <= key) {

                start = mid + 1;

            } else {

                end = mid - 1;
            }
        }
        return -1;
    }

}
