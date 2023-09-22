import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicateWordsCounter {
    public static void main(String[] args) {
        String inputSentence = "This is a test. This is a Test, test.";
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Remove punctuation and split the sentence into words
        String[] words = inputSentence.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

        // Count the occurrences of each word
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        // Print duplicate words and their counts
        System.out.println("Duplicate Words and Their Counts:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
