import java.util.Scanner;
 
public class EXAMPLE
{
   public static void main(String args[])
   {
       String str1, str2;
       Scanner scan = new Scanner(System.in);
 
       System.out.print("Enter a String : ");
       str1 = scan.nextLine();
 
       str2 = str1.replaceAll("[aeiouAEIOU]", "");
 
       System.out.print("All Vowels Removed Successfully..!!\nNew String is : ");
 
       System.out.print(str2);
   }
}