import java.io.*;
import java.util.*;
public class Test 
{
   public static void main(String args[] ) throws Exception 
    {
Scanner sc=new Scanner(System.in);
int t=sc.nextInt();
while(t-->0)
{
   int n=sc.nextInt();
   int m=sc.nextInt();
   String arr[][]=new String[n][m];
   for(int i=0;i<n;i++){
       for(int j=0;j<m;j++){
           arr[i][j]=sc.next();
       }
   }
   StringBuffer sb=new StringBuffer("");
   int c=1;
   String f=sc.next();
   boolean vis[][]=new boolean[n][m];
   bot b=new bot();
   while(true)
   {
     sb.append(arr[b.i][b.j]);
     vis[b.i][b.j]=true;
     if(arr[b.i][b.j].compareTo(f)==0){
         c=0;
         break;
     }
     if(b.o.compareTo("right")==0)
     {
        if(b.i<n&&(b.j+1)<m&&vis[b.i][b.j+1]==false)
        {
            b.j=b.j+1;
        }
        else if((b.i+1)<n&&b.j<m&&vis[b.i+1][b.j]==false)
        {
            b.i=b.i+1;
            b.o="down";
        }
        else{
            break;
        }
     }
     else if(b.o.compareTo("left")==0){
          if(b.i<n&&(b.j-1)>=0&&vis[b.i][b.j-1]==false)
        {
            b.j=b.j-1;
        }
        else if((b.i-1)>=0&&b.j<m&&vis[b.i-1][b.j]==false)
        {
            b.i=b.i-1;
            b.o="up";
        }
        else{
            break;
        }
     }
     else if(b.o.compareTo("up")==0){
         if((b.i-1)>=0&&(b.j)<m&&vis[b.i-1][b.j]==false)
        {
            b.i=b.i-1;
        }
        else if((b.i)<n&&(b.j+1)<m&&vis[b.i][b.j+1]==false)
        {
            b.j=b.j+1;
            b.o="right";
        }
        else{
            break;
        } 
     }
     else if(b.o.compareTo("down")==0){
          if((b.i+1)<n&&(b.j)<m&&vis[b.i+1][b.j]==false)
        {
            b.i=b.i+1;
        }
        else if((b.i)<n&&(b.j-1)>=0&&vis[b.i][b.j-1]==false)
        {
            b.j=b.j-1;
            b.o="left";
        }
        else{
            break;
        }
     }
   }
   if(c==1){
       System.out.println("NO");
   }
   else{
       System.out.println(sb);
   }
}


}
}
class bot{
    int i,j;
    String o;
bot(){
   i=0;
   j=0;
   o="right";
}
}
