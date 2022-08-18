package com.personal.al;

public class SegmentTree {
    private int tree[];

    private int getMid(int s, int e) {
        return (s + e) / 2;
    }

    private int construct(int[] arr, int start, int end, int i) {
        if (start == end) {
            tree[i] = arr[start];
            return tree[i];
        }
        int mid = getMid(start, end);
        tree[i] = Math.min(construct(arr, start, mid, 2 * i + 1), construct(arr, mid + 1, end, 2 * i + 2));
        return tree[i];
    }

    private int rangeQuery(int qs, int qe, int s, int e, int treeIndex) {
        if (qs <= s && e <= qe) {
            /*
              qs              qe
              |---------------|
                |----------|
                s          e
             */
            return tree[treeIndex];
        } else if (e < qs || s > qe) {
            /*
                                 qs              qe
                                 |---------------|
                |----------|
                s          e

                                    OR

                qs              qe
                |---------------|
                                    |----------|
                                    s          e
             */
            return Integer.MAX_VALUE;
        }

                    /*
                     qs              qe
                     |---------------|
                |----------|
                s          e

                                    OR

                qs              qe
                |---------------|
                         |----------|
                         s          e
             */

        int m = getMid(s, e);
        return Math.min(
                rangeQuery(qs, qe, s, m, 2 * treeIndex + 1),
                rangeQuery(qs, qe, m + 1, e, 2 * treeIndex + 2));
    }

    public static void main(String args[]) {
        int arr[] = {2, 5, 1, 4, 9, 3};
        int n = arr.length;
        int h = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, h) - 1;
        SegmentTree tree = new SegmentTree();
        tree.tree = new int[max_size];
        tree.construct(arr, 0, arr.length - 1, 0);

        System.out.println(tree.rangeQuery(4, 4, 0, arr.length -1, 0));
    }
}
