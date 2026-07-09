package com.leetcode;

import java.util.Arrays;

public class DecodeWays {

    boolean isValid(char[] c) {
        //System.out.println(Arrays.toString(c));

        if (c.length == 0) {
            return true;
        } else if (c.length == 1) {

            int c1 = c[0] - 48;
            return c1 >= 1 && c1 <= 9;

        } else if (c.length == 2) {

            int c1 = c[0] - 48;
            int c2 = c[1] - 48;

            if (c1 == 1) {
                return c2 >= 0 && c2 <= 9;
            } else if (c1 == 2) {
                return c2 >= 0 && c2 <= 6;
            }

            return (c1 == 49 || c1 == 50) && (c2 >= 48 && c2 < 58);

        } else {

            return false;
        }
    }

    public int compute(int[] R, char[] s, int index) {

        if (index == s.length) {
            R[index] = 1;
            return R[index];
        }

        if (R[index] > 0) {
            return R[index];
        }


        int r1 = 0, r2 = 0;

        if (index + 1 <= s.length && isValid(Arrays.copyOfRange(s, index, index + 1))) {
            r1 = compute(R, s, index + 1);
        }

        if (index + 2 <= s.length && isValid(Arrays.copyOfRange(s, index, index + 2))) {
            r2 = compute(R, s, index + 2);
        }

        R[index] = r1 + r2;

        return R[index];
    }

    public int numDecodings(String string) {

        char[] s = string.toCharArray();
        int[] R = new int[s.length + 1];
        Arrays.fill(R, -1);
        R[0] = 0;

        return compute(R, s, 0);
    }

    public static void main(String[] args) {
        String s = "27";
        System.out.println(new DecodeWays().numDecodings(s));
    }
}
