package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindOriginalTypedStringII {

    int MOD = 1_000_000_007;

    public static void main(String[] args) {
        FindOriginalTypedStringII findOriginalTypedStringII = new FindOriginalTypedStringII();
        RecursiveSolution solution1 = findOriginalTypedStringII.new RecursiveSolution();

        if (5 != solution1.possibleStringCount("aabbccdd", 7))
            System.out.println("Invalid 1");
        if (1 != solution1.possibleStringCount("aabbccdd", 8))
            System.out.println("Invalid 2");
        if (8 != solution1.possibleStringCount("aaabbb", 3))
            System.out.println("Invalid 3");

        DpSolutionUnoptimised solution2 = findOriginalTypedStringII.new DpSolutionUnoptimised();
        solution2.possibleStringCount("aaabb", 4);
        if (834168507 != solution2.possibleStringCount("ggggggggaaaaallsssssaaaaaaaaaiiqqqqqqqqqqbbbbbbbvvfffffjjjjeeeeeefffmmiiiix", 34)) System.out.println("Invalid 4");
        if (2121993 != solution2.possibleStringCount("mmzzzzzbbbbbbbbbmmyyyyyyyyttttttzzzooogggggggggyyyyyyyyhhhttllllhhhhqcccchh", 65)) System.out.println("Invalid 5");
        if (986007796 != solution2.possibleStringCount("bbzoggrccukkwwzqdrrhhhkvbbvjwggvvccsdwwiicumoocklyynyyhhcwjssppmmaacceehhtoowkooccekkddppxppzzyyvvll",80)) System.out.println("Invalid 6");

        DpSolutionOptimised solution3 = findOriginalTypedStringII.new DpSolutionOptimised();
        if (174752921 != solution3.possibleStringCount("aajjjjjjjjdddddffffffwwwwwwwwwwhhhhhhhhhhyyyyyyyjmmmmmmmmmmmnnnnnnnnnnlllll", 32)) System.out.println("Invalid 7");

    }

    class DpSolutionOptimised {
        public int possibleStringCount(String str, int k) {

            char[] word = str.toCharArray();
            List<Integer> blocks = computeBlocks(word);

            return computePossibleKStrings(word.length, k, blocks);
        }

        // Computing δ = 𝚻 - φ
        int computePossibleKStrings(int L, int k, List<Integer> blocks) {
            int total = totalPossibleText(blocks);
            if (blocks.size() >= k) { // number of blocks guarantee k
                return total;
            }
            int invalid = computeAllInvalidText(k, blocks);
            return ((total - invalid) % MOD + MOD) % MOD;
        }

        // Computing 𝚻
        int totalPossibleText(List<Integer> blocks) {
            int result = 1;
            for (int b : blocks) {
                result = (int) (((long) result * b) % MOD);
            }
            return result;
        }

        // Computing φ
        int computeAllInvalidText(int k, List<Integer> blocks) {
            int[] dp = computeInvalidTextDp(k, blocks);
            int total = 0;
            for (int l = 1; l < k; l++) {
                total += dp[l];
                total %= MOD;
            }
            return total;
        }

        int[] computeInvalidTextDp(int k, List<Integer> blocks) {

            int blockSize = blocks.size();
            int[] dp = new int[k];

            dp[0] = 1;

            for (int b = blockSize - 1; b >= 0; b--) {
                int[] prefix = new int[k];
                prefix[0] = dp[0];
                for (int l = 1; l < k; l++) {
                    prefix[l] = (prefix[l - 1] + dp[l]) % MOD;
                }

                int[] dpNew = new int[k];
                for (int l = 1; l < k; l++) {
                    int maxC = Math.min(blocks.get(b), l);
                    int left = l - maxC;
                    int right = l - 1;

                    dpNew[l] = prefix[right];
                    if (left > 0) {
                        dpNew[l] = (dpNew[l] - prefix[left - 1] + MOD) % MOD;
                    }
                }
                dp = dpNew;
            }
            return dp;
        }
    }

    class DpSolutionUnoptimised {
        public int possibleStringCount(String str, int k) {

            char[] word = str.toCharArray();
            List<Integer> blocks = computeBlocks(word);

            return computePossibleKStrings(word.length, k, blocks);
        }

        // Computing δ = 𝚻 - φ
        int computePossibleKStrings(int L, int k, List<Integer> blocks) {
            int total = totalPossibleText(blocks);
            int invalid = computeAllInvalidText(k, blocks);
            return ((total - invalid) % MOD + MOD) % MOD;
        }

        // Computing 𝚻
        int totalPossibleText(List<Integer> blocks) {
            int result = 1;
            for (int b : blocks) {
                result = (int) (((long) result * b) % MOD);
            }
            return result;
        }

        // Computing φ
        int computeAllInvalidText(int k, List<Integer> blocks) {
            int[][] dp = computeInvalidTextDp(k, blocks);
            int total = 0;
            for (int l = 1; l < k; l++) {
                total += dp[l][0];
                total %= MOD;
            }
            return total;
        }

        int[][] computeInvalidTextDp(int k, List<Integer> blocks) {
            int blockSize = blocks.size();
            int[][] dp = new int[k][blockSize + 1];

            dp[0][blockSize] = 1;

            for (int l = 1; l < k; l++) {
                for (int b = blockSize - 1; b >= 0; b--) {
    
                    int maxC = Math.min(blocks.get(b), l);
                    for (int c = 1; c <= maxC; c++) {
                        dp[l][b] += dp[l - c][b + 1];
                        dp[l][b] %= MOD;
                    }
                }
            }
            return dp;
        }
    }

    class RecursiveSolution {
        public int possibleStringCount(String str, int k) {
            char[] word = str.toCharArray();
            List<Integer> blocks = computeBlocks(word);

            int totalPossibleText = totalPossibleText(blocks);
            int invalidText = computePossibleKStrings(k, blocks);
            return totalPossibleText - invalidText;
        }

        // Computing 𝚻
        int totalPossibleText(List<Integer> blocks) {
            int result = 1;
            for (int b : blocks) {
                result *= b;
            }
            return result;
        }

        // Computing φ
        int computeInvalidText(int l, int b, List<Integer> blocks) {
            if (l == 0 && b == blocks.size())
                return 1;
            if (l == 0 && b < blocks.size())
                return 0;
            if (l < 0)
                return 0;
            if (l > 0 && b == blocks.size())
                return 0;

            int total = 0;
            for (int c = 1; c <= blocks.get(b); c++) {
                total += computeInvalidText(l - c, b + 1, blocks);
                total %= MOD;
            }
            return total;
        }

        int computePossibleKStrings(int k, List<Integer> blocks) {
            int total = 0;
            for (int l = 1; l < k; l++) {
                total += computeInvalidText(l, 0, blocks);
                total %= MOD;
            }
            return total;
        }
    }

    List<Integer> computeBlocks(char[] word) {

        List<Integer> blocks = new ArrayList<>();

        int i = 0;
        while (i < word.length) {
            char ch = word[i];
            int j = i;
            while (j < word.length && word[j] == ch) {
                j++;
            }
            blocks.add(j - i);
            i = j;
        }

        return blocks;
    }
}

/*
    T, text or word on the screen
    L, length of word
    blocks[b], block of certain length in T where b ∈ [1, blocks.length]. for e.g. aaa bbb cc so on, continuos chars
    
    
    𝑓(l, b) number of valid texts for length, l considering first b blocks
    
                blocks[b]
    𝑓(l, b)  =     Σ         𝑓 (l - c, b + 1)    otherwise
                    c=1
            = 1 		                        if l = 0, b = blocks.length
            = 0 		                        if l = 0, b < blocks.length
    
    total T = total number of text that could be generated
    
        n
    𝚻 =  ∏  blocks[i]
        i=1
    total number of possible texts
    
        k-1  
    φ = ∑ f(l, b)    computing invalid string less than size k. In other words, exclusions
        l=1
    
    𝙍 = 𝚻 - φ
*/