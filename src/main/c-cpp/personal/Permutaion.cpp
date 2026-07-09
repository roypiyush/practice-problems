#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <string>
#include <chrono>

using namespace std;

#if defined(__linux)
    #define TIME_TAKEN_FORMAT "Time Taken %ld %s\n"
#elif defined(__APPLE__) && defined(__MACH__)
    #define TIME_TAKEN_FORMAT "Time Taken %lld %s\n"
#endif


/* Function to print permutations of string
   This function takes three parameters:
   1. String
   2. Starting index of the string
   3. Ending index of the string. */
void permute(string &a, int i, int n, int& count)
{
   if (i == n)
   {
      count++;
      //cout << a << " count: " << count << endl;
   }
   else 
   {
      for (int j = i; j < n; j++)
      {
         swap(a[i], a[j]);
         permute(a, i + 1, n, count);
         swap(a[i], a[j]); // backtrack
      }
   }
}


int main()
{
   chrono::steady_clock::time_point begin = chrono::steady_clock::now();
   int count = 0;
   string a = "abcdefghihi";
   int len = a.size();
   printf("length of input string is %d\n", len);
   permute(a, 0, len, count);
   chrono::steady_clock::time_point end = chrono::steady_clock::now();
   printf("Total Permuations/Factorial: %d! = %d\n", len, count);
   printf(TIME_TAKEN_FORMAT, chrono::duration_cast<chrono::microseconds>(end - begin).count(), "us");
   printf(TIME_TAKEN_FORMAT, chrono::duration_cast<chrono::milliseconds>(end - begin).count(), "ms");
   printf(TIME_TAKEN_FORMAT, chrono::duration_cast<chrono::seconds>(end - begin).count(), "sec");   

   return 0;
}
