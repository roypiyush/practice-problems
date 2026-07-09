package com.personal.eightpuzzle;

public class PuzzleNode {
    private int emptySquare;
    private int[] puzzle;
    private int depth = 0;
    private PuzzleNode parentNode;

    public PuzzleNode(final int[] puzzle, final PuzzleNode parentNode) {
        this.parentNode = parentNode;
        this.puzzle = puzzle;
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[i] == 0) {
                emptySquare = i;
            }
        }
    }

    @Override
    public int hashCode() {
        return getNumber();
    }

    public int[] getPuzzle() {
        return puzzle;
    }

    public int getEmptySquare() {
        return emptySquare;
    }

    public PuzzleNode getParentNode() {
        return parentNode;
    }

    public boolean hasParent() {
        return this.getParentNode() != null;
    }

    /**
     * Number of displaced items
     * @return
     */
    public int hammingDistance() {
        int cost = 0;
        for (int i = 0; i < getPuzzle().length; i++) {
            if (getPuzzle()[i] != i + 1) {
                cost++;
            }
        }
        return cost;
    }

    public int manhattanDistance() {
        int cost = 0;
        for (int i = 0; i < getPuzzle().length; i++) {
            final int finalPosition = getPuzzle()[i] - 1;
            final int currentPosition = i;
            final int diff = Math.abs(finalPosition - currentPosition);
            final double size = Math.sqrt(getPuzzle().length);
            cost += Math.ceil(Math.sqrt(Math.pow(diff % size, 2) + Math.pow(diff / size, 2)));
            // cost += diff%3 + diff/3; // Doesn't perform better
        }
        return cost;
    }

    public int heuristic() {
        return (hammingDistance() + manhattanDistance());
    }

    public int getNumber() {
        int num = 0;
        for (int i = getPuzzle().length - 1; i >= 0; i--) {
            num = (int) (Math.pow(10, getPuzzle().length - 1 - i) * getPuzzle()[i]) + num;
        }
        return num;
    }
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getPuzzle().length; i++) {
            final int value = getPuzzle()[i];
            builder.append(value == 0 ? "X" : value);
            if ((i + 1) % 3 == 0) {
                builder.append("\n");
            } else {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
