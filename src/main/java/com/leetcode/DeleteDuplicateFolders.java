package com.leetcode;

import com.lib.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DeleteDuplicateFolders {

    public static void main(String[] args) {

        DeleteDuplicateFolders sol = new DeleteDuplicateFolders();
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.stream((new String[]{"a"})).toList());
        paths.add(Arrays.stream((new String[]{"c"})).toList());
        paths.add(Arrays.stream((new String[]{"d"})).toList());
        paths.add(Arrays.stream((new String[]{"a", "b"})).toList());
        paths.add(Arrays.stream((new String[]{"c", "b"})).toList());
        paths.add(Arrays.stream((new String[]{"d", "a"})).toList());

        List<List<String>> result = sol.deleteDuplicateFolder(paths);
        for (List<String> path : result) {
            System.out.println(path);
        }
    }
    
    List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {

        // build tree 
        Tree tree = buildTree(paths);
        List<Pair<Tree, Integer>> structure = new ArrayList<>();
        serialise(structure, tree);
        markNodesAndDelete(structure);

        // write as paths to result
        List<List<String>> result = new ArrayList<>();
        toPath(result, new ArrayList<>(), tree);
        return result;
    }

    void toPath(List<List<String>> result, List<String> path, Tree tree) {
        if (tree == null) {
            return;
        }

        if (!tree.node.equals("/")) {
            path.add(tree.node);
            result.add(new ArrayList<>(path));
        }

        for (Map.Entry<String, Tree> e : tree.children.entrySet()) {
            toPath(result, path, e.getValue());
        }
        if (!path.isEmpty()) {
            path.removeLast();
        }
    }

    Tree buildTree(List<List<String>> paths) {

        Tree tree = new Tree("/");
        for (List<String> path : paths) {
            tree.insert(path);
        }
        return tree;
    }

    void markNodesAndDelete(List<Pair<Tree, Integer>> structure) {
        Map<Integer, List<Tree>> map = new HashMap<>();
        // nodeName -> serializedTree
        for (Pair<Tree, Integer> s : structure) {
            map.computeIfAbsent(s.getValue(), k -> new ArrayList<>()).add(s.getKey());
        }

        for (Map.Entry<Integer, List<Tree>> e : map.entrySet()) {
            if (e.getValue().size() > 1) {
                deleteDuplicates(e.getValue());
            }
        }
    }

    String serialise(List<Pair<Tree, Integer>> pairs, Tree tree) {

        StringBuilder path = new StringBuilder();
        for (Map.Entry<String, Tree> child : tree.children.entrySet()) {
            path.append(String.format("(%s)", serialise(pairs, child.getValue())));
        }

        String childSerialized = path.toString();
        if (!childSerialized.isEmpty()) {
            pairs.add(new Pair<>(tree, childSerialized.hashCode()));
        }

        return tree.node + path;
    }

    void deleteDuplicates(List<Tree> toDelete) {
        for (Tree t : toDelete) {
            Tree parent = t.parent;
            parent.children.remove(t.node);
        }
    }

    static class Tree {
        String node;
        Tree parent;

        /* map is required because of reusing node 
         * if it exists ideally list, but we will
         * have to iterate to find if node existed
         */
        Map<String, Tree> children; 

        public Tree(String node) {
            this.node = node;
            children = new TreeMap<>();
        }
        
        public void insert(List<String> path) {
            Tree curNode = this;

            for (String folder : path) {
                Tree newNode = curNode.children.get(folder);
                if (newNode == null) {
                    newNode = new Tree(folder);
                    curNode.children.put(folder, newNode);
                    newNode.parent = curNode;
                }
                curNode = newNode;
            }
        }

        @Override
        public String toString() {
            return node;
        }
    }

    
}


/*
 * [y x b a] [y w] [x b a] [b a] [a] [b c] [c] [w]
 * if leaf folder has 2 or more parents then those leafs and their respective parents are 
 * marked for deletion
 *
 * while deleting remove all paths ending with marked child and parent
 * 
 * how to find the leafs?
 * if a node doesn't have child then it is a leaf.
 */
