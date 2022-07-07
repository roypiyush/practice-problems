package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    
    class Point {
        int i;
        int j;
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));

    }

    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                 islands = islands + explore(grid, i, j);
            }
        }
        
        return islands;        
    }
    
    int explore(char[][] grid, int i, int j) {
        
        int r = grid[i][j] == '1' ? 1 : 0;
        if (r == 0) {
            return 0;
        }
        
        grid[i][j] = '0';
        Queue<Point> queue = new LinkedList<>();
        Point start = new Point(i, j);
        queue.add(start);
        
        while (!queue.isEmpty()) {
            
            Point p = queue.poll();
            
            Point e = p.j + 1 >= grid[0].length || grid[p.i][p.j + 1] == '0' ? null : new Point(p.i, p.j + 1);
            if (e != null) {
                grid[p.i][p.j + 1] = '0';
                queue.offer(e);
            }
            
            Point w = p.j - 1 < 0 || grid[p.i][p.j - 1] == '0' ? null : new Point(p.i, p.j - 1);
            if (w != null) {
                grid[p.i][p.j - 1] = '0';
                queue.offer(w);
            }
            
            Point n = p.i - 1 < 0 || grid[p.i - 1][p.j] == '0' ? null : new Point(p.i - 1, p.j);
            if (n != null) {
                grid[p.i - 1][p.j] = '0';
                queue.offer(n);
            }
            
            Point s = p.i + 1 >= grid.length || grid[p.i + 1][p.j] == '0' ? null : new Point(p.i + 1, p.j);
            if (s != null) {
                grid[p.i + 1][p.j] = '0';
                queue.offer(s);
            }
            
        }
        return r;
    }

}
