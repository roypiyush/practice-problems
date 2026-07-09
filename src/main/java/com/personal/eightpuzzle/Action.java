package com.personal.eightpuzzle;

import java.util.Arrays;

public class Action {
    public static final int LEFT = -1;
    public static final int RIGHT = 1;
    public static final int UP = -3;
    public static final int DOWN = 3;

    public PuzzleNode doAction(final PuzzleNode puzzleNode, final int action)
            throws IllegalArgumentException {
        switch (action) {
            case LEFT:
            case RIGHT:
            case UP:
            case DOWN:
                final int[] newState = Utility.getNewState(puzzleNode.getPuzzle(),
                        puzzleNode.getEmptySquare(),
                        puzzleNode.getEmptySquare() + action);
                final PuzzleNode newPuzzleNode = new PuzzleNode(newState, puzzleNode);
                return newPuzzleNode;
            default:
                throw new IllegalArgumentException("Invalid Action");
        }
    }

    public boolean isValidAction(final PuzzleNode newNode) {
        if (newNode == null) {
            return false;
        }
        if (newNode.getParentNode() != null && newNode.getParentNode().getParentNode() != null
                && Arrays.deepEquals(new Object[] {newNode.getParentNode().getParentNode().getPuzzle()}, new Object[] {newNode.getPuzzle()})) {
            return false;
        }
        return true;
    }

    public int[] getNeighbors(final int indexPosition) throws IllegalArgumentException {
        switch (indexPosition) {
            case 0: return new int[] {1, 3};
            case 1: return new int[] {0, 2, 4};
            case 2: return new int[] {1, 5};
            case 3: return new int[] {0, 4, 6};
            case 4: return new int[] {1, 3, 5, 7};
            case 5: return new int[] {2, 4, 8};
            case 6: return new int[] {3, 7};
            case 7: return new int[] {4, 6, 8};
            case 8: return new int[] {5, 7};
            default: throw new IllegalArgumentException("Invalid Index Position");
        }
    }
}

