package com.personal.primes;

/**
 * Storing inverted boolean to avoid linear time to convert to true
 */
public class ArrayBasedNonPrimes implements ISieveMarker {

    private boolean[] markers;
    private int uptoNumber;

    public ArrayBasedNonPrimes(int number) {
        markers = new boolean[number + 1]; // +1 for zero
        if (number < 2) {
            throw new IllegalArgumentException("anyting below 2 is non prime");
        }

        this.uptoNumber = number;
    }

    @Override
    public boolean isFalse(int number) {
        // checks if number is prime
        return !markers[number];
    }

    @Override
    public void markNumber(int number) {
        // marks the number as non prime
        markers[number] = true;
    }

    @Override
    public int getPrimesUpto() {
        return uptoNumber;
    }
}
