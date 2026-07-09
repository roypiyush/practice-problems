#include <iostream>
#include <vector>
#include <map>

using namespace std;

template <typename T>
ostream &operator<<(ostream &os, const vector<T> &v)
{
    os << "[";
    for (int i = 0; i < v.size(); ++i)
    {
        os << v[i];
        if (i != v.size() - 1)
            os << ", ";
    }
    os << "]\n";
    return os;
}

class Solution
{
public:
    vector<int> twoSum(vector<int> &nums, int target)
    {
        vector<int> x;
        map<int, int> m;
        for (int k = 0; k < nums.size(); k++)
        {
            int t = target - nums[k];
            if (m.find(t) == m.end()) {
                m.emplace(make_pair(nums[k], k));
            } else {
                x.push_back(m.at(t));
                x.push_back(k);
                break;
            }    
        }
        return x;
    }
};

int main(int argc, char const *argv[])
{
    std::vector<int> nums = {2, 3, 1, 1, 4};
    Solution *solution;
    cout << solution->twoSum(nums, 7);
    return 0;
    
}