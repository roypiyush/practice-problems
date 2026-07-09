package com.personal.al;

import com.personal.eightpuzzle.PuzzleNode;
import com.personal.eightpuzzle.Utility;

import java.util.concurrent.atomic.AtomicInteger;

public class EightPuzzle {
    public static void main(String[] args) {
        final int[] puzzle = new int[] {3, 0, 6, 7, 8, 1, 2, 4, 5};
        //final int[] puzzle = new int[] {1, 2, 3, 4, 5, 6, 7, 0, 8};
        //AStar(puzzle);
        //HillClimbing(puzzle); // GETS STUCK
        SimulatedAnnealing(puzzle); // GETS STUCK
    }

    // private static void AStar(int[] puzzle) {
    //     final long start = System.currentTimeMillis();
    //     final PuzzleNode initialPuzzleNode = new PuzzleNode(puzzle, null);
    //     AtomicInteger statesGenerated = new AtomicInteger(0);

    //     PuzzleNode current = Utility.aStar(initialPuzzleNode, statesGenerated);
    //     System.out.println();
    //     if (current == null) {
    //         System.out.println("No solution found");
    //         return;
    //     }
    //     Utility.printSolution(current);
    //     System.out.println("States generated " + statesGenerated.get());
    //     System.out.printf("Solution found in %s ms\n", System.currentTimeMillis() - start);
    // }

    // private static void HillClimbing(int[] puzzle) {
    //     final long start = System.currentTimeMillis();
    //     final PuzzleNode initialPuzzleNode = new PuzzleNode(puzzle, null);
    //     AtomicInteger statesGenerated = new AtomicInteger(0);

    //     PuzzleNode current = Utility.hillClimbing(initialPuzzleNode, statesGenerated);
    //     if (current == null) {
    //         System.out.println("No solution found");
    //         return;
    //     }
    //     Utility.printSolution(current);
    //     System.out.println("States generated " + statesGenerated.get());
    //     System.out.printf("Solution found in %s ms\n", System.currentTimeMillis() - start);
    // }

    private static void SimulatedAnnealing(int[] puzzle) {
        final long start = System.currentTimeMillis();
        final PuzzleNode initialPuzzleNode = new PuzzleNode(puzzle, null);
        AtomicInteger statesGenerated = new AtomicInteger(0);

        PuzzleNode current = Utility.simulatedAnnealing(initialPuzzleNode, statesGenerated);
        if (current == null) {
            System.out.println("No solution found");
            return;
        }
        System.out.println("States generated " + statesGenerated.get());
        System.out.printf("Solution found in %s ms\n", System.currentTimeMillis() - start);
    }
}
