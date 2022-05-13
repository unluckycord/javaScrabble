import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class WordStorage{
    
    //this is the ArrayList that holds the words
    //to reference it, use wordStroage.wordbank

    public static ArrayList<String> wordbank = new ArrayList<String>();
    
    //this method grabs words from the dictionary
    //and stores them in an ArrayList
    
    public static void loadingWords() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"))){
            String line = br.readLine();
            while(line != null){
                wordbank.add(line);
                line = br.readLine();
            }
        }
    }
}
