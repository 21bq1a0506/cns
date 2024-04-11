import java.security.*;
import java.util.*;

public class DSE {
    public static void main(String[] args) throws Exception {
        KeyPair k = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message:");
        String m = s.nextLine();
        Signature g = Signature.getInstance("SHA256withRSA");
        g.initSign(k.getPrivate());
        g.update(m.getBytes());
        byte[] i = g.sign();
        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(i));
        Signature v = Signature.getInstance("SHA256withRSA");
        v.initVerify(k.getPublic());
        v.update(m.getBytes());
        boolean r = v.verify(i);
        System.out.println("Signature verified: " + r);
    }
}
