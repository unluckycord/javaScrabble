import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assets {
    //this stores a text file to be loaded at the start
    public static ArrayList<String> logo = new ArrayList<String>();
    protected static void loadAssets() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("Assets/logo.ans"))){
            String line = br.readLine();
            while(line != null){
                logo.add(line);
                line = br.readLine();
            }
        }
    }
}
