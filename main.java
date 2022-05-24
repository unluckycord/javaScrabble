import java.io.IOException;
import java.util.HashMap;

public class main{

    //test

    private static HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
    
    public static void intLetterCount(){
        // initializes our letter count for our bag
        
        for(char start = 'A'; start <= 'Z'; start++){
            letterCount.put(start, Letters.valueOf(""+start).getCount());
        }
        letterCount.put(' ', Letters.BLANK.getCount());
    }

    public static boolean run = true;
    public static void main(String[] args) throws IOException {
        wordStorage.loadingWords();
        System.out.println(GameLogic.letterComparison("word"));
        intLetterCount();
        //Assets.loadAssets();
        degubbing();
        //while(run){
            
        //}
    }
    // invoke this method to test code out
    public static void degubbing(){
        for(int i = 0; i < 100; i++){
            System.out.print(GameLogic.randomLetter(letterCount));
        }
        System.out.println(letterCount);
    }
}