
#include <bits/stdc++.h>

using namespace std;


class Solution {
public:
    int findMin(vector<int>& nums) {

        int i = 0;
        int j = nums.size() - 1;

        int min_value = INT_MAX;
        while (i <= j) 
        {
            int mid = getMid(i, j);
            if (nums[i] <= nums[mid] && nums[mid] >= nums[j]) {
                min_value = min(min_value, nums[mid]);
                i++;
                j--;
            }
            if (nums[mid] < nums[j]) 
            {
                j = mid - 1;
            } 
            else 
            {
                i = mid + 1;
            }

        }
        return min_value;
    }

    int getMid(int i, int j) 
    {
        return i + (j - i) / 2;
    }
};

int main(int argc, char const *argv[])
{
    vector<int> nums;
    nums.push_back(4);
    nums.push_back(4);
    nums.push_back(5);
    nums.push_back(6);
    nums.push_back(0);
    nums.push_back(1);
    nums.push_back(2);
    nums.push_back(3);
    nums.push_back(4);
    Solution s = Solution();
    cout<<s.findMin(nums)<<endl;
    return 0;
}
