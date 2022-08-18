package com.personal.al;

import java.util.PriorityQueue;

class HuffmanNode {
    private String value;
    private int frequency;

    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(final String value, final int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public String getValue() {
        return value;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return frequency + "->" + value;
    }
}

public class HuffmanCoding {


    /**
     * Build Binary Tree for Huffman codes
     * 
     * @param queue
     * @return root of tree
     */
    private HuffmanNode buildTree(final PriorityQueue<HuffmanNode> queue) {

        if (queue.size() == 0) {
            return null;
        }

        while (!queue.isEmpty()) {
            if (queue.size() == 1) {
                break;
            }
            final HuffmanNode n1 = queue.poll();
            final HuffmanNode n2 = queue.poll();

            final HuffmanNode parent = new HuffmanNode(null, n1.getFrequency() + n2.getFrequency());
            parent.setLeft(n1);
            parent.setRight(n2);
            
            queue.add(parent);
        }
        return queue.poll();
    }

    private void printCodes(final HuffmanNode root, final String code) {
        if (root.getValue() != null) {
            System.out.println(root.getValue() + " -> " + code);
            return;
        }

        printCodes(root.getLeft(), code + "0");
        printCodes(root.getRight(), code + "1");
    }

    public static void main(String[] args) {
        final PriorityQueue<HuffmanNode> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getFrequency() - o2.getFrequency() > 0) {
                return 1;
            } else if (o1.getFrequency() - o2.getFrequency() < 0) {
                return -1;
            } else {
                if (o1.getValue() != null && o2.getValue() == null) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        queue.add(new HuffmanNode("D", 5));
        queue.add(new HuffmanNode("C", 4));
        queue.add(new HuffmanNode("B", 3));
        queue.add(new HuffmanNode("A", 2));
        
        final HuffmanCoding mainDriver = new HuffmanCoding();
        final HuffmanNode root = mainDriver.buildTree(queue);
        mainDriver.printCodes(root, "");
    }
}
