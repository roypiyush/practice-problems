package com.personal.ds;

import java.util.HashMap;
import java.util.Map;

public class DisjointSets {
    // Key as child and parent and value
    private Map<Integer, Integer> childToParent = new HashMap<>();

    public void makeSet(int[] universe) {
        /*
            Initially all items belong to own set as parent to itself.
            Later we start forming child parent relationship based on input graph
         */
        for (int i : universe) {
            childToParent.put(i, i); // Parent will have reference to itself only
        }
    }

    public int find(int k) {
        while (childToParent.get(k) != k) {
            k = childToParent.get(k);
        }
        return k;
    }

    /**
     * Builds child to parent relationship. This operation works on the edge, if we were to consider this a graph
     *
     * @param a
     * @param b
     */
    public void union(int a, int b) {
        int a1 = find(a);
        int b1 = find(b);

        childToParent.put(a1, b1);
    }

    public static void printSets(int[] elements, DisjointSets ds) {
        for (int i : elements) {
            System.out.print(ds.find(i) + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // elements of items
        int[] elements = {1, 2, 3, 4, 5};
        DisjointSets ds = new DisjointSets();

        ds.makeSet(elements);
        printSets(elements, ds);

        ds.union(4, 3);        // 4 and 3 are in the same set
        printSets(elements, ds);

        ds.union(2, 1);        // 1 and 2 are in the same set
        printSets(elements, ds);

        ds.union(1, 3);        // 1, 2, 3, 4 are in the same set
        printSets(elements, ds);
    }
}
