package com.personal.al;

public class MaxSegmentTree {
    // Tree has its own storage locations
    // has nothing to do with array initially passed during construction
    private int[] tree;

    private int getMid(int s, int e) {
        return (s + e) / 2;
    }

    private int construct(int[] arr, int start, int end, int indexOfNode) {
        if (start == end) {
            tree[indexOfNode] = arr[start];
            return tree[indexOfNode];
        }
        int mid = getMid(start, end);
        int leftChild = 2 * indexOfNode + 1;
        int rightChild = 2 * indexOfNode + 2;
        tree[indexOfNode] = Math.min(
                construct(arr, start, mid, leftChild),
                construct(arr, mid + 1, end, rightChild)
        );
        return tree[indexOfNode];
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
        int leftChild = 2 * treeIndex + 1;
        int rightChild = 2 * treeIndex + 2;
        if (qe <= m) {
            return rangeQuery(qs, qe, s, m, leftChild);
        } else if (qs > m) {
            return rangeQuery(qs, qe, m + 1, e, rightChild);
        } else {
            // intersection
            return Math.min(
                    rangeQuery(qs, qe, s, m, leftChild),
                    rangeQuery(qs, qe, m + 1, e, rightChild));
        }
    }

    public static void main(String[] args) {

        int[] arr = {2, 5, 1, 4, 9, 3};
        int n = arr.length;
        int h = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, h) - 1;
        MaxSegmentTree tree = new MaxSegmentTree();
        tree.tree = new int[max_size];
        tree.construct(arr, 0, arr.length - 1, 0);

        System.out.println(tree.rangeQuery(4, 4, 0, arr.length -1, 0));
        System.out.println(tree.rangeQuery(2, 5, 0, arr.length -1, 0));
    }
}
