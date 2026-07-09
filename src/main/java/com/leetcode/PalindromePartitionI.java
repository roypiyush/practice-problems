package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitionI {

    public static void main(String[] args) {

        PalindromePartitionI solution = new PalindromePartitionI();
        System.out.println(solution.partition("aab"));

    }

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        String[][] palindromeStrings = new String[n][n];

        buildPalindromeTable(s, isPalindrome, palindromeStrings);

        List<List<String>> res = new ArrayList<>();
        computeWithRecursion(res, new ArrayList<>(), isPalindrome, palindromeStrings, 0, n - 1);
        return res;
    }

    void computeWithRecursion(List<List<String>> res, List<String> cur,
                   boolean[][] isPalindrome, String[][] palindromeStrings,
                   int start, int end) {
        if (start > end) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int k = start; k <= end; k++) {
            if (isPalindrome[start][k]) {
                cur.add(palindromeStrings[start][k]);
                computeWithRecursion(res, cur, isPalindrome, palindromeStrings, k + 1, end);
                cur.removeLast();
            }
        }
    }

    void buildPalindromeTable(String s, boolean[][] isPalindrome, String[][] palindromeStrings) {
        int n = s.length();
        char[] arr = s.toCharArray();

        for (int l = 1; l <= n; l++) { // length of substring
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;

                if (i == j) { // single character
                    isPalindrome[i][j] = true;
                    palindromeStrings[i][j] = s.substring(i, j + 1);
                } else if (i + 1 == j) { // two characters
                    if (arr[i] == arr[j]) {
                        isPalindrome[i][j] = true;
                        palindromeStrings[i][j] = s.substring(i, j + 1);
                    }
                } else { // length >= 3
                    if (arr[i] == arr[j] && isPalindrome[i + 1][j - 1]) {
                        isPalindrome[i][j] = true;
                        palindromeStrings[i][j] = s.substring(i, j + 1);
                    }
                }
            }
        }
    }
}
