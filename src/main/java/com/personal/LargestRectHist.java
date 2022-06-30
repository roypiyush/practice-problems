package com.personal;

import java.util.Deque;
import java.util.LinkedList;

public class LargestRectHist {

    private static int bruteForce(final int[] hists) {
        int result = 0;
        for (int i = 1; i < hists.length - 1; i++) {
            int left = i;
            int right = i;
            while (left - 1 >= 0 && hists[left - 1] >= hists[i]) {
                left--;
            }
            while (right + 1 < hists.length && hists[right + 1] >= hists[i]) {
                right++;
            }
            result = Math.max(result, (right - left + 1) * hists[i]);
        }
        return result;
    }

    private static int linearTime(final int[] hists) {

        int curPtr = 0;
        int maxArea = 0;
        Deque<Integer> stack = new LinkedList<>();

        while (curPtr < hists.length) {
            if (stack.size() == 0 || hists[curPtr] > hists[stack.peek()]) {
                stack.push(curPtr);
                curPtr++;
            } else {
                final Integer h = stack.pop();
                int area = (stack.peek() == null ? curPtr : curPtr - stack.peek() - 1) * hists[h];
                maxArea = Math.max(maxArea, area);
            }
        }
        while (stack.peek() != null) {
            final Integer h = stack.pop();
            int area = (stack.peek() == null ? curPtr : curPtr - stack.peek() - 1) * hists[h];
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] hists;
        hists = new int[]{6, 2, 5, 4, 5, 1, 6};
        System.out.println(bruteForce(hists));
        System.out.println(linearTime(hists));

        hists = new int[]{2, 3, 2, 4, 0, 5, 3, 4};
        System.out.println(bruteForce(hists));
        System.out.println(linearTime(hists));
    }
}
