package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class BitwiseOrSubarrays {

    public static void main(String[] args) {
       final BitwiseOrSubarrays subarrays = new BitwiseOrSubarrays();
       int[] arr = new int[] {1, 2, 4, 1};
       System.out.println(subarrays.subarrayBitwiseORs(arr));
    }

    public int subarrayBitwiseORs(int[] arr) {
        int zero = 0;
        Set<Integer> result = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zero = 1;
            }
            int or = 0;
            for (int j = i; j < arr.length; j++) {
                if (i != j && orCoveredBy(arr[i], arr[j])) {
                    break;
                }
                or |= arr[j];
                result.add(or);
            }
        }

        return result.size() + zero;
    }

    private boolean orCoveredBy(int i, int j) {
        return (i | j) == j;
    }
}

/*
 * take 1 window sized subarrays
 * take 2 window sized subarrays
 * take 3 so on 
 *
 * how many different combinations leading to bitwise or?
 * Count OR of those combinations to get the result
 *
 * 1 1 2 3
 *
 * [1] [1] [2] [3]
 * [1 1] [1 2] [2 3]
 * [1 1 2] [1 2 3]
 * [1 1 2 3]
 *
 * Total combinations = size = 4 = 4 * 5 / 2 = 10  
 * Combinations making the difference
 * [1] [2] [3]
 * Total combinations = 3
 * So you have to do 3 operations to make algorithm efficient
 * 
 */
