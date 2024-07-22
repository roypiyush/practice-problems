package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Node> nodes = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.offer(node);

        while (!queue.isEmpty()) {

            Node oldNode = queue.poll();

            Node newNode = getOrCreate(nodes, oldNode.val);
            for (Node neighbor : oldNode.neighbors) {
                Node n = getOrCreate(nodes, neighbor.val);

                // this check can be improved
                if (!newNode.neighbors.stream().anyMatch(nn -> nn.val == n.val)) {
                    newNode.neighbors.add(n);
                }

                if (!visited.contains(neighbor.val)) {
                    // don't add for visiting if already visited
                    queue.offer(neighbor);
                }
            }

            visited.add(oldNode.val);
        }

        return nodes.get(node.val);
    }

    private Node getOrCreate(final Map<Integer, Node> nodes, final Integer nodeNumber) {
        final Node newValue = new Node(nodeNumber);
        final Node node = nodes.putIfAbsent(nodeNumber, newValue);
        return node == null ? newValue : node;
    }

    public static void main(String[] args) {
        final CloneGraph cloneGraph = new CloneGraph();

        Node _1 = new Node(1);
        Node _2 = new Node(2);
        Node _3 = new Node(3);
        Node _4 = new Node(4);

        _1.neighbors.add(_2);
        _1.neighbors.add(_4);

        _2.neighbors.add(_1);
        _2.neighbors.add(_3);

        _3.neighbors.add(_2);
        _3.neighbors.add(_4);

        _4.neighbors.add(_1);
        _4.neighbors.add(_3);

        cloneGraph.cloneGraph(_1);
    }
}
