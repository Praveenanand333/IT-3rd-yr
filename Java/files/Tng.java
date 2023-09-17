import java.io.*;

public class Tng {

    private static final String[] letters = {
            "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"
    };

    public static void generateWords(int[] phoneNumber, int currentDigit, String currentWord, PrintWriter writer) {
        if (currentDigit == 7) {
            writer.println(currentWord);
            return;
        }

        int digit = phoneNumber[currentDigit];
        String possibleLetters = letters[digit];

        for (int i = 0; i < possibleLetters.length(); i++) {
            char letter = possibleLetters.charAt(i);
            generateWords(phoneNumber, currentDigit + 1, currentWord + letter, writer);
        }
    }

    public static void main(String[] args) {
        int[] phoneNumber = {6, 8, 6, 2, 3, 7, 7}; // Replace with the desired seven-digit number

        try (PrintWriter writer = new PrintWriter(new FileWriter("word_combinations.txt"))) {
            generateWords(phoneNumber, 0, "", writer);
            System.out.println("Word combinations have been written to 'word_combinations.txt'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
