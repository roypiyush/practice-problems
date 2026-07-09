package com.leetcode;

public class MaxNumPointsCost {

    public long maxPoints(int[][] points) {
        int rows = points.length, cols = points[0].length;
        long[] previousRow = new long[cols];

        // Initialize the first row
        for (int col = 0; col < cols; ++col) {
            previousRow[col] = points[0][col];
        }

        // Process each row
        for (int row = 0; row < rows - 1; ++row) {
            long[] leftMax = new long[cols];
            long[] rightMax = new long[cols];
            long[] currentRow = new long[cols];

            // Calculate left-to-right maximum
            leftMax[0] = previousRow[0];
            for (int col = 1; col < cols; ++col) {
                leftMax[col] = Math.max(leftMax[col - 1] - 1, previousRow[col]);
            }

            // Calculate right-to-left maximum
            rightMax[cols - 1] = previousRow[cols - 1];
            for (int col = cols - 2; col >= 0; --col) {
                rightMax[col] = Math.max(rightMax[col + 1] - 1, previousRow[col]);
            }

            // Calculate the current row's maximum points
            for (int col = 0; col < cols; ++col) {
                currentRow[col] = points[row + 1][col] + Math.max(leftMax[col], rightMax[col]);
            }

            // Update previousRow for the next iteration
            previousRow = currentRow;
        }

        // Find the maximum value in the last processed row
        long maxPoints = 0;
        for (int col = 0; col < cols; ++col) {
            maxPoints = Math.max(maxPoints, previousRow[col]);
        }

        return maxPoints;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{
                {1, 2, 3},
                {1, 5, 1},
                {3, 1, 1}
        };
        System.out.println(new MaxNumPointsCost().maxPoints(points));
    }

}

/*

    1   2   3
    
    4   5   6 => 7

    7   8   9 => 3




    
    7   8   9

    4 + 7 - 0 = 11
    4 + 8 - 1 = 11
    4 + 9 - 2 = 11


    5 + 7 - 1 = 11
    5 + 8 - 0 = 13
    5 + 9 - 1 = 13


    6 + 7 - 2 = 11
    6 + 8 - 1 = 13
    6 + 9 - 0 = 15





*/
