import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5MACExample {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the secret key: ");
            String secretKey = scanner.nextLine();
            System.out.print("Enter the message: ");
            String message = scanner.nextLine();
            byte[] mac = generateMD5MAC(secretKey, message.getBytes());
            System.out.println("Original Message: " + message);
            System.out.println("Generated MAC: " + bytesToHex(mac));
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] generateMD5MAC(String secretKey, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Key key = new SecretKeySpec(secretKey.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        return mac.doFinal(message);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) result.append(String.format("%02x", b));
        return result.toString();
    }
}

