package com.coding;

import java.util.Iterator;
import java.util.List;

class Pair<Key, Value> {
    private Key key;
    private Value value;

    private Pair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public static <Key, Value> Pair<Key, Value> of(Key key, Value value) {
        return new Pair<>(key, value);
    }
}

public class MatrixChainMultiply {
    static int[] createDimension(List<int[][]> matrices) {
        int[] p = new int[matrices.size() + 1];
        Iterator<int[][]> it = matrices.iterator();
        int[][] mx = it.next();
        p[0] = mx.length;
        int count = 1;
        do {
            p[count] = mx[count - 1].length;
            count++;
        } while (it.hasNext());
        return p;
    }

    static Pair<int[][], int[][]> matrixChainMultiply(int[] p) {
        // returns m, s

        int n = p.length - 1;
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        for (int i = 0; i < n; i++) {
            m[i][i] = 0;
        }


        // l, running length of list of matrices a0, a1, a2, a3
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int min = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j];
                    if (m[i][j] > min) {
                        m[i][j] = min;
                        s[i][j] = k;
                    }
                }
            }
        }

        return Pair.of(m, s);
    }


    public static void main(String... args) {
        List<int[][]> matrices = readFromStdIn();
        int[] p = createDimension(matrices);
        Pair<int[][], int[][]> resultPair = matrixChainMultiply(p);
        // Print
    }


    static List<int[][]> readFromStdIn() {
        // TODO impl
        return null;
    }
}
