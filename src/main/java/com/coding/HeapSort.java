package com.coding;

import com.lib.Utils;
import jdk.jshell.execution.Util;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int size = 10;
        int[] array = new int[size];
        Utils.populateWithRandomValues(array);

        new HeapSort().sort(array);
        System.out.println(Arrays.toString(array));
    }

    private void sort(final int[] array) {
        buildMapHeap(array);
        int heapSize = array.length - 1;
        for (int i = array.length - 1; i > 0; i--) {
            Utils.swap(array, 0, i);
            maxHeapify(array, heapSize--, 0);
        }
    }

    private void maxHeapify(final int[] array, final int heapSize, final int pos) {
        int left = left(pos);
        int right = right(pos);
        int largest = pos;
        if (left < heapSize && array[left] > array[pos]) {
            largest = left;
        }

        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != pos) {
            Utils.swap(array, pos, largest);
            // after swap largestPos has become smaller so maxHeapify
            maxHeapify(array, heapSize, largest);
        }
    }

    private int left(final int i) {
        return 2 * i + 1;
    }

    private int right(final int i) {
        return 2 * i + 2;
    }

    private void buildMapHeap(final int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, array.length - 1, i);
        }
    }


}
