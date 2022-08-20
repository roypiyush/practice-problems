package com.personal.ds;

import java.util.Deque;
import java.util.LinkedList;

class AVLNode {
    Integer key;
    AVLNode left;
    AVLNode right;
    Integer height;

    public AVLNode(int key) {
        this.key = key;
        this.height = 1;
    }

    @Override
    public String toString() {
        return key == null ? null : (String.format("k=%d h=%d", key, height));
    }
}

public class AVLTree {
    AVLNode ROOT;

    /**
     * Difference of height between right and left subtree.
     * +ve if right heavy subtree
     * -ve if left heavy subtree
     *
     * @param left
     * @param right
     * @return integer value of height difference
     */
    int balancingFactor(AVLNode left, AVLNode right) {
        if (left == null && right == null) {
            return 0;
        } else if (left == null) {
            return 0 - right.height;
        } else if (right == null) {
            return left.height - 0;
        } else {
            return left.height - right.height;
        }
    }

    /**
     *
     * @param node
     * @return pointer to this subtree root node
     */
    AVLNode leftRotate(AVLNode node) {
        AVLNode n = node.right;
        node.right = n.left;
        n.left = node;

        node.height = node.left == null && node.right == null ? 1 : (Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height) + 1);
        n.height = n.left == null && n.right == null ? 1 : (Math.max(n.left == null ? 0 : n.left.height, n.right == null ? 0 : n.right.height) + 1);

        return n;
    }

    /**
     *
     * @param node
     * @return pointer to this subtree root node
     */
    AVLNode rightRotate(AVLNode node) {
        AVLNode n = node.left;
        node.left = n.right;
        n.right = node;

        node.height = node.left == null && node.right == null ? 1 : (Math.max(node.left == null ? 0 : node.left.height, node.right == null ? 0 : node.right.height) + 1);
        n.height = n.left == null && n.right == null ? 1 : (Math.max(n.left == null ? 0 : n.left.height, n.right == null ? 0 : n.right.height) + 1);

        return n;
    }

    void insert(AVLNode node) {
        AVLNode p = null;
        AVLNode cur = ROOT;

        Deque<AVLNode> stack = new LinkedList<>();
        while (cur != null) {
            p = cur;
            stack.push(p);
            if (node.key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        if (p == null) {
            ROOT = node;
            return;
        }

        if (node.key < p.key) {
            p.left = node;
        } else {
            p.right = node;
        }

        // Fix unbalanced node
        while (!stack.isEmpty()) {
            AVLNode parent = stack.pop();
            AVLNode temp = stack.peek();
            Boolean isLeft = temp == null ? null : temp.left == parent;

            int ht = balancingFactor(parent.left, parent.right);
            // ht >= 2 or ht <= -2 is considered unbalanced
            if (ht == 2) {
                int h = balancingFactor(parent.left.left, parent.left.right);
                if (h == -1) {
                    parent = leftRotate(parent);
                }
                parent = rightRotate(parent);
                if (isLeft == null) {
                    ROOT = parent;
                } else if (isLeft) {
                    temp.left = parent;
                } else {
                    temp.right = parent;
                }
            } else if (ht == -2) {
                int h = balancingFactor(parent.right.left, parent.right.right);
                if (h == 1) {
                    parent = rightRotate(parent);
                }
                parent = leftRotate(parent);
                if (isLeft == null) {
                    ROOT = parent;
                } else if (isLeft) {
                    temp.left = parent;
                } else {
                    temp.right = parent;
                }
            }

            parent.height = Math.max((parent.left == null ? 0 : parent.left.height), (parent.right == null ? null : parent.right.height)) + 1;
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int size = 100;
        for (int i = 1; i < size + 1; i++) {
            tree.insert(new AVLNode(i));
        }
        System.out.println(tree);
    }
}
