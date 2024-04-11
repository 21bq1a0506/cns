import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.print("Secret key: ");
        String key = s.nextLine();
         System.out.print("enter the message: ");
        String message = s.nextLine();
        byte[] hmac = generateHmacSHA256(key, message.getBytes());
        System.out.println("Original Message: " + message);
        System.out.println("HMAC: " + bytesToHex(hmac));
    }

    private static byte[] generateHmacSHA256(String key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
        return mac.doFinal(message);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) result.append(String.format("%02x", b));
        return result.toString();
    }
}
