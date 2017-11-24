import java.util.*;
import java.io.*;
class Edge
{
    int a, b, c;
    Edge(int a,int b,int c)
    {
        this.a=a;
        this.b=b;
        this.c=c;
    }
}
class subset
{
    int parent,rank;
    
}
class EdgeC implements Comparator<Edge>
{

        @Override
        public int compare(Edge o1, Edge o2)
        {
            return o1.c-o2.c;
        }
    
}
class Main
{
static int find (subset subsets[],int i)
{
      if (subsets[i].parent != i)
            return find(subsets, subsets[i].parent);
 
        return i;
}
static void Union (subset subset[],int x,int y){
    int xroot=find(subset,x);
    int yroot=find(subset,y);
      if (subset[xroot].rank < subset[yroot].rank){
            subset[xroot].parent = yroot;}
        else if (subset[xroot].rank > subset[yroot].rank){
            subset[yroot].parent = xroot;}

        else
        {
            subset[yroot].parent = xroot;
            subset[xroot].rank++;
        }
    
}
 public static void main(String args[]) throws IOException
{ 
Reader sc=new Reader();
int t=sc.nextInt();
while(t-->0)
{
    int p=sc.nextInt();
    int n=sc.nextInt();
    int m=sc.nextInt();
    PriorityQueue<Edge> pq=new PriorityQueue<Edge>(new EdgeC());
    subset subset[]=new subset[n+1];
    for(int i=0;i<=n;i++)
    {
    subset[i]=new subset();
    subset[i].parent=i;
    subset[i].rank=0;
}
    while(m-->0)
    {
        pq.add(new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt()));
    }
    int c=0;
    long sum=0;
    while(c<(n-1))
    {
      Edge e=pq.poll();
      if(find(subset,e.a)!=find(subset,e.b))
      {
          sum=sum+e.c;
          c++;
          Union(subset,e.a,e.b);
      }
    }
    System.out.println(sum*p);
}
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
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
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
