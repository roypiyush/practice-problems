package com.leetcode;

public class MinFlips {

    public static void main(String[] args) {
        System.out.println(new MinFlips().minFlips(2, 6, 5));
    }

    public int minFlips(int a, int b, int c) {
        int flip = 0;

        int count = 0;
        while (count < 31) {

            int i = 1 << count;

            int ca = a & i;
            int cb = b & i;

            int cc = c & i;

            if (cc == 0) {

                if ((ca & cb) > 0) {
                    flip += 2;
                } else if ((ca ^ cb) > 0) {
                    flip += 1;
                }
            } else {
                if ((ca | cb) == 0) {
                    flip += 1;
                }
            }

            count++;
        }

        return flip;
    }
}
