package com.leetcode;

public class CountSubarraysLessThanK {
    public static void main(String[] args) {
        int[] nums = { 3228, 69040, 78987, 91272, 30479, 24963, 46345, 9764, 35471, 85790, 99941, 38661, 82580, 73375, 22447, 25039, 25921, 58146, 14074, 63926 };
        int k = 1000000;

        System.out.println(new CountSubarraysLessThanK().countSubarrays(nums, k));
    }

    public long countSubarrays(int[] nums, long k) {

        long count = 0;
        int s = 0;
        int e = 0;
        long sum = 0;

        while (e < nums.length) {
            sum += nums[e];
            while (s <= e && score(sum, s, e) >= k) {
                sum -= nums[s];
                s++;
            }
            count += e - s + 1;
            e++;
        }
        return count;
    }

    long score(long sum, int s, int e) {
        return sum * (e - s + 1);
    }
}
