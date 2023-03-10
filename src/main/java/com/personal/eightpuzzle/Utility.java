package com.personal.eightpuzzle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Utility {

    public static void printSolution(PuzzleNode solutionNode) {
        //Stack<PuzzleNode> puzzleNodes = new Stack<>();
        if (solutionNode.hasParent()) {
            printSolution(solutionNode.getParentNode());
        }
        System.out.println(solutionNode);
        System.out.print("\r");
    }

    public static int[] getNewState(final int[] currentState, final int source, final int destination) {
        final int[] clone = Arrays.copyOf(currentState, currentState.length);
        final int temp = clone[destination];
        clone[destination] = clone[source];
        clone[source] = temp;
        return clone;
    }

    public static boolean isGoalReached(final PuzzleNode currentNode) {
        for (int i = 0; i < currentNode.getPuzzle().length; i++) {
            if (i + 1 == currentNode.getPuzzle().length) {
                continue;
            }
            if (currentNode.getPuzzle()[i] != (i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static PuzzleNode aStar(final PuzzleNode currentNode, final AtomicInteger statesGenerated) {
        final Set<Integer> seen = new HashSet<>();
        statesGenerated.set(1);
        final Comparator<PuzzleNode> comparator = Comparator.comparingInt(PuzzleNode::heuristic);
        if (Utility.isGoalReached(currentNode)) {
            return currentNode;
        }
        final PriorityQueue<PuzzleNode> states = new PriorityQueue<>(comparator);
        states.add(currentNode);

        while (!states.isEmpty()) {
            System.out.print(String.format("\r statesGen=%s statesSeen=%s", statesGenerated, seen.size()));
            final PuzzleNode current = states.poll();
            if (Utility.isGoalReached(current)) {
                return current;
            }
            final PriorityQueue<PuzzleNode> nextStates = populateNextStates(current, comparator);
            for (PuzzleNode p : nextStates) {
                if (!seen.contains(p.getNumber())) {
                    states.add(p);
                    seen.add(p.getNumber());
                }
            }
            statesGenerated.addAndGet(nextStates.size());
        }
        return null;
    }

    private static PriorityQueue<PuzzleNode> populateNextStates(final PuzzleNode currentNode, final Comparator<PuzzleNode> comparator) {
        final PriorityQueue<PuzzleNode> nextStates = new PriorityQueue<>(comparator);
        final Action action = new Action();
        final int[] neighbors = action.getNeighbors(currentNode.getEmptySquare());
        for (int i = 0; i < neighbors.length; i++) {
            final int a = neighbors[i] - currentNode.getEmptySquare();
            final PuzzleNode e = action.doAction(currentNode, a);
            if (currentNode.hasParent() && Arrays.deepEquals(new Object[] {currentNode.getParentNode().getPuzzle()}, new Object[] {e.getPuzzle()})) {
                continue;
            }
            if (action.isValidAction(e)) {
                nextStates.add(e);
            }
        }
        return nextStates;
    }

    public static PuzzleNode hillClimbing(final PuzzleNode currentNode, final AtomicInteger statesGenerated) {
        final Set<Integer> seen = new LinkedHashSet<>();
        statesGenerated.set(1);

        final Comparator<PuzzleNode> comparator = Comparator.comparingInt(PuzzleNode::hammingDistance);
        PuzzleNode current = currentNode;

        while (true) {
            System.out.print(String.format("\r statesGen=%s statesSeen=%s", statesGenerated, seen.size()));
            if (Utility.isGoalReached(current)) {
                return current;
            }

            seen.add(current.getNumber());

            final PriorityQueue<PuzzleNode> nextStates = populateNextStates(current, comparator);
            statesGenerated.addAndGet(nextStates.size());
            final PuzzleNode puzzleNode = nextStates.peek();
            if (!seen.contains(puzzleNode.getNumber())) {
                current = puzzleNode;
            } else {
                break;
            }
        }
        return null;
    }

    public static PuzzleNode simulatedAnnealing(final PuzzleNode currentNode, final AtomicInteger statesGenerated) {
        final Set<Integer> seen = new LinkedHashSet<>();
        statesGenerated.set(1);

        final Comparator<PuzzleNode> comparator = Comparator.comparingInt(p -> (int) (Math.random() * 100));
        PuzzleNode current = currentNode;

        AtomicLong i = new AtomicLong(0);
        while (i.get() != Long.MAX_VALUE) {
            System.out.print("\r " + statesGenerated + " " + seen.size());
            if (Utility.isGoalReached(current)) {
                System.out.println("\n" + current);
                return current;
            }

            seen.add(current.getNumber());

            final PriorityQueue<PuzzleNode> nextStates = populateNextStates(current, comparator);
            statesGenerated.addAndGet(nextStates.size());
            final PuzzleNode puzzleNode = nextStates.peek();
            if (puzzleNode.heuristic() - current.heuristic() < 0) {
                final PuzzleNode p = nextStates.poll();
                p.setDepth(current.getDepth() + 1);
                current = p;
            } else {
                final PriorityQueue<PuzzleNode> nextStates1 = new PriorityQueue<>((o1, o2) -> (int) (Math.exp((o1.heuristic() - o2.getNumber()) / i.incrementAndGet()) * 1000));
                nextStates1.addAll(nextStates);
                final PuzzleNode p = nextStates1.poll();
                p.setDepth(current.getDepth() + 1);
                current = p;
            }
        }
        return null;
    }
}