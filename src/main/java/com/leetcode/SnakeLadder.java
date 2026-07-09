package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeLadder {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        int[][] board = {
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 35, -1, -1, 13, -1 },
                { -1, -1, -1, -1, -1, -1 },
                { -1, 15, -1, -1, -1, -1 }

        };
        System.out.println(new SnakeLadder().snakesAndLadders(board));
    }

    private static void test2() {
        int[][] board = {
                { -1, -1 },
                { -1, 3 }
        };
        System.out.println(new SnakeLadder().snakesAndLadders(board));
    }

    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 1, 0 });
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            int cellNum = cell[0];
            int rollCount = cell[1];

            // visit neighbours
            for (int i = 1; i <= 6; i++) {
                int nextCell = cellNum + i;

                if (nextCell > n * n) {
                    continue;
                }

                int[] xy = intToPoint(n, nextCell);

                int curRollCount = rollCount + 1;

                if (board[xy[0]][xy[1]] != -1) {
                    // trace to next cell
                    nextCell = board[xy[0]][xy[1]];
                }

                if (nextCell == n * n) {
                    return curRollCount;
                }

                if (!visited[nextCell]) {
                    visited[nextCell] = true;
                    queue.offer(new int[] { nextCell, curRollCount });
                }

            }
        }
        return -1;
    }

    int[] intToPoint(int n, int curCell) {
        curCell--;

        int row = n - 1 - (curCell / n);
        int col = (n - 1 - row) % 2 == 0 ? (curCell % n) : (n - 1 - (curCell % n));

        return new int[] { row, col };
    }

}
