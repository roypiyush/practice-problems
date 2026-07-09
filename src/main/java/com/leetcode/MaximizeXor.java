package com.leetcode;

public class MaximizeXor {
    public static void main(String[] args) {
        int num1 = 25;
        int num2 = 72;

        System.out.printf("Binary : %s Bit bitCount %s\n", Integer.toBinaryString(num1), Integer.bitCount(num1));
        System.out.printf("Binary : %s Bit bitCount %s\n", Integer.toBinaryString(num2), Integer.bitCount(num2));
        System.out.printf("Binary : %s Bit bitCount %s\n", Integer.toBinaryString(24), Integer.bitCount(24));

        System.out.println(new MaximizeXor().maximizeXor(num1, num2));
    }

    private int maximizeXor(int num1, int num2) {
        int result = 0;
        int count = Integer.bitCount(num2);

        for (int i = 30; i >= 0; i--) {
            if (count == 0) {
                break;
            }

            int x = 1 << i;
            if ((x & num1) == x) {
                result |= x;
                count--;
            }
        }

        for (int i = 0; i < 31; i++) {
            if (count == 0) {
                break;
            }

            int x = 1 << i;
            if ((result & x) == 0) {
                result |= x;
                count--;
            }
        }

        return result;
    } 

}
