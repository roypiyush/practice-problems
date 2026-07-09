package com.personal.primes;

import java.util.BitSet;

public class BitSetNonPrimes implements ISieveMarker {

    private BitSet markerSet;

    public BitSetNonPrimes(int number) {
        markerSet = new BitSet(number);
    }

    @Override
    public boolean isFalse(int number) {
        return !markerSet.get(number) ;
    }

    @Override
    public void markNumber(int number) {
        markerSet.set(number);
    }

    @Override
    public int getPrimesUpto() {
        return markerSet.length() - 1;
    }
    
}
