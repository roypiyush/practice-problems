package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestPath {

    public static void main(String[] args) {

        ShortestPath path = new ShortestPath();

        int[][] test1 = new int[][] { { 0, 1 }, { 1, 0 } };
        int[][] test2 = new int[][] { { 0, 1, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };

        int[][] test3 = new int[][] { { 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 0, 0, 1 },
                { 1, 1, 1, 1, 1 } };
        int[][] test4 = new int[][] {
                { 1, 1, 1, 1, 0, 0 },
                { 1, 1, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1, 1 },
                { 0, 0, 0, 0, 1, 1 }
        };

        System.out.println(path.shortestBridge(test1));
        System.out.println(path.shortestBridge(test2));
        System.out.println(path.shortestBridge(test3));
        System.out.println(path.shortestBridge(test4));

    }

    public int shortestBridge(int[][] grid) {

        PriorityQueue<int[]> queue = nodesToStart(grid);
        return performBFS(grid, queue);

    }

    private PriorityQueue<int[]> nodesToStart(int[][] grid) {

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    performDFS(grid, i, j, queue);
                    return queue;
                }

            }
        }
        return queue;
    }

    private int performBFS(int[][] grid, Queue<int[]> queue) {

        int result = Integer.MAX_VALUE;

        int rowSize = grid.length;
        int colSize = grid[0].length;

        while (!queue.isEmpty()) {

            int[] node = queue.poll();
            int row = node[0];
            int col = node[1];
            int distance = node[2];

            if (row - 1 >= 0 && grid[row - 1][col] == 0) {
                grid[row - 1][col] = -1;
                queue.add(new int[] { row - 1, col, distance + 1 });
            }
            if (row + 1 < rowSize && grid[row + 1][col] == 0) {
                grid[row + 1][col] = -1;
                queue.add(new int[] { row + 1, col, distance + 1 });
            }
            if (col - 1 >= 0 && grid[row][col - 1] == 0) {
                grid[row][col - 1] = -1;
                queue.add(new int[] { row, col - 1, distance + 1 });
            }
            if (col + 1 < colSize && grid[row][col + 1] == 0) {
                grid[row][col + 1] = -1;
                queue.add(new int[] { row, col + 1, distance + 1 });
            }

            // edge
            if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                result = Math.min(result, distance);
            }
            if (row + 1 < rowSize && grid[row + 1][col] == 1) {
                result = Math.min(result, distance);
            }
            if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                result = Math.min(result, distance);
            }
            if (col + 1 < colSize && grid[row][col + 1] == 1) {
                result = Math.min(result, distance);
            }
        }

        return result;

    }

    void performDFS(int[][] grid, int i, int j, Queue<int[]> queue) {

        if (grid[i][j] != 1) {
            return;
        }

        grid[i][j] = -1;
        queue.offer(new int[] { i, j, 0 });

        List<int[]> neighbours = findNeighbours(grid, i, j, 1);
        for (int[] neighbour : neighbours) {
            performDFS(grid, neighbour[0], neighbour[1], queue);
        }

    }

    List<int[]> findNeighbours(int[][] grid, int row, int col, int landMass) {

        int rowSize = grid.length;
        int colSize = grid[0].length;

        List<int[]> list = new LinkedList<>();

        if (row - 1 >= 0 && grid[row - 1][col] == landMass) {
            list.add(new int[] { row - 1, col });
        }

        if (row + 1 < rowSize && grid[row + 1][col] == landMass) {
            list.add(new int[] { row + 1, col });
        }

        if (col - 1 >= 0 && grid[row][col - 1] == landMass) {
            list.add(new int[] { row, col - 1 });
        }

        if (col + 1 < colSize && grid[row][col + 1] == landMass) {
            list.add(new int[] { row, col + 1 });
        }

        return list;
    }

}
