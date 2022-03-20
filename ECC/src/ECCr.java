import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.util.Scanner;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class ECCr {
    public static void main(String[] args) throws Exception
    {
    	Scanner sc=new Scanner(System.in);
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
        ecKeyGen.initialize(new ECGenParameterSpec("secp256r1"));

        KeyPair ecKeyPair = ecKeyGen.generateKeyPair();
        

        Cipher iesCipher = Cipher.getInstance("ECIESwithAES-CBC");
        Cipher iesDecipher = Cipher.getInstance("ECIESwithAES-CBC");
        iesCipher.init(Cipher.ENCRYPT_MODE, ecKeyPair.getPublic());
        
        System.out.println("Enter the Plain Text:");
        String message = sc.nextLine();

        byte[] ciphertext = iesCipher.doFinal(message.getBytes());
        System.out.println("Cipher Text:"+Hex.toHexString(ciphertext));

        iesDecipher.init(Cipher.DECRYPT_MODE, ecKeyPair.getPrivate(), iesCipher.getParameters());
        byte[] plaintext = iesDecipher.doFinal(ciphertext);

        System.out.println("The Plain Text:"+ new String(plaintext));
    }

}
