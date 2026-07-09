package com.leetcode;

import com.lib.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Problem: #3666. Minimum Operations to Equalize Binary String
 */
public class EqualizeStringMinOps {
    public static void main(String[] args) {
        System.out.println(new DFSBasedSolution().minOperations("010001", 3));
        System.out.println(new BFSBasedSolution().minOperations("010001", 3));
    }

    static class BFSBasedSolution {

        public int minOperations(String s, int K) {
            int size = s.length();
            int zeroCount = (int) s.chars().filter(i -> i == '0').count();
            return compute(zeroCount, K, size);
        }

        int compute(int zero, int k, int size) {
            Queue<Integer> queue = new LinkedList<>();

            TreeSet<Integer> even = new TreeSet<>();
            TreeSet<Integer> odd = new TreeSet<>();
            for (int i = 0; i <= size; i++) {
                if (zero == i) {
                    continue;
                }
                if (i % 2 == 0) {
                    even.add(i);
                } else  {
                    odd.add(i);
                }
            }
            int[] visited = new int[size + 1];
            Arrays.fill(visited, -1);
            queue.offer(zero);
            visited[zero] =  0;

            while (!queue.isEmpty()) {
                int zeroCount = queue.poll();
                int steps = visited[zeroCount];

                if (zeroCount == 0) {
                    return steps;
                }
                int minI = Math.max(0, k - size + zeroCount);
                int maxI = Math.min(zeroCount, k);
                int L = zeroCount + k - 2 * maxI;
                int R = zeroCount + k - 2 * minI;
                populateNextNodes(even, odd, visited, queue, zeroCount, k, L, R);
            }
            return -1;
        }

        void populateNextNodes(TreeSet<Integer> even, TreeSet<Integer> odd, int[] visited, Queue<Integer> queue,
                               int zeroCount, int k, int L, int R) {
            TreeSet<Integer> target = (zeroCount + k) % 2 == 0 ? even : odd;
            Integer next = target.ceiling(L);
            while (next != null && next <= R) {
                visited[next] = visited[zeroCount] + 1;
                queue.offer(next);
                target.remove(next);
                next = target.ceiling(L);
            }
        }
    }


    static class DFSBasedSolution {
        // Unoptimised solution

        public int minOperations(String s, int k) {

            int minDepth = Integer.MAX_VALUE;

            LinkedList<Pair<String, Integer>> stack = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            stack.push(new Pair<>(s, 0));

            while (!stack.isEmpty()) {
                Pair<String, Integer> strPair = stack.pop();
                if (isAllOnes(strPair.getKey())) {
                    minDepth = Math.min(minDepth, strPair.getValue());
                    continue; // no need to drill further
                }

                visited.add(strPair.getKey());

                List<String> neighbours = findNeighbours(strPair.getKey(), k);

                for (String neighbour : neighbours) {
                    if (visited.contains(neighbour)) {
                        continue;
                    }
                    stack.push(new Pair<>(neighbour, strPair.getValue() + 1));
                }
            }

            return minDepth == Integer.MAX_VALUE ? -1 : minDepth;
        }

        List<String> findNeighbours(String s, int k) {

            List<String> list = new ArrayList<>();
            char[] arr = s.toCharArray();

            for (int i = 0; i < arr.length; i++) {
                flip(arr, i);
                newStr(arr, i + 1, k - 1, list);
                flip(arr, i);
            }

            return list;
        }

        void flip(char[] arr, int pos) {

            if (arr[pos] == '1') {
                arr[pos] = '0';
            } else {
                arr[pos] = '1';
            }
        }

        void newStr(char[] arr, int pos, int k, List<String> list) {

            if (k == 0) {
                list.add(new String(arr));
                return;
            }

            for (int i = pos; i < arr.length; i++) {
                flip(arr, i);
                newStr(arr, i + 1, k - 1, list);
                flip(arr, i);
            }
        }

        boolean isAllOnes(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '1') {
                    return false;
                }
            }
            return true;
        }
    }
}
