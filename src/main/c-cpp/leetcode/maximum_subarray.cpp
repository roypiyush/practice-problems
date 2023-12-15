#include <iostream>
#include <vector>

class Solution
{
public:
    int maxSubArray(std::vector<int> &nums)
    {
        int size = nums.size();

        int curSum = 0;
        int maxSum = nums[0];

        for (std::vector<int>::iterator it = nums.begin(); it != nums.end(); ++it)
        {
            curSum = std::max(curSum + *it, *it);
            maxSum = std::max(maxSum, curSum);
        }

        return maxSum;
    }
};

int main(int argc, char const *argv[])
{
    std::vector<int> nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    Solution *solution;
    int result = solution->maxSubArray(nums);
    std::cout << result << std::endl;
    return 0;
}