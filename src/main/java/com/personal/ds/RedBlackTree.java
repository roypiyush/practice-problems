package com.personal.ds;

enum Color {
    R, // Red
    B  // Black
}

class RBNode {
    public Integer key;
    public Color color;
    public RBNode left;
    public RBNode right;
    public RBNode parent;

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public String toString() {
        return key == null ? "NIL" : Integer.toString(key);
    }
}

/**
 * Properties of <b>Red Black Tree</b>.
 * <ol>
 *     <li>Every node is either black or red</li>
 *     <li>Root is always black</li>
 *     <li>Every leaf node is NIL and BLACK</li>
 *     <li>If a node is red, then both its children are black</li>
 *     <li>For each node, all simple paths from the node to descendant leaves have same number of black nodes</li>
 * </ol>
 */
public class RedBlackTree {
    private static final RBNode NIL = new RBNode();
    private RBNode ROOT;

    private RedBlackTree() {
        NIL.color = Color.B;
        ROOT = NIL;
    }

    public int getHeight(RBNode node) {
        if (node == NIL) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private RBNode leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == NIL) {
            ROOT = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
        return y;
    }

    private RBNode rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == NIL) {
            ROOT = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
        return x;
    }


    public void insert(RBNode newNode) {
        RBNode parent = NIL;
        RBNode currentNode = ROOT;
        while (currentNode != NIL) {
            parent = currentNode;
            if (newNode.key < currentNode.key) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        newNode.parent = parent;
        if (parent == NIL) {
            ROOT = newNode;
        } else if (newNode.key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.left = NIL;
        newNode.right = NIL;
        newNode.color = Color.R;
        fixInsert(newNode);
    }

    private void fixInsert(RBNode newNode) {
        while (newNode.parent.color == Color.R) {
            // If newNode's parent is left child
            if (newNode.parent.parent.left == newNode.parent) {

                RBNode uncle = newNode.parent.parent.right;
                if (uncle.color == Color.R) { // Case 1
                    // Change color of newNode's parent, uncle, grandparent
                    uncle.color = Color.B;
                    newNode.parent.color = Color.B;
                    newNode.parent.parent.color = Color.R;
                    newNode = newNode.parent.parent; // Move the pointer up

                } else {
                    // uncle is black
                    if (newNode.parent.right == newNode) { // case 2
                        newNode = newNode.parent; // Move the pointer up
                        leftRotate(newNode);
                    }
                    // case 3
                    newNode.parent.color = Color.B;
                    newNode.parent.parent.color = Color.R;
                    rightRotate(newNode.parent.parent);
                }
            } else { // If newNode's parent is right child

                RBNode uncle = newNode.parent.parent.left;
                if (uncle.color == Color.R) { // Case 1
                    // Change color of newNode's parent, uncle, grandparent
                    uncle.color = Color.B;
                    newNode.parent.color = Color.B;
                    newNode.parent.parent.color = Color.R;
                    newNode = newNode.parent.parent;

                } else {
                    if (newNode.parent.left == newNode) {
                        newNode = newNode.parent; // Move the pointer up
                        rightRotate(newNode);
                    }
                    newNode.parent.color = Color.B;
                    newNode.parent.parent.color = Color.R;
                    leftRotate(newNode.parent.parent);
                }
            }
        }
        ROOT.color = Color.B;
    }

    @SuppressWarnings("unused")
    private void transplant(RBNode u, RBNode v) {
        if (u.parent == NIL) {
            ROOT = v;
        } else if (u.parent.left == u) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private static RBNode buildNewNode(int key, Color color) {
        RBNode node = new RBNode();
        node.key = key;
        node.color = color;
        node.left = NIL;
        node.right = NIL;
        node.parent = NIL;
        return node;
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int size = 1000;
        for (int i = 0; i < size; i++) {
            final int key = (int) (Math.random() * 1000);
            tree.insert(buildNewNode(key, Color.R));
        }
        System.out.printf("Size=%d TreeHeight=%d%n", size, tree.getHeight(tree.ROOT));
    }
}