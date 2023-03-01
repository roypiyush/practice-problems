#include <iostream>
#include <vector>

using namespace std;

template <typename S> 
ostream& operator<<(ostream& os, const vector<S>& vector) {
    for (auto element : vector) {
        os << element << " ";
    }
    return os;
}

int binarySearch(vector<int> &A, int size, int key) {

    int low, high, mid;
    low = 0; high = size - 1;
    while(low <= high) {
        mid = (low + high) / 2;
        if(key == A[mid]) {
            return mid;
        }
        else if( key > A[mid]) {
            low = mid + 1;
        }
        else {
            high = mid - 1;
        }
    }
    return mid;
}

int main() {

    vector<int> A;
    A.push_back(1);
    A.push_back(2);
    A.push_back(3);
    A.push_back(4);
    A.push_back(5);
    A.push_back(6);
    A.push_back(7);
    A.push_back(8);
    A.push_back(9);
    A.push_back(10);

    cout<<A<<endl;
    int num = 8;
    cout<<"Number: "<<num<<" is present at index = "<<binarySearch(A, 10, num)<<endl;

    return 0;
}
