package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequence {

    public static void main(String[] args) {
        final RepeatedDNASequence rabinKarpAlgo = new RepeatedDNASequence(AlgoType.RABIN_KARP);
        System.out.println(rabinKarpAlgo.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(rabinKarpAlgo.findRepeatedDnaSequences("AAAAAAAAAAAAA"));
        System.out.println(rabinKarpAlgo.findRepeatedDnaSequences("AAAAAAAAAAA"));

        final RepeatedDNASequence kmp = new RepeatedDNASequence(AlgoType.KMP);
        System.out.println(kmp.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(kmp.findRepeatedDnaSequences("CCGGCCGGCCGGCC"));
    }

    public RepeatedDNASequence(final AlgoType algoType) {
        this.algoType = algoType;
    }

    private Enum algoType;

    enum AlgoType {
        KMP,
        RABIN_KARP
    }

    private List<String> findRepeatedDnaSequences(String s) {
        if (algoType == AlgoType.RABIN_KARP) {
            long start = System.currentTimeMillis();
            try {
                return new RabinKarpImpl().findRepeatedDnaSequences(s);
            } finally {
                System.out.printf("Time taken %d ms\t", (System.currentTimeMillis() - start));
            }
        } else if (algoType == AlgoType.KMP) {
            long start = System.currentTimeMillis();
            try {
                return new KMPImpl().findRepeatedDnaSequences(s);
            } finally {
                System.out.printf("Time taken %d ms\t", (System.currentTimeMillis() - start));
            }
        }
        throw new IllegalArgumentException("" + algoType);
    }

    class KMPImpl {

        private List<String> findRepeatedDnaSequences(final String s) {
            Map<String, Integer> map = new HashMap<>();

            int n = s.length();
            int PAT_SIZE = 10;

            for (int i = 0; i < n - PAT_SIZE; i++) {
                String pat = s.substring(i, i + PAT_SIZE);
                int[] pi = computePrefixFunction(pat, pat.length());
                int q = -1;

                for (int j = i + 1; j < n; j++) {
                    while (q >= 0 && pat.charAt(q + 1) != s.charAt(j)) {
                        q = pi[q];
                    }

                    if (pat.charAt(q + 1) == s.charAt(j)) {
                        q = q + 1;
                    }

                    if (q + 1 == PAT_SIZE) {
                        final Integer value = map.get(pat);
                        if (value == null) {
                            map.put(pat, 1);
                        } else {
                            map.put(pat, value + 1);
                        }
                        break;
                    }
                }
            }

            List<String> result = new ArrayList<>();
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() >= 1) {
                    result.add(e.getKey());
                }
            }

            return result;
        }

        int[] computePrefixFunction(String pat, int m) {
            int[] pi = new int[m];
            int k = -1;
            pi[0] = -1; // prefix at the beginning

            for (int i = 1; i < m; i++) {
                while (k >= 0 && pat.charAt(k + 1) != pat.charAt(i)) {
                    k = pi[k]; // reverts to previous prefix
                }
                if (pat.charAt(k + 1) == pat.charAt(i)) {
                    k = k + 1;
                }
                pi[i] = k;
            }

            return pi;
        }

    }

    class RabinKarpImpl {
        private List<String> findRepeatedDnaSequences(final String s) {

            Map<String, Integer> map = new HashMap<>();

            int n = s.length();
            int PAT_SIZE = 10;
            int q = 10000009;
            int d = 4;
            int H = ((int) Math.pow(d, PAT_SIZE - 1)) % q;

            for (int i = 0; i < n - PAT_SIZE; i++) {
                String pat = s.substring(i, i + PAT_SIZE);
                int patHash = getHash(pat, d, q, 0, PAT_SIZE);

                for (int j = i + 1; j <= n - PAT_SIZE; j++) {
                    if (matches(s, pat, n, PAT_SIZE, d, q, H, patHash, j)) {
                        final Integer value = map.get(pat);
                        if (value == null) {
                            map.put(pat, 1);
                        } else {
                            map.put(pat, value + 1);
                        }
                    }
                }
            }

            List<String> result = new ArrayList<>();
            for (Map.Entry<String, Integer> e : map.entrySet()) {
                if (e.getValue() >= 1) {
                    result.add(e.getKey());
                }
            }

            return result;
        }

        int getHash(String t, int d, int q, int start, int size) {
            int p = 0;

            for (int i = start; i < start + size; i++) {
                p = (d * p + getValue(t.charAt(i))) % q;
            }
            return p;
        }

        boolean matches(String str, String pat, int n, int m, int d, int q, int H, int p, int start) {

            int t = getHash(str, d, q, start, m);

            for (int i = start; i <= n - m; i++) {
                if (p == t) {
                    if (check(str, pat, i)) {
                        return true;
                    }
                }

                if (i + 1 < n) {
                    t = d * (t - H * getValue(str.charAt(i))) + getValue(str.charAt(i + 1));
                }
            }

            return false;
        }

        boolean check(String str, String pat, int i) {
            int size = pat.length();
            return str.substring(i, i + size).equals(pat);
        }

        int getValue(char c) {
            if ('A' == c) {
                return 1;
            } else if ('C' == c) {
                return 2;
            } else if ('G' == c) {
                return 3;
            } else {
                return 4;
            }

            // Assuming no other letter in sequence
        }
    }

}
