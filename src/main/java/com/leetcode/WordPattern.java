package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    public static void main(String[] args) {
        System.out.println(new WordPattern().wordPattern("abba", "dog cat cat fish"));
    }

    public boolean wordPattern(String pattern, String s) {

        String[] items = s.split(" ");

        if (items.length != pattern.length()) {
            return false;
        }

        int i = 0;
        Map<String, Character> map1 = new HashMap<>();
        Map<Character, String> map2 = new HashMap<>();
        for (String item : items) {
            Character c = pattern.charAt(i);

            String str = map2.get(c);
            Character c1 = map1.get(item);

            if (str == null && c1 == null) {
                map1.put(item, c);
                map2.put(c, item);
            } else if (str != null && c1 != null) {
                if (!(str.equalsIgnoreCase(item) && c1 == c)) {
                    return false;
                }
            } else {
                return false;
            }

            i++;
        }
        return true;

    }
}
