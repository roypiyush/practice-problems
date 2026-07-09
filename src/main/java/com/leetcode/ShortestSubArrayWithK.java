package com.leetcode;

public class ShortestSubArrayWithK {

    public static void main(String[] args) {
        final int[] nums = {1,81,32,2,73,43};
        final int k = 107;
        System.out.println(new ShortestSubArrayWithK().minimumSubarrayLength(nums, k));
    }
    public int minimumSubarrayLength(int[] nums, int k) {
        if (nums.length == 0) {
            return -1;
        }

        // index represents lower bit
        int[] numWithBits = new int[32];

        int leftPtr = 0;
        int rightPtr = 0;
        setBits(numWithBits, nums[rightPtr]);

        int len = nums.length + 1;

        while (leftPtr < nums.length && rightPtr < nums.length) {

            if (parseNumber(numWithBits) < k) {
                rightPtr++;
                if (rightPtr < nums.length) {
                    setBits(numWithBits, nums[rightPtr]);
                }
            } else {
                if (leftPtr <= rightPtr) {
                    len = Math.min(len, rightPtr - leftPtr + 1);
                    unsetBits(numWithBits, nums[leftPtr]);
                }
                leftPtr++;
            }
        }

        return len == (nums.length + 1) ? -1 : len;
    }

    void setBits(int[] numWithBits, int num) {
        int pos = 0;
        while (pos < 32) {
            if ((num & (1 << pos)) != 0) {
                numWithBits[pos]++;
            }
            pos++;
        }
    }

    void unsetBits(int[] numWithBits, int num) {
        int pos = 0;
        while (pos < 32) {
            if ((num & (1 << pos)) != 0) {
                if (numWithBits[pos] - 1 >= 0) {
                    numWithBits[pos]--;
                }
            }
            pos++;
        }
    }

    int parseNumber(int[] numWithBits) {
        int r = 0;
        int pos = 0;

        while (pos < 32) {
            if (numWithBits[pos] > 0) {
                r = r | (1 << pos);
            }
            pos++;
        }
        return r;
    }
}
