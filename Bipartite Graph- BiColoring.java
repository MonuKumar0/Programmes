import java.util.*;
public class Test 
{     
public static void main(String[] args)
{
       Scanner sc=new Scanner(System.in);
       int n=sc.nextInt();
      Vector<node> ve=new Vector<node>();
     for(int i=0;i<n;i++){
         ve.add(new node(i+1));
     }
     int u,v,c;
     for(int i=1;i<n;i++){
        u=sc.nextInt();
        v=sc.nextInt();
        ve.get(u-1).s.add(ve.get(v-1));
        ve.get(v-1).s.add(ve.get(u-1));
}
     Stack<node> st=new Stack<node>();
     Vector<node> set0=new Vector<node>();
     Vector<node> set1=new Vector<node>();
     boolean[] visited=new boolean[n+1];
     st.add(ve.get(0));
     set0.add(ve.get(0));
     visited[1]=true;
     int size;
     while(!st.isEmpty())
     {node p=st.pop();
         size=p.s.size();
         for(int i=0;i<size;i++)
         {
             if(p.color==0)
             {
               node pp=p.s.get(i);
               if(!visited[pp.data])
               {
                  pp.color=1;
                  st.add(pp);
                  visited[pp.data]=true;
                  set1.add(pp);
                  
               }
             }
             else
             {
                node pp=p.s.get(i);
               if(!visited[pp.data])
               {
                  pp.color=0;
                  st.add(pp);
                  visited[pp.data]=true;
                  set0.add(pp);
                  
               } 
             }
         }
         
     }
     int s1=set0.size();
     int s2=set1.size();
    

System.out.println((((long)(s1))*s2-(n-1)));

     
}
}
class node{
    int data;
    int color;
    Vector<node> s;
    node(int data){
        this.data=data;
        color=0;
        s=new Vector<node>();
    }
}
