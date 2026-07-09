package com.lib;

import java.util.HashMap;
import java.util.Map;

public class AugmentedUniqueStore<T> {
    Map<T, Node<T>> map;

    Node<T> head;
    Node<T> tail;

    public AugmentedUniqueStore() {
        map = new HashMap<>();
    }

    public Node<T> get(T value) {
        return map.get(value);
    }

    public void add(Node<T> node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;

            tail = node;
        }
        map.put(node.value, node);
    }

    public void remove(Node<T> node) {

        map.remove(node.value);

        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev == null) {
            // this is head
            head = next;
            if (next != null) {
                next.prev = null;
            }
        } else if (next == null) {
            // this is tail
            tail = prev;
            if (prev != null) {
                prev.next = null;
            }
        } else {
            prev.next = next;
            next.prev = prev;
        }

        node.prev = null;
        node.next = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node<T> cur = head;
        while (cur != null) {
            sb.append(cur.value);
            cur = cur.next;
        }

        return sb.toString();
    }
}
