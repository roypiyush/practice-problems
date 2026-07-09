package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * EventualSafeState
 */
public class EventualSafeState {

    public static void main(final String[] args) {
        final EventualSafeState eventualSafeState = new EventualSafeState();
        final int[][] graph = { { 1, 2 }, { 2, 3 }, { 5 }, { 0 }, { 5 }, {}, {} };

        final List<Integer> resultDfs = eventualSafeState.depthFirstSearch(graph);
        final List<Integer> resultKahn = eventualSafeState.kahnApproach(graph);

        System.out.println(resultKahn);
        System.out.println(resultDfs);

    }

    private List<Integer> kahnApproach(final int[][] graph) {

        List<Integer> result = new LinkedList<>();

        int nodeCount = graph.length;
        int[] outDegree = new int[nodeCount];

        Map<Integer, Set<Integer>> adjList = new HashMap<>();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < nodeCount; i++) {
            if (graph[i].length == 0) {
                q.offer(i);
            }

            outDegree[i] = graph[i].length;

            for (int n : graph[i]) {
                Set<Integer> l = adjList.computeIfAbsent(n, n1 -> new HashSet<>());
                l.add(i); // reverse link
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);

            Set<Integer> reverse = adjList.getOrDefault(node, new HashSet<>());
            for (int r : reverse) {
                outDegree[r]--;
                if (outDegree[r] == 0) {
                    q.offer(r);
                }
            }
        }

        return result;
    }

    private List<Integer> depthFirstSearch(final int[][] graph) {

        final int nodeCount = graph.length;
        final boolean[] visiting = new boolean[nodeCount];
        final boolean[] visited = new boolean[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            if (!visited[i]) {
                compute(graph, visiting, visited, i);
            }
        }

        final List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nodeCount; i++) {
            if (!visiting[i]) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean compute(final int[][] graph, final boolean[] visiting, final boolean[] visited, final int curNode) {

        if (visiting[curNode]) {
            // this is unsafe node
            return true;
        }

        if (visited[curNode]) {
            return false;
        }

        visited[curNode] = true;
        visiting[curNode] = true;

        for (final int edge : graph[curNode]) {
            if (compute(graph, visiting, visited, edge)) {
                return true;
            }
        }

        visiting[curNode] = false;
        return visiting[curNode];
    }
}
