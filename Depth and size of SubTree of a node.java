import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 static boolean vis[]=new boolean[100001];
    static void findd(Vector<node> ve)
    {
        int n=ve.size();
        boolean vis[]=new boolean[n+1];
        Queue<node> st=new LinkedList<node>();
        st.add(ve.get(0));
       vis[1]=true;
        while(!st.isEmpty()){
            node e=st.poll();
            Vector<node> v=e.s;
            int s=v.size();
            int d=e.depth;
            for(int i=0;i<s;i++)
            {
                if(!vis[v.get(i).data])
                {
                    v.get(i).depth=d+1;
                    st.add(v.get(i));
                    vis[v.get(i).data]=true;
                }
            }
            
            
        }
    }
    static int findss(node e)
    {   vis[e.data]=true;
        Vector<node> v=e.s;
        int s=v.size();
        for(int i=0;i<s;i++)
           {
            if(!vis[v.get(i).data])
            {
            e.sb=e.sb+findss(v.get(i));
            }
        }
        

        return e.sb;
    }
    public static void main(String args[] ) throws Exception 
    {
     Scanner sc=new Scanner(System.in);
     int n=sc.nextInt();
      Vector<node> ve=new Vector<node>();
     for(int i=0;i<n;i++){
         ve.add(new node(i+1));
     }
     int u,v,c;
     for(int i=1;i<n;i++)
     {
        u=sc.nextInt();
        v=sc.nextInt();
        ve.get(u-1).s.add(ve.get(v-1));
        ve.get(v-1).s.add(ve.get(u-1));
}
     findd(ve);
     findss(ve.get(0));
    double prod=0;
    long d=0;
   
      for(int i=1;i<n;i++)
      {
         prod=prod+(double)ve.get(i).depth*(n-ve.get(i).sb);
d=d+ve.get(i).depth;
       
     }
      
 
System.out.println(prod/d);

}
}
class node{
    int data;
    int depth;
    int sb;
    Vector<node> s;
    node(int data){
        this.data=data;
        s=new Vector<node>();
        depth=0;
        sb=1;
    }
}
