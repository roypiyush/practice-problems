package com.leetcode;

import java.util.Arrays;
import java.util.List;

public class WordBreak {

    boolean match(String word, char[] s, int index) {
        char[] w = word.toCharArray();

        if (index + w.length > s.length) {
            return false;
        }

        char[] s1 = Arrays.copyOfRange(s, index, index + w.length);

        return Arrays.compare(w, s1) == 0;
    }

    boolean compute(final Boolean[] R, char[] s, List<String> wordDict, int startIndex) {

        // base case
        if (startIndex == s.length) {
            return R[startIndex];
        }

        if (R[startIndex] != null) {
            return R[startIndex];
        }

        boolean result = false;
        for (int j = 0; j < wordDict.size(); j++) {
            String word = wordDict.get(j);
            final boolean validChoice = match(word, s, startIndex);
            if (validChoice) {
                final boolean r1 = compute(R, s, wordDict, startIndex + word.length());
                result = result || r1;
            }
        }

        R[startIndex] = result;
        return result;
    }

    public boolean wordBreak(String string, List<String> wordDict) {

        char[] s = string.toCharArray();
        Boolean[] R = new Boolean[s.length + 1];
        R[s.length] = true;
        return compute(R, s, wordDict, 0);
    }

    public static void main(String[] args) {
        String string = "helloworld";
        final String[] dictionary = {"hello", "funny", "world"};

        List<String> wordDict = Arrays.asList(dictionary);
        System.out.println(new WordBreak().wordBreak(string, wordDict));
    }
}
