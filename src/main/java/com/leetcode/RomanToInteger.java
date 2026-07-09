package com.leetcode;

public class RomanToInteger {
    static int getInt(char roman) {
        switch (roman) {
            case 'I':
            case 'i':
                return 1;
            case 'V':
            case 'v':
                return 5;
            case 'X':
            case 'x':
                return 10;
            case 'L':
            case 'l':
                return 50;
            case 'C':
            case 'c':
                return 100;
            case 'D':
            case 'd':
                return 500;
            case 'M':
            case 'm':
                return 1000;
        }
        throw new IllegalArgumentException(Character.toString(roman));
    }

    public static void main(String[] args) {
        System.out.println(convertToInt("IL"));
        System.out.println(convertToInt("cxxxxiv"));
        System.out.println(convertToInt("MMCMXCII"));
    }

    private static int getOps(int i1, int i2) {
        if (i1 >= i2) {
            return 1;
        } else {
            return -1;
        }
    }

    private static int convertToInt(String romanValue) {
        int i = romanValue.length() - 1;
        int intValue = 0;
        int errorCount = 0;
        while (i >= 0) {
            int i1 = getInt(romanValue.charAt(i));
            int i2 = i + 1 < romanValue.length() ? getInt(romanValue.charAt(i + 1)) : 0;
            if (i1 == i2) {
                errorCount = 2 * errorCount + 1;
            } else if (i1 < i2) {
                errorCount += 7;
            } else {
                errorCount = 0;
            }
            if (errorCount > 8) {
                throw new IllegalArgumentException("Invalid number " + romanValue);
            }
            intValue = intValue + i1 * getOps(i1, i2);
            i--;
        }
        return intValue;
    }
}
