import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

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

    public static void askForWord(HashMap<Character, Integer> letterScore, Scanner ask){
        String word = ask.next().toUpperCase();
        tempStorage = word.toCharArray();
        for(int i = 0; i< tempStorage.length; i++){
            if(tempStorage[i] == '['){
                System.out.println("please pick a blank letter");
                tempStorage[i] = ask.next().toUpperCase().charAt(0);
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
        char random = '|';
        if(bag.isEmpty()){
            return random;
        }
        Set<Character> bagSet = bag.keySet();
        int r = (int)(Math.random()*bagSet.size());
        int i = 0;
        for(char c : bagSet){
            if(i == r){
                random=c;
            }
            i++;
        }
        // 91 = [ or Z+1
        if(random == 91){
            random = ' ';
        }
        //recursion
        if(bag.get(random) == 0){
            bag.remove(random);
            return randomLetter(bag);
        }
        //decrimetns letter count by 1
        bag.put(random, bag.get(random)-1);
        return random;
    }
}
