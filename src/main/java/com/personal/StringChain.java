package com.personal;

import java.nio.channels.Pipe;
import java.util.PriorityQueue;

public class StringChain {
    public static void main(String[] args) {
        System.out.println(new StringChain().integerBreak(10));
    }

    public int integerBreak(int n) {

        int prod = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        if (n % 2 == 0) {
            queue.add(n / 2);
            queue.add(n / 2);
        } else {
            queue.add(n / 2);
            queue.add((n / 2) + 1);
        }

        int cur = 1;
        while (cur > prod) {

            int poll = queue.poll();


            System.out.println(prod);
        }
        return prod;
    }
}
