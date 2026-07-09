package com.leetcode;

import java.util.Arrays;

public class CountVowels {
    public static void main(String[] args) {
      String[] words = {"aba","bcb","ece","aa","e"};
      int[][] queries = {{0, 2}, {1, 4}, {1, 1}};
      System.out.println(Arrays.toString(new CountVowels().vowelStrings(words, queries)));
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
    
        int[] runningSum = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            boolean isMatch = isStartEndWithVowel(words[i]);
            runningSum[i] = isMatch ? 1 : 0;
            runningSum[i] += i - 1 < 0 ? 0 : runningSum[i - 1]; 
        }
    

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
             int s = queries[i][0] - 1;
             int e = queries[i][1];

             int v1 = s < 0 ? 0 : runningSum[s];
             int v2 = runningSum[e];
             result[i] = v2 - v1;
          }
          return result;
      }

      boolean isStartEndWithVowel(String word) {
          int size = word.length();
          return size > 0 && isVowel(word.charAt(0)) && isVowel(word.charAt(size - 1));
      }

      boolean isVowel(char c) {
          return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
      }
}
