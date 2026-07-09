package com.lib;

public class Pair<L, R> {
    L left;
    R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public void setRight(R right) {
        this.right = right;
    }

    public L left() {
        return left;
    }

    public R right() {
        return right;
    }

    public L getKey() {
        return left;
    }

    public R getValue() {
        return right;
    }



    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    public String toString() {
        return String.format("%s -> %s", left.toString(), right.toString());
    }
}
