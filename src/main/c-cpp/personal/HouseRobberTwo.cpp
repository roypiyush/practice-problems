#include <vector>
#include <iostream>

using namespace std;

int _rob(vector<int> &nums, int houseNumber, vector<int> &res, int end)
{
    if (!(houseNumber <= end))
    {
        return 0;
    }

    if (res[houseNumber] > -1)
    {
        return res[houseNumber];
    }

    int choice1 = _rob(nums, houseNumber + 1, res, end);
    int choice2 = nums[houseNumber] + _rob(nums, houseNumber + 2, res, end);

    if (choice1 > choice2)
    {
        res[houseNumber] = choice1;
    }
    else
    {
        res[houseNumber] = choice2;
    }

    return res[houseNumber];
}

int rob(vector<int> &nums)
{
    vector<int> res(nums.size());
    fill(res.begin(), res.end(), -1);
    int c1 = _rob(nums, 0, res, nums.size() - 2);
    int c2 = _rob(nums, 1, res, nums.size() - 1);
    return c1 > c2 ? c1 : c2;
}

int main()
{
    vector<int> nums = {1,2,3};
    cout << rob(nums) << endl;
    return 0;
}