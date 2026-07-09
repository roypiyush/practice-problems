package com.leetcode;

import java.util.Arrays;

public class MedianTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {2,2,4,4};
        int[] nums2 = {2,2,2,4,4};

        System.out.println(new MedianTwoSortedArrays().findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0) {
            return median(nums2, 0, nums2.length - 1);
        } else if (nums2.length == 0) {
            return median(nums1, 0, nums1.length - 1);
        }

        if (nums1[0] > nums2[0]) {
            return findMedianSortedArrays(nums2, nums1);
        }

        if (nums1[nums1.length - 1] <= nums2[0]) {

            int len = nums1.length + nums2.length;

            if (len % 2 == 0) {

                int l2 = len / 2;
                int l1 = l2 - 1;

                int v1 = l1 < nums1.length ? nums1[l1] : nums2[l1 - nums1.length];
                int v2 = l2 < nums1.length ? nums1[l2] : nums2[l2 - nums1.length];

                double sum = (double) v1 + (double) v2;
                return sum / 2;

            } else {
                int l = len / 2;
                return l < nums1.length ? nums1[l] : nums2[l - nums1.length];
            }
        } else {
            return sortedArrayMedian(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1);
        }
    }

    public double sortedArrayMedian(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {

        int l1 = e1 - s1 + 1;
        int l2 = e2 - s2 + 1;

        if (l1 + l2 <= 4) {
            return compute(nums1, s1, e1, nums2, s2, e2);
        }

        double m1 = median(nums1, s1, e1);
        double m2 = median(nums2, s2, e2);

        if (m1 == m2) {
            return m1;
        }

        int left1 = leftBound(nums1, s1, e1, m1, m2);
        int right1 = rightBound(nums1, s1, e1, m1, m2);

        int left2 = leftBound(nums2, s2, e2, m1, m2);
        int right2 = rightBound(nums2, s2, e2, m1, m2);

        return sortedArrayMedian(nums1, left1, right1, nums2, left2, right2);
    }

    double median(int[] nums, int s, int e) {
        int len = e - s + 1;

        if (len % 2 == 0) {

            int j = s + len / 2;
            int i = j - 1;
            return (nums[i] + nums[j]) / 2;

        } else {
            return nums[s + len / 2];
        }
    }

    int leftBound(int[] nums, int s, int e, double m1, double m2) {
        if (m2 < m1) {
            return leftBound(nums, s, e, m2, m1);
        }

        return leftElement(nums, s, e, m1);
    }

    int rightBound(int[] nums, int s, int e, double m1, double m2) {
        if (m2 < m1) {
            return rightBound(nums, s, e, m2, m1);
        }

        return rightElement(nums, s, e, m1);
    }

    int leftElement(int[] nums, int s, int e, double key) {

        while (s < e) {
            int m = s + (e - s) / 2;
            if (key < nums[m]) {
                e = m;
            } else {
                s = m + 1;
            }
        }
        // either = or <
        if (nums[s] < key) {
            s++;
        }
        return s;
    }

    int rightElement(int[] nums, int s, int e, double key) {

        while (s < e) {
            int m = s + (e - s) / 2;
            if (nums[m] <= key) {
                s = m + 1;
            } else {
                e = m;
            }
        }

        return s;
    }

    double compute(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {

        int t1 = nums1.length;
        int t2 = nums2.length;

        int l1 = e1 - s1 + 1;
        int l2 = e2 - s2 + 1;

        if ((t1 + t2) % 2 == 0) {

            if (l1 + l2 == 2) {

                int[] arr = new int[]{nums1[e1], nums2[e2]};
                Arrays.sort(arr);
                return ((double) arr[0] + (double) arr[1]) / 2;

            } else if (l1 + l2 == 3) {

                int[] arr = l1 == 2 ? new int[]{nums1[s1], nums1[e1], nums2[s2]} : new int[]{nums1[s1], nums2[s2], nums2[e2]};
                Arrays.sort(arr);
                return (double) arr[1];

            } else {

                int[] arr = new int[]{nums1[s1], nums1[e1], nums2[s2], nums2[e2]};
                Arrays.sort(arr);
                return ((double) arr[1] + (double) arr[2]) / 2;
            }
        } else {

            if (l1 + l2 == 2) {

                int[] arr = new int[]{nums1[s1], nums2[s2]};
                Arrays.sort(arr);
                return arr[0];

            } else if (l1 + l2 == 3) {

                int[] arr = l1 == 2 ? new int[]{nums1[s1], nums1[e1], nums2[s2]} : new int[]{nums1[s1], nums2[s2], nums2[e2]};
                Arrays.sort(arr);
                return arr[1];

            } else {
                int[] arr = new int[]{nums1[s1], nums1[e1], nums2[s2], nums2[e2]};
                Arrays.sort(arr);
                return arr[1];
            }
        }
    }
}
