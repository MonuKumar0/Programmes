import java.io.*;


public class Test
{ 
static int primes[]=new int[]{2,3,5,7,11,13,17,19,23,29,31,37};
static long mul(long x,long y,long p){
    long k=0;
    while(y>0){
        if((y&1)>0){
            k=k+x;
            k%=p;
        }
        y=y/2;
        x=x+x;
        x%=p;
    }
    return k;
}
static long power(long x,long y,long p)
{
   long k=1;
   while(y>0){
       if((y&1)>0){
           k=mul(k,x,p);
           k%=p;
       }
       y=y/2;
       x=mul(x,x,p);
       x%=p;
   }
   return k;
}
static boolean millerTest(long d,long n,long a)
{
    long x=power(a,d,n);
    if(x==1||x==n-1){
        return true;
    }
    while(d!=n-1){
        x=mul(x,x,n);
        d*=2;
        if(x==1) return false;
        if(x==n-1) return true;
    }
    return false;
}
static boolean isPrime(long n){
    if(n<=1||n==4) return false;
    if(n<=3) return true;
    long d=n-1;
    while(d%2==0)
        d=d>>1;
    for(int i=0;i<12;i++){
        if(primes[i]>=n) break;
        if(millerTest(d,n,primes[i])==false) return false;
    }
    return true;
}
   public static void main (String[] args)  throws IOException
    {
      Reader sc=new Reader();
      StringBuffer sb=new StringBuffer("");
      int t=sc.nextInt();
      while(t-->0){
      long n=sc.nextLong();
      if(isPrime(n)==true)
      {
          sb.append("1\n");
      }
      else
      {
         sb.append("0\n");
      }
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
