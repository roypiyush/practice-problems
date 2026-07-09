package com.leetcode;

public class LengthOfLongestSubString {

    public static void main(String[] args) {
        LengthOfLongestSubString longestSubString = new LengthOfLongestSubString();
        System.out.println(longestSubString.lengthOfLongestSubstring("dvdf"));        
    }

    public int lengthOfLongestSubstring(String s) {
        
        char[] arr = s.toCharArray();
        if (arr.length == 0) {
            return 0;
        }
        
        int[] map = new int[256];
        int i = 0; int j = 0;
        int maxLen = 1;
        
        while (j < arr.length) {
            
            int ch = (int) arr[j];
            map[ch]++;
            
            while (map[ch] > 1 && i < j) {
                maxLen = Math.max(maxLen, j - i);
                int ch2 = (int) arr[i];
                map[ch2]--;
                i++;
            }
            j++;
        }
        maxLen = Math.max(maxLen, j - i);
        
        return maxLen;
        
    }
}