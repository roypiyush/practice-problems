package com.personal.al;

public class SquareRootDecomposition {
    private int[] decomposedArray;
    private int chunkSize;

    void createDecomposedArray(int[] array) {
        chunkSize = (int) Math.ceil(Math.sqrt(array.length)); // sqrt(n)
        decomposedArray = new int[chunkSize];
        int index = 0; // index for main array
        int indexDecomposedArray = 1;  // index for decomposed array
        while (index < array.length) {
            int min = Integer.MAX_VALUE;
            // Iterate chunk and store value
            while (index < array.length && index < chunkSize * indexDecomposedArray) {
                min = Math.min(min, array[index]);
                index++;
            }
            decomposedArray[indexDecomposedArray - 1] = min;
            indexDecomposedArray++;
        }
    }

    int minInRange(int[] arr, int i, int j) {
        int lowChunk = i - i % chunkSize + chunkSize;
        int highChunk = j - (j % chunkSize) - 1;
        if (highChunk < lowChunk) {
            return findMinInRange(arr, i, j);
        } else {
            int min = findMinInRange(arr, i, lowChunk);
            while (chunkSize * lowChunk < highChunk) {
                min = Math.min(min, decomposedArray[lowChunk / chunkSize]);
                lowChunk++;
            }
            min = Math.min(min, findMinInRange(arr, chunkSize * lowChunk, j));
            return min;
        }
    }

    /**
     * Linear iteration over i till j
     * @param arr
     * @param i
     * @param j
     * @return
     */
    private int findMinInRange(final int[] arr, final int i, final int j) {
        int min = Integer.MAX_VALUE;
        int c = i;
        while (c <= j) {
            min = Math.min(min, arr[c++]);
        }
        return min;
    }

    int _min(int[] arr, int i, int j) {
        int min = Integer.MAX_VALUE;
        while (i <= j) {
            min = Math.min(min, arr[i]);
            i++;
        }
        return min;
    }

    public static void main(String[] args) {
        int array[] = {1, 5, 2, 4, 6, 1, 3, 5, 7};
        final SquareRootDecomposition srd = new SquareRootDecomposition();
        srd.createDecomposedArray(array);
        System.out.println(srd.minInRange(array, 8, 8));
    }
}
