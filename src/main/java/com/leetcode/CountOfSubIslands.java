package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class CountOfSubIslands {

    public static void main(String[] args) {

        int[][] grid1 = {
                {1,1,1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,1},
                {1,1,1,0,0,1,0,1,1,1,0,1,1,1,1,1,1,1},
                {1,0,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1},
                {1,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,1},
                {1,1,0,0,1,0,1,1,1,0,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1},
                {0,0,0,0,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
                {1,1,1,0,0,1,0,0,1,0,1,1,1,1,0,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
                {1,1,1,1,1,1,0,0,1,0,1,0,1,0,1,1,1,1},
                {1,1,1,1,1,0,1,1,0,1,1,1,1,0,1,1,1,1},
                {1,0,0,1,1,1,1,1,0,1,0,1,0,1,1,1,1,1},
                {1,1,1,1,0,0,1,0,1,1,0,1,1,1,1,1,1,1},
                {1,1,1,0,1,0,1,0,1,1,1,1,0,1,0,1,0,1}
        };
        int[][] grid2 = {
                {1,1,0,0,1,1,1,0,1,0,0,1,1,1,1,1,1,1},
                {1,0,0,1,1,1,0,1,1,1,0,0,1,1,0,1,1,0},
                {1,1,0,1,1,1,1,1,0,1,0,1,0,0,1,1,1,1},
                {1,1,0,1,0,0,1,1,1,1,0,1,1,1,1,1,0,1},
                {1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,0,1},
                {1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,0,1},
                {1,1,0,0,0,1,0,0,1,1,0,1,1,1,1,1,1,1},
                {1,1,1,0,0,1,1,1,1,1,0,0,1,0,1,1,1,0},
                {0,0,0,1,1,1,1,1,0,0,1,1,0,1,1,1,1,1},
                {0,1,0,1,0,1,0,0,1,1,1,0,1,1,1,1,1,1},
                {1,0,0,0,1,0,1,1,0,0,0,1,0,0,1,1,0,1},
                {1,0,1,1,1,0,1,0,1,1,0,0,1,1,0,0,1,1},
                {1,1,0,1,1,1,1,1,0,0,1,1,1,0,1,1,1,1},
                {1,1,1,0,1,1,1,1,1,1,0,0,1,1,1,0,1,1}
        };

        System.out.println(new CountOfSubIslands().countSubIslands(grid1, grid2));
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {

        /*
        Observations
            for each cell in region - grid2, corresponding cell must exist
            previous neighbourId shall be assigned to this current cell
            carry forward unmatched cells info to other cells within the group

        Approach
            group leader points to itself
            unmatched cell should propagate to group leader
                store group leader to matching information
                store group leader to child information -> disjoint set

            makeSet(ki) -> i [0, # of cells)
            find(k) -> returns group leader
            union(ki, kj) -> joins to two group leaders making 1 parent and other child of parent
                call union only for adjacent cells as per 4-directional criteria

        */

        int m = grid1.length;
        int n = grid1[0].length;

        int[] disjointSet = makeSet(m * n);

        int[][] directions = {
                {-1, 0},
                {0, -1},
                // {1, 0},
                // {0, 1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid2[i][j] == 0) {
                    continue;
                }

                // current cell doesn't point to anyone but itself
                int cell = getCellNum(n, i, j);

                for (int[] d : directions) {
                    int x = i + d[0];
                    int y = j + d[1];

                    if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] > 0) {
                        int prev = getCellNum(n, x, y);
                        union(disjointSet, cell, prev);
                    }
                }

                // cell now either points to parent or self, meaning cell belongs to a region
            }
        }

        Set<Integer> invalid = new HashSet<>();
        Set<Integer> total = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid2[i][j] == 0) {
                    continue;
                }

                int cell = getCellNum(n, i, j);
                int root = find(disjointSet, cell);

                if (grid1[i][j] == 0) {
                    // invalid
                    invalid.add(root);
                }

                total.add(root);
            }
        }

        return total.size() - invalid.size();
    }

    int[] makeSet(int cellCount) {
        int[] disjointSet = new int[cellCount + 1];
        for (int i = 0; i <= cellCount; i++) {
            disjointSet[i] = i;
        }
        return disjointSet;
    }

    int find(int[] disjointSet, int cellNum) {
        while (disjointSet[cellNum] != cellNum) {
            cellNum = Math.abs(disjointSet[cellNum]);
        }
        return cellNum;
    }

    void union(int[] disjointSet, int cell, int prev) {
        int i = find(disjointSet, cell);
        int j = find(disjointSet, prev);

        int min = Math.min(i, j);
        int max = Math.max(i, j);
        disjointSet[max] = min;
    }

    int getCellNum(int n, int i, int j) {
        return i * n + j + 1;
    }
}
