package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class CanonicalPath {
    public String simplifyPath1(String path) {
        LinkedList<String> stack = new LinkedList<>();
        String[] pathArr = path.split("/");
        for (String p : pathArr) {
            if (p.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (p.equals(".") || p.equals("")) {
                // do nothing
            } else {
                stack.push(p);
            }
        }

        StringBuilder s = new StringBuilder();
        
        while (!stack.isEmpty()) {
            s.append("/");
            s.append(stack.removeLast());
        }
        
        return s.length() == 0 ? "/" : s.toString();
    }

    public String simplifyPath2(String path) {
        LinkedList<String> queue = new LinkedList<>();

        StringBuilder dir = new StringBuilder();
        int len = path.length();
        for (int i = 1; i < len; i++) {
            if (path.charAt(i) == '/') {

                if (dir.length() != 0) {
                    queue.offer(dir.toString());
                    dir.setLength(0);
                }

            } else if (path.charAt(i) == '.') {

                if (path.charAt(i - 1) == '.') {
                    queue.removeLast();
                }

            } else {
                dir.append(path.charAt(i));
            }
        }

        if (dir.length() > 0) {
            queue.offer(dir.toString());
        }

        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            res.append("/" + queue.poll());
        }
        return res.length() == 0 ? "/" : res.toString();
    }

    public static void main(String[] args) {
        testWithVariable("/home//foo/");
        testWithVariable("/home/bar/../foo/");
        testWithVariable("/home/bar/../foo/bar");
        testWithVariable("/home/bar/../foo/./bar");
    }

    private static void testWithVariable(final String path) {
        System.out.println(new CanonicalPath().simplifyPath1(path));
        System.out.println(new CanonicalPath().simplifyPath2(path));
        System.out.println();
    }
}
