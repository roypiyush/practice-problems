#include <iostream>
#include <vector>

using namespace std;

class Solution
{
public:
    bool canJump(vector<int> &nums)
    {
        int size = nums.size();
        bool result[size];
        fill(result, result + size, false);

        int j = size - 1;
        result[j--] = true;

        while (j >= 0) {
            int jump = nums[j];
            while (jump > 0) {
                int toPlace = j + jump; 
                if (toPlace >= size - 1 || result[toPlace]) {
                    result[j] = true;
                    break;
                }
                jump--;
            }
            j--;
        }

        return result[0];
    }
};

int main(int argc, char const *argv[])
{
    std::vector<int> nums = {2, 3, 1, 1, 4};
    Solution *solution;
    std::cout << solution->canJump(nums) << std::endl;
    return 0;
}