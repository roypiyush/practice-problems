#include <iostream>
#include <string>
#include <vector>

using namespace std;


class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (strs.empty()) {
            return "\0";
        }

        string prefix = strs[0];
        strs.erase(strs.begin());

        while (!strs.empty()) {
            string cur = strs[0];
            strs.erase(strs.begin());
            
            prefix = getPrefix(prefix, cur);
        }
        return prefix;
    }

    string getPrefix(string &prefix, string &cur)
    {
        int size = min(prefix.size(), cur.size());
        
        string p{"\0"};
        int i = 0;
        while (i < size) {
            char c1 = prefix[i];
            char c2 = cur[i];
            if (c1 != c2) {
                break;
            }
            p.push_back(c1);
            i++;
        }
        return p;
        
    }
};


int main(int argc, char const *argv[])
{
    Solution sol;
    vector<string> strs;
    strs.push_back("flower");
    strs.push_back("flow");
    strs.push_back("flight");
    sol.longestCommonPrefix(strs);
}
