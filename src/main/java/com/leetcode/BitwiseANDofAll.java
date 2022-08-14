package com.leetcode;

public class BitwiseANDofAll {
    public int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
           left = left >>> 1;
           right = right >>> 1;
           count++;
        }
        return left << count;
    }

    public static void main(String[] args) {
        System.out.println(new BitwiseANDofAll().rangeBitwiseAnd(1, 242344234));
    }
}
