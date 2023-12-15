#include <iostream>
#include <map>
#include <set>

using namespace std;

class Solution {
public:

    template <typename T>
    // requires std::copyable<T> c++20
    T nthUglyNumber(T n) {
        T x = 0, y = 0, z = 0;
        T result[n];
        result[0] = 1;

        for (auto i = 1; i < n; i++) {
            T min_value = min(result[x] * 2, min(result[y] * 3, result[z] * 5));
            if (min_value == result[x] * 2) x++;
            if (min_value == result[y] * 3) y++;
            if (min_value == result[z] * 5) z++; 
            result[i] = min_value;
        }
        return result[n - 1];
    }

    int nthSuperUglyNumber(int n, vector<int>& primes) {

        const int size = primes.size();
        int* counters = new int[size];
        fill(counters, counters + size, 0);
        

        set<int> store;
        int result[n];
        fill(result, result + n, 0);
        result[0] = 1;

        for (int i = 1; i < n;) {
            
            int cj;
            int min_value = INT_MAX;
            for (int j = 0; j < size; j++) {
                int x = primes[j] * result[counters[j]];
                if (x < min_value) {
                    min_value = x;
                    cj = j;
                }
            }

            counters[cj] = counters[cj] + 1; // increment
            if (store.find(min_value) != store.end()) {
                continue;
            }
            result[i] = min_value;
            store.insert(min_value);
            i++;
        }
        delete[] counters;
        return result[n - 1];
    }
};

int main(int argc, char const *argv[])
{
    Solution sol;
    cout << sol.nthUglyNumber(3) << endl;
    vector<int> vector = {2,7,13,19};
    int r = sol.nthSuperUglyNumber(12, vector);
    cout << r << " " << endl;
}