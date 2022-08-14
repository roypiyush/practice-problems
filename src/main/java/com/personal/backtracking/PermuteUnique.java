package com.personal.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class PermuteUnique {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3, 3};
        System.out.println(new PermuteUnique().permuteUnique(nums));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        permute(nums, res, 0);
        return res;
    }


    void permute(int[] nums, List<List<Integer>> res, int i) {
        if (i == nums.length) {
            List<Integer> l = new ArrayList<>();
            for (int k : nums) {
                l.add(k);
            }
            res.add(l);
        } else {
            Set<Integer> unique = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                if (unique.contains(nums[j])) {
                    continue;
                }
                unique.add(nums[j]);
                swap(nums, i, j);
                permute(nums, res, i + 1);
                swap(nums, i, j);
            }
        }
    }


    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
