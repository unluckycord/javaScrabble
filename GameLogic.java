import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class GameLogic {
    public static int total = 100;
    private static char[] tempStorage;
    private static String newLetters;
    private static ArrayList<Integer> y = new ArrayList<Integer>();
    private static ArrayList<Integer> x = new ArrayList<Integer>();
    private static ArrayList<Character> letter = new ArrayList<Character>();
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
        System.out.println(" ");
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

    public static void askForLetterAndPos(Player player, ArrayList<Character> letter, ArrayList<Integer> x,
            ArrayList<Integer> y, Scanner ask) {
        String letterInput;
        System.out.println(player.getUsername() + ": " + playersLetters(player));
        System.out.println("input your Letter and an x and y pos to play, type done to put word\n");
        letterInput = ask.next().toUpperCase();
        /*
         * if(letterInput == "*"){
         * System.out.println("please input a letter");
         * letterInput = ask.next().toUpperCase();
         * }
         */
        letter.add(letterInput.charAt(0));
        x.add(ask.nextInt());
        y.add(ask.nextInt());

    }

    // asks for a user word, still needs to be worked on
    public static int askForWord(HashMap<Character, Integer> letterScore,
            HashMap<Character, Integer> letterCount, Scanner ask, Player player,
            Board gameBoard, Ai[] ais) {
        x.clear();
        y.clear();
        letter.clear();
        int score = 0;
        word = "";

        askForLetterAndPos(player, letter, x, y, ask);
        while (letter.get(letter.size() - 1) != '.') {
            askForLetterAndPos(player, letter, x, y, ask);
        }
        for (int i = 0; i < letter.size() - 1; i++) {
            if (gameBoard.setSpace(letter.get(i), x.get(i), y.get(i))) {
                gameBoard.setSpace(letter.get(i), x.get(i), y.get(i));
            } else {
                System.out.println("space already taken");
                askForWord(letterScore, letterCount, ask, player, gameBoard, ais);
                return 0;
            }
            word += letter.get(i);
        }
        word = word.substring(0, letter.size() - 1);
        tempStorage = word.toCharArray();

        word = String.valueOf(tempStorage).toUpperCase();
        if (GameLogic.wordComparison(word)) {
            for (int i = 0; i < tempStorage.length; i++) {
                score += letterScore.get(tempStorage[i]);
            }
            player.setScore(score, player);
            System.out.println(player.getUsername() + ": " + player.getScore());
        } else {
            System.out.println("try again, not a valid word");
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
