package com.personal.al;

public class ExtendedEuclid {

    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    public static void extendedEuclidean(long a, long b) {
        long oldR = a, r=b;
        long oldS = 1, s = 0;
        long oldT = 0, t = 1;

        while(r != 0){
            long quotient = oldR / r;

            long rTemp = r;
            r = oldR - quotient * r; oldR = rTemp;

            long sTemp = s;
            s = oldS - quotient * s; oldS = sTemp;

            long tTemp = t;
            t = oldT - quotient * t; oldT = tTemp;
        }

        System.out.printf("Coefficients: s:%s, t:%s%n", oldS,oldT);
        System.out.printf("GCD:%s%n", oldR);
    }

    public static void main(String[] args) {
        //System.out.println(gcd(785646, 252));
        System.out.println(gcd(15, 35));
    }
}
