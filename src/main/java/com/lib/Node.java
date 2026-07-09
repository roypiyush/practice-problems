package com.lib;

public class Node<T> {
    public T value;
    public Node<T> prev;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
        prev = null;
        next = null;
    }
}
