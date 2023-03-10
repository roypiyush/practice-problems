#include <vector>
#include <iostream>
#include <climits>

using namespace std;

template <typename S>
ostream &operator<<(ostream &os,
                    const vector<S> &vector)
{
    // Printing all the elements
    // using <<
    // for (auto element : vector) --> this works as well
    for (S element : vector)
    {
        os << element << " ";
    }
    return os;
}

int minSubArrayLen(int target, vector<int> &nums)
{

    for (int i = 1; i < nums.size(); i++)
    {
        nums[i] += nums[i - 1];
    }
    cout << nums << endl;

    int size = nums.size();
    int min = INT_MAX;
    int i = -1;
    int j = 0;
    while (i < j && i < size && j < size)
    {
        int diff = nums[j] - (i == -1 ? 0 : nums[i]);
        if (diff == target)
        {
            int m = j - (i == -1 ? 0 : i);
            min = m < min ? m : min;
            i++;
        }
        else if (diff < target)
        {
            j++;
        }
        else
        {
            i++;
        }
    }
    return min;
}

int main(int argc, char *argv[])
{

    vector<int> v = {2, 3, 1, 2, 4, 3};
    cout << minSubArrayLen(7, v) << endl;

    return 0;
}
