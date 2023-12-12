#include <iostream>
#include <vector>
#include <string.h>

class Solution
{

private:
    std::string LEFT_PARENTHESIS = "(";
    std::string RIGHT_PARENTHESIS = ")";

    void compute(std::vector<std::string>& result, int leftCount, int rightCount, std::string& data) 
    {
        if (leftCount == 0 && rightCount == 0)
        {
            result.push_back(data);
            return;
        }

        bool isLeft = leftCount > 0;
        bool isRight = leftCount < rightCount;

        if (isLeft)
        {
            data.append(LEFT_PARENTHESIS);
            compute(result, leftCount - 1, rightCount, data);
            data.erase(data.size() - 1);
        }

        if (isRight)
        {
            data.append(RIGHT_PARENTHESIS);
            compute(result, leftCount, rightCount - 1, data);
            data.erase(data.size() - 1);
        }
    }
public:
    std::vector<std::string> generate_parenthesis(int n)
    {
        std::vector<std::string> result;
        std::string data = "";
        this->compute(result, n, n, data);
        return result;
    }
};

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::vector<T>& vec) {
    os << "[";
    int i = 0;
    size_t size = vec.size();
    for (auto data : vec) {
        os << data;
        if (i + 1 < size) {
            os << ", ";
        }
        i++;
    }
    os << "]\n";
    return os;
}

int main(int argc, char const *argv[])
{
    Solution solution;
    std::vector<std::string> res{solution.generate_parenthesis(3)};
    std::cout << res;
    return 0;
}
