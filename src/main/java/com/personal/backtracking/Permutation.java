package com.personal.backtracking;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutation {

    public static void main(String[] args) {

        Permutation permutation = new Permutation();
        String string = "1234";
        List<Character> chars = new LinkedList<>();
        for (char c : string.toCharArray()) {
            chars.add(c);
        }

        long start = System.currentTimeMillis();
        List<List<Character>> result = new LinkedList<>();
        Set<Character> choices = new LinkedHashSet<>();

        permutation.permute(result, chars, choices);
        System.out.println("Time elapsed " + (System.currentTimeMillis() - start) + " ms");
    }

    void permute(List<List<Character>> result, List<Character> chars, Set<Character> choices) {
        Set<Character> remaining = new HashSet<>(chars);
        remaining.removeAll(choices);

        if (remaining.isEmpty()) {
            System.out.println(choices);
            result.add(new LinkedList<>(choices));
            return;
        }

        for (char c : remaining) {
            choices.add(c);
            permute(result, chars, choices);
            choices.remove(c);
        }
    }

}
