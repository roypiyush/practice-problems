package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RectangleArea {
    public static void main(String[] args) {
        int ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4;
        int bx1 = 0, by1 = -1, bx2 = 9, by2 = 2;
        new RectangleArea().computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
    }

    
    public int rectangleArea(int[][] rectangles) {

        List<int[]> newRectList = new ArrayList<>();
        int j = 1; // starting with 2nd rectangle
        
        while (j < rectangles.length - 1) {
            
            for (int i = 0; i < newRectList.size(); i++) {
                
            }

            int i = 0; // error

            while (i >= 0) {
                // compare i, j
                int[] ri = rectangles[i];
                int[] rj = rectangles[j];
                int[] or = overlapRectangle(ri[0], ri[1], ri[2], ri[3], rj[0], rj[1], rj[2], rj[3]);
                if (area(or[0], or[1], or[2], or[3]) > 0) {
                    newRectList.add(or);
                } else {

                }
                i--;
            }
            j++;
        }
        return 0; // error
    }

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int[] overlapRectangle = overlapRectangle(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        int lbx = overlapRectangle[0], lby = overlapRectangle[1];
        int rtx = overlapRectangle[2], rty = overlapRectangle[3];

        return area(ax1, ay1, ax2, ay2) + area(bx1, by1, bx2, by2) - area(lbx, lby, rtx, rty);
    }

    int[] overlapRectangle(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int lbx = Math.max(ax1, bx1), lby = Math.max(ay1, by1);
        int rtx = Math.min(ax2, bx2), rty = Math.min(ay2, by2);
        
        return new int[] { lbx, lby, rtx, rty };
    }

    public int area(int x1, int y1, int x2, int y2) {
        if (x1 >= x2 || y1 >= y2) {
            return 0;
        }
        return (x2 - x1) * (y2 - y1);
    }
}
