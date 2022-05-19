import java.util.HashMap;

public class GameLogic {

    // will compare user input to words in wordbank
    public static boolean letterComparison(String word){
        if(wordStorage.wordbank.containsKey(word.toUpperCase())){
            return true;
        }
        return false;
    }
    
    // random letter bag
    public static String randomLetter(HashMap<Letters, Integer> bag){
        
        
        return "nothing";
    }
}
