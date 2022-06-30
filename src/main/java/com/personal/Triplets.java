package com.personal;

public class Triplets<T1, T2, T3> {
    T1 t1;
    T2 t2;
    T3 t3;

    private Triplets(T1 t1, T2 t2, T3 t3) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
    }

    public static <T1, T2, T3> Triplets of(T1 t1, T2 t2, T3 t3) {
        Triplets triplets = new Triplets(t1, t2, t3);
        return triplets;
    }

    public String toString() {
        return String.format("%s, %s -> %s", t1.toString(), t2.toString(), t3.toString());
    }
}
