import java.io.IOException;

public class main{
    public static boolean run = true;
    public static void main(String[] args) throws IOException {
        wordStorage.loadingWords();
        degubbing();
        //while(run){

        //}
    }
    // invoke this method to test code out
    public static void degubbing(){
        if(wordStorage.wordbank.contains("APPLE")){
            System.out.println("contains apple");
        }
        System.out.println(wordStorage.wordbank);
    }
}