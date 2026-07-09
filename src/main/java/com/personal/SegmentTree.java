package com.personal;

import java.util.HashMap;
import java.util.Map;


public class SegmentTree {

    private static int N = 10;
    Map<Integer, Integer> tree;
    Map<Integer, Integer> lazy;

    public SegmentTree() {
        tree = new HashMap<>();
        lazy = new HashMap<>();
    }

    void push(int i, int l, int r) {

        Integer value = lazy.get(i);
        if (value == null) {
            return;
        }

        // update current node
        // update will be from all the nodes within the range. hence multiply
        // in case of Segment Sum Tree
        tree.put(i, tree.getOrDefault(i, 0) + (r - l + 1) * value);

        if (l != r) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            lazy.put(left, lazy.getOrDefault(left, 0) + value);
            lazy.put(right, lazy.getOrDefault(right, 0) + value);
        }
        // clear the current lazy node
        lazy.remove(i);
    }

    public void updateRange(int ql, int qr, int value) {
        updateRange(0, 0, N, ql, qr, value);
    }

    private void updateRange(int idx, int l, int r, int ql, int qr, int value) {
        push(idx, l, r);

        if (qr < l || r < ql) {
            return;
        }

        if (ql <= l && r <= qr) {
            lazy.put(idx, lazy.getOrDefault(idx, 0) + value);
            push(idx, l, r);
            return;
        }

        int mid = l + (r - l) / 2;
        updateRange(2 * idx + 1, l, mid, ql, qr, value);
        updateRange(2 * idx + 2, mid + 1, r, ql, qr, value);

        int lval = tree.getOrDefault(2 * idx + 1, 0);
        int rval = tree.getOrDefault(2 * idx + 2, 0);

        tree.put(idx, lval + rval);
    }

    public int queryRange(int l, int r) {
        return queryRange(0, 0, N, l, r);
    }

    private int queryRange(int idx, int l, int r, int ql, int qr) {
        push(idx, l, r);

        if (qr < l || r < ql) {
            return 0;
        }

        if (ql <= l && r <= qr) {
            return tree.getOrDefault(idx, 0);
        }

        int mid = l + (r - l) / 2;
        return queryRange(2 * idx + 1, l, mid, ql, qr)
                + queryRange(2 * idx + 2, mid + 1, r, ql, qr);
    }

    public static void main(String[] args) {
        SegmentTree seg = new SegmentTree();

        seg.updateRange(2, 4, 5);

        // Add 3 to [3,10]
        seg.updateRange(3, 10, 3);

        // Queries
        System.out.println(seg.queryRange(2, 4));
        System.out.println(seg.queryRange(3, 3));
        System.out.println(seg.queryRange(0, 10));
    }

}
