package com.personal.string;

import java.util.Arrays;

public class KMPMatcher {

    public static void main(String[] args) {

        final KMPMatcher kmpMatcher = new KMPMatcher();
        kmpMatcher.matchText("bababababcababcabab".toCharArray(), "ababcabab".toCharArray());
        kmpMatcher.matchText("ABCABCDABABCDABCDABDE".toCharArray(), "ABCDABD".toCharArray());
        kmpMatcher.matchText("dd".toCharArray(), "abc".toCharArray());
    }

    private void matchText(final char[] text, final char[] pattern) {

        System.out.printf("\n%-10s %s\n", "Text", Arrays.toString(text));   
     
        final int[] prefixArray = computePrefixFunction(pattern, pattern.length);
        // initially, this field acts as length and index as well.
        // zero means to match the ith index
        // once matched it will +1 to match next char
        int matchedLength = 0;

        for (int cur = 0; cur < text.length; cur++) {

            while (matchedLength > 0 && text[cur] != pattern[matchedLength]) {
                // reset to previous match
                matchedLength = prefixArray[matchedLength - 1];
            }

            if (text[cur] == pattern[matchedLength]) {
                matchedLength++;
            }

            if (matchedLength == pattern.length) {
                System.out.println(String.format("Pattern matched at %d", (cur - matchedLength + 1)));

                // decrease by one and see next match occurs
                matchedLength = prefixArray[matchedLength - 1];
            }
        }
    }

    private int[] computePrefixFunction(final char[] pat, final int m) {

        /*
         * Computes PREFIX which is also a SUFFIX. Helps in skipping matches if repeated sub-pattern is observed.
         */
        final int[] prefixArray = new int[m];
        int matchedLength = 0; // length of matching prefix

        for (int cur = 1; cur < pat.length; cur++) {

            while (matchedLength > 0 && pat[cur] != pat[matchedLength]) {
                // reset to previous match
                matchedLength = prefixArray[matchedLength];
            }

            if (pat[matchedLength] == pat[cur]) {
                matchedLength++;
            }

            prefixArray[cur] = matchedLength;
        }

        System.out.printf("%-10s %2$s\n", "Pattern", Arrays.toString(pat));
        System.out.printf("%-10s %2$s\n", "Array", Arrays.toString(prefixArray));
        return prefixArray;
    }
}
