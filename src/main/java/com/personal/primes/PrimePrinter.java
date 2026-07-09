package com.personal.primes;

public class PrimePrinter implements IPrimePrinter {
    private ISieveMarker nonPrimes;

    public PrimePrinter(ISieveMarker nonPrimes) {
        this.nonPrimes = nonPrimes;
    }

    @Override
    public void print(boolean isSummary) {

        System.out.println("Printing primes...");

        int count = 0;

        for (int i = 0; i <= nonPrimes.getPrimesUpto(); i++) {
            if (nonPrimes.isFalse(i)) {
                if (!isSummary) {
                    System.out.printf("%d ", i);
                }
                count++;
            }
        }

        if (isSummary) {
            System.out.printf("Found %d prime numbers up to %d\n", count, nonPrimes.getPrimesUpto());
        }

    }
}
