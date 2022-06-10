import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {
    public static int total = 100;
    private static ArrayList<String> AisWords = new ArrayList<String>();
    private static ArrayList<Integer> y = new ArrayList<Integer>();
    private static ArrayList<Integer> x = new ArrayList<Integer>();
    private static ArrayList<Character> letter = new ArrayList<Character>();
    private static ArrayList<Integer> totalPoints = new ArrayList<Integer>();
    private static String word;

    // will compare user input to words in wordbank
    public static boolean wordComparison(String word) {
        return wordStorage.wordbank.containsKey(word.toUpperCase());
    }

    // gives initial letters for players
    public static char[] intitalLetters(HashMap<Character, Integer> letterCount) {
        String playersLetters = "";
        for (int i = 0; i < 7; i++) {
            playersLetters += GameLogic.randomLetter(letterCount);
        }
        return playersLetters.toCharArray();
    }

    // returns players letters as a string with some formating done
    public static String playersLetters(Player player) {
        String letters = "";
        char[] storage = player.getLettersOwned();
        for (int i = 0; i < player.getLettersOwned().length; i++) {
            letters += "[ " + storage[i] + " ]";
        }
        return letters;
    }

    public static void askForLetterAndPos(Player player, ArrayList<Character> letter,
            ArrayList<Integer> x, ArrayList<Integer> y, Scanner ask) {
        String letterInput;
        System.out.println(player.getUsername() + ": " + playersLetters(player));
        System.out.println(
                "Input your letter first, x coordinate second, and y coordinate third. \nTo finish your turn, input a . and input any random number twice. \n");
        letterInput = ask.next().toUpperCase();
        letter.add(letterInput.charAt(0));
        x.add(ask.nextInt());
        y.add(ask.nextInt());

    }

    public static void removeLetters(Player player, ArrayList<Character> letter) {
        char[] currentLetters = player.getLettersOwned();
        ArrayList<Character> storage = new ArrayList<Character>();
        for (int i = 0; i < 7; i++) {
            storage.add(currentLetters[i]);
        }

        for (int i = 0; i < 7; i++) {
            System.out.println(storage);
            System.out.println(letter);
            char currentLetter = storage.get(i);
            for (int f = 0; i < letter.size() - 1; i++) {
                char currentPlayedLetter = letter.get(f);
                if (currentLetter == currentPlayedLetter) {
                    System.out.println("MATCH:" + letter.get(i));
                    storage.remove(f);
                    letter.remove(i);
                }
            }
        }

        char[] newCurrentLetters = new char[storage.size() - 1];
        for (int i = 0; i < storage.size() - 1; i++) {
            newCurrentLetters[i] = storage.get(i);
        }
        player.setLettersOwned(newCurrentLetters, player);
    }

    public static void newLetters(HashMap<Character, Integer> letterCount, int missingLetters, Player player) {
        String playersLetters = "";
        for (int i = 0; i < 7 - missingLetters; i++) {
            playersLetters += player.getLettersOwned();
        }
        for (int i = 0; i < missingLetters; i++) {
            playersLetters += GameLogic.randomLetter(letterCount);
        }
        player.setLettersOwned(playersLetters.toCharArray(), player);
    }

    public static int playerScore(ArrayList<Character> letter, ArrayList<Integer> x,
            ArrayList<Integer> y, Board gameBoard, HashMap<Character, Integer> letterScore) {
        // edittteedddd
        int score = 0;
        totalPoints.clear();
        for (int i = 0; i < letter.size(); i++) {
            if (gameBoard.getTripleLetterScore(x.get(i), y.get(i))) {
                totalPoints.add(letterScore.get(letter.get(i)) * 3);
                gameBoard.setTripleLetterScore(x.get(i), y.get(i), false);
            } else if (gameBoard.getDoubleLetterScore(x.get(i), y.get(i))) {
                totalPoints.add(letterScore.get(letter.get(i)) * 2);
                gameBoard.setDoubleLetterScore(x.get(i), y.get(i), false);
            } else {
                totalPoints.add(letterScore.get(letter.get(i)));
            }
        }
        for (int i = 0; i < totalPoints.size(); i++) {
            score += totalPoints.get(i);
        }
        for (int i = 0; i < totalPoints.size(); i++) {
            if (gameBoard.getTripleWordScore(x.get(i), y.get(i))) {
                gameBoard.setTripleWordScore(x.get(i), y.get(i), false);
                return score * 3;
            }
            if (gameBoard.getDoubleWordScore(x.get(i), y.get(i))) {
                gameBoard.setDoubleWordScore(x.get(i), y.get(i), false);
                return score * 2;
            }
        }
        return score;
    }

    // WordSolver in AiLogic was Editted from Coding with John
    // The rest was done by Logan and Zach
    // https://www.youtube.com/watch?v=urqlvUX-Q-Q

    public static void AiLogic(HashMap<Character, Integer> letterScore,
            HashMap<Character, Integer> letterCount, Ai Ai,
            Board gameBoard) throws IOException {
        char[] storage = Ai.getLettersOwned();
        AisWords.clear();
        letter.clear();
        x.clear();
        y.clear();
        String Randomword = "";
        boolean CanPlay = false;
        // Input the letters from the AI Rack here
        // Use something else besides next line
        String letters = "";
        for (int i = 0; i < Ai.getLettersOwned().length - 1; i++) {
            letters += storage[i];

        }

        Map<Character, Integer> lettersCountMap = getCharacterCountMap(letters);

        BufferedReader reader = new BufferedReader(
                new FileReader("C:/Users/Nightmare357/Scrabble/javaScrabble/dictionary.txt"));
        // BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));

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
                AisWords.add(currentWord);
            }
        }
        if (AisWords.size() < 1) {
            return;
        }
        Random rand = new Random();
        Randomword = AisWords.get(rand.nextInt(AisWords.size()));
        char[] Aichoice = Randomword.toCharArray();
        for (int i = 0; i < Aichoice.length; i++) {
            letter.add(Aichoice[i]);
        }
        int Randomx = (int) Math.floor(Math.random() * (15 - letter.size()) + 1);
        int Randomy = (int) Math.floor(Math.random() * (15 - letter.size()) + 1);
        for (int i = Randomx; i <= 15; i++) {
            x.add(i);
            y.add(Randomy);
        }
        for (int i = 0; i < letter.size(); i++) {
            if (gameBoard.setSpace(letter.get(i), x.get(i), y.get(i))) {
                CanPlay = true;

                gameBoard.setSpace(letter.get(i), x.get(i), y.get(i));

            } else {
                AiLogic(letterScore, letterCount, Ai, gameBoard);
            }
        }
        if (CanPlay) {
            Ai.setScore(playerScore(letter, x, y, gameBoard, letterScore), Ai);
        }
        System.out.println(Ai.getUsername() + ": " + Ai.getScore());
    }

    private static Map<Character, Integer> getCharacterCountMap(String letters) {
        Map<Character, Integer> lettersCountMap = new HashMap<>();

        for (int i = 0; i < letters.length(); i++) {
            char currentChar = letters.charAt(i);

            int count = lettersCountMap.containsKey(currentChar) ? lettersCountMap.get(currentChar) : 0;
            lettersCountMap.put(currentChar, count + 1);
        }
        return lettersCountMap;

        // set up a way to extrapolate words from an char array
        // the char array is referenced with .getLettersOwned

    }

    // asks for a user word, still needs to be worked on
    public static int playerMove(HashMap<Character, Integer> letterScore,
            HashMap<Character, Integer> letterCount, Scanner ask, Player player,
            Board gameBoard) {
        x.clear();
        y.clear();
        letter.clear();
        word = "";
        char newLetter = ' ';

        askForLetterAndPos(player, letter, x, y, ask);
        while (letter.get(letter.size() - 1) != '.') {
            askForLetterAndPos(player, letter, x, y, ask);
        }
        for (int i = 0; i < letter.size() - 1; i++) {
            if (letter.get(i) == '*') {
                System.out.println("blank tile detected, input your choice letter");
                newLetter = ask.next().toUpperCase().charAt(0);
                letter.set(i, newLetter);
            }
            word += letter.get(i);
        }
        word = word.substring(0, letter.size() - 1);
        if (GameLogic.wordComparison(word)) {
            for (int i = 0; i < letter.size() - 1; i++) {
                if (gameBoard.setSpace(letter.get(i), x.get(i), y.get(i))) {

                    gameBoard.setSpace(letter.get(i), x.get(i), y.get(i));

                } else {
                    System.out.println("space already taken");
                    playerMove(letterScore, letterCount, ask, player, gameBoard);
                    return 0;
                }

            }
            letter.remove(letter.size() - 1);
            player.setScore(playerScore(letter, x, y, gameBoard, letterScore), player);
            // removeLetters(player, letter);
            // newLetters(letterCount, 7-(letter.size()-1), player);
            System.out.println(player.getUsername() + ": " + player.getScore());
        } else {
            System.out.println("try again, not a valid word");
            playerMove(letterScore, letterCount, ask, player, gameBoard);
        }
        return 0;
    }

    // random letter bag
    public static char randomLetter(HashMap<Character, Integer> bag) {
        char random = '|';
        if (bag.isEmpty()) {
            return random;
        }
        Set<Character> bagSet = bag.keySet();
        int r = (int) (Math.random() * bagSet.size());
        int i = 0;
        for (char c : bagSet) {
            if (i == r) {
                random = c;
            }
            i++;
        }
        // 91 = [ or Z+1
        if (random == 91) {
            random = ' ';
        }
        // recursion
        if (bag.get(random) == 0) {
            bag.remove(random);
            return randomLetter(bag);
        }
        // decrimetns letter count by 1
        bag.put(random, bag.get(random) - 1);
        return random;
    }
}
