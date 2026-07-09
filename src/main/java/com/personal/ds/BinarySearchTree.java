package com.personal.ds;

class TreeNode { 
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public String toString() {
        return String.format(" %d ", val);
    }
}

public class BinarySearchTree {

    private TreeNode root;

    public BinarySearchTree(TreeNode node) {
        root = node;
    }

    public void insert(int val) {

        TreeNode newNode = new TreeNode(val);

        TreeNode parent = null;
        TreeNode cur = root;

        while (cur != null) {
            parent = cur;
            if (cur.left != null && val < cur.left.val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        if (parent == null) {
            root = newNode;
        } else {
            if (val < parent.val) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    public void delete(int val) {
        
        _delete(root, val);
    }

    private TreeNode _delete(TreeNode node, int val) {
        if (node == null) {
            return null;
        }

        if (val < node.val) {
            node.left = _delete(node.left, val);
        } else if (val > node.val) {
            node.right = _delete(node.right, val);
        } else {
            if (node.right == null) {

                return _delete(node.left, val);

            } else if (node.left == null) {
                
                return _delete(node.right, val);

            } else if (node.right.left == null) {

                node.val = node.right.val;
                node.right = node.right.right;
                
            } else {
                TreeNode minNode = _minimum(node.right);
                node.val = minNode.val;
                node.right = _delete(node.right, minNode.val);
            }
        }

        return node;
    }

    private TreeNode _minimum(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    } 

    public void inorder() {
        _inorder(root);
    }

    void _inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        _inorder(node.left);
        System.out.printf("%d ", node.val);
        _inorder(node.right);
    }

    public static void main(String[] args) {
        /*
         *                                              30
         *                          15                                      72 
         *              13                      28                  60              90
         *                                                  58              68
         *                                                             65       70
         */

        TreeNode root = new TreeNode(30);
        root.left = new TreeNode(15);
        root.right = new TreeNode(72);

        root.left.left = new TreeNode(13);
        root.left.right = new TreeNode(28);

        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(90);

        root.right.left.left = new TreeNode(58);
        root.right.left.right = new TreeNode(68);

        root.right.left.right.left = new TreeNode(65);
        root.right.left.right.right = new TreeNode(70);

        root.right.right = new TreeNode(90);
        
        final BinarySearchTree tree = new BinarySearchTree(root);
        tree.inorder();
        
        tree.delete(30);
        System.out.println("\nDeleting node 30");
        tree.inorder();
    }
}
