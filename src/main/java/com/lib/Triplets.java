package com.lib;

public class Triplets<L, R, V> {
    L left;
    R right;
    V value;

    private Triplets(L left, R right, V value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public static <L, R, V> Triplets of(L left, R right, V value) {
        return new Triplets<L, R, V>(left, right, value);
    }

    public String toString() {
        return String.format("%s, %s -> %s", left.toString(), right.toString(), value.toString());
    }
}
