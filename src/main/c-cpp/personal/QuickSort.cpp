#include <iostream>
#include <cstdlib>

using namespace std;

template <typename T, size_t N>
ostream &operator<<(ostream &out, const T(&a)[N])
{
    out << "[";
    int N = sizeof(a) / sizeof(int);
    for (size_t i = 0; i < N; ++i)
    {
        out << a[i];
        if (i != N - 1)
        {
            out << ", ";
        }
    }
    out << "]";
    return out;
}

void quickSort(int A[], int p, int r);
int partition(int A[], int p, int r);
#define size 7



int main(int argc, char *argv[])
{
    int A[size];
    for (int i = 0; i < size; i++) {
        A[i] = rand() % 10;
    }
    //printArray(A);
    cout<<A;
    quickSort(A, 0, size - 1);
    //printArray(A);
    cout<<A;
    return 0;
}

void quickSort(int A[], int p, int r)
{
    if (p < r)
    {
        int q = partition(A, p, r);
        quickSort(A, p, q - 1);
        quickSort(A, q + 1, r);
    }
}

int partition(int A[], int p, int r)
{
    int pivot = A[r];
    int i = p - 1;

    for (int j = p; j <= r - 1; j++)
    {
        if (A[j] <= pivot)
        {
            i = i + 1;
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            cout<<A;
        }
    }
    int temp = A[r];

    A[r] = A[i + 1];
    A[i + 1] = temp;
    cout<<A;
    return i + 1;
}
