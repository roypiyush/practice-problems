package com.personal;

import java.util.Deque;
import java.util.LinkedList;

interface Compare {
    boolean compare(int a, int b);
}

public class MonotonicQueue {

    Deque<Integer> deque;
    Compare cmp;

    public MonotonicQueue(Compare cmp) {
        this.deque = new LinkedList<>();
        this.cmp = cmp;
    }

    public void remove(int item) {
        if (!deque.isEmpty() && deque.peek() == item) {
            deque.poll();
        }
    }

    public void add(int item) {
        while (!deque.isEmpty() && cmp.compare(deque.peekLast(), item)) {
            deque.pollLast();
        }
        deque.offerLast(item);
    }

    public int value() {
        return deque.isEmpty() ? 0 : deque.peek();
    }

    @Override
    public String toString() {
        return deque.toString();
    }

    public static void main(String[] args) {
        MonotonicQueue minQ = new MonotonicQueue(((a, b) -> a > b));
        MonotonicQueue maxQ = new MonotonicQueue(((a, b) -> a < b));

        int[] arr = {8, 2, 1, -1, 5};

        for (int a : arr) {
            minQ.add(a);
            maxQ.add(a);
        }

        System.out.println(minQ);
        System.out.println(maxQ);
    }
}
