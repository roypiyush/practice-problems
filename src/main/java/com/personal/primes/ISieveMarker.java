package com.personal.primes;

public interface ISieveMarker {
    public boolean isFalse(int number);
    public void markNumber(int number);
    
    // Number up to which primes to be generated
    public int getPrimesUpto();
}
