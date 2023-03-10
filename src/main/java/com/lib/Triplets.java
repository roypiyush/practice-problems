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

    public static <L, R, V> Triplets <L, R, V> of(L left, R right, V value) {
        return new Triplets<L, R, V>(left, right, value);
    }

    public String toString() {
        return String.format("%s, %s -> %s", left.toString(), right.toString(), value.toString());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Triplets<?, ?, ?> triplets = (Triplets<?, ?, ?>) o;

        if (left != null ? !left.equals(triplets.left) : triplets.left != null) return false;
        if (right != null ? !right.equals(triplets.right) : triplets.right != null) return false;
        return value != null ? value.equals(triplets.value) : triplets.value == null;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
