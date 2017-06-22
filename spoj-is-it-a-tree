
package javaapplication13;
import java.util.*;
import java.io.*;

public class JavaApplication13 {
    static int c=0;//just a control variable
static ArrayList nodes=new ArrayList();//to store nodes
//definition of node
static class Node{
    int data;
    boolean visited;
    Node(int data){
        this.data=data;
    }
}
public static ArrayList findNeighbours(int a_m[][],Node x){
    //this method returns arraylist of adjacent nodes for node x
    int nodeIndex=-1;
    ArrayList neighbours =new ArrayList();
    for(int i=0;i<nodes.size();i++){
        if(nodes.get(i).equals(x)){
            nodeIndex=i;
            break;
        }
    }
    if(nodeIndex!=-1){
        for(int j=0;j<a_m[nodeIndex].length;j++){
            if(a_m[nodeIndex][j]==1){
                neighbours.add(nodes.get(j));
            }
        }
    }
  return neighbours;  
}
public static void dfs(int a_m[][],Node node){
    Stack stack=new Stack();
    stack.add(node);
    node.visited=true;
    while(!stack.isEmpty()){
        Node element=(Node)stack.pop();
        
        ArrayList neighbours =findNeighbours(a_m,element);
        for(int i=0;i<neighbours.size();i++){
            Node n=(Node)neighbours.get(i);
            if(n.visited){//if a node is being repeated , then there is a cycle, hence not a tree
                c=1;
                System.out.println("NO");
                return;
            }
            else if(n!=null&&!n.visited){//else proceed further
                
                stack.add(n);
                n.visited=true;
            }
            
        }
    }
}
  
    public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
int n=sc.nextInt();
int m=sc.nextInt();
for(int i=0;i<n;i++){
    nodes.add(new Node(i));//storing nodes in ArrayList 'nodes'
}
int[][] a_m=new int[n][n];//adjacency matrix
int u,v;
for(int i=0;i<m;i++){//filling up adjacency matrix
    u=sc.nextInt();
    v=sc.nextInt();
    a_m[u-1][v-1]=1;
}
        
        
  dfs(a_m,(Node)nodes.get(0));//dfs for 0th node
 if(c==0){
     Node node;
     for(int i=0;i<n;i++){//checks if a node left unvisited
       node=(Node)nodes.get(i);
       if(node.visited==false){
           System.out.println("NO");
           c=1;
           break;
       }
     }
     if(c==0){
         System.out.println("YES");
     }
 }
 
  
  
    }

 }

