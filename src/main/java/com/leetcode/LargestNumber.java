package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LargestNumber {
    public static void main(String[] args) {
        final int[] nums = {111311, 1113};
        System.out.println(new LargestNumber().largestNumber(nums));
    }

    private String largestNumber(final int[] nums) {
        List<String> list = new ArrayList<>();
        for (int n : nums) {
            list.add(Integer.toString(n));
        }
        Collections.sort(list, (o1, o2) -> {
            String order1 = o1 + o2;
            String order2 = o2 + o1;
            return order2.compareTo(order1);
        });

        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s);
        }

        return result.toString();
    }
}
