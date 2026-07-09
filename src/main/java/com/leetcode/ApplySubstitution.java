package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lib.Pair;

public class ApplySubstitution {

    public static void main(String[] args) {

        List<List<String>> replacements = new ArrayList<>();
        List<String> l1 = new ArrayList<>(); l1.add("T"); l1.add("%P%o");
        replacements.add(l1);

        List<String> l2 = new ArrayList<>(); l2.add("P"); l2.add("etiw%F%i");
        replacements.add(l2);

        List<String> l3 = new ArrayList<>(); l3.add("F"); l3.add("ydqs");
        replacements.add(l3);

        System.out.println(new ApplySubstitution().applySubstitutions(replacements, "%F%_%T%_%P%"));
    }

    public String applySubstitutions(List<List<String>> replacements, String text) {

        Map<String, String> map = prepareKeyValPairs(replacements);
        return replaceText(map, text.toCharArray());
    }

    String replaceText(Map<String, String> map, char[] textCharArray) {

        StringBuilder sb = new StringBuilder();

        int size = textCharArray.length;
        int start = 0;
        
        while (start < size) {

            char ch = textCharArray[start];

            if (ch == '%') {
                Pair<String, Integer> pair = readKey(textCharArray, start + 1);
                sb.append(map.get(pair.getKey()));
                start = pair.getValue() + 1;
            } else {
                sb.append(ch);
                start++;
            }
        }

        return sb.toString();
    }

    Map<String, String> prepareKeyValPairs(List<List<String>> replacements) {

        Map<String, String> map = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();

        for (List<String> keyVal : replacements) {
            String key = keyVal.get(0);
            String value = keyVal.get(1);
            map.put(key, value);

            graph.computeIfAbsent(key, k -> new ArrayList<>());
        
            List<String> dependsOn = parseKeyIfPresent(value);
            dependsOn.forEach(d -> graph.computeIfAbsent(d, k -> new ArrayList<>()).add(key));
        }

        LinkedList<String> nodesToProcess = topologicalSort(graph);
        while (!nodesToProcess.isEmpty()) {
            String node = nodesToProcess.pop();
            String t = map.get(node);
            if (t != null) {
                map.put(node, replaceText(map, t.toCharArray()));
            }
            
        }

        return map;
    }

    List<String> parseKeyIfPresent(String text) {
        int i = 0;
        char[] textCharArray = text.toCharArray();
        List<String> list = new ArrayList<>();

        while (i < textCharArray.length) {

            if (textCharArray[i] == '%') {
                Pair<String, Integer> index = readKey(textCharArray, i + 1);
                list.add(index.getKey());
                i = index.getValue();
            }
            i++;
        }

        return list;
    }

    /**
     * Parses key and return index where 2nd % is present
     * 
     * @param textCharArray
     * @param index of 1st %
     * @return
     */
    Pair<String, Integer> readKey(char[] textCharArray, int index) {
        StringBuilder sb = new StringBuilder();
        while (textCharArray[index] != '%') {
            sb.append(textCharArray[index]);
            index++;
        }
        return new Pair<String, Integer>(sb.toString(), index);
    }

    LinkedList<String> topologicalSort(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        LinkedList<String> sortedNodes = new LinkedList<>();
        Set<String> keys = graph.keySet();
        for (String key : keys) {
            if (!visited.contains(key)) {
                dfs(graph, visited, key, sortedNodes);
            }
        }

        return sortedNodes;
    }

    void dfs(Map<String, List<String>> graph, Set<String> visited, String node, LinkedList<String> sortedNodes) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        List<String> neighbours = graph.get(node);
        if (neighbours != null) {
            for (String neighbour : graph.get(node)) {
                dfs(graph, visited, neighbour, sortedNodes);
            }
        }

        sortedNodes.push(node);
    }

}
