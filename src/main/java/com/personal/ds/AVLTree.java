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
     * @return integer value of height difference
     */
    int balancingFactor(AVLNode node) {
        return height(node.left) - height(node.right);
    }

    int getHeight(AVLNode node) {
        return 1 + Math.max(height(node.left), height(node.right));
    }

    int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     *
     * @param X child node
     * @return pointer to this subtree root left
     */
    AVLNode leftRotate(AVLNode X) {
        /*
    
            Right Rotation    <=>       Left Rotation
            -------------------|----------------------
                    Y          |           X                             
                   / \         |          / \                            
                  X   C        |         A   Y                            
                 / \           |            / \                           
                A   B          |           B   C                          
             
         */
        AVLNode Y = X.right;
        X.right = Y.left;
        Y.left = X;

        X.height = getHeight(X);
        Y.height = getHeight(Y);

        return Y;
    }

    /**
     *
     * @param Y child node
     * @return pointer to this subtree root right
     */
    AVLNode rightRotate(AVLNode Y) {
        /*
    
            Right Rotation    <=>       Left Rotation
            -------------------|----------------------
                    Y          |           X                             
                   / \         |          / \                            
                  X   C        |         A   Y                            
                 / \           |            / \                           
                A   B          |           B   C                          
             
         */
        AVLNode X = Y.left;
        Y.left = X.right;
        X.right = Y;

        Y.height = getHeight(Y);
        X.height = getHeight(X);

        return X;
    }

    public void insert(int key) {
        if (ROOT == null) {
            ROOT = insert(null, key);
        } else {
            ROOT = insert(ROOT, key);
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
        node.height = getHeight(node);

        int bf = balancingFactor(node);

        if (bf > 1) {
            // left heavy, node and node.left is unbalanced
            if (balancingFactor(node.left) < 0) {
                // right heavy, then refer to left node of node
                node.left = leftRotate(node.left);
            }
            // stream lined so rotate current node to right and return it
            return rightRotate(node);
        }
        
        if (bf < -1) {
            // mirror logic of previous
            // right heavy
            if (balancingFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        
        return node;
    }

    private int _minHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.min(_minHeight(node.left), _minHeight(node.right));
    }

    public int minHeight() {
        return _minHeight(ROOT);
    }

    private int _maxHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(_maxHeight(node.left), _maxHeight(node.right));
    }

    public int maxHeight() {
        return _maxHeight(ROOT);
    }

    AVLNode _delete(AVLNode node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = _delete(node.left, key);
        } else if (key > node.key) {
            node.right = _delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                AVLNode successor = treeMin(node.right);
                node.key = successor.key;
                node.right = _delete(node.right, successor.key);
            }
        }

        node.height = getHeight(node);
        int bf = balancingFactor(node);

        if (bf > 1) {
            // left heavy, node and node.left is unbalanced
            if (balancingFactor(node.left) < 0) {
                // right heavy, then refer to left node of node
                node.left = leftRotate(node.left);
            }
            // stream lined so rotate current node to right and return it
            return rightRotate(node);
        }

        if (bf < -1) {
            // mirror logic of previous
            // right heavy
            if (balancingFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        return node;
    }

    AVLNode treeMin(AVLNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int size = 5000;
        for (int i = 0; i < size; i++) {
            tree.insert((int) (Math.random() * 10));
        }
        System.out.printf("Size=%d TreeHeight=%d%n", size, tree.ROOT.height);

        System.out.println("Min Height " + tree.minHeight());
        System.out.println("Max Height " + tree.maxHeight());
        System.out.println("Deleting " + tree._delete(tree.ROOT, 8));
    }
}
