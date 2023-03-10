package com.leetcode;

public class Search2DMatrix {
    private int mid(int a, int b) {
        return a + (b - a) / 2;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int lx = 0, ly = 0;
        int hx = matrix.length - 1, hy = matrix[0].length - 1;


        while (lx <= hx) {
            // locate row
            int mx = mid(lx, hx);

            //Arrays.fill();

            if (matrix[mx][ly] <= target && target <= matrix[mx][hy]) {
                // row found
                lx = mx; 
                break;
            } else if (target < matrix[mx][ly]) {
                hx = mx - 1;
            } else if (target > matrix[mx][hy]) {
                lx = mx + 1;
            } else {
                return false;
            }
        }

        // now find column
        while (ly <= hy && lx >=0 && lx <= matrix.length - 1) {
            int my = mid(ly, hy);

            if (target == matrix[lx][my]) {
                return true;
            } else if (target < matrix[lx][my]) {
                hy = my - 1;
            } else {
                ly = my + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1}, {3}, {5}};
        int target = 3;
        System.out.println(new Search2DMatrix().searchMatrix(matrix, target));
    }
}
