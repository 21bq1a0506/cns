import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    public Main(int bitLength) {
        SecureRandom random = new SecureRandom();
        BigInteger p = new BigInteger(bitLength / 2, 100, random);
        BigInteger q = new BigInteger(bitLength / 2, 100, random);
        modulus = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        publicKey = new BigInteger("65537");
        while (phi.gcd(publicKey).intValue() > 1) {
            publicKey = publicKey.add(new BigInteger("2"));
        }

        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(privateKey, modulus);
    }

    public static void main(String[] args) {
        int bitLength = 1024;
        Main rsa = new Main(bitLength);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the message to encrypt:");
        String message = scanner.nextLine();
        scanner.close();

        BigInteger plaintext = new BigInteger(message.getBytes());
        BigInteger ciphertext = rsa.encrypt(plaintext);

        System.out.println("Ciphertext: " + ciphertext);

        BigInteger decryptedMessage = rsa.decrypt(ciphertext);
        System.out.println("Decrypted message: " + new String(decryptedMessage.toByteArray()));
    }
}
