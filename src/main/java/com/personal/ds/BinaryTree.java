package com.personal.ds;

import java.util.LinkedList;

public class BinaryTree {

    static int max = 0;
    int value;
    int level;
    BinaryTree left;
    BinaryTree right;

    BinaryTree(int value) {
        this.value = value;
    }

    static void levelOrder(LinkedList<BinaryTree> queue) {
        int cur = 1;
        while (queue.size() != 0) {
            BinaryTree item = queue.poll();
            if (item.level > cur) {
                cur = item.level;
                System.out.println();
            }
            System.out.print(item.value + " ");
            if (item.left != null) {
                item.left.level = item.level + 1;
                queue.offer(item.left);
            }
            if (item.right != null) {
                item.right.level = item.level + 1;
                queue.offer(item.right);
            }
        }
    }

    static void spiralLevelOrder(LinkedList<BinaryTree> queue) {
        int cur = 1;
        LinkedList<BinaryTree> even = new LinkedList<>();
        //LinkedList<BinaryTree> odd = new LinkedList<>();
        while (queue.size() != 0) {
            BinaryTree item = queue.poll();
            if (item.level > cur) {
                cur = item.level;
                System.out.println();
            }
            if (item.level % 2 != 0) {
                System.out.print(item.value + " ");
            } else {
                System.out.print(even.pop().value + " ");
            }
            if (item.left != null) {
                item.left.level = item.level + 1;
                queue.offer(item.left);
                if (item.left.level % 2 == 0) {
                    even.push(item.left);
                }
            }
            if (item.right != null) {
                item.right.level = item.level + 1;
                queue.offer(item.right);
                if (item.right.level % 2 == 0) {
                    even.add(item.right);
                }
            }
        }
    }

    static void leftSide(BinaryTree root, int cur) {
        if (root == null)
            return;
        if (cur > max) {
            max = cur;
            System.out.println(root.value);
        }
        leftSide(root.left, cur + 1);
        leftSide(root.right, cur + 1);
    }

    static void inorder(BinaryTree root, LinkedList<BinaryTree> inorder) {
        if (root == null) {
            return;
        }
        inorder(root.left, inorder);
        inorder.add(root);
        inorder(root.right, inorder);
    }

    static void inOrderIterativeWithStack(BinaryTree root) {
        LinkedList<BinaryTree> stack = new LinkedList<>();
        BinaryTree tree = root;

        System.out.printf("In-Order iterative using stack [");
        while (tree != null || !stack.isEmpty()) {
            if (tree != null) {

                stack.push(tree);
                tree = tree.left;
            } else {
                tree = stack.pop();
                System.out.printf("%s, ", tree.value);
                tree = tree.right;
            }
        }
        System.out.println("\b\b]");

    }

    static void preOrderIterativeWithStack(BinaryTree root) {
        System.out.printf("Pre-Order iterative using stack [");

        LinkedList<BinaryTree> stack = new LinkedList<>();
        BinaryTree tree = root;

        while (tree != null || !stack.isEmpty()) {

            if (tree != null) {
                System.out.printf("%s, ", tree.value);
                stack.push(tree);
                tree = tree.left;
            } else {
                tree = stack.pop();
                tree = tree.right;
            }

        }
        System.out.println("\b\b]");

        /*
        inefficient way where stack size is 2 * log(n)

        while (!stack.isEmpty()) {
            BinaryTree tree = stack.pop();
            System.out.printf("%s, ", tree.value);

            if (tree.right != null)
                stack.push(tree.right);
            if (tree.left != null)
                stack.push(tree.left);

        }
         */
    }

    static void postOrderIterativeWith2Stacks(BinaryTree root) {
        LinkedList<BinaryTree> stack = new LinkedList<>();
        LinkedList<BinaryTree> rightChild = new LinkedList<>();

        BinaryTree tree = root;

        System.out.printf("Post-Order iterative using stack [");

        while (tree != null || !stack.isEmpty()) {

            if (tree != null) {
                    stack.push(tree);
                    if (tree.right != null) {
                        rightChild.push(tree.right);
                    }
                    tree = tree.left;

            } else {
                if (stack.peek().right != null && stack.peek().right == rightChild.peek()) {
                    tree = rightChild.isEmpty() ? null : rightChild.pop();
                } else {
                    System.out.printf("%s, ", stack.pop().value);
                }
            }
        }
        System.out.println("\b\b]");
    }

