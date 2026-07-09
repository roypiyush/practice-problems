package com.personal.sort;

import com.lib.Utils;

import java.util.Arrays;
import java.util.Random;

class Heap {
    int[] heap;
    int heapSize;
    int capacity;

    public Heap(int capacity) {
        this(capacity, -1);
    }

    public Heap(int capacity, int initializer) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        Arrays.fill(heap, initializer);
    }

    /**
     * Insert operation on heap.
     * 1. Add new element at the end
     * 2. Perform heap<Increase for maxHeap or Decrease for minHeap>Key
     * 3. Increment the size
     * 
     * @param key
     */
    public void maxHeapInsert(int key) {
        heap[heapSize] = Integer.MIN_VALUE; 
        heapIncreaseKey(key);
        this.heapSize += 1;
    }

    /**
     * Move current element toward root if heap property violated
     * 
     * @param key
     */
    private void heapIncreaseKey(int key) {
        if (heap[heapSize] >= key) {
            throw new RuntimeException("Heap property violated");
        }

        heap[heapSize] = key;
        int i = heapSize;

        int p = parent(i);
        while (p >= 0) {
            if (heap[i] > heap[p]) {
                Utils.swap(heap, i, p);
            }
            i = p;
            p = parent(i);
        }
    }

    public int[] sort() { 
        // buildMapHeap(heap); // not required, as heap is already built
        
        int[] sortedArray = Arrays.copyOf(heap, this.heapSize);
        int tmpHeapSize = this.heapSize;
        // move max value to last position
        for (int i = tmpHeapSize - 1; i > 0; i--) {
            Utils.swap(sortedArray, 0, i);
            tmpHeapSize--;
            maxHeapify(sortedArray, tmpHeapSize, 0);
        }

        return sortedArray;
    }

    private void maxHeapify(final int[] heap, final int heapSize, final int pos) {
        int left = left(pos);
        int right = right(pos);
        int largest = pos;
        if (left < heapSize && heap[left] > heap[pos]) {
            largest = left;
        }

        if (right < heapSize && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != pos) {
            Utils.swap(heap, pos, largest);
            // after swap largestPos has become smaller so maxHeapify
            maxHeapify(heap, heapSize, largest);
        }
    }

    private int left(final int i) {
        return 2 * i + 1;
    }

    private int right(final int i) {
        return 2 * i + 2;
    }

    int parent(final int i) {
        // 2p + 1 = p, 2p + 2 = p + 1
        return i % 2 == 0 ? i / 2 - 1 : i / 2; 
    }

    /**
     * called when array needs to be transformed into heap
     * @param array
     */
    @SuppressWarnings("unused")
    private void buildMapHeap(final int[] array) {
        for (int i = this.heapSize / 2; i >= 0; i--) {
            maxHeapify(this.heap, this.heapSize, i);
        }
    }

    public String toString() {
        return Arrays.toString(heap);
    }
}

public class HeapSort {

    public static void main(String[] args) {

        Random random = new Random();

        Heap heap = new Heap(20);
        for (int i = 0; i < 14; i++) {
            heap.maxHeapInsert(random.nextInt(100));
        }
        System.out.println(heap);
        int[] sortedArray = heap.sort();
        Utils.assertSorted(sortedArray, sortedArray.length);
    }

}
