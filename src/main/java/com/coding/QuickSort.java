package com.coding;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    int partition(int[] nums, int p, int q, int pivot) {
        int i = p - 1; // insertion position
        int j = p; // iterator
        while (j < q) {
            if (nums[j] <= pivot) {
                int t = nums[j];
                nums[j] = nums[i + 1];
                nums[i + 1] = t;
                i++;
            }
            j++;
        }
        int t = nums[q];
        nums[q] = nums[i + 1];
        nums[i + 1] = t;
        return i + 1;
    }


    void quickSort(int[] nums, int p, int q) {
        if (p > q) {
            return;
        }
        int partition = partition(nums, p, q, nums[q]);
        quickSort(nums, p, partition - 1);
        quickSort(nums, partition + 1, q);
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 2, 0, 1, 2, 0, 1, 2, 0};

        QuickSort quickSort = new QuickSort();
        int partition = quickSort.partition(nums, 0, nums.length - 1, 0);
        System.out.println(partition);
        quickSort.partition(nums, partition + 1, nums.length - 1, 1);

        System.out.println(Arrays.toString(nums));

        populateWithRandomValues(nums);

        quickSort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private static void populateWithRandomValues(int[] nums) {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(1000);
        }
        System.out.printf("%s -> Generated Array\n", Arrays.toString(nums));
    }
}
