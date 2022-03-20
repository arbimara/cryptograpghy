/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author DheepaChandhiranM
 */
public class RSA {
    
    public static long mod(long x,long y,long n){
        
        BigInteger result=BigInteger.ONE;
        BigInteger X=BigInteger.valueOf(x%n);
        while(y>0)
        {
            if(mod(y,2)==1)
            {
                result=result.multiply(X).mod(BigInteger.valueOf(n));
                y--;
            }
            if(mod(y,2)==0)
            {
                y=y/2;
                X=((X.multiply(X)).mod(BigInteger.valueOf(n)));
            }
        }
        return result.longValue();
    }
    
    public static long mod(long a,long b){
        
        while(a<0)
        {
            a+=b;
        }
        return a%b;
    }
    
    public static long modInverse(long a,long b){
        
        long q,r,t1=1,t2=0,t=0,m=b;
        a=mod(a,b);
        while(true)
        {
            q=a/b;
            r=mod(a,b);
            if(r==0)
                return mod(t,m);
            t=t1-q*t2;
            t1=t2;
            t2=t;
            a=b;
            b=r;
        }
    }
    
    public static long gcd(long a,long b){
        
        long r;
        
        while(true)
        {
            r=mod(a,b);
            
            if(r==0)
                return b;
            else
            {
                a=b;
                b=r;
            }
        }
    }
    
    public static long modInv(long a,long b){
        
        long q,r=-1,t1=1,t2=0,t=0;
        a=mod(a,b);
        while(r!=0)
        {
            q=a/b;
            r=mod(a,b);
            t=t1-q*t2;
            t1=t2;
            t2=t;
            a=b;
            b=r;
        }
        return t;
    }
    
    public static void main(String[] args) {
        
        long p,q,n,phi,M,C,e=0;
        Scanner sc=new Scanner(System.in);
        
        p=150287;
        q=150431;
        n=p*q;
       // System.out.println(n);
        phi=(p-1)*(q-1);
        for(long i=2;i<phi;i++)
        {
            if(gcd(i,phi)==1)
            {
                e=i;
                break;
            }
        }
        long d=modInverse(e,phi);
        
        //ENCRYPTION
        System.out.println("Enter the plain text : ");
        M=sc.nextLong();

        C=mod(M,e,n);
        
        System.out.println("ENCRYPTION :");
        System.out.println("The Plain Text :"+M);
        System.out.println("The Public Key :"+e);
        System.out.println("The Cipher Text:"+C);
        
        //DECRYPTION
        M=mod(C,d,n);
        
        System.out.println("DECRYPTION :");
        System.out.println("The Cipher Text:"+C);
        System.out.println("The Private Key:"+d);
        System.out.println("The Plain Text :"+M);
    }
}