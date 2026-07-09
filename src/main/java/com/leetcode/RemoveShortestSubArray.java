package com.leetcode;

import java.util.Arrays;

public class RemoveShortestSubArray {
    public static void main(String[] args) {
        int[] arr = { 6, 3, 10, 11, 15, 20, 13, 3, 18, 12 };

        RemoveShortestSubArray solution = new RemoveShortestSubArray();
        // System.out.println(solution.bruteForce(arr));
        System.out.println(solution.twoPointer(arr));
    }

    int twoPointer(int[] arr) {
        /*
         * time complexity O(n) space complexity = O(1)
         */

        int size = arr.length;
        int leftEnd = 0;
        int rightStart = size - 1;

        while (leftEnd < size) {
            if (get(arr, size, leftEnd) > get(arr, size, leftEnd + 1)) {
                break;
            }
            leftEnd++;
        }

        if (leftEnd == size) {
            return 0;
        }

        while (rightStart >= 0) {
            if (get(arr, size, rightStart - 1) > get(arr, size, rightStart)) {
                break;
            }
            rightStart--;
        }

        // at this point we have identified sorted list portion of the list

        int result = Math.min(size - leftEnd - 1, rightStart);

        leftEnd++;

        int l = 0;
        while (l <= leftEnd && rightStart < size) {
            if (arr[l] <= arr[rightStart] && get(arr, size, l - 1) <= arr[l]) {
                l++;
            } else {
                result = Math.min(result, rightStart - l);
                rightStart++;
            }
        }

        return result;
    }

    int get(int[] arr, int size, int pos) {
        if (pos < 0) {
            return Integer.MIN_VALUE;
        } else if (pos >= size) {
            return Integer.MAX_VALUE;
        } else {
            return arr[pos];
        }
    }

    int binarySearch(int[] arr) {

        /*
         * time complexity O(nlog(n)) space complexity = O(1)
         */

        int size = arr.length;
        int l = 0;
        int r = size - 1;

        while (l < size) {
            if (get(arr, size, l) > get(arr, size, l + 1)) {
                break;
            }
            l++;
        }

        if (l == size) {
            return 0;
        }

        while (r >= 0) {
            if (get(arr, size, r - 1) > get(arr, size, r)) {
                break;
            }
            r--;
        }

        // at this point we have identified sorted list portion of the list

        int result = Math.min(size - l - 1, r);

        while (r < size) {
            int key = arr[r];

            int ptr = Arrays.binarySearch(arr, 0, l + 1, key);

            if (ptr == -1) {
                // remove the first portion
                result = Math.min(result, r);

            } else if (ptr < 0) {
                ptr = -ptr - 1;
                result = Math.min(result, r - ptr);
            } else {

                while (get(arr, size, ptr) == get(arr, size, ptr + 1)) {
                    ptr++;
                }
                result = Math.min(result, r - ptr - 1);

            }

            r++;
        }

        return result;

    }

    public int bruteForce(int[] arr) {

        int size = arr.length;
        if (size < 2) {
            return 0;
        }

        for (int l = 0; l < size; l++) {
            for (int i = 0; i <= size - l; i++) {
                final int s = checkSorted(arr, i, l, size);
                if (s >= 0) {
                    return s;
                }
            }
        }

        // will not reach here
        return -1;
    }

    private static int checkSorted(final int[] arr, final int i, final int l, final int size) {
        int rangeEnd = i + l;

        int ptr = 0;
        int prev = Integer.MIN_VALUE;
        while (ptr < size) {
            if (i <= ptr && ptr < rangeEnd) {
                ptr++;
                continue;
            }
            if (prev > arr[ptr]) {
                return -1;
            }
            prev = arr[ptr];
            ptr++;
        }

        return l;
    }

    boolean checkSorted(int[] arr, int start, int end) {
        int prev = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (prev > arr[i]) {
                return false;
            }
            prev = arr[i];
        }

        return true;
    }
}
