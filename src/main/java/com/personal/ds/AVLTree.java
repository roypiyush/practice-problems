package com.personal.ds;

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
        return (left == null ? 0 : left.height) - (right == null ? 0 : right.height);
    }

    int getHeight(AVLNode node) {
        return  1 + Math.max(
                node.left == null ? 0 : node.left.height,
                node.right == null ? 0 : node.right.height);
    }

    /**
     *
     * @param left
     * @return pointer to this subtree root left
     */
    AVLNode leftRotate(AVLNode left) {
        AVLNode n = left.right;
        left.right = n.left;
        n.left = left;

        left.height = getHeight(left);
        n.height = getHeight(n);

        return n;
    }

    /**
     *
     * @param right
     * @return pointer to this subtree root right
     */
    AVLNode rightRotate(AVLNode right) {
        AVLNode n = right.left;
        right.left = n.right;
        n.right = right;

        right.height = getHeight(right);
        n.height = getHeight(n);

        return n;
    }

    public void insert(int key) {
        if (ROOT == null) {
            ROOT = insert(null, key);
        } else {
            insert(ROOT, key);
        }
    }

    private AVLNode insert(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }

        int bf = balancingFactor(node.left, node.right);
        if (bf > 1) {
            int h = balancingFactor(node.left.left, node.left.right);
            if (h == -1) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (bf < -1) {
            int h = balancingFactor(node.right.left, node.right.right);
            if (h == 1) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        } else {
            node.height = getHeight(node);
            return node;
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int size = 100000;
        for (int i = 0; i < size; i++) {
            tree.insert((int) (Math.random() * 10000));
        }
        System.out.printf("Size=%d TreeHeight=%d%n", size, tree.ROOT.height);
    }
}
