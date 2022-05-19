import java.io.IOException;
import java.util.HashMap;

public class main{

    private static HashMap<Letters, Integer> letterCount = new HashMap<Letters, Integer>();
// This comment worked Kyrie
    public static void intLetterCount(){
        /**
         * initializes our letter count for our bag
        **/
        
        //switch(Letters){
            
        //}
    }

    public static boolean run = true;
    public static void main(String[] args) throws IOException {
        wordStorage.loadingWords();
        intLetterCount();
        //degubbing();
        //while(run){
            
        //}
    }
    // invoke this method to test code out
    public static void degubbing(){
        if(wordStorage.wordbank.containsKey("WHETSTONE")){
            System.out.println("contains WHETSTONE");
        }
        //System.out.println(wordStorage.wordbank);
    }
}