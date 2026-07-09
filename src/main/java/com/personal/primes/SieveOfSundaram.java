package com.personal.primes;

public class SieveOfSundaram {

    public static void main(String[] args) {

        // find primes up to n
        int n = 1_000;
        computePrimes(0, n, true);

    }

    private static void computePrimes(int dsType, int uptoNumber, boolean isSummaryPrint) {

        long start = System.currentTimeMillis();
        int half = (uptoNumber - 1) / 2;
        ISieveMarker primeMarker = new ArrayBasedNonPrimes(half + 1);
        sieve(half, primeMarker);
        System.out.printf("Computed sieve in %dms\n", (System.currentTimeMillis() - start));

        IPrimePrinter printer = new SundaramSievePrinter(primeMarker);
        printer.print(isSummaryPrint);
    }

    /**
     * Here we mark i, actual primes would be 2i + 1
     * 
     * @param half
     * @return markers to generate primes of the form 2i + 1
     */
    private static void sieve(int half, ISieveMarker sieveMarker) {

        sieveMarker.markNumber(0);

        for (int i = 1; i <= half; i++) {
            for (int j = i; i + j + 2 * i * j <= half; j++) {

                // mark i + j + 2 * i * j as non-prime
                int marker = i + j + 2 * i * j;
                sieveMarker.markNumber(marker);
            }
        }
    }

    /**
     * Sieve works along with its printer.
     */
    private static class SundaramSievePrinter implements IPrimePrinter {

        private ISieveMarker marker;

        public SundaramSievePrinter(ISieveMarker marker) {
            this.marker = marker;
        }

        @Override
        public void print(boolean isSummaryPrint) {

            // markers take form 2i + 1 = n
            int n = marker.getPrimesUpto();

            int total = 2 * n;
            int count = 0;
            System.out.println("Primes up to " + (2 * n) + ": ");

            if (n >= 2) {
                if (!isSummaryPrint) {
                    System.out.print("2 ");
                }
                count++;
            }

            for (int i = 1; i < n; i++) {
                if (marker.isFalse(i)) {
                    count++;
                    if (!isSummaryPrint) {
                        System.out.print((2 * i + 1) + " ");
                    }
                }
            }

            if (isSummaryPrint) {
                System.out.printf("Found %d prime numbers up to %d\n", count, total);
            }
        }
    }

}
