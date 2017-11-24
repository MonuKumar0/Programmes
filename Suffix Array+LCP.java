import java.util.*;
import java.io.*;
class Suffix
{
    int index;
    int rank[];
    Suffix(int i)
    {
        this.index=i;
       this.rank=new int[2];
    }
}
class sc implements Comparator<Suffix>
{

    @Override
    public int compare(Suffix o1, Suffix o2) 
    {
       return (o1.rank[0] == o2.rank[0])? ((o1.rank[1] == o2.rank[1])? 0:(o1.rank[1] > o2.rank[1])? 1:-1):((o1.rank[0] > o2.rank[0])? 1:-1);
    }
    
}
class Main
{
static int[] buildSuffixArray(String str)
{
    int n=str.length();
    char[] s=str.toCharArray();
    Suffix suffixes[]=new Suffix[n];
    for(int i=0;i<n;i++)
    {
  
        suffixes[i]=new Suffix(i);
        suffixes[i].rank[0]=s[i];
        suffixes[i].rank[1]=((i+1) < n)? (s[i + 1]): -1;
    }
    Arrays.sort(suffixes,new sc());
   int ind[]=new int[n];
   for(int k=4;k<2*n;k*=2)
   {
             
      int rank=0;
      int prev_rank=suffixes[0].rank[0];
      suffixes[0].rank[0]=rank;
      ind[suffixes[0].index]=0;
      for(int i=1;i<n;i++)
      {
   
          if(suffixes[i].rank[0]==prev_rank&&suffixes[i].rank[1]==suffixes[i-1].rank[1])
          {
              prev_rank=suffixes[i].rank[0];
              suffixes[i].rank[0]=rank;
          }
          else
          {
            prev_rank=suffixes[i].rank[0];
            suffixes[i].rank[0]=++rank;
          }
          ind[suffixes[i].index]=i;
      }
      for(int i=0;i<n;i++)
      {
             
          int ni=suffixes[i].index+k/2;
          suffixes[i].rank[1]=(ni<n)?suffixes[ind[ni]].rank[0]:-1;
      }
      Arrays.sort(suffixes,new sc());
 
       
   }
    int arr[]=new int[n];
    for(int i=0;i<n;i++)
    {
       arr[i]=suffixes[i].index;
    }
      return arr;
}
static int[] lcp(String str,int[] sa)
{
    int n=sa.length,k=0;
    char s[]=str.toCharArray();
    int[] arr=new int[n];
    int[] rank=new int[n];
    for(int i=0;i<n;i++)
    {
        rank[sa[i]]=i;
    }
    for(int i=0;i<n;i++)
    {
        if(rank[i]==n-1){
            k=0;continue;
        }
        int j=sa[rank[i]+1];
        while((i+k<n)&&(j+k<n)&&(s[i+k]==s[j+k])){
            k++;
        }
        arr[rank[i]]=k;
        if(k>0)
        {
            k--;
        }
    }
    return arr;
}
    public static void main(String args[]) throws IOException
    { 
        Scanner sc=new Scanner(System.in);
int t=sc.nextInt();
while(t-->0)
{
String str=sc.next();
int ns=str.length();
int arr[]=buildSuffixArray(str);

int na=arr.length;
int lcp[]=lcp(str,arr);
int nl=lcp.length;
int ar[]=new int[nl];
for(int i=0;i<nl-1;i++)
{  
    ar[i+1]=lcp[i];
}
int c=0;
for(int i=0;i<nl;i++)
{    
    c=c+Math.abs((ns-arr[i])-ar[i]);
   
}
System.out.println(c);
}
}
}
