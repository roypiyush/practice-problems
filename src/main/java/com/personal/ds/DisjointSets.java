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
            childToParent.put(i, i);
        }
    }

    public int find(int k) {
        while (childToParent.get(k) != k) {
            k = childToParent.get(k);
        }
        return k;
    }

    public void union(int a, int b) {
        int a1 = find(a);
        int b1 = find(b);

        childToParent.put(a1, b1);
    }

    public static void printSets(int[] universe, DisjointSets ds) {
        for (int i : universe) {
            System.out.print(ds.find(i) + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // universe of items
        int[] universe = {1, 2, 3, 4, 5};

        // initialize `DisjointSet` class
        DisjointSets ds = new DisjointSets();

        // create a singleton set for each element of the universe
        ds.makeSet(universe);
        printSets(universe, ds);

        ds.union(4, 3);        // 4 and 3 are in the same set
        printSets(universe, ds);

        ds.union(2, 1);        // 1 and 2 are in the same set
        printSets(universe, ds);

        ds.union(1, 3);        // 1, 2, 3, 4 are in the same set
        printSets(universe, ds);
    }
}
