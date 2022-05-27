import java.util.HashMap;
import java.util.Scanner;

public class GameLogic {
    public static int total = 100;
    
    private static char[] tempStorage;
    public static int score = 0;
    // will compare user input to words in wordbank
    public static boolean wordComparison(String word){
        return wordStorage.wordbank.containsKey(word.toUpperCase());
    }
    
    public static void intitalLetters(HashMap<Character, Integer>letterCount){
        for(int i = 0; i < 7; i++){
            System.out.print(GameLogic.randomLetter(letterCount));
        }
        System.out.println(" ");
    }

    public static void askForWord(Scanner userWord, Scanner blankLetterPick,HashMap<Character, Integer> letterScore){
        String word = userWord.next().toUpperCase();
        tempStorage = word.toCharArray();
        for(int i = 0; i< tempStorage.length; i++){
            if(tempStorage[i] == '['){
                System.out.println("please pick a blank letter");
                tempStorage[i] = blankLetterPick.next().toUpperCase().charAt(0);
            }
        }
        word = String.valueOf(tempStorage).toUpperCase();
        if(GameLogic.wordComparison(word)){
            System.out.println("that word exists");
                
            for(int i = 0; i < tempStorage.length; i++){
                score += letterScore.get(tempStorage[i]);
            }
            System.out.println(score);

        }else{
            System.out.println("try again, not a valid word");
        }

    }

    // random letter bag
    public static char randomLetter(HashMap<Character, Integer> bag){
        //base case
        char random = (char)(Math.random() * 27 + 'A');
        // 91 = [ or Z+1
        if(random == 91){
            random = ' ';
        }
        //recursion
        if(bag.get(random) == 0){
            return randomLetter(bag);
        }
        //decrimetns letter count by 1
        bag.put(random, bag.get(random)-1);
        
        return random;
    }
}
