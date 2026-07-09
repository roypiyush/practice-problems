package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestStringWithoutRepetition {
    
    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int i = 0;
        int maxSize = 1;
    
        map.put(s.charAt(i), i);
            
        int j = i + 1;
        while (j < n) {
            char c = s.charAt(j);
            Integer pos = map.remove(c);
            if (pos != null) {
                
                for (int k = i; k <= pos; k++) {
                    map.remove(s.charAt(k));
                }
                
                maxSize = Math.max(maxSize, j - i);
                i = pos + 1;
            }

            map.put(c, j);
            j++;
        }
    
        
        return Math.max(maxSize, j - i);
    }

    public static void main(String[] args) {

        long st = System.currentTimeMillis();
        System.out.println(lengthOfLongestSubstring("abba"));
        System.out.printf("Time taken %d ms\n", (System.currentTimeMillis() - st));
    }
}
