package com.leetcode;

public class MinElement {
    int getMid(int i, int j) {
        return i + (j - i) / 2;
    }

    int dip(int[] nums, int i, int j) {

        int min = Integer.MAX_VALUE;

        while (i <= j) {
            int mid = getMid(i, j);
            min = Math.min(min, nums[mid]);

            if (nums[i] >= nums[mid] && nums[mid] <= nums[j]) {
                min = Math.min(min, nums[mid]);
                i++;
                j--;
            }
            else if (nums[mid] <= nums[j]) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return min;
    }

    public int findMin(int[] nums) {
        return dip(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        // int[] nums = {4,4, 1};
        // int[] nums = {2,2,2,0,1};
        int[] nums = { 4 };
        System.out.println(new MinElement().findMin(nums));
    }
}
