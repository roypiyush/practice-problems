package com.personal.graphtheory;

import java.util.*;

public class TopologicalSort {
    public static void main(String[] args) {

        int[][] adjMatrix = {
                /*           0  1  2  3  4  5 */
                /* 0 */     {0, 0, 0, 0, 0, 0},
                /* 1 */     {0, 0, 0, 0, 0, 0},
                /* 2 */     {0, 0, 0, 1, 0, 0},
                /* 3 */     {0, 1, 0, 0, 0, 0},
                /* 4 */     {1, 1, 0, 0, 0, 0},
                /* 5 */     {1, 0, 1, 0, 0, 0},
        };

        boolean[] isVisited = new boolean[6];
        System.out.println(StandardTopoSort.doTopologicalSort(adjMatrix, isVisited));
        Arrays.fill(isVisited, false);
        System.out.println(KahnTopoSort.doTopologicalSort(adjMatrix));

    }

    static class KahnTopoSort {
        /*
        1. Compute in-degrees of all nodes.
        2. Add 0 in-degree nodes to a queue.
        3. While queue not empty:
            * Remove node
            * Add to result
            * Reduce in-degree of neighbors
            * If neighbor becomes 0 → add to queue

         Kahn’s algorithm should also detect cycles.
         */
        static List<Integer> doTopologicalSort(int[][] adjMatrix) {

            // In-Degree - number of incoming edges
            int[] inDegree = populateInDegree(adjMatrix);
            Queue<Integer> queue = new LinkedList<>();
            // start with 0-degree nodes
            for (int i = adjMatrix.length - 1; i >= 0; i--) { // reverse just to match with original
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }

            List<Integer> topoSorted = new ArrayList<>();

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                // node with no further dependency added to final list
                topoSorted.add(cur);
                // look for neighbors of cur and decrement indegrees
                for (int n = 0; n < adjMatrix[cur].length; n++) {
                    if (adjMatrix[cur][n] == 0) {
                        // not a neighbor
                        continue;
                    }

                    inDegree[n]--;
                    if (inDegree[n] == 0) {
                        queue.offer(n);
                    }
                }
            }

            if (topoSorted.size() != adjMatrix.length) {
                throw new RuntimeException("No topological sort found. Cycle detected.");
            }
            return topoSorted;
        }

        private static int[] populateInDegree(int[][] adjMatrix) {
            int[] ingress = new int[adjMatrix.length];

            for (int i = adjMatrix.length - 1; i >= 0; i--) {
                for (int j = adjMatrix[i].length - 1; j >= 0; j--) {
                    if (adjMatrix[i][j] == 1) {
                        ingress[j]++;
                    }
                }
            }
            return ingress;
        }
    }

    static class StandardTopoSort {
        static List<Integer> doTopologicalSort(int[][] adjMatrix, boolean[] isVisited) {

            List<Integer> topologicalSortedList = new ArrayList<>();
            for (int i = 0; i <= 5; i++) {
                if (!isVisited[i]) {
                    dfs(adjMatrix, isVisited, topologicalSortedList, i);
                    isVisited[i] = true;
                }
            }
            return topologicalSortedList;
        }

        static void dfs(int[][] adjMatrix, boolean[] isVisited, List<Integer> topoSort, int vertex) {

            if (isVisited[vertex]) {
                return;
            }

            for (int n = 0; n < adjMatrix[vertex].length; n++) {
                if (adjMatrix[vertex][n] == 0 || isVisited[n]) {
                    continue;
                }
                dfs(adjMatrix, isVisited, topoSort, n);
            }
            isVisited[vertex] = true;
            topoSort.add(0, vertex);
        }
    }

}
