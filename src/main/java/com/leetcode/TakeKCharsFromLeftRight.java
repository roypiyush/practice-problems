package com.leetcode;

@SuppressWarnings("ALL")
public class TakeKCharsFromLeftRight {

    public static void main(String[] args) {
        //System.out.println(new BinarySearchMethod().binarySearch("aabbccca", 2));
        System.out.println(new LinearPointer().linearPointer("acbcc", 1));
    }

    static class LinearPointer {
        int linearPointer(String s, int k) {

            // time complexity O(n) and O(n)
            if (k == 0) {
                return 0;
            }

            int size = s.length();

            int[] totalCharCount = calculateTotalCharCount(s, size);
            int[] charCountsSoFar = new int[3];

            if (!isConstraintSatisfied(k, totalCharCount, charCountsSoFar)) {
                return -1;
            }

            int[] aIndices = new int[totalCharCount[0]];
            int[] bIndices = new int[totalCharCount[1]];
            int[] cIndices = new int[totalCharCount[2]];

            populateIndices(s, aIndices, bIndices, cIndices);

            int result = Integer.MAX_VALUE;

            for (int i = -1; i < size; i++) {

                if (i >= 0) {
                    char t = s.charAt(i);
                    charCountsSoFar[t - 'a']++;
                }

                int[] requiredCounts = calculateRequiredCount(k, charCountsSoFar);

                int r = calculateRightPortion(aIndices, bIndices, cIndices, size, requiredCounts, i);
                result = Math.min(result, i + 1 + r);
            }

            return result > size ? -1 : result;
        }

        void populateIndices(String s, int[] aIndices, int[] bIndices, int[] cIndices) {

            int aPtr = 0;
            int bPtr = 0;
            int cPtr = 0;

            int size = s.length();
            for (int i = 0; i < size; i++) {
                char t = s.charAt(i);

                if (t == 'a') {
                    aIndices[aPtr++] = i;

                } else if (t == 'b') {
                    bIndices[bPtr++] = i;

                } else {
                    cIndices[cPtr++] = i;

                }
            }
        }

        int[] calculateRequiredCount(int k, int[] charCountsSoFar) {
            int[] requiredCounts = new int[3];
            for (int i = 0; i < 3; i++) {
                requiredCounts[i] = Math.max(0, k - charCountsSoFar[i]);
            }
            return requiredCounts;
        }

        int[] calculateTotalCharCount(String s, int size) {

            int[] charCountsSoFar = new int[3];
            for (int i = 0; i < size; i++) {
                char t = s.charAt(i);
                charCountsSoFar[t - 'a']++;
            }

            return charCountsSoFar;
        }

        int calculateRightPortion(int[] aIndices, int[] bIndices, int[] cIndices, int size, int[] requiredCounts, int leftEnd) {

            int aSize = aIndices.length;
            int bSize = bIndices.length;
            int cSize = cIndices.length;

            int aPosRef = aSize - requiredCounts[0];
            int bPosRef = bSize - requiredCounts[1];
            int cPosRef = cSize - requiredCounts[2];

            if ((requiredCounts[0] == 0 ? size : aIndices[aPosRef]) <= leftEnd
                    || (requiredCounts[1] == 0 ? size : bIndices[bPosRef]) <= leftEnd
                    || (requiredCounts[2] == 0 ? size : cIndices[cPosRef]) <= leftEnd) {
                return size + 1;
            }

            int aDist = requiredCounts[0] == 0 ? 0 : size - aIndices[aPosRef];
            int bDist = requiredCounts[1] == 0 ? 0 : size - bIndices[bPosRef];
            int cDist = requiredCounts[2] == 0 ? 0 : size - cIndices[cPosRef];

            return Math.max(aDist, Math.max(bDist, cDist));
        }

        boolean isConstraintSatisfied(int k, int[] charCountsSoFar, int[] currentCharCounts) {

            for (int i = 0; i < charCountsSoFar.length; i++) {
                if (charCountsSoFar[i] + currentCharCounts[i] < k) {
                    return false;
                }
            }

            return true;
        }
    }

    static class BinarySearchMethod {
        int binarySearch(String s, int k) {

            // time complexity O(n2 / 2) and O(1)
            if (k == 0) {
                return 0;
            }

            int size = s.length();

            int[] totalCharCount = calculateTotalCharCount(s, size);
            int[] charCountsSoFar = new int[3];

            if (!isConstraintSatisfied(k, totalCharCount, charCountsSoFar)) {
                return -1;
            }

            int[][] runningCount = calculateRunningCount(s, size);

            int result = Integer.MAX_VALUE;

            for (int i = -1; i < size; i++) {

                if (i >= 0) {
                    char t = s.charAt(i);
                    charCountsSoFar[t - 'a']++;
                }

                int[] requiredCounts = calculateRequiredCount(k, charCountsSoFar);

                int r = calculateRightPortion(size, runningCount, requiredCounts, i);
                result = Math.min(result, i + 1 + r);
            }

            return result > size ? -1 : result;
        }

        int[] calculateRequiredCount(int k, int[] charCountsSoFar) {
            int[] requiredCounts = new int[3];
            for (int i = 0; i < 3; i++) {
                requiredCounts[i] = Math.max(0, k - charCountsSoFar[i]);
            }
            return requiredCounts;
        }