    static void postOrderIterativeWithStack(BinaryTree root) {
        // FIXME
        
        LinkedList<BinaryTree> stack = new LinkedList<>();

        BinaryTree tree = root;

        System.out.printf("Post-Order iterative using stack [");
        int counter = 0;
        BinaryTree popped = null;

        while (tree != null || !stack.isEmpty()) {

            if (tree != null) {
                stack.push(tree);
                if (tree.right != null) {
                    stack.push(tree.right);
                }
                tree = tree.left;

            } else {

                BinaryTree b = stack.pop();
                if (popped == null) {
                    popped = b;
                    counter++;
                }
                if (popped == b) {
                    counter++;
                }

                if (counter == 2) {
                    System.out.printf("%s, ", b.value);
                    tree = null;
                    popped = null;
                }
            }
        }
        System.out.println("\b\b]");
    }

    static void preorder(BinaryTree root, LinkedList<BinaryTree> preorder) {
        if (root == null) {
            return;
        }
        preorder.add(root);
        preorder(root.left, preorder);
        preorder(root.right, preorder);
    }

    static void postorder(BinaryTree root, LinkedList<BinaryTree> postOrder) {
        if (root == null) {
            return;
        }
        postorder(root.left, postOrder);
        postorder(root.right, postOrder);
        postOrder.add(root);
    }

    static int findElement(BinaryTree[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (key == array[i].value)
                return i;
        }
        return -1;
    }

    static BinaryTree construct(BinaryTree[] inorder, int i, int j, LinkedList<BinaryTree> preorder) {
        if (i > j)
            return null;
        int k = preorder.poll().value;
        BinaryTree node = new BinaryTree(k);
        int index = findElement(inorder, k);
        node.left = construct(inorder, i, index - 1, preorder);
        node.right = construct(inorder, index + 1, j, preorder);
        return node;
    }

    private static void main1() {
        /*
                     8
               5           4
           9      7    1       12

         */
        BinaryTree root = new BinaryTree(8);
        root.level = 1;
        root.left = new BinaryTree(5);
        root.right = new BinaryTree(4);
        root.left.left = new BinaryTree(9);
        root.left.right = new BinaryTree(7);
        root.right.left = new BinaryTree(1);
        root.right.right = new BinaryTree(12);
        root.right.right.right = new BinaryTree(24);

        LinkedList<BinaryTree> inorderTree = new LinkedList<>();
        inorder(root, inorderTree);

        LinkedList<BinaryTree> preorderTree = new LinkedList<>();
        preorder(root, preorderTree);

        LinkedList<BinaryTree> postorderTree = new LinkedList<>();
        postorder(root, postorderTree);

        System.out.println(inorderTree);
        System.out.println(preorderTree);
        System.out.println(postorderTree);

        inOrderIterativeWithStack(root);
        preOrderIterativeWithStack(root);
        postOrderIterativeWith2Stacks(root);
        postOrderIterativeWithStack(root);

        LinkedList<BinaryTree> queue = new LinkedList<>();
        queue.offer(root);
        spiralLevelOrder(queue);
        max = 0;
        System.out.println("\n");
        leftSide(root, 1);

        LinkedList<BinaryTree> inorder = new LinkedList<>();
        inorder(root, inorder);
        LinkedList<BinaryTree> preorder = new LinkedList<>();
        preorder(root, preorder);
        System.out.println(inorder);
        System.out.println(preorder);
        BinaryTree[] inorderArray = inorder.toArray(new BinaryTree[inorder.size()]);
        BinaryTree newTree = construct(inorderArray, 0, inorder.size() - 1, preorder);
        inorder.clear();
        inorder(newTree, inorder);
        System.out.println(inorder);
    }


    public static void main(String[] args) {
        main1();
    }

    
    @Override
    public String toString() {
        return value + "";
    }
}
