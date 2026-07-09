package com.leetcode;

public class PathWithMaxProb {

    public static void main(String[] args) {
        PathWithMaxProb pathWithMaxProb = new PathWithMaxProb();
        final int[][] edges = {{1, 4}, {2, 4}, {0, 4}, {0, 3}, {0, 2}, {2, 3}};
        final double[] succProb = {0.37, 0.17, 0.93, 0.23, 0.39, 0.04};
        System.out.println(pathWithMaxProb.maxProbability(5, edges, succProb, 3, 4));
    }

    public double maxProbability(int numVertices, int[][] edges, double[] succProb, int startNode, int endNode) {

        PQ pq = new PQ(numVertices);

        double[][] graph = prepareGraph(numVertices, edges, succProb);

        pq.initKey(startNode);
        pq.setDistance(startNode, 1.0);

        while (pq.getHeapSize() > 0) {

            int maxKey = pq.extractMax();
            if (maxKey == 4) {
                break;
            }
            double[] neighbours = graph[maxKey];

            for (int n = 0; n < neighbours.length; n++) {

                if (graph[maxKey][n] == 0.0) {
                    continue;
                }

                double w = graph[maxKey][n];

                double wt = pq.getDistance(maxKey) * w;
                if (pq.getDistance(n) < wt) {
                    pq.setDistance(n, wt);
                    pq.heapIncreaseKey(n, wt);
                }
            }


        }

        return pq.getDistance(endNode);
    }

    double[][] prepareGraph(int numVertices, int[][] edges, double[] succProb) {
        double[][] graph = new double[numVertices][numVertices];
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int x = edge[0];
            int y = edge[1];

            graph[x][y] = succProb[i];
            graph[y][x] = succProb[i];
        }
        return graph;
    }

    static class PQ {
        int heapSize;
        int[] heap;
        double[] distances;

        public PQ(int heapSize) {

            this.heapSize = heapSize;

            this.heap = new int[heapSize];
            for (int i = 0; i < heapSize; i++) {
                this.heap[i] = i;
            }
            this.distances = new double[heapSize];
        }

        void initKey(int key) {
            heap[key] = 0;
            heap[0] = key;
        }

        void setDistance(int key, double value) {
            distances[key] = value;
        }

        double getDistance(int key) {
            return distances[key];
        }

        int parent(int i) {
            return i / 2;
        }

        int left(int i) {
            return i * 2 + 1;
        }

        int right(int i) {
            return i * 2 + 2;
        }

        void heapIncreaseKey(int key, double value) {

            int i;
            for (i = 0; i < heapSize; i++) {
                if (heap[i] == key) {
                    break;
                }
            }
            distances[heap[i]] = value;

            while (parent(i) >= 0 && distances[heap[parent(i)]] < distances[heap[i]]) {
                int t = heap[parent(i)];
                heap[parent(i)] = heap[i];
                heap[i] = t;

                i = parent(i);
            }
        }

        int extractMax() {

            int minValue = heap[0];
            heap[0] = heap[heapSize - 1];
            heapSize--;

            maxHeapify(0);
            return minValue;
        }

        void maxHeapify(int key) {

            int max = key;
            int l = left(key);
            int r = right(key);

            if (l < heapSize && distances[heap[l]] > distances[heap[max]]) {
                max = l;
            }

            if (r < heapSize && distances[heap[r]] > distances[heap[max]]) {
                max = r;
            }

            if (key != max) {
                int t = heap[max];
                heap[max] = heap[key];
                heap[key] = t;

                maxHeapify(max);
            }
        }

        int getHeapSize() {
            return this.heapSize;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (final int j : heap) {
                sb.append(String.format("(k=%s v=%s) ", j, distances[j]));
            }
            return sb.toString();
        }
    }
}
