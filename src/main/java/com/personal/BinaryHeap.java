package com.personal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryHeap {

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap(20);
        for (int i = 1; i < 16; i++) {
            heap.add(i);
        }
        System.out.println(heap);

        System.out.println("Level order traversal");
        heap.levelOrder();
        System.out.println("\nEnd of Level order traversal\n");

        heap.changeItem(heap.size() - 1, 20);
        while (heap.size() > 0) {
            System.out.printf("%d ", heap.extractPeek());
        }
    }

    void levelOrder() {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});

        int level = 0;
        while (!queue.isEmpty()) {

            int[] nodeWithLevel = queue.poll();
            if (nodeWithLevel[1] != level) {
                System.out.println();
                level = nodeWithLevel[1];
            }

            System.out.print(heap[nodeWithLevel[0]] + " ");

            if (left(nodeWithLevel[0]) < size) {
                queue.offer(new int[] {left(nodeWithLevel[0]), nodeWithLevel[1] + 1});
            }

            if (right(nodeWithLevel[0]) < size) {
                queue.offer(new int[] {right(nodeWithLevel[0]), nodeWithLevel[1] + 1});
            }
        }
    }

    public BinaryHeap(int capacity) {
        this.heap = new int[capacity];
    } 

    int size() {
        return size;
    }

    public String toString() {
        return Arrays.toString(heap);
    }

    int peek() {
        if (size == 0) {
            throw new RuntimeException("No such element exception");
        }
        return heap[0];
    }

    int extractPeek() {

        int peek = peek();

        size--;
        heap[0] = heap[size];
        heap[size] = 0; // denotes no element from this point

        maxHeapify(0); // restore heap property
        return peek;
    } 

    void maxHeapify(int i) {

       while (true) { 
            int largest = i;
            int l = left(i);
            int r = right(i);

            if (l < size && heap[l] > heap[largest]) {
                largest = l;
            }

            if (r < size && heap[r] > heap[largest]) {
                largest = r;
            }

            if (i == largest) {
                break;
            }

            // ith element changed and heap violation observed WRT children
            swap(i, largest);
            i = largest;
        }

    }

    void add(int item) {
        if (size == heap.length) {
            throw new RuntimeException("Heap Full");
        }

        heap[size] = item;
        size++;

        moveUp(size - 1);
    }

    void changeItem(int i, int item) {
        System.out.printf("Changing %d to %d\n", heap[i], item);
        heap[i] = item;
        maxHeapify(i);
        moveUp(i);
    }

    /**
     * Converts an array to heap
     */
    void buildHeap() {
        for (int i = size / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    void moveUp(int i) {

        while (parent(i) >= 0 && heap[parent(i)] < heap[i]) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    void swap(int i, int j) {
        int t = heap[i];
        heap[i] = heap[j];
        heap[j] = t;
    }

    int left(int i) {
        return 2 * i + 1;
    }

    int right(int i) {
        return 2 * i + 2;
    }

    int parent(int i) {
        if (i - 1 < 0) {
            return -1;
        }

        return (i - 1) / 2;
    }

    int[] heap;
    int size = 0;
}
