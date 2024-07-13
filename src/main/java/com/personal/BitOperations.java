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

    public static void main(String[] args) {
        printMsbLsb(10);
        System.out.println( 10 - mostSignificantBit(10));
        System.out.println(count(10));
    }
}
