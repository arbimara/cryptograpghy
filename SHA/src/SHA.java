import java.security.*;
import java.util.Scanner;

public class SHA {
    
    public static void main(String args[]) 
    {  
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the plain text :");
        String plainTxt=sc.nextLine();
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
            byte[] hash=md.digest(plainTxt.getBytes());
            System.out.print("The Hashed Text : ");
            for(byte b : hash) 
            {
                String st = String.format("%02X", b);
                System.out.print(st);
            }
            System.out.println("");
        }catch(Exception ex){
            System.out.println("Error : "+ex);
        }
    } 
}
