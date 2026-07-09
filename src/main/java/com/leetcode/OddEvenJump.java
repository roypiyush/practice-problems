package com.leetcode;

import java.util.LinkedList;

public class OddEvenJump {

    public static void main(String[] args) {
        OddEvenJump main = new OddEvenJump();
        int[] arr = { 2, 3, 1, 1, 4 };
        System.out.println(main.oddEvenJumps(arr));
    }

    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return 1; // If there's only one element, it is trivially reachable.
        }

        int[][] minMaxTracker = computeMinMaxTracker(arr);
        int[][] oddEvenJumps = computeOddEvenJumps(arr, minMaxTracker);

        boolean[][] dp = new boolean[2][n];
        dp[0][n - 1] = true; // Odd jump from last element is trivially reachable.
        dp[1][n - 1] = true; // Even jump from last element is trivially reachable.

        int count = 0;

        // Traverse the array in reverse order
        for (int i = n - 2; i >= 0; i--) {
            // After updating dp[i], check if it's reachable
            if (dp[0][i] || dp[1][i]) {
                count++;  // If either odd or even jump from current position can reach the end
            }

            // If there's a valid odd jump to the next position
            if (oddEvenJumps[0][i] != -1) {
                dp[0][i] = dp[1][oddEvenJumps[0][i]]; // Odd jump leads to the next reachable even position
            }

            // If there's a valid even jump to the next position
            if (oddEvenJumps[1][i] != -1) {
                dp[1][i] = dp[0][oddEvenJumps[1][i]]; // Even jump leads to the next reachable odd position
            }
        }

        // The first element is reachable if its dp[0][0] is true (odd jump)
        return count + (dp[0][0] ? 1 : 0); // If the first element can reach the end, count it.
    }

    // Compute next greater (odd jump) and next smaller (even jump) elements
    int[][] computeOddEvenJumps(int[] arr, int[][] minMaxTracker) {
        int n = arr.length;
        int[][] oddEvenJumps = new int[2][n];

        // Initialize the jumps for the last element as -1 (no valid jump)
        oddEvenJumps[0][n - 1] = -1;  // Odd jump from the last element
        oddEvenJumps[1][n - 1] = -1;  // Even jump from the last element

        for (int i = n - 2; i >= 0; i--) {
            // Odd jump: Next greater element
            if (minMaxTracker[0][i] != -1 && arr[i] <= arr[minMaxTracker[0][i]]) {
                oddEvenJumps[0][i] = minMaxTracker[0][i]; // Odd jump can land on the next valid greater element
            }

            // Even jump: Next smaller element
            if (minMaxTracker[1][i] != -1 && arr[i] >= arr[minMaxTracker[1][i]]) {
                oddEvenJumps[1][i] = minMaxTracker[1][i]; // Even jump can land on the next valid smaller element
            }
        }

        return oddEvenJumps;
    }

    // Compute next greater and next smaller elements for each index in the array
    int[][] computeMinMaxTracker(int[] arr) {
        int n = arr.length;
        LinkedList<Integer> monoDec = new LinkedList<>(); // Stack for next greater element (odd jump)
        LinkedList<Integer> monoInc = new LinkedList<>(); // Stack for next smaller element (even jump)

        int[][] minMaxTracker = new int[2][n];
        // Initialize with -1, meaning no valid jump from that position
        minMaxTracker[0][n - 1] = -1; // Next greater element for odd jump
        minMaxTracker[1][n - 1] = -1; // Next smaller element for even jump

        for (int i = n - 1; i >= 0; i--) {
            // For next smaller element (even jump)
            while (!monoInc.isEmpty() && arr[i] < arr[monoInc.peek()]) {
                monoInc.pop();
            }
            if (!monoInc.isEmpty()) {
                minMaxTracker[1][i] = monoInc.peek();
            }
            monoInc.push(i);

            // For next greater element (odd jump)
            while (!monoDec.isEmpty() && arr[i] >= arr[monoDec.peek()]) {
                monoDec.pop();
            }
            if (!monoDec.isEmpty()) {
                minMaxTracker[0][i] = monoDec.peek();
            }
            monoDec.push(i);
        }

        return minMaxTracker;
    }
}
