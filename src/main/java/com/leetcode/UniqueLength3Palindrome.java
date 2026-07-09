package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueLength3Palindrome {
    public static void main(String[] args) {
        System.out.println(new UniqueLength3Palindrome().countPalindromicSubsequence("aywvhbwycmbttdmogwlfosfizqlndfipffbqfxwbgrfdyomuuecllmsrzckiwgelkhgylwobz"));  
    }

    public int countPalindromicSubsequence(String str) {

        int[][] store = new int[26][3];
        for (int[] st : store) {
            Arrays.fill(st, -1);
        }

        char[] s = str.toCharArray();
        
        for (int i = 0; i < s.length; i++) {

            int[] occ = store[s[i] - 'a'];
           
            if (occ[0] == -1) {
		occ[0] = i;
            }
	    occ[1] = i;
        }

        int result = 0;
        for (int[] st : store) {
            int start = st[0];
            int end = st[2];

            if (start == -1) {
                continue;
            }

	    Set<Integer> chars = new HashSet<>();
	    for (int j = start + 1; j < end; j++) {
		int ch = s[j] - 'a';
		chars.add(ch);
	    }
            
            result += chars.size();
        }

        return result;
        
    }

    /*
        a a b c a
        0 1 2 3 4

        a = 0, 1, 4 | 0, 1, 1 -> then no self char exist
        b = 2, 2, 2
        c = 3, 3, 3

        1 1 2 3 3 
        0 1 2 3 4

        diff(1, 0) = 

        ***** first and last occurrence matter

        init = 26 * [3] matrix with each value -1 to denote non existence

        for a -> [0, 0, 0] -> this is the 1st occurrence itself
                 [0, 1, 1] -> this is 2nd occurrence, candidate to calculate and denotes there is no other a 
                 [0, 1, 4] -> then diff between 4 - 0 and consider a therefore 4 - 0 + 1



    */
}
