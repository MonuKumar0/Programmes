#include<bits/stdc++.h>
#define m 1000000007
using namespace std;
 int possibleways(int n , int* arr)
    {
     long long sum[n];
     long long pd[n];
     long long ans[n];
     sum[0]=arr[0];
     for(int i=1;i<n;i++){
         sum[i]=arr[i]+sum[i-1];
     }
     pd[0]=0;
     pd[1]=0;
     pd[2]=arr[0];
     for(int i=3;i<n;i++){
         pd[i]=arr[i-2]+pd[i-1]+sum[i-3];             //DP
     }
     ans[0]=arr[0];                                                  
     ans[1]=arr[1];
     ans[2]=arr[2]-arr[0];
     for(int i=3;i<n;i++){
         ans[i]=arr[i]*(i*sum[i]+arr[i]-arr[i-2]-pd[i-1]-sum[i-3]);                     //DP
     }
     int res=0;
     for(int i=0;i<n;i++){
         res=(res%m+ans[i]%m)%m;
     }
     return res;    
    }
int main(){
    int output = 0;
    int ip1_size = 0;
    int ip1_i;
    scanf("%d\n", &ip1_size);
    int ip1[ip1_size];
    for(ip1_i = 0; ip1_i < ip1_size; ip1_i++) {
        int ip1_item;
        scanf("%d", &ip1_item);
        
        ip1[ip1_i] = ip1_item;
    }
    output = possibleways(ip1_size,ip1);
    printf("%d\n", output);

    
  return 0;  
}
