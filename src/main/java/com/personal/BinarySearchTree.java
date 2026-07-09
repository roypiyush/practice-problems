package com.personal;


class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key) {
        this.val = key;
    }
}

public class BinarySearchTree {
    private TreeNode root;

    void insert(int key) {

        TreeNode parent = null;
        TreeNode cur = root;
        while (cur != null) {
            parent = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        if (parent == null) {
            root = new TreeNode(key);
        } else if (key < parent.val) {
            parent.left = new TreeNode(key);
        } else {
            parent.right = new TreeNode(key);
        }
    }

    public void inorderTraversal() {
        inorder(root);
        System.out.println();
    }

    void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    TreeNode search(int key) {

        TreeNode cur = root;
        while (cur != null) {

            if (key == cur.val) {
                return cur;
            } else if (key < cur.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    void delete(int key) {
        root = _delete(root, key);
    }

    TreeNode minimum(TreeNode cur) {
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    TreeNode maximum(TreeNode cur) {
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    TreeNode _delete(TreeNode cur, int key) {

        // restructuring corresponding left / right subtree
        if (cur == null) {
            return null;
        } else if (key < cur.val) {
            cur.left = _delete(cur.left, key);
        } else if (key > cur.val) {
            cur.right = _delete(cur.right, key);
        } else {

            if (cur.left == null) {
                return cur.right;
            } else if (cur.right == null) {
                return cur.left;
            } else if (cur.right.left == null) {
                return cur.right;
            } else {
                TreeNode min = minimum(cur.right);
                cur.val = min.val;
                cur.right = _delete(cur.right, min.val);
            }
        }
        return cur;
    }

    TreeNode predecessor(TreeNode node) {
        if (node.left != null) {
            return maximum(node.left);
        } else {
            TreeNode predecessor = null;
            TreeNode current = root;

            while (current != null) {
                if (node.val < current.val) {
                    current = current.left;
                } else if (node.val > current.val) {
                    predecessor = current;
                    current = current.right;
                } else {
                    break;
                }
            }
            return predecessor;
        }
    }

    TreeNode successor(TreeNode node) {
        if (node.right != null) {
            return minimum(node.right);
        } else {
            TreeNode successor = null;
            TreeNode current = root;

            while (current != null) {
                if (node.val < current.val) {
                    successor = current;
                    current = current.left;
                } else if (node.val > current.val) {
                    current = current.right;
                } else {
                    break;
                }
            }
            return successor;
        }
    }


    public static void main(String[] args) {

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(5);
        binarySearchTree.insert(2);
        binarySearchTree.insert(1);
        binarySearchTree.insert(3);
        binarySearchTree.insert(7);
        binarySearchTree.insert(6);
        binarySearchTree.insert(8);
        binarySearchTree.insert(10);

        binarySearchTree.inorderTraversal();
        TreeNode node4 = binarySearchTree.search(4);
        System.out.println("node 4 " + node4);
        TreeNode node5 = binarySearchTree.search(5);

        System.out.println(binarySearchTree.successor(node5).val);
        System.out.println(binarySearchTree.predecessor(node5).val);

        binarySearchTree.delete(5);
        binarySearchTree.inorderTraversal();

    }
}


