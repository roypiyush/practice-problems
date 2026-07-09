package com.personal;

public class BitOperations {

    static int mostSignificantBit(int n) {
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n += 1;
        return n >> 1;
    }

    static int leastSignificantBit(int n) {
        return n & -n;
    }

    static void printMsbLsb(int n) {
        System.out.println(Integer.toBinaryString(n) + " of " + n);
        System.out.println(Integer.toBinaryString(mostSignificantBit(n)) + " msb");
        System.out.println(Integer.toBinaryString(leastSignificantBit(n)) + " lsb");
    }

    static int count(int i) {
        int count = 0;
        while (i > 0) {
            i = i - (i & -i);
            count++;
        }
        return count;
    }

    static void setBit() {
        for (int pos = 0; pos < 5; pos++) {
            System.out.printf("%s ", Integer.toBinaryString(5 | (1 << pos)));
        }
        System.out.println();
    }

    static void isSetBit() {
        for (int pos = 0; pos < 5; pos++) {
            System.out.printf("%s ", (5 & (1 << pos)) != 0);
        }
        System.out.println();
    }

    static void unsetBit() {
        for (int pos = 0; pos < 5; pos++) {
            System.out.printf("%s ", Integer.toBinaryString(7 & ~(1 << pos)));
        }
        System.out.println();
    }

    static void flipBit() {
        for (int pos = 0; pos < 5; pos++) {
            System.out.printf("%s ", Integer.toBinaryString(7 ^ (1 << pos)));
        }
        System.out.println();

        for (int pos = 0; pos < 5; pos++) {
            System.out.printf("%s ", Integer.toBinaryString(0 ^ (1 << pos)));
        }
        System.out.println();
    }

    static void isPowerOfTwo() {
        for (int pos = 0; pos < 31; pos++) {
            int n = (0 | (1 << pos));
            System.out.printf("%s %s ", n, (n - (n - 1)) != 0);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printMsbLsb(10);
        System.out.println( 10 - mostSignificantBit(10));
        System.out.println(count(10));
        setBit();
        isSetBit();
        unsetBit();
        flipBit();
        isPowerOfTwo();
    }
}
