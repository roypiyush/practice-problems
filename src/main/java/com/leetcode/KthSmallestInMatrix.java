package com.leetcode;

import java.util.PriorityQueue;

public class KthSmallestInMatrix {

    public static void main(String[] args) {
        // int[][] matrix = {
        //         { 1, 5, 9 },
        //         { 10, 11, 13 },
        //         { 12, 13, 15 }
        // };
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};

        // int[][] matrix = {
        // {1, 2},
        // {1 ,3}
        // };

        System.out.println(new KthSmallestInMatrix().kthSmallest(matrix, 5));
        // System.out.println(new KthSmallestInMatrix().kthSmallest(matrix, 6));
    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int size = matrix.length;
        int count = 0;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pq.offer(matrix[i][j]);
            }
        }

        int result = matrix[0][0];
        while (pq.peek() != null) {
            result = pq.poll();
            count++;
            if (count == k) {
                break;
            }
        }
        return result;
    }

    public int solution(int[][] matrix, int k) {
        int n = matrix.length;
        int lo = matrix[0][0], hi = matrix[n - 1][n - 1];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0, j = n - 1;

            for (int i = 0; i < matrix.length; i++) {
                while (j >= 0 && matrix[i][j] > mid)
                    j--;

                count += j + 1;
            }

            if (count < k)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }

    public int[] point(int k, int size) {
        int[] pair = new int[2];
        pair[0] = (k - 1) / size;
        pair[1] = (k - 1) % size;
        return pair;
    }

}