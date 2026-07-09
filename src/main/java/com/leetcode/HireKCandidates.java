package com.leetcode;

import com.lib.Pair;

import java.util.PriorityQueue;

public class HireKCandidates {
    public static void main(String[] args) {
        int[] costs = {17,12,10,2,7,2,11,20,8};

        /*

        {17,12,10, ,11,20,8};
        2 2 7
         */
        int k = 3;
        int candidates = 4;
        System.out.println(new HireKCandidates().totalCost(costs, k, candidates));
    }
    public long totalCost(int[] costs, int k, int candidates) {

        PriorityQueue<Pair<Integer, Boolean>> pq = new PriorityQueue<>((o1, o2) -> {
            if (costs[o1.getKey()] - costs[o2.getKey()] == 0) {
                if (o1.getValue() && !o2.getValue()) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                return costs[o1.getKey()] - costs[o2.getKey()];
            }
        });

        int lptr = -1;
        int rptr = costs.length;

        int chooseCandidates = 0;
        while (chooseCandidates < candidates) {

            if (lptr + 1 < rptr) {
                lptr++;
                pq.offer(new Pair<>(lptr, true));
            }

            if (lptr < rptr - 1) {
                rptr--;
                pq.offer(new Pair<>(rptr, false));
            }
            chooseCandidates++;
        }


        lptr = pq.size() / 2 - (pq.size() % 2 == 0 ? 1 : 0);
        rptr = costs.length - pq.size() / 2;

        long total = 0;

        while (k > 0 && !pq.isEmpty()) {

            Pair<Integer, Boolean> p = pq.poll();
            total += costs[p.getKey()];
            if (p.getValue()) {
                if (lptr + 1 < rptr) {
                    lptr++;
                    pq.offer(new Pair<>(lptr, true));
                }
            } else {
                if (lptr < rptr - 1) {
                    rptr--;
                    pq.offer(new Pair<>(rptr, false));
                }
            }

            k--;
        }

        return total;
    }
}
