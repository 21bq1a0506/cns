import java.util.Scanner;

public class CipherExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input text from the user
        System.out.print("Enter the input text: ");
        String plainText = scanner.nextLine();

        // Get the secret key from the user
        System.out.print("Enter the secret key: ");
        int key = scanner.nextInt();

        // Apply Caesar cipher
        String caesarCipherText = caesarCipher(plainText, key);
        System.out.println("Cipher text is: " + caesarCipherText);

        // Display decrypted text for each key
        System.out.println("Decrypted text for each key:");
        for (int i = 1; i <= 26; i++) {
            String decryptedText = caesarDecipher(caesarCipherText, i);
            System.out.println("At key " + i + ": " + decryptedText);
           
        }
        System.out.println("Match found at"+key+":"+plainText);
        scanner.close();
    }

    // Caesar cipher implementation
    private static String caesarCipher(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                result.append((char) (((ch - base + key) % 26) + base));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    // Caesar decipher implementation
    private static String caesarDecipher(String text, int key) {
        return caesarCipher(text, 26 - key); // Decipher is just the cipher with the inverse key
    }
}
