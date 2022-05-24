import java.util.HashMap;

public class GameLogic {
    public static int total = 100;
    
    // will compare user input to words in wordbank
    public static boolean wordComparison(String word){
        return wordStorage.wordbank.containsKey(word.toUpperCase());
    }
    
    // random letter bag
    public static char randomLetter(HashMap<Character, Integer> bag){
        //base case
        //random letter bag
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
