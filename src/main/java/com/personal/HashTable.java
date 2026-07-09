package com.personal;

import com.lib.Utils;

import java.util.Arrays;

public class HashTable {

    public static void main(String[] args) {
        int[] arr = new int[20];
        Utils.populateWithRandomValues(arr);
        Integer[] hashTable = new Integer[arr.length];
        for (int a : arr) {
            insert(hashTable, a);
        }

        for (int a : arr) {
            int pos = get(hashTable, a);
            if (hashTable[pos] != a) {
                System.out.println("Incorrect " + a);
            }
        }

        // FIXME

        System.out.println(Arrays.toString(hashTable));


    }

    static int hash(int key, int m) {
        int l = nextPowerOf2(m);
        int w = l + 1;
        float pi = 0.41f;
        int _2w = 1 << w;
        int a = Float.floatToIntBits(pi * _2w);
        return (key * a) % _2w >>> (w - l);
    }

    private static int nextPowerOf2(int m) {
        int shifts = 0;
        while (m > 0) {
            m = m >> 1;
            shifts++;
        }
        return shifts;
    }

    static void insert(Integer[] table, int key) {
        int pos = hash(key, table.length);
        while (table[pos] != null && table[pos] != key) {
            pos++;
            pos = pos % table.length;
        }

        if (table[pos] == null) {
            table[pos] = key;
        }
    }

    static int get(Integer[] table, int key) {
        int pos = hash(key, table.length);
        while (table[pos] != null && table[pos] != key) {
            pos++;
            pos = pos % table.length;
        }

        return pos;
    }


}