        int[][] calculateRunningCount(String s, int size) {

            int[][] runningCount = new int[3][size];

            for (int i = size - 1; i >= 0; i--) {
                char t = s.charAt(i);
                if ('a' == t) {
                    runningCount[0][i] = (i + 1 == size ? 0 : runningCount[0][i + 1]) + 1;
                } else {
                    runningCount[0][i] = i + 1 == size ? 0 : runningCount[0][i + 1];
                }
            }

            for (int i = size - 1; i >= 0; i--) {
                char t = s.charAt(i);
                if ('b' == t) {
                    runningCount[1][i] = (i + 1 == size ? 0 : runningCount[1][i + 1]) + 1;
                } else {
                    runningCount[1][i] = i + 1 == size ? 0 : runningCount[1][i + 1];
                }
            }

            for (int i = size - 1; i >= 0; i--) {
                char t = s.charAt(i);
                if ('c' == t) {
                    runningCount[2][i] = (i + 1 == size ? 0 : runningCount[2][i + 1]) + 1;
                } else {
                    runningCount[2][i] = i + 1 == size ? 0 : runningCount[2][i + 1];
                }
            }

            return runningCount;
        }

        int[] calculateTotalCharCount(String s, int size) {

            int[] charCountsSoFar = new int[3];
            for (int i = 0; i < size; i++) {
                char t = s.charAt(i);
                charCountsSoFar[t - 'a']++;
            }

            return charCountsSoFar;
        }

        int calculateRightPortion(int size, int[][] runningCount, int[] requiredCounts, int leftEnd) {

            int i = leftEnd + 1;
            int j = size - 1;

            // binary search to run between i to j

            int maxRight = Integer.MIN_VALUE;
            for (int l = 0; l < 3; l++) {
                int key = requiredCounts[l];
                int right = key == size - 1 ? 0 : search(runningCount[l], i, j, key);
                maxRight = Math.max(maxRight, size - right);
            }

            return maxRight;
        }

        int search(int[] arr, int i, int j, int key) {
            if (key == 0) {
                return j + 1;
            }
            while (i < j) {
                int mid = i + (j - i) / 2;

                if (arr[mid] >= key) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }

            return i - 1;
        }

        boolean isConstraintSatisfied(int k, int[] charCountsSoFar, int[] currentCharCounts) {

            for (int i = 0; i < charCountsSoFar.length; i++) {
                if (charCountsSoFar[i] + currentCharCounts[i] < k) {
                    return false;
                }
            }

            return true;
        }
    }

    static class BruteForce2 {
        int bruteForce(String s, int k) {

            // time complexity O(n2 / 2) and O(1)
            if (k == 0) {
                return 0;
            }

            int size = s.length();

            int[] charCountsSoFar = new int[3];

            int result = Integer.MAX_VALUE;

            for (int i = 0; i < size - 1; i++) {

                char t = s.charAt(i);
                charCountsSoFar[t - 'a']++;

                int r = calculateRightPortion(s, k, size, charCountsSoFar, i);
                result = Math.min(result, i + 1 + r);
            }

            return result >= size ? -1 : result;
        }

        int calculateRightPortion(String s, int k, int size, int[] charCountsSoFar, int leftEnd) {
            int j = size - 1;

            int[] currentCharCounts = new int[3];

            if (isConstraintSatisfied(k, charCountsSoFar, currentCharCounts)) {
                return 0;
            }

            while (leftEnd < j) {

                char t = s.charAt(j);
                currentCharCounts[t - 'a']++;

                if (isConstraintSatisfied(k, charCountsSoFar, currentCharCounts)) {
                    return size - j;
                }

                j--;
            }

            return size;
        }

        boolean isConstraintSatisfied(int k, int[] charCountsSoFar, int[] currentCharCounts) {

            for (int i = 0; i < charCountsSoFar.length; i++) {
                if (charCountsSoFar[i] + currentCharCounts[i] < k) {
                    return false;
                }
            }

            return true;
        }
    }
    /**
     * Partitions input string into left and right portions.
     * Checks for minimum length with k constraint during processing left and right portions.
     * time complexity O(n2) and O(1)
     *
     */
    static class BruteForce1 {
        int solution(String s, int k) {

            if (k == 0) {
                // special case
                return 0;
            }

            int size = s.length();

            int result = Integer.MAX_VALUE;

            for (int l = 0; l <= size; l++) {
                int[] ptrs = checkBucket(s, k, size, l, size - l);
                if (ptrs != null) {
                    result = Math.min(result, ptrs[0] + ptrs[1]);
                }
            }

            return result == Integer.MAX_VALUE ? -1 : result;
        }

        int[] checkBucket(String s, int k, int size, int leftLength, int rightLength) {

            int a = 0, b = 0, c = 0;

            int leftPos = leftLength - 1;
            int rightPos = size - rightLength;

            int i = 0;
            int j = size - 1;

            while (i <= leftPos || j >= rightPos) {
                if (0 <= i && i <= leftPos) {
                    if (s.charAt(i) == 'a') {
                        a++;
                    } else if (s.charAt(i) == 'b') {
                        b++;
                    } else {
                        c++;
                    }
                    i++;
                }

                if (rightPos <= j && j < size) {
                    if (s.charAt(j) == 'a') {
                        a++;
                    } else if (s.charAt(j) == 'b') {
                        b++;
                    } else {
                        c++;
                    }
                    j--;
                }

                if (a >= k && b >= k && c >= k) {
                    if (leftLength == 0) {
                        return new int[] {0, size - (j + 1)};
                    } else if (rightLength == 0) {
                        return new int[] {i, 0};
                    } else {
                        return new int[] {i, size - (j + 1)};
                    }
                }
            }

            return null;
        }
    }


}
