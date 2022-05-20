import java.util.HashMap;

public class GameLogic {
    public static int total = 100;
    // will compare user input to words in wordbank
    public static boolean letterComparison(String word){
        return wordStorage.wordbank.containsKey(word.toUpperCase());
    }
    
    // random letter bag
    public static char randomLetter(HashMap<Character, Integer> bag){
        //random letter bag
        
        char random = (char)(Math.random() * 27 + 'A');
        // 91 = [ or Z+1
        if(random == 91){
            random = ' ';
        }
        
        bag.put(random, bag.get(random)-1);
        total--;

        //System.out.println(total);
        
        return random;
    }
}
