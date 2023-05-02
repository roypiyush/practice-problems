package com.leetcode;

import java.util.List;
import java.util.LinkedList;

public class SpiralOrder {
    
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> list = new LinkedList<>();

        int rs = 0, re = matrix.length - 1;
        int cs = 0, ce = matrix[0].length - 1;


        while (rs <= re && cs <= ce) {
            for (int i = cs; i <= ce && rs <= re; i++) {
                list.add(matrix[rs][i]);
            }
            rs++;

            for (int i = rs; i <= re && cs <= ce; i++) {
                list.add(matrix[i][ce]);
            }
            ce--;

            for (int i = ce; i >= cs && rs <= re; i--) {
                list.add(matrix[re][i]);
            }
            re--; 

            for (int i = re; i >= rs && cs <= ce; i--) {
                list.add(matrix[i][cs]);
            }
            cs++;

        }
        return list;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}

        };
        System.out.println(new SpiralOrder().spiralOrder(matrix));
    }

}
