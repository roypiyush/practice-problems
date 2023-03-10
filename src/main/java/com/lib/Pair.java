package com.lib;

public class Pair<L, R> {
    L left;
    R right;

    private Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<L, R>(left, right);
    }

    public String toString() {
        return String.format("%s -> %s", left.toString(), right.toString());
    }
}
