package com.personal;

import java.util.Arrays;

public class DAryHeap {

    private int[] A;
    private int d;
    private int size;

    public DAryHeap(int[] A, int d) {
        this.A = A;
        this.d = d;
        this.size = A.length;
        buildMaxHeap();
    }

    private void swap(int i, int j) {
        A[i] = A[i] + A[j];
        A[j] = A[i] - A[j];
        A[i] = A[i] - A[j];
    }

    private int child(int i, int n) {
        if (n > d) {
            throw new IllegalArgumentException(String.format("%d > %d is incorrect", n, d));
        }
        return d * i + n;
    }

    private int parent(int i, int d) {
        return (i - i % d) / d;
    }

    public void maxHeapify(int i) {
        int largest = i;
        for (int x = 1; x <= d; x++) {
            final int child = child(i, x);
            if (child < size) {
                if (A[child] > A[largest]) {
                    largest = child;
                }
            }
        }
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    private void buildMaxHeap() {
        int s = size / d;
        for (int i = s; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public int heapExtractMax() {
        int max = A[0];
        A[0] = A[size - 1];
        A[size - 1] = -1;
        size = size - 1;
        maxHeapify(0);
        return max;
    }

    public void heapIncreaseKey(int i, int key) {
        if (A[i] > key) {
            throw new IllegalArgumentException(String.format("Key %d is greater than A[%d]", key, i));
        }

        if (A[i] == key) {
            return;
        }

        A[i] = key;
        while (i > 0) {
            final int parent = parent(i, d);
            if (!(A[parent] < A[i])) break;
            swap(parent, i);
            i = parent;
        }
    }

    public void maxHeapInsert(int key) {
        size = size + 1;
        heapIncreaseKey(size - 1, key);
    }

    @Override
    public String toString() {
        return "DAryHeap{" +
                "A=" + Arrays.toString(A) +
                ", d=" + d +
                '}';
    }

    public static void heapSort(int[] A, int d) {
        final DAryHeap dAryHeap = new DAryHeap(A, d);
        final int heapifyIndex = 0;
        for (int i = dAryHeap.size - 1; i > 0; i--) {
            dAryHeap.swap(heapifyIndex, dAryHeap.size - 1);
            dAryHeap.size = dAryHeap.size - 1;
            dAryHeap.maxHeapify(heapifyIndex);
        }
        dAryHeap.size = dAryHeap.A.length;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int d = 3;
        final DAryHeap dAryHeap = new DAryHeap(A, d);
        System.out.println(dAryHeap);
        heapSort(dAryHeap.A, d);
        System.out.println(dAryHeap);
        dAryHeap.buildMaxHeap();
        System.out.println(dAryHeap);
        System.out.println("Extract max " + dAryHeap.heapExtractMax());
        System.out.println(dAryHeap);
        dAryHeap.maxHeapInsert(15);
        System.out.println(dAryHeap);
        heapSort(dAryHeap.A, d);
        System.out.println(dAryHeap);
        dAryHeap.buildMaxHeap();
        System.out.println(dAryHeap);

        int[] B = new int[A.length];
        Arrays.fill(B, -1);
        final DAryHeap bHeap = new DAryHeap(B, d);
        bHeap.size = 0;

        for (int i = 1; i <= 15; i++) {
            bHeap.maxHeapInsert(i);
        }

        System.out.println(bHeap);


    }
}
