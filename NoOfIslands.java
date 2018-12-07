
import java.util.*;
import java.io.*;

public class main
{ 


static boolean visited[][];


static void dfs(int arr[][],int i,int j,int r,int c) 
{
	Stack<Integer> x = new Stack();
	Stack<Integer> y = new Stack();
	visited[i][j]=true;
	x.add(i);
	y.add(j);
	while((!x.isEmpty())&&(!y.isEmpty())) {
		int xx=x.pop();
		int yy=y.pop();
		if(xx-1>=0&&(arr[xx-1][yy]==1)&&(!visited[xx-1][yy])) {
			x.add(xx-1);
			y.add(yy);
			visited[xx-1][yy]=true;
		}
		if(xx-1>=0&&yy+1<c&&(arr[xx-1][yy+1]==1)&&(!visited[xx-1][yy+1])) {
			x.add(xx-1);
			y.add(yy+1);
			visited[xx-1][yy+1]=true;
		}
		if(xx-1>=0&&yy-1>=0&&(arr[xx-1][yy-1]==1)&&(!visited[xx-1][yy-1])) {
			x.add(xx-1);
			y.add(yy-1);
			visited[xx-1][yy-1]=true;
		}
		if(xx+1<r&&(arr[xx+1][yy]==1)&&(!visited[xx+1][yy])) {
			x.add(xx+1);
			y.add(yy);
			visited[xx+1][yy]=true;
		}
		if(xx+1<r&&yy+1<c&&(arr[xx+1][yy+1]==1)&&(!visited[xx+1][yy+1])) {
			x.add(xx+1);
			y.add(yy+1);
			visited[xx+1][yy+1]=true;
		}
		if(xx+1<r&&yy-1>=0&&(arr[xx+1][yy-1]==1)&&(!visited[xx+1][yy-1])) {
			x.add(xx+1);
			y.add(yy-1);
			visited[xx+1][yy-1]=true;
		}
		if(yy-1>=0&&(arr[xx][yy-1]==1)&&(!visited[xx][yy-1])) {
			x.add(xx);
			y.add(yy-1);
			visited[xx][yy-1]=true;
		}
		if(yy+1<c&&(arr[xx][yy+1]==1)&&(!visited[xx][yy+1])) {
			x.add(xx);
			y.add(yy+1);
			visited[xx][yy+1]=true;
		}
	}
}


		public static void main(String[] args)
{
	Scanner sc = new Scanner(System.in);
	int t = sc.nextInt();
	while(t-->0) {
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		visited = new boolean[n][m];
		for(int i=0;i<n;i++) {
		   Arrays.fill(visited[i], false);
		}
		int arr[][]=new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		int count =0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j]==1&&(!visited[i][j])) {
					count++;
					dfs(arr,i,j,n,m);
				}
			}
		}
		System.out.println(count);
	}
	}
  
}
