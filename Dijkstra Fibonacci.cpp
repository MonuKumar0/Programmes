#include<stdio.h>
#define M 1000000007
const int MAX = 10000001;

long long f[MAX] = {0};
long long fib(int n)
{

    if (n == 0)
        return 0;
    if (n == 1 || n == 2)
        return f[n]=1;

      if (n<=10000000&&f[n])
        return f[n];
    int k = (n & 1)? (n+1)/2 : n/2;

   long long res=(n & 1)? ((fib(k)%M*fib(k)%M)%M + (fib(k-1)%M*fib(k-1)%M)%M)%M: (((2*fib(k-1)%M)%M + fib(k)%M)%M*fib(k)%M)%M;
   if(n<=10000000){
   	f[n]=res;
   }
return res;
}
 
long long calculateSum(int n)
{
    return (fib(n+2)%M - 1+M)%M;
}
int main()
{
    int n,m,t;
    scanf("%d",&t);
    while(t--)
    {
    scanf("%d%d",&n,&m);
    printf("%llu\n",(calculateSum(m)%M-calculateSum(n-1)%M+M)%M);
    }
    return 0;
}
