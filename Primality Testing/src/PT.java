import java.util.Scanner;
import java.util.Random;

public class PT

{
    public boolean isPrime(long n, int iteration)

    {

        if (n == 0 || n == 1)

            return false;

        if (n == 2)

            return true;

        if (n % 2 == 0)

            return false;

        Random rand = new Random();

        for (int i = 0; i < iteration; i++)

        {

            long r = Math.abs(rand.nextLong());            

            long a = r % (n - 1) + 1;

            if (modPow(a, n - 1, n) != 1)

                return false;

        }

        return true;        

    }

    public long modPow(long a, long b, long c)

    {

        long res = 1;

        for (int i = 0; i < b; i++)

        {

            res *= a;

            res %= c;

        }

        return res % c;

    }    

    public static void main (String[] args)

    {

        Scanner sc = new Scanner(System.in);

        System.out.println("Fermat Primality Algorithm Test\n");

        PT fp = new PT();

        System.out.println("Enter number\n");

        long num = sc.nextLong();

        System.out.println("\nEnter number of iterations");

        int k = sc.nextInt();

        boolean prime = fp.isPrime(num, k);

        if (prime)

            System.out.println("\n"+ num +" is prime");

        else

            System.out.println("\n"+ num +" is composite");        

    }

}