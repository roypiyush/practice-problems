#include <vector>
#include <iostream>

class Solution
{

public:
    std::vector<std::vector<int>> threeSum(std::vector<int> &nums)
    {
        std::vector<std::vector<int>> result;
        std::sort(nums.begin(), nums.end());

        int size = nums.size();
        int i = 0;

        while (i < size)
        {
            int j = i + 1;
            int k = size - 1;

            while (j < k)
            {
                int s = sum(nums, i, j, k);

                if (s == 0)
                {

                    std::vector<int> r;
                    r.push_back(nums[i]);
                    r.push_back(nums[j]);
                    r.push_back(nums[k]);
                    result.push_back(r);

                    j++;
                    while (j < size && nums[j - 1] == nums[j])
                    {
                        j++;
                    }

                    k--;
                    while (j < k && nums[k] == nums[k + 1])
                    {
                        k--;
                    }
                }
                else if (s < 0)
                {
                    j++;
                    while (j < size && nums[j - 1] == nums[j])
                    {
                        j++;
                    }
                }
                else
                {
                    k--;
                    while (j < k && nums[k] == nums[k + 1])
                    {
                        k--;
                    }
                }
            }

            i++;
            while (i < size && nums[i - 1] == nums[i])
            {
                i++;
            }
        }

        return result;
    }

    int sum(std::vector<int> &nums, int i, int j, int k)
    {
        return nums[i] + nums[j] + nums[k];
    }
};

int main(int argc, char const *argv[])
{
    std::vector<int> nums = {-1, 0, 1, 2, -1, -4};
    Solution *solution;
    std::vector<std::vector<int>> result = solution->threeSum(nums);
    std::cout << result.size() << std::endl;
    return 0;
}
