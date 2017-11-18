import java.util.*;
import java.io.*;
class SegmentTree
{
   long a[];
    long tree[];
    long lazy[];
    int n;
       SegmentTree(long arr[])
    {   a=arr;
        n=arr.length;
        tree=new long[5*n];
        lazy=new long[5*n];
    }
       void build(int node, int start, int end)
{
    if(start == end)
    {
        
        tree[node] = a[start];
    }
    else
    {
        int mid = (start + end) / 2;
    
        build(2*node, start, mid);
       
        build(2*node+1, mid+1, end);
       
        tree[node] = tree[2*node] + tree[2*node+1];
    }
}
       void update(int node,int start,int end,int l,int r,long val)
       {
           if(lazy[node]!=0)
           {
              tree[node]+=(end-start+1)*lazy[node]; 
              if(start!=end)
              {
                   lazy[node*2] += lazy[node];  
                   lazy[node*2+1] += lazy[node];
              }
              lazy[node] = 0; 
           }
             if(start > end || start > r || end < l)              // Current segment is not within range [l, r]
                  return;
    if(start >= l && end <= r)
    {

        tree[node] += (end - start + 1) * val;
        if(start != end)
        {

            lazy[node*2] += val;
            lazy[node*2+1] += val;
        }
        return;
    }
      int mid = (start + end) / 2;
    update(node*2, start, mid, l, r, val);        // Updating left child
    update(node*2 + 1, mid + 1, end, l, r, val);   // Updating right child
    tree[node] = tree[node*2] + tree[node*2+1];
       }
       long queryRange(int node, int start, int end, int l, int r)
{
    if(start > end || start > r || end < l)
        return 0;         
    if(lazy[node] != 0)
    {
        
        tree[node] += (end - start + 1) * lazy[node];            // Update it
        if(start != end)
        {
            lazy[node*2] += lazy[node];         // Mark child as lazy
            lazy[node*2+1] += lazy[node];    // Mark child as lazy
        }
        lazy[node] = 0;                 // Reset it
    }
    if(start >= l && end <= r)             // Current segment is totally within range [l, r]
        return tree[node];
    int mid = (start + end) / 2;
    long p1 = queryRange(node*2, start, mid, l, r);         // Query left child
    long p2 = queryRange(node*2 + 1, mid + 1, end, l, r); // Query right child
    return (p1 + p2);
}

}
public class Test
{
    public static void main(String args[]) throws IOException
    { 
Reader sc=new Reader();
int t=sc.nextInt();
StringBuffer sb=new StringBuffer("");
while(t-->0){
    int n=sc.nextInt();
        int c=sc.nextInt();
    long arr[]=new long[n+1];
    SegmentTree st=new SegmentTree(arr);

    while(c-->0){
        int ch=sc.nextInt();
        if(ch==0)
        {
            int p=sc.nextInt();
            int q=sc.nextInt();
            int v=sc.nextInt();
            st.update(1, 1, n, p, q, v);
            
        }
        else{
            sb.append(st.queryRange(1, 1, n, sc.nextInt(), sc.nextInt())+"\n");
        }
    }
    
}
System.out.println(sb);
}
}
class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
 
 
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }

 
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
