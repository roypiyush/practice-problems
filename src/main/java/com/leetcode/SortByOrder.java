package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortByOrder {
    public static void main(String[] args) {
        System.out.println(new SortByOrder().getKth(12, 15, 7));
    }

    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(1, 1);

        PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> {
            String[] s1s = s1.split("-");
            String[] s2s = s2.split("-");

            if (Integer.parseInt(s1s[1]) < Integer.parseInt(s2s[1])) {
                return -1;
            } else if (Integer.parseInt(s1s[1]) > Integer.parseInt(s2s[1])) {
                return 1;
            } else {
                return Integer.compare(Integer.parseInt(s1s[0]), Integer.parseInt(s2s[0]));
            }
        });

        for (int i = lo; i <= hi; i++) {
            pq.offer(i + "-" + power(i, dp));
        }

        int result = 0;
        while (k-- > 0) {
            String s = pq.poll();
            if (s == null) {
                continue;
            }
            result = Integer.parseInt(s.split("-")[0]);
        }

        return result;
    }

    int power(int num, Map<Integer, Integer> dp) {

        if (dp.get(num) != null) {
            return dp.get(num);
        }

        int result;
        if (num % 2 == 0) {
            result = 1 + power(num / 2, dp);
        } else {
            result = 1 + power(num * 3 + 1, dp);
        }

        dp.put(num, result);
        return result;
    }
}
