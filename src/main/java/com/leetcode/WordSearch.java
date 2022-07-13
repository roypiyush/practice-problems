package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {

    public static void main(String[] args) {
        // char[][] board = {
        //         { 'A', 'B', 'C', 'E' }, 
        //         { 'S', 'F', 'C', 'S' },
        //         { 'A', 'D', 'E', 'E' }
        // };
        // String word = "ABCB";

        char[][] board = {
            {'a', 'b'},
            {'c', 'd'}
        };
        String word = "acdb";

        System.out.println(new WordSearch().exist(board, word));;
    }

    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                boolean res = compute(board, word, i, j, board.length, board[0].length, new HashSet<Integer>(), 0);
                if (res)
                    return res;

            }
        }
        return false;
    }

    int getCellNum(int i, int j, int m, int n) {
        return n * i + j;
    }

    boolean compute(char[][] board, String word, int i, int j, int m, int n, Set<Integer> path, int cur) {

        if (cur == word.length()) {
            return true;
        }

        if (cur > word.length()) {
            return false;
        }

        if (i >= m || j >= n || i < 0 || j < 0) {
            return false;
        }

        if (path.contains(getCellNum(i, j, m, n))) {
            return false;
        }
        
        if (board[i][j] != word.charAt(cur)) {
            return false;
        }

        path.add(getCellNum(i, j, m, n));

        boolean r1 = compute(board, word, i, j + 1, m, n, path, cur + 1);
        if (r1)
            return r1;

        boolean r2 = compute(board, word, i, j - 1, m, n, path, cur + 1);
        if (r2)
            return r2;

        boolean r3 = compute(board, word, i + 1, j, m, n, path, cur + 1);
        if (r3)
            return r3;

        boolean r4 = compute(board, word, i - 1, j, m, n, path, cur + 1);
        if (r4)
            return r4;

        path.remove(getCellNum(i, j, m, n));
        return false;
    }
}
