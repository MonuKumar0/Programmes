import java.util.*;
class Bit
{
    static long M=1000000007;
    long[] bit;
    int n;
    Bit(int n)
    {
        bit=new long[n+1];
        this.n=n;
    }
    void update(int p,long v)
    {
        p=p+1;
        for(;p<=n;p+=(p&(-p)))
        {
            bit[p]=(bit[p]%M+v%M+M)%M;
        }
    }
    void update(int l,int r,long v)    // Adds v from l to r
    {
        update(l,v);
        update(r+1,-v);
    }
    long valueAt(int b)
    {
        b=b+1;
        long sum=0;
        for(;b>0;b-=b&(-b))
        {
            sum=(sum%M+bit[b]%M+M)%M;
        }
        return sum;
    }
}
public class JavaApplication5 
{
public static void main(String args[])  
{
Scanner sc=new Scanner(System.in);
int t=sc.nextInt();
while(t-->0)
{
    int n=sc.nextInt();
    int m=sc.nextInt();
    int[] q=new int[m];
     int[] l=new int[m];
      int[] r=new int[m];
      for(int i=0;i<m;i++){
        q[i]=sc.nextInt();
        l[i]=sc.nextInt();
        r[i]=sc.nextInt();
      }

      Bit qc=new Bit(m);
     qc.update(0, m-1, 1);
     
for(int i=(m-1);i>=0;i--)
{
  if(q[i]==2){
    
    qc.update(l[i]-1, r[i]-1,qc.valueAt(i));
  }  
}
Bit ar=new Bit(n);
ar.update(0, n-1, 0);
for(int i=0;i<m;i++)
{
    if(q[i]==1)
    {
        ar.update(l[i]-1, r[i]-1, qc.valueAt(i));
    }
}
for(int i=0;i<n;i++)
{
 System.out.print(ar.valueAt(i)+" ");
}
System.out.println();
}
}
    
}
