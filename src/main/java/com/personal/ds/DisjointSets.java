package com.personal.ds;

import java.util.HashMap;
import java.util.Map;

public class DisjointSets {

    // Key as child and parent and value
    private final Map<Integer, Integer> childToParent = new HashMap<>();
    // Distance of root from the child
    private final Map<Integer, Integer> rank = new HashMap<>();

    // Tree size to mark distance
    private final Map<Integer, Integer> size = new HashMap<>();

    public DisjointSets(int[] universe) {
        makeSet(universe);
    }

    private void makeSet(int[] universe) {
        if (universe.length == 0) {
            throw new RuntimeException("Cannot create set out of empty universe");
        }

        /*
            Initially all items belong to own set as parent to itself.
            Later we start forming child parent relationship based on input graph
         */
        for (int i : universe) {
            childToParent.put(i, i); // Parent will have reference to itself only
            rank.put(i, 1);
        }
    }

    // uses path compression
    public int find(int k) {
        if (!childToParent.containsKey(k)) {
            throw new RuntimeException("Item k must be part of set during set creation.");
        }

        if (childToParent.get(k) != k) {
            childToParent.put(k, find(childToParent.get(k)));
        }
        return childToParent.get(k);
    }

    /**
     * Builds child to parent relationship. This operation works on the edge,
     * if we were to consider this a graph
     *
     * @param a first element
     * @param b second element
     */
    public void union(int a, int b) {
        unionByRank(a, b);
        unionBySize(a, b);
    }

    private void unionByRank(int a, int b) {
        if (!childToParent.containsKey(a) || !childToParent.containsKey(b)) {
            throw new RuntimeException(String.format("%d & %d should be part of set", a, b));
        }

        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            // root is same. hence no further optimization needed
            return;
        }

        int rankA = rank.getOrDefault(rootA, 0);
        int rankB = rank.getOrDefault(rootB, 0);

        if (rankA < rankB) {
            childToParent.put(rootA, rootB);
        } else {
            childToParent.put(rootB, rootA);
            if (rankA == rankB) {
                // distance to root is same
                // therefore, one of the distance will increase
                rank.put(rootA, rankA + 1);
            }
        }
    }

    private void unionBySize(int a, int b) {

        if (!childToParent.containsKey(a) || !childToParent.containsKey(b)) {
            throw new RuntimeException(String.format("%d & %d should be part of set", a, b));
        }

        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            // root is same. hence no further optimizing path
            return;
        }

        int sizeA = size.getOrDefault(rootA, 1);
        int sizeB = size.getOrDefault(rootB, 1);

        if (sizeA < sizeB) {
            // could swap rootA and rootB
            childToParent.put(rootA, rootB);
            size.put(rootB, sizeA + sizeB);
        } else {
            childToParent.put(rootB, rootA);
            size.put(rootA, sizeA + sizeB);
        }
    }

    public static void printSets(int[] elements, DisjointSets ds) {
        for (int i : elements) {
            System.out.printf("%d->%d ", i, ds.find(i));
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // elements of items
        int[] elements = {1, 2, 3, 4, 5};
        DisjointSets ds = new DisjointSets(elements);

        printSets(elements, ds);

        ds.union(4, 3);        // 4 and 3 are in the same set
        printSets(elements, ds);

        ds.union(2, 1);        // 1 and 2 are in the same set
        printSets(elements, ds);

        ds.union(1, 4);        // 1, 2, 3, 4 are in the same set
        printSets(elements, ds);

        ds.union(5, 3);

        System.out.printf("%d %d %s\n", 5, 1, (ds.find(5) == ds.find(1)));
    }
}
