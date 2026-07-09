package com.personal.primes;

/*

Algorithm                Space Complexity  Time Complexity  Notes                                                
-----------------------  ----------------  ---------------  ---------------------------------------------------- 
Sieve of Eratosthenes    O(n)              O(n log log n)   Classic, simple, works well for reasonably large $n$ 
Sieve of Sundaram        O(n)              O(n log log n)   Efficient for odd primes, but can’t find 2           
Segmented Sieve          O(sqrt(n))        O(n log log n)   Good for large $n$ ranges where memory is limited    
Atkin Sieve              O(n)              O(n / log n)     Very complex          
Wheel Factorization      O(n)              O(n log log n)   Skips known non-primes for better efficiency         
Sieve of Euler           O(n)              O(n log log n)   Variation with focus on prime powers                 

 */
public class SieveOfEratosthenes {

    public static void main(String[] args) {
        
        // find primes up to n

        int n = 100;
        System.out.println();

        computePrimes(0, n, true);
        System.out.println();
        computePrimes(1, n, true);
        
    }


    private static void computePrimes(int dsType, int uptoNumber, boolean isSummaryPrint) {

        long start = System.currentTimeMillis();
        ISieveMarker nonPrimes = createISieveNonPrimes(dsType, uptoNumber);
        sieve(uptoNumber, nonPrimes);
        System.out.printf("Computed sieve in %dms\n", (System.currentTimeMillis() - start));

        IPrimePrinter printer = printerFactory(nonPrimes);
        printer.print(isSummaryPrint);
    }

    private static ISieveMarker createISieveNonPrimes(int type, int n) {

        switch (type) {
            case 0:
                return new ArrayBasedNonPrimes(n);
            case 1:
                return new BitSetNonPrimes(n);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void sieve(int n, ISieveMarker nonPrimes) {

        System.out.printf("Using %s implementation\n", nonPrimes.getClass().getSimpleName());

        // 0 and 1 are non primes
        nonPrimes.markNumber(0);
        nonPrimes.markNumber(1);

        int sqrt = (int) Math.sqrt(n);

        for (int i = 2; i <= sqrt; i++) {

            if (nonPrimes.isFalse(i)) { // returns prime which is false in nonPrimes
                // take the prime and proceed to mark multiples as non primes
                for (int j = i * i; j <= n; j += i) {
                    nonPrimes.markNumber(j);
                }
            }
        }
    }

    private static IPrimePrinter printerFactory(ISieveMarker nonPrimes) {
        return new PrimePrinter(nonPrimes);
    }

}
