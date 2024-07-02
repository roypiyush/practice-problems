package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class JumpGame2 {
    public int jump(int[] nums) {

        int[] res = new int[nums.length];

        Arrays.fill(res, -1);
        int j = nums.length - 1;
        res[j] = 0;
        j--;

        while (j >= 0) {

            int min = Integer.MAX_VALUE;

            for (int k = nums[j]; k > 0; k--) {
                int step = getStep(res, j + k);
                if (step == -1) {
                    continue;
                }
                min = Math.min(min, step + 1);
            }

            // minor handling required to store -1 denoting unreachable path
            res[j] = Integer.MAX_VALUE == min ? -1 : min;
            j--;
        }

        return res[0];

    }

    /**
     * Graceful handling, if position goes beyond last index then return zero
     * If -1 is returned it means unreachable path to last index from pos
     *
     * @param res
     * @param pos
     * @return
     */
    private int getStep(int[] res, int pos) {
        if (pos >= res.length) {
            return 0;
        }
        return res[pos];
    }

    public static void main(String[] args) {
        int[] nums = {2,3,0,1,4};

        HashMap<Integer, List<Integer>> integerIntegerHashMap = new HashMap<>();
        integerIntegerHashMap.computeIfAbsent(1, i -> new ArrayList<>());
        System.out.println(new JumpGame2().jump(nums));
    }


}
