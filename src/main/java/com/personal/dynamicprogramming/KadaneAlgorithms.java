/**
 *
 */
package com.personal.dynamicprogramming;

/**
 * @author piyush
 */
public class KadaneAlgorithms {

    public static void main(String[] args) {

        int[] arr = {8, -17, 6, 4, -5, 2, -1, 3, 8, -9};
        kadane(arr);
    }

    private static void kadane(final int[] arr) {
        int maxLocal = arr[0];
        int maxGlobal = arr[0];

        int start = 0;

        int startGlobal = 0;
        int endGlobal = 0;

        int i = 1;
        int size = arr.length;

        while (i < size) {

            int num = arr[i];

            if (maxLocal + num > num) {
                maxLocal += num;
            } else {
                maxLocal = num;
                start = i;
            }

            if (maxLocal > maxGlobal) {
                maxGlobal = maxLocal;
                startGlobal = start;
                endGlobal = i;
            }
            i++;
        }

        System.out.printf("start=%s end=%s len=%s sum=%s\n", startGlobal, endGlobal, endGlobal - startGlobal + 1, maxGlobal);
    }
}
