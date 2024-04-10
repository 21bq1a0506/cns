import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SHAExample {

    public static void main(String[] args) {
        try {
            // Generate a secret key for the HMAC algorithm
            String secretKey = "SecretKey123"; // Replace with your secret key

            // Message to be authenticated
            String message = "Hello, this is a secret message.";

            // Generate HMAC for the message using SHA-256
            byte[] hmac = generateHmacSHA256(secretKey, message.getBytes());

            // Print the original message and HMAC
            System.out.println("Original Message: " + message);
            System.out.println("Generated HMAC: " + bytesToHex(hmac));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    // Function to generate an HMAC for the given message using SHA-256
    private static byte[] generateHmacSHA256(String secretKey, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Key key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        return mac.doFinal(message);
    }

    // Function to convert byte array to hex string for better display
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
