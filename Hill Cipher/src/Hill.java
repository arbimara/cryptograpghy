import java.util.Scanner;


public class Hill {
    public static void cofactor(int A[][],int temp[][],int row,int col,int n){
        
        int a=0,b=0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=row && j!=col)
                {
                    temp[a][b]=A[i][j];
                    b++;
                    if(b==n-1)
                    {
                        b=0;
                        a++;
                    }
                }
            }
        
        }
    }

    public static int determinant(int A[][],int n){
        
        if(n==1)
        {
            return A[0][0];
        }
        int[][] temp=new int[n][n];
        int det=0;
        for(int i=0;i<n;i++)
        {
            cofactor(A,temp,0,i,n);
            det=det+(int)Math.pow(-1, i)*A[0][i]*determinant(temp,n-1);
        }
        return det;
    }
    
    public static void adjoint(int A[][],int adj[][],int n){
        
        int[][] temp=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                cofactor(A,temp,i,j,n);
                adj[j][i]=mod((int)Math.pow(-1, i+j)*determinant(temp,n-1),26);
            }
        }
    }
    
    public static int modInverse(int a,int b){
        
        for(int i=0;i<b;i++)
        {
            if(mod(a*i,b)==1)
                return i;
        }
        return 0;
    }
    
    public static int mod(int a,int b){
        
        while(a<0)
        {
            a=a+b;
        }
         return a%b;
    }
    
    public static void keyMatrixGen(String K,int row,int col,int key[][]){
        
        int k=0;
        int temp=0;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                if(k==K.length())
                {
                    key[i][j]=temp;
                    temp++;
                }
                else
                {
                    key[i][j]=(int)(K.charAt(k)%65);
                    k++;
                }
            }
        }
    }
    
    public static void matrixGen(String K,int row,int col,int txtMatrix[][]){
        
        int k=0;
        for(int j=0;j<col;j++)
        {
            for(int i=0;i<row;i++)
            {
                if(k==K.length())
                {
                    txtMatrix[i][j]=23;
                }
                else
                {
                    txtMatrix[i][j]=(int)(K.charAt(k)%65);
                    k++;
                }
            }
        }
    }
    
    public static void matrixMul(int A[][],int B[][],int product[][]){
        
        int R=A.length; //number of rows in A
        int C=B[0].length;//number of columns in B
        int K=A[0].length;//number of colums in A
        for(int i=0;i<R;i++)
        {
            for(int j=0;j<C;j++)
            {
                product[i][j]=0;
                for(int k=0;k<K;k++)
                {
                    product[i][j]+=A[i][k]*B[k][j];
                }
                product[i][j]=mod(product[i][j],26);
            }
        }
    }
    
    public static String txtGen(int Cipher[][]){
        
        int R=Cipher.length;
        int C=Cipher[0].length;
        char[] cipher=new char[R*C];
        int k=0;
        for(int j=0;j<C;j++)
        {
            for(int i=0;i<R;i++)
            {
                cipher[k++]=(char)(Cipher[i][j]+'A');
            }
        }
        String cipherTxt=String.valueOf(cipher);
        return cipherTxt;
    }

    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        
        
        System.out.println("Enter the Plain text:");
        String M= sc.nextLine();
         System.out.println("Enter the key:");
        String K= sc.nextLine();
        
        int order=(int)Math.ceil(Math.sqrt(K.length()));//order of key matrix
        int count=(int)Math.ceil((float)M.length()/(float)order);//number of sub strings of plain text

        int[][] intKey=new int[order][order];
        int[][] intPlainTxt=new int[order][count];
        int[][] intCipherTxt=new int[order][count];
        
        keyMatrixGen(K,order,order,intKey);//Generate key matrix and stores in intKey
        
        for(int i=0;i<order;i++)
        {
        	for(int j=0;j<order;j++)
        	{
        		System.out.print(intKey[i][j]+" ");
        	}
        	System.out.println("");
         }
  
        matrixGen(M,order,count,intPlainTxt);//Generate plain text column matrix and stores in intPlainTxt 
        
        for(int i=0;i<order;i++)
         {
        	for(int j=0;j<count;j++)
        	{
        		System.out.print(intPlainTxt[i][j]+" ");
        	}
        	System.out.println("");
        }
        
        matrixMul(intKey,intPlainTxt,intCipherTxt);//Multiplies the Matrix intKey and inPlainTxt then stores in intCipherText
        
        String C=txtGen(intCipherTxt);  
        
        System.out.println("ENCRYPTION :");
        System.out.println("The Plain Text : "+M);
        System.out.println("The Key : "+K);
        System.out.println("The Cipher Text : "+C);
        
        //DECRYPTION
        count=(int)Math.ceil((float)C.length()/(float)order);//number of sub strings of cipher text
        order=(int)Math.ceil(Math.sqrt(K.length()));//order of key matrix
        keyMatrixGen(K,order,order,intKey);//Generate key matrix and stores in intKey
        matrixGen(C,order,count,intCipherTxt);//Generate cipher text column matrix and stores in intCipherTxt
        
        int det=determinant(intKey,order);
        if(det==0)
            System.out.println("Cannot decrypt because the key matrix is a singular matrix");
        else
        {
            int[][] adj=new int[order][order];
            int invDet=modInverse(det, 26);
            adjoint(intKey,adj,order);
            int[][] invKey=new int[order][order];
            
            for(int i=0;i<order;i++)
            {
                for(int j=0;j<order;j++)
                {
                    invKey[i][j]=mod(invDet*adj[i][j],26);
                }
            }
            
            matrixMul(invKey,intCipherTxt,intPlainTxt);
            
            M=txtGen(intPlainTxt);
            
            System.out.println("\nDECRYPTION :");
            System.out.println("The Cipher Text : "+C);
            System.out.println("The Key : "+K);
            System.out.println("The Plain Text : "+M);
        } 
    }
    
}

	
