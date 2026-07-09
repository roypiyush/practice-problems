package com.personal;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("(%s %s) -> %s", key, value, next);
    }
}

public class LRUCache {

    Map<Integer, Node> keyVal = new HashMap<>();

    Node front;
    Node last;

    int capacity;
    int curCapacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.curCapacity = 0;
    }

    public int get(int key) {
        Node node = _get(key);
        return node == null ? -1 : node.value;
    }

    Node _get(int key) {
        Node node = keyVal.get(key);
        removeFromList(node);
        insertToFront(node);
        return node;
    }

    public void put(int key, int value) {

        Node node = _get(key);
        if (node != null) {
            node.value = value;
            return;
        }

        node = new Node(key, value);
        insertToFront(node);
        if (curCapacity > capacity) {
            removeFromList(last);
        }
    }

    void removeFromList(Node node) {
        if (node == null) {
            return;
        }

        Node prev = node.prev;
        Node next = node.next;

        node.prev = null;
        node.next = null;
        if (prev != null) {
            prev.next = null;
        }
        if (next != null) {
            next.prev = null;
        }

        if (prev == null && next == null) {
            front = null;
            last = null;
        } else if (prev == null) {
            // this is front
            next.prev = null;
            front = next;
        } else if (next == null) {
            // this is last
            prev.next = null;
            last = prev;
        } else {

            // somewhere within the list
            prev.next = next;
            next.prev = prev;
        }

        keyVal.remove(node.key);
        curCapacity--;
    }

    void insertToFront(Node node) {
        if (node == null) {
            return;
        }

        if (front != null) {
            front.prev = node;
        }

        node.next = front;
        front = node;

        if (last == null) {
            last = node;
        }

        keyVal.put(node.key, node);
        curCapacity++;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(10);
        lruCache.put(10, 13);
        lruCache.put(3, 17);
        lruCache.put(6, 11);
        lruCache.put(10, 5);
        lruCache.put(9, 10);
        System.out.println(lruCache.get(13));
        lruCache.put(2, 19);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        lruCache.put(5, 25);
        System.out.println(lruCache.get(8));
        lruCache.put(9, 22);
        lruCache.put(5, 5);
        lruCache.put(1, 30);
        System.out.println(lruCache.get(11));
        lruCache.put(9, 12);
        System.out.println(lruCache.get(7));
        System.out.println(lruCache.get(5));
        System.out.println(lruCache.get(8));
        System.out.println(lruCache.get(9));
        lruCache.put(4, 30);
        lruCache.put(9, 3);
        System.out.println(lruCache.get(9));
        System.out.println(lruCache.get(10));
        System.out.println(lruCache.get(10));
        lruCache.put(6, 14);
        lruCache.put(3, 1);
        System.out.println(lruCache.get(3));
        lruCache.put(10, 11);
        System.out.println(lruCache.get(8));
        lruCache.put(2, 14);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(5));
        System.out.println(lruCache.get(4));
        lruCache.put(11, 4);
        lruCache.put(12, 24);
        lruCache.put(5, 18);
        System.out.println(lruCache.get(13));
        lruCache.put(7, 23);
        System.out.println(lruCache.get(8));
        System.out.println(lruCache.get(12));
        lruCache.put(3, 27);
        lruCache.put(2, 12);
        System.out.println(lruCache.get(5));
        lruCache.put(2, 9);
        lruCache.put(13, 4);
        lruCache.put(8, 18);
        lruCache.put(1, 7);
        System.out.println(lruCache.get(6));
        lruCache.put(9, 29);
        lruCache.put(8, 21);
        System.out.println(lruCache.get(5));
        lruCache.put(6, 30);
        lruCache.put(1, 12);
        System.out.println(lruCache.get(10));
        lruCache.put(4, 15);
        lruCache.put(7, 22);
        lruCache.put(11, 26);
        lruCache.put(8, 17);
        lruCache.put(9, 29);
        System.out.println(lruCache.get(5));
        lruCache.put(3, 4);
        lruCache.put(11, 30);
        System.out.println(lruCache.get(12));
        lruCache.put(4, 29);
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(9));
        System.out.println(lruCache.get(6));
        lruCache.put(3, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(10));
        lruCache.put(3, 29);
        lruCache.put(10, 28);
        lruCache.put(1, 20);
        lruCache.put(11, 13);
        System.out.println(lruCache.get(3));
        lruCache.put(3, 12);
        lruCache.put(3, 8);
        lruCache.put(10, 9);
        lruCache.put(3, 26);
        System.out.println(lruCache.get(8));
        System.out.println(lruCache.get(7));
        System.out.println(lruCache.get(5));
        lruCache.put(13, 17);
        lruCache.put(2, 27);
        lruCache.put(11, 15);
        System.out.println(lruCache.get(12));
        lruCache.put(9, 19);
        lruCache.put(2, 15);
        lruCache.put(3, 16);
        System.out.println(lruCache.get(1));
        lruCache.put(12, 17);
        lruCache.put(9, 1);
        lruCache.put(6, 19);
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(5));
        System.out.println(lruCache.get(5));
        lruCache.put(8, 1);
        lruCache.put(11, 7);
        lruCache.put(5, 2);
        lruCache.put(9, 28);
        System.out.println(lruCache.get(1));
        lruCache.put(2, 2);
        lruCache.put(7, 4);
        lruCache.put(4, 22);
        lruCache.put(7, 24);
        lruCache.put(9, 26);
        lruCache.put(13, 28);
        lruCache.put(11, 26);
    }
}
