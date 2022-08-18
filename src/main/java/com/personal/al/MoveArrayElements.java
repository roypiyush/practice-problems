package com.personal.al;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MoveArrayElements {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        for (int i = 1; i <= arr.length; i++) {
            System.out.printf("\nMove by %d\n", i);
            System.out.println(Arrays.toString(arr));
            rotateBy(arr, i);
            System.out.println(Arrays.toString(arr));
            // rotate back to original
            rotateBy(arr, arr.length - i);
        }
    }

    private static void rotateBy(final int[] arr, final int x) {
        if (x % arr.length == 0) {
            return;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int a = 0; a < arr.length; a++) {
            pq.add(a);
        }

        int d = x % arr.length;
        int i = 0;
        while (i < arr.length) {
            int t = arr[i];
            pq.remove(i);

            int j = (i + d) % arr.length;
            while (j != i) {
                int v = arr[j];
                pq.remove(j);

                arr[j] = t;
                t = v;
                j = (j + d) % arr.length;
            }
            arr[j] = t;

            if (pq.peek() == null) {
                break;
            }
            i = pq.poll();
        }
    }
}
