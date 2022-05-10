import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class wordStorage {
    public static ArrayList<String> wordbank = new ArrayList<String>();
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
