import java.util.*;
import java.io.*;
class SegmentTree 
{
    static int max(int a,int b,int c,int d,int e,int f)
    {
        return Math.max(Math.max(a,b),Math.max(Math.max(c,d),Math.max(e,f)));
    }
int st[][];
int arr[];
int n;
static Vector<Integer> ve=new Vector<Integer>();
SegmentTree(int arr[])
{
    n=arr.length;
    this.arr=arr;
    st=new int[4][5*n];
    this.build(1, 1, n-1);

    
}
int query()
{

   int ls,rs,w,ms=0;
   int len=ve.size();
  
   w=st[0][ve.get(0)];
   ls=st[1][ve.get(0)];
   rs=st[2][ve.get(0)];
   ms=st[3][ve.get(0)];
   if(len!=1)
   {
   for(int i=1;i<len;i++)
   {   
      ms=Math.max(Math.max(w+st[0][ve.get(i)],ms),Math.max(st[3][ve.get(i)],rs+st[1][ve.get(i)]));
      ls=Math.max(ls,w+st[1][ve.get(i)]);
      rs=Math.max(st[2][ve.get(i)], rs+st[0][ve.get(i)]);
      w=w+st[0][ve.get(i)];
   }
   }
    
   ve=new Vector<Integer>();
   return ms;

   
   
}
void query(int node,int start,int end,int l,int r)
{
    
    if(r<start||end<l)
    {
        return;
    }
    if(start>=l&&end<=r)
    {
        ve.add(node);
       
        return;
    }
    int mid=start+(end-start)/2;
   query(2*node,start,mid,l,r);
   query(2*node+1,mid+1,end,l,r);
    
}
void build(int node,int start,int end)
{
    if(start==end)
    {
        st[0][node]=arr[start];
        st[1][node]=arr[start];
        st[2][node]=arr[start];
        st[3][node]=arr[start];
    }
    else
    {
        int mid=start+(end-start)/2;
        build(2*node,start,mid);
        build(2*node+1,mid+1,end);
        st[0][node]=st[0][2*node]+st[0][2*node+1];
        st[1][node]=Math.max(st[1][2*node],st[0][2*node]+st[1][2*node+1]);
        st[2][node]=Math.max(st[2][2*node+1],st[2][2*node]+st[0][2*node+1]);
        st[3][node]=Math.max(Math.max(st[3][2*node],st[3][2*node+1]),st[2][2*node]+st[1][2*node+1]);
    }
}


}
public class Test
{
    public static void main(String args[]) throws IOException
    { 
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int arr[]=new int[n+1];
    for(int i=1;i<=n;i++){
        arr[i]=sc.nextInt();
    }
    SegmentTree st=new SegmentTree(arr);
    int t=sc.nextInt();
    StringBuffer sb=new StringBuffer("");
    while(t-->0)
    {
        st.query(1, 1, n, sc.nextInt(), sc.nextInt());
        sb.append(st.query()+"\n");
    }
    System.out.println(sb);

    }
    
 

}
