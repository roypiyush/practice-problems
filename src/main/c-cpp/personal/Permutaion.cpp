#include <stdio.h>
#include <algorithm>


void swap(char *a, int i, int j)
{
   char t = a[i];
   a[i] = a[j];
   a[j] = t;
}

/* Function to print permutations of string
   This function takes three parameters:
   1. String
   2. Starting index of the string
   3. Ending index of the string. */
void permute(int *seq, char *a, int i, int n)
{
   int j;
   if (i == n)
   {
      *seq = *seq + 1;
      printf("%s -> %d\n", a, *seq);
      return;
   }

   for (j = i; j <= n; j++)
   {
      swap(a, i, j);
      permute(seq, a, i + 1, n);
      swap(a, i, j); // backtrack
   }
}


int main()
{

   char a[] = "abcd";
   int len = sizeof(a) / sizeof(char) - 1;
   printf("length of input string is %d\n", len);
   int seq = 0;
   permute(&seq, a, 0, len - 1);
   return 0;
}
