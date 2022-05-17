import java.io.IOException;
import java.util.HashMap;

public class main{

    private HashMap<Letters, Integer> letterCount = new HashMap<Letters, Integer>();

    public static void intLetterCount(){
        //switch(Letters){
            
        //}
    }

    public static boolean run = true;
    public static void main(String[] args) throws IOException {
        
        wordStorage.loadingWords();
        degubbing();
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