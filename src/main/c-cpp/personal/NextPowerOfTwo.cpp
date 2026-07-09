#include <iostream>
#include <limits>
#include <cmath>

using namespace std;

int posOfLeftMostSetBit(int num) {
    int count = 0;
    if (num == 0 || num == 1) {
        return num;
    }
    while (num != 0) {
        num = num >> 1;
        count++;
    }
    return count == 0 ? count : count - 1;
}

int rangeBitwiseAnd(int left, int right) {
    
    int res = 0;
    while (right != 0) {
        int leftMostBit = posOfLeftMostSetBit(left);
        res = res | (right & (1 << leftMostBit));

        left = left & ~(1 << leftMostBit);
        right = right >> 1;
    }
    return res;
}

int main(int argc, char *argv[]) {
    int num = 5;
    int res = posOfLeftMostSetBit(num);
    printf("Number = %d UpperBound = %d LowerBound = %d\n", num, 1 << res, 1 << (res - 1));

    int left = 5, right = 7;
    cout << rangeBitwiseAnd(left, right) << endl;

    return 0;
}