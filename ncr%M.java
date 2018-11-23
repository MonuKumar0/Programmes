import java.util.*;
import java.io.*;

public class main {
	static long M = 1000000007;
	static long[] fac = new long[5001];
	static long[] inverse = new long[5001];
	   static long power(long x, long y, long p) 
	    { 
	        long res = 1; 
	        x = x % p; 
	                      
	        while (y > 0) 
	        { 
	            if (y % 2 == 1) 
	                res = (res * x) % p; 
	            y = y >> 1; // y = y/2 
	            x = (x * x) % p; 
	        } 
	          
	        return res; 
	    } 
	    static long modInverse(long n, long p) 
	    { 
	        return power(n, p-2, p); 
	    } 
	    static long calc(long n, long r, 
	                                    long p) 
	    { 
	        if (r == 0) 
	            return 1; 
	    return (fac[(int)n]* inverse[(int)r] 
	                % p * inverse[(int)(n-r)] 
	                                    % p) % p; 
	    } 
    public static void main(String args[]) throws Exception { 
           fac[0] = 1; 
	        for (int i = 1 ;i <= 5000; i++) 
	            fac[i] = fac[i-1] * i % M; 
	        for(int i=0;i<=5000;i++){
	            inverse[i] = modInverse(fac[i],M);
	        }
	        long[][] ncr = new long[5001][5001];
	        for(int i=0;i<=5000;i++){
	            for(int j=0;j<=i;j++){
	                ncr[i][j]=calc(i,j,M);
	            }
	        }
    	}
}
