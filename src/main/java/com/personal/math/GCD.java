package com.personal.math;

public class GCD {
    public static void main(String[] args) {

        int a = 100;
        int b = 30;
        final GCD gcd = new GCD();
        System.out.println(gcd.gcdRecursive(a, b));
        System.out.println(gcd.gcdIterative(a, b));

        System.out.println("LCM " + (a * b) / gcd.gcdIterative(a, b));
    }

    private int gcdRecursive(final int a, final int b) {
        if (b == 0 || a == 0) {
            return a;
        }
        if (a < b) {
            return gcdRecursive(b, a);
        }

        int r = a % b;
        return gcdRecursive(b, r);
    }

    private int gcdIterative(int a, int b) {
        if (b == 0 || a == 0) {
            return a;
        }

        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }

        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

}
