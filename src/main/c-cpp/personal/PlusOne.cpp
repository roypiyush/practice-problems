#include <algorithm>
#include <array>
#include <iostream>
#include <iterator>
#include <string>
#include <vector>

using namespace std;

int main(int argc, char const *argv[])
{
    const int size = 3;
    const std::array<int, size> arr = {9, 9, 9};

    vector<int> v;

    int carry = 1;
    for (auto i = arr.rbegin(); i != arr.rend(); i++) { 
        int val = *i + carry;
        carry = val / 10;
        val = val % 10;
        v.push_back(val);
    }

    if (carry == 1) {
        v.push_back(carry);
    }

    for (auto i = v.rbegin(); i != v.rend(); i++) { 
        cout << *i << ' ';
    }
    cout << endl;


    return 0;
}
