package com.leetcode;

import java.util.LinkedList;

public class MaximumScoreRemoveSubstring {

    public static void main(String[] args) {
        MaximumScoreRemoveSubstring solution = new MaximumScoreRemoveSubstring();

        System.out.println(solution.maximumGain("cdbcbbaaabab", 4, 5));
        System.out.println(solution.maximumGain("cdbcbbaaabab", 5, 4));
        System.out.println(solution.maximumGain("cdbcbbaaabab", 4, 4));
    }

    public int maximumGain(String s, int x, int y) {

        int result = 0;
        LinkedList<Integer> positions = new LinkedList<>();

        char[] str = s.toCharArray();
        int size = str.length;

        char higher = higherChar(x, y);
        char lower = higher == 'a' ? 'b' : 'a';

        for (int i = 0; i < size; i++) {

            char currentChar = str[i];

            if (currentChar == lower || currentChar == higher) {
                // if current lower than process now
                // this is greedily taking max values

                if (!positions.isEmpty()
                        && str[positions.peek()] == higher
                        && currentChar == lower) {
                    // meaning higher value either x or y is processed
                    positions.pop();
                    result += x > y ? x : y;
                } else {
                    // add current to stack
                    positions.push(i);
                }

            } else {
                result += processTillNow(str, positions, x, y);
            }
        }

        result += processTillNow(str, positions, x, y);

        return result;
    }

    private char higherChar(int x, int y) {
       return x >= y ? 'a' : 'b';
    }

    private int processTillNow(char[] s, LinkedList<Integer> position, int x, int y) {
        int result = 0;
        LinkedList<Integer> peek = new LinkedList<>();

        while (!position.isEmpty()) {
            int cur = position.pop();
            if (!peek.isEmpty() && match(s[cur], s[peek.peek()])) {
                result += compute(x, y, s[cur], s[peek.pop()]);
            } else {
                peek.push(cur);
            }
        }

        return result;
    }

    private boolean match(char first, char second) {
        return (first == 'a' && second == 'b') || (first == 'b' && second == 'a') ;
    }

    private int compute(int x, int y, char first, char second) {
        if (first == 'a' && second == 'b') {
            return x;
        } else if (first == 'b' && second == 'a') {
            return y;
        } else {
            return 0;
        }
    }

}

/*
 *
 * ax + by = result
 * need to find a and b
 * where "a" represents "ab", "b" represents "ba"
 * new formations (ab or ba) are found once, ab or ba is removed like bbaa ->
 * remove ba then ba will be found
 *
 * possible count of values for ab = [0, n]
 * possible count of values for ba = [0, m]
 *
 * cdbc bbaaabab
 * ab = 2
 * ba = 2
 *
 *
 * there are two formations if we take two paths but one leads to complete
 * maximal value
 * can we do this graphically?
 *
 *
 * how to create this path?
 * Choose the pattern which has maximum value, then choose other pattern from
 * leftover.
 * what if I choose lower pattern which results in formation of higher valued
 * pattern?
 *
 * ab ba = path1 can be created
 * ba ba = path2 can be created
 * ab > ba
 * baba x = 5 y = 4
 * 5 + 4 = 9 correct
 * 4 + 4 = 8 incorrect
 *
 * ab < ba
 * baab x=4 y=5 => 9
 * abba x=5 y=4 => 9
 * abab x=4 y=5 => 9
 * baba x=4 y=5 => 10
 *
 * it confirms that greedily choosing higher value gives overall maximum gain
 *
 * how to choose efficiently?
 *
 * str = cdbcbbaaabab
 * str[2] = b be ignored as str[3] cuts its usage
 * 0 1 2 3 4 5 6 7 8 9 10 11
 * c d b c b b a a a b a b
 * P P C P C C C C C C C C
 * P(st) now process lower valued items
 *
 * P(st, i) = process all prior to i index
 * A(i) = add ith element to stack
 * C(i) = check if computation poossible else A(st, i)
 * G(st, i) = greater pair should be computed, rest added to stack
 *
 * for i in str.length
 * if not a or b
 * processEverythingTillNow()
 * else
 * check which sequence is higher, if current one is then process and add to
 * result
 * if not processed then add to stack
 *
 * if anything remaining in stack
 * then processEverythingTillNow()
 *
 */
