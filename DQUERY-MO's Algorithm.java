import java.util.*;
import java.io.*;
class Q
{
   int l,r,ind;
   Q(int l,int r,int ind)
   {
    this.l=l;
    this.r=r;
    this.ind=ind;
}
}
public class Test
{
   static int L,R,anss;
   static int[] A=new int[1000002];
   static int cont[]=new int[1000002];
   static int ans[]=new int[100002];
   static void remove( int i )
    {
	cont[ A[i] ]--;
	if( cont[ A[i] ] == 0 )
		anss--;
    }

static void add( int i){
	cont[ A[i] ]++;
	if( cont[ A[i] ] == 1 )
		anss++;
}
    static int query(int i,Q que[]){
        while(L<que[i].l)
        {
            remove(L);
            L++;
        }
        while(L>que[i].l)
        {
            L--;
            add(L);
        }
        while(R<que[i].r)
        {
           R++;
           add(R);
        }
        while( R > que[i].r){
		remove( R );
		R--;
	}
	return anss;
    }
    public static void main(String args[]) throws IOException
    { 
Reader sc=new Reader();
int n=sc.nextInt();
int sq=(int)Math.sqrt(n);
for(int i=1;i<=n;i++)
{
   A[i]=sc.nextInt();
}
int q=sc.nextInt();
Q que[]=new Q[q+1];
que[0]=new Q(-100000,-10000,0);
for(int i=1;i<=q;i++)
{
    que[i]=new Q(sc.nextInt(),sc.nextInt(),i);
}
Arrays.sort(que,1, q, new quec(sq));
for(int i=1;i<=q;i++)
{
  ans[que[i].ind]=query(i,que);  
}
StringBuffer sb=new StringBuffer("");
for(int i=1;i<=q;i++)
{
   sb.append(ans[i]+"\n");
}
System.out.println(sb);
}
}
class quec implements Comparator<Q>{
int sq;
quec(int sq){
    this.sq=sq;
}
    @Override
    public int compare(Q o1, Q o2) {
  if(o1.l/sq!=o2.l/sq)
  {
      return o1.l/sq-o2.l/sq;
  }
  return o1.r-o2.r;
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
