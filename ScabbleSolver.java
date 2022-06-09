import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// pulled from Coding with John
// https://www.youtube.com/watch?v=urqlvUX-Q-Q

public class ScabbleSolver {
    public static void main(String[] args) throws IOException {

        // Ai.getLettersOwned(); isn't working

        Scanner scanner = new Scanner(System.in);
        System.out.println("This is where your letters go");
        // Input the letters from the AI Rack here
        // Use something else besides next line
        String letters = scanner.nextLine().toUpperCase();

        Map<Character, Integer> lettersCountMap = getCharacterCountMap(letters);

        BufferedReader reader = new BufferedReader(
                new FileReader("C:/Users/Nightmare357/Scrabble/javaScrabble/Assets/dictionary2.txt"));

        System.out.println("Please print the usable words Java I'm begging you");

        for (String currentWord = reader.readLine(); currentWord != null; currentWord = reader.readLine()) {
            Map<Character, Integer> currentWordMap = getCharacterCountMap(currentWord);

            boolean canMakeCurrentWord = true;
            for (Character character : currentWordMap.keySet()) {
                int currentWordCharCount = currentWordMap.get(character);
                int lettersCharCount = lettersCountMap.containsKey(character) ? lettersCountMap.get(character) : 0;

                if (currentWordCharCount > lettersCharCount) {
                    canMakeCurrentWord = false;
                    break;
                }
            }
            if (canMakeCurrentWord) {
                System.out.println(currentWord);
            }
            scanner.close();
            // reader.close(); makes the system crash and idk why
            // but without it, the thing keeps leaving me in the terminal
        }
    }

    private static Map<Character, Integer> getCharacterCountMap(String letters) {
        Map<Character, Integer> lettersCountMap = new HashMap<>();

        for (int i = 0; i < letters.length(); i++) {
            char currentChar = letters.charAt(i);

            int count = lettersCountMap.containsKey(currentChar) ? lettersCountMap.get(currentChar) : 0;
            lettersCountMap.put(currentChar, count + 1);
        }
        return lettersCountMap;
    }
}
