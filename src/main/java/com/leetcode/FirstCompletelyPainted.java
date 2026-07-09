package com.leetcode;

/**
 * FirstCompletelyPainted
 */
public class FirstCompletelyPainted {

    public static void main(String[] args) {
        final FirstCompletelyPainted painted = new FirstCompletelyPainted();
        int[] arr = {};
        int[][] mat = {{}};
        System.out.println(painted.firstCompleteIndex(arr, mat));
    }

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rSize = mat.length;
        int cSize = mat[0].length;

        int[] rowSum = new int[rSize];
        int[] colSum = new int[cSize];

        int[] idToCellMap = new int[rSize * cSize];

        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                int id = mat[i][j];
                idToCellMap[id - 1] = i * cSize + j;

                rowSum[i] += id;
                colSum[j] += id;
            }
        }
        
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            int cell = idToCellMap[a - 1];
            int r = cell / cSize;
            int c = cell % cSize;

            rowSum[r] -= a;
            colSum[c] -= a;

            if (rowSum[r] == 0 || colSum[c] == 0) {
                return i;
            }
        }

        return -1;
    }
}
