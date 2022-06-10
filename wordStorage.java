import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class wordStorage {

    // this is the ArrayList that holds the words
    // to reference it, use wordStroage.wordbank

    public static HashMap<String, Integer> wordbank = new HashMap<String, Integer>();

    // this method grabs words from the dictionary
    // and stores them in an ArrayList

    // code modified from stackoverflow
    // https://stackoverflow.com/questions/19844649/java-read-file-and-store-text-in-an-array
    public static void loadingWords() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader("C:/Users/Nightmare357/Scrabble/javaScrabble/dictionary.txt"))) {
            // try (BufferedReader br = new BufferedReader(new
            // FileReader("dictionary.txt"))) {
            String line = br.readLine();
            while (line != null) {
                wordbank.put(line, 0);
                line = br.readLine();
            }
        }
    }
}
