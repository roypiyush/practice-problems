package com.leetcode;

import java.util.LinkedList;

public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);

        System.out.println(minStack);

        System.out.printf("Min item %d\n", minStack.getMin());
        System.out.printf("Popped %d\n", minStack.pop());

        System.out.printf("Min item %d\n", minStack.getMin());
        System.out.printf("Popped %d\n", minStack.pop());

        System.out.printf("Min item %d\n", minStack.getMin());
        System.out.printf("Popped %d\n", minStack.pop());

        System.out.printf("Min item %d\n", minStack.getMin());
        System.out.printf("Popped %d\n", minStack.pop());
        
        System.out.println();
    }

    private Integer getMin() {
        if (min.isEmpty()) {
            throw new RuntimeException("Empty stack");
        }

        return min.peek();
    }

    private void push(int value) {
        stack.push(value);

        if (min.isEmpty() || value <= min.peek()) {
            min.push(value);
        }
    }

    private Integer pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Cannot pop from empty stack");
        }

        Integer popped = stack.pop();
        if (popped == min.peek()) {
            min.pop();
        }
        return popped;
    }

    public MinStack() {
        this.min = new LinkedList<>();
        this.stack = new LinkedList<>();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    private LinkedList<Integer> min;
    private LinkedList<Integer> stack;
}