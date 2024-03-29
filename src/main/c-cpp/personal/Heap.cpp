#include <iostream>

using namespace std;

int parent(int i)
{
    int ret = i / 2;
    return ret;
}

int left(int i)
{
    return 2 * i;
}

int right(int i)
{
    return (2 * i + 1);
}

void maxHeapify(int *A, int i, int size) // Using directly pointer instead of A[]
{
    int l = left(i);
    int r = right(i);

    // Find index of largest value amount i, l, r
    int g = i;

    if (l < size && A[i] <= A[l])
    {
        g = l;
    }
    else
    {
        g = i;
    }

    if (r < size && A[r] > A[g])
    {
        g = r;
    }

    if (g != i)
    {
        int t = A[i];
        A[i] = A[g];
        A[g] = t;

        // Max Heapify
        maxHeapify(A, g, size);
    }
}

void buildMaxHeap(int A[], int size)
{
    for (int i = size / 2; i >= 0; i--)
    {
        maxHeapify(A, i, size);
    }
}

void printArray(int A[], int size)
{
    cout << "Size : " << size << endl;
    for (int k = 0; k < size; k++)
    {
        cout << A[k];
        if (k + 1 < size)
            cout << ",";
    }

    cout << endl;
}

int main(int argc, char *argv[])
{
    int A[] = {3, 1, 3, 5, 2, 4, 3, 5, 2, 36};
    const int size = sizeof(A) / sizeof(int);

    printArray(A, size);
    buildMaxHeap(A, size);
    printArray(A, size);

    return 0;
}
