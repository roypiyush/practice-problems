package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EarliestAndLatest {

    public static void main(String[] args) {


        // TODO - Needs optimization
        EarliestAndLatest earliestAndLatest = new EarliestAndLatest();
        System.out.println(Arrays.toString(earliestAndLatest.earliestAndLatest(5, 2, 3)));
        System.out.println(Arrays.toString(earliestAndLatest.earliestAndLatest(11, 2, 4)));
        System.out.println(Arrays.toString(earliestAndLatest.earliestAndLatest(16, 4, 8)));
    }

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        int[] state = new int[]{firstPlayer - 1, secondPlayer - firstPlayer, n - secondPlayer};
        return compute(state, 1);
    }

    List<int[]> makeSates(int R, int l, int b, int r) {
        List<int[]> states = new ArrayList<>();
        for (int i = Math.min(l, R); i >= 0; i--) {
            for (int j = Math.min(R - i, b); j >= 0; j--) {
                for (int k = Math.min(R - i - j, r); k >= R - i - j; k--) {
                    states.add(new int[]{i, j, k});
                }
            }
        }
        return states;
    }

    int[] compute(int[] state, int d) {

        int[] result = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        if (state[0] == state[2]) {
            return new int[]{d, d};
        }

        int n = state[0] + state[1] + state[2] + 2;
        int R = (n + 1) / 2 - 2;
        int l = state[0];
        int b = state[1];
        int r = state[2];
        List<int[]> states = makeSates(R, l, b, r);

        for (int[] s : states) {
            int[] res = compute(s, d + 1);
            result[0] = Math.min(result[0], res[0]);
            result[1] = Math.max(result[1], res[1]);
        }
        return result;
    }

    boolean checkIfClash(List<Integer> players, int fp, int sp) {
        int i = 0;
        int j = players.size() - 1;
        while (i < j) {
            if (players.get(i) == fp && players.get(j) == sp) {
                return true;
            }
            i++;
            j--;
        }
        return false;
    }

    public List<List<Integer>> generateStates(List<Integer> players, int fp, int sp) {

        List<List<Integer>> states = new ArrayList<>();
        _generateStates(states, new ArrayList<>(players), fp, sp, 0, players.size() - 1);
        return states;
    }

    void _generateStates(List<List<Integer>> states, List<Integer> players, int fp, int sp, int i, int j) {

        if (i >= j) {
            List<Integer> pr = new ArrayList<>();
            for (int p : players) {
                if (p > 0) {
                    pr.add(p);
                }
            }
            states.add(pr);
            return;
        }

        if (players.get(i) == fp && players.get(j) == sp) {
            _generateStates(states, players, fp, sp, i + 1, j - 1);
        } else if (players.get(i) == fp && players.get(j) != sp) {
            players.set(j, players.get(j) * -1);
            _generateStates(states, players, fp, sp, i + 1, j - 1);
            players.set(j, players.get(j) * -1);

        } else if (players.get(i) != fp && players.get(j) == sp) {
            players.set(i, players.get(i) * -1);
            _generateStates(states, players, fp, sp, i + 1, j - 1);
            players.set(i, players.get(i) * -1);

        } else {

            // i looses
            players.set(i, players.get(i) * -1);
            _generateStates(states, players, fp, sp, i + 1, j - 1);
            players.set(i, players.get(i) * -1);

            // j looses
            players.set(j, players.get(j) * -1);
            _generateStates(states, players, fp, sp, i + 1, j - 1);
            players.set(j, players.get(j) * -1);
        }
    }
}
