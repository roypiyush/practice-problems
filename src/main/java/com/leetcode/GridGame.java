package com.leetcode;


import java.util.Arrays;

public class GridGame {
    public static void main(String[] args) {
        GridGame game = new GridGame();
        int[][] grid = {{20,3,20,17,2,12,15,17,4,15}, 
                        {20,10,13,14,15,5,2,3,14,3}
                        };
        System.out.println(game.gridGame(grid));
    }

    public long gridGame(int[][] grid) {

        int sum0 = Arrays.stream(grid[0]).sum();

        int min = Integer.MAX_VALUE;
        int sum1 = 0;
        for (int i = 0; i < grid[0].length; i++) {

            // robot1 takes turn at i, sum1 is now remaining values in 1st row
            sum0 -= grid[0][i];
            // at this point, 2nd robot can collect all data upto i - 1
            min = Math.min(min, Math.max(sum0, sum1));
            // keep track lower row as grid is divided into two parts
            sum1 += grid[1][i];
        }
        return min;
    }
}