package com.leetcode;

public class MaxRepeating {

    public static void main(String[] args) {
        System.out.println(new MaxRepeating()
                .maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba"));
    }

    public int maxRepeating(String sequence, String word) {

        int count = 0;
        String repeat = word;
        while (sequence.contains(repeat)) {
            count++;
            repeat += word;
        }

        return count;
    }

}
