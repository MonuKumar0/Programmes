import java.io.*;
import java.util.*;
public class HLD
{ 
    static int ithp[][]=new int[100200][18];
    static int parents[]=new int[100200];
    static int[] base=new int[100200];
    static int[] posinbase=new int[100200];
    static int chainhead[]=new int[100200];
    static int[] chainbelong=new int[100200];
    static int[] chainend=new int[100200];
    static int chainno=0;
    static int ptr=0;
    static int[] depth=new int[100200];
    static int[] subtree=new int[100200];
    static boolean vis2[]=new boolean[100200];
    static boolean vis1[]=new boolean[100200];
    static int LCA(int a, int b)
    {
        if(depth[a]<depth[b])
        {
            return LCA(b,a);
        }
        int diff=depth[a]-depth[b];
        int u=a;
        int v=b;
        for(int i=0;i<17;i++){
            if((diff&(1<<i))>0){
                u=ithp[u][i];
            }
        }
        if(u==v){
            return u;
        }
        for(int i=17;i>=0;i--)
        {
           if(ithp[u][i]!=ithp[v][i])
           {
               u=ithp[u][i];
               v=ithp[v][i];
           } 
          }
        return ithp[u][0];
    }
    static void findd(Vector<Vector<Integer>> ve)
    {
        int n=ve.size();
        boolean vis[]=new boolean[n+1];
        Queue<Integer> st=new LinkedList<Integer>();
        st.add(1);
        vis[1]=true;
        while(!st.isEmpty())
        {
            int e=st.poll();
            Vector<Integer> v=ve.get(e-1);
            int s=v.size();
            int d=depth[e];
            for(int i=0;i<s;i++)
            {
                if(!vis[v.get(i)])
                {
                    depth[v.get(i)]=d+1;
                    parents[v.get(i)]=e;
                    st.add(v.get(i));
                    vis[v.get(i)]=true;
                }
            }
            }
    }
    static int findss(int e,Vector<Vector<Integer>> ve)
    {   
        
        vis2[e]=true;
        Vector<Integer> v=ve.get(e-1);
        int s=v.size();
        for(int i=0;i<s;i++)
           {
            if(!vis2[v.get(i)])
            {
            subtree[e]=subtree[e]+findss(v.get(i),ve);
            }
        }
        return subtree[e];
    }
    static void hld(int cur, Vector<Vector<Integer>> vee)
    {   
        
        if(chainhead[chainno]==-1)
        {
           chainhead[chainno]=cur; 
        }
        vis1[cur]=true;
        posinbase[cur]=ptr;
        base[ptr++]=cur;
        chainbelong[cur]=chainno;
        if(subtree[cur]==1)
        {
            chainend[chainno]=cur;
            return;
        }
        Vector<Integer> ve=vee.get(cur-1);
        int size=ve.size();
        int max=Integer.MIN_VALUE;
        int n=-1;
        for(int i=0;i<size;i++)
        {
            if(subtree[ve.get(i)]>=max&&(!vis1[ve.get(i)]))
            {
                n=ve.get(i);
                max=subtree[ve.get(i)];
            }
        }
        if(n!=(-1))
        {
        hld(n,vee);
        }
        for(int i=0;i<size;i++)
        {
            if(ve.get(i)!=n&&(!vis1[ve.get(i)]))
            {
                chainno++;
                hld(ve.get(i),vee);
            }
        }
    }
public static void main(String[] args)
{
     Reader sc=new Reader(System.in);
     int n=sc.nextInt();
     int m=sc.nextInt();
     Bit bit=new Bit(n);
     Arrays.fill(chainhead, -1);
     Vector<Vector<Integer>> ve=new Vector<Vector<Integer>>();
     for(int i=0;i<n;i++)
     {
         ve.add(new Vector<Integer>()); 
         subtree[i+1]=1;
     }
     int u,v;
     for(int i=1;i<n;i++)
     {
        u=sc.nextInt();
        v=sc.nextInt();
        ve.get(u-1).add(v);
        ve.get(v-1).add(u);
     }
     findd(ve);
     findss(1,ve);
     hld(1,ve);
   for(int i=1;i<=n;i++)
     {
        ithp[i][0]=parents[i];
     }
     for(int i=1;i<=n;i++)
     {
         for(int j=1;j<=17;j++)
         {
             ithp[i][j]=ithp[ithp[i][j-1]][j-1];
         }
     }
     int a,b,lca;
     while(m-->0)
     {
      a=sc.nextInt();
      b=sc.nextInt();
      lca=LCA(a,b);
      //System.out.println(lca);
     while(chainbelong[a]!=chainbelong[lca])
     {
         int chain=chainbelong[a];
         int head=chainhead[chain];
         bit.update(posinbase[head], posinbase[a], 1);
         a=parents[head];
         
     }
     bit.update(posinbase[lca], posinbase[a], 1);
          while(chainbelong[b]!=chainbelong[lca])
     {
         int chain=chainbelong[b];
         int head=chainhead[chain];
         bit.update(posinbase[head], posinbase[b], 1);
         b=parents[head];
         
     }
     bit.update(posinbase[lca], posinbase[b], 1);
     bit.update(posinbase[lca], posinbase[lca],-1);
     }
   long max=Long.MIN_VALUE;
   long value;
   for(int i=0;i<n;i++){
       value=bit.valueAt(i);
       if(value>0){
           max=Math.max(max, value);
       }
   }
   System.out.println(max);

 }
}

class Bit
{

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
            bit[p]=(bit[p]+v);
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
            sum=(sum+bit[b]);
        }
        return sum;
    }
}
class Reader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;
		
		public Reader(InputStream stream) {
			this.stream = stream;
		}
		
		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}
		
		public int nextInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
		
		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}
		
		public double nextDouble() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, nextInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E') {
						return res * Math.pow(10, nextInt());
					}
					if (c < '0' || c > '9') {
						throw new InputMismatchException();
					}
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}
		
		public long nextLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
		
		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
		
		public String next() {
			return readString();
		}
		
		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
