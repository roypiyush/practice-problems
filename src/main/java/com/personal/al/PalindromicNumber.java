package com.personal.al;

public class PalindromicNumber {
    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            if (isPalindromicNumber(i)) {
                System.out.printf("%s ", i);
            }
        }
    }

    private static boolean isPalindromicNumber(final int number) {
        if (number < 0) {
            return false;
        }

        int result = 0;
        int length = 0;
        int t = number;
        while (t / 10 != 0) {
            length++;
            t = t / 10;
        }

        t = number;
        for (int power = length; power >= 0; power--) {
            int mod = t % 10;
            t = t / 10;
            result += Math.pow(10, power) *  mod;
        }
        return result == number;
    }
}
