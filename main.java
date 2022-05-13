import java.io.IOException;

public class Main{
    public static boolean run = true;
    public static void main(String[] args) throws IOException {
        WordStorage.loadingWords();
        degubbing();
        //while(run){

        //}
    }
    // invoke this method to test code out
    public static void degubbing(){
        if(WordStorage.wordbank.contains("WHETSTONE")){
            System.out.println("contains WHETSTONE");
        }
        //System.out.println(wordStorage.wordbank);
    }
}