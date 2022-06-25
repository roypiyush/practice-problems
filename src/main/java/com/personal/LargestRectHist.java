package com.personal;

public class LargestRectHist {
    public static int largestRectangleN2(final int[] hists) {
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

    public static void main(String[] args) {
        final int[] hists = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(largestRectangleN2(hists));
    }
}
