package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(new CombinationSumIII().combinationSum3(3, 9));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        compute(k, n, result, new ArrayList<>(), 0);
        return result;
    }

    void compute(int k, int n, List<List<Integer>> result, List<Integer> l, int prevNum) {

        if (k == 0) {
            if (n == 0) {
                result.add(new ArrayList<>(l));
            }
            return;
        }

        for (int i = prevNum + 1; i <= n; i++) {
            l.add(i);
            compute(k - 1, n - i, result, l, i);
            l.remove(l.size() - 1);
        }

    }
}
