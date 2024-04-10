import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class DSE {
    public static void main(String[] args) throws Exception {
        KeyPair keyPair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the message:");
        String message = scanner.nextLine();

        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(keyPair.getPrivate());
        signer.update(message.getBytes());
        byte[] signature = signer.sign();

        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(signature));

        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(keyPair.getPublic());
        verifier.update(message.getBytes());
        boolean isVerified = verifier.verify(signature);

        System.out.println("Signature verified: " + isVerified);
    }
}
