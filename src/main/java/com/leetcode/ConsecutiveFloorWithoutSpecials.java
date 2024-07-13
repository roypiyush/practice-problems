package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class ConsecutiveFloorWithoutSpecials {
    public static void main(String[] args) {
        final ConsecutiveFloorWithoutSpecials solution = new ConsecutiveFloorWithoutSpecials();
        System.out.println(solution.maxConsecutive(10, 30, new int[]{19, 10, 15}));
    }

    public int maxConsecutive(int bottom, int top, int[] special) {

        Arrays.sort(special);

        LinkedList<int[]> list = new LinkedList<>();
        list.offer(new int[]{bottom, top});

        int max = 0;
        for (int i = 0; i < special.length; i++) {

            int s = special[i];
            int[] last = list.pollLast();

            if (last == null) {
                break;
            }

            int x = last[0];
            int y = last[1];

            if (x <= s && s <= y) {

                int t = y;
                if (x <= s - 1) {
                    y = s - 1;
                    max = Math.max(max, y - x + 1);
                    list.offer(new int[]{x, y});
                }

                x = s + 1;
                y = t;

                if (x <= y) {
                    list.offer(new int[]{x, y});
                }
            } else {
                list.offer(new int[]{x, y});
                break;
            }

        }

        int[] last = list.peekLast();
        max = Math.max(max, last[1] - last[0] + 1);

        return max;
    }
}
